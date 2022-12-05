package org.example.logger.appenders.filesAppenders.rolling;

import org.example.logger.MyLog;
import org.example.logger.appenders.filesAppenders.logFiles.LogFilesManager;

import java.io.IOException;
import java.nio.file.Path;
import java.time.temporal.ChronoUnit;

public class TimeRollingFileAppender extends RollingFileAppender {
    private long minutesToRoll;


    public TimeRollingFileAppender(Path folderLocation, LogFilesManager logFileManager, long minutesToRoll) {
        super(folderLocation, logFileManager);
        this.minutesToRoll = minutesToRoll;
    }


    public void partitionByNumberOfSeconds(long minutes) {
        this.minutesToRoll = minutes;
    }

    private boolean isRoleNeeded() throws IOException {
        long minutesPassed = ChronoUnit.MINUTES.between(this.getCurrentLogFileDateTime(), java.time.LocalDateTime.now());
        return (minutesPassed > this.minutesToRoll);
    }

    @Override
    protected void doLog(MyLog log) throws IOException {
        if (this.isRoleNeeded())
            this.rollFile();
        super.doLog(log);
    }
}
