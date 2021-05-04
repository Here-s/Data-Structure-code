#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>


//移除元素
//给你一个数组nums和一个值val，你需要原地移除所有数值等于val的元素 并返回移除后数组的新长度
//不要使用额外的数组空间，你必须仅使用O(1)额外空间并原地修改数组
//元素的顺序可以改变，你不需要考虑数组中超出新长度后面的元素

//比如1 3 4 3 5 3 3 就是把里面的3全移除掉 
//无视时间复杂度和空间复杂度的要求就是 遇到3就把后面的数据往前挪 这样的话 时间复杂度就是O(N^2)
//方法二就是再拿一个空数组 把不是3的数就拷到下面这个数组 就得到答案了 然后再把新的数组拷回去 空间复杂度就是O(N)

//所以最后的方法就是通过两个下标 如果第一个下标指向的内容不是3的话 就把第一个下标的值赋给第二个下标 刚开始两个下标都从头开始
//如果第一个下标指向3 那么第二个下标不动 第一个下标往后挪 直到不是3 然后把值付给第二个下标 现在就是原数组上面修改
//也就是每次第二个下标赋值之后 才往后面挪一位

int removeElement(int* nums, int numsSize, int val)//val是要移除的数值  有返回值 返回移除后数组的新长度
{
	int src = 0;//这里是第一个下标 可以理解为上面的
	int dst = 0;
	while (src < numsSize)//第挪动的下标到了结尾就结束了
	{
		if (nums[src] !=val)//如果src的值不等于val就说明应该把它留下来
		{
			nums[dst] = nums[src];//这样就把数值留下来了
			src++;
			dst++;
		}
		else//代表src遇到的是3 
		{
			++src;//这里表示src遇到的是3 所以后移一个位
		}
	}
	//发现移完之后dst就是数组的大小  因为dst刚好是移完之后第一个3的下标 刚好是前面元素的个数
	return dst;
}

int main()
{
	int nums[7] = { 1,3,4,3,5,3,3 };
	int numsSize = sizeof(nums) / sizeof(nums[0]);
	int val = 3;
	int lon = removeElement(&nums, numsSize, val);
	printf("%d\n", lon);
	return 0;
}