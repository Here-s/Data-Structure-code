#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>
//����Ҫע����ѭ��


//����һ�����ͷָ��ListNode* pHead����һ��ֵx����дһ�δ��뽫����С��x�Ľڵ�����
//������֮ǰ���Ҳ��ܸı�ԭ��������˳�򣬷����������к�������ͷָ��

//��˼�� ����һ��������������4->2->5->3->6->9->0 ��3Ϊ�ֽ��� С��4�ķ���ǰ��
//����4�ķ��ں��治�ܸı����˳�� ���˳���������֮����2->3->0->4->5->6->9

//���Զ����������� ����β��һ����lessList ����Ϊͷָ�� �ڱ�λ Ȼ���ٸ�һ��
//��һ��greaterList�ڱ�λ ����ԭ���� �ѱ�4С��β�嵽lessList���� �Ѵ��β�嵽
//β�嵽greaterList���� Ȼ���ٰ�������������� �����ڱ�λ��ͷָ�������

typedef struct ListNode//����һ��Ҫ��tydedef
{
	struct ListNode* next;
	int val;
}ListNode;
struct ListNode* partition(struct ListNode* pHead, int x)
{
	//����������ͷ��β
	ListNode* lessHead;
	ListNode* lessTail;
	//���峤�����ͷ��β
	ListNode* greaterHead;
	ListNode* greaterTail;
	//��ͷ�����
	lessHead = lessTail = (ListNode*)malloc(sizeof(ListNode));
	greaterHead = greaterTail = (ListNode*)malloc(sizeof(ListNode));
	lessHead->next = greaterHead->next = NULL;//����ͷΪNULL
	ListNode* cur = pHead;
	//���������������֮�� ���һ������x���� ��ָ���������Ǹ�С��x���� ����С��x������
	//��������һ����Ҳָ���Ǹ����������Ǹ��� �ͳ��������������� ���γ��˴��� Ҳ���Ǳ����ѭ����
	while (cur)
	{
		if (cur->val < x)
		{
			lessTail->next = cur;
			lessTail = lessTail->next;
		}
		else
		{
			greaterTail->next = cur;
			greaterTail = greaterTail->next;
		}
		cur = cur->next;
	}
	lessTail->next = greaterHead->next;
	greaterTail->next = NULL;
	ListNode* list = lessHead->next;
	free(lessHead);
	free(greaterHead);
	return list;
}

int main()
{
	return 0;
}