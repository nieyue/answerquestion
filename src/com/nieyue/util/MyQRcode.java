package com.nieyue.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.EnumMap;
import java.util.Hashtable;

/**
 * 二维码
 */
public class MyQRcode {
    
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;
    private static final int margin = 0;
    private static final int LogoPart = 4;
    
    /**
     * 生成二维码矩阵信息
     * @param content 二维码图片内容
     * @param width 二维码图片宽度
     * @param height 二维码图片高度
     */
    public static BitMatrix setBitMatrix(String content, int width, int height){
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // 指定编码方式,防止中文乱码
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); // 指定纠错等级
        hints.put(EncodeHintType.MARGIN, margin); // 指定二维码四周白色区域大小
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return bitMatrix;
    }
    /**
     * 解析二维码图片
     * @param filePath 图片路径
     */
    public static String decodeQR(String filePath) {
        if ("".equalsIgnoreCase(filePath) && filePath.length() == 0) {
            return "二维码图片不存在!";
        }
        String content = "";
        EnumMap<DecodeHintType, Object> hints = new EnumMap<DecodeHintType, Object>(DecodeHintType.class);
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8"); // 指定编码方式,防止中文乱码
        try {
            BufferedImage image = ImageIO.read(new FileInputStream(filePath));
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            MultiFormatReader reader = new MultiFormatReader();
            Result result = reader.decode(binaryBitmap, hints);
            content = result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
    /** 
     * 将二维码图片输出
     * @param matrix 二维码矩阵信息
     * @param format 图片格式
     * @param outStream 输出流
     * @param logoPath logo图片路径
     */  
    public static void writeToFile(BitMatrix matrix, String format, OutputStream outStream, String logoPath) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        // 加入LOGO水印效果
        if (StringUtils.isNotBlank(logoPath)) {
            image = addLogo(image, logoPath);
        }
        ImageIO.write(image, format, outStream);
    }
    
    /**
     * 生成二维码图片
     * @param matrix 二维码矩阵信息
     */
    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }
    
    /**
     * 在二维码图片中添加logo图片
     * @param image 二维码图片
     * @param logoPath logo图片路径
     */
    public static BufferedImage addLogo(BufferedImage image, String logoPath) throws IOException {
        Graphics2D g = image.createGraphics();
        BufferedImage logoImage = ImageIO.read(new File(logoPath));
        // 计算logo图片大小,可适应长方形图片,根据较短边生成正方形
        int width = image.getWidth() < image.getHeight() ? image.getWidth() / LogoPart : image.getHeight() / LogoPart;
        int height = width;
        // 计算logo图片放置位置
        int x = (image.getWidth() - width) / 2;
        int y = (image.getHeight() - height) / 2;
        // 在二维码图片上绘制logo图片
        g.drawImage(logoImage, x, y, width, height, null);
        // 绘制logo边框,可选
//        g.drawRoundRect(x, y, logoImage.getWidth(), logoImage.getHeight(), 10, 10);
        g.setStroke(new BasicStroke(2)); // 画笔粗细
        g.setColor(Color.WHITE); // 边框颜色
        g.drawRect(x, y, width, height); // 矩形边框
        logoImage.flush();
        g.dispose();
        return image;
    }
    /**
     * 在图片中添加二维码图片
     * @param imgPath 图片路径
     * @param qrcodePath 二维码图片路径
     * @param width 二维码图片宽
     * @param height 二维码图片高
     * @param x 二维码图片x路径
     * @param y 二维码图片y路径
     * @return  outPath 输出路径
     */
    public static void imgAddQrcode(String imgPath, String qrcodePath,String outPath,int width,int height,int x,int y) throws IOException {

        BufferedImage imgImage = ImageIO.read(new File(imgPath));
        Graphics2D g = imgImage.createGraphics();
        BufferedImage qrcodeImage = ImageIO.read(new File(qrcodePath));
        // 计算二维码图片大小,可适应长方形图片,根据较短边生成正方形
        // 计算二维码图片放置位置
        // 在图片上绘制二维码图片
        g.drawImage(qrcodeImage, x, y, width, height, null);
        // 绘制二维码边框,可选
//        g.drawRoundRect(x, y, logoImage.getWidth(), logoImage.getHeight(), 10, 10);
        g.setStroke(new BasicStroke(2)); // 画笔粗细
        g.setColor(Color.WHITE); // 边框颜色
        g.drawRect(x, y, width, height); // 矩形边框
        qrcodeImage.flush();
        g.dispose();
        ImageIO.write(imgImage, "jpg", new FileOutputStream(new File(outPath)));
    }
    /**
     * 为图片添加文字
     * @param pressText 文字
     * @param newImage 带文字的图片
     * @param targetImage 需要添加文字的图片
     * @param fontStyle 字体风格
     * @param color 字体颜色
     * @param fontSize 字体大小
     * @param width 图片宽度
     * @param height 图片高度
     */
    public static void pressText(String pressText, String newImage, String targetImage, int fontStyle, Color color, int fontSize, int width, int height) {
        // 计算文字开始的位置
        // x开始的位置：（图片宽度-字体大小*字的个数）/2
        int startX = (width-(fontSize*pressText.length()))/2;
        // y开始的位置：图片高度-（图片高度-图片宽度）/2
        int startY = height-(height-width)/2 + fontSize;
        try {
            File file = new File(targetImage);
            BufferedImage src = ImageIO.read(file);
            int imageW = src.getWidth(null);
            int imageH = src.getHeight(null);
            BufferedImage image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, imageW, imageH, null);
            g.setColor(color);
            g.setFont(new Font(null, fontStyle, fontSize));
            g.drawString(pressText, startX, startY);
            g.dispose();
            FileOutputStream out = new FileOutputStream(newImage);
            ImageIO.write(image, "png", out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建二维码
     * @param originPath 跳转链接
     * @param logoPath logo本地路径，
     * @param format 格式，如jpg
     * @param width 宽
     * @param height 高
     * @param height 高
     * @param outputStream 输出流，也可以写入本地地址
     *
     */
    public static void createQrcode(String originPath,String logoPath,String format,int width,int height,OutputStream outputStream){
        BitMatrix bitMatrix = setBitMatrix(originPath, width, height);
        try {
            writeToFile(bitMatrix, format, outputStream, logoPath);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void createQrcode(String originPath,OutputStream outputStream){
        BitMatrix bitMatrix = setBitMatrix(originPath, 300, 300);
        try {
            writeToFile(bitMatrix, "jpg", outputStream, null);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static  void test(){
        String content = "http://www.baidu.com";
        String logoPath = "D:\\nieyue\\uploaderPath\\img\\20180908\\1536409840752.png";

        String format = "jpg";
        int width = 220;
        int height = 220;
        BitMatrix bitMatrix = setBitMatrix(content, width, height);
        // 可通过输出流输出到页面,也可直接保存到文件
        OutputStream outStream = null;
        String path = "D:\\nieyue\\uploaderPath\\img\\20180908\\1536409841277.png";
        try {
            outStream = new FileOutputStream(new File(path));
            writeToFile(bitMatrix, format, outStream, null);
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 添加文字效果
        int fontSize = 12; // 字体大小
        int fontStyle = 1; // 字体风格
        String text = "测试二维码";
        String withTextPath = "D:\\nieyue\\uploaderPath\\img\\20180908\\logo3.png";
        pressText(text, withTextPath, path, fontStyle, Color.BLUE, fontSize, width, height);
        //解析
        System.out.println(decodeQR(withTextPath));
    }
    public static void main(String[] args) throws IOException {
       // test();
        /*imgAddQrcode("D:\\nieyue\\uploaderPath\\img\\20180908\\test.jpg"
                ,"D:\\nieyue\\uploaderPath\\img\\20180908\\logo3.png"
                ,"D:\\nieyue\\uploaderPath\\img\\20180908\\testout.png"
                ,150
                ,150
                ,460
                ,920
        );*/
        String result="";
        FileOutputStream fos=new FileOutputStream(new File("c:/a.png"));

        createQrcode("http://www.baidu.com",fos);

    }
}