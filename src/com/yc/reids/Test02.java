package com.yc.reids;

import redis.clients.jedis.Jedis;

public class Test02 {
	public static void main(String[] args) {
		//完成一个抽奖小项目 用户信息随机抽取一一个作为幸运用户
		Jedis jedis=new Jedis("localhost", 6379);
		//jedis.auth("123456");
		//键如何设计yc_1 -- yonghuming,
		for(int i=1;i<=10;i++) {
			jedis.set("yc_"+i,"Lili_Yang"+i);
		}
		//抽奖.
		String key=jedis.randomKey();
		String uname=jedis.get (key);
		System.out.println("当日幸运用户: "+uname);
	}
}
