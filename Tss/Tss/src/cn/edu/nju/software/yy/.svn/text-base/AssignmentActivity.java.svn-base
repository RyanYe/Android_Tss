package cn.edu.nju.software.yy;

import java.util.ArrayList;
import java.util.HashMap;

import cn.edu.nju.software.yy.getData.Assignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AssignmentActivity extends Activity{

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.assignment);
        ListView list=(ListView) this.findViewById(R.id.assignList);
        list.setBackgroundColor(R.color.background_color);
	   
        ArrayList<Assignment> as=MainActivity.me.getAssignment();
        ArrayList<HashMap<String, Object>> listItem=new ArrayList<HashMap<String, Object>>();
    	for (Assignment i:as) {

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("assignNo","Course:  "+MainActivity.me.getCourseName(i.courseid));
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
