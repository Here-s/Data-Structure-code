#define _CRT_SECURE_NO_WARNINGS 1
#include"5.3链表.c"



void TestSeqList1()
{
	SeqList s;//结构体使用的话  第一步应该初始化  不初始化的话  原来是随机值
	SeqListInit(&s);//这里是随机值  
	//下面传的是结构体指针  上面传的是结构体
	SeqListPushBack(&s, 1);//传地址过去，把值传过去
	SeqListPushBack(&s, 2);
	SeqListPushBack(&s, 3);
	SeqListPushBack(&s, 4);
	SeqListPushBack(&s, 5);
	SeqListPushBack(&s, 6);
	SeqListPushBack(&s, 7);
	SeqListPushBack(&s, 8);//看似没问题  但是只给了四个空间  越界了一定有问题(就像查酒驾 不会全查出来) 没越界不一定没有问题 在某个点的时候就会报

	SeqListPrint(&s);//因为是结构体  所以尽量多用指针

	SeqListPopBack(&s);
	SeqListPopBack(&s);//两个PopBack 就是执行两个尾删  删掉最后插入的两个数据
	SeqListPrint(&s);

	SeqListPushFront(&s, -1);
	SeqListPushFront(&s, -2);
	SeqListPushFront(&s, -3);//虽然看起来没错  但是调试的时候 会发现数据和申请的空间大小不符合 空间给的少 相当于越界访问
	//只要是插入 都应该考虑空间扩容
	SeqListPrint(&s);

	SeqListPopFront(&s);
	SeqListPopFront(&s);//删掉两个前面的数据
	SeqListPrint(&s);

	SeqListInit(&s);//销毁动态开辟的空间 局部变量不需要管

	int pos = SeqListFind(&s, 5);
	if (pos != -1)
	{
		SeqListErase(&s, pos);//找到5了 然后再删除 通过位置删除
	}

}
int main()
{
	TestSeqList1();

	return 0;
}