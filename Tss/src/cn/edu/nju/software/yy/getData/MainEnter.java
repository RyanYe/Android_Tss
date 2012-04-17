package cn.edu.nju.software.yy.getData;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;


public class MainEnter {
	ArrayList<Course> courselist;
	ArrayList<Topic> topiclist;
	ArrayList<Anouncement> anouncementlist;
	Date lastLoginDate;
	public MainEnter(Date date){
		lastLoginDate = date;
	}
	public void init(Context c) throws IOException{
//		new DownloadHome().readUrl();
//		new DownloadCourse(c).readUrl();
		courselist = SearchCourse.search();
		topiclist = SearchForum.search();
		for(int i=0;i<topiclist.size();i++){
			if(topiclist.get(i).date.unread(lastLoginDate)){
				topiclist.remove(i);
				i--;
			}
		}
     	anouncementlist = SearchAnouncement.search();
		for(int i=0;i<anouncementlist.size();i++){
			if(anouncementlist.get(i).time.unread(lastLoginDate)){
				anouncementlist.remove(i);
				i--;
			}
		}
	}
	
	public ArrayList<Course> getCourse(){
		return this.courselist;
	}
	public ArrayList<Anouncement> getAnouncement(){
		return this.anouncementlist;
	}
	public ArrayList<Topic> getTopic(){
		return this.topiclist;
	}
}
