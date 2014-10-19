package cse.usf.edu.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import cse.usf.edu.android.R;

public class FirstTimeRegister extends Activity {
	
	String username;
	
	protected Button login;
	
	TextView name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_time_register);
		
		login = (Button) this.findViewById(R.id.login_registration);
		
		// Obtain values passed through Intents
        Bundle b = getIntent().getExtras();
        username = b.getString("USERNAME");
        
        name = (TextView) findViewById(R.id.registeredUsername);
        name.setText("" + username + "!!");
        
        
        
        login.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				onButtonLoginClick(v);
			}
		});
	}
	
	private void onButtonLoginClick(View w){
		Intent myIntent = new Intent(w.getContext(), MainMenu.class);
	    Bundle bundle = new Bundle();
	
	    EditText age = (EditText) findViewById(R.id.age);
	    EditText weight = (EditText) findViewById(R.id.weight);
	    EditText height_feet = (EditText) findViewById(R.id.height_feet);
	    EditText height_inches = (EditText) findViewById(R.id.height_inches);
	    
	    bundle.putString("USERNAME", username.toString());
	    bundle.putString("AGE", age.getText().toString());
	    bundle.putString("WEIGHT", weight.getText().toString());
	    bundle.putString("HEIGHT_FEET", height_feet.getText().toString());
	    bundle.putString("HEIGHT_INCHES", height_inches.getText().toString());
	    
	    		
	    myIntent.putExtras(bundle);
	    startActivityForResult(myIntent, 0);
	}
}
