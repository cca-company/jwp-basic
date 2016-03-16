package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.model.User;

@WebServlet("/user/profile")
public class ProfileUserFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(CreateUserServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		if(user == null){
			resp.sendRedirect("/index.jsp");
			return;
		}
		
		req.setAttribute("user", user);
		RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		if(user == null){
			resp.sendRedirect("/index.jsp");
			return;
		}
		
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		User updateUser = DataBase.findUserById(user.getUserId());
		updateUser.setPassword(password);
		updateUser.setName(name);
		updateUser.setEmail(email);
		
        resp.sendRedirect("/index.jsp");
	}
	
}
