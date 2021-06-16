package com.yc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yc.bean.GoodsInfo;
import com.yc.bean.GoodsVO;
import com.yc.commons.DbHelper;

public class GoodsInfoDAO implements BaseDao<GoodsInfo> {
	DbHelper db = new DbHelper();

	@Override
	public int add(GoodsInfo t) throws Exception {
		String sql = "insert into goodsinfo values(null,?,?,?,?,?,?,?,?,?,?)";
		return db.update(sql, t.getGname(), t.getTno(), t.getPrice(), t.getIntro(), t.getBalance(), t.getPics(),
				t.getUnit(), t.getQperied(), t.getWeight(), t.getDescr());
	}

	/**
	 * 分类查询显示每个类的前四条数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<GoodsInfo> finds() throws Exception {
		String sql = "select gno,tno,gname,price,intro,balance,pics,unit,qperied,"
				+ "weight,descr from goodsinfo gf1 where 4> "
				+ " (select count(gno) from goodsinfo gf2 where gf1.tno=gf2.tno and gf1.gno>gf2.gno) "
				+ " order by gf1.tno asc, gf1.gno desc";
		return db.findMutipl(sql, null, GoodsInfo.class);
	}

	@Override
	public List<GoodsInfo> findByTrem(GoodsInfo t) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public int update(GoodsInfo t) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public int delete(GoodsInfo t) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}

	public List<GoodsVO> findByPage(GoodsVO t, Integer pageNum, Integer pageSize) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select t.tno,tname,pic,status,gno,gname,price,intro,balance,pics,unit,qperied,"
				+ "weight,descr from goodstype t inner join goodsinfo g on g.tno=t.tno where 1 =1 ");
		List<Object> params = null;
		if (null != t) {
			params = new ArrayList<Object>();
			if (null != t.getTno()) {
				sb.append(" and t.tno =? ");
				params.add(t.getTno());
				System.out.println(t.getTno());
			}
			if (null != t.getGno()) {
				sb.append(" and gno =? ");
				params.add(t.getGno());
			}
			if (null != t.getGname()) {
				sb.append(" and gname =? ");
				params.add(t.getGname());
			}
			if (null != t.getTname()) {
				sb.append(" and tname =? ");
				params.add(t.getTname());
			}
		}
		sb.append(" order by gno desc ");
		if (pageSize != null && pageNum != null) {
			sb.append(" limit " + (pageNum - 1) * pageSize + "," + pageSize);
		}
		System.out.println(sb.toString());
		return db.findMutipl(sb.toString(), params, GoodsVO.class);
	}

	public int totalPage(GoodsVO t) throws SQLException {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) from goodstype t inner join goodsinfo g on g.tno =t.tno where 1 =1 ");
		List<Object> params=null;
		if (null!= t) {
			params = new ArrayList<Object>();
			if (null != t.getTno()) {
				sb.append(" and t.tno =? ");
				params.add(t.getTno());
			}
			if (null != t.getGno()) {
				sb.append(" and gno =? ");
				params.add(t.getGno());
			}
			if (null != t.getGname()) {
				sb.append(" and gname =? ");
				params.add(t.getGname());
			}
			if (null != t.getTname()) {
				sb.append(" and tname=?");
				params.add(t.getTname());
			}
		}
		System.out.println(sb);
		return (int) db.getPolymer(sb.toString(),params);
	}
}
