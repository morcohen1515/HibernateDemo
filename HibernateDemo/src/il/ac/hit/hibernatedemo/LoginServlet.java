package il.ac.hit.hibernatedemo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
    	PrintWriter out = response.getWriter();
        String userName = request.getParameter("name");
        String userPassword = request.getParameter("password");

        if(userName!=null && userPassword!=null) {
            try {
                HibernatDAO dao = new HibernatDAO();
                try {
                	User u = new User();
                	u = dao.login(userName, userPassword);
                    out.println("<br/>user name is " + u.getName());
                    out.println("<br/>user password is " + u.getPassword());
                    out.println("<br/>user id is " + u.getId());
                } catch (ProductManagmentException e) {
                    e.getMessage();
                }
            } catch(NumberFormatException e) {
                out.println("<br/>Problem with converting" + e.getMessage());
            }
        } else {
            out.print("<br/>Please enter all the details");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
