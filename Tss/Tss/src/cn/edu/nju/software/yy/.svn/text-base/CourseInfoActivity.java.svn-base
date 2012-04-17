package cn.edu.nju.software.yy;

import java.util.HashMap;

import cn.edu.nju.software.yy.getData.CourseInfo;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CourseInfoActivity extends Activity{
	
    
	 protected void onCreate(Bundle savedInstanceState) {
	        // TODO Auto-generated method stub
	        super.onCreate(savedInstanceState);
	        this.setContentView(R.layout.courseinfo);
	        Intent i=this.getIntent();
	        Bundle bd=i.getBundleExtra("bd");
	        CourseInfo c=new CourseInfo();
	        HashMap<String,String> map=c.search(bd.getString("number"));
	        TextView info1=(TextView) this.findViewById(R.id.courseinfo1);
	        info1.setText("Course No.:  "+map.get("courseID"));
	        TextView info2=(TextView) this.findViewById(R.id.courseinfo2);
	        info2.setText("Course Name:  "+map.get("courseName"));
	        TextView info3=(TextView) this.findViewById(R.id.courseinfo3);
	        info3.setText(map.get("desciption"));
	 }
}
