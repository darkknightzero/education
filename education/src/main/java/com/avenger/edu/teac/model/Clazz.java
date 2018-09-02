package com.avenger.edu.teac.model;

/**
 * 班级类
 * @author zc
 * 2018年8月4日15:59:41
 */
public class Clazz {

	private int id;
	private String name;
	private int period;
	private Major major;
	
	public Clazz() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}
	
}
