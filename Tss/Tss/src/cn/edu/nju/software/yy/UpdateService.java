package cn.edu.nju.software.yy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.util.EncodingUtils;

import cn.edu.nju.software.yy.getData.Anouncement;
import cn.edu.nju.software.yy.getData.Date;
import cn.edu.nju.software.yy.getData.MainEnter;
import cn.edu.nju.software.yy.getData.Topic;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.Preference;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

public class UpdateService extends Service {

	private final IBinder mBinder = new LocalBinder();
	private static String name;
	private Intent intent;
	Context context;

	public void onCreate() {
	}

	public void onDestroy() {
	}

	public class LocalBinder extends Binder {
		UpdateService getService() {
			return UpdateService.this;
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return mBinder;
	}

	public static void set(String n) {
		name = n;
	}

	@Override
	public void onStart(Intent intent0, int startId) {

		context = getApplicationContext();

		intent = intent0;

		Thread temp = new Thread() {
			public void run() {

				if (intent != null) {
					Bundle bundle = intent.getExtras();
					if (bundle != null) {
						set(bundle.getString("username"));
						while (true) {
							update();
							try {
								Thread.sleep(10000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}

			}
		};
		temp.start();
	}

	public void update() {
		Date date;
		ArrayList<String> courseList = new ArrayList<String>();

		try {
			FileInputStream isTime = openFileInput(name + "T");

			String time;
			int length = isTime.available();
			byte[] buffer0 = new byte[length];
			isTime.read(buffer0);
			time = EncodingUtils.getString(buffer0, "GBK");
			if(length!=0)
			date = Date.parseDate(time);
			else
				date=new Date(2012,1,1,1,1);
			System.out.println("read date: " + time);
			FileInputStream isCourse = openFileInput(name + "C");

			String mycourse;
			length = isCourse.available();
			byte[] buffer = new byte[length];
			isCourse.read(buffer);
			mycourse = EncodingUtils.getString(buffer, "GBK");
			String[] s = mycourse.split("@");

			for (int i = 0; i < s.length; i++) {
				courseList.add(s[i]);
				// System.out.println(s[i]);
			}

			MainEnter me = new MainEnter(date, courseList);
			me.init(this);
			ArrayList<Anouncement> anouncelist = me.getAnouncement();

			ArrayList<String> tlist = new ArrayList<String>();
			for (Anouncement a : anouncelist) {

				if (!tlist.contains(a.courseNo)) {

					if (a.time.unread(date))

						tlist.add(a.courseNo);
				}

			}
			ArrayList<Topic> topicList = me.getTopic();
			for (Topic a : topicList) {
				if (!tlist.contains(a.courseNo)) {
					if (a.date.unread(date))
						tlist.add(a.courseNo);
				}
			}

			for (int i = 0; i < tlist.size(); i++) {
				System.out.println("ann:" + anouncelist.get(i).courseNo);
				int flag = 0;
				for (int j = 0; j < courseList.size(); j++) {
					if (courseList.get(j).equals(tlist.get(i))) {
						NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
						Notification notification = new Notification(
								R.drawable.sign, "new Anouncement",
								System.currentTimeMillis());
						// Intent intentd = new Intent(this,DfActivity.class);
						PendingIntent contentIntent = PendingIntent
								.getActivity(this, 0, intent, 0);
						notification.setLatestEventInfo(this, "TSS",
								"You Have New Anouncement  " + time,
								contentIntent);
						manager.notify(1, notification);
						// manager.notify(0,notification);
						flag = 1;

						FileOutputStream ost = openFileOutput(name + "T",
								Context.MODE_PRIVATE);
						Time times = new Time("GMT+8");
						times.setToNow();
						Date d = new Date(times.year, times.month + 1,
								times.monthDay, times.hour, times.minute);
						String dateS = d.toString();
						System.out.println("date save : " + dateS);
						ost.write(dateS.getBytes());
						ost.close();
						MainActivity.me.init(this);
						break;
					}
				}
				if (flag == 1) {
					break;
				}
			}
			isTime.close();
			isCourse.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
