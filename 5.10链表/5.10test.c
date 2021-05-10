
//#define _CRT_SECURE_NO_WARNINGS 1
#include"5.10链表.h"
//数据结构的程序崩了一般是内存问题 更多是越界
//将链表定义为只有一个头指针 就是


//多用调试定位问题

int main()
{
	//SListNode* phead = NULL;//
	SListNode* pList = NULL;//也可以这样写 只有一个头指针 pList 再申请一个节点，让指针指向节点 以此类推
	//初始化也没问题 不初始化也可以
	SListPushBack(pList, 1);
	SListPushBack(pList, 2);
	SListPushBack(pList, 3);
	SListPushBack(pList, 4);

	SListPrint(pList);//外面调用方式
	return 0;
}