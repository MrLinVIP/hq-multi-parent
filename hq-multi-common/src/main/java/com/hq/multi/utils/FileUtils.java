package com.hq.multi.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author: linliangkun
 * @date: 2019/11/26 0026
 * @description:文件处理工具类
 */
@Slf4j
public class FileUtils {

    /**
     * 获得指定文件的byte数组
     * File 转byte[]
     * @param file
     * @return
     */
    public static byte[] getBytes(File file){
        byte[] buffer = null;
        FileInputStream fis=null;
        ByteArrayOutputStream bos=null;
        try {
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream(1000);
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
        }finally {
            /**
             * 资源回收
             */
            if(null!=fis){
                try {
                    fis.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(null!=bos){
                try {
                    bos.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return buffer;
    }

    /**
     * 多个文件压缩成一个zip文件
     * @param srcFileList
     * @param zipFile
     */
    public static void zipFiles(List<File> srcFileList, File zipFile){
        if(null!=srcFileList && srcFileList.size()>0){
            File[] srcFiles = new File[srcFileList.size()];
            srcFileList.toArray(srcFiles);
            zipFiles(srcFiles,zipFile);
        }
    }
    /**
     * 多个文件压缩成一个zip文件
     * @param srcFiles
     * @param zipFile
     */
    public static void zipFiles(File[] srcFiles, File zipFile) {
        /**
         * 判断压缩后的文件存在不，不存在则创建
         */
        if (!zipFile.exists()) {
            try {
                zipFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /**
         * 创建 FileOutputStream 对象
         */
        FileOutputStream fileOutputStream = null;
        /**
         * 创建 ZipOutputStream
         */
        ZipOutputStream zipOutputStream = null;
        /**
         * 创建 FileInputStream 对象
         */
        FileInputStream fileInputStream = null;
        try {
            /**
             * 实例化 FileOutputStream 对象
             */
            fileOutputStream = new FileOutputStream(zipFile);
            /**
             * 实例化 ZipOutputStream 对象
             */
            zipOutputStream = new ZipOutputStream(fileOutputStream);
            /**
             * 创建 ZipEntry 对象
             */
            ZipEntry zipEntry = null;
            /**
             * 遍历源文件数组
             */
            for (int i = 0; i < srcFiles.length; i++) {
                /**
                 * 将源文件数组中的当前文件读入 FileInputStream 流中
                 */
                fileInputStream = new FileInputStream(srcFiles[i]);
                /**
                 * 实例化 ZipEntry 对象，源文件数组中的当前文件
                 */
                zipEntry = new ZipEntry(srcFiles[i].getName());
                zipOutputStream.putNextEntry(zipEntry);
                /**
                 * 该变量记录每次真正读的字节个数
                 */
                int len;
                /**
                 * 定义每次读取的字节数组
                 */
                byte[] buffer = new byte[1024];
                while ((len = fileInputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, len);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            /**
             * 资源回收
             */
            try {
                if(null!=zipOutputStream){
                    zipOutputStream.closeEntry();
                    zipOutputStream.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            try {
                if(null!=fileInputStream){
                    fileInputStream.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            try {
                if(null!=fileOutputStream){
                    fileOutputStream.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    /**
     * 根据byte数组，生成文件
     * @param bfile       byte 数组
     * @param fileName    文件名（不带文件后缀）
     * @param suffix      文件后缀（".xls"）
     * @return
     */
    public static File byteToFile(byte[] bfile,String  fileName,String suffix) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file =null;
        try {
            file = File.createTempFile(fileName, suffix);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }
}
