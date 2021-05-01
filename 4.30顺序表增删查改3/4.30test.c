#define _CRT_SECURE_NO_WARNINGS 1
#include"4.30顺序表增删查改3.c"



//接口应该写一个测一个

//测试头尾插入删除  调用接口  规范接口
void TestSeqList1()
{
	SeqList s;//结构体使用的话  第一步应该初始化  不初始化的话  原来是随机值
	SeqListInit(&s);//这里是随机值  
	//下面传的是结构体指针  上面传的是结构体
	//SeqListPushBack(&s, 1);//传地址过去，把值传过去
	//SeqListPushBack(&s, 2);
	//SeqListPushBack(&s, 3);
}
int main()
{
	TestSeqList1();
	return 0;
}