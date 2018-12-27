package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.DBNames;
import net.sf.json.JSONObject;
import util.DatabaseUtil;

/**
 * Servlet implementation class newsServlet
 */
@WebServlet("/newsServlet")
public class newsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newsServlet() {
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
		BufferedReader read = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = read.readLine()) != null) {
			sb.append(line);
		}
		String req = sb.toString();
		System.out.println(req);
		
		String sql = String.format("SELECT * FROM %s", 
				"news");
		System.out.println(sql);
 
		// 自定义的结果信息类
		CommonResponse res = new CommonResponse();
		try {
			ResultSet result = DatabaseUtil.query(sql); //数据库查询操作

			while (result.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("idnews", result.getString("idnews"));
				map.put("newstitle", result.getString("newstitle"));
				map.put("newscontent", result.getString("newscontent"));
				map.put("newstime", result.getString("newstime"));
				map.put("adminname", result.getString("adminname"));
				
				res.addListItem(map);
			}
			res.setResCode("0"); // 表示业务结果正确
		} catch (SQLException e) {
			res.setResult("300", "数据库查询错误");
			e.printStackTrace();
		}
 
		String resStr = JSONObject.fromObject(res).toString();
		response.getWriter().append(resStr).flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}
}

