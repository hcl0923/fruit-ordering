package com.yc.bean;

import java.io.Serializable;
/**
 * 
 * @author hp
 *
 */
public class CartVO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 799217661278320204L;
	private Integer cno;
	private Integer mno;
	private Integer gno;
	private Integer num;
	private String gname;
	private Integer tno;
	private Double price; //单价
	private String intro; // 简介
	private Integer balance; //库存量
	private String pics; //图片
	private String unit;//单位
	private String qperied; //保质期
	private String weight; // 净重
	private String descr; // 描述
	@Override
	public String toString() {
		return "CartVO [cno=" + cno + ", mno=" + mno + ", gno=" + gno + ", num=" + num + ", gname=" + gname + ", tno="
				+ tno + ", price=" + price + ", intro=" + intro + ", balance=" + balance + ", pics=" + pics + ", unit="
				+ unit + ", qperied=" + qperied + ", weight=" + weight + ", descr=" + descr + "]";
	}
	public Integer getCno() {
		return cno;
	}
	public void setCno(Integer cno) {
		this.cno = cno;
	}
	public Integer getMno() {
		return mno;
	}
	public void setMno(Integer mno) {
		this.mno = mno;
	}
	public Integer getGno() {
		return gno;
	}
	public void setGno(Integer gno) {
		this.gno = gno;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public Integer getTno() {
		return tno;
	}
	public void setTno(Integer tno) {
		this.tno = tno;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public String getPics() {
		return pics;
	}
	public void setPics(String pics) {
		this.pics = pics;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getQperied() {
		return qperied;
	}
	public void setQperied(String qperied) {
		this.qperied = qperied;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
}