package com.yc.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
/**
 * ������Servlet��װ
 * @author hp
 *
 */
public class BaseServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);//�ڲ�����get�����д���  �򻯴���   ���ܷ���get����post����  ����ͳһ����
	}
	/**
	 * ���ñ��뼯
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws UnsupportedEncodingException
	 */
	public void setCharacterEncoding(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	}
	/**
	 * �����������
	 * @param <T>
	 * @param request
	 * @param cls
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public <T> T parseRequest(HttpServletRequest request,Class<T> cls) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		T t=null;
		Field []fields=cls.getDeclaredFields();
		Method []methods=cls.getDeclaredMethods();
		//ͨ�����䴴������
		t=cls.newInstance();
		//ѭ������ ��������е����ݿ������  �����javaBean������������һ��
		
		for(Field f:fields) {//���ݷ����request��ȡֵ�Ƚ�
			String name=f.getName();
			String value=request.getParameter(name);//form���е�nameҪ��beanһ��
			if(null==value||"".equals(value)) {//���������û�д�����
				continue;
			}
			for(Method m:methods) {
				if(("set"+name).equalsIgnoreCase(m.getName())) {
					String typeName=m.getParameterTypes()[0].getName();//��ȡset�������βε���������
					if("java.lang.Integer".equals(typeName)) {
						m.invoke(t, Integer.parseInt(value));//���ݿ���ֶ���Ҫ�Ͷ�������fieldһ��
					}else if("java.lang.Double".equals(typeName)){
						m.invoke(t, Double.parseDouble(value));
					}else if("java.lang.Float".equals(typeName)){
						m.invoke(t, Float.parseFloat(value));
					}else if("java.lang.Long".equals(typeName)){
						m.invoke(t, Long.parseLong(value));
					}else if("java.lang.String".equals(typeName)){
						m.invoke(t, value);
					}else {
						//������չ
					}
				}
			}
		}
		return t;
	}
	/**
	 * ������תΪjson��ʽ�ַ������
	 * @param response
	 * @param obj
	 * @throws IOException
	 */
	public void toPrintJson(HttpServletResponse response,Object obj) throws IOException {
		Gson gson=new Gson();
		String str=gson.toJson(obj);
		PrintWriter out=response.getWriter();
		out.print(str);
	}
}
