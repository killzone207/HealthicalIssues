package cse.usf.edu.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import cse.usf.edu.android.R;

public class Pedometer extends Activity {
	
	protected Button mainmenu;
	
	TextView steps;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pedometer);
		
		mainmenu = (Button) this.findViewById(R.id.mainmenu_pedometer);
		
        steps = (TextView) findViewById(R.id.steps);
		
		mainmenu.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		onButtonMainmenu(v);
        	}
        });
		
	}
		
	private void onButtonMainmenu(View w){
		Intent mainmenu_intent = new Intent(w.getContext(), MainMenu.class);
		
		// put code here for saving to database
		
	    startActivity(mainmenu_intent);
	}
}
