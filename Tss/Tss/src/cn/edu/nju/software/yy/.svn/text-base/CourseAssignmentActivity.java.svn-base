package cn.edu.nju.software.yy;

import java.util.ArrayList;
import java.util.HashMap;

import cn.edu.nju.software.yy.getData.Assignment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class CourseAssignmentActivity extends Activity{
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.assignment);
        ListView list=(ListView) this.findViewById(R.id.assignList);
        list.setBackgroundColor(R.color.background_color);
	    Assignment a=new   Assignment();
	    Intent intent=this.getIntent();
        Bundle bd=intent.getBundleExtra("bd");
        ArrayList<Assignment> as=a.search(bd.getString("number"));
        ArrayList<HashMap<String, Object>> listItem=new ArrayList<HashMap<String, Object>>();
    	for (Assignment i:as) {

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("assignNo","Assignment No:  "+i.courseid);
			map.put("assignDate","Due Date:  "+i.dueDate);
			map.put("assignDe",i.description+"\n\n");
			listItem.add(map);

		}
    	
    	SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,
				R.layout.assignmentlist, new String[] { "assignNo","assignDate","assignDe"}, new int[] { R.id.assignNo,R.id.assignDate,
    			R.id.assignContent
						});

		list.setAdapter(listItemAdapter);
       
       
	}
}
