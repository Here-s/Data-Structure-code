
//#define _CRT_SECURE_NO_WARNINGS 1
#include"5.10����.h"
//���ݽṹ�ĳ������һ�����ڴ����� ������Խ��
//��������Ϊֻ��һ��ͷָ�� ����


//���õ��Զ�λ����

int main()
{
	//SListNode* phead = NULL;//
	SListNode* pList = NULL;//Ҳ��������д ֻ��һ��ͷָ�� pList ������һ���ڵ㣬��ָ��ָ��ڵ� �Դ�����
	//��ʼ��Ҳû���� ����ʼ��Ҳ����
	SListPushBack(pList, 1);
	SListPushBack(pList, 2);
	SListPushBack(pList, 3);
	SListPushBack(pList, 4);

	SListPrint(pList);//������÷�ʽ
	return 0;
}