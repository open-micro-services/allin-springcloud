package com.boonya.springcloud.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.ParseException;
import java.util.Date;

/**
 * @Copyright: 2019-2021
 * @FileName: Base64.java
 * @Author: PJL
 * @Date: 2020/5/19 15:47
 * @Description: 图片压缩工具
 */
public class ImgCompress {
    private Image img;
    private int width;
    private int height;

    /**
     * 构造函数
     */
    public ImgCompress(String fileName) throws IOException {
        File file = new File(fileName);// 读入文件  
        img = ImageIO.read(file);      // 构造Image对象  
        width = img.getWidth(null);    // 得到源图宽  
        height = img.getHeight(null);  // 得到源图长  
    }

    /**
     * 构造函数
     *
     * @param file
     * @throws IOException
     */
    public ImgCompress(File file) throws IOException {
        img = ImageIO.read(file);      // 构造Image对象
        width = img.getWidth(null);    // 得到源图宽  
        height = img.getHeight(null);  // 得到源图长 
    }

    //获得指定文件的byte数组
    public static byte[] getBytes(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 测试方法
     *
     * @param args
     * @throws IOException
     * @throws ParseException
     */
    public static void main(String[] args) throws IOException, ParseException {
        // TODO Auto-generated method stub
        System.out.println("开始：" + new Date().toLocaleString());
        ImgCompress imgCom = new ImgCompress("F:\\IMG_0967.JPG");
        imgCom.resize(60, 80, "F:\\111J.JPG");
        System.out.println("结束：" + new Date().toLocaleString());

//        MobileService ms=new MobileService();
//        byte[] file =ImgCompress.getBytes("F:\\20150821_093748.jpg");
//        ms.uploadAttach("2015082502659", "1", "20150821_093748.jpg", file);
    }

    /**
     * 按照宽度还是高度进行压缩
     *
     * @param w int 最大宽度
     * @param h int 最大高度
     */
    public void resizeFix(int w, int h, String fileName) throws IOException {
        if (width / height > w / h) {
            resizeByWidth(w, fileName);
        } else {
            resizeByHeight(h, fileName);
        }
    }

    /**
     * 以宽度为基准，等比例放缩图片
     *
     * @param w int 新宽度
     */
    public void resizeByWidth(int w, String fileName) throws IOException {
        int h = (int) (height * w / width);
        resize(w, h, fileName);
    }

    /**
     * 以高度为基准，等比例缩放图片
     *
     * @param h int 新高度
     */
    public void resizeByHeight(int h, String fileName) throws IOException {
        int w = (int) (width * h / height);
        resize(w, h, fileName);
    }

    /**
     * 强制压缩/放大图片到固定的大小
     *
     * @param w int 新宽度
     * @param h int 新高度
     */
    public void resize(int w, int h, String fileName) throws IOException {
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
        File destFile = new File(fileName);
        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
        // 可以正常实现bmp、png、gif转jpg
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(image); // JPEG编码
        out.close();
    }

}
