package com.yc.biz;
import java.util.List;

import com.yc.bean.CartInfo;
import com. yc. bean. CartVO;
import com.yc.dao.CartDAO;
public class CartBiz {
	CartDAO dao=new CartDAO();
	/**
	 * ���ݻ�Ա��Ų鿴���ﳵ
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
	 * ��ӹ��ﳵ
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public int addCart(CartInfo t)throws Exception{
		return dao.add(t);//�����ڲ���
	}
	/**
	 * ɾ�����ﳵ
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public int delete(CartInfo t) throws Exception{
		return dao.delete(t);
	}
	/**
	 * �޸Ĺ��ﳵ
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public int updateCart(CartInfo t) throws Exception {
		return dao.update(t);
	}
}