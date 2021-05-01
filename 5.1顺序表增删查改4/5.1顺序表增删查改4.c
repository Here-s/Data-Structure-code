//#define _CRT_SECURE_NO_WARNINGS 1
#include"5.1顺序表增删查改4.h"


void SeqListInit(SL* ps)//这里是初始化的第二种方法
{
	ps->a = (SLDataType* )malloc(sizeof(SLDataType) * 4);//这里是开辟四个空间
	if (ps->a == NULL)
	{
		printf("申请内存失败\n");
		exit(-1);//结束掉程序
	}
	ps->size = 0;
	ps->capacity = 4;
}

//扩容接口
void SeqListCheckCapacity(SL* ps)
{
	//扩容代码
	if (ps->size >= ps->capacity)
	{
		ps->capacity *= 2;//C语言中的扩容  如果每次增一个 会导致频繁增容 可以一次增几个 通用的方案是增2倍 越到后面增的越大 如果要换位置增容的话
			//会拷贝当前的数据到某块 然后再增容  但是会浪费一定的空间 
		ps->a = (SLDataType*)realloc(ps->a, sizeof(SLDataType) * ps->capacity);//realloc  把原来的空间交给它 然后再增容到需要的空间
		if (ps->a == NULL)
		{
			printf("增容失败\n");
			exit(-1);
		}
	}

}


//顺序表尾插   
void SeqListPushBack(SL* ps, SLDataType x)//ps是指针
{
	assert(ps);
	//插入这里更重要的一个逻辑是 判断 如果满了需要增容 
	ps->a[ps->size] = x;//那数组来说  size就是最后一个数的后面那个数的下标  所以就把x放入最后一个数的后面
	ps->size++;//在让size加一个数  又变成一个下标
}

void SeqListPrint(SL* ps)
{
	assert(ps);
	for (int i = 0; i < ps->size; ++i)
	{
		printf("%d ", ps->a[i]);
	}
	printf("\n");
}



//顺序表尾删
void SeqListPopBack(SL* ps)
{
	assert(ps);
	//ps->a[ps->size - 1] = 0;//这句代码没什么意义 因为如果这个位置就是0的话 也不影响
	ps->size--;
}

//顺序表头插  其实就是挪动数据 把数据整体往后挪 是从后往前一个一个挪 如果插入一个 那么就整体往后挪一个 
void SeqListPushFront(SL* ps, SLDataType x)
{
	assert(ps);
	//只要是插入 都应该考虑空间扩容
	//可以把扩容直接作为一个接口 然后去调用它
	void SeqListCheckCapacity(SL * ps);//现在就不会越界访问了
	int end = ps->size - 1;//当end变成第一个时就不用挪了 因为第一个就是0
	while (end>=0)
	{
		ps->a[end + 1] = ps->a[end];
		--end;
	}
	ps->a[0] = x;
	ps->size++;//++是因为又多了一个数据
}
//顺序表头删
void SeqListPopFront(SL* ps)
{
	assert(ps);
	//从前往后挪数据 然后size--
	int start = 0;
	while (start<ps->size-1)//start 从前往后挪 start要挪在数据删除前的倒数第二个位置 比如原来是1234 挪到3那个位置
	{
		ps->a[start] = ps->a[start + 1];
		++start;//这样就覆盖了
	}
	ps->size--;//不需要把最后一个赋值为0 因为万一原来就是0 就浪费算力了
}


//任意位置的插入删除
void SeqListInsert(struct SL* ps, int pos, SLDataType x);//int pos 是插入数据的位置
void SeqListErase(struct SL* ps, int pos);//删除的时候 给个位子就行了
