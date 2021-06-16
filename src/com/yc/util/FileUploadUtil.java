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
 * �ļ��ϴ�������
 * @author hp
 *
 */
public class FileUploadUtil {
	private static final String IMAGEPATH="../fresh_images/";
	private static final String CHARSET="UTF-8";
	/**
	 * �ļ��ϴ�
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
		//�������ϴ�����
		//�����������
		//Parse the request
		List<FileItem> items = upload.parseRequest(request);
		//����T����
		T t=cls.newInstance();
		Method []methods=cls.getDeclaredMethods();
		//ѭ���ļ���
		for (FileItem item : items) {
			if (item.isFormField()) {
				//��ȡ��Ԫ�ص�nameֵ
				String name = item.getFieldName();
				//��ȡ��Ԫ�ص�valueֵ
				String value = item.getString(CHARSET);
				for(Method m:methods) {
					if(("set"+name).equalsIgnoreCase(m.getName())) {
						String type=m.getParameterTypes()[0].getName();
						if("java.lang.Integer".equals(type)) {
							m.invoke(t, Integer.parseInt(value));//���ݿ���ֶ���Ҫ�Ͷ�������fieldһ��
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
				String fieldName=item.getFieldName();//��ȡ����Name����ֵ
				//��ȡ�ļ�����
				String name=item.getName();
				//�ļ����ڷ��������ĸ�λ��
				String path=request.getServletContext().getRealPath("/");
				//�ļ�����������
				UUID uuid=UUID.randomUUID();
				String fileName=uuid.toString()+""+name;
				//System.out.println(uuid.toString()+""+name);
				//�����ͼƬ�浽��Ŀ��Ŀ¼�£����ǵ�����������������Ŀ���ϴ���ͼƬ����ʧ
				//������webappsĿ¼����һ����Ϊimages���ļ��У����൱��һ���ļ�����Ŀ
				//D:\\tomcat\apach-tomcat-8.0\webapps\web321\
				//D:\\tomcat\apach-tomcat-8.0\webapps\images\
				//�ļ����д�뵽ָ��λ��    �ŵ���Ŀ��
	            //�����ļ�����	
	            File file=new File(path,IMAGEPATH+fileName);
				//���ļ�����д��������
				item.write(file);
				//��ȡ�洢����ļ�·��  ��δ���  �洢��image_path	
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
