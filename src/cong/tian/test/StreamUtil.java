package cong.tian.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtil {

	
	public static String convertStreamToString(InputStream is){
		
		byte[] buf = new byte[1024];
//		StringBuilder sb = new StringBuilder();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			int len = -1;
			while((len= is.read(buf))!=-1){
				output.write(buf, 0, len);
//				String str = new String(buf, 0, len);
//				sb.append(str);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output.toString();
	}
}
