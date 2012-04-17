package cn.edu.nju.software.yy.getData;

import org.htmlparser.Parser;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class SearchAnouncement {

	public static ArrayList<Anouncement> search() {

		Parser parser = null;
		parser = new Parser();
		try {
			parser.setConnection((HttpURLConnection) (new URL(
					"http://218.94.159.102/tss/en/home/index.html"))
					.openConnection());
			parser.setEncoding("gb2312");
		} catch (ParserException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		NodeFilter tableFilter = new HasAttributeFilter("border", "1");

		NodeList nodes1 = null;
		try {
			nodes1 = parser.extractAllNodesThatMatch(tableFilter);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		ArrayList<Anouncement> list = new ArrayList<Anouncement>();

		Anouncement anounce;
		Node tableNode = (Node) nodes1.elementAt(0);
		Node beginNode = tableNode.getFirstChild().getNextSibling()
				.getNextSibling().getNextSibling().getNextSibling().getNextSibling();
		while (!(beginNode.toHtml().contains("height=\"10\""))) {
			Node aNode = beginNode.getFirstChild().getNextSibling()
					.getFirstChild().getNextSibling();
//			LinkTag tag = (LinkTag) aNode;
//			System.out.println(tag.getLink());
			
			Node titleNode = aNode.getFirstChild().getNextSibling().getNextSibling();
			String title = titleNode.toPlainTextString().split(";")[2];
		//	System.out.println(titleNode.toPlainTextString());
			
			Node timeNode = aNode.getParent().getNextSibling().getNextSibling();
			String time = timeNode.toPlainTextString().split(";")[2];
			Date date = Date.parseDate("2012-"+time);
		//	System.out.println(timeNode.toPlainTextString());
			
			Node courseNode = timeNode.getNextSibling().getNextSibling();
			LinkTag courseURL =(LinkTag) courseNode.getFirstChild().getNextSibling();
			String courseNo = courseURL.getLink().split("/")[5];
//			System.out.println(courseURL.getLink());	
			Node courseNameNode = courseURL.getChild(1).getNextSibling();
			String courseName = courseNameNode.toPlainTextString().split(";")[2];
	//		System.out.println(courseNameNode.toPlainTextString());
			anounce = new Anouncement(title,courseNo,date,courseName);
			list.add(anounce);
			beginNode = beginNode.getNextSibling().getNextSibling();
		}
		return list;
		// ArrayList<Anouncement> list = new ArrayList<Anouncement>();
		// if (nodes1 != null) {
		// Anouncement anounce = new Anouncement();
		// for (int i = 0; i < nodes1.size(); i++) {
		// Node textnode = (Node) nodes1.elementAt(i);
		// LinkTag linkNode = (LinkTag) textnode;
		// String link = linkNode.getLink();
		// String r= link.replace("file://localhost", "http://218.94.159.102");
		// r = r.split(";")[0];
		// if(i%2==0){
		// anounce = new Anouncement();
		// anounce.anoucementUrl=r;
		// // System.out.println("Anounce URL:"+r);
		// Node parent = textnode.getParent();
		// Node timeNode = parent.getNextSibling().getNextSibling();
		// String time = timeNode.toHtml(false).split(";")[2].replace("</td>",
		// "");
		// anounce.time=time;
		// // System.out.println(anounce.time);
		// }
		// else{
		// anounce.courseUrl=r;
		// // System.out.println("Course URL:"+r);
		// }
		// Node child = textnode.getFirstChild();
		//
		// child=child.getNextSibling().getNextSibling();
		// if(i%2==0){
		// anounce.title=child.toHtml(false).split(";")[2];
		// // System.out.println("Anounce title:"+anounce.title);
		//
		// }
		// else{
		// anounce.course=child.toHtml(false).split(";")[2];
		// // System.out.println("course name:"+anounce.course);
		// list.add(anounce);
		// }
		// }
		// }
		// for(int i=0;i<list.size();i++){
		// System.out.println("course name: "+list.get(i).course+"  \n  title:  "+list.get(i).title+"\n");
		// }
		// return list;
	}

}
