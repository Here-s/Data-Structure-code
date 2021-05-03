#define _CRT_SECURE_NO_WARNINGS 1
#include"5.3链表.h"


//顺序表就在干两件事 保持数据插入删除 保持数据是连续的
//顺序表 1可动态增长的数组 2 数据在数组中存储时必须是连续的
//缺点 1 中间或头部的插入删除很慢，需要挪动数据 时间复杂度是O(N) 2 空间不够时 增容会有一定的消耗和空间浪费
//优点 1 可以随机访问 2 缓存利用率(命中率)比较高  和链表对比 缓存在访问一个数据时 不会只加载一个数据到缓存 而是这个数据开始一段数据到缓存(预加载)
//访问链表也会预加载 

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

//销毁接口
void SeqListDestory(SL* ps)
{
	free(ps->a);//释放内容
	ps->a = NULL;//再置为空 防止野指针
	ps->size = ps->capacity = 0;//其他数据也置为空
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
	while (end >= 0)
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
	while (start < ps->size - 1)//start 从前往后挪 start要挪在数据删除前的倒数第二个位置 比如原来是1234 挪到3那个位置
	{
		ps->a[start] = ps->a[start + 1];
		++start;//这样就覆盖了
	}
	ps->size--;//不需要把最后一个赋值为0 因为万一原来就是0 就浪费算力了
}


//任意位置的插入删除  可以代替前面的插入方式
void SeqListInsert(SL* ps, int pos, SLDataType x)//int pos 是插入数据的位置   int也可以写成size_t size_t是unsignedint 无符号整型的意思
{
	assert(ps);
	assert(pos < ps->size && pos>0);//断言删除的位置是否有效 放在非法删除  不能等于size 因为size不是一个有效位置
	//并且pos不能小于0 不然也出错
	SeqListCheckCapacity(ps);//只要是插入 都应该检查容量
	//插入的时候 应该从后往前挪 不然就出错了
	int end = ps->size - 1;//end是最后一个数据的位置
	while (end>=pos)//当end走到pos位置就结束了 所以是>=  这里就挪完数据了
	{
		ps->a[end + 1] = ps->a[end];//把end的位置的值 给了end+1
		--end;
	}
	ps->a[pos] = x;
	ps->size++;//x是插入的数据
}
void SeqListErase(SL* ps, int pos)//删除的时候 给个位子就行了
{
	//删除的时候 选择覆盖 因为顺序表的结构必须是连续的 所以是覆盖
	assert(ps);//这里是检查结构体指针
	assert(pos < ps->size&& pos >= 0);
	int start = pos;
	//最后一个数据的位置是size-1 所以挪数据的时候挪到size-2就行了 不然就会越界访问
	while (start < ps->size-1)
	{
		ps->a[start] = ps->a[start + 1];
		++start;
	}
	ps->size--;//因为size是控制有效数据的
}

int SeqListFind(SL* ps, SLDataType x)
{
	assert(ps);
	int i = 0;
	while (i<ps->size )
	{
		if (ps->a[i] == x)
		{
			return i;
		}
		++i;
	}
	return -1;//下标不可能是负数 所以返回-1
}

//应用底层是用数据结构存储的
