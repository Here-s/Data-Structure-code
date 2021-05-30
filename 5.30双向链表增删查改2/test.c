#define _CRT_SECURE_NO_WARNINGS 1
#include"5.30双向链表增删查改2.h"


void TestList01()
{
	//ListNode* phead = NULL;//定义一个链表的带头节点
	//ListInit(&phead);//并不能达到这样的效果 传给指针 然后另外一边创建节点
	//所以用二级指针可以解决这个问题 但是还有第二种方法

	//第二种方法 把值改变了 但是再返回新的值就好了 就是返回新的头
	ListNode* phead = ListInit();//因为返回的是创建的链表的头 所以不需要传指针
	//这样就是返回新的头指针 就不需要上面那么麻烦了
	ListPushBack(phead, 1);
	ListPushBack(phead, 2);
	ListPushBack(phead, 3);
	ListPushBack(phead, 4);

	ListPrint(phead);

	ListPopBack(phead);
	ListPopBack(phead);
	ListPopBack(phead);

	ListPrint(phead);

	ListPushFront(phead, 1);
	ListPushFront(phead, 2);
	ListPushFront(phead, 3);
	ListPushFront(phead, 4);
	ListPushFront(phead, 5);

	ListPrint(phead);

	ListPopFront(phead);
	ListPopFront(phead);
	ListPopFront(phead);
	ListPopFront(phead);
	ListPopFront(phead);

	ListPrint(phead);

}

void TestList02()
{
	ListNode* phead = ListInit();
	ListPushBack(phead, 1);
	ListPushBack(phead, 2);
	ListPushBack(phead, 3);
	ListPushBack(phead, 4);

	ListPrint(phead);

	ListNode* pos = ListFind(phead, 3);
	pos->data = 300;
	ListInsert(pos, 30);
	ListPrint(phead);

	ListErase(pos);
	ListPrint(phead);

}

int main()
{
	//TestList01;
	TestList02;
	return 0;
}