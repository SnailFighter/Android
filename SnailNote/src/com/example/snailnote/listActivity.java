//显示文章列表
package com.example.snailnote;

import java.util.ArrayList;
import java.util.List;

import util.saveToSDutil;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class listActivity extends ListActivity {
	private ListView listview;
	private List<String> list;
	private Bundle bundle;
	private String title;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.articlelist);
		SysApplication.getInstance().addActivity(this);
		//Log.i("xxx","dddddddddddd");
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, // 注意顺序
				R.layout.listtitle);
		list=new saveToSDutil().allFile();
		// listview=(ListView)findViewById(R.id.);
		// 设置一个adpter，并且加载这个adpter
		setListAdapter(new myadapter(this));
		//要注意这一步的位置，不能放的太前，不能不能获取ListView，原因你可能是前边ListActivity中还没有加载ListView这个控件，故不能获得控件
		listview=getListView();
//		LinearLayout toWrite = (LinearLayout) getWindow().getLayoutInflater()
//				.inflate(R.layout.listtitle, null);
		//不用使用上面的代码
		ImageButton to = (ImageButton) findViewById(R.id.toWrite);
		to.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				bundle=new Bundle();
				bundle.putBoolean("openA", false);
				Intent intent = new Intent(listActivity.this, note.class);
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});
		//item上的单击事件
		listview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> myadapter, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				//打开对应的文本，在note中显示出来。传递一个bundle对象，放一个标志位
				bundle=new Bundle();
				bundle.putBoolean("openA", true);
				//传递文章标题
				bundle.putString("title",list.get(position).toString());
				Intent intent=new Intent(listActivity.this,note.class);
				intent.putExtras(bundle);
				startActivity(intent);
			}});
		
		listview.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> myadapter, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//创建一个对话框，删除文件与重命名文件两个功能
				AlertDialog.Builder builder=new AlertDialog.Builder(listActivity.this);
				builder.setTitle("操作选型");
				builder.setItems(R.array.select, new DialogInterface.OnClickListener(){
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						switch(which){
						case 0:
							Log.i("1111","eeeeee");
							final LinearLayout Rename=(LinearLayout)getLayoutInflater().inflate(R.layout.rename, null);
							
							AlertDialog.Builder build=new AlertDialog.Builder(listActivity.this);
							build.setTitle("重命名");
							build.setView(Rename);
							build.setPositiveButton("确定", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									EditText rename=(EditText)Rename.findViewById(R.id.re);
									if(!rename.getText().toString().equals("")){
										String newname=rename.getText().toString();
										new saveToSDutil().renameFile(newname, title);
									}else{
										return;
									}
								}
							});
							build.setNegativeButton("取消", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									
								}
							});
							build.show();
							break;
						case 1:
							saveToSDutil tool=new saveToSDutil();
							Boolean flag1=tool.deleteFile(title);
							if(flag1==true){
								Toast.makeText(listActivity.this, "删除成功！！！！", 1000).show();
							}
							break;
						}
					}
					
				});
				builder.show();
				
				return false;
			}});
		
		
	}
	public void onResume(){
		//在此方法中重新加载数据
		super.onResume();
		
		list=new saveToSDutil().allFile();
		// listview=(ListView)findViewById(R.id.);
		// 设置一个adpter，并且加载这个adpter
		setListAdapter(new myadapter(this));
		//要注意这一步的位置，不能放的太前，不能不能获取ListView，原因你可能是前边ListActivity中还没有加载ListView这个控件，故不能获得控件
		listview=getListView();
//		LinearLayout toWrite = (LinearLayout) getWindow().getLayoutInflater()
//				.inflate(R.layout.listtitle, null);
		//不用使用上面的代码
		ImageButton to = (ImageButton) findViewById(R.id.toWrite);
		to.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				bundle=new Bundle();
				bundle.putBoolean("openA", false);
				Intent intent = new Intent(listActivity.this, note.class);
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});
		//item上的单击事件
		listview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> myadapter, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				//打开对应的文本，在note中显示出来。传递一个bundle对象，放一个标志位
				bundle=new Bundle();
				bundle.putBoolean("openA", true);
				//传递文章标题
				bundle.putString("title",list.get(position).toString());
				Intent intent=new Intent(listActivity.this,note.class);
				intent.putExtras(bundle);
				startActivity(intent);
			}});
		
		listview.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> myadapter, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//创建一个对话框，删除文件与重命名文件两个功能
				AlertDialog.Builder builder=new AlertDialog.Builder(listActivity.this);
				builder.setTitle("操作选型");
				builder.setItems(R.array.select, new DialogInterface.OnClickListener(){
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						switch(which){
						case 0:
							Log.i("1111","eeeeee");
							final LinearLayout Rename=(LinearLayout)getLayoutInflater().inflate(R.layout.rename, null);
							
							AlertDialog.Builder build=new AlertDialog.Builder(listActivity.this);
							build.setTitle("重命名");
							build.setView(Rename);
							build.setPositiveButton("确定", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									EditText rename=(EditText)Rename.findViewById(R.id.re);
									if(!rename.getText().toString().equals("")){
										String newname=rename.getText().toString();
										new saveToSDutil().renameFile(newname, title);
										onResume();
									}else{
										return;
									}
								}
							});
							build.setNegativeButton("取消", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									
								}
							});
							build.show();
							break;
						case 1:
							saveToSDutil tool=new saveToSDutil();
							Boolean flag1=tool.deleteFile(title);
							if(flag1==true){
								Toast.makeText(listActivity.this, "删除成功！！！！", 1000).show();
								onResume();
							}
							break;
						}
					}
					
				});
				builder.show();
				
				return false;
			}});
	}

	 class myadapter extends BaseAdapter {
		private Context mContext;

		public myadapter(Context context) {
			this.mContext = context;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//list=new ArrayList<String>();
			//把查询到的文章放入list中
			//list=new saveToSDutil().allFile();
			// TODO Auto-generated method stub
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.item, null);
				ItemViewCache viewCache = new ItemViewCache();
				viewCache.mTextView = (TextView) convertView
						.findViewById(R.id.txt);
				viewCache.mImageView = (ImageView) convertView
						.findViewById(R.id.txtimage);
				convertView.setTag(viewCache);
			}
			ItemViewCache cache = (ItemViewCache) convertView.getTag();
			// 设置文本和图片，然后返回这个View，用于ListView的Item的展示
			title=list.get(position).toString();
			cache.mTextView.setText(title);
			
			cache.mImageView.setImageResource(R.drawable.txt);
			return convertView;
		}

	}

	// 元素的缓冲类,用于优化ListView
	private static class ItemViewCache {
		public TextView mTextView;
		public ImageView mImageView;
	}

	// 展示的文字
	//private String[] texts = new String[] { "天气", "我团", "背景" };
	// 展示的图片
	//private int[] images = new int[] { R.drawable.txt, R.drawable.txt, R.drawable.txt  };

}
