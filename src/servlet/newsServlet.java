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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());	
		BufferedReader read = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = read.readLine()) != null) {
			sb.append(line);
		}
		String req = sb.toString();
		System.out.println(req);
		
		String sql = String.format("SELECT * FROM %s", 
				"forum_main");
		System.out.println(sql);
 
		
		CommonResponse res = new CommonResponse();
		try {
			ResultSet result = DatabaseUtil.query(sql); 
			while (result.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("main_id", result.getString("main_id"));
				map.put("main_title", result.getString("main_title"));
				map.put("main_flag", result.getString("main_flag"));
				map.put("main_content", result.getString("main_content"));
				map.put("main_creatime", result.getString("main_creatime"));
				map.put("main_creatuser", result.getString("main_creatuser"));
				map.put("main_zan", result.getString("main_zan"));
				map.put("cmtnum", result.getString("cmtnum"));
				res.addListItem(map);
			}
			res.setResCode("0"); 
		} catch (SQLException e) {
			res.setResult("300", "fail");
			e.printStackTrace();
		}
 
		String resStr = JSONObject.fromObject(res).toString();
		response.getWriter().append(resStr).flush();
		
	}
}

