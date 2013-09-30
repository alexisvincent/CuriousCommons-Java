package io.files;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author alexisvincent
 */
public class Secretary {

    public static Path getWorkingDirectory() {
        return Paths.get("").toAbsolutePath();
    }

    public static ArrayList<Path> getDirectoryFiles(Path path) {
        ArrayList<Path> files = new ArrayList<>();
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(path)) {
            for (Path file : dirStream) {
                files.add(file);
            }
        } catch (IOException | DirectoryIteratorException ex) {
            ex.printStackTrace();
        }
        return files;
    }

    public static void touch(Path path) {
        if (!path.toFile().exists()) {
            try {
                path.toFile().createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Secretary.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void delete(Path path) {
        if (path.toFile().exists()) {
            path.toFile().delete();
        }
    }
    
    public static byte[] fileToByteArray(Path path) throws IOException {
        if (path.toFile().exists()) {
            return FileUtils.readFileToByteArray(path.toFile());
        }
        return null;
    }
    
    public static void byteArrayToFile(Path path, byte[] byteArray) throws IOException {
        if (path.toFile().exists()) {
            FileUtils.writeByteArrayToFile(path.toFile(), byteArray);
        }
    }
}
