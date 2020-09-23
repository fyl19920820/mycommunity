package cn.fengylb.mycommunity.mycommunity.controller;

import cn.fengylb.mycommunity.mycommunity.dto.FileDTO;
import cn.fengylb.mycommunity.mycommunity.provider.TXCloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by codedrinker on 2019/6/26.
 */
@Controller
public class FileController {

    @Autowired
    private TXCloudProvider txCloudProvider;

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("editormd-image-file");
        File file = new File("E:/"+multipartFile.getOriginalFilename());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            InputStream inputStream = multipartFile.getInputStream();
            byte[] bytes = new byte[1024];
            int i;
            while ((i = inputStream.read(bytes)) != -1){
                fileOutputStream.write(bytes,0,i);
            }

            txCloudProvider.upload(file,file.getName(),multipartFile.getContentType());
            fileOutputStream.close();
            inputStream.close();
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/images/wechat.png");
        return fileDTO;
    }
}
