#pragma once
#include<stdio.h>
#include<stdlib.h>
#include<assert.h>

//顺序表结构就是这些 保持线性的特性 可以自动去增容  缺陷就是：用的不好 会浪费空间 尾插尾删会很快 使用
//头插头删不快 时间复杂度是O(N)  代价在挪动数据上面  但是使用链表就可以解决这个问题  链表不能随机访问（不能排序和二分查找）
//顺序表和链表互相离不开

typedef int SLDataType;

typedef struct SeqList
{
	SLDataType* a;
	int size;
	int capacity;//记录空间
}SL, SeqList;


//实现四个接口

void SeqListInit(SL* ps);//初始化结构体
void SeqListDestory(SL* ps);//销毁结构体 销毁realloc  malloc的数据

void SeqListPrint(SL* ps);

void SeqListCheckCapacity(SL* ps);//检查容量 容量不够就扩容


//顺序表尾插
void SeqListPushBack(SL* ps, SLDataType x);//ps是指针
//顺序表尾删
void SeqListPopBack(SL* ps);
//顺序表头插
void SeqListPushFront(SL* ps, SLDataType x);
//顺序表头删
void SeqListPopFront(SL* ps);


//任意位置的插入删除
void SeqListInsert(SL* ps, int pos, SLDataType x);//int pos 是插入数据的位置
void SeqListErase(SL* ps, int pos);//删除的时候 给个位子就行了

//顺序表查找
int SeqListFind(SL* ps, SLDataType x);
//顺序表排序
int SeqListSort(SL* ps, SLDataType x);
//顺序表二分查找
int SeqListBinaryFind(SL* ps, SLDataType x);

