package cn.edu.nju.software.yy;

import java.util.ArrayList;
import java.util.HashMap;

import cn.edu.nju.software.yy.getData.Anouncement;
import cn.edu.nju.software.yy.getData.Topic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class AnnoucementActivity extends Activity {
	 ArrayList<Anouncement> an;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        an=MainActivity.me.getAnouncement();
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
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(AnnoucementActivity.this,
						CourseBoardActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("number", an.get(arg2).courseNo);
				
				intent.putExtra("bd", bundle);
				startActivity(intent);
				
				// arg0.setSelection(arg2);
				// TODO Auto-generated method stub

			}
		});
		
		
        this.setContentView(list);
        
        
        
        
        
	}
	
	
}
