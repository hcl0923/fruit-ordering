package com.yc.reids;

import java.util.List;

import redis.clients.jedis.Jedis;

public class Test03 {
	public static void main(String[] args) {
		//需求:发送大量的微博信息如"hello" "bye" "how are you "
		//但只保存最新的10条微博信息，并将最新的微博信息输出
		Jedis jedis = new Jedis("localhost",6379);
		//jedis.auth("123456");
		//mysql数据库内容--》redis weibo表键设计:表名:字段名:值  weibo:uid:1
		for(int i=0;i<100;i++) {
			jedis.lpush("weibo:userid:1","hello"+i);
			//如果已存满10条数据
			if(jedis.llen("weibo:userid:1")>10 ) {
				jedis.ltrim ("weibo:userid:1", 0,9);
			}
		}
		System.out.println("登录后进行输出.....");
		List<String> list =jedis.lrange("weibo:userid:1", 0,-1);
		for (String s:list) {
			System.out.println(s) ;
		}
		System.out.println("登录后删除......");
		String result =null;
		while((result=jedis.lpop("weibo:userid:1"))!=null){
			System.out.println(result);
		}
	}
}
