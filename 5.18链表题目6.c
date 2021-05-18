#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>
//链表要注意死循环


//现有一链表的头指针ListNode* pHead，给一定值x，编写一段代码将所有小于x的节点排在
//其余结点之前，且不能改变原来的数据顺序，返回重新排列后的链表的头指针

//意思是 假设一个链表是这样的4->2->5->3->6->9->0 以3为分界线 小于4的放在前面
//大于4的放在后面不能改变相对顺序 相对顺序就是排完之后是2->3->0->4->5->6->9

//可以定义两个链表 进行尾插一个叫lessList 定义为头指针 哨兵位 然后再给一个
//给一个greaterList哨兵位 遍历原链表 把比4小的尾插到lessList后面 把大的尾插到
//尾插到greaterList后面 然后再把两个链表合起来 借助哨兵位的头指针更好用

typedef struct ListNode//这里一定要有tydedef
{
	struct ListNode* next;
	int val;
}ListNode;
struct ListNode* partition(struct ListNode* pHead, int x)
{
	//定义短链表的头和尾
	ListNode* lessHead;
	ListNode* lessTail;
	//定义长链表的头和尾
	ListNode* greaterHead;
	ListNode* greaterTail;
	//把头处理好
	lessHead = lessTail = (ListNode*)malloc(sizeof(ListNode));
	greaterHead = greaterTail = (ListNode*)malloc(sizeof(ListNode));
	lessHead->next = greaterHead->next = NULL;//重置头为NULL
	ListNode* cur = pHead;
	//如果按照这样排完之后 最后一个大于x的数 还指向它后面那个小于x的数 但是小于x的链表
	//里面的最后一个数也指向那个拿下来的那个数 就出现这样的问题了 就形成了带环 也就是变成死循环了
	while (cur)
	{
		if (cur->val < x)
		{
			lessTail->next = cur;
			lessTail = lessTail->next;
		}
		else
		{
			greaterTail->next = cur;
			greaterTail = greaterTail->next;
		}
		cur = cur->next;
	}
	lessTail->next = greaterHead->next;
	greaterTail->next = NULL;
	ListNode* list = lessHead->next;
	free(lessHead);
	free(greaterHead);
	return list;
}

int main()
{
	return 0;
}