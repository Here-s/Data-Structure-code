#define _CRT_SECURE_NO_WARNINGS 1
#include"5.30˫��������ɾ���2.h"


void TestList01()
{
	//ListNode* phead = NULL;//����һ������Ĵ�ͷ�ڵ�
	//ListInit(&phead);//�����ܴﵽ������Ч�� ����ָ�� Ȼ������һ�ߴ����ڵ�
	//�����ö���ָ����Խ��������� ���ǻ��еڶ��ַ���

	//�ڶ��ַ��� ��ֵ�ı��� �����ٷ����µ�ֵ�ͺ��� ���Ƿ����µ�ͷ
	ListNode* phead = ListInit();//��Ϊ���ص��Ǵ����������ͷ ���Բ���Ҫ��ָ��
	//�������Ƿ����µ�ͷָ�� �Ͳ���Ҫ������ô�鷳��
	ListPushBack(phead, 1);
	ListPushBack(phead, 2);
	ListPushBack(phead, 3);
	ListPushBack(phead, 4);

	ListPrint(phead);

	ListPopBack(phead);
	ListPopBack(phead);
	ListPopBack(phead);

	ListPrint(phead);

	ListPushFront(phead, 1);
	ListPushFront(phead, 2);
	ListPushFront(phead, 3);
	ListPushFront(phead, 4);
	ListPushFront(phead, 5);

	ListPrint(phead);

	ListPopFront(phead);
	ListPopFront(phead);
	ListPopFront(phead);
	ListPopFront(phead);
	ListPopFront(phead);

	ListPrint(phead);

}

void TestList02()
{
	ListNode* phead = ListInit();
	ListPushBack(phead, 1);
	ListPushBack(phead, 2);
	ListPushBack(phead, 3);
	ListPushBack(phead, 4);

	ListPrint(phead);

	ListNode* pos = ListFind(phead, 3);
	pos->data = 300;
	ListInsert(pos, 30);
	ListPrint(phead);

	ListErase(pos);
	ListPrint(phead);

}

int main()
{
	//TestList01;
	TestList02;
	return 0;
}