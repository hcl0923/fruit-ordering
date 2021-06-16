package com.yc.biz;
import java.util.List;

import com.yc.bean.CartInfo;
import com. yc. bean. CartVO;
import com.yc.dao.CartDAO;
public class CartBiz {
	CartDAO dao=new CartDAO();
	/**
	 * 根据会员编号查看购物车
	 * @param mno
	 * @return
	 * @throws Exception
	 */
	public List<CartVO> findByMnoVO(int mno) throws Exception{
		CartVO vo= new CartVO();
		vo.setMno(mno);
		return dao.findByTrem(vo);
	}
	public List<CartVO> findByTrem(CartVO t) throws Exception{
		return dao.findByTrem(t);
	}
	
	public List<CartInfo> findByMno(int mno) throws Exception{
		CartInfo t= new CartInfo();
		t.setMno(mno);
		return dao.findByTrem(t);
	}
	/**
	 * 添加购物车
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public int addCart(CartInfo t)throws Exception{
		return dao.add(t);//不存在插入
	}
	/**
	 * 删除购物车
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public int delete(CartInfo t) throws Exception{
		return dao.delete(t);
	}
	/**
	 * 修改购物车
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public int updateCart(CartInfo t) throws Exception {
		return dao.update(t);
	}
}