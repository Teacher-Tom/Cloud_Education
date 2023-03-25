package xyz.likailing.cloud.service.service;

import xyz.likailing.cloud.service.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OssService {
    File upload(MultipartFile file, String catalogue);

    String delete(String id);

    String uploadfile(MultipartFile file);

    String deleteVideo(String id);

    String uploadFileAvatar(MultipartFile file);

    List<File> uploadPptFile(MultipartFile file, String catalogue);
}
