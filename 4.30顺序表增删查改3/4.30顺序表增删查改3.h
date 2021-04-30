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


//实现四个接口

void SeqListInit(SL* s);//初始化结构体
//顺序表尾插
void SeqListPushBack(SL* ps, SLDataType x);//ps是指针
//顺序表尾删
void SeqListPopBack(SL* ps);
//顺序表头插
void SeqListPushFront(SL* ps, SLDataType x);
//顺序表头删
void SeqListPopFront(SL* ps);


//任意位置的插入删除
void SeqListInsert(struct SL* ps, int pos, SLDataType x);//int pos 是插入数据的位置
void SeqListErase(struct SL* ps, int pos);//删除的时候 给个位子就行了
