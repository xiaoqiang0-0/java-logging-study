# SLFJ && Logback
两者同出自为了`Gülcü `，目的就是为了解决`JCL`和`Logback`的不足而诞生，因此使用起来极为简单方便。
## 依赖
```xml
<dependencies>
    <!--  ...  -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
    </dependency>

    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
    </dependency>
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-core</artifactId>
    </dependency>
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-access</artifactId>
    </dependency>
    <!--  ...  -->
</dependencies>
```
## 使用
```java
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackTest {
    Logger logger=  LoggerFactory.getLogger(LogbackTest.class);

    @Test
    public void logTest(){
        logger.debug("debug message");
        logger.info("info message");
        logger.warn("warning message");
        logger.error("error message");
        logger.warn("login message");
    }
}
```
## 配置
`classpath`下申明配置文件`logback.xml`
```xml
<!--每天生成一个文件，归档文件保存30天：-->
<configuration >

    <!--设置自定义pattern属性-->
    <property name="pattern" value="%d{HH:mm:ss.SSS} [%-5level] [%thread] [%logger] %msg%n"/>

    <!--控制台输出日志-->
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <!--设置控制台输出日志的格式-->
        <encoder>
            <pattern>${pattern}</pattern>
        </encoder>
    </appender>

    <!--滚动记录日志文件：-->
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!--当天生成的日志文件名称：-->
        <file>e:/log.out</file>
        <!--根据时间来记录日志文件：-->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--归档日志文件的名称：-->
            <fileNamePattern>testLog-%d{yyyy-MM-dd}.log</fileNamePattern>
            <!--归档文件保存30天-->
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <!--生成的日志信息格式-->
        <encoder>
            <pattern>${pattern}</pattern>
        </encoder>
    </appender>

    <!--根root logger-->
    <root level="INFO">
        <!--设置根logger的日志输出目的地-->
        <appender-ref ref="FILE" />
        <appender-ref ref="CONSOLE" />
    </root>

</configuration>
```
详情参考[官方文档](https://logback.qos.ch/manual/configuration.html)
