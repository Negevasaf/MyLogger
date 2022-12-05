package org.example.logger;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

@Getter
@Setter
public class MyLog {
    private String message;
    private LocalDateTime date;
    private Level level;

    public MyLog(String message, LocalDateTime date, Level level) {
        this.message = message;
        this.date = date;
        this.level = level;
    }


    @Override
    public String toString() {
        Function<String, String> getMessage = m -> level.name() + " " + this.date + " " + m;
        return getMessage.apply(message);
    }
}
