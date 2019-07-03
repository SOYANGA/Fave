# Leetcode52 N Queens II

- **位运算高效解决N皇后问题**

- **DFS**
- **剪枝**



强力高效解法--位运算
通过二进制位将当前行中皇后可以去放的位置，然后一 一进行dfs

*dfs的递归终止条件：*

```
if (row>=n) {  
	count++; 
	return;
}
```
- 利用cols pie na int型整数来分别表示列 撇 捺
- (~(cols | pie | na)) 表示的时当前撇捺列中可以放以及不可以放皇后的位置
- 1表示可以放(空位）   0表示不可以放(对列撇捺的剪枝处理)

 > **将x最高位至第n位(含)清零     x&( (1<<n)-1)**
 > 添加过滤将无关位屏蔽掉 （(~(cols | pie | na))&( (1<<n)-1)

即：bits记录当前行所有的空位（(~(cols | pie | na))&( (1<<n)-1)

- 当bits大于0，则表示还有空位可以放入，则一直尝试将棋盘当前行能放的空位放满

```
while(bits>0){
	p = bits&(-bits);  //1.得到一个空位
	dfs(n, row+1, cols | p , (pie | p)<<1, na | p) >>1);//2.给下一行设置剪枝
	bits = bits &(bits-1);//3.标记所站的空位
}
```

**1.x & -x =>得到最低位的1          即得到一个空位**
**2.row+1：切换至下一行
			 cols | p ：下一行列不能放的位
			(pie | p)<<1：下一行撇不能放的位
			(na | p) >>1：下行捺不能放的位**
**3.x = x & (x-1) => 清零最低为的1    即将当前所占的位标记位以占**

最后获取到的dfs外部变量count的总数



#### [52. N皇后 II](https://leetcode-cn.com/problems/n-queens-ii/)

n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/8-queens.png)

上图为 8 皇后问题的一种解法。

给定一个整数 n，返回 n 皇后不同的解决方案的数量。

示例:

输入: 4
输出: 2
解释: 4 皇后问题存在如下两个不同的解法。
[
 [".Q..",  // 解法 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // 解法 2
  "Q...",
  "...Q",
  ".Q.."]
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/n-queens-ii

## 位运算解解（最优解）

```java
class Solution {
    private int count = 0; //计算的总数

    public int totalNQueens(int n) {
        if (n < 1) {
            return 0;
        }
        dfs(n, 0, 0, 0, 0);
        return count;
    }

    public void dfs(int n, int row, int cols, int pie, int na) {
        if (row >= n) {
            count++;
            return;
        }
        int bits = (~(cols | pie | na)) & ((1 << n) - 1); //得到当前所有空位
        while (bits > 0) {
            int p = bits & (-bits); //获取最低位的1 获取空位
            dfs(n, row + 1, (cols | p), (pie | p) << 1, (na | p) >> 1); //下一行继续进行dfs
            bits &= (bits - 1); //清除最低位的1 标记空位为已占位
        }
    }
}
```

