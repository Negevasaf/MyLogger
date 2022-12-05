package org.example.logger;

import java.io.IOException;
import java.time.LocalDateTime;

public abstract class AbstractLogger {


    protected abstract void doLog(MyLog log) throws IOException;

    public void debug(String message) throws IOException {
        Level level = Level.DEBUG;
        MyLog log = this.getLog(message, level);
        doLog(log);
    }

    public void info(String message) throws IOException {
        Level level = Level.INFO;
        MyLog log = this.getLog(message, level);
        doLog(log);
    }

    public void warning(String message) throws IOException {
        Level level = Level.WARNING;
        MyLog log = this.getLog(message, level);
        doLog(log);
    }

    public void error(String message) throws IOException {
        Level level = Level.ERROR;
        MyLog log = this.getLog(message, level);
        doLog(log);
    }

    public void fatal(String message) throws IOException {
        Level level = Level.FATAL;
        MyLog log = this.getLog(message, level);
        doLog(log);
    }

    //Offir told me that whenever I can, I should use functional programming
    protected MyLog getLog(String message, Level level) {
        return this.getLog(message,java.time.LocalDateTime.now(),level);
    }

    private MyLog getLog(String message, LocalDateTime localDateTime, Level level) {
        return new MyLog(message,localDateTime,level);
    }



    public void custom(String message, String levelName) {
    }

}
