package cn.fengylb.mycommunity.mycommunity.provider;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;

/**
 * @Version:
 * @Description:
 * @Author:
 * @Modified:
 * @Date: 2020/9/23 10:21
 */
@Service
public class TXCloudProvider {
    @Value("${txcloud.upload.secretid}")
    private String secretId;
    @Value("${txcloud.upload.secretkey}")
    private String secretKey;
    @Value("${txcloud.upload.bucket}")
    private String bucket;
    @Value("${txcloud.upload.region}")
    private String region;
    public void upload(File file, String name, String contentType){
        // 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(this.region);
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);

        // 指定要上传的文件
        File localFile = file;
        // 指定要上传到的存储桶
        String bucketName = bucket;
        // 指定要上传到 COS 上对象键
        String key = name;
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
    }
}