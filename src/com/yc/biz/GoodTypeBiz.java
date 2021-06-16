package com.yc.biz;

import java.util.List;

import com.yc.bean.GoodsType;
import com.yc.dao.GoodsTypeDAO;

public class GoodTypeBiz {
	GoodsTypeDAO dao=new GoodsTypeDAO();
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<GoodsType> findBytrem(GoodsType t)throws Exception {
		// TODO 自动生成的方法存根
		return dao.findByTrem(t);
	}

}
