package cn.edu.nju.software.yy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import cn.edu.nju.software.yy.getData.Date;
import cn.edu.nju.software.yy.getData.MainEnter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;


public class LoadActivity extends Activity{
	LoadActivity temp;
	static Thread t;
	 Handler handler = new Handler() {
     	@Override
     	public void handleMessage(Message msg) {
     	switch (msg.what) {
     	case 0:
     		 Intent intent =new Intent(LoadActivity.this,MainActivity.class);
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
         temp=this;
        this.setContentView(R.layout.loading);
      
        Bundle receive = this.getIntent().getExtras();
        String username = receive.getString("username");
        String passwd = receive.getString("password");
        ArrayList<String> courseList = login(username,passwd);
        Date date = null;
        if(courseList.get(0).equals("password_unmatch")){
        	Toast.makeText(this, "Sorry, name or password incorrect", Toast.LENGTH_SHORT).show();
        	Intent returnIntent = new Intent();
        	returnIntent.setClass(this, TssActivity.class);
        	startActivity(returnIntent);
        }
        else if(courseList.get(0).equals("regist")){
        	Toast.makeText(this, "Regist Success", Toast.LENGTH_SHORT).show();
        	date = new Date(2011,1,1,0,0);
        }
        else{
        	String time = courseList.get(0);
        	date = Date.parseDate(time);
        }
        Bundle send = new Bundle();
        send.putStringArrayList("courseList", courseList);
        final Date lastDate = date;
        Intent loginIntent = new Intent();
        loginIntent.putExtras(send);  
    	
        
        t=new Thread(){
        	public void run(){
        		while(true){
        			Message msg = new Message();
        			msg.what=0;
        			MainActivity.me=new MainEnter(lastDate);
        			try {
        				MainActivity.me.init(temp);
        				handler.sendMessage(msg);
        			} catch (IOException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			try {
						sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        	}
    				  
    	};
    		   
    	t.start();
    
	}
	
	
	public ArrayList<String> login(String username, String password) {
		ArrayList<String> courseList = new ArrayList<String>();
		try {
			InputStream is = getAssets().open(username);
			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					"GBK"));
			String passwd = br.readLine();

			if (password.equals(passwd)) {
				InputStream isTime = getAssets().open(username+"T");
				BufferedReader brTime = new BufferedReader(new InputStreamReader(isTime,
						"GBK"));
				String lastLogindate = brTime.readLine();
				courseList.add(lastLogindate);
				
				InputStream isCourse = getAssets().open(username+"C");
				BufferedReader brCourse = new BufferedReader(new InputStreamReader(isCourse,
						"GBK"));
				String mycourse;
				while ((mycourse = brCourse.readLine()) != null) {
					courseList.add(mycourse);
				}
				isTime.close();
				isCourse.close();
			}
			else{
				courseList.add("password_unmatch");
				return courseList;
			}
			is.close();

		} catch (IOException e) {

			String regist = regist(username,password);
			courseList.add(regist);
			return courseList;
		}

		return courseList;

	}
	private String regist(String username,String password){
		try {
			FileOutputStream os = openFileOutput(username,Context.MODE_PRIVATE);
			String passwd = password+"\n";
			os.write(passwd.getBytes());
			os.close();
			

			FileOutputStream ost = openFileOutput(username+"T",Context.MODE_PRIVATE);
			ost.close();
			

			FileOutputStream osc = openFileOutput(username+"C",Context.MODE_PRIVATE);
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
