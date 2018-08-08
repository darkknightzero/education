package com.avenger.edu.stu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.avenger.edu.stu.model.Grade;
import com.avenger.edu.stu.model.MajoCourInfo;
import com.avenger.edu.stu.model.PoinScor;
import com.avenger.edu.stu.model.Schedule;
import com.avenger.edu.stu.model.SeleCourInfo;
import com.avenger.edu.stu.model.Student;

@Mapper
public interface StuMapper {

	/**
	 * 登录 如果返回值不为null，表明登录成功
	 * 
	 * @param s
	 * @return 学生姓名
	 */
	@Select("select stu_name from student where stu_id=#{id} and stu_password=#{password}")
	@Results(value = { @Result(column = "stu_name", property = "name") })
	public String login(Student s);

	/**
	 * 通过学号获得学生个人信息
	 * 
	 * @param id
	 *            学生Id
	 * @return
	 */
	@Select("select stu_id,stu_name,stu_sex,stu_addr,stu_phone,"
			+ "stu_email,stu_pic,stu_clas,stu_majo,stu_col from v_stuInfo where stu_id=#{id}")
	@Results(value = { @Result(column = "stu_id", property = "stuId"),
			@Result(column = "stu_name", property = "stuName"), @Result(column = "stu_sex", property = "stuSex"),
			@Result(column = "stu_addr", property = "stuAddress"), @Result(column = "stu_phone", property = "stuPhone"),
			@Result(column = "stu_email", property = "stuEmail"), @Result(column = "stu_pic", property = "stuPic"),
			@Result(column = "stu_clas", property = "stuClazz"), @Result(column = "stu_majo", property = "stuMajor"),
			@Result(column = "stu_col", property = "stuCollege"),

	})
	public Student load(int id);

	/**
	 * 班级编号
	 * 
	 * @param id
	 * @return
	 */
	@Select("select clas_id from student where stu_id=#{id}")
	public int getClasByid(int id);

	/**
	 * 专业编号
	 * 
	 * @param id
	 * @return
	 */
	@Select("select majo_id from student where stu_id=#{id}")
	public int getMajoByid(int id);

	/**
	 * 得到学生密码
	 * 
	 * @param id
	 * @return
	 */
	@Select("select password from student where stu_id=id")
	public String getPW(int id);

	/**
	 * 修改学生密码
	 * 
	 * @param id
	 *            学生Id
	 * @param password
	 *            新密码
	 */
	@Update("update student set stu_password=#{password} where stu_id=#{id}")
	public void updateStuPW(int id, String password);

	/**
	 * 查看大学期间major专业在time学期的所需学的必修课程
	 * 
	 * @param major
	 *            专业名
	 * @param time
	 *            学期
	 * @return
	 */
	@Select("select * from v_majocourinfo where mcMajorName=#{major} and mcSubTime=#{time}")
	public List<MajoCourInfo> findMCInfo(String major, int time);

	/**
	 * 查看可选修课程，进行选课
	 * 
	 * @return
	 */
	@Select("select * from v_selecourinfo")
	public List<SeleCourInfo> findSeleCourAll();

	/**
	 * 获得一个学生的选修课程信息
	 * 
	 * @param id
	 *            学生Id
	 * @return
	 */
	@Select("select scId,scCourName,scTeacName,scCourCredit,scCourNature,scCourPeriod,scSubTime,scTeacTitle"
			+ " from selec as s inner join v_selecourinfo as v on  s.selecour_id=v.scId where s.stu_id=#{id}")
	public List<SeleCourInfo> findSeleCourByStu(int id);

	/**
	 * 获得一个学生在time学期的选修课程信息
	 * 
	 * @param id
	 *            学生Id
	 * @param time
	 *            学期
	 * @return
	 */
	@Select("select scId,scCourName,scTeacName,scCourCredit,scCourNature,scCourPeriod,scSubTime,scTeacTitle"
			+ " from selec as s inner join v_selecourinfo as v on  s.selecour_id=v.scId where s.stu_id=#{id} and v.scTime=#{time}")
	public List<SeleCourInfo> findSeleCourByStu(int id, String time);

