#pragma once
#include<stdio.h>
#include<stdlib.h>

//����
//�߼����������� �������ϲ��������� ����һ��һ���������� ��ӳ��Ϳ��Լӳ� ����̾ͼ���
//����������˳����ȱ������Ƶ� ����ľ���˳����ȱ�� ��Ϊ˳����Ƕ������� ����ɿռ��˷�

//��ʽ�ṹ ����һ������ ������һ���ռ���� 
//ÿ�����������һ��ָ�� ָ����һ������ ����������ϵ�� �������һ������ָ��� ������֪��������
//ͷ��Ļ� ����һ���������� �Ͳ���Ҫ���������� ���ָ��ָ����һ���������� ����һ���ṹ��


typedef int SListDataType;
//��� ������������  ��һ�����ݿ�һ�����
typedef struct SListNode//����һ��Ҫ��tydedef
{
	SListDataType data;
	struct SListNofde* next;//ָ����һ���ṹ�� �γ���ʽ ÿ����㶼��һ����ַ ���ǵ�ַ�ǲ�һ����
	//�������һ��λ�õĵ�ַ �����ҵ���һ��λ�� Ȼ��һ��һ���ҵ���� ֱ�����һ����ָ�� ��ʾ����
	//�����˳���Ļ� ���˷ѿռ� ����Ҫ���� Ҫһ����һ��
}SListNode;

//���Ͽ���������д��
//struct SList
//{
//   SListNode* head;//ͷָ��
//   SListNode* tail;//βָ��  ������ȡ����ϵ
//�������ֽṹ�����ܺܺõ������Ժ�����
//};

//�ض�������Ϊ ͷ�ļ�ִ����֮�� ����.c  ��test.c ������չ��һ�� ������.c ��ֱ�����һ��.o 
//���ϵ�ʱ�� ����������PushBack ���Իᱨ�� ���Էŵ�.c����

//������������ɾ��� ����Ҫ��ʼ���� ��Ϊֻ��һ��ͷָ��ָ��һ���ڵ� û�ڵ� ͷָ��ָ����ǿյ� ���Բ���Ҫ��ʼ��


void SListPushBack(SListNode** pphead, SListDataType x);//pphead��ʾ����ָ��

void SListPopBack(SListNode* phead);//βɾ ����Ҫ��ֵ��

void SListPushFront(SListNode* phead, SListDataType x);//ͷ��

void SListPopFront(SListNode* phead);//ͷɾ

void SListPrint(SListNode* phead);//������ӡ ���ﲻ��Ҫ����ָ�� ��Ϊ������ֻ���� ����Ҫ�ı�ֵ

void SListSize(SListNode* phead);//��������