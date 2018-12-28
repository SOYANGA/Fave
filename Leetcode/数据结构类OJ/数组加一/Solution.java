package addone;

class Solution {
    public int[] plusOne(int[] digits) {
        int num = 0;
        int size = digits.length;
        for(;num<size;num++){      //判断是否是999 99情况
            if(digits[num]!=9){
                break;
            }
        }
        if(num==size){           //判断999 99 情况
            int[] arr = new int[size+1];
            arr[0]=1;
            for(int i = 1 ;i <(size+1) ;i++){
                arr[i]=0;
            }
            return arr;
        }
        ++digits[size-1];       //最后一个数组自增
        if((digits[size-1])!=10){
            return digits;
        }
        for(int i = size-1;i>=0;i--){  //非特殊情况
            if((digits[i])<10)
            {
                break;
            }else{              //考虑进位
                digits[i]%=10;
                digits[i-1]++;
            }
        }
        return digits;
    }
}