#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>
#include<malloc.h>

//时间复杂度不计算时间  计算大概的运算次数
//空间复杂度不计算空间  计算大概定义的变量个数
//都是O来计算


//计算斐波那契数列Fibonacci的时间复杂度 因为每次递归是一次，所以就意味着要算递归多少次
// 根据斐波那契数列就又引申出黄金分割率
//递归调用是：斐波那契n=斐波那契n-1+斐波那契n-2  斐波那契n-1又等于斐波那契n-2+斐波那契n-3  以此类推
//这是二叉树的形状  就是上面有一个 下面就有两个 这样就是第1层2的0次方  第二层是2的1次方
//第三层是2的2次方 
//递归到n=1就结束了 所以调用次数是 2^0+2^1+2^3+....+2^(n-1) 因为第一层是2的0次方1
//所以这些加起来就是2^n-1  就画图然后就能找到这个规律  因为算的时候-1可以忽略掉  所以就是2^n
//画图出来之后可以发现最后几行，最右边的先变成0  然后是左边变成0  所以最后一层只有一个斐波那契1
//所以后面的区域 是斜上来的 但是这一点对结果影响不大  所以还是2的n次方
long long Fibonacci_r(size_t N)//_r表示递归   递归到空间复杂度 是常数个*N  所以是O(N) 
{
	return N < 2 ? N: Fibonacci_r(N - 1) + Fibonacci_r(N - 2);
}
// 现有两个要求：1 求出整个斐波那契数列  
long long* Fibonacci(size_t N)
{
	long long* fibarray = malloc(sizeof(long long)* (N+1));//因为以后数据会大 所以用long long
	//多开一个N+1 使得就不会如果N等于1 或者2 就会出问题
	fibarray[0] = 0;
	if (N == 0)
		return fibarray;
	fibarray[1] = 1;
	//以空间换时间：多开了一个空间，开了一个数组出来多消耗空间 但是使性能更快
	//用第一项第二项去算第三项 就能算出时间复杂度是O(N)
	for (int i = 2; i <= N; ++i)
	{
		fibarray[i] = fibarray[i - 1] + fibarray[i - 2];
	}
	return fibarray;//如果N等于1 或者2 就会出问题
}
int main()
{
	printf("%d\n", Fibonacci(40));//时间复杂度是2^n 所以这里是1000次  增加到30是10亿次
	//当40次就是一万亿次   改成50就要算好久  优化方法：因为使用递归方式计算 会有很多的重复
	return 0;
}
