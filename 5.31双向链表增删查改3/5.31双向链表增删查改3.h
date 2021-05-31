#pragma once
#include<stdio.h>
#include<assert.h>
#include<string.h>
#include<stdlib.h>
//写完链表之后要释放 不然万一一直运行 就会导致服务器崩溃

//链表和顺序表（数组）的区别和联系：
//链表是在顺序表的上面补充出来的  数组的缺陷是不能动态增长 顺序表就是在数组的基础上面
//实现增删查改 并且插入时可以动态增长 但是是二倍增的 就可能空间浪费
// 
//顺序表缺陷：a 可能存在一定空间浪费 b 增容有一些效率损失 因为开辟新空间 要拷贝数据
//c 中间或者头部的插入删除 时间复杂度是O(n) 因为要挪动数据  所以就出现了链表来解决这个问题
// 
//链表缺陷：a 最大的缺陷 不能随机访问 排序也不方便 因为排序也是随机访问 
//
//顺序表和链表是互补的数据结构 链表会有碎片问题 顺序表的缓存利用率更高
//《深入理解计算机系统》 这本书不错



//链表有八种结构 
//单双节点类型：单向(只有一个next指针指向下一个位置) 双向(有next 还有prev指向上一个)
//带头和不带头：是指带哨兵位的头结点和不带哨兵位的头结点 哨兵位的头结点不存储有效数据
//循环和非循环类型：非循环链表的尾的next指向NULL 循环链表的next指向头 是带环链表的一种
//这八种结构是这下面的几种两两组合 有八种
//  
//双向链表就是每个里面有两个指针 一个指向前面 一个指向后面 
//
//最常用的结构是两种：1 无头单向非循环链表 2 带头双向循环链表
// 
//1 无头单向非循环链表：就是单链表 oj题出现很多   基本不会单独作为链表使用 因为如果要尾插
//就要找尾 那么就是O(n) 很费时间   可能作为其他数据结构的子结构 例如 图，哈希表
// 
//2 带头双向循环链表：首先有一个头结点 头结点不存储有效数据 每个节点是双向的 next 和prev
//既能正着遍历 也能倒着遍历 循环是尾节点的next指向头 头结点的prev指向尾 就是一个环状的
//特点：作为链表来使用 STL中的list就是这种结构 
// 
//双向链表：结构复杂 但是操作简单
//

typedef int LTDataType;//通过这两个 来定义 使得以后写类型的时候 就不需要改int或者其他类型了

typedef struct ListNode//节点里面包含这些东西
{
	struct ListNode* next;
	struct ListNode* prev;
	LTDataType data;
}ListNode;

//假设要尾插一个节点 要拿一个指针 来找尾 其实不用找尾 因为是循环链表 循环链表很方便 
//只有头指针 就能很方便的找到尾 phead 找尾的话 phead->prev就是尾 创建一个节点 值不管
//通过phead的prev找到尾 然后就变成头 尾 新节点 的链接关系 循环链表不需要找尾 因为头
//头的prev指向尾 通过找到的尾 然后把tail的next指向新节点 新节点的prev指向tail 
//新节点的next指向phead phead的prev指向新节点 这样就好了 这样找尾的话 时间复杂度就是O(1)
//面试官问的时候：单链表尾插的时候 如果不想找尾怎么办  给个头指针指向头 尾指针指向尾
//通过尾指针就能直接找到尾 
//循环链表的话 就不需要尾指针 通过prev就能直接找到尾 这就是天然的结构优势 单链表的话
//插入删除都是判断 但是循环链表的话 就不需要处理 因为如果链表是NULL的话 就是双向带头不循环
//让尾的next指向头 让头的prev指向next 这样就是循环链表了 但是如果一个节点都没有的话 
//head的next和prev都要指向自己 这种情况不需要单独判断 因为代码可以自己判定 当尾和头都指向自己的时候
//再新创建一个节点 让尾的next指向新节点的头 让新节点的prev指向尾 让新节点的next指向头
//头的prev指向新节点 和写多节点的时候 代码是一样的 但是链表为NULL的时候 头和尾指向自己
//

//void ListInit(ListNode** pphead);//不能达到初始化的效果 所以使用地址传递 二级指针 就可以了

ListNode* ListInit();//第二种方法

//释放
void ListClear(ListNode* phead);//考虑头结点删不删的情况 如果链表待会儿不用了 那就删头结点
//如果不用这个头结点了 就删

void ListDestory(ListNode* phead);

ListNode* BuyListNode(LTDataType x);//这是给一个值 并且创建一个节点 节点里面保存的这个值
//并且返回这个节点的指针 
void ListPrint(ListNode* phead);

void ListPushBack(ListNode* phead, LTDataType x);//尾插

void ListPopBack(ListNode* phead);//尾删

void ListPushFront(ListNode* phead, LTDataType x);//头插

void ListPopFront(ListNode* phead);//头删

//链表是和顺序表互补的结构 循环链表不支持随机访问 链表不支持随机访问
ListNode* ListFind(ListNode* phead, LTDataType x);//顺序表查找是返回下标 链表是返回节点的指针
//这里的查 既是查 也是修改 因为找到的是指针 所以也可以直接修改 再配合后面的接口 就可以实现
void ListInsert(ListNode* pos, LTDataType x);//在pos节点前面去插入x find就可以找到位置
void ListErase(ListNode* pos);//在某个节点去删除
