package com.yc.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.AdminInfo;
import com.yc.biz.AdminBiz;
import com.yc.util.LogUtil;
@WebServlet("/back/admin.action")
public class AdminServlet extends BaseServlet{
	AdminBiz biz=new AdminBiz();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//一张表所有的请求处理都在Servlet类   如何区分   从页面上传过来op
		String op =request.getParameter("op") ;
		if("login".equals(op)) {
			//登录操作
			doLogin(request,response);
		}else if("find".equals(op)) {
			//查看所有
			doFind(request,response);
		}else if("findPage".equals(op)) {
			doPage(request,response);
		}
	}
	private void doPage(HttpServletRequest request, HttpServletResponse response) {
		//获取每个显示的条数以及页码数
		String page=request.getParameter("page");//页码
		String rows=request.getParameter("rows");//条数
		try {
			AdminInfo bean=parseRequest(request, AdminInfo.class);
			Map<String, Object> map=biz.findByPage(bean,Integer.parseInt(rows),Integer.parseInt(page));
			toPrintJson(response, map);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	/**
	 * 查看用户信息  根据条件查看
	 * @param request
	 * @param response
	 */
	private void doFind(HttpServletRequest request, HttpServletResponse response) {
		try {
			AdminInfo bean=parseRequest(request,AdminInfo.class);
			List<AdminInfo> list=biz.findByTrem(bean);
			System.out.print(bean+"----");//全部搜索
			toPrintJson(response,list);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void doLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			AdminInfo bean=parseRequest(request,AdminInfo.class);
			AdminInfo admin=biz.login(bean);
			//PrintWriter out=response.getWriter();
			if(null==admin) {
				//out.print(0);
				toPrintJson(response, 0);
			}else {
				request.getSession().setAttribute("admin", admin);
				//out.print(1);
				toPrintJson(response, 1);
			}
		} catch (Exception e) {
			//用日志记录
			LogUtil.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
