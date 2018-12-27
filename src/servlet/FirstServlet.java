package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet(
		urlPatterns = { "/FirstServlet" }, 
		initParams = { 
				@WebInitParam(name = "123", value = "456", description = "789")
		})
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String idnews = "";
		String newstitle = "";
		String newscontent = "";
		String newstime = "";
		String adminname = "";
		
		//response.setContentType("text/html;charset=GB2312");
		try {
			Connection connect = DBUtil.getConnect();
			Statement statement = connect.createStatement(); // Statement可以理解为数据库操作实例，对数据库的所有操作都通过它来实现
			ResultSet res;
			String sqlQuery = "select * from news" ;
			res = statement.executeQuery(sqlQuery);
			
			 StringBuffer sb = new StringBuffer();
 
			 while(!res.isAfterLast()&&res.next()) {
				 	idnews = res.getString("idnews");
	                newstitle = res.getString("newstitle");
	                newscontent = res.getString("newscontent");
	                newstime = res.getString("newstime");
	                adminname = res.getString("adminname");
	                response.getWriter().append(idnews);
	                response.getWriter().append(newstitle);
	                response.getWriter().append(newscontent);
	                response.getWriter().append(newstime);
	                response.getWriter().append(adminname);
	               
	              }
		
	       	 res.close();
	       	 statement.close();
	       	 connect.close();
	       	        
	        }catch (SQLException e) {
	    			e.printStackTrace();}
		
		 	/**HashMap<String, String> 
	        map = new HashMap<>();
	        map.put( "idnews",idnews);
	        map.put("newstitle", newstitle);
	        map.put("newscontents", newscontents);
	        map.put("newstime", newstime);
	        map.put("adminname", adminname);

			response.setContentType("text/html;charset=utf-8"); // 设置响应报文的编码格式
	        PrintWriter pw = response.getWriter(); // 获取 response 的输出流
	        pw.println(map.toString()); // 通过输出流把业务逻辑的结果输出
	        pw.flush();
	        response.getWriter().append(idnews);
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String username =request.getParameter("username");
		String password =request.getParameter("password");
		String email =request.getParameter("email");
		
		System.out.println("这里是doget方法");
		System.out.println("username:"+username);
		System.out.println("password:"+password);
		System.out.println("email:"+email);
		
	}

}
