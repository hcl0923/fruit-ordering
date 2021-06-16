package com.yc.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.AdminInfo;
/**
 * 访问权限过滤
 * @author hp
 *
 */
@WebFilter(filterName="loginFilter",urlPatterns= {"/back/addGood.html","/back/findAdmin.html","/back/findGood.html","/back/findMember.html","/back/findOrder.html","/back/index.html","/back/welcome.html"})
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("loginFilter doFilter....");
		HttpServletResponse response=(HttpServletResponse)res;
		HttpServletRequest request=(HttpServletRequest)req;
		HttpSession session=request.getSession();
		AdminInfo admin=(AdminInfo)session.getAttribute("admin");
		if(admin==null) {
			//根据项目名  路径/  服务器的根目录(webApps)
			response.sendRedirect("../back/backLogin.html");
		}else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO 自动生成的方法存根
		
	}
	
}
