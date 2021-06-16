package com.yc.reids;

import java.util.List;
import java.util.Random;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class Test04 {
	public static void main(String[] args) {
		//需求: 10个用户发送大量的微博信息如"hello" "bye" "how are you
		//但只保存每个用户最新的10条微博信息，并将最新的微博信息输出
		Jedis jedis = new Jedis ("localhost", 6379);
		//jedis.auth("123456");
		Random rd = new Random();
		for(int i =0;i<100;i++) {
			int userid=rd.nextInt(10)+1;
			jedis.lpush("weibo:uid:"+userid,"he1lo"+i);
			//如果存储10条数据不同用户各自存储10条
			if(jedis.llen("weibo:uid:"+userid)>10 ){
				jedis.ltrim("weibo:uid: "+userid,0,9);
			}
		}
		//获取发送微博信息键
		Set<String> set = jedis.keys("weibo:uid:*");
		int i=0;
		for(String weibokey:set) {
			List<String> weibos=jedis.lrange(weibokey,0,-1);
			System.out.println("用户: "+weibokey+ "发布微博信息如下: ");
			for(String s:weibos){
				System.out.println("\t"+s);
				i++;
			}
		}
		System. out.println(i);
	}
}
