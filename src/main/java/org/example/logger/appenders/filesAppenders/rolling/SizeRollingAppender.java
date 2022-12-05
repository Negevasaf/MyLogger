package org.example.logger.appenders.filesAppenders.rolling;

import lombok.Getter;
import lombok.Setter;
import org.example.logger.MyLog;
import org.example.logger.appenders.filesAppenders.logFiles.LogFilesManager;

import java.io.IOException;
import java.nio.file.Path;

@Setter
@Getter
public class SizeRollingAppender extends RollingFileAppender {
    long maxFileSizeBytes;

    public SizeRollingAppender(Path folderLocation, LogFilesManager logFileManager, long maxFileSizeBytes) {
        super(folderLocation, logFileManager);
        this.maxFileSizeBytes = maxFileSizeBytes;
        this.rollFile();
    }


    private boolean isRollNeeded() throws IOException {
        return (this.getCurrentLogFileSizeBytes() > this.maxFileSizeBytes);
    }

    @Override
    protected void doLog(MyLog log) throws IOException {
        if (this.isRollNeeded())
            this.rollFile();
        super.doLog(log);
    }
}
