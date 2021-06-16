package com.yc.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yc.bean.CartInfo;
import com.yc.bean.CartVO;
import com.yc.commons.DbHelper;
/**
 * 购物车
 * @param t
 * @return
 * @throws Exception
 */
public class CartDAO implements BaseDao<CartInfo> {
	DbHelper db = new DbHelper();
	/**
	 *  添加购物车
	 */
	public int add (CartInfo t) throws Exception{
		String sql="insert into cartinfo values(null,?,?,?)";
		return db.update(sql, t.getMno(),t.getGno(),t.getNum());
	}
	/**
	 * 查看购物车信息
	 */
	public List<CartInfo> findByTrem(CartInfo t) throws Exception {
		StringBuffer sb = new StringBuffer () ;
		sb.append("select cno,mno,num,gno from cartinfo where 1=1 ");
		List<Object> params=null;
		if(null!=t) {
			params =new ArrayList<Object>();
			if(null!=t.getCno()) {
				sb.append(" and cno =? ");
				params.add(t.getCno());
			}
			if(null!=t.getMno()){
				sb.append(" and mno =? ");
				params.add(t.getMno());
			}
			if(null!=t.getGno()){
				sb.append(" and gno =? ");
				params.add(t.getGno());
			}
		}
		sb.append(" order by cno desc");
		return db.findMutipl(sb.toString(), params, CartInfo.class);
	}
	/**
	 * 修改数量
	 */
	public int update (CartInfo t) throws Exception {
		String sql="update cartinfo set num=num+1 where cno= ? ";
		return db.update(sql,t.getCno());
	}
	/**
	 * 删除购物车
	 */
	public int delete (CartInfo t) throws Exception {
		String sql="delete from cartinfo where cno =? ";
		return db.update(sql, t.getCno());
	}
	/**
	 * 查看购物车信息
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public List<CartVO> findByTrem (CartVO t) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select cno,mno,num,tno,g.gno,gname,price,intro,balance,pics,unit,qperied,weight,descr from cartinfo c inner join goodsinfo g on g.gno =c.gno where 1=1 ");
		List<Object> params =null;
		if (null!=t) {
			params =new ArrayList<Object>();
				if(null!=t.getTno()) {
					sb.append(" and tno =? ");
					params.add(t.getTno());
				}
				if(null!=t.getGno()){
					sb.append(" and g.gno =? ");
					params.add(t.getGno());
				}
				if(null!=t.getCno()){
					sb.append(" and cno =? ");
					params.add(t.getCno());
				}
				if (null!=t.getMno()){
					sb.append(" and mno =? ");
					params.add(t.getMno());
				}
		}
		sb.append(" order by cno desc ");
		return db.findMutipl(sb.toString(),params,CartVO.class);
	}
	public List<CartVO> findByCnos(String[] cnos) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, SQLException {
		StringBuffer sb = new StringBuffer();
		sb.append("select cno,mno,num,tno,g.gno,gname,price,intro,balance,pics,unit,qperied,weight,descr from cartinfo c inner join goodsinfo g on g.gno =c.gno where 1=1 ");
		List<Object> params =null;
		if(cnos.length!=0&&cnos!=null) {
			params =new ArrayList<Object>();
			for(String cno:cnos) {
				sb.append(" or cno =? ");
				params.add(cno);
			}
			return db.findMutipl(sb.toString(), params,CartVO.class);
		}
		return null;
	}
	
}