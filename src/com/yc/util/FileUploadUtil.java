package com.yc.util;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 * 文件上传工具类
 * @author hp
 *
 */
public class FileUploadUtil {
	private static final String IMAGEPATH="../fresh_images/";
	private static final String CHARSET="UTF-8";
	/**
	 * 文件上传
	 * @param <T>
	 * @param request
	 * @param t
	 * @return
	 * @throws Exception 
	 */
	public static <T> T parseRequest(HttpServletRequest request,Class<T> cls) throws Exception {
		//Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		//服务器上传对象
		//解析请求对象
		//Parse the request
		List<FileItem> items = upload.parseRequest(request);
		//创建T对象
		T t=cls.newInstance();
		Method []methods=cls.getDeclaredMethods();
		//循环文件项
		for (FileItem item : items) {
			if (item.isFormField()) {
				//获取表单元素的name值
				String name = item.getFieldName();
				//获取表单元素的value值
				String value = item.getString(CHARSET);
				for(Method m:methods) {
					if(("set"+name).equalsIgnoreCase(m.getName())) {
						String type=m.getParameterTypes()[0].getName();
						if("java.lang.Integer".equals(type)) {
							m.invoke(t, Integer.parseInt(value));//数据库的字段名要和对象属性field一致
						}else if("java.lang.Double".equals(type)){
							m.invoke(t, Double.parseDouble(value));
						}else if("java.lang.Float".equals(type)){
							m.invoke(t, Float.parseFloat(value));
						}else if("java.lang.Long".equals(type)){
							m.invoke(t, Long.parseLong(value));
						}else if("java.lang.String".equals(type)){
							m.invoke(t, value);
						}else {
						
						}
						break;
					}
					
				}
				System.out.println(name+"="+value);
			} else {
				String fieldName=item.getFieldName();//获取表单的Name属性值
				//获取文件名称
				String name=item.getName();
				//文件存在服务器的哪个位置
				String path=request.getServletContext().getRealPath("/");
				//文件重命名问题
				UUID uuid=UUID.randomUUID();
				String fileName=uuid.toString()+""+name;
				//System.out.println(uuid.toString()+""+name);
				//如果把图片存到项目的目录下，但是当我重启服务器后，项目下上传的图片就消失
				//服务器webapps目录创建一个名为images的文件夹，就相当于一个文件夹项目
				//D:\\tomcat\apach-tomcat-8.0\webapps\web321\
				//D:\\tomcat\apach-tomcat-8.0\webapps\images\
				//文件如何写入到指定位置    放到项目下
	            //创建文件对象	
	            File file=new File(path,IMAGEPATH+fileName);
				//将文件对象写到磁盘中
				item.write(file);
				//获取存储后的文件路径  如何处理  存储到image_path	
				String image_path=IMAGEPATH+fileName;
				System.out.println(image_path+"_____");
				for(Method m:methods) {
					if(("set"+fieldName).equalsIgnoreCase(m.getName())) {
						m.invoke(t, image_path);
					}
				}
			}
		}
		return t;
	}
}
