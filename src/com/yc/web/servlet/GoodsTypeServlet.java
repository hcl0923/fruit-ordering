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
import com.yc.bean.GoodsType;
import com.yc.biz.GoodTypeBiz;

@WebServlet("/goodsType.action")
public class GoodsTypeServlet extends BaseServlet {
	GoodTypeBiz biz=new GoodTypeBiz();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//һ�ű����е���������Servlet��   �������   ��ҳ���ϴ�����op
		String op =request.getParameter("op") ;
		if("find".equals(op)) {
			//��¼����
			doFind(request,response);
		}
	}
	/**
	 * �鿴��Ʒ����
	 * @param request
	 * @param response
	 */
	private void doFind(HttpServletRequest request, HttpServletResponse response) {
		try {
			GoodsType bean=parseRequest(request, GoodsType.class);
			List<GoodsType> list=biz.findBytrem(bean);
			toPrintJson(response, list);
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
}
