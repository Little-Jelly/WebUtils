package chapter04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IncludedServlet extends HttpServlet {

	/*
	 * ������֤����������ܹ�ͨ��request����󴫵�����
	 * 1. ��������ϵ���ʾ
	 * 2. ��֤����ͨ��request����󴫵����ݵ��뷨
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.println("�й�" + "<br />");
		out.println("para1:"+ (String)req.getAttribute("para1")+ "<br />");
		out.println("URI" + req.getRequestURI()+ "<br />");
		out.println("QueryString:" + req.getQueryString() + "<br />");
		out.println("parameter p1:" + req.getParameter("p1") + "<br />");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
