package com.avenger.edu.teac.service;

import java.util.List;

import com.avenger.edu.teac.model.Teacher;

public interface TeacService {

	/**
	 * 查找某个老师的信息
	 * @return
	 */
	public Teacher findOne(int id);
	
	/**
	 * 查找所有教师的信息
	 * @return
	 */
	public List<Teacher> findAll();
	
	/**
	 * 修改密码
	 * @param 教师编号
	 */
	public void changePwd(int id);
	
	/**
	 * 登录
	 * @param 教师编号
	 * @param 教师密码
	 * @return
	 */
	public boolean adminTeac(int id,String pwd);

	/**
	 * 找回密码
	 * @param 教师编号
	 * @param 身份证后六位
	 * @return
	 */
	//public boolean findPwd(int id,String card);
	
	/**
	 * 录入必修成绩
	 */
	public void resultInput();
	
	/**
	 * 录入选修成绩
	 */
	public void resultInput2();
	
	
}
