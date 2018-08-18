package chapter12;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String filename = req.getParameter("filename");
		System.out.println(filename);
		String folder = "/download/";
		resp.addHeader("Content-Type", "application/octet-stream");
		resp.addHeader("Content-Disposition", "attachment;filename="+filename);
		System.out.println("filepath: "+folder+filename);
		InputStream in = this.getServletContext().getResourceAsStream(folder+filename);
		System.out.println("in: "+in);
		OutputStream out = resp.getOutputStream();
		System.out.println("out: "+out);
		byte[] buffer = new byte[1024];
		int len;
		while( (len = in.read(buffer)) != -1){ 
			out.write(buffer, 0, len);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
