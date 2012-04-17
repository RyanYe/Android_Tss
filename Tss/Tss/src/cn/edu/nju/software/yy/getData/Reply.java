package cn.edu.nju.software.yy.getData;


public class Reply {
	public String content;
	public Date time;
	public String author;
	
	public Reply(String a,String c,Date time){
		author =a;
		content =c;
		this.time = time;
	}

}
