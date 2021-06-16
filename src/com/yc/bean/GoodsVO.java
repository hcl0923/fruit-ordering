package com.yc.bean;

import java.io.Serializable;

public class GoodsVO implements Serializable{

	private Integer tno;
	private String tname;
	private String pic;
	private Integer status;
	private Integer gno;
	private String gname;
	private Double price;
	private String intro;
	private Integer balance;
	private String pics;
	private String unit;
	private String qperied;
	private String weight;
	private String descr;
	private static final long serialVersionUID = -2434141779826565597L;
	public Integer getTno() {
		return tno;
	}
	public void setTno(Integer tno) {
		this.tno = tno;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getGno() {
		return gno;
	}
	public void setGno(Integer gno) {
		this.gno = gno;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
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
	@Override
	public String toString() {
		return "GoodsVO [tno=" + tno + ", tname=" + tname + ", pic=" + pic + ", status=" + status + ", gno=" + gno
				+ ", gname=" + gname + ", price=" + price + ", intro=" + intro + ", balance=" + balance + ", pics="
				+ pics + ", unit=" + unit + ", qperied=" + qperied + ", weight=" + weight + ", descr=" + descr + "]";
	}
}
