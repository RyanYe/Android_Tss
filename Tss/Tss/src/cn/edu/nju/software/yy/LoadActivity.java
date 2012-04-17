package cn.edu.nju.software.yy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.util.EncodingUtils;

import cn.edu.nju.software.yy.getData.Course;
import cn.edu.nju.software.yy.getData.Date;
import cn.edu.nju.software.yy.getData.MainEnter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class LoadActivity extends Activity {
	LoadActivity temp;
    Thread t;
   public static String username ;
   public static ArrayList<String> myCourseList; 
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Intent intent = new Intent(LoadActivity.this,
						MainActivity.class);
				Bundle bd=new Bundle();
				bd.putString("username", username);
				intent.putExtra("bd",bd);
				startActivity(intent);
				break;
			default:
				super.handleMessage(msg);
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		temp = this;
		this.setContentView(R.layout.loading);

		Bundle receive = this.getIntent().getExtras();
		username = receive.getString("username");
		//Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
		String passwd = receive.getString("password");
		
		//¿ªÆô·þÎñ
		Intent intent = new Intent();
		intent.setClass(this, UpdateService.class);
		Bundle bd=new Bundle();
		bd.putString("username", username);
		intent.putExtras(bd);
		
	    this.startService(intent);
		
		
		ArrayList<String> courseList = login(username, passwd);
		Date date = null;
		if (courseList.get(0).equals("password_unmatch")) {
			Toast.makeText(this, "Sorry, name or password incorrect",
					Toast.LENGTH_SHORT).show();
			Intent returnIntent = new Intent();
			returnIntent.setClass(this, TssActivity.class);
			startActivity(returnIntent);
		} else if (courseList.get(0).equals("regist")) {
			Toast.makeText(this, "Regist Success", Toast.LENGTH_SHORT).show();
			date = new Date(2011, 1, 1, 0, 0);
			courseList.remove(0);
			myCourseList=courseList;
		} else {
			String time = courseList.get(0);
			System.out.println(courseList.size());
			date = Date.parseDate(time);
			courseList.remove(0);
			myCourseList=courseList;
		}
//		Bundle send = new Bundle();
//		send.putStringArrayList("courseList", courseList);
//		Intent loginIntent = new Intent();
//		loginIntent.putExtras(send);
		//date=new Date(2012,3,1,0,0);
		final Date lastDate = date;
		final ArrayList<String> courselist = courseList;

		t = new Thread() {
			public void run() {
				
					Message msg = new Message();
					msg.what = 0;
					MainActivity.me = new MainEnter(lastDate,courselist);
					try {
						MainActivity.me.init(temp);
						handler.sendMessage(msg);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
			}

		};

		t.start();

	}

	public ArrayList<String> login(String username, String password) {
		ArrayList<String> courseList = new ArrayList<String>();
		try {
			FileInputStream is = openFileInput(username);
			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					"GBK"));
			String passwd = br.readLine();
	//		Toast.makeText(this, passwd,
	//				Toast.LENGTH_SHORT).show();
			if (password.equals(passwd)) {
				FileInputStream isTime = openFileInput(username + "T");
				BufferedReader brTime = new BufferedReader(
						new InputStreamReader(isTime, "GBK"));
				String lastLogindate = brTime.readLine();
				if(lastLogindate!=null)
				courseList.add(lastLogindate);

				FileInputStream isCourse = openFileInput(username + "C");
				String mycourse;
				int length = isCourse.available(); 
			    byte [] buffer = new byte[length]; 
			    isCourse.read(buffer);     
			    mycourse = EncodingUtils.getString(buffer, "GBK");
				String [] s=mycourse.split("@");
				for(int i=0;i<s.length;i++){
					courseList.add(s[i]);
				}
				isTime.close();
				isCourse.close();
			} else {
				courseList.add("password_unmatch");
				this.username=null;
				return courseList;
			}
			is.close();

		} catch (IOException e) {

			String regist = regist(username, password);
			courseList.add(regist);
			return courseList;
		}

		return courseList;

	}

	private String regist(String username, String password) {
		try {
			FileOutputStream os = openFileOutput(username, Context.MODE_PRIVATE);
			String passwd = password + "\n";
			os.write(passwd.getBytes());
			os.close();

			FileOutputStream ost = openFileOutput(username + "T",
					Context.MODE_PRIVATE);
			ost.close();

			FileOutputStream osc = openFileOutput(username + "C",
					Context.MODE_PRIVATE);
			osc.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "regist";
	}

}
