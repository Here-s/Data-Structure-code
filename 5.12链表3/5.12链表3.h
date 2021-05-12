#pragma once
#include<stdio.h>
#include<stdlib.h>

//链表
//逻辑上是连续的 但物理上不是连续的 可以一个一个扣起来的 想加长就可以加长 想减短就减短
//链表就是针对顺序表的缺点来设计的 补足的就是顺序表的缺点 因为顺序表是二倍增容 会造成空间浪费

//链式结构 插入一个数据 就申请一个空间出来 
//每个数据里面加一个指针 指向下一个数据 这样就有联系了 但是最后一个让其指向空 这样就知道结束了
//头插的话 申请一个结点就行了 就不需要插入数据了 这个指针指向下一个数据类型 还是一个结构体


typedef int SListDataType;
//结点 链表中这样叫  存一个数据开一个结点
typedef struct SListNode//这里一定要有tydedef
{
	SListDataType data;
	struct SListNofde* next;//指向下一个结构体 形成链式 每个结点都有一个地址 但是地址是不一样的
	//存的是下一个位置的地址 方便找到下一个位置 然后一个一个找到结点 直到最后一个空指针 表示结束
	//相对于顺序表的话 不浪费空间 不需要增容 要一个给一个
}SListNode;

//书上可能是这样写的
//struct SList
//{
//   SListNode* head;//头指针
//   SListNode* tail;//尾指针  这样来取得联系
//但是这种结构并不能很好的适配以后做题
//};

//重定义是因为 头文件执行完之后 会在.c  和test.c 当中再展开一次 这两个.c 会分别生成一个.o 
//最后合的时候 发现有两个PushBack 所以会报错 所以放到.c当中

//接下来就是增删查改 不需要初始化了 因为只有一个头指针指向一个节点 没节点 头指针指向的是空的 所以不需要初始化



void SListPushBack(SListNode** pphead, SListDataType x);//pphead表示二级指针

void SListPopBack(SListNode** pphead);//尾删 不需要给值了

void SListPushFront(SListNode** phead, SListDataType x);//头插

void SListPopFront(SListNode** phead);//头删

void SListPrint(SListNode* phead);//用来打印 这里不需要二级指针 因为这里是只读的 不需要改变值

void SListSize(SListNode* phead);//遍历计数

//查找返回的是结点的指针 查就是改 因为返回的是结点的指针 所以可以直接该
SListNode* SListFind(SListNode* phead, SListDataType x);

//中间插入
void SListInsertAfter(SListNode* pos, SListDataType x);
//也可以在中间位置插入 比如说在第二个结点前面插入一个20 单链表在当前位置之后插入 因为如果要在前面插的话
//就得先知道前一个结点的位置 通过指针插入一个值 所以很麻烦 所以传参很麻烦 所以应该在当前位置之后插入
//就改一下指向就好了 要在前面插入的话 就是用双向链表跟方便 给一个值20(newNode) 
//应该先用一个指针next保存后面结点的地址 然后再把这个地方的指针指向newNode newNode->next指向next
//或者这样写 newNode->next=pos->next    pos->next=newNode   pos是当前位置 有严格的先后位置

void SListEraseAfter(SListNode* pos, SListDataType x);//中间删除 删除的不是pos 而是pos后面的位置
//单链表在实际中不如双向链表好用 OJ里面基本都是单链表 缺陷大 方便出题 哈希图里面是单链表
//把删的这个位置的前一个的指针指向这个位置的下一个位置 就能free这个位置了
