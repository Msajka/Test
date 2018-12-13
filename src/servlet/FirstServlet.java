package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet(
		urlPatterns = { "/FirstServlet" }, 
		initParams = { 
				@WebInitParam(name = "123", value = "456", description = "789")
		})
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String username =request.getParameter("username");
		String password =request.getParameter("password");
		String email =request.getParameter("email");
		
		System.out.println("这里是doget方法");
		System.out.println("username:"+username);
		System.out.println("password:"+password);
		System.out.println("email"+email);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String username =request.getParameter("username");
		String password =request.getParameter("password");
		String email =request.getParameter("email");
		
		System.out.println("这里是doget方法");
		System.out.println("username:"+username);
		System.out.println("password:"+password);
		System.out.println("email:"+email);
		
	}

}