	/**
	 * 获得一个学生的主修课程信息
	 * 
	 * @param id
	 *            学生Id
	 * @return
	 */
	@Select("select mcCourName,mcCourCredit,mcCourNature,mcCourPeriod,mcSubTime,mcTeacName,mcTeacTitle"
			+ " from student as s inner join v_maincourinfo as vm on s.clas_id=vm.clas_id where s.stu_id=#{id}")
	public List<SeleCourInfo> findMainCourByStu(int id);

	/**
	 * 获得一个学生在time学期的主修课程信息
	 * 
	 * @param id
	 *            学生Id
	 * @param time
	 *            学期
	 * @return
	 */
	@Select("select mcCourName,mcCourCredit,mcCourNature,mcCourPeriod,mcSubTime,mcTeacName,mcTeacTitle"
			+ " from student as s inner join v_maincourinfo as vm on s.clas_id=vm.clas_id where s.stu_id=#{id} and vm.mcSubTime=#{time}")
	public List<SeleCourInfo> findMainCourByStu(int id, String time);

	/**
	 * 获得一个学生的课表信息
	 * 
	 * @param id
	 *            学生Id
	 * @return
	 */
	@Select("Select courName, teacName ,subTime, day, week, part, site from v_persche as vp where vp.stu_id=#{id}"
			+ " Union Select courName, teacName ,subTime, day, week, part, site from v_schedule as vs inner join student s"
			+ " on s.clas_id=vs.clas_id where s.stu_id=#{id}")
	public List<Schedule> getScheduleById(int id);

	/**
	 * 获得一个学生在time学期的课表信息
	 * 
	 * @param id
	 *            学生Id
	 * @param time
	 *            学期
	 * @return
	 */
	@Select("Select courName, teacName ,subTime, day, week, part, site from v_persche as vp where vp.stu_id=#{id} and vp.subTime=#{time}"
			+ " Union Select courName, teacName ,subTime, day, week, part, site from v_schedule as vs inner join student s"
			+ " on s.clas_id=vs.clas_id where s.stu_id=#{id} and vs.subTime=#{time}")
	public List<Schedule> getScheduleById(int id, String time);

	/**
	 * 获得一个学生的成绩
	 * 
	 * @param id
	 *            学生Id
	 * @return
	 */
	@Select("select * from v_grade where stuId=#{id}")
	public List<Grade> getGradeById(int id);

	/**
	 * 在time学期的一个学生成绩
	 * 
	 * @param id
	 *            学生Id
	 * @param time
	 *            学期
	 * @return
	 */
	@Select("select * from v_grade where stuId=#{id} and subTime=#{time}")
	public List<Grade> getGradeById(int id, String time);

	/**
	 * time学期的班级排名
	 * 
	 * @param clasId
	 *            班级Id
	 * @param time
	 *            学期
	 * @return
	 */
	@Select("select * from v_poinscor vp where clasId=#{clasId} and subTime=#{time}  order by sum desc")
	public List<PoinScor> getRankByclas(int clasId, String time);

	/**
	 * period届majoId专业time学期的排名
	 * 
	 * @param majoId
	 *            专业Id
	 * @param time
	 *            学期
	 * @param period
	 *            届
	 * @return
	 */
	@Select("select * from v_poinscor vp where majoId=#{majoId} and period=#{period} and subTime=#{time} order by sum desc")
	public List<PoinScor> getRankBymajo(int majoId, String time, int period);

	/**
	 * 获得一个学生的挂科情况
	 * 
	 * @param id
	 *            学生Id
	 * @return
	 */
	@Select("select * from v_grade where stuId=#{id} and judge='没过' or judge='重修过'")
	public List<Grade> getFailCour(int id);

}
