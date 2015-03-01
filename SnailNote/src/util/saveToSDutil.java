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

/*把文本存储到sd卡上，
 * 首先判断是否挂载了sd卡
 * 每次判断本目录是否存在，如果存在就直接使用，不存在择创建一个文件目录
 * 创建四个方法，
 * 分别判断sd是否存在，文件夹是否存在；
 * 保存文件
 * 查询文件
 * 删除文件
 * 
 */
public class saveToSDutil {

	public byte[] data = null;
	public File filePath;
	public List<String> alist = new ArrayList<String>();

	// 判断是否挂载sd卡，是否创建了文件夹，如果没有创建文件夹则，创建文件夹
	public void createDir() {
		try {
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				// 获取根目录
				File sdCardDir = Environment.getExternalStorageDirectory();
				// 判断保存文本的目录是否存在，如果不存在则创建文件夹fileDir.
				String path = sdCardDir.getPath() + "/蜗牛的笔记";
				filePath = new File(path);
				if (!filePath.exists()) {
					// 创建文件夹
					filePath.mkdirs();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 保存文本 fileTitle 文本标题 fileContent 文本的内容
	 */
	public void saveFile(final String fileTitle, final byte[] fileContent) {
		// 开启一个新线程完成保存操作

		new Thread() {
			public void run() {
				try {
					// new File(filePath,fileTitle)把文件保存为指定的格式
					FileOutputStream articleStream = new FileOutputStream(
							new File(filePath, fileTitle + ".txt"));
					// 把数据输入文件中
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

	// 查询并返回文本
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
		 * //首先调用createDir（）方法，因为要判断文件夹，sd卡是否存在，初始化filePath createDir();
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

	/* 删除文本,返回true，用于提示用户删除成功
	 * 根据传入的title的值删除指定的文件
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
				//已经存在这个文件
				return;
			}else{
				//重命名文件
				  oldfile.renameTo(newfile); 
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/*
	 * 返回文章题目 返回文本内容，但是不能使String对象，因为如果字数吐过特别多的话内存的开销会变的很大 title 传递一个文章标题
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
			// 注意陷阱：while条件会执行while方法体次数+1次
			while ((s = br.readLine()) != null) {
				con.append(s + "\n");
			}
			// if(s==null){s="没有内容";}
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
