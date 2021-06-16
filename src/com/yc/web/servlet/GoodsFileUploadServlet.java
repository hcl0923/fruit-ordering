package com.yc.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.GoodsInfo;
import com.yc.biz.GoodsInfoBiz;
import com.yc.util.FileUploadUtil;
@WebServlet("/goodFileUpload.action")
public class GoodsFileUploadServlet extends BaseServlet {
	GoodsInfoBiz biz=new GoodsInfoBiz();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//解析请求对象  文件上传
		try {
			GoodsInfo bean=FileUploadUtil.parseRequest(req, GoodsInfo.class);
			System.out.print(bean);
			//调用业务层
			int i=biz.add(bean);
			toPrintJson(resp, i);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
