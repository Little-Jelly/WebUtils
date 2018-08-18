package chapter12;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// 设置ContentType字段值
			resp.setContentType("text/html;charset=utf-8");
			// 创建DiskFileItemFactory工厂对象
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置文件缓存目录，如果该目录不存在则创建一个新的
			File f = new File("G:\\TempFolder");
			if( !f.exists()){
				f.mkdirs();
			}
			// 设置文件缓存路径
			factory.setRepository(f);
			// 创建ServletFileUpload对象
			ServletFileUpload fileupload = new ServletFileUpload(factory);
			// 设置字符编码
			fileupload.setHeaderEncoding("utf-8");
			// 解析request，得到上传文件的FileItem对象
			List<FileItem> fileitems = fileupload.parseRequest(req);
			// 获取字符流
			PrintWriter writer = resp.getWriter();
			// 遍历集合
			for ( FileItem fileitem : fileitems){
				// 判断是否为普通字段
				if(fileitem.isFormField()){
					// 获得字段名和字段值
					String name = fileitem.getFieldName();
					if(name.equals("name")){
						// 如果文件不为空，将其保存在value中
						if(!fileitem.getString().equals("")){
							String value = fileitem.getString("utf-8");
							writer.print("上传者："+value + "<br />");
						}
					}
				}else{
					// 获取上传的文件名
					String filename = fileitem.getName();
					// 处理上传文件
					if(filename != null && !filename.equals("")){
						writer.print("上传的文件名称是：" + filename + "<br />");
						// 截取文件名
						filename = filename.substring(filename.lastIndexOf("\\")+1);
						// 文件名需要唯一
						filename = UUID.randomUUID().toString() +"_" + filename;
						// 在服务器创建同名文件
						String webPath = "/upload/";
						// 将服务器中文件夹路径与文件名组合成完整的服务器端路径
						String filepath = this.getServletContext().getRealPath(webPath + filename);
						// 创建文件
						File file = new File(filepath);
						file.getParentFile().mkdirs();
						file.createNewFile();
						// 获得上传文件流
						InputStream in = fileitem.getInputStream();
						// 使用FileOutputStream打开服务器端的上传文件
						FileOutputStream out = new FileOutputStream(file);
						// 流的对拷
						byte [] buffer = new byte[2048]; // 每次读取一个字节
						int len=0;
						// 开始读取上传文件的字节，并将其输出到服务器端的上传文件输出流中
					
						// read 有问题
						while( (len = in.read(buffer) )  != -1){
							out.write(buffer, 0, len);
						}
						// 关闭流
						out.close();
						in.close();
						// 删除临时文件
						fileitem.delete();
						writer.print("上传文件成功！<br />");
					}
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
