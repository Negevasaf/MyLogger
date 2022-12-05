package org.example.logger.appenders.filesAppenders.logFiles;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;

public class LogFilesManager {
    private Path path;
    private Writer writer;

    public LogFilesManager(Path path) {
        this.path = path;
    }

    public LogFilesManager() {
    }

    public LogFile getCreateLogFile(@NotNull Path path) throws IOException {
        LogFile logFile = new LogFile(new FileWriter(path.toString(), true), new File(path.toString()));
        logFile.createLogFile();
        return logFile;
    }

    public LogFile getCreateLogFile(Writer writer, File file) throws IOException {
        LogFile logFile = new LogFile(writer, file);
        logFile.createLogFile();
        return logFile;
    }

}
