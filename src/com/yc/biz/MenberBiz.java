package com.yc.biz;
import java.util.List;

import com.yc.bean.MenberInfo;
import com.yc.dao.MenberDAO;
public class MenberBiz {
	MenberDAO dao = new MenberDAO();
	/**
	 * 获取登录
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public MenberInfo login (MenberInfo t) throws Exception{
		t.setStatus(1) ; //1可用 2 禁用
		List<MenberInfo> list =dao.findByTrem(t) ;
		if (null!=list &&list.size ()>0) {
			return list.get(0);
		}
		return null ;
	}
}