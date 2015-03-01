package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Environment;
import android.util.Log;

/*���ı��洢��sd���ϣ�
 * �����ж��Ƿ������sd��
 * ÿ���жϱ�Ŀ¼�Ƿ���ڣ�������ھ�ֱ��ʹ�ã��������񴴽�һ���ļ�Ŀ¼
 * �����ĸ�������
 * �ֱ��ж�sd�Ƿ���ڣ��ļ����Ƿ���ڣ�
 * �����ļ�
 * ��ѯ�ļ�
 * ɾ���ļ�
 * 
 */
public class saveToSDutil {

	public byte[] data = null;
	public File filePath;
	public List<String> alist = new ArrayList<String>();

	// �ж��Ƿ����sd�����Ƿ񴴽����ļ��У����û�д����ļ����򣬴����ļ���
	public void createDir() {
		try {
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				// ��ȡ��Ŀ¼
				File sdCardDir = Environment.getExternalStorageDirectory();
				// �жϱ����ı���Ŀ¼�Ƿ���ڣ�����������򴴽��ļ���fileDir.
				String path = sdCardDir.getPath() + "/��ţ�ıʼ�";
				filePath = new File(path);
				if (!filePath.exists()) {
					// �����ļ���
					filePath.mkdirs();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * �����ı� fileTitle �ı����� fileContent �ı�������
	 */
	public void saveFile(final String fileTitle, final byte[] fileContent) {
		// ����һ�����߳���ɱ������

		new Thread() {
			public void run() {
				try {
					// new File(filePath,fileTitle)���ļ�����Ϊָ���ĸ�ʽ
					FileOutputStream articleStream = new FileOutputStream(
							new File(filePath, fileTitle + ".txt"));
					// �����������ļ���
					try {
						articleStream.write(fileContent, 0, fileContent.length);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						try {
							articleStream.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}

	// ��ѯ�������ı�
	public List<String> allFile() {

		createDir();
		// List<String> list=new ArrayList<String>();

		File art[] = filePath.listFiles();
		Log.i("www",art.length+"");
		
		String[] str = new String[2];
		for (int i = 0; i < art.length; i++) {
			str= art[i].getName().split("[.]");			
			alist.add(str[0]);			
		}

		/*
		 * new Thread(){ public void run(){
		 * //���ȵ���createDir������������ΪҪ�ж��ļ��У�sd���Ƿ���ڣ���ʼ��filePath createDir();
		 * //List<String> list=new ArrayList<String>();
		 * 
		 * File art[] = filePath.listFiles(); for(int i=0;i<art.length;i++){
		 * alist.add(art[i].getName()); Log.i("11",i+""); }
		 * //Log.i("123",alist.size()+""); } }.start();
		 * Log.i("77",alist.size()+"");
		 */
		// Log.i("77",alist.size()+"");
		return alist;
	}

	/* ɾ���ı�,����true��������ʾ�û�ɾ���ɹ�
	 * ���ݴ����title��ֵɾ��ָ�����ļ�
	 */
	public Boolean deleteFile(String title) {
		Boolean flag=true;
		try{
			createDir();
			File thefile=new File(filePath+"/"+title+".txt");
			Log.i("11",thefile+"");
			Log.i("11",title);
			flag=thefile.delete();
			
			Log.i("11",flag+"");
		}catch(Exception e){
			e.printStackTrace();
			}
		return flag;
	}
	
	public void renameFile(String newname,String oldname){
		createDir();
		try{
			File oldfile=new File(filePath+"/"+oldname+".txt");
			File newfile=new File(filePath+"/"+newname+".txt");
			if(!oldfile.exists()){
				return;
			}
			if(newfile.exists()){
				//�Ѿ���������ļ�
				return;
			}else{
				//�������ļ�
				  oldfile.renameTo(newfile); 
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/*
	 * ����������Ŀ �����ı����ݣ����ǲ���ʹString������Ϊ��������¹��ر��Ļ��ڴ�Ŀ������ĺܴ� title ����һ�����±���
	 */
	public StringBuffer openFile(String title) {

		StringBuffer con = new StringBuffer();
		BufferedReader br = null;
		FileReader fr = null;
		try {
			createDir();
			fr = new FileReader(filePath + "/" + title+".txt");
			br = new BufferedReader(fr);

			String s = "";
			// ע�����壺while������ִ��while���������+1��
			while ((s = br.readLine()) != null) {
				con.append(s + "\n");
			}
			// if(s==null){s="û������";}
			// Log.i("y",s);
			// do{int i=0;i++;Log.i("22",i+"");}while((s=br.readLine())!=null);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return con;

	}

}
/*
 * class savefile implements Runnable { List<String> list=new
 * ArrayList<String>(); public savefile(List<String> alist) { // TODO
 * Auto-generated constructor stub alist=this.list; }
 * 
 * 
 * 
 * @Override public void run() { // TODO Auto-generated method stub saveToSDutil
 * a=new saveToSDutil(); a.createDir();
 * 
 * File art[] = a.filePath.listFiles(); for (int i = 0; i < art.length; i++) {
 * list.add(art[i].getName());
 * 
 * }
 * 
 * } }
 */
