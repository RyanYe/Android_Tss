package cn.edu.nju.software.yy.getData;

public class Topic {

	public String title,course,courseNo;
	public Date date;
	public String topicURL,CourseURL;
	
	public Topic(String title,Date time,String course,String courseNo){
		this.title = title;
		this.date = time;
		this.course = course;
		this.courseNo = courseNo;
	}
	
	public String getTitle(){
		return title;
	}
	public Date getTime(){
		return date;
	}
	public String getCourse(){
		return course;
	}
}
