#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>

//时间复杂度，空间复杂度（用来分析性能）
//1 算法效率     降低复杂度
//2 时间复杂度   重点关注 算法的时间复杂度是一个函数 就是数学里面的函数表达式  算的是程序执行的大概的次数
// 
//3 空间复杂度   重在了解 因为内存很快 所以不需要  1G可以定义两千五百万个整型


//实际当中表示次数的时候 一般采用大O的渐近表示法 就是估算的意思  下面就可以表示为: O(N^2)
//大O符号（Big O nitation)：是用于描述的函数渐进行为的数学符号
//推导大O阶的方法
//1 用常数1取代运行时间中的所有加法常数
//2 在修改后的运行次数函数中，只保留最高阶项，比如这里有N的三次方 那么就保留n的三次方 前面有系数，也去掉
// 比如前面是2乘N的平方 那么就把2也去掉
//3 如果最高阶想存在且不是1，则去除与这个项目相乘的常数，得到的结果就是大O阶。
//使用大O的渐进表示法以后，func1的时间复杂度为：O(N^2)


//计算bubblesort的时间复杂度  两层循环  准确的次数是
//时间复杂度函数是(不要看代码，思考过程)：F(N)=1+2+.....+(n-1)的等差数列  时间复杂度是 O(N^2)
//最好的情况是 O(N)  平均是二分之N方

void bubbleSort(int* arr,int sz)//把数组的地址传过来  用指针传的
{
	int i = 0;
	int j = 0;
	for (i = 0; i < sz; i++)
	{
		for (j = 0; j < sz - 1 - i; j++)
		{
			if (arr[j] > arr[j + 1])
			{
				int tmp = 0;
				tmp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = tmp;
			}
		}
	}
}


//计算strchr的时间复杂度
const char* strchr(const char* str, int character);//调用的是strchr 时间复杂度是 O(N)


//有一个数组 在这个数组里面查找一个数，它的时间复杂度是 有三种情况
//最坏情况：达到最大运行次数       O(N)
//平均情况：在中间某个位置找到了   O(N/2)
//最好情况：在第一个就找到了       O(1)
//有最好和最坏 则看最坏

//计算func4的时间复杂度 是O(1)  就是常数次
void func4(int n)
{
	int count = 0;
	for (int k = 0; k < 100; ++k)//没有用n 用的是常数 所以按照上面说的那样，拿1来替代 就是O(1)
	{
		++count;
	}
	printf("%d\n", count);
}

//计算func3的时间复杂度   是O(m+n) 因为m n都是未知数 如果m远大于n  那么就是m
void func3(int n, int m)
{
	int count = 0;
	for (int k = 0; k < m; ++k)
	{
		++count;
	}
	for (int k = 0; k < n; ++k)
	{
		++count;
	}
	printf("%d\n", count);
}

//计算func2的时间复杂度  是n 因为按照什么的方法 这里是n
void func2(int n)
{
	int count = 0;
	for (int k = 0; k < 2 * n; ++k)
	{
		++count;
	}
	int m = 10;
	while (m--)
	{
		++count;
	}
	printf("%d\n", count);
}

//请算一下func1基本操作执行力多少次  根据分析发现 F(N)=N^2+2*N+10次  
//实际当中表示次数的时候 一般采用大O的渐近表示法 就是估算的意思  下面就可以表示为: O(N^2)

void func1(int n)
{
	int count = 0;
	for (int i = 0; i < n; ++i)//外面走一次，里面走n次，所以是走n的平方次
	{
		for (int j = 0; j < n; ++j) 
		{
			++count;
		}
	}
	for (int k = 0; k < 2 * n; ++k)//这里执行2*N次
	{
		++count;
	}
	int m = 10;//这里是执行了10次
	while (m--)
	{
		++count;
	}
	printf("%d\n", count);
}
