#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>

//反转一个单链表  例如：
//输入：1->2->3->4->5->NULL  输出：5->4->3->2->1->NULL
//可以迭代或递归地反转链表

//方法2：头插法 插到一个新的链表当中 就变成 5->4->3->2->1->NULL了 取上面结点头插 新链表只有一个newhead 
//newhead是空的 把上面的依次取下来 然后头插进去 取一个cur指向1 next指向2 所以就让cur指向newhead 
//然后newhead往后挪一个 挪到1 next再指向cur  next=next->next 这样循环就够了 就是让cur变成新的头
//当cur指向NULL的时候就结束了

typedef struct ListNode//这里一定要有tydedef
{
	struct ListNode* next;
}ListNode;

struct ListNode* reverseList(struct ListNode* head)
{
	//核心思路：取cur头插到以newhead为头的新链表当中
	struct ListNode* newHead = NULL;
	struct ListNode* cur = head;
	struct ListNode* next = cur->next;
	while (cur != NULL)
	{
		//头插
		cur->next = newHead;
		newHead = cur;//完成头插
		cur = next;
		next = next->next;
	}
	return newHead;//这里就是链表新的头
}
int main()
{

	return 0;
}
