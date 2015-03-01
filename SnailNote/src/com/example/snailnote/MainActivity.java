package com.example.snailnote;



import java.io.FileOutputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView imageview;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//hide the window's title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		imageview=(ImageView)findViewById(R.id.imageview1);
		//set the translate animation 
		AnimationSet animationset=new AnimationSet(true);
		TranslateAnimation translateanimation=new TranslateAnimation(0.0f,400.0f,550.0f,550.0f);
		AlphaAnimation alphaanimation=new AlphaAnimation(1.0f,0.0f);
		
		translateanimation.setDuration(3000);
		alphaanimation.setDuration(3000);
		alphaanimation.setRepeatCount(0);
		translateanimation.setRepeatCount(0);
		animationset.addAnimation(translateanimation);
		animationset.addAnimation(alphaanimation);
		animationset.setFillAfter(true);
		animationset.setFillBefore(false);
		//imageview.setAnimation(translateanimation);
		imageview.startAnimation(animationset);
		animationset.setAnimationListener(new myListener());
		
		
	}
	 class myListener implements AnimationListener{

		@Override
		public void onAnimationEnd(Animation animation) {
			// TODO Auto-generated method stub
			startOther();			
		}
		private void startOther() {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MainActivity.this,listActivity.class);
			startActivity(intent);
			finish();
		}
		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub
		}
		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
