#pragma once
#include<stdio.h>
#include<stdlib.h>

typedef int SLDataType;

typedef struct SeqList
{
	SLDataType* a;
	int size;
	int capacity;
}SL,SeqList;


//ʵ���ĸ��ӿ�

void SeqListInit(SL* s);//��ʼ���ṹ��
//˳���β��
void SeqListPushBack(SL* ps, SLDataType x);//ps��ָ��
//˳���βɾ
void SeqListPopBack(SL* ps);
//˳���ͷ��
void SeqListPushFront(SL* ps, SLDataType x);
//˳���ͷɾ
void SeqListPopFront(SL* ps);


//����λ�õĲ���ɾ��
void SeqListInsert(struct SL* ps, int pos, SLDataType x);//int pos �ǲ������ݵ�λ��
void SeqListErase(struct SL* ps, int pos);//ɾ����ʱ�� ����λ�Ӿ�����
