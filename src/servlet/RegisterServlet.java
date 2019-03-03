package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DatabaseUtil;
import util.LogUtil;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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

		String code = "";
		String message = "";
		
		BufferedReader reader = request.getReader();
		String requestStr = reader.readLine();
		LogUtil.log(requestStr); 
		
		HashMap<String, String> requestMap = parseStrToMap(requestStr);

		//response.getWriter().append("Served at: ").append(request.getContextPath());

		String status = "0";
		
		String account = requestMap.get("account");
		String password = requestMap.get("password");
		String name = requestMap.get("name");
		String gender = requestMap.get("gender");
		String school = requestMap.get("school");
		String major = requestMap.get("major");
		String classnum = requestMap.get("class");
		String studentnum = requestMap.get("studentnum");
		
		
		LogUtil.log(account + ";" + password);
 
		Connection connect = DatabaseUtil.getConnection();
		try {
			Statement statement = connect.createStatement();
			String sql = "select account from " + "account" + " where account='" + account + "'";
			LogUtil.log(sql);
			ResultSet result = statement.executeQuery(sql);
			if (result.next()) { // �ܲ鵽���˺ţ�˵���Ѿ�ע�����
				code = "100";
				message = "账号已存在";
			} 
			
			//String sql2 = "select email from " + "accountinfo" + " where email='" + email + "'";
			//LogUtil.log(sql2);
			//ResultSet result2 = statement.executeQuery(sql2);
			//if (result2.next()) { 
			//	code = "101";
			//	message = "邮箱已存在";
			//} 
		
			if (code == "0")
			{
				String sqlInsert = "insert into " + "account" + "(account, password) values('"
						+ account + "', '" + password + "')";
				LogUtil.log(sqlInsert);
				String sqlInsert2 = "insert into " + "accountinfo" + "(account, name,gender,school,major,studentnum) values('"
						+ account + "', '" + name + "', '"+ gender +"', '"+ school +"', '"+ major + "', '"+ classnum + "', '" + studentnum +  "')";
				LogUtil.log(sqlInsert2);
				
				if (statement.executeUpdate(sqlInsert) > 0 && statement.executeUpdate(sqlInsert2) > 0) {
					code = "200";
					message = "注册成功";
					
				} else {
					code = "300";
					message = "注册失败";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
 
		//response.getWriter().append("code:").append(code).append(";message:").append(message);
		response.getWriter().append(code);

	}


	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
