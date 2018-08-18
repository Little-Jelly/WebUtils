package chapter06;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AutoFillUserName extends HttpServlet {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		// ����û���
		String name = req.getParameter("name");
		//���û������и�ʽ����
		name = java.net.URLEncoder.encode(name, "UTF-8");
		//����һ��Cookie���󣬲����û������浽Cookie������
		Cookie nameCookie =new Cookie("username", name);
		//����Cookie�Ĺ���֮ǰ��ʱ�䣬��λΪ��
		nameCookie.setMaxAge(60);
		//��Cookie���󱣴浽�ͻ����������Cookie��
		resp.addCookie(nameCookie);
		req.getRequestDispatcher("success.jsp").forward(req, resp);
	}
}
