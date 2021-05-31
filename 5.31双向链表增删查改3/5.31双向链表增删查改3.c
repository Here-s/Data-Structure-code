#define _CRT_SECURE_NO_WARNINGS 1
#include"5.31双向链表增删查改3.h"
//操作系统 网络 数据库 很重要
//创建变量的时候 要注意起名 防止混了


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

void ListClear(ListNode* phead)//就比如购物车 清空的时候 把商品清空了 但是购物车这个功能还得有
//就相当于还有头结点2
{
	//清理所有的数据节点 保留头结点 可以继续使用
	assert(phead);
	ListNode* cur = phead;
	while (cur != phead)
	{
		ListNode* next = cur->next;
		free(cur);
		cur = next;//这里就结束了
	}
	phead->next = phead;
	phead->prev = phead;//使phead变成循环结构 这样就能保持增删查改没问题
}

void ListDestory(ListNode* phead)//销毁链表
{
	assert(phead);
	ListClear(phead);//调用Clear 就能保证把前面的清楚了
	free(phead);//这里就练phead也不要了 就把链表销毁了
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

void ListPushBack(ListNode* phead, LTDataType x)//尾插
{
	//assert(phead);//断言一下
	//ListNode* tail = phead->prev;
	//ListNode* newnode = BuyListNode(x);
	////现在就是链接三个节点关系 phead tail newnode 
	//tail->next = newnode;
	//newnode->prev = tail;
	//newnode->next = phead;
	//phead->prev = newnode;
	////这个尾插是O(1)的时间复杂度 

	//尾插也能这样写
	ListInsert(phead, x);//phead的前一个就是尾

}

void ListPopBack(ListNode* phead)//尾删
{
	assert(phead);
	assert(phead->next);
	ListNode* tail = phead->prev;
	ListNode* tailprev = tail->prev;
	tailprev->next = phead;
	phead->prev = tailprev;
	free(tail);
	tail = NULL;//也可以不置空 因为这是个局部变量

	//尾删的第二种写法
	ListErase(phead->prev);//尾删就是删头结点的前一个
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

	//增加代码复用性 也能这样写
	ListInsert(phead->next, x);//这样就实现头插了

	ListInsert(phead, x);//这样就实现尾插了 因为插在了phead的前面 
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

	//头删的第二种方法
	ListErase(phead->next);//头删就是删第一个的next
}

ListNode* ListFind(ListNode* phead, LTDataType x)
{
	assert(phead);
	ListNode* cur = phead->next;
	while (cur != phead)
	{
		if (cur->data == x)
		{
			return cur;
		}
		cur = cur->next;
	}
	return NULL;
}

void ListInsert(ListNode* pos, LTDataType x)//在pos节点前面去插入x find就可以找到位置
{
	//先创建一个节点 然后就是三个节点的相互连接 在任意位置插入都没问题 因为是循环链表 不会NULL
	assert(pos);
	ListNode* posPrev = pos->prev;
	ListNode* newnode = BuyListNode(x);
	//现在的话 就是pos newnode posPrev 三个节点的连接问题了
	posPrev->next = newnode;
	newnode->prev = posPrev;
	newnode->next = pos;
	pos->prev = newnode;
}

void ListErase(ListNode* pos)//在某个节点去删除 删的时候不能删phead
{
	assert(pos);
	ListNode* posPrev = pos->next;
	ListNode* posNext = pos->next;
	free(pos);
	posPrev->next = posNext;
	posNext->prev = posPrev;
}