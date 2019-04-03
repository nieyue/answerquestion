package com.nieyue.util;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * 图片地址转换
 * @author yy
 *
 */
public class IMGIndivisibleUtil {
	/**
	 * 多地址合一
	 * @param imgs
	 * @return
	 */
	public static String arrayToString(String[] imgs){
		String img="";
	    for (int i = 0; i < imgs.length; i++) {
				img+=imgs[i]+"NY@";
			}
		return img;
	}
	/**
	 * 分割成地址数组
	 * @param img
	 * @return
	 */
	public static String[] stringToArray(String img){
		String[] imgs;
		imgs=img.split("NY@");
		return imgs;
	}
	/**
	 * 分割成一个地址
	 * @param imgs
	 * @return
	 */
	public static String arrayToOne(String[] imgs){
		String img="";
		for (int i = 0; i < imgs.length; i++) {
			img+=imgs[i];
		}
		return img;
	}
	public static byte[] readInputStream(InputStream inStream) throws Exception{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while( (len=inStream.read(buffer)) != -1 ){
			outStream.write(buffer, 0, len); }
			inStream.close();
		return outStream.toByteArray();
	}
	/**
	 * 将图片转换成Base64编码 ,带头文件
	 * @param imgFile 待处理图片
	 * @return
	 */
	public static String imageToBase64Head(String imgFile){
		//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		String type = imgFile.substring(imgFile.length()-3,imgFile.length());
		//为编码添加头文件字符串
		String head = "data:image/"+type+";base64,";

		return head + imgBase64FromUrl(imgFile);
	}/**
	 * 将一张网络图片转化成File指定路径
	 * @param imgURL
	 * @return
	 */
	public static void imgPathFromUrl(String imgURL,String filePath,String fileName) {
		ByteArrayOutputStream data = new ByteArrayOutputStream();
		try {
			// 创建URL
			URL url = new URL(imgURL);
			byte[] by = new byte[1024];
			// 创建链接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			conn.setRequestProperty("Referer", "");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36");
			conn.setDoOutput(true);
			InputStream is = conn.getInputStream();
			// 将内容读取内存中
			int len = -1;
			while ((len = is.read(by)) != -1) {
				data.write(by, 0, len);
			}
			File file = null;
			//创建文件目录
			File  dir=new File(filePath);
			if (!dir.exists() && !dir.isDirectory()) {
				dir.mkdirs();
			}
			FileOutputStream fos = null;
			try {
				file=new File(filePath+"\\"+fileName);
				fos = new FileOutputStream(file);
				fos.write(data.toByteArray());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			/*try {
				Files.write(Paths.get(filePath+fileName), Base64.getDecoder().decode(data.toByteArray()), StandardOpenOption.CREATE);
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			// 关闭流
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 将一张网络图片转化成Base64字符串
	 * @param imgURL
	 * @return
	 */
	public static String imgBase64FromUrl(String imgURL) {
		ByteArrayOutputStream data = new ByteArrayOutputStream();
		try {
			// 创建URL
			URL url = new URL(imgURL);
			byte[] by = new byte[1024];
			// 创建链接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			conn.setRequestProperty("Referer", "");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
			"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36");
			conn.setDoOutput(true);
			InputStream is = conn.getInputStream();
			// 将内容读取内存中
			int len = -1;
			while ((len = is.read(by)) != -1) {
				data.write(by, 0, len);
			}
			// 关闭流
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		return Base64.getEncoder().encodeToString(data.toByteArray());
		//return new BASE64Encoder().encode(data.toByteArray());
	}

	/**
	 *
	 * @description 将本地文件转base64字符串
	 */
	public static  String fileToBase64(String path) {
		//io
		/*String base64 = null;
		InputStream in = null;
		try {
			File file = new File(path);
			ByteArrayOutputStream data = new ByteArrayOutputStream();
			in = new FileInputStream(file);
			byte[] by = new byte[1024];
			int len = -1;
			byte[] bytes;
			while ((len = in.read(by)) != -1) {
				data.write(by, 0, len);
			}
			base64=Base64.getEncoder().encodeToString(data.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//return "data:image/"+path.substring(path.length()-3,path.length())+";base64,"+base64;
		//System.out.println(base64);
		return base64;*/
		//nio
		if (path == null) {
			return null;
		}
		try {
			byte[] b = Files.readAllBytes(Paths.get(path));
			return Base64.getEncoder().encodeToString(b);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 将base64图片转为指定路径文件
	 * @param base64
	 * @param filePath
	 * @param fileName
	 */
	public static void base64ToFile(String base64,String filePath, String fileName) {
		//nio
		/*try {
			Files.write(Paths.get(filePath+fileName), Base64.getDecoder().decode(base64), StandardOpenOption.CREATE);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		//io
		File file = null;
		//创建文件目录
		File  dir=new File(filePath);
		if (!dir.exists() && !dir.isDirectory()) {
			dir.mkdirs();
		}
		FileOutputStream fos = null;
		try {
			byte[] bytes = Base64.getDecoder().decode(base64);
			//byte[] bytes = new BASE64Decoder().decodeBuffer(base64);
			file=new File(filePath+"\\"+fileName);
			fos = new FileOutputStream(file);
			fos.write(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		String [] ss={"sdfsdf","233423"};
		System.out.println(ss instanceof String[]);
		System.out.println(arrayToString(ss));
		System.out.println(stringToArray(arrayToString(ss))[0]);
		System.out.println(arrayToOne(ss));
		//System.out.println(imgBase64FromUrl("https://mmbiz.qpic.cn/mmbiz_jpg/Fo7Ted9jEoiaiaNWlUicK1Or3cde83bmbbGKEQOetL0VvVg0Y2r9oqibxVkkEUuTG3g5xEqyAibqRw6KicicgNMTLQnibQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1"));
		//System.out.println(imageToBase64Head("http://pic1.wed114.cn/allimg/180831/64_180831180355_1.jpg"));

		//IMGIndivisibleUtil.base64ToFile(IMGIndivisibleUtil.imgBase64FromUrl("http://pic1.wed114.cn/allimg/180831/64_180831180355_1.jpg"),"D:\\nieyue\\uploaderPath\\img\\20180907","\\2.jpg");
		/*String imgUrl = "https://mmbiz.qpic.cn/mmbiz_gif/EtxOn3KiaVUwDjhcZnyPUErX1987gazv9miawm7vA4D2uUftvIl81bIUiagxsgvmYfibHmfSjOEke0Wgj9K6BaS7dw/640?wx_fmt=gif&tp=webp&wxfrom=5&wx_lazy=1";
		String base64 = IMGIndivisibleUtil.imgBase64FromUrl(imgUrl);
		IMGIndivisibleUtil.base64ToFile(base64,"D:\\nieyue\\uploaderPath\\img\\20180908","2.gif");
		String imgUrl2="https://mmbiz.qpic.cn/mmbiz_jpg/EtxOn3KiaVUwDjhcZnyPUErX1987gazv9Wv6uKFppm9HLsgl932Oglw58XYicEIMpQYYL2FHqXicQibiazpWEfO8ugQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1";
		String base642 = IMGIndivisibleUtil.imgBase64FromUrl(imgUrl2);
		IMGIndivisibleUtil.base64ToFile(base642,"D:\\nieyue\\uploaderPath\\img\\20180908","22.jpe");*/
		String imgUrl = "https://mmbiz.qpic.cn/mmbiz_gif/EtxOn3KiaVUwDjhcZnyPUErX1987gazv9miawm7vA4D2uUftvIl81bIUiagxsgvmYfibHmfSjOEke0Wgj9K6BaS7dw/640?wx_fmt=gif&tp=webp&wxfrom=5&wx_lazy=1";
		IMGIndivisibleUtil.imgPathFromUrl(imgUrl,"D:\\nieyue\\uploaderPath\\img\\20180908","2.gif");
		String imgUrl2="https://mmbiz.qpic.cn/mmbiz_jpg/EtxOn3KiaVUwDjhcZnyPUErX1987gazv9Wv6uKFppm9HLsgl932Oglw58XYicEIMpQYYL2FHqXicQibiazpWEfO8ugQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1";
		IMGIndivisibleUtil.imgPathFromUrl(imgUrl2,"D:\\nieyue\\uploaderPath\\img\\20180908","22.jpe");

	}
}
