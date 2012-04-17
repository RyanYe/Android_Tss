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
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class SearchCourse {

	public static ArrayList<Course> search() {


		Parser parser = null;
			parser = new Parser();
			try {
				parser.setConnection((HttpURLConnection) (new URL("http://218.94.159.102/tss/en/home/courselist.html"))
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
			NodeFilter filter1 = new TagNameFilter("tr");
			NodeFilter innerFilter = new TagNameFilter("a");
			NodeFilter filter2 = new HasChildFilter(innerFilter);
			NodeFilter filter3 = new HasChildFilter(filter2);
			NodeFilter centerfilter = new HasAttributeFilter("align","center");
			NodeFilter filter5 = new HasChildFilter(centerfilter);
			NodeFilter filter4 = new AndFilter(filter3,filter5);
			NodeFilter andFilter = new AndFilter(filter1,filter4);
			NodeList nodes1 = null;
		try {
			nodes1 = parser.extractAllNodesThatMatch(andFilter);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}

		ArrayList<Course> list = new ArrayList<Course>();
		if (nodes1 != null) {
			Course course = new Course();
			System.out.println(nodes1.size());
			for (int i = 0; i < nodes1.size(); i++) {
				Node textnode = (Node) nodes1.elementAt(i);
				Node idNode = textnode.getFirstChild().getNextSibling();
				String courseID=idNode.toPlainTextString();
				Node nameNode = idNode.getNextSibling().getNextSibling().getFirstChild().getNextSibling();
				String courseName=nameNode.toPlainTextString().replace(" ", "").replace("\n", "");
//				System.out.println(nameNode.toHtml());
				Node teacherNode = idNode.getNextSibling().getNextSibling().getNextSibling().getNextSibling().getFirstChild();
				String teachers[] = teacherNode.toPlainTextString().split(";");
				String teacher[] = teachers[teachers.length-1].split(" ");
				ArrayList<String> teacherlist = new ArrayList<String>();
				for(int j=0;j<teacher.length;j++){
					teacherlist.add(teacher[j]);
					System.out.print (teacher[j]+" ");
				}
				System.out.println("\n");
				course = new Course(courseName,courseID,teacherlist);
				list.add(course);
			}
		}
		for(int x=0;x<list.size();x++){
			System.out.println(list.get(x).courseURL);
		}
		return list;
	}
	
}
	

