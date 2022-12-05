package org.example.logger.appenders.filesAppenders.logFiles;

import lombok.Getter;
import lombok.Setter;
import org.example.logger.MyLog;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;
@Getter @Setter
public class LogFile {
    private Writer myWriter;
    private File myFile;

    public LogFile(Writer writer, File file) {
        this.myWriter = writer;
        this.myFile = file;
    }

//    public LogFile(Path path) throws IOException {
//      this.myWriter = new FileWriter(path.toString(), true);
//      this.myFile= new File(path.toString());
//    }


    public void createLogFile() {
        try {
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeLogToFile(MyLog log) {
        try {
            myWriter.write(log.toString() + "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public long getFileSizeBytes() throws IOException {
        return Files.size(myFile.toPath());
    }

    public LocalDateTime getFileCreationTime() throws IOException {
        BasicFileAttributes attr = Files.readAttributes(myFile.toPath(), BasicFileAttributes.class);
        LocalDateTime convertedFileTime = LocalDateTime.ofInstant(attr.creationTime().toInstant(), ZoneId.systemDefault());
        return convertedFileTime;

    }
}
