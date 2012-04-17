package cn.edu.nju.software.yy.getData;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.StringFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class CourseInfo {
	
	public static HashMap<String,String> search(String courseNO){
		HashMap<String,String> mapList = new HashMap<String,String>();
		
		Parser parser = new Parser();
		Parser parser2 = new Parser();
		Parser parser3 = new Parser();
		try{
			parser.setConnection((HttpURLConnection) (new URL(
					"http://218.94.159.102/tss/en/"+courseNO+"/index.html")).openConnection());
			parser.setEncoding("gb2312");
			parser2.setConnection((HttpURLConnection) (new URL(
					"http://218.94.159.102/tss/en/"+courseNO+"/index.html")).openConnection());
			parser2.setEncoding("gb2312");

			parser3.setConnection((HttpURLConnection) (new URL(
					"http://218.94.159.102/tss/en/"+courseNO+"/index.html")).openConnection());
			parser3.setEncoding("gb2312");
					
		}catch(ParserException e1){
			e1.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodeFilter NoFilter = new StringFilter("Course No");
		
		NodeFilter nameFilter  =new StringFilter("Course Name:");
		
		NodeFilter infoFilter = new StringFilter("Description");
		NodeList nodes = null;
		NodeList nodes2=null;
		NodeList nodes3 = null;
		try{
			nodes = parser.extractAllNodesThatMatch(nameFilter);
			nodes2 = parser2.extractAllNodesThatMatch(NoFilter);
			nodes3 = parser3.extractAllNodesThatMatch(infoFilter);
		}catch(ParserException e){
			e.printStackTrace();
		}
		String courseID=null;
		String courseName = null;
		String description = null;
		
		if(nodes2!=null){
			Node startnode = (Node) nodes2.elementAt(0);
			Node courseIDNode = startnode.getNextSibling().getNextSibling().getNextSibling();
			courseID = courseIDNode.toPlainTextString().split("\n")[2].replace(" ", "");
			mapList.put("courseID", courseID);
		}
		
		if(nodes!=null){
			Node startnode = (Node) nodes.elementAt(0);
			Node courseNameNode = startnode.getNextSibling().getNextSibling().getNextSibling();
			courseName = courseNameNode.toPlainTextString().split("\n")[2].replace(" ", "");
			mapList.put("courseName", courseName);
		}
		
		if(nodes3!=null){
			Node startnode = (Node) nodes3.elementAt(0);
			Node courseInfoNode = startnode.getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling();
			description = courseInfoNode.toPlainTextString().split("\n")[1].replace(" ", "");
			mapList.put("desciption", description);
		}
		
		
		
		
		
		
		return mapList;
		
	}
//	public static void main(String args[]){
//		CourseInfo courseInfo = new CourseInfo();
//		HashMap<String,String> map = courseInfo.search("c0600");
//		System.out.println(map.get("courseID")+"\n"+map.get("courseName")+"\n"+map.get("description"));
//	}

}
