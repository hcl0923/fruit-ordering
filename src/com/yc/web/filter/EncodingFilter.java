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
/**
 * 请求和响应资源的编码集设置
 *-/*项目下的所有资源都初始化设置
 * @author hp
 *
 */
@WebFilter("/*")
public class EncodingFilter implements Filter{

	@Override
	public void destroy() {
		System.out.println("字符编码集过滤器destroy....");
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) res;
		//设置编码集
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("字符编码集过滤。。。。");
		chain.doFilter(request, response); //请求   响应对象通过 
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("字符编码集过滤器init....");
	}
	
}
