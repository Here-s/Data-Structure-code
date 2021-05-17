#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>
//做题的时候 要画图

//哨兵位指针：在空链表的时候 就已经有这一个节点了 也只需要传一级指针就行了 尾插也不需要新开节点
//只需要连到哨兵位节点就行了 头删的话 拿哨兵位节点指向第二个数据节点就行了 不需要二级指针


//合并两个有序链表
//将两个升序链表合并为一个新的升序链表并返回 新链表是通过拼接给定的两个链表的所有节点组成的
//例如：输入1->2->4   1->3->4->5  输出：1->1->2->3->4->4->5  合并以后还有序
//取两个指针 一个指针指向第一个(L1)的1  另一个指针指向第二个(L2)的1  然后进行尾插 取小的那一个 
//相当于互相比 取小的进行尾插 就相当于每次都是取小的进行尾插 尾插到新的链表 如果一个链表为NULL
//一个为NULL就结束了 然后让新的链表指向另外一个没取完的旧的链表
//核心思路是取小的节点进行尾插 新的链表叫head 然后返回新链表的头 每次插入的时候 拿到头去找尾
//如果取一个节点找一次尾 这就是一个n^2的算法 所以可以同时定义一个尾指针出来 最开始是NULL
//要尾插的话 第一次插入要把第一个取下来的节点给了NULL 所以可以通过第一步在两个链表当中
//取一个比较小的拿来做头 就是还没进循环之前 就已经找到头了 通过这样来初始化 然后再进去循环
//当一个链表为NULL 循环就结束了 然后就是尾插 把小的链到尾的后面 然后变成新的尾 
//然后L*=L*->next 然后再取一个小的下来 然后就这样循环 直到其中一个为NULL就结束了　另外一个不为NULL
//另外一个不为NULL的再整体弄下来  就好了 

typedef struct ListNode//这里一定要有tydedef
{
	struct ListNode* next;
	int val;
}Node;
struct ListNode* mergeTwoLists(struct ListNode* l1, struct ListNode* l2)
{
	//可能给的l1 l2 都为NULL 或者一个为NULL
	if (l1 == NULL)
	{
		return l2;//因为l2可能为NULL 也可能不为NULL 
	}
	if (l2 == NULL)
	{
		return l1;
	}
	//经过上面的判断 l1和l2合并的链表就是不为空的那一个 所以就能进行下面的判断了
	Node* head = NULL;
	Node* tail = NULL;
	//可以这样优化
	head = tail = (Node*)malloc(sizeof(Node));//这个节点叫做哨兵位头节点 不存储有效数据 
	//这样就不需要下面判断方式来连着头了 但是返回的时候不能返回head 因为没数据 所以
	if (l1->val < l2->val)//根据分析 应该拿一个小的来做头
	{
		head = tail = l1;//把l1取下来 但是也应该把l1往后挪
		l1 = l1->next;
	}
	else
	{
		head = tail = l2;
		l2 = l2->next;
	}
	//取小的进行尾插
	while (l1 && l2)//使l1和l2的值都不为NULL 有一个为NULL就结束了
	{
		if (l1->val < l2->val)//因为是升序 所以取小
		{
			tail->next = l1;
			l1 = l1->next;
		}
		else
		{
			tail->next = l2;
			l2 = l2->next;
		}
		tail = tail->next;
	}
	//走到这里的话 就说明有一个为NULL了
	if (l1)
	{
		tail->next = l1;
	}
	else
	{
		tail->next = l2;
	}
	return head;
	//使用哨兵位节点的返回方式
	Node* realhead = head->next;
	free(head);
	return realhead;
}
int main()
{

	return 0;
}