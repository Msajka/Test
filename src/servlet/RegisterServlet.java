package servlet;

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
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String code = "";
		String message = "";
 
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		LogUtil.log(account + ";" + password);
 
		Connection connect = DatabaseUtil.getConnection();
		try {
			Statement statement = connect.createStatement();
			String sql = "select account from " + "account" + " where account='" + account + "'";
			LogUtil.log(sql);
			ResultSet result = statement.executeQuery(sql);
			if (result.next()) { // �ܲ鵽���˺ţ�˵���Ѿ�ע�����
				code = "100";
				message = "���˺��Ѵ���";
			} else {
				String sqlInsert = "insert into " + "account" + "(account, password) values('"
						+ account + "', '" + password + "')";
				LogUtil.log(sqlInsert);
				if (statement.executeUpdate(sqlInsert) > 0) { // �������ע���߼����������˺����뵽���ݿ�
					code = "200";
					message = "ע��ɹ�";
				} else {
					code = "300";
					message = "ע��ʧ��";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
 
		response.getWriter().append("code:").append(code).append(";message:").append(message);

	}


	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
