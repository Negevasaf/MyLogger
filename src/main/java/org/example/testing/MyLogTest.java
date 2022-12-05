package org.example.testing;


import org.example.logger.Level;
import org.example.logger.MyLog;
import org.example.logger.appenders.filesAppenders.logFiles.LogFile;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;

import java.io.File;
import java.io.StringWriter;
import java.nio.file.FileSystems;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyLogTest {
    MyLog myLog;

    @BeforeAll
    void init() {
        myLog = new MyLog("hi", java.time.LocalDateTime.of(2022, 12, 4, 20, 20, 20, 200000000), Level.INFO);

    }

    @Test
    public void testWriting() {
        assertEquals(myLog.toString(), "INFO 2022-12-04T20:20:20.200 hi");
    }
}
