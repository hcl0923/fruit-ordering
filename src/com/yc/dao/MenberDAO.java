package com.yc.dao;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.MenberInfo;
import com.yc.commons.DbHelper;

public class MenberDAO implements BaseDao<MenberInfo> {
	DbHelper db=new DbHelper();
	@Override
	public int add(MenberInfo t) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public List<MenberInfo> findByTrem(MenberInfo t) throws Exception {
		StringBuffer sb = new StringBuffer () ;
		sb. append ("select mno,nickName,realName,pwd,tel,email,photo,regDate,status from menberinfo where 1 =1 ");
		List<Object> params =null;
		if (null!=t) {
			params =new ArrayList<Object>();
			if(null!=t.getMno()) {
				sb.append(" and mno =? ");
				params.add(t.getMno());
			}
			if(null!=t.getNickName()){
				sb.append(" and nickName =? ");
				params.add (t.getNickName());
			}
			if(null!=t.getPwd()){
				sb.append(" and pwd =MD5(?) ");
				params.add( t.getPwd());
			}
			if (null!=t.getTel()){
				sb.append(" and tel =? ");
				params.add(t.getTel());
			}
			if(null!=t.getStatus()) {
				sb.append(" and status =? ");
				params.add(t.getStatus());
			}
		
			System. out.println (sb.toString());
		}
		return db.findMutipl(sb.toString(),params,MenberInfo.class);
	}

	@Override
	public int update(MenberInfo t) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public int delete(MenberInfo t) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}

	
}
