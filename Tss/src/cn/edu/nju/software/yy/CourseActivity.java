package cn.edu.nju.software.yy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import cn.edu.nju.software.yy.getData.Course;
import cn.edu.nju.software.yy.getData.MainEnter;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

public class CourseActivity extends TabActivity {

	CourseActivity s;
	int tabNum;
	ImageButton b1;
	ImageButton b2;
	ImageButton b3;
	ImageButton b4;
	ImageButton b5;
	LinearLayout lv;

	String select;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		s = this;
		tabNum = 0;
		initButton();
		TabHost myTabhost;
		myTabhost = this.getTabHost();

		LayoutInflater.from(this).inflate(R.layout.course,
				myTabhost.getTabContentView(), true);

		View view = LayoutInflater.from(this).inflate(R.layout.tab, null);
		TextView tv = (TextView) view.findViewById(R.id.tabView);
		tv.setText("all course");
		myTabhost.addTab(myTabhost.newTabSpec("all course").setIndicator(view)
				.setContent(R.id.allCourse));
		view = LayoutInflater.from(this).inflate(R.layout.tab, null);
		tv = (TextView) view.findViewById(R.id.tabView);
		tv.setText("my course");
		myTabhost.addTab(myTabhost.newTabSpec("my course").setIndicator(view)
				.setContent(R.id.myCourse));

		myTabhost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String arg0) {
				// TODO Auto-generated method stub
				if (arg0 == "my course")
					tabNum = 1;
				else
					tabNum = 0;

			}
		});

		addListView(R.id.allCourseList);
		addListView(R.id.myCourseList);

	}

	public void addListView(int id) {

		ListView list = (ListView) this.findViewById(id);

		ArrayList<Course> courses = MainActivity.me.getCourse();

		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < courses.size(); i++) {

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("coursename", courses.get(i).courseName);
			String teacher = "";
			for (int k = 0; k < courses.get(i).courseTeacher.size(); k++) {
				teacher = teacher + courses.get(i).courseTeacher.get(k) + "  ";
			}

			map.put("courseteacher", courses.get(i).courseTeacher.get(0));
			listItem.add(map);

		}

		SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,
				R.layout.courselist, new String[] { "coursename",
						"courseteacher" }, new int[] { R.id.courseName,
						R.id.courseTeacher });

		list.setAdapter(listItemAdapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				addButton(arg1);
				select = MainActivity.me.getCourse().get(arg2).courseID;
				// arg0.setSelection(arg2);
				// TODO Auto-generated method stub

			}
		});

		list.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				arg0.setSelected(true);
				
				return false;
			}

		});

		list.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				addButton(arg1);
				select = MainActivity.me.getCourse().get(arg2).courseID;
				// TODO Auto-generated method stub

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

	}

	public void initButton() {
		lv = new LinearLayout(this);

		b1 = new ImageButton(this);
		b2 = new ImageButton(this);
		b3 = new ImageButton(this);
		b4 = new ImageButton(this);
		b5 = new ImageButton(this);
		b1.setImageResource(R.drawable.info);
		//b1.setBackgroundResource(R.color.background_color);
		b2.setImageResource(R.drawable.board2);
		//b2.setBackgroundResource(R.color.background_color);
		b3.setImageResource(R.drawable.forum2);
		//b3.setBackgroundResource(R.color.background_color);
		b4.setImageResource(R.drawable.pencil);
		//b4.setBackgroundResource(R.color.background_color);
		b5.setImageResource(R.drawable.add);
	//	b5.setBackgroundResource(R.color.background_color);
		lv.addView(b1);
		lv.addView(b2);
		lv.addView(b3);
		lv.addView(b4);
		lv.addView(b5);

		b1.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(CourseActivity.this,
						CourseInfoActivity.class);

				Bundle bundle = new Bundle();
				bundle.putString("number", select);
				intent.putExtra("bd", bundle);

				startActivity(intent);
			}
		});

		b2.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(CourseActivity.this,
						CourseBoardActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("number", select);
				intent.putExtra("bd", bundle);
				startActivity(intent);
			}
		});

		b3.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(CourseActivity.this,
						CourseForumActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("number", select);
				intent.putExtra("bd", bundle);
				startActivity(intent);
			}
		});

		b4.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(CourseActivity.this,
						AssignmentActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("number", select);
				intent.putExtra("bd", bundle);
				startActivity(intent);
			}
		});

		b5.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void addButton(View v) {
		LinearLayout a = (LinearLayout) v;

		if (lv.getParent() != null) {
			LinearLayout s = (LinearLayout) lv.getParent();
			s.removeView(lv);
		}

		a.addView(lv);
	}

}
