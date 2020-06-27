package util;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {

	public static String getTimes(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String str = sdf.format(new Date());
		return str;
	}
	
	public static String nullToString(String str){
		if(str==null){
			str="";
		}
		return str;
	}

	public static String DateTime(){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(date);
	}

}
