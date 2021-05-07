#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>
#include<stdlib.h>//malloc的调用函数头文件


//数组形式的整数加法
//对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组，例如：如果 X =1231
//那么X的数组形式为{1，2，3，1}  return一个数组 这个数组必须被malloc的

//给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式
//例如：输入：A[1,2,0,0],k=34  输出：[1,2,3,4] 解释：1200+34=1234

//如果用模10 除10 这样表示出来  然后再弄成数组回去
//如果把转出来的时候表示成 unsigned long long 最多表示到2^64  long long最多2^63 因为有一半是负的
//2^60大约等于 10亿*10亿 也就是1后面18个0 再*2^4 也就是16后面加18个0 也就是说 如果大于这个值就表示不了了

//如果值太大的话（大数运算） 用类来算

//做法 比如数组是{2，7，4}  给的值是181 将这两个分离出来 使用一个下标指向数组[2,7,4]的4 另外一个下标指向181的1
//然后再开一个数组 用来算相加的值 它俩加的结果不能放在原数组中 因为如果要进位的话 那就放不下了 最多往前进一位
//这两个加起来的话 等于455

int* addtoarrayform(int* A, int ASize, int K, int* returnSize)
{
	//尝试着把k分离出来放到一个数组里面 题目没说哪个大哪个小 所以要分辨一下 看看那个数组大
	//题目给的K<=10000 A<=10000 也就是说 这两个值加完之后最多是一个6位数
	int kSize = 0;
	int knum = K;//防止下面把K弄消失
	while (K)//算k是多少位
	{
		++kSize;//因为k>0 所以进来先加一位
		knum /= 10;
	}
	int len = ASize > kSize ? ASize : kSize;
	int* retarr = (int*)malloc(sizeof(int) * (len + 1));//这样数组空间就变得多一位了
	//接下来依次取值相加 以大的位数为主 要大的完了才行
	int Ai = ASize - 1;//数组A的最后一个元素的下标
	int reti = 0;//新开数组的下标
	int nextNum = 0;//进位的数字

	//用这个代码 如果是1234 + 99 就没问题 因为用len来控制循环就没问题 有几位数 就循环几次
	//如果上面是99 下面是1234 的话 Ai 取两次之后就是非法访问了 所以再对Ai控制一次
	while (len--)//循环要走长度次 比如说长的是5 就走5次
	{
		int a = 0;
		if (Ai >= 0)//说明这样是合法访问
		{
			a = A[Ai];
			A--;
		}
		//当A取完之后 K还没取完 所以此时循环将A的值赋为0 然后就可以继续相加了 就不影响逻辑结果
		int ret = a + K % 10 + nextNum;//K模10 就得到K的个位了 把是一位的进位也加进来
		K /= 10;
		//加出来的结果往新数组里面放 可以顺着放 然后最后在逆序一下
		if (ret > 9)
		{
			ret -= 10;//说明这里大于10 要进位
			nextNum = 1;//进位1
		}
		else
		{
			nextNum = 0;//不需要进位 所以再把nextNum置为0
		}
		retarr[reti] = ret;
		++reti;//新开数组下标往后移一个位置
	}
	if (nextNum == 1)
	{
		retarr[reti] = 1;//往前进一位
		++reti;
	}
	int left = 0;
	int right = reti - 1;
	while (left < right)//逆置数组
	{
		int tmp = retarr[left];
		retarr[left] = retarr[right];
		retarr[right] = tmp;
		++left;
		--right;
	}
	*returnSize = reti;//*returnSize是传的值 所以这里再把reti的值赋会去
	return retarr;//返回结果数组
}
int main()
{

	return 0;
}