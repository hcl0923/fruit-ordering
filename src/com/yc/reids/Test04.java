package com.yc.reids;

import java.util.List;
import java.util.Random;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class Test04 {
	public static void main(String[] args) {
		//����: 10���û����ʹ�����΢����Ϣ��"hello" "bye" "how are you
		//��ֻ����ÿ���û����µ�10��΢����Ϣ���������µ�΢����Ϣ���
		Jedis jedis = new Jedis ("localhost", 6379);
		//jedis.auth("123456");
		Random rd = new Random();
		for(int i =0;i<100;i++) {
			int userid=rd.nextInt(10)+1;
			jedis.lpush("weibo:uid:"+userid,"he1lo"+i);
			//����洢10�����ݲ�ͬ�û����Դ洢10��
			if(jedis.llen("weibo:uid:"+userid)>10 ){
				jedis.ltrim("weibo:uid: "+userid,0,9);
			}
		}
		//��ȡ����΢����Ϣ��
		Set<String> set = jedis.keys("weibo:uid:*");
		int i=0;
		for(String weibokey:set) {
			List<String> weibos=jedis.lrange(weibokey,0,-1);
			System.out.println("�û�: "+weibokey+ "����΢����Ϣ����: ");
			for(String s:weibos){
				System.out.println("\t"+s);
				i++;
			}
		}
		System. out.println(i);
	}
}
