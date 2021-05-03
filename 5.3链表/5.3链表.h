#pragma once
#include<stdio.h>
#include<stdlib.h>
#include<assert.h>

//˳���ṹ������Щ �������Ե����� �����Զ�ȥ����  ȱ�ݾ��ǣ��õĲ��� ���˷ѿռ� β��βɾ��ܿ� ʹ��
//ͷ��ͷɾ���� ʱ�临�Ӷ���O(N)  ������Ų����������  ����ʹ������Ϳ��Խ���������  ������������ʣ���������Ͷ��ֲ��ң�
//˳�����������벻��

typedef int SLDataType;

typedef struct SeqList
{
	SLDataType* a;
	int size;
	int capacity;//��¼�ռ�
}SL, SeqList;


//ʵ���ĸ��ӿ�

void SeqListInit(SL* ps);//��ʼ���ṹ��
void SeqListDestory(SL* ps);//���ٽṹ�� ����realloc  malloc������

void SeqListPrint(SL* ps);

void SeqListCheckCapacity(SL* ps);//������� ��������������


//˳���β��
void SeqListPushBack(SL* ps, SLDataType x);//ps��ָ��
//˳���βɾ
void SeqListPopBack(SL* ps);
//˳���ͷ��
void SeqListPushFront(SL* ps, SLDataType x);
//˳���ͷɾ
void SeqListPopFront(SL* ps);


//����λ�õĲ���ɾ��
void SeqListInsert(SL* ps, int pos, SLDataType x);//int pos �ǲ������ݵ�λ��
void SeqListErase(SL* ps, int pos);//ɾ����ʱ�� ����λ�Ӿ�����

//˳������
int SeqListFind(SL* ps, SLDataType x);
//˳�������
int SeqListSort(SL* ps, SLDataType x);
//˳�����ֲ���
int SeqListBinaryFind(SL* ps, SLDataType x);

