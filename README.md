# GuizhanLib 插件

将 GuizhanLib 库整合到插件中，并提供自动更新站点选择功能。

## 下载

从构建站下载: [点击前往](https://builds.guizhanss.cn/ybw0014/GuizhanLibPlugin/master)

![构建状态](https://builds.guizhanss.cn/f/ybw0014/GuizhanLibPlugin/master/badge.svg)

## 如何使用

### 对于服主

将下载好的jar放入`plugins`目录，然后重启服务器即可。

### 对于开发者

将GuizhanLibPlugin添加为依赖项:

[![Maven Central](https://img.shields.io/maven-central/v/net.guizhanss/GuizhanLibPlugin.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22net.guizhanss%22%20AND%20a:%22GuizhanLibPlugin%22)

```xml
    <dependency>
        <groupId>net.guizhanss</groupId>
        <artifactId>GuizhanLibPlugin</artifactId>
        <version>将此处替换为版本号</version>
        <scope>provided</scope>
    </dependency>
```

并在`plugin.yml`中添加`GuizhanLibPlugin`为前置：

```yaml
depend:
  - GuizhanLibPlugin
```
