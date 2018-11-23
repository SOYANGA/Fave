package InsertSort;






public class InsertSort {
    public static void main(String[] args) {
        int [] num = new int[]{9,8,7,6,5,4,3,2,1,0};
        insertSort(num);
        shellSort(num);
        for(int temp:num){
            System.out.println(temp+",");
        }
    }
    public static void insertSort(int[]num){
        if(num==null){
            return;
        }
        for(int i =0;i<num.length;i++){
            int j = 0;
            int temp = num[i];
            for(j = i;j>0&&num[j-1]>temp;j--){
                num[j]=num[j-1];
            }
            num[j]=temp;
        }
    }
    public static void shellSort(int[]num){
        if(num==null){
            return;
        }
        int gap=0;
        int i = 0;
        int j = 0;
        for(gap=(num.length/2);gap>0;gap/=2){
            for(i = gap;i<num.length;i++){
                for(j =i-gap;j>=0&&num[j]>num[j+gap];j-=gap){
                        Sawp(num,j,j+gap);
                }
            }
        }
    }

}
