package Day_26;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description:  计票统计
 * @Author: SOYANGA
 * @Create: 2019-04-01 23:09
 * @Version 1.0
 */
public class Test2 {

    public Map<Character, Integer> candidatemap = new HashMap<>();
    public int invalidResult = 0;

    /**
     * 设置候选人姓名
     * 输入非法返回0，已经添加过返回0，添加成功返回1
     *
     * @param pCandidateName 候选人姓名
     * @return 是否设置成功
     */
    public int AddCandidate(Character pCandidateName) {
        if (pCandidateName == null || candidatemap.containsKey(pCandidateName)) {
            return 0;
        } else {
            candidatemap.put(pCandidateName, 0);
            return 1;
        }
    }

    /**
     * 获取候选人票数，如果传入的是空指针，返回无效票数，同时说明本次投票活动结束，释放资源
     *
     * @param pCandidateName
     * @return 获得票个数
     */
    public int GetVoteResult(Character pCandidateName) {
        if (pCandidateName == null) {
            return invalidResult;
        } else {
            if (candidatemap.containsKey(pCandidateName)) {
                candidatemap.put(pCandidateName, candidatemap.get(pCandidateName) + 1);
                return candidatemap.get(pCandidateName);
            } else {
                invalidResult++;
            }
        }
        return -1;
    }

    /**
     * 清空投票结果释放所有资源
     */
    public void Clear() {
        candidatemap = new HashMap<>();
        invalidResult = 0;
    }

    public static void main(String[] args) {
        Test2 t = new Test2();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            int canum = scanner.nextInt();
            canum = canum * 2 - 1;
            int i = 0;
            while ((canum--) > 0) {
                String s = scanner.next();
                if (!" ".equals(s)) {
                    t.AddCandidate(s.charAt(0));
                }
            }
            int toupiao = scanner.nextInt();
            toupiao = toupiao * 2 - 1;
            int j = 0;
            while ((toupiao--) > 0) {
                String s2 = scanner.next();
                if (!" ".equals(s2)) {
                    int result = t.GetVoteResult(s2.charAt(0));
                    if (result != -1) {
                        System.out.println(s2 + " : " + result);
                    }

                }
            }

        }
    }
}
