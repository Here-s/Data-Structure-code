#define _CRT_SECURE_NO_WARNINGS 1
#include"5.12����3.h"
//���ݽṹ�ĳ������һ�����ڴ����� ������Խ��
//��������Ϊֻ��һ��ͷָ�� ����

//���õ��Զ�λ����

void test01()
{
	//SListNode* phead = NULL;//
	SListNode* pList = NULL;//Ҳ��������д ֻ��һ��ͷָ�� pList ������һ���ڵ㣬��ָ��ָ��ڵ� �Դ�����
	//��ʼ��Ҳû���� ����ʼ��Ҳ����
	//int swap(int a,int b) swap����a��b 
	//��x��y����swap ���ǲ�����������x y��ֵ ��������a b ��ֵ ����ַ��ȥ�ͺ���

	SListPushBack(&pList, 1);//���Ե�ʱ�� ����û�а�ֵ����ȥ ����Ӧ��ʹ�ö���ָ��
	SListPushBack(&pList, 2);//�������� phead�ĸı� ����Ӱ��plist��ֵ ��Ϊ���ǵ�ַ����
	SListPushBack(&pList, 3);//����Ӧ�ô���ַ
	SListPushBack(&pList, 4);

	SListPrint(pList);//������÷�ʽ

	//Ҫɾ��β��� ��Ϊ�����Ͱ�NULLҲɾ��
	SListPopBack(&pList);
	SListPopBack(&pList);
	SListPopBack(&pList);
	SListPopBack(&pList);
	SListPopBack(&pList);//������ÿ�

	SListPrint(pList);//����ɾ�ɾ���û��

	SListPushFront(&pList, 1);
	SListPushFront(&pList, 2);
	SListPushFront(&pList, 3);
	SListPushFront(&pList, 4);
	SListPushFront(&pList, 5);

	SListPrint(pList);

	SListPopFront(&pList);
	SListPopFront(&pList);
	SListPopFront(&pList);
	SListPopFront(&pList);
	SListPopFront(&pList);
	SListPopFront(&pList);//ɾ���� ����ֱ��ɾ�ɿ�
	SListPrint(pList);

}

void test02()
{
	SListNode* pList = NULL;

	SListPushBack(&pList, 1);
	SListPushBack(&pList, 2);
	SListPushBack(&pList, 3);
	SListPushBack(&pList, 4);

	SListPrint(pList);

	SListNode* pos = SListFind(pList, 3);
	if (pos)
	{
		pos->data = 30;
	}

	SListPrint(pList);
}

int main()
{
	//test01();
	test02();
	return 0;
}