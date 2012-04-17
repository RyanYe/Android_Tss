package cn.edu.nju.software.yy;

import cn.edu.nju.software.yy.getData.MainEnter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity {
	 

public static MainEnter me;
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.mainframe);
	        
	        
	     
	        
	        
	        
	        ImageView announceiv = (ImageView)this.findViewById(R.id.announcement);
	        announceiv.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(v.getContext(),AnnoucementActivity.class);
					startActivity(intent);
				}
	        	
	        });
	        ImageView forumiv = (ImageView)this.findViewById(R.id.forum);
	        forumiv.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(v.getContext(),ForumActivity.class);
					startActivity(intent);
				}
	        	
	        });
	        ImageView courseiv = (ImageView)this.findViewById(R.id.course);
	        courseiv.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(v.getContext(),CourseActivity.class);
					startActivity(intent);
				}
	        	
	        });
	        
	        ImageView assignmentiv = (ImageView)this.findViewById(R.id.deadline);
	        assignmentiv.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(v.getContext(),AssignmentActivity.class);
					startActivity(intent);
					
				}
	        	
	        });
	    }
}
