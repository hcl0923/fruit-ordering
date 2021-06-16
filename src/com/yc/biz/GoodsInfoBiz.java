package com.yc.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.bean.GoodsInfo;
import com.yc.bean.GoodsType;
import com.yc.bean.GoodsVO;
import com.yc.dao.GoodsInfoDAO;
import com.yc.dao.GoodsTypeDAO;

public class GoodsInfoBiz {
	GoodsInfoDAO dao=new GoodsInfoDAO();
	GoodsTypeDAO typeDAO=new GoodsTypeDAO();
	public int add(GoodsInfo t) throws Exception {
		return dao.add(t);
	}
	/**
	 * 查看每个类别的前四条数据
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> finds() throws Exception{
		List<GoodsInfo> goods=dao.finds();
		List<GoodsType> types=typeDAO.findByTrem(null);//查看所有的类型
		//每个类型加个状态值true|false
		for(GoodsType type:types) {
			for(GoodsInfo info:goods) {
				if(type.getTno()==info.getTno()) {
					type.setStatus(2);
					break;
				}
			}
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("goods",goods);
		map.put("types",types);
		return map;
	}
	public Map<String,Object> findByPage(GoodsVO v, Integer pageNum,Integer pageSize) throws Exception{
		List<GoodsVO> list = dao.findByPage(v,pageNum,pageSize);
		int total =dao.totalPage(v) ;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total",total);
		map.put("rows",list);
		return map;
	}
}
