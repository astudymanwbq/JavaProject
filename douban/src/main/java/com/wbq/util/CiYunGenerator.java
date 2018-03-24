package com.wbq.util;

import java.io.*;

/**
 * @Author wubiqin
 * @Date 2018-2-6 23:11
 * @Description 写入评论到txt文件中
 */
public class CiYunGenerator {
    private static String filepath = "D:\\wubiqin\\project\\ciyun\\movie_now\\";
    private static String fileNameTemp;

    /**
     * 创建txt文件
     *
     * @param name
     * @return
     * @throws IOException
     */
    //TODO log
    public static void createTxtFile(String name, String data) throws IOException {
        fileNameTemp = filepath + name + ".txt";
        File file = new File(fileNameTemp);
        writeDataToTxtFile(name, data, file);
    }

    /**
     * 内容写入txt文件中
     *
     * @param name
     * @param data
     * @param file
     * @throws IOException
     */
    public static void writeDataToTxtFile(String name, String data, File file) throws IOException {
        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(data);
        StringBuffer path = new StringBuffer();
        path.append(name);
        try {
            outputStream = new FileOutputStream(file);
            printWriter = new PrintWriter(outputStream);
            printWriter.write(stringBuffer.toString().toCharArray());
            printWriter.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
