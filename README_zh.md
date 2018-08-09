

### Shaker

[ ![Download](https://api.bintray.com/packages/lovejjfg/maven/Shaker/images/download.svg) ](https://bintray.com/lovejjfg/maven/Shaker/_latestVersion)

    dependencies {
     debugImplementation 'com.lovejjfg:shake:0.0.4'
     releaseImplementation 'com.lovejjfg:shake-no-op:0.0.4'
    }

`Shaker` 是一个调试工具，通过摇一摇的方式，可以直接展示出当前页面的信息（包含 : `Activity` 和 附属 `Fragment` 名称，支持嵌套关系展示），让你方便快捷定位指定页面。具体功能如下：
* 支持弹窗 UI 样式自定义
* 支持摇一摇的规则指定，向上向下触发随你意
* 支持 Fragment 过滤配置，不显示指定的Fragment名称
* 支持 Activity 过滤配置，指定Activity不支持摇一摇
* 支持自定义 FragmentsHandler 控制相关展示逻辑

![定制弹窗效果](https://raw.githubusercontent.com/lovejjfg/screenshort/8653ede91bd3f8979e33760f314414035eb8dc29/WechatIMG291.jpeg)
![默认弹窗效果](https://raw.githubusercontent.com/lovejjfg/screenshort/8653ede91bd3f8979e33760f314414035eb8dc29/WechatIMG292.jpeg)







