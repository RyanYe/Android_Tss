package cn.edu.nju.software.yy;

import java.util.ArrayList;
import java.util.HashMap;

import cn.edu.nju.software.yy.getData.Anouncement;
import cn.edu.nju.software.yy.getData.Forum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class CourseForumActivity extends Activity{
	ArrayList<String> urls;
	ArrayList<Forum> fs;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Anouncement a=new   Anouncement();
	    Intent intent=this.getIntent();
        Bundle bd=intent.getBundleExtra("bd");
        Forum f=new Forum();
         fs=f.search(bd.getString("number"));
        ListView list=new ListView(this);
        urls=new ArrayList<String>();
        ArrayList<HashMap<String, Object>> listItem=new ArrayList<HashMap<String, Object>>();
    	for (Forum i:fs) {
            urls.add(i.forumURL);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("topictitle","title:  "+i.title);
			map.put("topicDate",i.date.toString());
			map.put("topicAu","Author:  "+i.author+"\n\n");
			listItem.add(map);

		}
    	
    	SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,
				R.layout.courseforumlist, new String[] { "topictitle","topicDate","topicAu"}, new int[] { 
    			R.id.topictitle,R.id.topicdate,R.id.topicauthor
						});
    	list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				// TODO Auto-generated method stub
				Intent intent = new Intent(CourseForumActivity.this,
						TopicActivity.class);

				Bundle bundle = new Bundle();
				bundle.putString("url", urls.get(arg2));
				Forum f=fs.get(arg2);
				bundle.putString("name", f.title);
				bundle.putString("author", f.author);
				bundle.putString("date", f.date.toString());
				bundle.putString("content", f.content);
				intent.putExtra("bd", bundle);

				startActivity(intent);
			}
		});
        list.setBackgroundResource(R.color.background_color);
		list.setAdapter(listItemAdapter);
        this.setContentView(list);
	}
}
