#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>

//�ཻ����
//��дһ������ �ҵ������������ཻ����ʼ�ڵ�
//���磺A: a1->a2->c1->c2->c3   B: b1->b2->b3->c1->c2->c3
//�ڽڵ�c1��ʼ�ཻ

//һ���ڵ㲻��ָ�������ڵ�  ����ڵ���ָ��һ���ڵ� �ж����������Ƿ��ཻ ���һ��
//�Ϳ����һ��һ��һ�������� ����β�ڵ�һ�������� �������������Ͳ�һ��
//�����������û�н��� �ͷ���NULL ��������O(n)ʱ�临�Ӷ� �ҽ���O(1)�ڴ� 
//���ǲ��ܿ�����Ŀռ����

//��ֱ��ķ������ǰ�A��ÿ���ڵ㶼ȥb������һ�� ������������ཻ ��ôa�����ֵ�Ͳ�������b����
//��Ҫ��ֵȥ�� Ӧ���ýڵ�ĵ�ַȥ�� �����������ȥ�Ļ� ʱ�临�ӶȲ������� ����������ȶ���n�Ļ�
//ʱ�临�ӶȾ���O(n^2) ���Կ������la ��lb la��lb �ֱ�����������ĳ��� la��һ����һ����
//���ó����������ǵĲ�ಽ  ����la��5 lb��6 6-5=1 ����lb����һ�� ��ʱ�����ཻ�������� 
//Ȼ��ͬʱ�� ��ȵĵ�һ�����������Ľ��� ����c1 ����ʱ�临�ӶȾ���O(n) 
//

typedef struct ListNode//����һ��Ҫ��tydedef
{
	struct ListNode* next;
	int val;
}ListNode;
struct ListNode* getIntersectionNode(struct ListNode* headA, struct ListNode* headB)
{
	ListNode* curA = headA;
	int la = 0;
	while (curA)//����������ʱ�� �Ͳ���������Ҳ���ͷ��
	{
		++la;
		curA = curA->next;
	}
	ListNode* curB = headB;
	int lb = 0;
	while (curB)//����������ʱ�� �Ͳ���������Ҳ���ͷ��
	{
		++lb;
		curB = curB->next;
	}
	//�����������������ĳ�����
	//��������� a��b�� b��a�� a bһ����
	ListNode* longList = headA;
	ListNode* shortList = headB;//Ĭ��a��b��
	if (lb > la)//˵��ǰ���Ԥ�跴��
	{
		longList = headB;
		shortList = headA;
	}
	//���ھͲ���Ҫ������
	int gap = abs(la - lb);//��� abs���������ֵ
	while (gap--)
	{
		longList = longList->next;
	}
	//�ߵ������˵��һ������ ��һ��Ϊ׼������
	while (longList)
	{
		if (longList == shortList)
		{
			return longList;
		}
		longList = longList->next;
		shortList = shortList->next;
	}
	//û�ཻ ˵��ΪNULL
	return NULL;
}
int main()
{
	return 0;
}