package com.yc.dao;
import java.util.ArrayList;
import java.util.List;

import com.yc.bean.GoodsType;
import com.yc.commons.DbHelper;

public class GoodsTypeDAO implements BaseDao<GoodsType>{
	DbHelper db=new DbHelper();

	@Override
	public int add(GoodsType t) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public List<GoodsType> findByTrem(GoodsType t) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select tno,tname,pic,status from goodstype where 1=1 ");
		List<Object> params=null;
		if(null!=t) {
			params=new ArrayList<Object>();
			if(null!=t.getTno()) {
				sb.append(" and tno =? ");
				params.add(t.getTno());//
			}
			if(null!=t.getTname()) {
				sb.append(" and tname=? ");
				params.add(t.getTname());
			}
			if(null!=t.getStatus()) {
				sb.append(" and status= ? ");
				params.add(t.getStatus());
			}
		}
		sb.append(" order by tno asc ");
		return db.findMutipl(sb.toString(), params, GoodsType.class);
	}

	@Override
	public int update(GoodsType t) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public int delete(GoodsType t) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}
	
}
