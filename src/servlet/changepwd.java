package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DatabaseUtil;
import util.LogUtil;

/**
 * Servlet implementation class changepwd
 */
@WebServlet("/changepwd")
public class changepwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changepwd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String code = "";
		String message  = "";
		
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		Connection connect = DatabaseUtil.getConnection();
		try {
			Statement statement = connect.createStatement();
			String sql = "UPDATE account SET password = '" + password + " ' WHERE account = '" + account + "'" ;
			LogUtil.log(sql);
			ResultSet result = statement.executeQuery(sql);
			
			if (statement.executeUpdate(sql) > 0 && statement.executeUpdate(sql) > 0) { 
				code = "200";
				message = "成功";
				
			} else {
				code = "300";
				message = "失败";
			}
			
			 }catch (SQLException e) {
				e.printStackTrace();
			}
		
		response.getWriter().append(code);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
