#define _CRT_SECURE_NO_WARNINGS 1
#include"5.12����3.h"
//���==�ڵ㣨��������˼��ȣ�

//����ṹ SListNode* pList ���ָ������ǿյ� ���贴���˼����ڵ� ������SListNode* pList
//pList ��һ��ָ����� �ĸ��ֽ� ����� 0x30 ָ����ǵ�һ����� �Դ����� �ʹ�������
//ָ���һ����� ������������� ��ַ�� 0x30  0x10  0xA0 

//������һ�����
SListNode* BuySListNode(SListDataType x)//�����½��
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
		while (tail->next != NULL)//��������ѭ������֮�� tail���ǿ�ָ�� �����ٶ���һ��prev ��˼��ǰһ��
			//������tail�����ߵ�ʱ�� �Ȱ�ֵ����prev ����ѭ��������ʱ�� prevָ��ľ���ǰһ��
		{
			prev = tail;
			tail = tail->next;
		}
		free(tail);//�ͷŵ�tail֮�� ��û�˵���tail�� ���ԾͲ���Ҫר�Ű�tail�ó�NULL
		prev->next = NULL;

	}
}

//д����֮ǰ�ȷ���һ�� �����ǵ����� ��һ��ָ��ָ������ĵ�һ����� pList Ҫͷ��
//ͷ�� ������ǰ�涯̬����һ�������� Ȼ�����¿��ٵĽ��ָ���һ�� ��� 
//��ԭ����ָ���¿��ٵĽ�� 

//

void SListPushFront(SListNode** pphead, SListDataType x)//ͷ��
//��������� 1 �н��  2 �޽��
//�����������Ϊ��  ����һ���½�� ��������ָ��ԭ��pListָ��ĵط� ��pListָ���´����Ľ��
{
	SListNode* newNode = BuySListNode(x);
	newNode->next = *pphead;//pphead��ľ���pList �ĵ�ַ ��һ�����ĵ�ַ �ʹ�����newNode����
	*pphead = newNode;
}

void SListPopFront(SListNode** pphead)//ͷɾ
//�ж�һ�� 1 ����ǿ� �ǾͲ���ɾ   2 ���ֻ��һ�����   3 �����һ�����ϵĽ��
//���ܰѽ�㶼Ū����  ������� ���ֵڶ��ֺ͵����ֿ��Ժ���һ��
{
	if (*pphead == NULL)
	{
		return;
	}
	else//-> ��* �ǽṹ������ú�ָ�������
		//����ָ�����ȼ����� ����������ָ������� ��ֹ�������
		//������Ƕ����� ���ѵ�һ���ͷŵ���ʱ�� ���Ҳ���������� ��û�취ɾ����
		//���Ի�Ӧ����pListָ����һ����� ��ֹ������������� 
		//�ܶ���������� Free ������������ĸ�û   ������һ���������⣺freeָ�� ������ط� �ͷ�
		//�ͷŵ���ָ�뻹���ڴ� �ͷŵ���ָ��ָ����ڴ� free�ǰ�����ڴ��ʹ��Ȩ����ϵͳ ͨ��������ռ��ó����ֵ
		//�ڴ�й¶��malloc�Ŀռ�û�ͷţ���ָ�붪�˻����ڴ涪�� Ӧ����ָ�붪�� ��Ϊָ������ڵĻ� �����ͷ�
		//�ڴ��ǲ��ᶪ�� ��Ϊ�ڴ�һֱ�����λ�� 
		
		//��������˵�� ���һ��������һ�� ֱ���ͷŵ�һ�����Ļ� ��һ�����ĵ�ַ�Ǵ��ڵ�һ������ ���Ҳ���
		//�Ҳ�����һ�����ĵ�ַ�� �������ͷ�֮ǰһ��Ҫ�������λ�õĵ�ַ ��һ��ָ��ȥ������һ���ڵ�ĵ�ַ
		//Ȼ�����ͷ�����ռ� Ҳ����ֱ�Ӱ�pListָ��ڶ������ ���ֻʣһ����� ������Ȼû���� ���Կ��Ժ͵ڶ���һ����
	{
		SListNode* next = (*pphead)->next;//*pphead����pList
		free(*pphead);
		*pphead = next;
	}
}

//���������
SListNode* SListFind(SListNode* phead, SListDataType x)//����Ҫ������ָ�� ��Ϊ���治��ı�ͷ 
//Ҫ�ı�ͷ ��Ҫ����ַ
{
	SListNode* cur = phead;
	while (cur != NULL)//Ҳ���Բ�дNULL
	{
		if (cur->data == x)
		{
			return cur;
		}
		cur = cur->next;
	}
	return NULL;//˵��û�ҵ� ���Է���NULL
}

void SListInsertAfter(SListNode* pos, SListDataType x)//�м���� 
{
	assert(pos);//����һ�����λ���Ƿ�Ϊ�� ���Ϊ�� �Ͳ��ò���
	SListNode* newNode = BuySListNode(x);
	newNode->next = pos->next;
	pos->next = newNode;
}

void SListEraseAfter(SListNode* pos)//�м�ɾ��
{
	assert(pos);
	if (pos->next)
	{
		SListNode* next = pos->next;
		SListNode* nextnext = next->next;
		pos->next = nextnext;
		free(next);
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
	printf("NULL\n");
}

