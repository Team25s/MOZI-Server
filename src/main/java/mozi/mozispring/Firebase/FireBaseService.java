package mozi.mozispring.Firebase;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.StorageException;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FireBaseService {
    @Value("${app.firebase-bucket}")
    private String firebaseBucket;

    /**
     *  파이어베이스에 이미지 업로드하기
     */
    public String uploadFiles(MultipartFile file, String nameFile) throws IOException {
        if(nameFile == null  || file == null){
            return "";
        }
        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
        InputStream content = new ByteArrayInputStream(file.getBytes());
        Blob blob = bucket.create(nameFile.toString(), content, file.getContentType());
        return blob.getMediaLink();
    }

    /**
     * 파이어베이스에서 이미지 삭제하기
     */
    public boolean deleteFiles(String filename) throws StorageException {
        if(filename == null){
            return false;
        }
        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
        return  bucket.get(filename).delete();
    }
}