#define _CRT_SECURE_NO_WARNINGS 1
#include"5.12链表3.h"
//数据结构的程序崩了一般是内存问题 更多是越界
//将链表定义为只有一个头指针 就是

//多用调试定位问题

void test01()
{
	//SListNode* phead = NULL;//
	SListNode* pList = NULL;//也可以这样写 只有一个头指针 pList 再申请一个节点，让指针指向节点 以此类推
	//初始化也没问题 不初始化也可以
	//int swap(int a,int b) swap交换a和b 
	//把x和y传给swap 但是不能真正交换x y的值 交换的是a b 的值 传地址过去就好了

	SListPushBack(&pList, 1);//调试的时候 发现没有把值传进去 所以应该使用二级指针
	SListPushBack(&pList, 2);//传的那里 phead的改变 不会影响plist的值 因为不是地址传递
	SListPushBack(&pList, 3);//所以应该传地址
	SListPushBack(&pList, 4);

	SListPrint(pList);//外面调用方式

	//要删五次才行 因为这样就把NULL也删了
	SListPopBack(&pList);
	SListPopBack(&pList);
	SListPopBack(&pList);
	SListPopBack(&pList);
	SListPopBack(&pList);//第五个置空

	SListPrint(pList);//看看删干净了没有

	SListPushFront(&pList, 1);
	SListPushFront(&pList, 2);
	SListPushFront(&pList, 3);
	SListPushFront(&pList, 4);
	SListPushFront(&pList, 5);

	SListPrint(pList);

	SListPopFront(&pList);
	SListPopFront(&pList);
	SListPopFront(&pList);
	SListPopFront(&pList);
	SListPopFront(&pList);
	SListPopFront(&pList);//删六次 就是直接删成空
	SListPrint(pList);

}

void test02()
{
	SListNode* pList = NULL;

	SListPushBack(&pList, 1);
	SListPushBack(&pList, 2);
	SListPushBack(&pList, 3);
	SListPushBack(&pList, 4);

	SListPrint(pList);

	SListNode* pos = SListFind(pList, 3);
	if (pos)
	{
		pos->data = 30;
	}

	SListPrint(pList);
}

int main()
{
	//test01();
	test02();
	return 0;
}