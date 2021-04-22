# Java 日志体系
> 注意：在添加依赖的时候，要小心不要有循环依赖。
## 日志框架分类
总体分两类
1) 门面型日志框架（也就是只提供标准，不提供实现）
2) 记录型日志框架（具体的日志框架实现）

## 日志体系的发展

### 无标准时代
`jdk1.3`及之前，没有标准的日志实现方式。主要依赖`System.out.println()`，`System.err.println()`或者`e.printStackTrace()`,分别写入`STDOUT`和`STDERR`流中。  
缺陷：即无法定制化，且日志粒度不够细。

### Log4j
由于原始的方案在实际的环境中遇到了各种问题，`Gülcü` 于2001年发布了`Log4j`。  
`Log4j`定义了`Logger`,`Appender`,`Level`等概念，对于后续的日志框架起了非常重要的影响，被后续的各种框架沿用。
缺陷：性能问题。

### `J.U.L`
受`Log4j`启发，在`JDK1.4`中`Sun`公司引入了`java.util.logging`,然而`j.u.l`功能远不及`log4j`完善，开发人员需要自己去编写`Appenders`(`Handlers`),且只有`Console`和`File`两个可用。在`jdk1.5`以后才在性能和可用性上有所提升。

### `JCL`
由于项目中日志打印有了两种选择（或者两种同时存在）,这样一来就又带来了新的不统一的问题。因此`JCL`诞生。
`JCL`作为`Log Facade`提供了一套`Log API`，然后可以通过`Adapter`来使用 `Log4j` 或者 `JUL` 作为具体实现(当然自身也提供了一个简单的日志实现`org.apache.commons.logging.impl.SimpleLog`)。  
不足：`commons-logging`对`Log4j`和`j.u.l`的配置问题兼容的并不好，使用`commons-loggings`还可能会遇到类加载问题，导致`NoClassDefFoundError`的错误出现。

### `SLF4J & Logback`
`SLF4J`与`JCL`一致，都是用来在代码层和`log`之间起到门面作用，对于用户而言只要采用`SLF4J`,即可影藏日志的具体实现。  
`Logback` 是`Log4j`的升级版，主要包含`logback-core`,`logback-classic`和`logback-access`.  

`Logback`对比`Log4j`的优势：
1) 更快的执行速度
1) 更充分的测试
1) logback-classic 非常自然的实现了SLF4J
1) 使用XML配置文件或者Groovy
1) 自动重新载入配置文件
1) 优雅地从I/O错误中恢复
1) 自动清除旧的日志归档文件
1) 自动压缩归档日志文件
1) 谨慎模式
1) Lilith
1) 配置文件中的条件处理
1) 更丰富的过滤  
   
[更多详情](https://logback.qos.ch/reasonsToSwitch.html)

> `SLF4j`出现的原因，主要是Gülcü 认为 JCL 的 API 设计得不好，容易让使用者写出性能有问题的代码。  

`Gülcü `是个追求完美的人，他决定让这些Log之间都能够方便的互相替换，所以做了各种 Adapter 和 Bridge 来连接，甚至 Log4j 和 JUL 都可以桥接到SLF4J，再通过 SLF4J 适配到到 Logback！

### `Log4j2`
`Log4j`的维护人员不想坐看用户慢慢被`SLF4J/Logback`蚕食，因此搞出了`Log4j2`,且与`Log4j1.x`并不兼容，由于设计上很大程度参考了`SLF4J/Logback`因此在性能上有了很大的提升。在设计上分成了`log4j-api`和`log4j-core`。
