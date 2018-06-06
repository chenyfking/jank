package com.jank.common;

import java.io.*;
import java.util.List;

/**
 *
 */
public class FileUtil {
    /**
     * 导出
     * @param file Txt文件(路径+文件名)，Txt文件不存在会自动创建
     * @param dataList  数据
     * @param heads  表头
     * @return
     * @author liuweilong@zhicall.com
     * @create 2016-4-27 上午9:49:49
     */
    public static boolean exportTxt(File file, List<String> dataList, String heads){
        FileOutputStream out=null;
        try {
            out = new FileOutputStream(file);
            return exportTxtByOS(out, dataList, heads);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 导出
     * @param out 输出流
     * @param dataList  数据
     * @param heads  表头
     * @return
     * @author liuweilong@zhicall.com
     * @create 2016-4-27 上午9:49:49
     */
    public static boolean exportTxtByOS(OutputStream out, List<String> dataList, String heads){
        boolean isSucess=false;

        OutputStreamWriter osw=null;
        BufferedWriter bw=null;
        try {
            osw = new OutputStreamWriter(out);
            bw =new BufferedWriter(osw);
            //循环表头
            if(heads!=null&&!heads.equals("")){
                bw.append(heads).append("\r");
            }
            //循环数据
            if(dataList!=null && !dataList.isEmpty()){
                for(String data : dataList){
                    bw.append(data).append("\r");
                }
            }
            isSucess=true;
        } catch (Exception e) {
            e.printStackTrace();
            isSucess=false;
        }finally{
            if(bw!=null){
                try {
                    bw.close();
                    bw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(osw!=null){
                try {
                    osw.close();
                    osw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out!=null){
                try {
                    out.close();
                    out=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return isSucess;
    }

    //读取小说
    public static void main(String[] args) {

    }

}
