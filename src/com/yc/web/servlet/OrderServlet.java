package com.yc.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.CartInfo;
import com.yc.bean.GoodsType;
import com.yc.bean.MenberInfo;
import com.yc.bean.OrderInfo;
import com.yc.bean.OrderItemInfo;
import com.yc.biz.CartBiz;
import com.yc.biz.OrderBiz;
import com.yc.util.StringUtil;

@WebServlet("/order.action")
public class OrderServlet extends BaseServlet {
	OrderBiz biz = new OrderBiz();
	private CartBiz cartBiz = new CartBiz();

	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String op=request.getParameter("op");
		if("add".equals(op)){
			doGenOrder(request,response);
		}else if("find".equals(op)){
			doFind (request,response);
		}else if("update".equals(op)){
			//doUpdate (request,response);
		}else if ("delete".equals (op)){
			//doDelete (request,response);
		}
	}

	private void doFind(HttpServletRequest request, HttpServletResponse response) {
		try {
			GoodsType bean=parseRequest(request, GoodsType.class);
			HttpSession session=request.getSession();
			OrderInfo o=(OrderInfo)session.getAttribute("order");
			List<OrderItemInfo> list=biz.findBytrem(o);
			toPrintJson(response, list);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}

	private void doGenOrder(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		MenberInfo menber=(MenberInfo)session.getAttribute("menber");
		String cnos=request. getParameter("cnos");//传过来个数组
		String cnums =request.getParameter("cnums");
		Double totalPrice =Double.parseDouble(request.getParameter("totalPrice"));
		String orderid=StringUtil.genOid(menber.getMno());//生成订单编号
		
		OrderInfo o=new OrderInfo();
		o.setOno(orderid);
		o.setPrice(totalPrice);
		session.setAttribute("order", o);
		try {
			int i=biz.genOrder(o,cnos,cnums);
			if(i>0) {
				//重新更新购物车中的数据
				List <CartInfo> list=cartBiz.findByMno(menber.getMno());
				session.setAttribute("carts", list);
				//订单生成返回订单编号
				toPrintJson(response, orderid);
			}else {
				toPrintJson(response, i);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
