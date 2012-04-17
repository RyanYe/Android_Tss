package cn.edu.nju.software.yy;

import java.util.ArrayList;
import java.util.HashMap;

import cn.edu.nju.software.yy.getData.Forum;
import cn.edu.nju.software.yy.getData.Reply;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class TopicActivity extends Activity {

	ListView list;
	ArrayList<HashMap<String,Object>> listItem;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic);
        Intent intent=this.getIntent();
        Bundle bd=intent.getBundleExtra("bd");
        Forum f=new Forum();
        String url=bd.getString("url");
        Forum reply = f.searchSpec(url);
    //    System.out.println(url);
        
        TextView topictv = (TextView)this.findViewById(R.id.topicname);
        topictv.setText(bd.getString("name"));
       
        list = (ListView) findViewById(R.id.replyList);
        listItem = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name", "  " +reply.author);
		
		  String []str=reply.date.toString().split(" ");
		  map.put("date",  "  " +str[0]+"\n     "+str[1]);
		map.put("content",reply.content);
		listItem.add(map);
		for (Reply r:reply.replyList) {

			map = new HashMap<String, Object>();
		   str=r.time.toString().split(" ");
		    		
			map.put("name", "  " + r.author);
			map.put("date", "  " +str[0]+"\n     "+str[1]);
			map.put("content",r.content);

			listItem.add(map);

		}

		SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,
				R.layout.replylist, new String[] { "name","date","content"}, new int[] { R.id.replyname,
    			R.id.replydate,R.id.replycontent
						});
		list.setAdapter(listItemAdapter);
 }
 
}
