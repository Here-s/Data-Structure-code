#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>


//计算阶层递归factoria1的时间复杂度   实现的是阶层 底层是递归
//复杂度是 O(N)  递归算法如何计算：递归次数*每次每次递归函数的次数
//每次递归函数的次数是：N次乘以每次是1 就是乘N  所以就是N^2

//比如是 int m=10;  while(n--)  假设n是未知数，执行了n次 每次递归里面走了n次 所以就是n的平方
//如果有这种递归的循环的话   就是O(N^2) 
long long factorial(size_t N)
{
	return N < 2 ? N : factoria1(N - 1) * N;
}


//计算binarysearch的时间复杂度(二分查找，一半一半的找，完全二叉树的深度)  O(log以2为底的n的对数)
// 最开始是1，然后就是乘2乘2乘2，每找一次就乘2
// 每次查找，都是N/2/2/2/2/.../2=1   N=2^x  x是查找的次数  我们求的就是x  x在反解出来就是log以2为底的n的对数
//最好的情况是 O(1)

//二分查找要求：有序数组，
//红黑树 更强
int binarysearch(int* a, int n, int x)
{
	assert(a);
	int begin = 0;
	int end = n;
	while (begin < end)
	{
		int mid = begin + ((end - begin) >> 1);
		if (a[mid] < x)
		{
			begin = mid + 1;
		}
		else if (a[mid] > x)
		{
			end = mid;
		}
		else
			return mid;
	}
	return -1;
}