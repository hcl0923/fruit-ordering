package com.yc.bean;

import java.io.Serializable;

public class GoodsType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4860045833274080707L;
	private	Integer tno;
	private	String tname;
	private String pic;
	private Integer status;
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
	@Override
	public String toString() {
		return "GoodsType [tno=" + tno + ", tname=" + tname + ", pic=" + pic + ", status=" + status + "]";
	}
	
	
}
