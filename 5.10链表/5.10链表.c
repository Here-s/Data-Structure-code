#define _CRT_SECURE_NO_WARNINGS 1
#include"5.10����.h"
//���==�ڵ㣨��������˼��ȣ�

//����ṹ SListNode* pList ���ָ������ǿյ� ���贴���˼����ڵ� ������SListNode* pList
//pList ��һ��ָ����� �ĸ��ֽ� ����� 0x30 ָ����ǵ�һ����� �Դ����� �ʹ�������
//ָ���һ����� ������������� ��ַ�� 0x30  0x10  0xA0 


void SListPushBack(SListNode* phead, SListDataType x)//β��
{
	//�������ȱ�� β��һ����� Ӧ�ö�̬����һ����� ���뵽���һ�����ĺ��� ���ñ����ҵ�β
	if (phead == NULL)
	{
		SListNode* newNode = (SListNode*)malloc(sizeof(SListNode));
		if (newNode == NULL)
		{
			printf("������ʧ��\n");
			exit(-1);
		}
		newNode->data = x;
		newNode->next = NULL;//����һ��ָ����Ϊ�� ��ֹ�Ƿ�����
		phead = newNode;
	}
	else
	{
		SListNode* tail = phead;
		while (tail->next != NULL)//�����Ƿ��ʿ�ָ��
		{
			tail = tail->next;//������ѭ������֮�� ָ��ľ������һ����ָ��
		}
		//�ҵ�β�� ���ܲ��������� �����ڴ� ˳������Ч
	}
}

void SListPrint(SListNode* phead)//ͨ������ ���մ�����ֵ ������ֵҲ����pList�ĵ�ַ
{
	//����Ͳ���Ҫassert����һ���Ƿ�Ϊ���� ��Ϊ�����ܱ�����Ϊ��
	//Ϊ�˷�����һ�λ��ܱ���������� ��Ӧ������д����
	SListNode* cur = phead;//cur�Ƕ��嵱ǰλ�� ��phead����cur  ��˵��curҲָ���һ�����
	while (cur != NULL)//�������NULL�Ļ� ��˵���ǿ����� �Ͳ���ȥ������ǵĻ� �Ϳ��Դ�ӡ�����ֵ
	{
		printf("%d->", cur->data);//cur��һ��SListNode* ���� ��ǰ�������±� ����Ӧ��ָ������һ����ַ
		cur = cur->next;//����һ����ַ����cur �Ϳ���ͨ��ѭ����ʽ���� �����һ����ʱ�� cur����0 �ͽ�����
		//������Ǳ�������
	}
}
