package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import util.DatabaseUtil;
import util.LogUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/*response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		String code = "";
		String message = "";
 
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		LogUtil.log(account + ";" + password);
 
		Connection connect = DatabaseUtil.getConnection();
		try {
			Statement statement = connect.createStatement();
			String sql = "select account from " + "account" + " where account='" + account
					+ "' and password='" + password + "'";
			LogUtil.log(sql);
			ResultSet result = statement.executeQuery(sql);
			if (result.next()) { 
				code = "200";
				message = "登陆成功";
			} else {
 
				code = "100";
				message = "登录失败，密码不匹配或账号未注册";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
 
		//response.getWriter().apl.pend("code:").append(code).append(";message:").append(message);
		response.getWriter().append(code);
*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String account;
		String password;
		String code = "";
		String message = "";
		
		BufferedReader reader = request.getReader();
		String requestStr = reader.readLine();
		LogUtil.log(requestStr); 
		
		HashMap<String, String> requestMap = parseStrToMap(requestStr);
		account =requestMap.get("account");
		password = requestMap.get("password");
		LogUtil.log(account + ";" + password);
		 
		Connection connect = DatabaseUtil.getConnection();
		try {
			Statement statement = connect.createStatement();
			String sql = "select account from " + "account" + " where account='" + account
					+ "' and password='" + password + "'";
			LogUtil.log(sql);
			ResultSet result = statement.executeQuery(sql);
			if (result.next()) { 
				code = "200";
				message = "登陆成功";
			} else {
 
				code = "100";
				message = "登录失败，密码不匹配或账号未注册";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
 
		//response.getWriter().apl.pend("code:").append(code).append(";message:").append(message);
		response.getWriter().append(code);

	}
	
	
	
	private HashMap<String, String> parseStrToMap(String str) {
			HashMap<String, String> resultMap = new HashMap<>();
			String[] items = str.split("&");
			String[] itemStrs = new String[2];
			for (String item : items) {
				itemStrs = item.split("=");
				resultMap.put(itemStrs[0], itemStrs[1]);
			}
			return resultMap;
		

	}
	
}
