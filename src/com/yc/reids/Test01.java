package com.yc.reids;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class Test01 {
	public static void main(String[] args) {
		//连接到redis创建jedis对象
		Jedis jedis = new Jedis("localhost",6379);
		//如果设置密码需要添加以下代码
		//jedis.auth("123456");
		//添加数据字符串
		jedis. set ("name","lydia") ;
		jedis.set ("age", "12") ;
		//获取所有的键
		Set<String> set =jedis.keys("*");
		for (String key:set){
			System. out. println(key+"="+jedis.get (key));
		}
	}
}
