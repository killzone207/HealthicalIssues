package cse.usf.edu.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import cse.usf.edu.android.R;


public class MainMenu extends Activity {

	String username;
	String age;
	String weight;
	String height_feet;
	String height_inches;
	
	protected Button logout;
	protected Button heartrate;
	
	TextView name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		
		logout = (Button) this.findViewById(R.id.logoutButton);
		heartrate = (Button) this.findViewById(R.id.HRM);
		
		
		Bundle b = getIntent().getExtras();
        username = b.getString("USERNAME");
        age = b.getString("AGE");
        weight = b.getString("WEIGHT");
        height_feet = b.getString("HEIGHT_FEET");
        height_inches = b.getString("HEIGHT_INCHES");
        
        name = (TextView) findViewById(R.id.username);
        name.setText("Logged in as " + username);
        
        logout.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				onButtonLogoutClick(v);
			}
		});
        
        heartrate.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		onButtonHeartRate(v);
        	}
        });
        
	}
	
	private void onButtonLogoutClick(View w){
		Intent myIntent = new Intent(w.getContext(), LoginPage.class);

	    startActivityForResult(myIntent, 0);
	}
	
	private void onButtonHeartRate(View w){
		Intent heartIntent = new Intent(w.getContext(), HeartRate.class);
		
		startActivity(heartIntent);
	}
}
