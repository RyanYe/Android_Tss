package cn.edu.nju.software.yy;

import java.util.ArrayList;
import java.util.HashMap;

import cn.edu.nju.software.yy.getData.Anouncement;
import cn.edu.nju.software.yy.getData.Assignment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class CourseBoardActivity extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		 // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.courseboard);
        ListView list=(ListView) this.findViewById(R.id.boardnList);
        list.setBackgroundColor(R.color.background_color);
        Anouncement a=new   Anouncement();
	    Intent intent=this.getIntent();
        Bundle bd=intent.getBundleExtra("bd");
        ArrayList<Anouncement> as=a.search(bd.getString("number"));
        ArrayList<HashMap<String, Object>> listItem=new ArrayList<HashMap<String, Object>>();
    	for (Anouncement i:as) {

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("boardtitle",i.getTitle());
			map.put("boardDate","Date:  "+i.getTime());
			map.put("boardDe",i.content+"\n\n");
			listItem.add(map);

		}
    	
    	SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,
				R.layout.courseboardlist, new String[] { "boardtitle","boardDate","boardDe"}, new int[] { 
    			R.id.boardinfo1,R.id.boarddate,R.id.boardContent
						});

		list.setAdapter(listItemAdapter);
 }
}
