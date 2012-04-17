package cn.edu.nju.software.yy.getData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import cn.edu.nju.software.yy.MainActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/*
 * 1. 正常情况： 返回 courseList[0]：最近登录时间
 * 				courseList[1~n]:我的课程列表（courseNo）
 * 
 * 2. 密码错误： 返回courseList[0]: password_unmatch
 * 
 * 3. 第一次登录： 返回coureList[0]：regist
 * 
 */
public class Login extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle receive = this.getIntent().getExtras();
        String username = receive.getString("username");
        String passwd = receive.getString("password");
        ArrayList<String> courseList = login(username,passwd);
        Bundle send = new Bundle();
        send.putStringArrayList("courseList", courseList);
        
        Intent loginIntent = new Intent();
        loginIntent.putExtras(send);
        loginIntent.setClass(this, MainActivity.class);
        startActivity(loginIntent);
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
