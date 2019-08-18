# Linux常见指令总结

## 如何查找特定的文件（find）

语法：

```shell
find path [options] params
```

**作用：在指定目录下查找文件**

eg:

-  **从当前目录下查找名为target3.java的文件**

```shell
find -name "target3.java" // 从当前目录下查找名为target3.java的文件，返回这个文件的路径
```



- **进行全局查找一个文件( / Linux下的根目录)**

```shell
find / -name  "target3.java" //在全局查找名为target3.java的文件，返回这个文件的路径
```



- **在home目录下查找一个以`“target”`打头的文件（~ Linux下的home目录）**

```shell
find ~ -name "target*"
```



- **在home目录下查找一个“target”打头的文件且不区分大小写**

```shell
find ~ -iname "targe*"
```



输入`man  指令名`的形式获得指令名的参数选项（用法）



## 检索文件内容（grep）

语法：

```shell
grep [options] pattren file
```

- 全称： Globle Regular Expression Print

- 作用：**查找文件里符合条件的字符串** 

  显示的结果为文件中包含目标字符串所在的行，其余行是不会显示的



- **查找名为target开头的文件中包含 `moo`的文件** 

```
grep "moo" target*
```



## 管道操作符 （|）

- 可将指令连接起来，**前一个指令的输出作为后一个指令的输入**

  ![1566142159754](C:\Users\32183\AppData\Roaming\Typora\typora-user-images\1566142159754.png)

eg:

从home目录下查找一个文件名前缀为`target`的文件路径

```shell
find ~ -name "target*"
```

也可以用管道是实现

```shell
find ~ | grep "target"
```



find ~表示输出当前目录下所有文件，作为输入输入给grep命令，让grep命令在输入文件中筛选出名字包含target的文件名并且输出来。



### 使用管道注意的要点

- **只处理前一个命令正确的输出，不处理错误的输出。**

- **右边的命令必须能够接收标准输入流，否则传递过程中数据会被抛弃。**
- sed,awk,gerp,cut,head,top,less,more,wc,join,sort,split等接收标准输入流



### grep的使用——日志文件相关的



查找日志文件中包含`'partial\[true\]'`的行

```shell
grep 'partial\[true\]' bac-plat-al-data.info.log
```



将包含`partial\[true\]`行中的引擎名称打印出来 

```shell
grep 'partial\[true\]' bac-plat-al-data.info.log | grep -o 'engine\[[0-9a-z]*\]'
```

将前一个的输出流做为第二个grep 的输入流 且带一个-o参数进行二次筛选



将与tomcat相关的进程信息打印出来

```shell
ps -rf | grep tomcat
```

也会将当前grep指令查找tomcat都打印出来，我们不需要这条信息 使用grep -v “grep”清除含有grep的结果

```shell
ps -rf | grep tomcat | grep -v "grep"
```



## grep总结

- `grep 'partial\[true\]' bac-plat-al-data.info.log`
- `grep -o 'engine\[[0-9a-z]*\]'`  使用 -o选项筛选出符合正则表达式的结果输出
- `grep -v "grep"`过滤掉包含相关字符串的内容



## 对文件内容做统计(awk)

**awk统计指令**

语法：

```shell
awk [options] 'cmd' file
```

- **一次读取一行文本，按输入==分割符==进行切片，切成多个组成部分**
- **将切片直接保存在内建的变量中，\$1,\$2… ($0表示行的全部)** *按列逐行进行切片*
- **支持对单个切片的判断，支持循环判断，默认分割符为空格**

![1566147758436](C:\Users\32183\AppData\Roaming\Typora\typora-user-images\1566147758436.png)

**本文件中分为6列，我们利用awk将其按默认空格分割，且取出其第一列和第四列进行打印**

```shell
awk '{print $1,$4}' netstat.txt
```

![1566147889495](C:\Users\32183\AppData\Roaming\Typora\typora-user-images\1566147889495.png)

**筛选 Proto为tcp 且 Recv-Q为1的数据行**

```shell
awk ' $1== tcp && $2 == 1{print $0}' netstat.txt
```

![1566148145306](C:\Users\32183\AppData\Roaming\Typora\typora-user-images\1566148145306.png)

如果要显示出表头则引入内建变量`NR`即可，从awk开始执行开始后，按照**记录分割符**，读取数据的次数

默认记录分割符是换行符，**则默认的就是读取的数据行数。**表头位于第一行，则NR设置为1即可。

