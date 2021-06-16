package com.yc.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.bean.AdminInfo;
import com.yc.dao.AdminDAO;

public class AdminBiz {
	AdminDAO dao=new AdminDAO();
	/**
	 * 用户登录
	 * @author hp
	 *
	 */
	public AdminInfo login(AdminInfo admin) throws Exception {
		List<AdminInfo> list=dao.findByTrem(admin);
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	/**
	 * 跟据条件查询
	 * @param admin
	 * @return
	 * @throws Exception
	 */
	public List<AdminInfo> findByTrem(AdminInfo admin)throws Exception{
		return dao.findByTrem(admin);
	}
	/**
	 * 分页查询
	 * @param admin
	 * @param pageSize
	 * @param pageNum
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> findByPage(AdminInfo admin,Integer pageSize,Integer pageNum)throws Exception{
		List<AdminInfo> list=dao.findByPage(admin, pageSize, pageNum);
		int total=dao.getTotal(admin);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("total",total);
		map.put("rows",list);
		return map;
	}
	
}












