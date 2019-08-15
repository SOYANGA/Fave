package QueueReconstructionbyHeight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @program: LeetCode
 * @Description: 406. 根据身高重建队列 排序
 * @Author: SOYANGA
 * @Create: 2019-08-19 22:12
 * @Version 1.0
 */
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 || people[0].length == 0) {
            return new int[0][0];
        }
        //身高降序，k升序排列，因为身高高的先插入，身高低的插入不会对身高高的有影响。
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] == b[0] ? a[1] - b[1] : b[0] - a[0];
            }
        });
        //按k作为下标插入 ，身高降序插入
        List<int[]> list = new ArrayList<>();
        for (int[] p : people) {
            list.add(p[1], p);
        }
        return list.toArray(new int[list.size()][]);
    }
}
/*
身高从高到低排序的好处是，对于前面已经排好的队，
1.如果下一个人(h,k)比前面所有人都矮，那么，他插入队列的k处，使其达到k的要求，对其他人没影响，达到要求！
2.如果下一个人跟之前排好队的人中最矮的身高一样，这时候，就体现为什么之前排序时候，先考虑身高，再按照k的升序了，
这时候，新来的人虽然与之前最矮之人身高一样，但是由于他的k比之前最矮的人的k都大，所以，他插入的地方一定在已经排好队的，
和他身高一样的，最矮之人的后面，对这些最矮人们没有影响，当然，对其他比他高的人就更没有影响了。

其只要了解一点：我们一个一个地排队，对于前面已经排好的队，如果我们在k的位置插入一个新人，那么对k之前的人没有任何影响，
对于k之后比新人高的人也没有任何影响，因此，我们每插入一个人的时候，要么保证前面所有人都比新人高，
要么至少保证插入的位置后面的所有人都比新人高
 */