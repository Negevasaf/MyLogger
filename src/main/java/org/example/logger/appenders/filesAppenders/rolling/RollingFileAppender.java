package org.example.logger.appenders.filesAppenders.rolling;

import org.example.logger.AbstractLogger;
import org.example.logger.MyLog;
import org.example.logger.appenders.filesAppenders.logFiles.LogFile;
import org.example.logger.appenders.filesAppenders.logFiles.LogFilesManager;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class RollingFileAppender extends AbstractLogger {
    String DEFAULT_BASE_NAME = "app";
    String LOG_TYPE = ".log";

    private final Path folderLocation;
    private String fileBaseName;
    private final LogFilesManager logFileManager;
    private int index;
    private LogFile currentLogFile;

    public RollingFileAppender(Path folderLocation, LogFilesManager logFileManager) {
        this.folderLocation = folderLocation;
        this.logFileManager = logFileManager;
        this.fileBaseName = DEFAULT_BASE_NAME;
        this.index = 0;
    }

    protected void rollFile(String suffix) {
        setCurrentFilePath(suffix);
    }

    protected void rollFile() {
        this.index++;
        setCurrentFilePath(String.valueOf(this.index));
    }

    protected long getCurrentLogFileSizeBytes() throws IOException {
        return this.currentLogFile.getFileSizeBytes();
    }

    protected LocalDateTime getCurrentLogFileDateTime() throws IOException {
        return this.currentLogFile.getFileCreationTime();
    }

    private void setCurrentFilePath(String suffix)  {
        Path currentFilePath = FileSystems.getDefault()
                .getPath(this.folderLocation.toString(), this.fileBaseName + "-" + suffix + LOG_TYPE);
        try {
            this.currentLogFile = logFileManager.getCreateLogFile(currentFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    protected void doLog(MyLog log) throws IOException {
        this.currentLogFile.writeLogToFile(log);
    }
}
