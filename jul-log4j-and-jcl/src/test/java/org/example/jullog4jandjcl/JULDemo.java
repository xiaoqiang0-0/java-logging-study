package org.example.jullog4jandjcl;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static java.util.logging.Level.*;

public class JULDemo {
    Logger logger = Logger.getLogger(JULDemo.class.getName());

    public JULDemo() throws IOException {
        LogManager logManager = LogManager.getLogManager();
        InputStream in = JULDemo.class.getResourceAsStream("/logging.properties");
        logManager.readConfiguration(in);
        logManager.addLogger(logger);
    }

    @Test
    public void logTest() {
        logger.log(WARNING, "WARNING Message!");
        logger.log(INFO, "INFO Message!");
        logger.log(CONFIG, "CONFIG Message!");
        logger.log(FINE, "FINE Message!");
        logger.log(FINER, "FINER Message!");
        logger.log(FINEST, "FINEST Message!");
    }
}
