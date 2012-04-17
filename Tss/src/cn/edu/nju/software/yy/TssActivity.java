package cn.edu.nju.software.yy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TssActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button b=(Button) this.findViewById(R.id.registerbutton);
        b.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Intent intent =new Intent(TssActivity.this,LoadActivity.class);
				 startActivity(intent);
			}}
        );
        
        
    }
}