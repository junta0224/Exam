package chapter14;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import tool.Page;

@WebServlet(urlPatterns={"/chapter10/search2"})
public class Search2 extends HttpServlet {



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("search2.jsp").forward(req, resp);
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

			String keyword=req.getParameter("keyword");

//			"?"=>プレースホルダ
			PreparedStatement st=con.prepareStatement(
					"select * from product where name like "+keyword);
//			st.setStringメソッド...プリペアードステートメントのプレースホルダに値を埋め込む（バインド）
//			第一引数=プレースホルダ番号
			st.setString(1, keyword);

			ResultSet rs=st.executeQuery();

			while(rs.next()){
				out.println(rs.getInt("id"));
				out.println(":");
				out.println(rs.getString("name"));
				out.println(":");
				out.println(rs.getInt("price"));
				out.println("<br>");
			}

			st.close();
			con.close();

		}catch(Exception e){
			e.printStackTrace(out);
		}


		Page.footer(out);

	}

}
