#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>

//删除链表中重复的结点  链表删除 头尾也要考虑
//在一个排序的链表中 存在重复的结点 请删除该链表中重复的结点 重复的结点不保留
//返回链表头指针 例如：1->2->3->3->4->4->5 处理后为 1->2->5 
//
//思路：肯定还是一前一后两个指针 如果两个指针一样的话 就删掉 所以也要先判断是不是只有一个结点
//一前(cur)一后(next)两个指针 两个指针相等的时候 next往后移 直到cur和next不相同
//此时就把相同的free就行了 但是要让free前面的那个节点指向next这个节点 所以又需要一个指针
//需要一个指向前面的指针(prev) 然后再把next给了cur next继续往后移 继续比 如果一样 next往后移
//直到两个不一样 然后继续free掉cur next给了prev 继续往后走 直到next为NULL 就行了 
//不常规情况：如果头和尾有几个相同的 比如 1 1 1 3 4 4 prev在最前面 cur指向第一个1 prev指向第二个1
//一上来 cur和prev 相同 当cur和next不相同的时候 让prev的next指向next 程序崩溃了 因为prev要移动
//因为cur和next 一起移动的时候 prev也要移动 当cur和next没有同时移动的时候 prev就不用动
//除了这种头完全相同的 假设另外一种：1 2 3 3 4 5 5 5 刚开始 cur和next不相同 所以这三个
//这三个指针一块移动 如果删除了相同的 prev不动 但是当cur和next 走到相同的第一个和第二个5的时候
//next再往后走 当next走到NULL的时候 程序停下来 但是把这三个5也都删了 然后把prev指向了next(NULL)
//然后cur再移动 也是NULL 这样就可能出问题 为NULL 
//

typedef struct ListNode//这里一定要有tydedef
{
	struct ListNode* next;
	int val;
}ListNode;
struct ListNode* deleteDuplication(ListNode* pHead)
{
	if (pHead == NULL || pHead->next == NULL)
	{
		return pHead;
	}
	ListNode* prev = NULL;
	ListNode* cur = pHead;
	ListNode* next = cur->next;
	//接下来就是比的过程 要控制next不为NULL 先来解决普通情况
	while (next)
	{
		if (cur->val != next->val)//同时往后走 
		{
			prev = cur;
			cur = next;
			next = next->next;
		}
		else//表示遇到相等的情况了 
		{
			while (next && cur->val == next->val)//这里是把cur和next 改到指向不相等的地方 并且next不为NULL
				//这就是判断尾的那种情况
			{
				next = next->next;
			}
			//走到这里 就说明cur和next 不相等了
			if (prev)
			{
				prev->next = next;//这里就是刚好把尾相同的情况 把尾置成NULL了
			}
			else//当出现 1 1 1 3 4 这种情况的时候 就发生意外了 因为这里的cur和next相同 
				//当next和cur不相等的话 就把next给了prev->next 但是此时的prev是NULL 所以程序就崩溃了
				//所以这里加判断 这里就是一上来就是需要删的情况 前面头相等 那么就还会有尾相等的问题
			{
				pHead = next;
			}
			//然后释放相同的节点
			while (cur != next)//因为释放之后cur会往前走 所以这样最后就使得cur和next相等了
			{
				ListNode* del = cur;
				cur = cur->next;//避免释放掉cur之后 找不到下一个的地址
				free(del);//
			}
			if (next)
			{
				next = next->next;//释放之后 next继续往后挪 如果next为NULL的话 继续循环 然后就又出问题了
			}
		}
	}
	return pHead;
}
int main()
{
	return 0;
}