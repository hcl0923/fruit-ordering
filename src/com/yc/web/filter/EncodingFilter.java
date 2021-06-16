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
 * �������Ӧ��Դ�ı��뼯����
 *-/*��Ŀ�µ�������Դ����ʼ������
 * @author hp
 *
 */
@WebFilter("/*")
public class EncodingFilter implements Filter{

	@Override
	public void destroy() {
		System.out.println("�ַ����뼯������destroy....");
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) res;
		//���ñ��뼯
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("�ַ����뼯���ˡ�������");
		chain.doFilter(request, response); //����   ��Ӧ����ͨ�� 
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("�ַ����뼯������init....");
	}
	
}
