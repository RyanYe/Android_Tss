package cn.edu.nju.software.yy.getData;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.StringFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class Assignment {

	public int number;
	public Date dueDate;
	public String description;

	public Assignment(int n, Date d, String p) {
		number = n;
		dueDate = d;
		description = p;
	}

	public Assignment() {

	}

	public ArrayList<Assignment> search(String courseNo) {
		ArrayList<Assignment> assignList = new ArrayList<Assignment>();

		Parser parser = new Parser();
		Parser parser2 = new Parser();
		Parser parser3 = new Parser();
		try {
			parser.setConnection((HttpURLConnection) (new URL(
					"http://218.94.159.102/tss/en/" + courseNo
							+ "/assignment/index.html")).openConnection());
			parser.setEncoding("gb2312");
			parser2.setConnection((HttpURLConnection) (new URL(
					"http://218.94.159.102/tss/en/" + courseNo
							+ "/assignment/index.html")).openConnection());
			parser2.setEncoding("gb2312");

			parser3.setConnection((HttpURLConnection) (new URL(
					"http://218.94.159.102/tss/en/" + courseNo
							+ "/assignment/index.html")).openConnection());
			parser3.setEncoding("gb2312");

		} catch (ParserException e1) {
			e1.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodeFilter NoFilter = new StringFilter("Assignment No:");

		NodeFilter nameFilter = new StringFilter("Due date:");

		NodeFilter infoFilter = new StringFilter("Description:");
		NodeList nodes = null;
		NodeList nodes2 = null;
		NodeList nodes3 = null;
		try {
			nodes = parser.extractAllNodesThatMatch(nameFilter);
			nodes2 = parser2.extractAllNodesThatMatch(NoFilter);
			nodes3 = parser3.extractAllNodesThatMatch(infoFilter);
		} catch (ParserException e) {
			e.printStackTrace();
		}

		int i = 0;
		while (nodes2.elementAt(i) != null) {
			Node startnode = (Node) nodes2.elementAt(i);
			Node assignmentIDNode = startnode.getParent().getNextSibling()
					.getNextSibling().getFirstChild();
			String assignID = assignmentIDNode.toPlainTextString().replace(" ",
					"");

			Node startnode2 = (Node) nodes.elementAt(i);
			Node assignmentDateNode = startnode2.getParent().getNextSibling()
					.getNextSibling().getFirstChild();
			String assignDate = assignmentDateNode.toPlainTextString().replace(
					"  ", "");
			Date date = Date.parseDate(assignDate);

			Node startnode3 = (Node) nodes3.elementAt(i);
			Node assignmentInfoNode = startnode3.getParent().getNextSibling()
					.getNextSibling().getFirstChild();
			String description = assignmentInfoNode.toPlainTextString()
					.replace(" ", "");

			Assignment assign = new Assignment(Integer.parseInt(assignID),
					date, description);
			assignList.add(assign);
			i++;
		}
		return assignList;
	}

//	public static void main(String args[]) {
//		Assignment courseInfo = new Assignment();
//		ArrayList<Assignment> result = courseInfo.search("c0600");
//		int size = result.size();
//		for (int i = 0; i < size; i++) {
//			System.out.println(result.get(i).number + "\n"
//					+ result.get(i).dueDate + "\n" + result.get(i).description
//					+ "\n\n");
//		}
//	}

}
