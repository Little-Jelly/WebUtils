package chapter05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartServlet extends HttpServlet {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		List<Book> cart = null;
		boolean purFlag = true;
		HttpSession session = req.getSession();
		if (session == null){
			purFlag = false;
		} else{
			cart = (List) session.getAttribute("cart");
			if( cart == null){
				purFlag = false;
			}
		}
		if (!purFlag){
			out.write("对不起！您还没有购买任何商品！<br />");
		} else{
			out.write("您购买的图书有：<br />");
			double price = 0;
			for( Book book : cart){
				out.write(book.getName() + "<br />");
			}
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
