#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>


//删除排序数组中的重复项  也可以叫做去重
//给定一个排序数组，你需要在原地删除重复出现的元素，使得美国元素只出现一次，返回移除后数组的新长度

//不要使用额外的数组空间，你必须在原地修改输入数组并在使用O(1)额外空间的条件下完成

//假设原数组是[1,1,2,2,2,3] 要去重 因为是排好序的所以可以用前后两个指针 再开一个数组
//然后两个指针往后移 如果两个值不相同 那么就往新开的数组里面挪一个，挪前面那个指针指向的值
//就这样一直往后挪 又遇到两个值不相同 那么就把前面指针指向的值挪到新开的数组当中
//如果到尾之后 就把最后的数挪下来 然后把数组拷回去 覆盖原来的数组

//也可以使用前后两个下标 如果不相同的话 就一直覆盖 和上一道题一样 
//比如前一个下标是prev 后一个下标是cur  然后在定义一个位置作为目标 叫做dst
//两个下标往后移当两个下标不一样的时候 就说明前面那个可能过滤掉很多一样的
//把不一样的赋给dst 然后dst也往后面挪一个 那两个下标继续 当两个下标指向的数据不相同时 继续前一步那样
//当两个下标走到结尾的时候 把最后一个值给过去 此时的det就是前面数组内容的个数


int removeDuplicates(int* nums, int numsSize)
{
	int prev = 0;
	int cur = 1;
	int dst = 0;
	while (cur<numsSize)//不能拿prev来判断 因为prev没越界 而cur最后越界了 如果cur和prev恰好相同那就出问题了
	{
		if (nums[prev] != nums[cur])
		{
			nums[dst] = nums[prev];
			prev++;
			cur++;
			dst++;
		}
		else
		{
			++prev;
			++cur;
		}
	}
	nums[dst] = nums[prev];//这样就把最后一个值给了dst了
	dst++;
	prev++;
	return dst;
}
int main()
{
	int nums[] = { 1,1,2,2,2,3,4 };
	int sz = sizeof(nums) / sizeof(nums[0]);
	int p = removeDuplicates(nums, sz);
	printf("%d\n", p);
	return 0;
}