package com.yc.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.CartInfo;
import com.yc.bean.CartVO;
import com.yc.bean.MenberInfo;
import com.yc.biz.CartBiz;
import com.yc.util.LogUtil;
/**
 * 购物车控制类
 * @author hp
 *
 */
@WebServlet("/cart.action")
public class CartServlet extends BaseServlet {
	private CartBiz biz=new CartBiz();
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
		String op=request.getParameter("op");
		if("add".equals(op)){
			doAdd(request,response);
		}else if("find".equals(op)){
			doFind(request,response);
		}else if("update".equals(op)){
			doUpdate(request,response);
		}else if("delete".equals(op)) {
			doDelete(request, response);
		}
	}
	/**
	 * 查看购物车
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doFind(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		HttpSession session=request.getSession();
		MenberInfo menber=(MenberInfo) session.getAttribute("menber");
		try {
			List<CartVO> list=biz.findByMnoVO(menber.getMno());
			toPrintJson(response,list);
		}catch(Exception e){
			LogUtil.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * 添加购物车
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doAdd(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		HttpSession session=request.getSession();
		MenberInfo menber=(MenberInfo)session.getAttribute("menber");
		try {
			CartInfo t=parseRequest(request,CartInfo.class);//得到购物的东西
			t.setMno(menber.getMno());//设置menber
			int i=biz.addCart(t);
			if(i>0) {
				//添加成功，查看购物车数据
				List<CartInfo> list=biz.findByMno(menber.getMno());
				//防止别的页面加载
				session.setAttribute("carts",list);
				toPrintJson(response, 1);
			}else {
				System.out.println("weq");
				toPrintJson(response,i);	
			}
		}catch(Exception e){
			LogUtil.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	protected void doDelete(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		try {
			CartInfo t=parseRequest(request,CartInfo.class);
			int i=biz.delete(t);
			toPrintJson(response, i);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 修改购物车中的数量
	 * @param request
	 * @param response
	 */
	protected void doUpdate(HttpServletRequest request,HttpServletResponse response) {
		CartInfo t;
		try {
			t=parseRequest(request,CartInfo.class);
			int i=biz.updateCart(t);
			toPrintJson(response,i);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}







