package com.yc.reids;

import java.util.List;

import redis.clients.jedis.Jedis;

public class Test03 {
	public static void main(String[] args) {
		//����:���ʹ�����΢����Ϣ��"hello" "bye" "how are you "
		//��ֻ�������µ�10��΢����Ϣ���������µ�΢����Ϣ���
		Jedis jedis = new Jedis("localhost",6379);
		//jedis.auth("123456");
		//mysql���ݿ�����--��redis weibo������:����:�ֶ���:ֵ  weibo:uid:1
		for(int i=0;i<100;i++) {
			jedis.lpush("weibo:userid:1","hello"+i);
			//����Ѵ���10������
			if(jedis.llen("weibo:userid:1")>10 ) {
				jedis.ltrim ("weibo:userid:1", 0,9);
			}
		}
		System.out.println("��¼��������.....");
		List<String> list =jedis.lrange("weibo:userid:1", 0,-1);
		for (String s:list) {
			System.out.println(s) ;
		}
		System.out.println("��¼��ɾ��......");
		String result =null;
		while((result=jedis.lpop("weibo:userid:1"))!=null){
			System.out.println(result);
		}
	}
}
