package il.ac.hit.hibernatedemo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/RegesterServlet")
public class RegesterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String userId = request.getParameter("id");
        String userName = request.getParameter("name");
        String userPassword = request.getParameter("password");
        
        HibernatDAO dao = new HibernatDAO();
        try {
			List<User> users = dao.chackIfUserExists();
			for(int i=0; i<users.size(); i++) {
				if(users.get(i).getName().equals(userName)) {
		            out.println("The user already exists");
		            return;
				}	
			}		
		} catch (ProductManagmentException e1) {
			e1.getMessage();
		}
        

        if(userId!=null && userName!=null && userPassword!=null) {
            try {
                int id = Integer.parseInt(userId.trim());
                User u = new User(id, userName, userPassword);
                try {
                    dao.signUp(u);
                } catch (ProductManagmentException e) {
                    e.getMessage();
                }
                out.println("<br/>user id is " + id);
                out.println("<br/>user name is " + userName);
                out.println("<br/>user password is " + userPassword);
            } catch(NumberFormatException e) {
                out.println("<br/>Problem with converting a string to double " + e.getMessage());
            }
        } else {
            out.print("<br/>Please enter all the details");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
