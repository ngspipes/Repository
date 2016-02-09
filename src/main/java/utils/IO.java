/*-
 * Copyright (c) 2016, NGSPipes Team <ngspipes@gmail.com>
 * All rights reserved.
 *
 * This file is part of NGSPipes <http://ngspipes.github.io/>.
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;

public class IO {

	public static void write(String info, String fileName) throws IOException {
		PrintWriter writer = null;
		
		try{
			writer  = new PrintWriter(new File(fileName));			
			
			writer.println(info);
		} finally {
			if (writer != null)
				writer.close();
		}
	}	

	public static String read(String filePath) throws IOException {
		if(!new File(filePath).exists())
			filePath = getAbsolutePath(filePath);
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		String str;
		try {
			br = new BufferedReader(new FileReader(filePath));
			while((str = br.readLine()) != null) {
				sb.append(str);
				sb.append("\n");
			}
		} finally {
			if (br != null)
				br.close();
		}
		
		return sb.toString();
	}

	private static String getAbsolutePath(String fileName){
		URL s = ClassLoader.getSystemResource(fileName); 
		return s.getPath().substring(1);
	}

	public static void copyDirectory(File src, File dst) throws IOException{
		if (src.isDirectory()) {
	         if (!dst.exists()) 
	             dst.mkdir();
	         
	         for(String children : src.list()) 
	             copyDirectory(new File(src, children), new File(dst, children));
	         
	     } else {
	         InputStream in = new FileInputStream(src);
	         OutputStream out = new FileOutputStream(dst);
	          
	         byte[] buf = new byte[1024];
	         int len;
	         while ((len = in.read(buf)) > 0) 
	             out.write(buf, 0, len);

	         in.close();
	         out.close();
	     }
		
	}
	
}


