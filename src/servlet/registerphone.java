package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import util.LogUtil;

/**
 * Servlet implementation class registerphone
 */
@WebServlet("/registerphone")
public class registerphone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerphone() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String verifycode = "0";
		String code = "0";
		verifycode = request.getParameter("verifycode");
		
			JSONObject json = (JSONObject)request.getSession().getAttribute("verifyCode");
			
			
			LogUtil.log(json.getString("verifyCode"));	
			if(!json.getString("verifyCode").equals(verifycode)){
				code = "100";
			}
			else if((System.currentTimeMillis() - json.getLong("createTime")) > 1000 * 60 * 5){
				code = "101";
			}
			else
			code = "200";
			
			
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
