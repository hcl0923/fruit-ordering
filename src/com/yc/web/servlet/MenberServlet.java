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
	private CartBiz cartBiz = new CartBiz();//spring�������󴴽�
	
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
	 * ����û��Ƿ��¼
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
	 * ��Ա��¼
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
				//��Ա��¼�ɹ����鿴���ﳵ�����ݲ��洢��session��
				List<CartVO> list=cartBiz.findByMnoVO(menber.getMno());//���ݻ�Ա��Ų鿴���ﳵ����Ϣֻ��Ҫ���Լ����ﳵ
				//�洢��session
				session. setAttribute("carts",list);
				//�洢��¼�û�
				session. setAttribute("menber",menber);
				toPrintJson(response, 1);
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}
}
