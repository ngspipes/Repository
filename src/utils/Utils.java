package utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Utils {
	
	public static String getStackTrace(Throwable t){
		StringWriter sw = new StringWriter();
		t.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
	
	public static boolean equals(Object a, Object b){
		if((a==null && b!=null) || (a!=null && b==null))
			return false;
		
		if(a == b)
			return true;
		
		return a.equals(b);
	}
	
}
