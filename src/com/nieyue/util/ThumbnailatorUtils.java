package com.nieyue.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 谷歌图片处理类
 * @author 聂跃
 * @date 2017年8月1日
 */
public class ThumbnailatorUtils {
	 /**
     * 图片上传
     * 
     * @Title upload
     * @param request
     *            文件上传目录：比如upload(无需带前面的/) upload/news ..
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    public static String upload(HttpServletRequest request, String path, String rooturl, String imgdir) throws IllegalStateException,
            IOException {
    	Map<String, Integer> kgmap = new HashMap<String ,Integer>();
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession()
                .getServletContext());
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
        
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 记录上传过程起始时的时间，用来计算上传时间
                // int pre = (int) System.currentTimeMillis();
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                	return fileUpload(file,request.getSession(),path,rooturl,imgdir,kgmap);
                    }
                }
                // 记录上传该文件后的时间
                // int finaltime = (int) System.currentTimeMillis();
                // System.out.println(finaltime - pre);
        }
        return "";
    }
	/**
	 * FormData上传图片 图片
	 * rooturl /resources/sellerUpload 
	 * imgdir sellerid 示例 4
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException
	 */
	public static String fileUpload(MultipartFile file, HttpSession session, String path, String rooturl, String imgdir, Map<String,Integer> kgmap) throws IllegalStateException, IOException{
		//取得当前上传文件的文件名称  
		String fileName = null;
		String fileOriginalName=null;
		File localFile=null;
		//定义上传路径  
		if(path==null){
			 path = session.getServletContext().getRealPath("/");
		}
		if(!file.isEmpty()){
         //创建路径
           if(imgdir!=null){
           MyFile myfile=new MyFile();
           myfile.createDir(path+rooturl+"/"+imgdir);
           }
           fileOriginalName=file.getOriginalFilename();
          /* System.err.println(file.getContentType());
           System.err.println(file.getName());
           System.err.println(file.getOriginalFilename().lastIndexOf("."));
           System.err.println(file.getContentType());*/
           //建立新图片
           //只有大于100kb的png转换 
           if((fileOriginalName.lastIndexOf(".png")>-1
        		   ||fileOriginalName.lastIndexOf(".PNG")>-1)&&(file.getSize()>100*1024)){
        	   fileName = DateUtil.timeStamp()+(int)(Math.random()*1000)+".jpg"; 
        	   localFile = new File(path+rooturl+"/"+imgdir,fileName); 
        	   setThumbnailsJPG(file,localFile,kgmap);
        	   return rooturl+"/"+imgdir+"/"+fileName;
           }else{
        	   fileName = DateUtil.timeStamp()+(int)(Math.random()*1000)+fileOriginalName.substring(fileOriginalName.lastIndexOf(".")); 
        	   localFile = new File(path+rooturl+"/"+imgdir,fileName); 
        	   setThumbnails(file,localFile,kgmap);
              // file.transferTo(localFile);
           }
           }
		return rooturl+"/"+imgdir+"/"+fileName;
	}
	/**
	 * 设置缩小改为jpg初始化
	 * @throws IOException
	 */
	private static void setThumbnailsJPG(MultipartFile file,File localFile,Map<String,Integer> kgmap) throws IOException{
		Builder<? extends InputStream> jpgbuilder = Thumbnails.of(file.getInputStream()).outputFormat("jpg");   
		if(kgmap.get("width")!=null
				&&!kgmap.get("width").equals("")
				&&kgmap.get("height")!=null
				&&!kgmap.get("height").equals("")
				){
			jpgbuilder
			.size(kgmap.get("width"),kgmap.get("height"))
			.keepAspectRatio(false)
			.toFile(localFile.getAbsolutePath());
			}else{
				if(kgmap.get("compression").equals(1)){//压缩
				jpgbuilder
				.scale(1f)
				.outputQuality(1.0d)
				.toFile(localFile.getAbsolutePath());
				}else{
					file.transferTo(localFile);
				}
			}
	}
	/**
	 * 设置正常
	 * @throws IOException
	 */
	private static void setThumbnails(MultipartFile file,File localFile,Map<String,Integer> kgmap) throws IOException{
		Builder<? extends InputStream> jpgbuilder = Thumbnails.of(file.getInputStream());   
		if(kgmap.get("width")!=null
				&&!kgmap.get("width").equals("")
				&&kgmap.get("height")!=null
				&&!kgmap.get("height").equals("")
				){
			jpgbuilder
			.size(kgmap.get("width"),kgmap.get("height"))
			.keepAspectRatio(false)
			.toFile(localFile.getAbsolutePath());
			}else{
				if(kgmap.get("compression").equals(1)){//压缩
				jpgbuilder
				.scale(1f)
				.outputQuality(1.0f)
				.toFile(localFile.getAbsolutePath());
				}else{
					file.transferTo(localFile);
				}
			}
	}
	public static void main(String[] args) {
		//final String ss="十大法师的（）——都是————。.。.png";
		//int in = ss.lastIndexOf(".");
		//System.out.println(ss.substring(ss.lastIndexOf(".")));
		Thread r = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.err.println((int)(Math.random()*1000));
				
			}
		}); 
		Thread r2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.err.println((int)(Math.random()*1000));
				
			}
		}); 
		r.start();
		r.run();
		r2.run();
	}
}
