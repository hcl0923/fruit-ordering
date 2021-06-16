package com.yc.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.CartVO;
import com.yc.bean.MenberInfo;
import com.yc.biz.CartBiz;
import com.yc.biz.MenberBiz;

@WebServlet ("/menber.action")
public class MenberServlet extends BaseServlet{
	private MenberBiz biz=new MenberBiz();
	private CartBiz cartBiz = new CartBiz();//spring容器对象创建
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException{
		String op =request.getParameter("op");
		if("login".equals(op)) {
			doLogin(request,response);
		}else if("check".equals(op)){
			doCheck(request,response);
		}
	}
	/**
	 * 检查用户是否登录
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void doCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("menber",session.getAttribute("menber"));
		map.put("carts",session.getAttribute("carts"));
		toPrintJson(response, map);
	}
	/**
	 * 会员登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doLogin (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			MenberInfo bean =parseRequest (request, MenberInfo. class) ;
			MenberInfo menber=biz.login(bean);
			HttpSession session=request.getSession();
			if (null==menber) {
				toPrintJson (response, 0) ;
			}else{
				//会员登录成功，查看购物车中数据并存储到session中
				List<CartVO> list=cartBiz.findByMnoVO(menber.getMno());//根据会员编号查看购物车中信息只需要看自己的物车
				//存储到session
				session. setAttribute("carts",list);
				//存储登录用户
				session. setAttribute("menber",menber);
				toPrintJson(response, 1);
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}
}
