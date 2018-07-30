

### Shaker

[ ![Download](https://api.bintray.com/packages/lovejjfg/maven/Shaker/images/download.svg) ](https://bintray.com/lovejjfg/maven/Shaker/_latestVersion)

    dependencies {
     debugImplementation 'com.lovejjfg:shake:0.0.4'
     releaseImplementation 'com.lovejjfg:shake-no-op:0.0.4'
    }

Shaker is a debug util that you can through shake to show current Activity and it's Fragment name in a dialog.so that you can fast location the views.
It support some features:

* You can give the specified view, not just show the view's name in the dialog.
* You can design the shake trigger condition.
* You can add the ignore list of Fragments not to show in the shaker dialog.
* You can add the disable list of Activities that enable to shake.


![定制弹窗效果](https://raw.githubusercontent.com/lovejjfg/screenshort/8653ede91bd3f8979e33760f314414035eb8dc29/WechatIMG291.jpeg)
![默认弹窗效果](https://raw.githubusercontent.com/lovejjfg/screenshort/8653ede91bd3f8979e33760f314414035eb8dc29/WechatIMG292.jpeg)







