#define _CRT_SECURE_NO_WARNINGS 1
#include "Sort_Rank.h"
void Swap(int*a, int*b)//交换函数
{
	assert(a);
	int tmp = *a;
	*a = *b;
	*b = tmp;
}
void Print(int*a, int n)
{
	assert(a);
	for (int i = 0; i < n; i++)
	{
		printf("%d ", a[i]);
	}
	printf("\n");
}
void InsertSort(int *a, int n)//直接插入排序
{
	int i = 0;
	int j = 0;
	assert(a);
	int temp = 0;
	for (i = 1; i < n; i++)
	{
		temp = a[i];//将要比较的值先绶存起来留出一个空位，方便移动
		j = i - 1;
		while (j >= 0 && a[j]>temp)///比较直到出现比temp大的值，或向前找到头
		{
			a[j + 1] = a[j];//将前面的值往后移
			j--;
		}
		a[j + 1] = temp;//插在a[j]的后面
	}
}
void ShellSort(int*a, int n)
{
	assert(a);
	int gap, i, j;
	//gap的值逐渐递减
	for (gap = n / 2; gap > 0; gap /= 2)
	{
		for (i = gap; i < n; i++)//
		{
			for (j = i - gap; j >= 0 && a[j]>a[j + gap]; j -= gap)
			{
				//temp = a[j];
				//a[j] = a[j + gap];           //利用temp交换两个值
				//a[j + gap] = temp;
				Swap(&a[j], &a[j + gap]);
			}
		}
	}

}
void TestInserSort()
{
	srand((unsigned int)time(0));
	int a1[10000];
	int a2[10000];
	clock_t start1, end1, start2, end2;
	int n = sizeof(a1) / sizeof(int);
	for (int i = n - 1; i >= 0; i--)
	{
		a1[i] = rand();
		a2[i] = a1[i];
	}
	start1 = clock();
	InsertSort(a1, n);
	end1 = clock();
	double duration1 = (double)(end1 - start1);
	printf("%2lf\n", duration1);
	//Print(a1, n);

	start2 = clock();

	ShellSort(a2, n);
	end2 = clock();
	double duration2 = (double)(end2 - start2);
	printf("%2lf\n", duration2);
	//Print(a2, n);
}
