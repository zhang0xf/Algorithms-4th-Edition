# Algorithms4

Reference website : [Algorithms, 4th Edition](https://algs4.cs.princeton.edu/home/)

## Hello World in Java (Windows)

reference website : [Hello World in Java (Windows)](https://lift.cs.princeton.edu/java/windows/)

#### hello项目的结构
  1. .idea目录：与intellij的环境配置相关，作者已经配置好，可以不用管。
  2. .lift目录：与插件lift的配置相关，作者已经配置好，可以不用管。

#### hello项目的用法
  1. 展示如何在intellij中创建一个程序
  2. 展示如何在intellij中编译并执行一个程序；
  3. 展示如何在command line中编译并执行一个程序；
  4. 展示如何使用stdlib.jar；
  5. 此工程并不适合用来作为编写《算法4》习题的环境；
  6. stdlib.jar只是algs4.jar的一部分，关于stdlib.jar的细节见：[Standard Libraries](https://introcs.cs.princeton.edu/java/stdlib/)

## Java Algorithms and Clients

reference website：[Java Algorithms and Clients](https://algs4.cs.princeton.edu/code/)

#### 方式1：使用作者配置好的工程目录
  1. 下载percolation.zip，这个工程适合编写练习题。[下载地址](https://lift.cs.princeton.edu/java/windows/)
  2. 使用intellij打开项目。
  3. 可能存在问题：PercolationVisualizer.java和InteractivePercolationVisualizer.java由于缺少依赖项**Percolation.java**而编译不过。解决方法：在github上可找到Percolation.java，为项目新建一个文件即可。Percolation.java的地址是：[Percolation.java的地址](https://github.com/ashwinichauhan/Percolation/blob/master/Percolation.java)。或者直接删除文件。
  4. .png和.txt文件用于测试该算法，可忽略。
  5. 创建工程目录,并标记为源文件!

#### 方式2：新建一个工程，使用[algs4.jar](https://algs4.cs.princeton.edu/code/)搭建环境

## 测试算法的数据

* algs_data.zip的地址：[algs_data.zip](https://algs4.cs.princeton.edu/code/)

## Reference

* 环境搭建: [https://lift.cs.princeton.edu/java/mac/](https://lift.cs.princeton.edu/java/mac/)
* Source: [https://github.com/kevin-wayne/algs4](https://github.com/kevin-wayne/algs4)
