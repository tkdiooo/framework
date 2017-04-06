package com.qi.common.util;

import com.qi.common.constants.StringConstants;
import com.qi.common.tool.Assert;
import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;

/**
 * Class FileUtil
 *
 * @author 张麒 2016/4/7.
 * @version Description:
 */
public class FileUtil extends FileUtils {

    /**
     * 路径转换
     *
     * @param path File Address
     */
    public static String convertPath(String path) {
        Assert.isNotBlank(path, "路径为空");
        return path.replaceAll("\\\\", StringConstants.FORWARD_SLASH).replaceAll("//", StringConstants.FORWARD_SLASH);
    }

    /**
     * 物理创建文件
     *
     * @param path File Address
     * @return Boolean
     */
    public static boolean createFile(String path) {
        Assert.isNotBlank(path, "路径为空");
        String filePath = convertPath(path);
        boolean bool = false;
        if (createFolder(filePath)) {
            File file = new File(filePath);
            try {
                bool = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bool;
    }

    /**
     * 物理创建文件夹
     *
     * @param path Folder Address
     * @return Boolean
     */
    public static boolean createFolder(String path) {
        Assert.isNotBlank(path, "路径为空");
        String filePath = convertPath(path);
        int n = filePath.lastIndexOf(StringConstants.FORWARD_SLASH);
        int m = filePath.lastIndexOf(StringConstants.PERIOD);
        if (m > n) {
            filePath = filePath.substring(0, n);
        }
        File file = new File(filePath);
        boolean bool = true;
        if (!file.exists())
            bool = file.mkdirs();
        return bool;
    }


    /**
     * 把流对象内容写入文件
     *
     * @param file File Address
     * @param is   InputStream
     */
    public static void writeInputStreamToFile(String file, InputStream is) throws IOException {
        if (createFile(file)) {
            BufferedInputStream bis = null;
            FileOutputStream fos = null;
            try {
                byte[] buf = new byte[1024];
                int size;
                // 获取网络输入流
                bis = new BufferedInputStream(is);
                // 建立文件
                fos = new FileOutputStream(file);
                // 保存文件
                while ((size = bis.read(buf)) != -1)
                    fos.write(buf, 0, size);

            } finally {
                close(bis, fos);
            }
        }
    }


    /**
     * xml文档写入
     *
     * @param document xml文档
     * @param filePath 文件路径
     * @return File
     */
    public static File writeDom4jToFile(Document document, String filePath) throws IOException {
        Assert.isNotBlank(filePath, "文件路径为空");
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        FileOutputStream fos = null;
        XMLWriter xmlWriter = null;
        try {
            fos = new FileOutputStream(filePath);
            xmlWriter = new XMLWriter(fos, format);
            xmlWriter.write(document);
            xmlWriter.flush();
        } finally {
            if (null != xmlWriter)
                xmlWriter.close();
            close(fos);
        }
        return new File(filePath);
    }

    /**
     * 读取文件内容
     *
     * @param path File Address
     * @return File Content
     * @throws IOException
     */
    public static String readFileToString(String path) throws IOException {
        Assert.isNotBlank(path, "文件路径为空");
        return readFileToString(new File(path), getFileEncoding(path));
    }

    /**
     * 获取当前Class物理路径
     *
     * @return File Address
     */
    public static String getClassRoot() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource("");
        String path = "";
        try {
            if (null != url) path = URLDecoder.decode(url.getFile(), "utf-8").substring(1);
        } catch (UnsupportedEncodingException e) {
            ThrowableUtil.throwRuntimeException(e);
        }
        return path;
    }

    /**
     * 获取文件编码
     *
     * @param path File Address
     * @return Encoding
     * @throws IOException
     */
    public static String getFileEncoding(String path) throws IOException {
        String code = "GBK";
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(path));
            int p = (bis.read() << 8) + bis.read();
            //其中的 0xefbb、0xfffe、0xfeff、0x5c75这些都是这个文件的前面两个字节的16进制数
            switch (p) {
                case 0xefbb:
                    code = "UTF-8";
                    break;
                case 0xfffe:
                    code = "Unicode";
                    break;
                case 0xfeff:
                    code = "UTF-16BE";
                    break;
                case 0x5c75:
                    code = "ANSI|ASCII";
                    break;
            }
        } finally {
            close(bis);
        }
        return code;
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName File Name
     * @return Suffix
     */
    public static String getFileSuffixName(String fileName) {
        int pos = fileName.lastIndexOf(StringConstants.PERIOD);
        if (pos == -1) return "NO_EXT_NAME";
        return fileName.substring(pos + 1);
    }


