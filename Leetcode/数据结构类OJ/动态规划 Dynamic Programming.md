# 动态规划 Dynamic Programming

1. **递推（递归+记忆化）**
2. **状态定义: opt[n], dp[n] ,fib[n] 定义成数组**
3. **状态转移方程： opt[n] = best_of(opt[n-1], opt[n-2])**
4. **最优子结构**



# DP vs 回溯 vs 贪心

- **回溯（递归） —— 存在重新计算**  假如不存在最优子结构的化，回溯（递归）就是最佳解法
- **贪心 —— 永远局部最优**
- **DP —— 记录局部最优子结构/多种记录值**



## 爬楼梯

### 递归+记忆化

```java
class Solution {
    public int climbStairs(int n) {
        int memo[] = new int[n+1];
        return climb_Stairs(0, n, memo);
    }
    
     public int climb_Stairs(int i, int n, int memo[]) {
        if(i > n) {
            return 0;
        }
        if(i == n) {
            return 1;
        }
        if(memo[i] > 0){
             return memo[i];
        }
        memo[i] = climb_Stairs(i + 1, n, memo) + climb_Stairs(i + 2, n, memo);
        return memo[i];
    }
}
```

其中 memo为记忆数组 i为走过的台阶数  n为要走的台阶数

时间复杂度时O(n) 

空间复杂度O(n)



### 动态规划

反向递推 

不难发现，这个问题可以被分解为一些包含**最优子结构的子问题**，即它的最优解可以从其子问题的最优解来有效地构建，我们可以使用动态规划来解决这一问题。

第 i 阶可以由以下两种方法得到：

在第（i−1) 阶后向上爬1阶。

在第 (i−2) 阶后向上爬 2 阶。

所以到达第 i 阶的方法总数就是到第 (i−1) 阶和第 (i−2) 阶的方法数之和。

令 dp[i] 表示能到达第 i 阶的方法总数：

dp[i]=dp[i-1]+dp[i-2]

```java
class Solution {
    public int climbStairs(int n) {
        if(n == 1){
            return 1;
        }
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++) {
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
}
```

时间复杂度时O(n) 

空间复杂度O(n)