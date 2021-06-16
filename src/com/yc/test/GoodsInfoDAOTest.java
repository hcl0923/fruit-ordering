package com.yc.test;

import org.junit.Test;

import com.yc.dao.GoodsInfoDAO;

public class GoodsInfoDAOTest {
	GoodsInfoDAO dao=new GoodsInfoDAO();
	@Test
	public void testFind()throws Exception{
		System.out.println(dao.findByPage(null,null,null));
	}
}
