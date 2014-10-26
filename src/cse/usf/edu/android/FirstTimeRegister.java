package cse.usf.edu.android;

import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import cse.usf.edu.android.R;

import com.loopj.android.http.*;

//import java.io.UnsupportedEncodingException;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.Header;

import org.json.JSONException;
import org.json.JSONObject;


public class FirstTimeRegister extends Activity {
	
	private AsyncHttpResponseHandler handler;
	
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
        
		handler = new AsyncHttpResponseHandler() {
			Toast toast = Toast.makeText(getBaseContext(), null,
					Toast.LENGTH_LONG);

			@Override
			public void onStart() {
				toast.setText("Attempting to submit");
				toast.setDuration(Toast.LENGTH_SHORT);
				toast.show();
			}

			@Override
			public void onFailure(int arg0, Header[] header, byte[] message,
					Throwable e) {

				toast.setText(message.toString());
				toast.show();
			}

			@Override
			public void onSuccess(int arg0, Header[] header, byte[] message) {
				try {
					toast.setText(new String(message, "UTF-8"));
					toast.show();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
	
	private void onButtonLoginClick(View w){
		Intent myIntent = new Intent(w.getContext(), MainMenu.class);
	    Bundle bundle = new Bundle();
	
//	    EditText age = (EditText) findViewById(R.id.age);
//	    EditText weight = (EditText) findViewById(R.id.weight);
//	    EditText height_feet = (EditText) findViewById(R.id.height_feet);
//	    EditText height_inches = (EditText) findViewById(R.id.height_inches);
	    
	    int age = Integer.parseInt(((EditText) findViewById(R.id.age)).getText().toString());
	    double weight = Double.parseDouble(((EditText) findViewById(R.id.weight)).getText().toString());
	    int height_feet = Integer.parseInt(((EditText) findViewById(R.id.height_feet)).getText().toString());
	    int height_inches = Integer.parseInt(((EditText) findViewById(R.id.height_inches)).getText().toString());
	    //String username = ((EditText) findViewById(R.id.username)).getText().toString();
	    //String password = ((EditText) findViewById(R.id.password)).getText().toString();
	    //CreateNewUser(username.getText(), password.getText(), age, weight, height_feet, height_inches);
	    
//	    bundle.putString("USERNAME", username.toString());
//	    bundle.putString("AGE", age.getText().toString());
//	    bundle.putString("WEIGHT", weight.getText().toString());
//	    bundle.putString("HEIGHT_FEET", height_feet.getText().toString());
//	    bundle.putString("HEIGHT_INCHES", height_inches.getText().toString());
	    
	    		
	    myIntent.putExtras(bundle);
	    startActivityForResult(myIntent, 0);
	}
	
	private void CreateNewUser(String username, String password, int age, double weight, int HeightFeet, int HeightInches)
	{
		JSONObject json = new JSONObject();
		try
		{
			json.put("UserID", username);
			json.put("Password", password);
			
			StringEntity se = new StringEntity(json.toString());
			se.setContentType("application/json");
			
			AsyncHttpClient client = new AsyncHttpClient();
			client.post(getBaseContext(), getString(R.string.BASE_URL) + "UserCredential", se, "application/json", handler);
			
			json = new JSONObject();
			json.put("UserID", username);
			if (age > 0)
				json.put("Age", age);
			if (weight > 0)
				json.put("Weight", weight);
			//implement gender handling
			if (HeightFeet > 0)
				json.put("Height", HeightFeet + HeightInches / 12.0);
			
			se = new StringEntity(json.toString());
			Header headers[] = new Header[1];
			headers[0] = new BasicHeader("Authorization", "Basic " + username + ":" + password);
			client.post(getBaseContext(), getString(R.string.BASE_URL) + "User", headers, se, "application/json", handler);
			//client.post(getBaseContext(), getString(R.string.BASE_URL) + "HeartRate", se, "application/json", handler);
		}
		catch (Exception e)
		{
			Toast.makeText(getBaseContext(), "Unable to create user!", Toast.LENGTH_LONG).show();
		}
	}
}
