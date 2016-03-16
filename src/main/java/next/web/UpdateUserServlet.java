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

import next.model.User;

@WebServlet("/user/update")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(CreateUserServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		if (user == null) {
			resp.sendRedirect("/index.jsp");
			return;
		}

		req.setAttribute("user", user);
		RequestDispatcher rd = req.getRequestDispatcher("/user/profile.jsp");
		rd.forward(req, resp);
	}

}
