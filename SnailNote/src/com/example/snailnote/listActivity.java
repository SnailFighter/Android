//��ʾ�����б�
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
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, // ע��˳��
				R.layout.listtitle);
		list=new saveToSDutil().allFile();
		// listview=(ListView)findViewById(R.id.);
		// ����һ��adpter�����Ҽ������adpter
		setListAdapter(new myadapter(this));
		//Ҫע����һ����λ�ã����ܷŵ�̫ǰ�����ܲ��ܻ�ȡListView��ԭ���������ǰ��ListActivity�л�û�м���ListView����ؼ����ʲ��ܻ�ÿؼ�
		listview=getListView();
//		LinearLayout toWrite = (LinearLayout) getWindow().getLayoutInflater()
//				.inflate(R.layout.listtitle, null);
		//����ʹ������Ĵ���
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
		//item�ϵĵ����¼�
		listview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> myadapter, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				//�򿪶�Ӧ���ı�����note����ʾ����������һ��bundle���󣬷�һ����־λ
				bundle=new Bundle();
				bundle.putBoolean("openA", true);
				//�������±���
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
				//����һ���Ի���ɾ���ļ����������ļ���������
				AlertDialog.Builder builder=new AlertDialog.Builder(listActivity.this);
				builder.setTitle("����ѡ��");
				builder.setItems(R.array.select, new DialogInterface.OnClickListener(){
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						switch(which){
						case 0:
							Log.i("1111","eeeeee");
							final LinearLayout Rename=(LinearLayout)getLayoutInflater().inflate(R.layout.rename, null);
							
							AlertDialog.Builder build=new AlertDialog.Builder(listActivity.this);
							build.setTitle("������");
							build.setView(Rename);
							build.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
								
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
							build.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
								
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
								Toast.makeText(listActivity.this, "ɾ���ɹ���������", 1000).show();
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
		//�ڴ˷��������¼�������
		super.onResume();
		
		list=new saveToSDutil().allFile();
		// listview=(ListView)findViewById(R.id.);
		// ����һ��adpter�����Ҽ������adpter
		setListAdapter(new myadapter(this));
		//Ҫע����һ����λ�ã����ܷŵ�̫ǰ�����ܲ��ܻ�ȡListView��ԭ���������ǰ��ListActivity�л�û�м���ListView����ؼ����ʲ��ܻ�ÿؼ�
		listview=getListView();
//		LinearLayout toWrite = (LinearLayout) getWindow().getLayoutInflater()
//				.inflate(R.layout.listtitle, null);
		//����ʹ������Ĵ���
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
		//item�ϵĵ����¼�
		listview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> myadapter, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				//�򿪶�Ӧ���ı�����note����ʾ����������һ��bundle���󣬷�һ����־λ
				bundle=new Bundle();
				bundle.putBoolean("openA", true);
				//�������±���
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
				//����һ���Ի���ɾ���ļ����������ļ���������
				AlertDialog.Builder builder=new AlertDialog.Builder(listActivity.this);
				builder.setTitle("����ѡ��");
				builder.setItems(R.array.select, new DialogInterface.OnClickListener(){
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						switch(which){
						case 0:
							Log.i("1111","eeeeee");
							final LinearLayout Rename=(LinearLayout)getLayoutInflater().inflate(R.layout.rename, null);
							
							AlertDialog.Builder build=new AlertDialog.Builder(listActivity.this);
							build.setTitle("������");
							build.setView(Rename);
							build.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
								
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
							build.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
								
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
								Toast.makeText(listActivity.this, "ɾ���ɹ���������", 1000).show();
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
			//�Ѳ�ѯ�������·���list��
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
			// �����ı���ͼƬ��Ȼ�󷵻����View������ListView��Item��չʾ
			title=list.get(position).toString();
			cache.mTextView.setText(title);
			
			cache.mImageView.setImageResource(R.drawable.txt);
			return convertView;
		}

	}

	// Ԫ�صĻ�����,�����Ż�ListView
	private static class ItemViewCache {
		public TextView mTextView;
		public ImageView mImageView;
	}

	// չʾ������
	//private String[] texts = new String[] { "����", "����", "����" };
	// չʾ��ͼƬ
	//private int[] images = new int[] { R.drawable.txt, R.drawable.txt, R.drawable.txt  };

}
