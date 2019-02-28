package servlet;

import java.io.BufferedReader;
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

import net.sf.json.JSONObject;
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
		
		response.setContentType("text/html;charset=utf-8");
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
			if (result.next()) { // �ܲ鵽���˺ţ�˵���Ѿ�ע�����
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
