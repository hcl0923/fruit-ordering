package com.yc.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yc.bean.AdminInfo;
import com.yc.commons.DbHelper;

public class AdminDAO implements BaseDao<AdminInfo> {
	DbHelper db=new DbHelper();
	@Override
	public int add(AdminInfo t) throws Exception {
		return 0;
	}

	@Override
	public List<AdminInfo> findByTrem(AdminInfo t) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select aid,aname,pwd,tel from admininfo where 1=1 ");
		List<Object> params=null;
		if(null!=t) {
			params=new ArrayList<Object>();
			if(null!=t.getAid()) {
				sb.append(" and aid =? ");
				params.add(t.getAid());//
			}
			if(null!=t.getAname()) {
				sb.append(" and aname=? ");
				params.add(t.getAname());
			}
			if(null!=t.getPwd()) {
				sb.append(" and pwd=MD5(?) ");
				params.add(t.getPwd());
			}
			if(null!=t.getTel()) {
				sb.append(" and tel=? ");
				params.add(t.getTel());
			}
		}
		return db.findMutipl(sb.toString(), params, AdminInfo.class);
	}
	/**
	 * 分页查询
	 * @param t
	 * @param pageSize 每页显示的行数
	 * @param pageNum 显示第几页  
	 * @return
	 * @throws SQLException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public List<AdminInfo> findByPage(AdminInfo t,Integer pageSize,Integer pageNum) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, SQLException{
		StringBuffer sb=new StringBuffer();
		sb.append("select aid,aname,pwd,tel from admininfo where 1=1 ");
		List<Object> params=null;
		if(null!=t) {
			params=new ArrayList<Object>();
			if(null!=t.getAid()) {
				sb.append(" and aid =? ");
				params.add(t.getAid());//
			}
			if(null!=t.getAname()) {
				sb.append(" and aname=? ");
				params.add(t.getAname());
			}
			if(null!=t.getPwd()) {
				sb.append(" and pwd=MD5(?) ");
				params.add(t.getPwd());
			}
			if(null!=t.getTel()) {
				sb.append(" and tel=? ");
				params.add(t.getTel());
			}
			if(null!=pageSize&&pageNum!=null) {
				sb.append(" limit "+(pageNum-1)*pageSize+","+pageSize);
			}
			System.out.println(sb.toString());
		}
		return db.findMutipl(sb.toString(), params, AdminInfo.class);
	}
	/**
	 * 分页总条数
	 * @param t
	 * @return
	 * @throws SQLException 
	 */
	public int getTotal(AdminInfo t) throws SQLException {
		StringBuffer sb=new StringBuffer();
		sb.append("select count(*) from admininfo where 1=1 ");
		List<Object> params=null;
		if(null!=t) {
			params=new ArrayList<Object>();
			if(null!=t.getAid()) {
				sb.append(" and aid =? ");
				params.add(t.getAid());//
			}
			if(null!=t.getAname()) {
				sb.append(" and aname=? ");
				params.add(t.getAname());
			}
			if(null!=t.getPwd()) {
				sb.append(" and pwd=MD5(?) ");
				params.add(t.getPwd());
			}
			if(null!=t.getTel()) {
				sb.append(" and tel=? ");
				params.add(t.getTel());
			}
		}
		return (int) db.getPolymer(sb.toString(), params);
	}
	@Override
	public int update(AdminInfo t) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public int delete(AdminInfo t) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}

}
