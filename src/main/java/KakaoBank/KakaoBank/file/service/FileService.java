package KakaoBank.KakaoBank.file.service;

import KakaoBank.KakaoBank.customer.domain.User;
import KakaoBank.KakaoBank.customer.service.UserService;
import KakaoBank.KakaoBank.file.domain.FileUploadYn;
import KakaoBank.KakaoBank.file.repository.FileRepository;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FileService {

    private final FileRepository fileRepository;
    private final UserService userService;

    public FileService(FileRepository fileRepository, UserService userService) {
        this.fileRepository = fileRepository;
        this.userService = userService;
    }

    public void save(List<MultipartFile> multipartFiles, String user_email) throws Exception {
        User user = userService.findByEmail(user_email);
        // 서버에 업로드 파일을 저장할 경로
        File folder = new File("C:\\Users\\User\\Desktop\\fileupload\\" + user_email);
        // 해당 디렉토리가 없을 경우 디렉토리를 생성합니다.
        if (!folder.exists()){
            folder.mkdir(); // 폴더를 생성
        }
        String uploadFolder = "C:\\Users\\User\\Desktop\\fileupload\\" + user_email;
        List<FileUploadYn> lst = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles){
            String uploadFileName = multipartFile.getOriginalFilename();
            // 저장할 파일, 생성자로 경로와 이름을 지정해줌
            File saveFile = new File(uploadFolder, uploadFileName);
            multipartFile.transferTo(saveFile);
            String filePath = uploadFolder + "\\" + uploadFileName;

            FileUploadYn fileUploadYn = FileUploadYn.builder()
                    .file_path(filePath)
                    .user(user)
                    .build();
            lst.add(fileUploadYn);
        }
        try{
            fileRepository.saveAll(lst);
            user.setFileUploadYn("Y");
            userService.save(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
