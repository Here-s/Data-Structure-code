#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>


//消失的数字  代码实现  要在时间复杂度O(n)完成  排序的话 就是O(n^2)了

//举例 [9,3,5,2,0,6,1,4,7]就是nums   缺少8   numssize等于9
int missingnumber(int* nums, int numssize)
{
	int x = 0;//先将缺的值x赋值为0
	for (size_t i = 0; i < numssize; ++i)
	{
		x^= nums[i];//先让值都与x异或一遍  就相当于x与给的数组的值都异或了 唯独没有跟x异或
		//所以再与另外一个包含x的数组异或一遍就行了  x与这些值[9,3,5,2,0,6,1,4,7]异或
	}
	for (size_t i = 0; i <= numssize; ++i)//这里是x与0-9的所有值再异或一次
	{
		x ^= i;//这里是<=numssize 因为这是有多少个元素 但是数组是按照下标来算的 所以就再+1
		//+1就是多了x 因为假设原来是0-10 少一个 所以numssize是10 原来按照下标是10个元素
		//现在是11个元素 包括了原来的所有数字 并且多了x 所以这样结束之后就剩于x
	}
	return x;
}
int main()
{
	int nums[] = { 9,3,5,2,0,6,1,4,7 };
	int numssize = sizeof(nums) / sizeof(nums[0]);
	int z= missingnumber(nums, numssize);
	printf("%d\n", z);
	return 0;
}