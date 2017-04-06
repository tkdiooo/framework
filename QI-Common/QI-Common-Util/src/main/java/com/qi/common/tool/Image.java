package com.qi.common.tool;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;


/**
 * Class Image
 *
 * @author 张麒 2016/4/12.
 * @version Description:
 */
public class Image {

    private Random generator = new Random();

    private static Image image = null;

    private Font font = null;

    private int width = 65, height = 30;

    private Image() {
    }

    public static Image getInstance() {
        if (null == image)
            synchronized (Image.class) {
                if (null == image) image = new Image();
            }
        return image;
    }

    public enum TTF {
        Courier("Courier"), Antique("Antique"), Forte("Forte"), Gill("Gill Sans Ultra Bold");

        TTF(String ttf) {
            this.ttf = ttf;
        }

        private String ttf;

    }

    /**
     * 设置字体
     *
     * @param ttf 字体枚举
     */
    public void setFont(TTF ttf) {
        font = new Font(ttf.ttf, Font.PLAIN, 26);
    }

    /**
     * 设置图像大小
     *
     * @param width  Width
     * @param height Height
     */
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * 创建图象
     *
     * @param validCode 验证码
     * @return BufferedImage
     */
    public BufferedImage createImage(StringBuffer validCode) {

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 获取图形上下文
        Graphics2D g = image.createGraphics();

        // 设置随机种子
        Random rand = new Random();

//		// 设定图像背景色(因为是做背景，所以偏淡)
        Color bg = getRandColor(185, 220);

        g.setColor(bg);

        g.fillRect(0, 0, width, height);

        // 随机产生255条干扰线，使图象中的认证码不易被其它程序探测到
        for (int i = 0; i < 10; i++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            int x1 = rand.nextInt(width);
            int y1 = rand.nextInt(height);
            drawThickLine(g, x, y, x1 + 10, y1 + 10, 2, getRandColor(120, 170));
        }

        for (int i = 0; i < validCode.length(); i++) {
            // 设置字体
            g.setFont(getFont());
            // 设定背景色
            g.setColor(getRandColor(20, 100));

            String ch = String.valueOf(validCode.charAt(i));

            int x = 14 * i + rand.nextInt(3);
            int y = 22 + rand.nextInt(2);
            g.drawString(ch, x, y);
        }
        // 扭曲图像
        shear(g, width, height, bg);
        // 图象生效
        g.dispose();
        return image;
    }

    /**
     * 写入图像
     *
     * @param im         图像流
     * @param formatName 图像类型
     * @param imagePath  图像保存地址
     * @throws IOException
     */
    public void writeImage(BufferedImage im, String formatName, String imagePath) throws IOException {
        ImageIO.write(im, formatName, new File(imagePath));
    }

    /**
     * 获取图像流
     *
     * @param im         图像流
     * @param formatName 图像类型
     * @return InputStream
     * @throws IOException
     */
    public InputStream getInputStream(BufferedImage im, String formatName) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageOutputStream imOut = ImageIO.createImageOutputStream(bos);
        ImageIO.write(im, formatName, imOut);
        imOut.close();
        return new ByteArrayInputStream(bos.toByteArray());
    }

    /**
     * 给定范围获得一个随机颜色
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;

        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);

        return new Color(r, g, b);
    }

    /**
     * 获得随机字体
     */
    private Font getFont() {
        if (font == null) font = new Font("Courier", Font.PLAIN, 26);
        return font;
    }

    private void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    private void shearX(Graphics g, int w1, int h1, Color color) {
        int period = generator.nextInt(2);
        int frames = 1;
        int phase = generator.nextInt(2);
        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);

            g.copyArea(0, i, w1, 1, (int) d, 0);
            g.setColor(color);
            g.drawLine((int) d, i, 0, i);
            g.drawLine((int) d + w1, i, w1, i);
        }
    }

    private void shearY(Graphics g, int w1, int h1, Color color) {
        int period = generator.nextInt(5) + 10; // 50;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);

            g.copyArea(i, 0, 1, h1, 0, (int) d);
            g.setColor(color);
            g.drawLine(i, (int) d, i, 0);
            g.drawLine(i, (int) d + h1, i, h1);
        }

    }

    private void drawThickLine(Graphics g, int x1, int y1, int x2, int y2, int thickness, Color c) {
        // The thick line is in fact a filled polygon
        g.setColor(c);
        int dX = x2 - x1;
        int dY = y2 - y1;
        // line length
        double lineLength = Math.sqrt(dX * dX + dY * dY);

        double scale = (double) (thickness) / (2 * lineLength);

        // The x and y increments from an endpoint needed to create a rectangle...
        double ddx = -scale * (double) dY;

        double ddy = scale * (double) dX;

        ddx += (ddx > 0) ? 0.5 : -0.5;

        ddy += (ddy > 0) ? 0.5 : -0.5;

        int dx = (int) ddx;

        int dy = (int) ddy;

        // Now we can compute the corner points...
        int xPoints[] = new int[4];

        int yPoints[] = new int[4];

        xPoints[0] = x1 + dx;

        yPoints[0] = y1 + dy;

        xPoints[1] = x1 - dx;

        yPoints[1] = y1 - dy;

        xPoints[2] = x2 - dx;

        yPoints[2] = y2 - dy;

        xPoints[3] = x2 + dx;

        yPoints[3] = y2 + dy;

        g.fillPolygon(xPoints, yPoints, 4);
    }
}