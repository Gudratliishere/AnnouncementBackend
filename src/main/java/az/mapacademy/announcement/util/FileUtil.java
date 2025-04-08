package az.mapacademy.announcement.util;

import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@UtilityClass
public class FileUtil {
    public static final String FILE_DIRECTORY = "files";

    public static void saveFile(String fileName, MultipartFile multipartFile) {
        Path uploadPath = Paths.get(FILE_DIRECTORY);


        try (InputStream inputStream = multipartFile.getInputStream()) {
            if (!Files.exists(uploadPath))
                Files.createDirectory(uploadPath);

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new RuntimeException("Could not save file: " + fileName, ex);
        }
    }

    public static byte[] readFile(String fileName) {
        File file = new File(FILE_DIRECTORY + "\\" + fileName);

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] arr = new byte[(int) file.length()];
            fileInputStream.read(arr);
            fileInputStream.close();

            return arr;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteFile(String fileName) {
        File file = new File(FILE_DIRECTORY + "\\" + fileName);
        file.delete();
    }
}