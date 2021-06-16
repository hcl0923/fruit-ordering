package com.yc.test;

import org.junit.Test;

import com.yc.bean.CartVO;
import com.yc.biz.CartBiz;

public class CartBizTest {
	CartBiz biz=new CartBiz();
	@Test
	public void testFindBytrem() throws Exception {
		CartVO vo=new CartVO();
		vo.setMno(4);
		System.out.println(biz.findByTrem(vo));
	}
}
