package com.yc.reids;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class Test01 {
	public static void main(String[] args) {
		//���ӵ�redis����jedis����
		Jedis jedis = new Jedis("localhost",6379);
		//�������������Ҫ������´���
		//jedis.auth("123456");
		//��������ַ���
		jedis. set ("name","lydia") ;
		jedis.set ("age", "12") ;
		//��ȡ���еļ�
		Set<String> set =jedis.keys("*");
		for (String key:set){
			System. out. println(key+"="+jedis.get (key));
		}
	}
}
