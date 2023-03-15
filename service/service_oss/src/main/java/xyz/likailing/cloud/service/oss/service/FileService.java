package xyz.likailing.cloud.service.oss.service;

import java.io.InputStream;

/**
 * @Author 12042
 * @create 2023/2/1 13:01
 */
public interface FileService {
    /**
     *
     * 
     * @param inputStream   输入流
     * @param module    文件夹名称
     * @param originalFileName  文件原始名字
     * @return java.lang.String 文件在oss上的url
     * @author likailing
     * @create 2023/2/1
     **/
    
    String upload(InputStream inputStream, String module, String originalFileName);
    /**
     * 阿里云oss，文件删除
     * 
 * @param url   文件的url
     * @return void
     * @author likailing
     * @create 2023/3/7
     **/
    
    void removeFile(String url);
}
