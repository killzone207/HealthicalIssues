package cse.usf.edu.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import cse.usf.edu.android.R;

public class LoginPage extends Activity {

	protected Button login;
	protected Button register;
	
	TextView loginFailed;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        
        login = (Button) this.findViewById(R.id.loginButton);
        register = (Button) this.findViewById(R.id.registerButton);
        
        loginFailed = (TextView) findViewById(R.id.loginFail);
	    
        register.setOnClickListener(new OnClickListener(){
        		public void onClick(View v) {
        			onButtonRegisterClick(v);
        		}
        });
			 
        login.setOnClickListener(new OnClickListener(){
        		public void onClick(View v) {
					onButtonLoginClick(v);
				}
        	}
        );
    }
    
    private void onButtonRegisterClick(View w){
    		Intent myIntent = new Intent(w.getContext(), FirstTimeRegister.class);
    	    Bundle bundle = new Bundle();
    	
    	    EditText username = (EditText) findViewById(R.id.inputUsername);
    	    		
    	    bundle.putString("USERNAME", username.getText().toString());
    	    		
    	    myIntent.putExtras(bundle);
    	    startActivityForResult(myIntent, 0);
    }
    
    private void onButtonLoginClick(View w)
    {
    	// if username already registered, then login
    	
    	// else, login failed -> must register first
    	loginFailed.setText("Unable to locate username in database.  Please register.");
    }
}
