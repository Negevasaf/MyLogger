package org.example;

import org.example.logger.Level;
import org.example.logger.LoggerManager;
import org.example.logger.MyLog;
import org.example.logger.appenders.filesAppenders.FileAppender;
import org.example.logger.appenders.filesAppenders.logFiles.LogFile;
import org.example.logger.appenders.filesAppenders.rolling.LevelRollingAppender;
import org.example.logger.appenders.filesAppenders.rolling.SizeRollingAppender;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        LoggerManager loggerManager = new LoggerManager("C:\\Users\\IMOE001\\Documents\\Java Projects\\Log Appender\\untitled\\src\\main\\java\\org\\example\\logger\\config.json");
        FileAppender fileAppender = (FileAppender) loggerManager.getLogger("FileAppender");
        fileAppender.debug("manager");
        SizeRollingAppender sizeRollingAppender = (SizeRollingAppender) loggerManager.getLogger("SizeRollingAppender");
        sizeRollingAppender.info("hiii");
        LevelRollingAppender levelRollingAppender = (LevelRollingAppender) loggerManager.getLogger("LevelRollingAppender");
        levelRollingAppender.info("asdasd");


    }
}