package org.example.logger.appenders.filesAppenders;

import org.example.logger.AbstractLogger;
import org.example.logger.MyLog;
import org.example.logger.appenders.filesAppenders.logFiles.LogFile;

public class FileAppender extends AbstractLogger {
    private LogFile logFile;


    public FileAppender(LogFile logFile) {
        this.logFile = logFile;
    }

    @Override
    protected void doLog(MyLog log) {
        this.logFile.writeLogToFile(log);
    }


}

