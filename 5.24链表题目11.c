#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>

//复杂链表 在普通链表的上面多了随机一个指针 最后一个指向null 其实也是个简单链表 

//复制带随机指针的链表  大厂会考这种题
//给定一个链表 每个节点包含一个额外增加的随即指针 该指针可以指向链表中的任何节点或空节点
//但是随机指针random也可能指向它自己 题目是复制这个链表 random可能指向前面 后面 自己 null
//所以无法预测它是什么样子的
// 
//要求返回这个链表的深拷贝
// 
//我们用一个由n个节点组成的链表来表示输入/输出中的链表 每个节点用一个[val,random_index]
//   val:一个表示NOde.val的整数  random_index:随即指针指向的节点索引(范围从0到n-1)如果
//   不指向任何节点，则为null
// 
//现在要拷贝这样一个链表出来 其实并不难 有一个什么节点 拷个什么节点 单链表很好复制
//但是随即指针不好复制 旧的链表的节点里面有地址指向另外一个节点 所以复制的链表也应该
//应该指向复制的链表里面的那个地址 复制的链表里面的地址 不知道指向前面还是后面 
//不知道复制的链表里面 应该指向的节点在哪里 可以遍历一遍 找到和旧链表值一样的地址
//然后把地址给了random 但是这样的话 时间复杂度是O(n^2) 因为每个random都会找一遍
//假如有两个random 都指向同一个节点 那么拿值找的时候 不知道找到的是原来的值还是拷贝的值
//所以不能这样做
// 
//这道题只能这样做：分为三步  第一步 复制拷贝节点 不要链到新链表里面去 应该链到原链表
//原节点的后面 创建拷贝的节点 连在原节点的后面 第二步 处理一下拷贝节点的random 拷贝节点的
//的random 每个拷贝节点的random都在原节点的后面 所以拷贝节点的random也在原节点的random后面
//所以拷贝的random就在原random的后面 把原random的next 给了拷贝的random 这样依次就好了
//直接复制的random 如果按照值去找的话 有相同的值 无法确定是哪个 所以不能这样找xsxx有
//这样就把原链表破坏了  所以拆解出复制链表就行了 遍历原链表 用三个指针 cur(原节点) curcopy(拷贝的节点)
//next(原链表表示下一个) 要拆解链表的话 让cur的next指向next 这样就把拷贝链表断开了
//curcopy的next指向next的next(因为复制那会儿的next是连在原链表和复制链表的) 然后再让原链表指向第三个节点
//复制链表的第二个节点也指向第三个节点 然后就这样拆出来了
//
typedef struct ListNode//这里一定要有tydedef
{
    struct ListNode* head;
    struct ListNode* next;
    int val;
    int random;
}Node;
struct Node* copyRandomList(struct Node* head)
{
    if (head == NULL)
    {
        return NULL;
    }
    Node* cur = head;
    //拷贝节点链接到原链表的后面
    while (cur)//通过cur来判断多会儿结束
    {
        Node* copy = (Node*)malloc(sizeof(Node));
        copy->next = NULL;//开辟任何一个节点 都应该置NULL
        copy->random = NULL;
        copy->val = cur->val;
        copy->next = cur->next;
        cur->next = copy;//和上一步顺序一定不能乱
    }
    //处理拷贝节点的random 指向原节点的random的后一个
    cur = head;
    while (cur)
    {
        Node* copy = cur->next;
        if (cur->random)
        {
            copy->random = cur->random->next;
        }
        else
        {
            copy->random = NULL;
        }
        cur = cur->next->next;
    }
    //拆下拷贝链表
    cur = head;
    Node* copyHead = head->next;
    while (cur)
    {
        Node* copy = cur->next;
        Node* next = copy->next;
        cur->next = next;
        if (next)
        {
            copy->head = next->next;
        }
        else
        {
            copy->next = NULL;
        }
        cur = next;
    }
    return copyHead;
}
//还能优化 在拷贝节点的时候 利用KV结构 容器的方式 就是类似于查找这样的方式 p保留原链表的指针 val保留复制链表的指针 把拷贝节点放在
//放在容器里面 例如容器 就能找到拷贝节点了 这样就可以连起来了 但是要学了C++才能
int main()
{
    return 0;
}
