package cn.edu.nju.software.yy.getData;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;

public class MainEnter {
	ArrayList<Course> courselist;
	ArrayList<Topic> topiclist;
	ArrayList<Anouncement> anouncementlist;
	ArrayList<Assignment> assignmentList;
	ArrayList<String> courseNameList;
	Date lastLoginDate;

	public MainEnter(Date date, ArrayList<String> courseNameList) {
		lastLoginDate = date;
		this.courseNameList = courseNameList;
		assignmentList = new ArrayList<Assignment>();
	}

	
	public String getCourseName(String id){
		for(Course s:courselist){
			if(s.courseID.equals(id))
				return s.courseName;
		}
		return null;
	}
	
	
	public void init(Context c) throws IOException {
		// new DownloadHome().readUrl();
		// new DownloadCourse(c).readUrl();
		courselist = SearchCourse.search();
		topiclist = SearchForum.search();
		anouncementlist = SearchAnouncement.search();
		
		for (int i = 0; i < courseNameList.size(); i++) {
			ArrayList<Assignment> assignment = new Assignment()
					.search(courseNameList.get(i));
			for (int j = 0; j < assignment.size(); j++) {
				if (assignment.get(j).dueDate.unread(lastLoginDate))
					assignmentList.add(assignment.get(j));
			}

		}
	}

	public ArrayList<Course> getCourse() {
		return this.courselist;
	}

	public ArrayList<Anouncement> getAnouncement() {
		return this.anouncementlist;
	}

	public ArrayList<Topic> getTopic() {
		return this.topiclist;
	}

	public ArrayList<Assignment> getAssignment() {
		return assignmentList;
	}
}
