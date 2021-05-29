#define _CRT_SECURE_NO_WARNINGS 1
#include"5.29双向链表增删查改.h"
//操作系统 网络 数据库 很重要


ListNode* BuyListNode(LTDataType x)//malloc一个节点出来
{
	ListNode* node = (ListNode*)malloc(sizeof(ListNode));
	node->next = NULL;//C语言中除了静态和全局变量 都要初始化为NULL 不然就是随机值
	node->prev = NULL;
	node->data = x;
	return node;
}

//void ListInit(ListNode** pphead)
//{
//	*pphead = BuyListNode(0);//创建一个节点 这里的phead和测试里面的phead不是同一个phead
//	//因为传过来之后 是值传递 不是地址传递 应该传二级指针
//
//	(*pphead)->next = *pphead;//设计优先级 所以用括号
//	(*pphead)->prev = *pphead;
//}

//第二种方法
ListNode* ListInit()
{
	ListNode* phead = BuyListNode(0);
	phead->next = phead;
	phead->prev = phead;
	return phead;
}

void ListPrint(ListNode* phead)//现在打印链表 但是和以前的打印方式不一样了
//以前是定义一个cur cur不为NULL就继续走 为NULL就结束 因为不是循环链表
//循环链表永远不为NULL 会一直绕 这个链表还有一个头结点 头结点不是有效节点
//不能打印出来 因为存的不是有效值 所以cur不能指向头节点(phead) 应该指向第二个
//第二个节点就是有效值了 当cur往下走 当cur=phead的时候就结束了 
{
	assert(phead);//
	ListNode* cur = phead->next;
	while (cur != phead)
	{
		printf("%d ", cur->data);
		cur = cur->next;
	}
	printf("\n");
}

void ListPushBack(ListNode* phead, LTDataType x)
{
	assert(phead);//断言一下
	ListNode* tail = phead->prev;
	ListNode* newnode = BuyListNode(x);
	//现在就是链接三个节点关系 phead tail newnode 
	tail->next = newnode;
	newnode->prev = tail;
	newnode->next = phead;
	phead->prev = newnode;
	//这个尾插是O(1)的时间复杂度 
}

void ListPopBack(ListNode* phead)
{
	assert(phead);
	assert(phead->next);
	ListNode* tail = phead->prev;
	ListNode* tailprev = tail->prev;
	tailprev->next = phead;
	phead->prev = tailprev;
	free(tail);
	tail = NULL;//也可以不置空 因为这是个局部变量
}

void ListPushFront(ListNode* phead, LTDataType x)//头插
{
	assert(phead);
	ListNode* first = phead->next;
	ListNode* newnode = BuyListNode(x);
	phead->next = newnode;
	newnode->prev = phead;
	newnode->next = first;
	first->prev = newnode;
}

void ListPopFront(ListNode* phead)//头删
{
	assert(phead);
	assert(phead->next != phead);
	ListNode* first = phead->next;
	ListNode* second = first->next;
	phead->next = second;
	second->prev = phead;
	free(first);
}
