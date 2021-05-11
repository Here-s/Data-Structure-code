#define _CRT_SECURE_NO_WARNINGS 1
#include"5.11����2.h"
//���==�ڵ㣨��������˼��ȣ�

//����ṹ SListNode* pList ���ָ������ǿյ� ���贴���˼����ڵ� ������SListNode* pList
//pList ��һ��ָ����� �ĸ��ֽ� ����� 0x30 ָ����ǵ�һ����� �Դ����� �ʹ�������
//ָ���һ����� ������������� ��ַ�� 0x30  0x10  0xA0 

//������һ�����
SListNode* BuySListNode(SListDataType x)
{
	SListNode* newNode = (SListNode*)malloc(sizeof(SListNode));
	if (newNode == NULL)
	{
		printf("������ʧ��\n");
		exit(-1);
	}
	newNode->data = x;
	newNode->next = NULL;

	return newNode;
}

void SListPushBack(SListNode** pphead, SListDataType x)//β��  ��Ϊ�Ƕ���ָ�� ��������ʹ�õ�ʱ��
//ʹ�õ�ʱ�� ��Ҫ������һ��
{
	//�������ȱ�� β��һ����� Ӧ�ö�̬����һ����� ���뵽���һ�����ĺ��� ���ñ����ҵ�β
	SListNode* newNode = BuySListNode(x);//��������������ĺ��� �Ϳ��Դ���ʡ������
	if (*pphead == NULL)
	{
		*pphead = newNode;
	}
	else//��β
	{
		SListNode* tail = *pphead;
		while (tail->next != NULL)//�����Ƿ��ʿ�ָ��
		{
			tail = tail->next;//������ѭ������֮�� ָ��ľ������һ����ָ��
		}
		//�ҵ�β�� ���ܲ��������� �����ڴ� ˳������Ч
		tail->next = newNode;
	}
}

void SListPopBack(SListNode** pphead)// Ӧ�ÿ���������� 1 ��������ǿ� �Ͳ���Ҫɾ��
//�ڶ������ �������ֻ��һ����� �Ͳ�Ӧ����д��������   ��ȫɾ��֮�� Ҫ��pListָ����Ϊ�� �Ͱ������ɿ�������
//��������� һ�����Ͻ�� ����Ҫ������ָ����
{
	//��һ�����
	if (*pphead == NULL)//��Ϊ�ǿ�ָ�� ���Բ���Ҫɾ�� ֱ�ӷ���
	{
		return;
	}
	else if ((*pphead)->next == NULL)//*pphead��pList��ֵ ָ���һ����� �ж�֮�� ֻ��һ�����
	{
		//��Ϊֻ��һ����� ����Ҫɾ��Ҳ����pList��ֵ ����ֻ����һ���ÿվ�����
		*pphead = NULL;
	}
	else//��ӵ���� Ҫɾ��β ��Ϊÿ�����ֶ����ܱ仯 tail��next�����null�Ļ� �Ͱ�����ͷ�
		//����ǰ���Ǹ��ͱ���µ�β�� ����ٱ����Ļ� ǰ���һ����ָ����Ұָ�� �ͳ�Ϊ�Ƿ�������
	{
		SListNode* tail = *pphead;
		SListNode* prev = NULL;
		while (tail->next!=NULL)//��������ѭ������֮�� tail���ǿ�ָ�� �����ٶ���һ��prev ��˼��ǰһ��
			//������tail�����ߵ�ʱ�� �Ȱ�ֵ����prev ����ѭ��������ʱ�� prevָ��ľ���ǰһ��
		{
			prev = tail;
			tail = tail->next;
		}
		free(tail);//�ͷŵ�tail֮�� ��û�˵���tail�� ���ԾͲ���Ҫר�Ű�tail�ó�NULL
		prev->next = NULL;

	}
}

void SListPrint(SListNode* phead)//ͨ������ ���մ�����ֵ ������ֵҲ����pList�ĵ�ַ
{
	//����Ͳ���Ҫassert����һ���Ƿ�Ϊ���� ��Ϊ�����ܱ�����Ϊ��
	//Ϊ�˷�����һ�λ��ܱ���������� ��Ӧ������д����
	SListNode* cur = phead;//cur�Ƕ��嵱ǰλ�� ��phead����cur  ��˵��curҲָ���һ�����
	while (cur != NULL)//�������NULL�Ļ� ��˵���ǿ����� �Ͳ���ȥ������ǵĻ� �Ϳ��Դ�ӡ�����ֵ
	{
		printf("%d ", cur->data);//cur��һ��SListNode* ���� ��ǰ�������±� ����Ӧ��ָ������һ����ַ
		cur = cur->next;//����һ����ַ����cur �Ϳ���ͨ��ѭ����ʽ���� �����һ����ʱ�� cur����0 �ͽ�����
		//������Ǳ�������
	}
}

