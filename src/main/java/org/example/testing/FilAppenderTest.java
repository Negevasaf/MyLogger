package org.example.testing;


import org.example.logger.Level;
import org.example.logger.MyLog;
import org.example.logger.appenders.filesAppenders.FileAppender;
import org.example.logger.appenders.filesAppenders.logFiles.LogFile;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Spy;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.FileSystems;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.Validate.matchesPattern;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FilAppenderTest {

    StringWriter stringWriter = new StringWriter(); //I used StringWriter instead of FileWriter to mock logFile behavior
    @Mock
    File file;
    LogFile logFile;
    FileAppender fileAppender;

    @BeforeAll
    void init() {
        logFile = new LogFile(stringWriter, file);
        fileAppender = new FileAppender(logFile);
    }

    @Test
    public void testWriting() throws IOException {
        fileAppender.info("hi");
        Pattern pattern = Pattern.compile("[a-zA-Z]+.*[0-9]{4}-[0-9]{2}-[0-9]{2}.*[0-9]{2}:[0-9]{2}:[0-9]{2}(\\.[0-9]{1,3})?.*[a-zA-Z]+");
        Matcher matcher = pattern.matcher(stringWriter.toString());
        assertTrue(matcher.find());
    }


}