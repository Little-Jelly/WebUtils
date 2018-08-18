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
			// ����ContentType�ֶ�ֵ
			resp.setContentType("text/html;charset=utf-8");
			// ����DiskFileItemFactory��������
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// �����ļ�����Ŀ¼�������Ŀ¼�������򴴽�һ���µ�
			File f = new File("G:\\TempFolder");
			if( !f.exists()){
				f.mkdirs();
			}
			// �����ļ�����·��
			factory.setRepository(f);
			// ����ServletFileUpload����
			ServletFileUpload fileupload = new ServletFileUpload(factory);
			// �����ַ�����
			fileupload.setHeaderEncoding("utf-8");
			// ����request���õ��ϴ��ļ���FileItem����
			List<FileItem> fileitems = fileupload.parseRequest(req);
			// ��ȡ�ַ���
			PrintWriter writer = resp.getWriter();
			// ��������
			for ( FileItem fileitem : fileitems){
				// �ж��Ƿ�Ϊ��ͨ�ֶ�
				if(fileitem.isFormField()){
					// ����ֶ������ֶ�ֵ
					String name = fileitem.getFieldName();
					if(name.equals("name")){
						// ����ļ���Ϊ�գ����䱣����value��
						if(!fileitem.getString().equals("")){
							String value = fileitem.getString("utf-8");
							writer.print("�ϴ��ߣ�"+value + "<br />");
						}
					}
				}else{
					// ��ȡ�ϴ����ļ���
					String filename = fileitem.getName();
					// �����ϴ��ļ�
					if(filename != null && !filename.equals("")){
						writer.print("�ϴ����ļ������ǣ�" + filename + "<br />");
						// ��ȡ�ļ���
						filename = filename.substring(filename.lastIndexOf("\\")+1);
						// �ļ�����ҪΨһ
						filename = UUID.randomUUID().toString() +"_" + filename;
						// �ڷ���������ͬ���ļ�
						String webPath = "/upload/";
						// �����������ļ���·�����ļ�����ϳ������ķ�������·��
						String filepath = this.getServletContext().getRealPath(webPath + filename);
						// �����ļ�
						File file = new File(filepath);
						file.getParentFile().mkdirs();
						file.createNewFile();
						// ����ϴ��ļ���
						InputStream in = fileitem.getInputStream();
						// ʹ��FileOutputStream�򿪷������˵��ϴ��ļ�
						FileOutputStream out = new FileOutputStream(file);
						// ���ĶԿ�
						byte [] buffer = new byte[2048]; // ÿ�ζ�ȡһ���ֽ�
						int len=0;
						// ��ʼ��ȡ�ϴ��ļ����ֽڣ�������������������˵��ϴ��ļ��������
					
						// read ������
						while( (len = in.read(buffer) )  != -1){
							out.write(buffer, 0, len);
						}
						// �ر���
						out.close();
						in.close();
						// ɾ����ʱ�ļ�
						fileitem.delete();
						writer.print("�ϴ��ļ��ɹ���<br />");
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
