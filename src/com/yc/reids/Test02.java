package com.yc.reids;

import redis.clients.jedis.Jedis;

public class Test02 {
	public static void main(String[] args) {
		//���һ���齱С��Ŀ �û���Ϣ�����ȡһһ����Ϊ�����û�
		Jedis jedis=new Jedis("localhost", 6379);
		//jedis.auth("123456");
		//��������yc_1 -- yonghuming,
		for(int i=1;i<=10;i++) {
			jedis.set("yc_"+i,"Lili_Yang"+i);
		}
		//�齱.
		String key=jedis.randomKey();
		String uname=jedis.get (key);
		System.out.println("���������û�: "+uname);
	}
}
