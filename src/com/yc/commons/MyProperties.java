package com.yc.commons;

import java.io.IOException;
import java.util.Properties;

/**
 * 自定义类 加载配置文件 保证该对象只有一个对象
 * 单例模式
 * @author hp
 *
 */
public class MyProperties extends Properties{
	private static MyProperties instance=new MyProperties();
	private MyProperties(){
		try{
			this.load(MyProperties.class.getClassLoader().getResourceAsStream("db.properties"));//类加载器src
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static MyProperties getInstance(){
		return instance;
	}
}
