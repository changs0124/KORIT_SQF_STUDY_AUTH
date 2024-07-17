package servlet.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import entity.User;

@WebServlet("/api/mypage/edit")
public class mypageEditServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("authentication");
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		User modifyUser = User.builder()
				.userId(user.getUserId())
				.name(name)
				.email(email)
				.build();
		UserDao.updateUserInfo(modifyUser);
		user.setName(name);
		user.setEmail(email);
		
		StringBuilder responseBuilder = new StringBuilder();
		responseBuilder.append("<script>");
		responseBuilder.append("alert('수정 완료');");
		responseBuilder.append("location.href = '/ssa/mypage';");
		responseBuilder.append("</script>");
		resp.setContentType("text/html");
		resp.getWriter().println(responseBuilder.toString());
		
	}
}