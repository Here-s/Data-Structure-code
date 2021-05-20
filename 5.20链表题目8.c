#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>

//相交链表
//编写一个程序 找到两个单链表相交的起始节点
//例如：A: a1->a2->c1->c2->c3   B: b1->b2->b3->c1->c2->c3
//在节点c1开始相交

//一个节点不能指向两个节点  多个节点能指向一个节点 判断两个链表是否相交 最后一个
//就看最后一个一不一样就行了 就是尾节点一样就行了 如果是两个链表就不一样
//如果两个链表没有交点 就返回NULL 尽量满足O(n)时间复杂度 且仅用O(1)内存 
//就是不能开额外的空间出来

//最粗暴的方法就是把A的每个节点都去b里面找一遍 如果两个链表不相交 那么a里面的值就不出现在b里面
//不要拿值去比 应该拿节点的地址去比 如果按照这样去的话 时间复杂度不能满足 如果两个长度都是n的话
//时间复杂度就是O(n^2) 可以考虑求出la 和lb la和lb 分别是两个链表的长度 la有一个长一个短
//就让长的先走他们的差距步  比如la是5 lb是6 6-5=1 所以lb先走一步 这时距离相交点就相等了 
//然后同时走 相等的第一个点就是这里的交点 就是c1 这样时间复杂度就是O(n) 
//

typedef struct ListNode//这里一定要有tydedef
{
	struct ListNode* next;
	int val;
}ListNode;
struct ListNode* getIntersectionNode(struct ListNode* headA, struct ListNode* headB)
{
	ListNode* curA = headA;
	int la = 0;
	while (curA)//这样遍历的时候 就不会在最后找不到头了
	{
		++la;
		curA = curA->next;
	}
	ListNode* curB = headB;
	int lb = 0;
	while (curB)//这样遍历的时候 就不会在最后找不到头了
	{
		++lb;
		curB = curB->next;
	}
	//这样就求出两个链表的长度了
	//分三种情况 a长b短 b短a长 a b一样长
	ListNode* longList = headA;
	ListNode* shortList = headB;//默认a长b短
	if (lb > la)//说明前面的预设反了
	{
		longList = headB;
		shortList = headA;
	}
	//现在就不需要区分了
	int gap = abs(la - lb);//间距 abs就是求绝对值
	while (gap--)
	{
		longList = longList->next;
	}
	//走到这里就说明一样长了 以一个为准就行了
	while (longList)
	{
		if (longList == shortList)
		{
			return longList;
		}
		longList = longList->next;
		shortList = shortList->next;
	}
	//没相交 说明为NULL
	return NULL;
}
int main()
{
	return 0;
}