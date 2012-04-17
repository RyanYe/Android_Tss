package cn.edu.nju.software.yy;

import java.util.ArrayList;
import java.util.HashMap;

import cn.edu.nju.software.yy.getData.Anouncement;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AnnoucementActivity extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        ArrayList<Anouncement> an=MainActivity.me.getAnouncement();
        ListView list=new ListView(this);
	       
        ArrayList<HashMap<String, Object>> listItem=new ArrayList<HashMap<String, Object>>();
    	for (int i = 0; i < an.size(); i++) {

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("anounceNo","["+an.get(i).getCourse()+"]    "+an.get(i).getTitle());
			
			map.put("anounceDate",an.get(i).getTime());
			listItem.add(map);

		}
    	
    	SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,
				R.layout.anouncement, new String[] { "anounceNo","anounceDate"}, new int[] { R.id.anounceNo,
    			R.id.anounceDate
						});

		list.setAdapter(listItemAdapter);
        this.setContentView(list);
	}
	
	
}
