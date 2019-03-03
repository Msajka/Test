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
 * Servlet implementation class insertsecend
 */
@WebServlet("/insertsecend")
public class insertsecend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertsecend() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		String code = "0";
		String message = "";
		String status = "0";
		//response.getWriter().append("Served at: ").append(request.getContextPath());	
		BufferedReader read = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = read.readLine()) != null) {
			sb.append(line);
		}
		String req = sb.toString();
		System.out.println(req);
		JSONObject object = JSONObject.fromObject(req);

		Connection connect = DatabaseUtil.getConnection();
		try {
		Statement statement = connect.createStatement();
		 String sql = String.format("INSERT INTO forum_second(main_id,sec_sequence,sec_content,sec_creatuser,sec_creatime,sec_resequence,sec_delete,sec_reid) VALUES (%s,%s,%s,%s,NOW(),%s,'n',%s)",
				 object.getString("main_id"),
				 object.getString("sec_sequence"),
				 object.getString("sec_content"),
				 object.getString("sec_creatuser"),
				 object.getString("sec_resequence"),
				 object.getString("sec_delete"),
				 object.getString("sec_reid"));
		 System.out.println(sql);
		 if (statement.executeUpdate(sql) > 0 ) {
			 code = "200";
			 message = "发布成功";
			 Statement statement2= connect.createStatement();
			 String sql2 = String.format("UPDATE forum_main SET cmtnum=cmtnum+1");
			 LogUtil.log(sql);
			 ResultSet result2 = statement2.executeQuery(sql2);
		 } else {
			 code = "300";
			 message = "发布失败";
		 }
		} catch (SQLException e) {
			e.printStackTrace();
			}

		response.getWriter().append(code);
	}
}
