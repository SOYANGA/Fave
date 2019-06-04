package GenerateParentheses;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: LeetCode
 * @Description: 22. 括号生成
 * @Author: SOYANGA
 * @Create: 2019-06-04 23:17
 * @Version 1.0
 */
public class GenerateParentheses {
    class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> resultList = new ArrayList();
            generatOneByOne(n, n, resultList, "");
            return resultList;
        }

        public void generatOneByOne(int left, int right, List<String> resultList, String str) {
            if (left == 0 && right == 0) {
                resultList.add(str);
            }
            if (left > 0) {
                generatOneByOne(left - 1, right, resultList, str + "(");
            }
            if (right > left) {
                generatOneByOne(left, right - 1, resultList, str + ")");
            }
        }
    }
}

/*
DFS：全遍历+剪枝
全遍历中时间复杂度为O(2^n) 为了减少多余的非法遍历过程使用剪枝
剪枝：
1. 剪去 一开始就不合法的情况
2. 左右括号对半（括号用完会超出）
3. （右括号填充时）左括号一定大于右括号的个数
 */
