# data-import

#### 项目介绍
网上搞到了2000w的.csv数据，想导入到数据库，但是数据量太大，直接用命令导入不太现实。另外存在很多脏数据。命令导入操作太繁琐。

所以，写了一个基于SSM的数据导入。利用springMVC的文件上传功能，然后对文件进行解析，过滤脏数据，批量导入数据库。 平均200W数据导入大概需要6分钟。

我的想法是，使用线程池开启多个线程导入。

- 1.但是多个线程读取一个文件，这样肯定不行。 
- 2.就算多个线程可以读取，那么每个线程获取的数据都一样的，我需要保证插入的数据不重复。 
- 3.暂时的想法是，一个线程对文件进行解析，javaIO读取文件速度很快。然后开启多个线程导入数据库。 
- 4.不过，我没有成功，待需要的时候在研究。

#### 软件架构
软件架构说明


#### 安装教程

1. xxxx
2. xxxx
3. xxxx

#### 使用说明

1. xxxx
2. xxxx
3. xxxx

#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)