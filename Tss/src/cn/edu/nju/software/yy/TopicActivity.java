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
        System.out.println(url);
        
        TextView topictv = (TextView)this.findViewById(R.id.topicname);
        topictv.setText(bd.getString("name"));
        TextView name=(TextView) this.findViewById(R.id.replyname);
        name.setText(bd.getString("author"));
        TextView date=(TextView) this.findViewById(R.id.replydate);
        date.setText(bd.getString("date"));
        TextView content=(TextView) this.findViewById(R.id.replycontent);
        content.setText(reply.content);
        list = (ListView) findViewById(R.id.replyList);
        listItem = new ArrayList<HashMap<String, Object>>();
		for (Reply r:reply.replyList) {

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("name", "  " + r.author);
			map.put("date", "  " + r.time.toString());
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
