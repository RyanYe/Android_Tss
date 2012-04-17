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
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class Forum {

	public String courseID;
	public String forumURL;
	public String title;
	public String content,author;
	public Date date;
	public ArrayList<Reply> replyList;
	public Forum(){
		
	}
	public Forum(String title,String author,Date date,String url){
		this.title=title;
		this.author=author;
		this.date = date;
		this.forumURL=url;
	}
	public static ArrayList<Forum> search(String courseNo){
		ArrayList<Forum> forumList = new ArrayList<Forum>();
		
		Parser parser = new Parser();
		try{
			parser.setConnection((HttpURLConnection)(new URL(
					"http://218.94.159.102/tss/en/"+courseNo+"/forum/index.html")).openConnection());
		}catch(ParserException e){
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodeFilter classFilter = new HasAttributeFilter("class","forum_td2");
		NodeFilter alignFilter = new HasAttributeFilter("align","left");
		NodeFilter andFilter = new AndFilter(classFilter,alignFilter);
		NodeList nodes = null;
		
		try{
			nodes = parser.extractAllNodesThatMatch(andFilter);
		}catch(ParserException e){
			e.printStackTrace();
		}
		int i=0;
		while(nodes.elementAt(i)!=null){
			Node startNode = nodes.elementAt(i);
			Node titleNode = startNode.getFirstChild().getNextSibling();
			String title = titleNode.toPlainTextString();
			LinkTag linkNode = (LinkTag)titleNode;
			String forumURL = linkNode.getLink().split(";")[0];
			
			Node authorNode = startNode.getNextSibling().getNextSibling();
			String author = authorNode.toPlainTextString();
			
			Node timeNode = authorNode.getNextSibling().getNextSibling();
			String time = timeNode.toPlainTextString();
			
			Date date = Date.parseDate(time);
			Forum forum = new Forum(title,author,date,forumURL);
			
			forumList.add(forum);
			i++;
		}
		return forumList;
	}
	
	public Forum searchSpec(String forumURL){
		Forum forum = new Forum();
		forum.author = this.author;
		forum.title=this.title;
		forum.date=this.date;
		
		
		Parser parser = new Parser();
		try{
			parser.setConnection((HttpURLConnection)(new URL(forumURL)).openConnection());
		}catch(ParserException e){
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		NodeFilter tableFilter = new HasAttributeFilter("width","95%");
		NodeList nodes = null;
		try{
			nodes = parser.extractAllNodesThatMatch(tableFilter);
		}catch(ParserException e){
			e.printStackTrace();
		}
		Node startNode1 = (Node) nodes.elementAt(0);
		Node contentNode1 =  startNode1.getFirstChild().getChildren().elementAt(7);
		String content1 = contentNode1.toPlainTextString().replace("\r", "").replace("\n", "").replace("\t", "").replace("&nbsp;", " ");
		int i=1;
		ArrayList<Reply> replyList = new ArrayList<Reply>()  ;
		while(nodes.elementAt(i)!=null){

			Node startNode = (Node) nodes.elementAt(i);
			
			Node titleNode = startNode.getFirstChild().getChildren().elementAt(1);
			String title = titleNode.toPlainTextString().replace("\r", "").replace("\n", "").replace("\t", "").replace("&nbsp;", " ");
			
			Node authorNode = startNode.getFirstChild().getChildren().elementAt(3);
			String author = authorNode.toPlainTextString().replace("\r", "").replace("\n", "").replace("\t", "").replace("&nbsp;", " ");
			
			Node timeNode = startNode.getFirstChild().getChildren().elementAt(5);
			String time = timeNode.toPlainTextString().replace("\r", "").replace("\n", "").replace("\t", "").replace("&nbsp;", " ");
			Date date = Date.parseDate(time);
			Node contentNode =  startNode.getFirstChild().getChildren().elementAt(7);
			String content = contentNode.toPlainTextString().replace("\r", "").replace("\t", "").replace("&nbsp;", " ");
			System.out.println();
			
			Reply reply = new Reply(author, content,date);
			replyList.add(reply);
			i++;
			
			
		}
		forum.content=content1;
		forum.replyList=replyList;
		return forum;
		
		
	}
	
//	public static void main(String args[]){
//		Forum f = new Forum();
//		ArrayList<Forum> list = f.search("c0490");
//		int size = list.size();
//		for (int i=0;i<size;i++){
//			Forum fo = list.get(i).searchSpec(list.get(i).forumURL);
//			System.out.println(fo.author);
//			System.out.println(fo.content);
//			System.out.println(fo.forumURL);
//			System.out.println(fo.date.year+fo.date.month+fo.date.day+fo.date.hour+fo.date.minute);
//			for(int j=0;j<fo.replyList.size();j++){
//				System.out.println("   "+fo.replyList.get(j).author);
//			}
//		}
//	}
}
