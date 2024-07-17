package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import entity.User;

// @WebFilter({"/mypage", "/mypage/password"})
@WebFilter(filterName = "securityFilter")
public class SecurityFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		
		User user = (User) session.getAttribute("authentication"); 
		if(user == null) {
//			response.setContentType("text/plain");
//			response.getWriter().println("로그인 후에 접근가능한 페이지");
//			return;
			StringBuilder responseBuilder = new StringBuilder();
			responseBuilder.append("<script>");
			responseBuilder.append("alert('잘못된 접근입니다.\\n로그인 후에 이용바랍니다.');");
			responseBuilder.append("location.href = '/ssa/Login';");
			responseBuilder.append("</script>");
			response.setContentType("text/html");
			response.getWriter().println(responseBuilder.toString());
			return;
		}
		chain.doFilter(request, response);
	}


}
