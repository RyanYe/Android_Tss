package cn.edu.nju.software.yy;

import java.util.ArrayList;
import java.util.HashMap;


import cn.edu.nju.software.yy.getData.Forum;
import cn.edu.nju.software.yy.getData.Topic;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class ForumActivity extends Activity {
	private ListView list;
	ArrayList<Topic> tlist ;
	
	private ArrayList<HashMap<String,Object>> listItem;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.forum);
	        displayForum();
	 }
	 
	 
	 private void displayForum(){
		     tlist=MainActivity.me.getTopic();
	         list=(ListView) this.findViewById(R.id.forumView);
		       
	        ArrayList<HashMap<String, Object>> listItem=new ArrayList<HashMap<String, Object>>();
	    	for (int i = 0; i < tlist.size(); i++) {

				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("forumNo","["+tlist.get(i).getCourse()+"]    "+tlist.get(i).getTitle());
				
				map.put("forumDate",tlist.get(i).getTime());
				listItem.add(map);

			}
	    	
	    	SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,
					R.layout.forumlist, new String[] { "forumNo","forumDate"}, new int[] { R.id.forumNo,
	    			R.id.forumDate
							});

			list.setAdapter(listItemAdapter);
			list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					Intent intent = new Intent(ForumActivity.this,
							TopicActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString("url", tlist.get(arg2).topicURL);
					Topic f=tlist.get(arg2);
				
					bundle.putString("name", f.title);
					
					intent.putExtra("bd", bundle);
					startActivity(intent);
					
					// arg0.setSelection(arg2);
					// TODO Auto-generated method stub

				}
			});
		}

}
