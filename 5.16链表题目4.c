#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>
//要控制写代码的速度 因为面试的时候 一道算法题 时间在十分钟 因为标准的面试时间在40分钟
//建议纸上写


//题目：输入一个链表，输出该链表中倒数第k个结点 也是只能遍历一次
//例如：输入： 1 {1, 2, 3, 4, 5}  返回值：{5}

typedef struct ListNode//这里一定要有tydedef
{
	struct ListNode* next;
}Node;
struct ListNode* FindKthToTail(struct ListNode* pListHead, int k)
{ 
	//因为是倒数的k个，也就是正数的n-k个 也可以使用双指针来解决 假设求倒数第三个
	//一个慢指针 一个快指针 让快指针先走三步，然后再同时走 现在快慢指针的差别是三步
	//同时走 当fast走到NULL的时候 和slow还是差三步 这个结点就是倒数第三个结点 当是偶数也可以
	//如果fast要指向尾指针的话 就是走k-1步
	Node* fast = pListHead;
	Node* slow = pListHead;
	while (k--)//fast要先走k步 是k-- 这里已经拉开k步了
	{
		if (fast != NULL)//说明存在这种k大于链表长度的情况
		{
			fast = fast->next;//可能存在fast为NULL了 但是循环还在走 可能存在k大于链表的长度
			//k大于链表的长度就是这种情况 所以需要判断一下
		}
		else//说明已经提前结束了
		{
			return NULL;
		}
	}
	//上面已经拉开k步了 现在应该一起走了
	while (fast)
	{
		slow = slow->next;
		fast = fast->next;
	}
	return slow;
}
int main()
{

	return 0;
}


//链表的中间节点
//给定一个头结点为head的非空单链表，返回链表的中间结点，如果有两个中间结点，则返回第二个中间结点
//例如：输入：[1,2,3,4,5]  输出：此列表中的结点 3  返回的结点值为 3

//遍历一遍 得到总的个数n n/2 就知道中间位置在哪里了 再遍历一遍 就数到中间我值了 
//写上面的想法是很难满足要求的 要求只能遍历一次这个链表 涉及到链表里面的快慢指针 快慢指针可以
//可以解决一系列问题，快慢指针也叫双指针  定义两个指针 可以认为一快一慢 指向同一个位置
//满指针叫slow 快指针叫fast 让快指针一次走两步慢指针一次走一步 快指针走的速度是慢指针的两倍
//也就是说快指针走完的时候 慢指针刚走了一半 这就是方法 当五个元素的时候 fast走到最后一个 
//slow刚好走到中间  如果是偶数个的话 当fast指向最后一个节点的next的时候 slow指向中间位置
//

//typedef struct ListNode//这里一定要有tydedef
//{
//	struct ListNode* next;
//};
//struct ListNode* middleNode(struct ListNode* head)
//{
//	Node* slow = head;
//	Node* fast = head;//因为快慢指针都是从头开始的
//	while (fast && fast->next!=NULL)//不知道是奇数个还是偶数个
//	{
//		slow = slow->next;
//		fast = fast->next->next;//如果是奇数个的话 fast不为NULL 所以判断条件里面加上并且
//		//并且fast->next也不为NULL 
//	}
//	return slow;
//}
//int main()
//{
//
//	return 0;
//}