package cong.tian.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import cong.tian.filter.GZipUtil;
import cong.tian.filter.TripleDESEncrypt;

public class HttpRequest {
	
	public static void sendHttpRquest(){
		
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://localhost:8088/TestWeb/getRequest.do");
		post.addHeader("Accept", "image/gif,image/x-xbitmap");
		post.addHeader("Content-Type", "text/html;charset=utf-8");
		HttpEntity entity = null;
		try {
			
			String xml = "<?xml version=\"1.0\"?><root><name>tiancong</name></root>";
			TripleDESEncrypt encrypt = TripleDESEncrypt.getInstance();
			byte[] buffer = GZipUtil.gzip(encrypt.encrypt(xml));
			entity = new ByteArrayEntity(buffer);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		post.setEntity(entity);
		try {
			HttpResponse response  = client.execute(post);
			System.out.println("statuscode-->"+response.getStatusLine().getStatusCode());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		sendHttpRquest();
	}

}
