package com.yc.biz;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.CartVO;
import com.yc.bean.GoodsType;
import com.yc.bean.OrderInfo;
import com.yc.bean.OrderItemInfo;
import com.yc.dao.CartDAO;
import com.yc.dao.OrderDAO;
import com.yc.util.StringUtil;

public class OrderBiz {
	OrderDAO dao=new OrderDAO();
	CartDAO cartDAO=new CartDAO();
	public int genOrder(OrderInfo o, String nos, String nums) throws Exception {
		String []cnos=StringUtil.splitString(nos,",");
		String []cnums=StringUtil.splitString(nums,",");
		if(null==cnos||cnos.length<=0){
			return 0;
		}
		if(null==cnums||cnums.length<=0){
			return 0;
		}
		if(cnums.length!=cnos.length){
			return 0;
		}
		//查看购物车信息
		List<CartVO>list =cartDAO.findByCnos(cnos);//订单用不到cno
		if (null==list||list.size()<=0) {
			//System.out.println(4);
			return 0;
		}
		List<OrderItemInfo> items =new ArrayList<OrderItemInfo>();
		for(int i=0;i<list.size();i++){//循环购物车中
			CartVO vo =list.get(i);
			OrderItemInfo item =new OrderItemInfo();
			item.setOno(o.getOno()); //订单编号
			item.setGno(vo.getGno());//商品编号
			item.setNums(Integer.parseInt(cnums[i]));//数量来源于界面
			item.setPrice(vo.getPrice());//价格
			items.add(item);
		}
		return dao.genOrder(o,items,cnos);
	}
	public List<OrderItemInfo> findBytrem(OrderInfo o) throws Exception {
		List<OrderItemInfo> list=dao.findByTrem1(o);
		return list;
	}
}
