package AssignCookies;

import java.util.Arrays;

/**
 * @program: LeetCode
 * @Description: 455. 分发饼干
 * @Author: SOYANGA
 * @Create: 2019-08-19 22:00
 * @Version 1.0
 */
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int gi = 0, si = 0;
        while (gi < g.length && si < s.length) {
            if (g[gi] <= s[si]) {
                gi++;
            }
            si++;
        }
        return gi;
    }
}
