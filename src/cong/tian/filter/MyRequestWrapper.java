package cong.tian.filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyRequestWrapper extends HttpServletRequestWrapper {


	
	public MyRequestWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ServletInputStream getInputStream() throws IOException {
		
		// TODO Auto-generated method stub
		ServletInputStream stream = null;
		stream = super.getInputStream();
		byte[] buf = new  byte[1024];
		int len=-1;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while((len=stream.read(buf))!=-1){
			baos.write(buf, 0, len);
			
		}
		
		
		System.out.println("ios-->"+new String(baos.toByteArray(),"UTF-8"));
		String temp = new String(GZipUtil.unzip(baos.toByteArray()),"UTF-8");
		System.out.println("temp-->"+temp);
		TripleDESEncrypt encrypt = TripleDESEncrypt.getInstance();
		byte[] src = encrypt.decryptMode(encrypt.decode(temp)) ;
		System.out.println("servlerSteram--->"+new String(src,"UTF-8"));
		
		final ByteArrayInputStream bais = new ByteArrayInputStream(src);
		
		ServletInputStream servletStream = new ServletInputStream() {
			
			@Override
			public int read() throws IOException {
				// TODO Auto-generated method stub
				return bais.read();
			}
		};
		
		
		return servletStream;
	}

}
