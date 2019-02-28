package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.zhenzi.sms.ZhenziSmsClient;

import util.DatabaseUtil;
import util.LogUtil;


/**
 * Servlet implementation class sendSms
 */
@WebServlet("/sendSms")
public class sendSms extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sendSms() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		String code = "0";
		String phone = "";
		String message ="";
		phone = request.getParameter("account");
		String q = request.getParameter("q");
		
		
		Connection connect = DatabaseUtil.getConnection();
		try{
			Statement statement = connect.createStatement();
			String sql3 = "select account from " + "accountinfo" + " where account='" + phone + "'";
			LogUtil.log(sql3);
			ResultSet result3 = statement.executeQuery(sql3);
			if (result3.next()) { 
			code = "102";
			message = "手机号已存在";} 
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		if(code.equals("0")&& q.equals("1") || code == "102"&& q == "0")
		try {
			JSONObject json = null;
			//生成6位验证码
			String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
			//发送短信
			ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com","100809", "0c4eecfb-2348-4624-bda8-1e63cca4962a");
			String result = client.send(phone, "咻咻咻咻！！您的验证码为:" + verifyCode + "，该码有效期为5分钟，该码只能使用一次！");
			json = JSONObject.parseObject(result);
			
			LogUtil.log(json.getString("code"));
			
			
			if(json.getIntValue("code") != 0)//发送短信失败
				code = "100";
			else code = "200";
			//code =json.getString("code");
			//将验证码存到session中,同时存入创建时间
			//以json存放，这里使用的是阿里的fastjson
			HttpSession session = request.getSession();
			json = new JSONObject();
			json.put("verifyCode", verifyCode);
			json.put("createTime", System.currentTimeMillis());
			// 将认证码存入SESSION
			request.getSession().setAttribute("verifyCode", json);
			
			
		} catch (Exception e) {
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
