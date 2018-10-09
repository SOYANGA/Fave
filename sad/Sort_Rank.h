#define _CRT_SECURE_NO_WARNINGS 1
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <time.h>
void TestInserSort();//插曲希尔排序测试函数
//插入排序
void InsertSort(int *a, int n);//直接插入排序
void ShellSort();//希尔排序
//希尔排顺在直接插入排序的基础上优化，比直接插入排顺序效率高好多。