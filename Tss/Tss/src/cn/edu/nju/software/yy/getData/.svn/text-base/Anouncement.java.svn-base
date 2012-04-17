package cn.edu.nju.software.yy.getData;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.StringFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;


public class Anouncement {

	public Date time;
	public String title,course;
	public String courseUrl,anoucementUrl;
	public String content;
	public String courseNo;
	public Anouncement(){
	}
	public Anouncement(String title,String courseNo,Date time,String course){
		this.title=title;
		this.time=time;
		this.course=course;
		this.content=null;
		this.courseNo = courseNo;
	}
	public Anouncement(String t,Date ti,String c,String course){
		title=t;
		time=ti;
		content=c;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public Date getTime(){
		return this.time;
	}
	
	public String getCourse(){
		return this.course;
	}
	
	public ArrayList<Anouncement> search(String courseNo){
		ArrayList<Anouncement> list = new ArrayList<Anouncement>();
		Parser parser = new Parser();
		try{
			parser.setConnection((HttpURLConnection) (new URL(
					"http://218.94.159.102/tss/en/"+courseNo+"/announcement/index.html")).openConnection());
			parser.setEncoding("gb2312");
					
		}catch(ParserException e1){
			e1.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		NodeFilter tableFilter = new TagNameFilter("table");
		NodeFilter widthFilter = new HasAttributeFilter("width","90%");
		NodeFilter andFilter = new AndFilter(tableFilter,widthFilter);
		NodeList nodes = null;
		
		try{
			nodes = parser.extractAllNodesThatMatch(andFilter);
			
		}catch(ParserException e){
			e.printStackTrace();
		}
		int i=0;
		while(nodes.elementAt(i)!=null){
			Node dateNode = nodes.elementAt(i).getFirstChild().getNextSibling();
			String time = dateNode.getChildren().elementAt(1).getFirstChild().toPlainTextString().replace("\r\n\t\t\t\t","").replace("&nbsp;","");
			Date date = Date.parseDate(time);
			String title = dateNode.getChildren().elementAt(3).toPlainTextString().replace("\n", "").replace("&nbsp;", "").replace("\t", "").replace("\r", "");
			Node contentNode = nodes.elementAt(i).getChildren().elementAt(3);
			String content = contentNode.getChildren().elementAt(1).toPlainTextString().replace("&nbsp;", "").replace("\r\n\t\t\t\t\t", "");
			Anouncement anounce = new Anouncement(title,date,content,null);
			list.add(anounce);
			i++;
		}
		//
		return list;
	}
	
//	public static void main(String args[]){
//		new Anouncement().search("c0490");
//	}
}
