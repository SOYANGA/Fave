
public class InsertSort {
    public static void main(String[] args) {
        int[] arrys = new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
        int size = 10;
        InsertSort(arrys, size);
        Printarrys(arrys);
    }


//直接插排
    public static void InsertSort(int[] arr, int sz) { 
        assert (arr != null);
        int i = 0;
        int j = 0;
        int temp = 0;    //保存基准值
        for (i = 0; i < sz; i++) {  //区间由小到大递增
            temp = arr[i];    //储存区间内的基准值  i确定了0~i区间内要排序的数
            j = i - 1;        //前一个区间（已经排好序）的最后一位
            while (j >= 0 && arr[j] > temp) { //排列新的区间（i+1）多了一位的区间 只要比区间最大值小就要调整位置
                arr[j + 1] = arr[j];//通过相互赋值达到移动元素的目的
                j--; //移动一次j--向后一位
            }
            arr[j + 1] = temp; //遇到比基准值小的，将基准值放到他后面
        }
    }

    public static void Printarrys(int[] arrys) {
        for (int temp : arrys) {
            System.out.print(temp + ",");
        }
        System.out.println();
    }
}
/**
*稳定排序
最坏时间复杂度:O(N^2)
平均时间复杂度：O(N^2)
最好时间复杂度：O(N)

//空间复杂度:O(1)
//使用场景：数据量小且需要稳定性的排序 
  */