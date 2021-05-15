#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>

//移除链表中等于给定值val的所有节点  例如
//输入：1->2->6->3->4->5->6  val=6
//输出：1->2->3->4->5

//因为要删除所有的6 可以找一个查找接口 找到所有的6 然后删除 但是效率低 因为每次都要从头找
//要在O(N)的时间复杂度里面完成的话 就要只写一个代码 先给一个位置cur 从头开始一个一个往后找
//找到6之后 删不了 因为要有前一个 前一个里面有地址 通过地址来删除 前一个是prev 
//让prev的next指向cur的next 这样就把6删了 然后继续迭代的往后走 当cur指向第二个6的时候 
//prev的next指向NULL 这样就把所有的6都删了 还应该考虑第一个就是val 如果是这样的话 
//刚刚的程序就会崩溃 链表写的程序总是崩溃是指针的控制有问题 因为此时cur指向6 但prev还是NULL
//这种情况就属于头删了 需要新指针指向下一个节点 做新的头 

typedef struct ListNode//这里一定要有tydedef
{
	struct ListNode* next;
	int val;
}Node;

struct ListNode* removeElements(struct LIstNode* head, int val)//有返回值 说明是返回新链表的头
	//因为链表的头可能会变 所以返回新链表的头
{
	Node* prev = NULL;
	Node* cur = head;
	while (cur)//经过代码发现 cur为NULL就结束了
	{
		if (cur->val == val)//遇到了val 
		{
			if (cur == head)//说明prev等于NULL 这里要删的值就是头 也就不需要prev起作用
			{
				head = cur->next;//将头指向下一个节点 
				free(cur);
				cur = head;//将cur重新放到头上面 因为之前的被删了
			}
			else
			{
				prev->next = cur->next;
				free(cur);//当最后一个是6的时候 就走到这个else里面来了 这里free之后 cur就是一个野指针了
				cur = prev->next;//这样的话 cur就等于NULL了 
			}
		}
		else//这种就是没有遇到val 所以正常迭代 
		{
			prev = cur;//cur往下走之前 先把它给了prev 
			cur = cur->next;
		}
	}
	return head;
}
//如果刷题找不到问题的话 就在vs里面测试 不需要写链表 这样写 不需要打印 用调试一个一个看就好了
int main()
{
	//创建四个节点的链表 这样就创建好了一个链表
	Node* n1 = (Node*)malloc(sizeof(Node));
	n1->val = 6;
	Node* n2 = (Node*)malloc(sizeof(Node));
	n2->val = 6;
	Node* n3 = (Node*)malloc(sizeof(Node));
	n3->val = 1;
	Node* n4 = (Node*)malloc(sizeof(Node));
	n4->val = 6;
	n1->next = n2;
	n2->next = n3;
	n3->next = n4;
	n4->next = NULL;
	Node* head = removeElements(n1, 6);//n1就是这里的头 6就是我要删掉的值 Node* head来接收返回值
	return 0;
}