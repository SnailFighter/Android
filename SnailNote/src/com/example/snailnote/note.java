package com.example.snailnote;

import java.io.UnsupportedEncodingException;
import android.app.ActionBar;
import util.saveToSDutil;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;

public class note extends Activity implements OnClickListener {
	private EditText title, content;
	private Button size, color, destory, other;
	// ImageButton add,sub;
	// TextView simpleView;
	final String tag = "info";
	private Intent intent;
	private ActionBar actionbar;
	private Tag INFORMATION;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.note);
		//getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, // 注意顺序
		//		R.layout.notetltle);
		SysApplication.getInstance().addActivity(this);
		size = (Button) findViewById(R.id.button1);
		color = (Button) findViewById(R.id.button2);
		other = (Button) findViewById(R.id.button3);
		destory = (Button) findViewById(R.id.button4);
		//title = (EditText) findViewById(R.id.edittext00);
	
		content = (EditText) findViewById(R.id.editText2);
		actionbar=getActionBar();
		
		actionbar.setCustomView(R.layout.edittext00);  
		actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM  
		        | ActionBar.DISPLAY_SHOW_HOME);
		// add=(ImageButton)findViewById(R.id.imageButton1);
		// sub=(ImageButton)findViewById(R.id.imageButton2);
		// simpleView=(TextView)findViewById(R.id.textView1);
		size.setOnClickListener(this);
		color.setOnClickListener(this);
		destory.setOnClickListener(this);
		other.setOnClickListener(this);
		
		//由listActivity中传递一个bundle，取一个标志  ，用于判断是否加载相应的文本内容
		/*
		 * 这里需要注意，如果是从listActivity中标题栏中的按钮启动note，那么是没有intent传递过来的，那么下面的程序是会产生空指针异常的。
		 * */
		Bundle bundle = getIntent().getExtras();		
		boolean flag=bundle.getBoolean("openA");
		String thetil=bundle.getString("title");
		
		if(flag==true){
			saveToSDutil tool=new saveToSDutil();
			
			title = (EditText) findViewById(R.id.edittext00);
			title.setText(thetil);
			
			content.setText(tool.openFile(thetil).toString());
		}else{
			title = (EditText) findViewById(R.id.edittext00);
			title.setText("");
		
			content.setText("");
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.save:
			saveToSDutil savetool = new saveToSDutil();
			savetool.createDir();
			try {
				savetool.saveFile(title.getText().toString().trim(), content
						.getText().toString().getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.quit:
			//finish();
			SysApplication.getInstance().exit();
			break;

		}
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			RelativeLayout sizeSelect = (RelativeLayout) getLayoutInflater()
					.inflate(R.layout.sizeselect, null);
			AlertDialog.Builder sizeBuilder = new AlertDialog.Builder(this);
			sizeBuilder.setTitle("设置字体大小");
			sizeBuilder.setIcon(R.drawable.sizefont);
			// sizeBuilder.setMessage("dddddddddd");
			sizeBuilder.setView(sizeSelect);
			sizeBuilder.setPositiveButton("ok", null);
			sizeBuilder.create();
			sizeBuilder.show();
			ImageButton add = (ImageButton) sizeSelect
					.findViewById(R.id.imageButton2);
			ImageButton sub = (ImageButton) sizeSelect
					.findViewById(R.id.imageButton1);
			final TextView simpleView = (TextView) sizeSelect
					.findViewById(R.id.textView1);
			simpleView.setTextSize(content.getTextSize());
			// final float fontsize=simpleView.getTextSize();

			add.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					float fontsize = content.getTextSize();
					// simpleView.setTextSize(content.getTextSize());
					if ((content.getTextSize() + 1.0f) >= 50.0f) {
						simpleView.setTextSize(50.0f);
						title.setTextSize(50.0f);
					} else {
						simpleView.setTextSize(fontsize + 0.5f);
						title.setTextSize(fontsize + 0.5f);
						content.setTextSize(fontsize + 0.5f);
					}
				}
			});

			sub.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					float fontsize = content.getTextSize();
					// simpleView.setTextSize(content.getTextSize());
					if ((content.getTextSize() - 1.0f) <= 20.0f) {
						simpleView.setTextSize(20.0f);
						content.setTextSize(20.0f);
					} else {
						simpleView.setTextSize(fontsize - 0.5f);
						title.setTextSize(simpleView.getTextSize() - 0.5f);
						content.setTextSize(fontsize - 0.5f);
					}

				}
			});

			
			break;
		case R.id.button2:
			break;
		case R.id.button3:
			break;
		case R.id.button4:
			title.setText("");
			title.setTextSize(20.0f);
			content.setText("");
			content.setTextSize(20.0f);
			break;
		}

	}
}