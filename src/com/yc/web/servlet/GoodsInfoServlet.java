package com.yc.web.servlet;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.GoodsVO;
import com.yc.biz.GoodsInfoBiz;
import com.yc.util.LogUtil;
@WebServlet("/goods.action")
public class GoodsInfoServlet extends BaseServlet {
	GoodsInfoBiz biz=new GoodsInfoBiz();
	protected void doGet(HttpServletRequest request,HttpServletResponse response) {
		String op=request.getParameter("op");
		if("findPage".equals(op)) {
			doFindPage(request,response);
		}else if("finds".equals(op)) {
			doFinds(request,response);
		}
	}
	/**
	 * 根据类别查看前四条
	 * @param request
	 * @param response
	 */
	private void doFinds(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String,Object> map=biz.finds();
			toPrintJson(response,map);
		}catch(Exception e) {
			LogUtil.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * 分页类别查看前四条
	 * @param request
	 * @param response
	 */
	private void doFindPage(HttpServletRequest request, HttpServletResponse response) {
		String page =request.getParameter("page");
		String rows =request.getParameter("rows");
		try {
			GoodsVO v=parseRequest(request,GoodsVO.class);
			Map<String,Object> map=biz.findByPage(v,Integer.parseInt(page),Integer.parseInt(rows));
			toPrintJson(response,map);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
