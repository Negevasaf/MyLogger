package org.example.logger.appenders.filesAppenders.rolling;

import org.example.logger.MyLog;
import org.example.logger.appenders.filesAppenders.logFiles.LogFilesManager;

import java.io.IOException;
import java.nio.file.Path;

public class LevelRollingAppender extends RollingFileAppender {

    public LevelRollingAppender(Path folderLocation, LogFilesManager logFileManager) {
        super(folderLocation, logFileManager);
        this.rollFile();
    }



    @Override
    protected void doLog(MyLog log) throws IOException {
        this.rollFile(log.getLevel().name());
        super.doLog(log);
    }
}
