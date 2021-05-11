#define _CRT_SECURE_NO_WARNINGS 1
#include"5.11链表2.h"
//结点==节点（本文中意思相等）

//链表结构 SListNode* pList 这个指针最初是空的 假设创建了几个节点 就是让SListNode* pList
//pList 是一个指针变量 四个字节 存的是 0x30 指向的是第一个结点 以此类推 就存起来了
//指向第一个结点 假设有三个结点 地址是 0x30  0x10  0xA0 

//再增加一个结点
SListNode* BuySListNode(SListDataType x)
{
	SListNode* newNode = (SListNode*)malloc(sizeof(SListNode));
	if (newNode == NULL)
	{
		printf("申请结点失败\n");
		exit(-1);
	}
	newNode->data = x;
	newNode->next = NULL;

	return newNode;
}

void SListPushBack(SListNode** pphead, SListDataType x)//尾插  因为是二级指针 所以下面使用的时候
//使用的时候 都要解引用一下
{
	//单链表的缺陷 尾插一个结点 应该动态申请一个结点 插入到最后一个结点的后面 还得遍历找到尾
	SListNode* newNode = BuySListNode(x);//调用这个创建结点的函数 就可以大大节省代码量
	if (*pphead == NULL)
	{
		*pphead = newNode;
	}
	else//找尾
	{
		SListNode* tail = *pphead;
		while (tail->next != NULL)//这里是访问空指针
		{
			tail = tail->next;//当这样循环完了之后 指向的就是最后一个空指针
		}
		//找到尾巴 就能插入这个结点 申请内存 顺序表更高效
		tail->next = newNode;
	}
}

void SListPopBack(SListNode** pphead)// 应该考虑三种情况 1 这个链表是空 就不需要删了
//第二种情况 如果链表只有一个结点 就不应该先写再来查了   在全删除之后 要把pList指针置为空 就把链表变成空链表了
//第三种情况 一个以上结点 就需要传二级指针了
{
	//第一种情况
	if (*pphead == NULL)//因为是空指针 所以不需要删除 直接返回
	{
		return;
	}
	else if ((*pphead)->next == NULL)//*pphead是pList的值 指向第一个结点 判断之后 只有一个结点
	{
		//因为只有一个结点 所以要删的也就是pList的值 所以只把这一个置空就行了
		*pphead = NULL;
	}
	else//最复杂的情况 要删除尾 因为每个部分都可能变化 tail的next如果是null的话 就把这个释放
		//但是前面那个就变成新的尾了 如果再遍历的话 前面的一个就指向了野指针 就成为非法访问了
	{
		SListNode* tail = *pphead;
		SListNode* prev = NULL;
		while (tail->next!=NULL)//但是这样循环结束之后 tail就是空指针 所以再定义一个prev 意思是前一个
			//所以在tail往下走的时候 先把值赋给prev 这样循环结束的时候 prev指向的就是前一个
		{
			prev = tail;
			tail = tail->next;
		}
		free(tail);//释放掉tail之后 就没人调用tail了 所以就不需要专门把tail置成NULL
		prev->next = NULL;

	}
}

void SListPrint(SListNode* phead)//通过调用 接收传来的值 传来的值也就是pList的地址
{
	//这里就不需要assert断言一下是否为空了 因为它可能本来就为空
	//为了方便下一次还能遍历这个链表 就应该这样写代码
	SListNode* cur = phead;//cur是定义当前位置 把phead给了cur  就说明cur也指向第一个结点
	while (cur != NULL)//如果等于NULL的话 就说明是空链表 就不进去如果不是的话 就可以打印这里的值
	{
		printf("%d ", cur->data);//cur是一个SListNode* 类型 以前是数组下标 这里应该指向另外一个地址
		cur = cur->next;//把下一个地址赋给cur 就可以通过循环链式访问 当最后一个的时候 cur等于0 就结束了
		//这里就是遍历链表
	}
}