```shell
awk ' ($1==tcp && $2==1) || NR==1 {print $0}' netstat.txt
```

![1566148499562](C:\Users\32183\AppData\Roaming\Typora\typora-user-images\1566148499562.png)



使用自定义分割符筛选数据

使用``“,”``进行筛选 -F是不可缺少的，意思是以什么符号去分割筛选内容

```shell
awk -F "," '{print $2}' test.txt
```

![1566148623262](C:\Users\32183\AppData\Roaming\Typora\typora-user-images\1566148623262.png)



打印以上日志中每个引擎出现的次数

```shell
grep 'partial\[true\]' bac-plat-al-data.info.log | grep -o 'engine\[[0-9a-z]*\]' 
| awk '{enginearr[$1]++}END{for(i in enginearr)print i "\t" enginearr[i]}'
```

![1566149012312](C:\Users\32183\AppData\Roaming\Typora\typora-user-images\1566149012312.png)

分析指令含义：

`awk {enginearr[$1]++}` 将awk按空格分割的行的数据存储在一个数组中，且数组下标是第一列，数组中的内容是第一列出现的次数。

`END{for(i in enginearr)print i "\t" enginearr[i]}`END表示在前面执行完之后，会执行{}中的内容。

即遍历数组，i作为数组的下标，并且打印出数组中每行的下标  + “/t” + 数组内容。即`$1 +"\t" +出现次数 `

结果如上所示。

## awk总结

**awk特别适合处理表格等格式化好的结果，文件。**



- **筛选出文件内容中某些列的数据 筛选件可以是多个空格隔开即可**

```shell
awk '{pring $1,$4}' netstat.txt
```

- **筛选出文件内容中符合某些要求的列的数据**

```shell
awk ' ($1==tcp && $2==1) || NR==1 {print $0}' netstat.txt
```

- **对筛选的内容逐行做统计操作，并且列出对应的统计结果**

```shell
grep 'partial\[true\]' bac-plat-al-data.info.log | grep -o 'engine\[[0-9a-z]*\]' 
| awk '{enginearr[$1]++}END{for(i in enginearr)print i "\t" enginearr[i]}'
```



## 批量替换掉文件里的内容（sed）

sed指令

语法

```shell
sed [options] 'sed command' filename
```

- 全名 stream editor 流编辑器
- 适合用于对文本的行内容进行处理（利用正则表达式）



![1566149874323](C:\Users\32183\AppData\Roaming\Typora\typora-user-images\1566149874323.png)

**替换文件中错误的Str开头的行，替换为String**

```shell
sed -i 's/^Str/String/' replace.java
```

参数解析` '-i s/^Str/String/'`

**-i：选项表示的是目前最后的结果输入到文件中，否则只是将结果输出到终端上**

s: 表示要进行字符串的操作

/^Str：要替换的字符串的开头

/String：替换后的字符串

![1566150237798](C:\Users\32183\AppData\Roaming\Typora\typora-user-images\1566150237798.png)



**替换每一行末尾的`.`变为分号`;`**

```sh
sed -i 's/\.$/\;/'  replace.java
```

解析：

**-i：选项表示的是目前最后的结果输入到文件中，否则只是将结果输出到终端上**

 `.`需要被转义一下为`\.`且 `$`表示以什么结尾

`\;`表示转义



![1566150709745](C:\Users\32183\AppData\Roaming\Typora\typora-user-images\1566150709745.png)



## sed总结

- 替换以什么开头的字符串

```shell
sed -i 's/^Str/String/' replace.java
```



- 替换以什么结尾的字符串

```shell
sed -i 's/\.$/\;/'  replace.java
```



- 全文替换所有的文字出现的字符串

```shell
sed -i 's/Jack/me/g' replace.java
```

**-i：选项表示的是目前最后的结果输入到文件中，否则只是将结果输出到终端上**

**最后一个参数g表示替换全文的，不加这个参数表示则只是替换当前行第一次出现Jack的字符串**

- 删除文本中的空行

```shell
sed -i '/^ *$/d' replace.java
```

![1566150931800](C:\Users\32183\AppData\Roaming\Typora\typora-user-images\1566150931800.png)

`/^ *&`:以空格开头结尾 `d`表示删除

- 删除文中满足条件的行

```shell
sed -i '/Integer/d' replace.java
```

![1566151071477](C:\Users\32183\AppData\Roaming\Typora\typora-user-images\1566151071477.png)



