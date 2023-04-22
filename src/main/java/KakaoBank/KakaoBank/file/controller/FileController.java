package KakaoBank.KakaoBank.file.controller;

import KakaoBank.KakaoBank.customer.domain.User;
import KakaoBank.KakaoBank.customer.service.UserService;
import KakaoBank.KakaoBank.file.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class FileController {

    private final FileService fileService;


    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
    }

    @PostMapping("/fileUpload")
    @ResponseBody
    public String create(@RequestParam(value="multipartFiles")List<MultipartFile> multipartFiles,
                         @RequestParam(value="user_email")String user_email) throws Exception {
        fileService.save(multipartFiles, user_email);
        return "ok";
    }

}
