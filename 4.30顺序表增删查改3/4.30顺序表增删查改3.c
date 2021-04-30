#define _CRT_SECURE_NO_WARNINGS 1
#include"4.30顺序表增删查改3.h"





//void SeqListInit(SL s)//这里就是初始化
//{
//	s.size = 0;
//	s.a = NULL;
//	s.capacity = 0;// capacity是空间  这里表示初始化为0
//}
void SeqListInit(SL* ps)//这里是初始化的第二种方法
{
	ps->a = (SLDataType*)malloc(sizeof(SLDataType) * 4);//这里是开辟四个空间
	if (ps->a == NULL)
	{
		printf("申请内存失败\n");
		exit(-1);//结束掉程序
	}
	ps->size = 0;
	ps->capacity = 4;
}



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
