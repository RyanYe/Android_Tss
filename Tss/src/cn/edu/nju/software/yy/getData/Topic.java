package cn.edu.nju.software.yy.getData;

public class Topic {

	public String title,course;
	public Date date;
	public String topicURL,CourseURL;
	
	public Topic(String title,Date time,String course){
		this.title = title;
		this.date = time;
		this.course = course;
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
