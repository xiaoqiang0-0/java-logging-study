# JCL JUL/Log4j

## JUL
JUL中的日志级别  
1) SEVERE (highest value)
1) WARNING
1) INFO
1) CONFIG
1) FINE
1) FINER
1) FINEST (lowest value)

## Log4j
引入依赖
```xml
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
        </dependency>
```
代码中直接使用`log4j`日志类
```java
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
```
`classpath`下申明`log4j.properties`或`log4j.xml`配置文件,`log4j`即可正常工作。

日志级别：  
Log4j建议只使用4个级别，从高到低分别为 ERROR > WARN > INFO > DEBUG。  
配置文件详细介绍包括优化等相关可参考 [博客园资源](https://www.cnblogs.com/caoweixiong/p/11273091.html) 。

问题所在： 
1) 锁，`org.apache.log4j.Category.callAppenders`中采用了同步锁
2) 高并发下频繁的IO操作  

`log4j`在写入文件的时候支持了缓存，然而缓存锁带来的利弊都需要考虑，如果只是开启缓存，并且配置一个较大的缓存区，如果jvm或者系统奔溃，缓存区数据无法落入磁盘，则意味着数据的丢失。因此，如果开启缓存则需要保证这种极端情况下的数据安全问题。`jvm`的异常退出可以通过`java.lang.Runtime.addShutdownHook`去增加一个钩子函数，在退出前执行数据的写入本地磁盘操作。
## JCL 
引入依赖
```xml
        <dependency>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
        </dependency>
```
代码中直接使用`commons-logging`的`api`去处理日志
```java
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
```
`classpath`下定义配置文件：`commons-logging.properties`。  
通过修改`org.apache.commons.logging.Log`为对应的日志实现的类名（可查看`org.apache.commons.logging.impl`下所包含的`log`实现类）即可修改具体的日志实现。  
更多配置参考 [官方文档](http://commons.apache.org/proper/commons-logging/guide.html#Configuration)  

日志级别：`trace` < `debug` < `info` < `warn` < `error`


