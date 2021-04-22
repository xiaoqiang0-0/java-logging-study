# java日志的实际使用

## 日志框架的选择
1) Log4j 2与Log4j 1发生了很大的变化，Log4j 2不兼容Log4j 1。
2) Logback必须配合Slf4j使用。由于Logback和Slf4j是同一个作者，其兼容性不言而喻。
3) 比较常用的组合使用方式是Slf4j与Logback组合使用，Commons Logging与Log4j组合使用。

综合而言新项目中肯定优先选择SLF4J&&Logback。从设计到实现，就是为了解决原先的原有问题。且兼容性上都是同一作者开发，且在同一时期开发，兼容性上自然而然不存在问题。性能上也远超log4j和commons-logging的组合。
## 实践
1) 面向接口编程，不要面向实现编程
2) 只添加一个日志实现
3) 具体的日志依赖应该设置为 optional，并使用 runtime scope
4) 如果有必要, 排除依赖的第三方库中的日志依赖
