#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>


//反转一个单链表  例如：
//输入：1->2->3->4->5->NULL  输出：5->4->3->2->1->NULL
//可以迭代或递归地反转链表

//更好的解法是循环 因为是反转 所以不需要创建新链表 
//可以考虑3个指针逆方向  或者一个一个取下来头插 也可以实现
//逆方向这样来 在1前面加一个NULL 然后让1翻转过来 1原来指向2 现在让它指向这个NULL以此循环就好了
//node1 表示为n1 node2表示为n2  然后让n2指向n1 但是就找不到n3了 所以说再定义一个n3 n3是为了保存下一个
//这样一次之后通过循环 就可以一直逆序 从而得到结果 但是要让1的next指向NULL 所以可以一上来给一个NULL
//第一次让n1指向NULL 然后一起往后迭代 把n2给了n1  n3给了n2  n3的next给了n3 n3为NULL的时候
//n2还能指向n1 n2为NULL的时候就结束了 新链表的头就是n1 这样就反转了

typedef struct ListNode//这里一定要有tydedef
{
	struct ListNode* next;
}ListNode;

struct ListNode* reverseList(struct ListNode* head)
{
	if (head == NULL||head->next==NULL)
	{
		return head;
	}
	struct ListNode* n1 = NULL, * n2 = head, * n3 = head->next;
	while (n2)//因为n2为NULL是结束条件 所以这里以n2就好了
	{
		n2->next = n1;//这里是反转
		n1 = n2;
		n2 = n3;
		if(n3!=NULL)//因为n3为NULL就不需要迭代　所以判断一下
		n3 = n3->next;//因为迭代到最后的时候 n3是NULL 所以 就判断一下 防止代码崩溃
	}
	return n1;//循环结束之后 n1就是头
}

int main()
{
	return 0;
}