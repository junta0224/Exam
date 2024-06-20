package chapter14;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import tool.Page;

@WebServlet(urlPatterns={"/chapter14/insert"})
public class Insert extends HttpServlet {



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("insert.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		Page.header(out);

		try{
			InitialContext ic=new InitialContext();
			DataSource ds=(DataSource)ic.lookup(
					"java:/comp/env/jdbc/book");
			Connection con=ds.getConnection();

			String name=req.getParameter("name");
			int price=Integer.parseInt(req.getParameter("price"));

//			"?"=>プレースホルダ
			PreparedStatement st=con.prepareStatement(
					"insert into product values(null, ?, ?)");
//			st.setStringメソッド...プリペアードステートメントのプレースホルダに値を埋め込む（バインド）
//			第一引数=プレースホルダ番号
			st.setString(1, name);
			st.setInt(2, price);

			int line=st.executeUpdate();

			if(line>0){
				out.println("追加に成功しました。");
			}

			st.close();
			con.close();

		}catch(Exception e){
			e.printStackTrace(out);
		}


		Page.footer(out);

	}

}
