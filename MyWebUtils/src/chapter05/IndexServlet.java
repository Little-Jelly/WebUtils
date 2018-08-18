package chapter05;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IndexServlet extends HttpServlet {

	// �û���ʾ��ҳ����ҳ
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if ( user == null){
			resp.getWriter().print("����û�е�¼����<a href='/MyWebUtils/login.html'>��¼</a>");
		} else{
			resp.getWriter().print("���Ѿ���¼����ӭ����"+ user.getUsername() + "��");
			resp.getWriter().print("<a href='/MyWebUtils/LogoutServlet'>�˳�</a>");
			Cookie cookie = new Cookie("JSESSIONID", session.getId());
			cookie.setMaxAge(60 * 30);
			cookie.setPath("/MyWebUtils");
			resp.addCookie(cookie);
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
