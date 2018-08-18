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
		// 获得用户名
		String name = req.getParameter("name");
		//将用户名进行格式编码
		name = java.net.URLEncoder.encode(name, "UTF-8");
		//创建一个Cookie对象，并将用户名保存到Cookie对象中
		Cookie nameCookie =new Cookie("username", name);
		//设置Cookie的过期之前的时间，单位为秒
		nameCookie.setMaxAge(60);
		//将Cookie对象保存到客户端浏览器的Cookie中
		resp.addCookie(nameCookie);
		req.getRequestDispatcher("success.jsp").forward(req, resp);
	}
}
