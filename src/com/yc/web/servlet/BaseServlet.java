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
 * 基本的Servlet封装
 * @author hp
 *
 */
public class BaseServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);//内部调用get请求中代码  简化代码   不管发生get还是post请求  最终统一处理
	}
	/**
	 * 设置编码集
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws UnsupportedEncodingException
	 */
	public void setCharacterEncoding(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	}
	/**
	 * 解析请求对象
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
		//通过反射创建对象
		t=cls.newInstance();
		//循环属性 请求对象中的数据库参数名  必须和javaBean对象中属性名一致
		
		for(Field f:fields) {//根据反射从request中取值比较
			String name=f.getName();
			String value=request.getParameter(name);//form表中的name要和bean一致
			if(null==value||"".equals(value)) {//请求对象中没有此数据
				continue;
			}
			for(Method m:methods) {
				if(("set"+name).equalsIgnoreCase(m.getName())) {
					String typeName=m.getParameterTypes()[0].getName();//获取set方法的形参的数据类型
					if("java.lang.Integer".equals(typeName)) {
						m.invoke(t, Integer.parseInt(value));//数据库的字段名要和对象属性field一致
					}else if("java.lang.Double".equals(typeName)){
						m.invoke(t, Double.parseDouble(value));
					}else if("java.lang.Float".equals(typeName)){
						m.invoke(t, Float.parseFloat(value));
					}else if("java.lang.Long".equals(typeName)){
						m.invoke(t, Long.parseLong(value));
					}else if("java.lang.String".equals(typeName)){
						m.invoke(t, value);
					}else {
						//后期拓展
					}
				}
			}
		}
		return t;
	}
	/**
	 * 将数据转为json格式字符串输出
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
