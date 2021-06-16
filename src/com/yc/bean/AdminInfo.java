package com.yc.bean;

import java.io.Serializable;
/**
 * 后台管理员信息
 * 源辰信息
 * @author hp
 *
 */
public class AdminInfo implements Serializable{

	private static final long serialVersionUID = -8948883058873079470L;
	private	Integer aid;
	private	String aname;
	private String pwd;
	private String tel;
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "AdminInfo [aid=" + aid + ", aname=" + aname + ", pwd=" + pwd + ", tel=" + tel + "]";
	}
	
}
