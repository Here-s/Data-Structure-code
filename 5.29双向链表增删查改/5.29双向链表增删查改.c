#define _CRT_SECURE_NO_WARNINGS 1
#include"5.29˫��������ɾ���.h"
//����ϵͳ ���� ���ݿ� ����Ҫ


ListNode* BuyListNode(LTDataType x)//mallocһ���ڵ����
{
	ListNode* node = (ListNode*)malloc(sizeof(ListNode));
	node->next = NULL;//C�����г��˾�̬��ȫ�ֱ��� ��Ҫ��ʼ��ΪNULL ��Ȼ�������ֵ
	node->prev = NULL;
	node->data = x;
	return node;
}

//void ListInit(ListNode** pphead)
//{
//	*pphead = BuyListNode(0);//����һ���ڵ� �����phead�Ͳ��������phead����ͬһ��phead
//	//��Ϊ������֮�� ��ֵ���� ���ǵ�ַ���� Ӧ�ô�����ָ��
//
//	(*pphead)->next = *pphead;//������ȼ� ����������
//	(*pphead)->prev = *pphead;
//}

//�ڶ��ַ���
ListNode* ListInit()
{
	ListNode* phead = BuyListNode(0);
	phead->next = phead;
	phead->prev = phead;
	return phead;
}

void ListPrint(ListNode* phead)//���ڴ�ӡ���� ���Ǻ���ǰ�Ĵ�ӡ��ʽ��һ����
//��ǰ�Ƕ���һ��cur cur��ΪNULL�ͼ����� ΪNULL�ͽ��� ��Ϊ����ѭ������
//ѭ��������Զ��ΪNULL ��һֱ�� ���������һ��ͷ��� ͷ��㲻����Ч�ڵ�
//���ܴ�ӡ���� ��Ϊ��Ĳ�����Чֵ ����cur����ָ��ͷ�ڵ�(phead) Ӧ��ָ��ڶ���
//�ڶ����ڵ������Чֵ�� ��cur������ ��cur=phead��ʱ��ͽ����� 
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

void ListPushBack(ListNode* phead, LTDataType x)
{
	assert(phead);//����һ��
	ListNode* tail = phead->prev;
	ListNode* newnode = BuyListNode(x);
	//���ھ������������ڵ��ϵ phead tail newnode 
	tail->next = newnode;
	newnode->prev = tail;
	newnode->next = phead;
	phead->prev = newnode;
	//���β����O(1)��ʱ�临�Ӷ� 
}

void ListPopBack(ListNode* phead)
{
	assert(phead);
	assert(phead->next);
	ListNode* tail = phead->prev;
	ListNode* tailprev = tail->prev;
	tailprev->next = phead;
	phead->prev = tailprev;
	free(tail);
	tail = NULL;//Ҳ���Բ��ÿ� ��Ϊ���Ǹ��ֲ�����
}

void ListPushFront(ListNode* phead, LTDataType x)//ͷ��
{
	assert(phead);
	ListNode* first = phead->next;
	ListNode* newnode = BuyListNode(x);
	phead->next = newnode;
	newnode->prev = phead;
	newnode->next = first;
	first->prev = newnode;
}

void ListPopFront(ListNode* phead)//ͷɾ
{
	assert(phead);
	assert(phead->next != phead);
	ListNode* first = phead->next;
	ListNode* second = first->next;
	phead->next = second;
	second->prev = phead;
	free(first);
}
