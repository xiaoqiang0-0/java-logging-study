package org.example.jullog4jandjcl;

import org.apache.log4j.Logger;
import org.junit.Test;

public class Log4jDemo {
    Logger log= Logger.getLogger(Log4jDemo.class);
    @Test
    public void logTest(){
        log.trace("Trace Message!");
        log.debug("Debug Message!");
        log.info("Info Message!");
        log.warn("Warn Message!");
        log.error("Error Message!");
        log.fatal("Fatal Message!");
    }
}
