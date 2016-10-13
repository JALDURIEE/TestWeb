package cong.tian.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cong.tian.filter.DESEncryptAndGzipUtil;
import cong.tian.filter.TripleDESEncrypt;

/**
 * Servlet implementation class GetJson
 */
public class GetJson extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetJson() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// String json =
		// "{\"name\":\"tiancong\",\"age\":\"23\",\"sex\":\"male\"}";
		// request.setAttribute("jdata", json);
		// request.getRequestDispatcher("test.jsp").forward(request, response);
		response.setCharacterEncoding("UTF-8");
		// response.setHeader("Content-Type", "text/html;charset=utf-8");
		InputStream is = GetJson.class.getClassLoader().getResourceAsStream("git.png");
		// String result = StreamUtil.convertStreamToString(is);
		// System.out.println("result-->"+result);
		// TripleDESEncrypt encrypt = TripleDESEncrypt.getInstance();
		// byte[] buffer = DESEncryptAndGzipUtil.encryptAndGzip(result);
		// String t = DESEncryptAndGzipUtil.unzipAndDecrypt(buffer);
		// System.out.println("t-->"+t);

		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int len = -1;
			byte[] bytes = new byte[1024];
			while ((len = is.read(bytes)) != -1) {
				bos.write(bytes, 0, len);
			}
			ServletOutputStream output = response.getOutputStream();
			// System.out.println("传输数据11:"+new String(data,"UTF-8"));
			output.write(bos.toByteArray());
			output.flush();
			output.close();
			System.out.println("success!!!!!!!!!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
