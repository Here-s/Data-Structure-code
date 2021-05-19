#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>


//对于一个链表 请设计一个时间复杂度为O(n) 额外空间复杂度为O(1)的算法 判断其是否为回文结构
//给定一个链表的头指针A 请返回一个值来判断，代表其是否为回文结构，保证链表长度小于等于900
//如果为假 返回-1 为真 返回1 

//保证链表长度小于900 可以开辟一个小于900的数组 遍历链表 把链表的值全放到数组里面
//左边left 右边right 如果相等 往中间走 就像逆置的这样子来写 但是前提是题目告诉这个题是
//是长度小于900 但是尽量不要这样写 

//回文就是对称的意思 例如：1->2->2->1 以中间对称 限制了空间时间复杂度

//单链表是只能顺着走 不能逆着走  双向链表可以 因为双向链表有两个指针 一个指向前一个一个指向后一个
//借助之前写过的逆置和中间查找来解题 例如：1 2 3 3 2 1    1 2 3 2 1 都是链表，取出后半段来逆置
//逆置之后和前面的一样 就只需要比较连个链表一不一样就好了 在第一个链表里面找第二个3
//第二个链表里面找3 这样就找到中间位置了 应该用快慢指针来找 一个指针走一步 另一个指针走两步
//偶数个的话 慢指针指针走到第二个3的时候 快指针刚好指向NULL 单数个的话 慢指针走向3的时候 
//快指针刚好指向最后一个  因为快慢指针走的步数刚好是2倍 所以快指针走到最后一个的时候
//慢指针刚好走到中间
//

typedef struct ListNode//这里一定要有tydedef
{
	struct ListNode* next;
	int val;
}ListNode;
struct ListNode* reverseList(struct ListNode* head)
{
	struct ListNode* newHead = NULL;
	struct ListNode* cur = head;
	while (cur != NULL)
	{
		struct ListNode* next = cur->next;
		cur->next = newHead;
		newHead = cur;
		cur = next;
	}
	return newHead;
}
chkPalindrome(ListNode* A)
{
	ListNode* fast = A;
	ListNode* slow = A;
	ListNode* prev = NULL;
	while (fast && fast->next)
	{
		prev = slow;
		slow = slow->next;
		fast = fast->next->next;
	}
	prev->next = NULL;//这样就把前面和后面的链表断开了 没有任何关系了 就能比较了
	//这里结束之后 slow就是中间结点 接下来再写一个链表逆置就好了
	slow = reverseList(slow);//对链表进行逆置 逆置完之后 slow指向的是3 因为这里是值传递
	//用slow来接收链表的头
	//但是在偶数链表当中 slow指向第二个3 fast指向NULL 奇数链表当中 slow指向3 fast指向最后一个节点
	//奇数链表的话 是以3为头 把后面的逆置了 偶数链表的话 也是这样 接下来就要比较两个链表了
	//但是会有一个问题 拿偶数链表来举例 逆置后是 1 2 3 1 2 3 但是第一个3还是指向第二个3的
	//因为第一个3的next没动过 奇数链表的2 也是指向3 所以应该处理一下
	//知道slow 还得知道slow的prev(前一个)

	//前面链表是由A指向的
	while (A)//拿A来判断 就算是奇数链表也可以判断了
	{
		if (A->val != slow->val)
		{
			return -1;
		}
		else
		{
			A = A->next;
			slow = slow->next;
		}
	}
	//直到A结束 就能比较出相等的了 因为如果有一个地方不一样的话 在循环里面就退出了
	return 1;//走到这里一定是相等的
}

int main()
{
	return 0;
}