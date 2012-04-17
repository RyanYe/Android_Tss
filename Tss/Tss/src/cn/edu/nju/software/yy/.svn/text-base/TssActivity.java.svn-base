package cn.edu.nju.software.yy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.edu.nju.software.yy.getData.Date;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.format.Time;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TssActivity extends Activity {
	Intent intent;
	EditText name;
	EditText pass;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button b = (Button) this.findViewById(R.id.registerbutton);
		name = (EditText) this.findViewById(R.id.userText);
		pass = (EditText) this.findViewById(R.id.passwordText);
		
		pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
		pass.setTransformationMethod((PasswordTransformationMethod.getInstance()));
		b.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle bd = new Bundle();
				bd.putString("username", name.getText().toString());
				bd.putString("password", pass.getText().toString());

				intent = new Intent();
				intent.setClass(TssActivity.this, LoadActivity.class);
				intent.putExtras(bd);

				startActivity(intent);
			}
		});

	}

	@Override
	public void onStop() {
		/*Time time = new Time("GMT+8");
		time.setToNow();
		Date d = new Date(time.year, time.month+1, time.monthDay, time.hour,
				time.minute);
		if (LoadActivity.username != null) {
			FileOutputStream os;
			try {
				os = openFileOutput(LoadActivity.username + "T",
						Context.MODE_PRIVATE);
				String t = d.toString();
                System.out.println(t);
				os.write(t.getBytes());

				os.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
            */
		super.onStop();
	}

}