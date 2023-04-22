package KakaoBank.KakaoBank.file.repository;

import KakaoBank.KakaoBank.file.domain.FileUploadYn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public interface FileRepository extends JpaRepository<FileUploadYn, Long> {



}
