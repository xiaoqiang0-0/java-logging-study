# Log4j2
`Log4j2`的诞生是`Log4j`的维护团队为了挽回用户流失的现象，参考`SLF4J && Logback`所实现的全新版本。  
因此使用也是如丝般畅滑
## 依赖
```xml
<dependencies>
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-api</artifactId>
    </dependency>
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
    </dependency>
</dependencies>
```
## 使用
```java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class Log4j2Test {
    private static final Logger logger = LogManager.getLogger(Log4j2Test.class);
    @Test
    public void logTest(){
        logger.trace("Trace Message!");
        logger.debug("Debug Message!");
        logger.info("Info Message!");
        logger.warn("Warn Message!");
        logger.error("Error Message!");
        logger.fatal("Fatal Message!");
    }
}
```
## 配置文件
log4j2为用户提供了默认的配置：
Level=ERROR
Pattern=%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n
默认添加`ConsoleAppender`

详细的配置可查看[官方文档](https://logging.apache.org/log4j/2.x/manual/configuration.html)
