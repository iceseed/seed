package com.ice.seed.common.web.validation;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : IceSeed
 * @version : v0.0.1
 * @since : 2018/11/9
 */
public class SlidingValidation {
    // 滑块边长
    private  Integer l;
    // 滑块倍数
    private  Double multiple;
    // 图片x位置
    private  Integer x;
    // 图片y位置
    private  Integer y;
    // 图片路径
    private  String srcImg;
    // 生成图片路径
    private  String destImg;
    // 白底图片路径
    private final String whiteImg = "D:/迅雷下载/mh.jpg";


    // 生成图片前缀
    private static String DEFAULT_CUT_PREVFIX = "cut_";


    public SlidingValidation(String srcImg, String destImg, int x, int y, Integer l){
        this.srcImg = srcImg;
        this.destImg = destImg;
        this.x = x;
        this.y = y;
        this.l = l;
        this.multiple =  (double)l/18;
    }

    public String cutImage() throws Exception{
        return cutImage(new File(srcImg), destImg, new java.awt.Rectangle(x, y, l, l));
    }

    //生成目标文件路径
    public String cutImage(File srcImg, String destImgPath,java.awt.Rectangle rect) throws Exception{
        String base64Img = null;
        File destImg = new File(destImgPath);
        if (destImg.exists()) {
            String p = destImg.getPath();
            try {
                if (!destImg.isDirectory())
                    p = destImg.getParent();
                if (!p.endsWith(File.separator))
                    p = p + File.separator;

                BufferedImage image = getImage(srcImg, rect);

                //OutputStream output = new java.io.FileOutputStream(p + DEFAULT_CUT_PREVFIX+ "_"+ srcImg.getName());
                //ImageIO.write(image, "PNG", output);

                // base64图片
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(image, "PNG", outputStream);
                BASE64Encoder encoder = new BASE64Encoder();
                base64Img = encoder.encode(outputStream.toByteArray());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            throw new Exception("图片不存在！");
        }

        return base64Img;
    }

    public BufferedImage getImage(File srcImg, java.awt.Rectangle rect) {
        //主体正方形
        BufferedImage square = cutImage(srcImg, rect);
        return processImage(square,l);

    }

    //裁剪指定位置图片
    public BufferedImage cutImage(File srcImg, java.awt.Rectangle rect) {
        BufferedImage image = null;
        if (srcImg.exists()) {
            FileInputStream fis = null;
            ImageInputStream iis = null;
            try {
                fis = new FileInputStream(srcImg);
                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG,
                // JPEG, WBMP, GIF, gif]
                String types = Arrays.toString(ImageIO.getReaderFormatNames()).replace("]", ",");

                String suffix = null;
                // 获取图片后缀
                if (srcImg.getName().indexOf(".") > -1) {
                    suffix = srcImg.getName().substring(srcImg.getName().lastIndexOf(".") + 1);
                }// 类型和图片后缀全部小写，然后判断后缀是否合法
                if (suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase() + ",") < 0) {
                    return image;
                }
                // 将FileInputStream 转换为ImageInputStream
                iis = ImageIO.createImageInputStream(fis);
                // 根据图片类型获取该种类型的ImageReader

                ImageReader reader = ImageIO.getImageReadersBySuffix(suffix).next();
                reader.setInput(iis, true);
                ImageReadParam param = reader.getDefaultReadParam();

                param.setSourceRegion(rect);
                image = reader.read(0, param);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fis != null)
                        fis.close();
                    if (iis != null)
                        iis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return image;
    }

    //加工图片
    public BufferedImage processImage(BufferedImage image, int targetSize) {
        BufferedImage outputImage = new BufferedImage(targetSize, targetSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = outputImage.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);

        GeneralPath path = new GeneralPath();
        path.moveTo(0*multiple,0*multiple);
        path.lineTo(0*multiple,5*multiple);
        path.curveTo(5*multiple,3*multiple,5*multiple,11*multiple,0*multiple,9*multiple);
        path.lineTo(0*multiple,14*multiple);
        path.lineTo(5*multiple,14*multiple);
        path.curveTo(3*multiple,19*multiple,11*multiple,19*multiple,9*multiple,14*multiple);

        path.lineTo(14*multiple,14*multiple);
        path.lineTo(14*multiple,9*multiple);
        path.curveTo(19*multiple,11*multiple,19*multiple,3*multiple,14*multiple,5*multiple);
        path.lineTo(14*multiple,0*multiple);
        path.lineTo(9*multiple,0*multiple);
        path.curveTo(11*multiple,5*multiple,3*multiple,5*multiple,5*multiple,0*multiple);

        g2.fill(path);
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return outputImage;
    }


    /**
     * 合并图片
     * @param image1
     * @param image2
     * @param x
     * @param y
     * @return
     */
    public BufferedImage jointImage(BufferedImage image1, BufferedImage image2, int x, int y, Integer l) {

        BufferedImage combined = new BufferedImage(image1.getWidth(), image1.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // paint both images, preserving the alpha channels
        Graphics g = combined.getGraphics();

        g.drawImage(image1, 0, 0, null);
        g.drawImage(image2, x, y, null);


        return combined;

    }



    public String bgImage() throws Exception{
        return jointImage(new File(srcImg), destImg);
    }


    //生成目标文件路径
    public String jointImage(File srcImg, String destImgPath) throws Exception{
        String base64Img = null;
        File destImg = new File(destImgPath);
        if (destImg.exists()) {
            String p = destImg.getPath();
            try {
                if (!destImg.isDirectory())
                    p = destImg.getParent();
                if (!p.endsWith(File.separator))
                    p = p + File.separator;

                BufferedImage image1 = ImageIO.read(srcImg);
                BufferedImage image2 = getImage(new File(whiteImg), new java.awt.Rectangle(0, 0, l, l));

                // 转成图片
                //OutputStream output = new java.io.FileOutputStream(p + DEFAULT_CUT_PREVFIX+ "_"+ srcImg.getName());
                //ImageIO.write(jointImage(image1,image2,x,y,l), "PNG", output);

                // base64图片
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(jointImage(image1,image2,x,y,l), "PNG", outputStream);
                BASE64Encoder encoder = new BASE64Encoder();
                base64Img = encoder.encode(outputStream.toByteArray());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            throw new Exception("图片不存在！");
        }
        return base64Img;
    }


    public static Map<String,String> getComponent(String srcImg, int x, int y, Integer l) throws Exception{
        Map<String,String> imageMap = new HashMap();
        SlidingValidation slidingValidation = new SlidingValidation(srcImg,"D:/迅雷下载/", x, y, l);
        imageMap.put("slider",slidingValidation.cutImage());
        imageMap.put("background",slidingValidation.bgImage());
        return imageMap;
    }


}
