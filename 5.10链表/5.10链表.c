#define _CRT_SECURE_NO_WARNINGS 1
#include"5.10链表.h"
//结点==节点（本文中意思相等）

//链表结构 SListNode* pList 这个指针最初是空的 假设创建了几个节点 就是让SListNode* pList
//pList 是一个指针变量 四个字节 存的是 0x30 指向的是第一个结点 以此类推 就存起来了
//指向第一个结点 假设有三个结点 地址是 0x30  0x10  0xA0 


void SListPushBack(SListNode* phead, SListDataType x)//尾插
{
	//单链表的缺陷 尾插一个结点 应该动态申请一个结点 插入到最后一个结点的后面 还得遍历找到尾
	if (phead == NULL)
	{
		SListNode* newNode = (SListNode*)malloc(sizeof(SListNode));
		if (newNode == NULL)
		{
			printf("申请结点失败\n");
			exit(-1);
		}
		newNode->data = x;
		newNode->next = NULL;//将下一个指针置为空 防止非法访问
		phead = newNode;
	}
	else
	{
		SListNode* tail = phead;
		while (tail->next != NULL)//这里是访问空指针
		{
			tail = tail->next;//当这样循环完了之后 指向的就是最后一个空指针
		}
		//找到尾巴 就能插入这个结点 申请内存 顺序表更高效
	}
}

void SListPrint(SListNode* phead)//通过调用 接收传来的值 传来的值也就是pList的地址
{
	//这里就不需要assert断言一下是否为空了 因为它可能本来就为空
	//为了方便下一次还能遍历这个链表 就应该这样写代码
	SListNode* cur = phead;//cur是定义当前位置 把phead给了cur  就说明cur也指向第一个结点
	while (cur != NULL)//如果等于NULL的话 就说明是空链表 就不进去如果不是的话 就可以打印这里的值
	{
		printf("%d->", cur->data);//cur是一个SListNode* 类型 以前是数组下标 这里应该指向另外一个地址
		cur = cur->next;//把下一个地址赋给cur 就可以通过循环链式访问 当最后一个的时候 cur等于0 就结束了
		//这里就是遍历链表
	}
}
