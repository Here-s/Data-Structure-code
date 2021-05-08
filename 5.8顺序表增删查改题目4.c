#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>


//CPU只有加法器 乘法是通过一直加来算的 除法也是通过减法来算的
//合并两个有序数组
//给你两个有序整数数组nums1和nums2 请你将nums2合并到nums1当中 使nums1成为一个有序数组

//说明：初始化nums1和nums2的元素数量分别是m和n 
//      你可以假设nums1有足够的空间（空间大小大于或等于m+n）来保存nums2当中的元素

//例如：输入nums1[1,2,3,0,0,0] m=3 nums2[2,5,6] n=3 输出[1,2,2,3,4,5,6]

//没有提出时间复杂度和空间复杂度的要求 所以可以随便发挥

//方法一：极端情况下 效率很低
//可以把nums2的值插入到nums1当中 并且放进去保持有序 一个一个比  如果比第一个大 再和第二个比 
//比的结果 如果是小于等于第二个的话 就把这个数放在第二个数的前面 以此类推 
//可以从后往前比 如果比这个数小就再和前面的比 如果比前面的大或者一样 那就把后面的数往后挪 放在这里 

//方法二：将nums2拷贝到nums1上，在排序 时间复杂度就是 O((m+n)*log(m+n))以2为底 

//要求时间复杂度优化到O(m + n);可以额外开一段空间 开m+n 一个下标指向nums1的第一位
//另一个下标指向nums2的第一位 然后比较 把小的放在新开的第一位 然后小的++ 如果相等就随便放谁都可以 然后放的再++
//然后继续比 这样排完序就是从小到大排的 如果一个数组取完了 那就说明另外一个没走完 把另外一个数据拷上去 用空间换时间
void merge(int* nums1, int nums1Size, int m, int* nums2, int nums2Size, int n)
{
	int* tmp = (int*)malloc(sizeof(int) * (m + n));
	int i1 = 0;
	int i2 = 0;
	int i = 0;
	while (i1 < m && i2 < n)
	{
		if (nums1[i1] < nums2[i2])
		{
			tmp[i] = nums1[i1];
			++i;
			++i1;
		}
		else
		{
			tmp[i] = nums2[i2];
			++i;
			++i2;
		}
	}
	//一定有一个先走到尾
	while (i1 < m)//说明i1还没走到尾
	{
		tmp[i] = nums1[i1];
		++i;
		++i1;
	}
	while (i2 < n)//说明i2还没走到尾
	{
		tmp[i] = nums1[i2];
		++i;
		++i2;
	}
	//现在数据在tmp上 再写个循环往回拷
	memcpy(nums1, tmp, sizeof(int) * (m + n));//意思是把tmp拷到nums1上面 拷的数据大小是sizeof(int) * (m + n）
	free(tmp);
}
int main()
{

	return 0;
}