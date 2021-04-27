#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>


//一个整型数组 nums 里除了两个数字之外，其他数字都出现了两次，请写出程序找出这两个只出现一次的数字
//要求时间复杂度是O(n)，空间复杂度是O(1)

//先把所有数异或，然后就剩下这两个数以获得结果
//假设出现1次的数为x1和x2  这样之后 出现两次的都异或没了

int* singlenumber(int* nums, int numssize, int* returnsize)
{
	int ret = 0;//0和任何数异或都是任何数
	for (int i = 0; i < numssize; ++i)
	{
		ret ^= nums[i];//ret的值=x1^x2
		//下一步就是把x1和x2分离开  可以尝试找规律
	}//正数的原反补相同 异或：相异为1 相同为0
	//假设x2=5，x1=3  那么最后就是 0101   0011  异或结果就是0110 是6
	//此时随便找一个位 找出x位为1  ret里面可以分析为1的位 说明x1和x2的
	//的第m位不一样，一个为1，一个为0  
	// 
	//分离的时候从原数组分离出x1和x2  第m位为一的为一组  第m位为0的为一组
	//x1和x2各自在一组，其它数成对出现在一组 只有x1和x2出现一次

	int m = 0;
	while (m < 32)
	{
		if (ret & (1 << m))//找出ret的第m位为1的位
		{
			break;//因为是真的(非0) 所以break 因为ret不为0
		}
		else
		{
			++m;
		}
	}

	//分离
	int x1 = 0, x2 = 0;
	for (int i = 0; i < numssize; ++i)
	{
		if (nums[i] & (1 << m))
		{
			x1 ^= nums[i];
		}
		else
		{
			x2 ^= nums[i];
		}
	}
	int* retarr = malloc(sizeof(int) * 2);
	retarr[0] = x1;
	retarr[1] = x2;
	*returnsize = 2;
	return retarr;
}
int main()
{
	return 0;
}