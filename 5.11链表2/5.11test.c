#define _CRT_SECURE_NO_WARNINGS 1
#include"5.11����2.h"
//���ݽṹ�ĳ������һ�����ڴ����� ������Խ��
//��������Ϊֻ��һ��ͷָ�� ����


//���õ��Զ�λ����

int main()
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
	SListPopBack(&pList);

	return 0;
}