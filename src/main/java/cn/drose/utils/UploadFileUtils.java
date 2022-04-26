package cn.drose.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cn.drose.dto.GitHubShaDto;
import cn.drose.vo.GitHubRequestVo;
import com.alibaba.fastjson.JSON;
import com.qiniu.util.Json;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Slf4j
public class UploadFileUtils {
    /**
     * 多张/单张都可以用这个 保存图片
     * @param      List<MultipartFile> 要批量上传的文件
     * @param path 图片保存的路径
     * @return "wrong_file_extension"-错误的后缀, "file_empty"-空文件 或者 保存后的绝对路径
     */
    public static List<String> uploadFiles(List<MultipartFile> files, String path) throws IOException {
        List<String> msgs = new ArrayList<>();
        if (files.size() < 1) {
            msgs.add("file_empty");
            return msgs;
        }
        for (int i = 0; i < files.size(); ++i) {
            MultipartFile file = files.get(i);
            if (!file.isEmpty()) {
                String filename = file.getOriginalFilename();
                String type = filename.indexOf(".") != -1
                        ? filename.substring(filename.lastIndexOf("."), filename.length())
                        : null;
                if (type == null) {
                    msgs.add("file_empty");
                    return msgs;
                }

                if (!(".PNG".equals(type.toUpperCase()) || ".JPG".equals(type.toUpperCase()))) {
                    msgs.add("wrong_file_extension");
                    return msgs;
                }
            }
        }
        for (int i = 0; i < files.size(); ++i) {
            MultipartFile file = files.get(i);
            String filename = file.getOriginalFilename();
            String type = filename.indexOf(".") != -1 ? filename.substring(filename.lastIndexOf("."), filename.length())
                    : null;
            String filepath = path + UUID.randomUUID() + type;
            File filesPath = new File(path);
            if (!filesPath.exists()) {
                filesPath.mkdir();
            }

            BufferedOutputStream out = null;
            type = filepath.indexOf(".") != -1 ? filepath.substring(filepath.lastIndexOf(".") + 1, filepath.length())
                    : null;
            try {
                out = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
                out.write(file.getBytes());
                msgs.add(filepath);
            } catch (Exception e) {
                // 没有上传成功
                e.printStackTrace();
            } finally {
                out.flush();
                out.close();
            }
        }
        return msgs;
    }

    public static GitHubShaDto upLoadFile(MultipartFile file,String sha,String url,String flag) throws IOException {
        GitHubRequestVo requestVo = new GitHubRequestVo();
        String fileName = TaleUtils.getFileKey(file.getOriginalFilename(),flag).replaceFirst("/","");

        requestVo.setSha(sha);
        requestVo.setMessage("commit " + fileName);
        if (StringUtils.isBlank(sha)) {
            requestVo.setSha("");
        }
        String string = Base64.encodeBase64String(file.getBytes());
        requestVo.setContent(string);
        GitHubShaDto shaDto = null;
        String response = null;

        log.info("--上传请求路径->"+url);
        try {
            response = HttpClient.sendPutJson(url, Json.encode(requestVo));
            shaDto = JSON.parseObject(response, GitHubShaDto.class);
        }catch (Exception e){
            log.info("http put 请求失败~");
            e.printStackTrace();
        }

        return shaDto;
    }

}