    /**
     * 获取文件名
     *
     * @param path File Address
     * @return File Name
     */
    public static String getFileName(String path) {
        String filePath = convertPath(path);
        return filePath.substring(filePath.lastIndexOf(StringConstants.FORWARD_SLASH) + 1);
    }


    /**
     * 获取文件名，不包含后缀名
     *
     * @param fileName File Name
     * @return File Name
     */
    public static String getFileBaseName(String fileName) {
        String filePath = convertPath(fileName);
        String name = filePath.substring(filePath.lastIndexOf(StringConstants.FORWARD_SLASH) + 1);
        int pos = name.lastIndexOf(StringConstants.PERIOD);
        if (pos == -1) return name;
        else return name.substring(0, pos);
    }

    /**
     * 获取文件夹下所有文件集合
     *
     * @param path folder Address
     * @return List<File>
     */
    public static List<File> getFileFromFolders(String path) {
        String folderPath = convertPath(path);
        File folder = new File(folderPath);
        if (!folder.exists()) {
            ThrowableUtil.throwRuntimeException("(目录不存在。)folder [" + folder + "]not exist。");
        }
        File[] files = folder.listFiles(File::isDirectory);
        return Arrays.asList(files);
    }

    /**
     * 获取文件夹下所有相同后缀名的文件集合
     *
     * @param path   folder Address
     * @param suffix 后缀名
     * @return List<File>
     */
    public static List<File> getFileFromFolders(String path, final String suffix) {
        String folderPath = convertPath(path);
        File folder = new File(folderPath);
        if (!folder.exists()) {
            ThrowableUtil.throwRuntimeException("(目录不存在。)folder [" + folder + "]not exist。");
        }
        File[] files = folder.listFiles(pathname -> pathname.getName().toLowerCase().lastIndexOf("." + suffix.toLowerCase()) != -1);
        return Arrays.asList(files);
    }

    /**
     * 文件对象重命名
     *
     * @param file    File
     * @param newName new File Name
     * @return boolean
     */
    public static boolean fileRename(File file, String newName) {
        // 获取文件夹路径
        String path = file.getParent();
        // 获取文件名称
        String name = file.getName();
        // 获取文件后缀名
        String suffix = getFileSuffixName(name);
        // 如果新名称包含后缀
        if (newName.contains(StringConstants.PERIOD))
            newName = newName.substring(0, newName.indexOf(StringConstants.PERIOD));
        boolean bool = false;
        // 如果文件存在，执行改名
        if (file.exists()) {
            bool = file.renameTo(new File(path + StringConstants.FORWARD_SLASH + newName + StringConstants.PERIOD + suffix));
        }
        return bool;
    }

    /**
     * 文件重命名
     *
     * @param path    File Address
     * @param newName new File Name
     * @return boolean
     */
    public static boolean fileRename(String path, String newName) {
        String filePath = convertPath(path);
        return fileRename(new File(filePath), newName);
    }

    /**
     * 文件是否存在
     *
     * @param path File Address
     * @return boolean
     */
    public static boolean isExists(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     * 关闭流对象
     *
     * @param closeable interface Closeable
     * @throws IOException
     */
    public static void close(Closeable... closeable) throws IOException {
        for (Closeable c : closeable) {
            if (null != c) {
                c.close();
            }
        }
    }

    public static void imageResize(String origPath, String newPath, int width, int height) throws IOException {
        File origFile = new File(origPath);
        Assert.isFile(origFile, "原文件不是标准文件");
        // 后缀
        String suffix = getFileSuffixName(origPath);
        // 创建图片路径
        createFolder(newPath);

        BufferedImage bi = ImageIO.read(origFile);

        // 等比例压缩 :以输入的较小值为准计算压缩比例
        double ratio;

        if (height < width) ratio = (double) height / bi.getHeight();
        else ratio = (double) width / bi.getWidth();

        AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
        Image image = op.filter(bi, null);
        // 缩略图文件
        File newFile = new File(newPath);
        ImageIO.write((BufferedImage) image, suffix, newFile);
    }
}
