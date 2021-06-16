package com.yc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yc.bean.OrderInfo;
import com.yc.bean.OrderItemInfo;
import com.yc.commons.DbHelper;

public class OrderDAO implements BaseDao<OrderInfo>{
	DbHelper db=new DbHelper();
	/*OrderInfo o=new OrderInfo();
	o.setOno(orderid);*/
	public int genOrder(OrderInfo o, List<OrderItemInfo> items, String[] cnos) throws SQLException {
		//1.订单表中插入一-条数据2.订单详情表中插入多条 3.删除购物车中
		String sql01="insert into orderinfo values(?,now(),?,null,null,1,?,0)";
		StringBuffer sql02=new StringBuffer();
		sql02. append("delete from cartinfo where cno in ( ");
		StringBuffer sql03 =new StringBuffer();
		sql03. append(" insert into orderiteminfo (ono,gno,nums,price,status) values ") ;
		List<String> sqls =new ArrayList<String>();
		List<List<Object>> params =new ArrayList<List<Object>>();
		List<Object> param01=new ArrayList<Object>();
		param01.add(o.getOno());
		param01.add(o.getAno());
		param01.add(o.getPrice());
		sqls.add(sql01);
		params.add(param01);
		List<Object> param02=new ArrayList<Object>();
		for(int i=0;i<cnos.length;i++){
			if(i==cnos.length-1){
				sql02.append(" ? )");
			}else {
				sql02.append(" ?,");
			}
			param02.add(cnos[i]);
		}
		sqls.add(sql02.toString());
		params.add(param02);
		
		//购物车详情表
		List<Object> param03=new ArrayList<Object>();
		for(int i=0;i<items.size();i++){
			OrderItemInfo item =items.get(i);
			if(i==items.size()-1){
				sql03.append(" (?,?,?,?,1)");
			}else{
				sql03.append(" (?,?,?,?,1),");
			}
			param03.add(item.getOno());
			param03.add(item.getGno());
			param03.add(item.getNums());
			param03.add(item.getPrice());
		}
		sqls.add(sql03.toString());
		params.add(param03);
		System.out.println(sql02.toString());
		System.out.println(sql03.toString()) ;
		return db.update(sqls,params);
	}
	@Override
	public int add(OrderInfo t) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}
	
	public List<OrderItemInfo> findByTrem1(OrderInfo t) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select cno,mno,num,tno,g.gno,gname,price,intro,balance,pics,unit,qperied,weight,descr from cartinfo c inner join goodsinfo g on g.gno =c.gno where 1=1 ");
		List<Object> params =null;
		if (null!=t) {
			params =new ArrayList<Object>();
				if(null!=t.getOno()) {
					sb.append(" and ono =? ");
					params.add(t.getOno());
				}
		}
		sb.append(" order by cno desc ");
		return db.findMutipl(sb.toString(),params,OrderItemInfo.class);
	}
	@Override
	public int update(OrderInfo t) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}
	@Override
	public int delete(OrderInfo t) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}
	@Override
	public List<OrderInfo> findByTrem(OrderInfo t) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}
}
