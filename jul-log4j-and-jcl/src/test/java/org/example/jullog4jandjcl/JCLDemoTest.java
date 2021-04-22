package org.example.jullog4jandjcl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class JCLDemoTest {
    private Log log = LogFactory.getLog(JCLDemoTest.class);

    @Test
    public void logTest() {
        log.debug("Debug message!");
        log.info("Info message!");
        log.warn("Warn message!");
        log.error("Error message!");
        log.fatal("Fatal message!");
    }
}
