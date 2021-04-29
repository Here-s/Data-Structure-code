#pragma once //头文件里面放的是声明 方便查看有哪些接口


//顺序表，有效数据在数组中必须是连续的

//动态顺序表设计（和静态表差不多，就是大小可变）
typedef int sldatatype;

struct seqlist
{
	sldatatype* a;//因为是动态表 所以就不需要常量10了  不是给数组了 而是在这里给个指针
	int size;//有效数据的个数
	int capacity;//这里是表示容量空间大小   因为是动态的 所以加capacity 这里是容量的意思  也就是空间如果不够的话 就扩容

}; seqlist

//基本增删查改接口 
//顺序表初始化
void SeqListInit(SeqList* psl, size_t capacity);
//顺序表销毁
void SeqListDestory(SeqList* psl);
//顺序表打印
void SeqListPrint(SeqList* psl);
//检查空间，如果满了，进行增容
void ChenkCapacity(SeqList* psl);
//顺序表尾插
void SeqListPushBack(SeqList* psl, SLDataType x);
//顺序表尾删
void SeqListPopBack(SeqList* psl);
//顺序表头插
void SeqListPushFront(SeqList* psl, SLDataType x);
//顺序表头删
void SeqListPopFront(SeqList* psl);
//顺序表查找
int SeqListFind(SeqList* psl, SLDataType x);
//顺序表在pos位置插入x
void SeqListInsert(SeqList* psl, size_t pos, SLDataType x);
//顺序表删除pos位置的值
void SeqListErase(SeqList* psl, size_t pos);




//这里是静态顺序表设计（不能按需索取） 静态就是固定大小  以后更多的是动态数据表
typedef int sldatatype;
#define N 10
struct list
{
	sldatatype a[N];//顺序表是一个数组，所以这里定义一个数组
	int size;//这里是表示有效数据
};

//void listpushback(struct list* ps, int x);//插入数据  但是这样写有很大的问题，比如想存double，那么就得把结构体中
//把结构体中的int改成double  那么后来的插入删除都得改  就得用typedef来解决 所以就变成后面这种写法

void listpushback(struct list* ps, sldatatype x);//以后想写double的话 就把typedef int sldatatype的int改成double就行了

//如果想把结构体当中的数组改成20 这样又都得改 所以可以使用#define来定义 就是将结构体当中的数组大小使用
//使用#define定义为10  这样想改的话 直接改#define定义的N的大小就行了

//PushBack是尾部插入  PopBack也是一种尾部删除  PushFront是头上的插入 PopFront是头部的删除  使用insert tail 也可以 

//动态顺序表使用malloc和relloc解决
