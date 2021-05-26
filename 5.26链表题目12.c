#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>

//对链表进行插入排序
//就是把链表先插入 然后再排序 
// [3]->[1]->[4]->[2] 假设这四个单链表 排序 先把第一个当成有序的 把第二个当成插入的数 
//不能从后往前比 应该从前往后比 假设按照升序排列 如果比你小 就插入在你的前面 如果是头结点
//就要头插了 再下一步要找到[4] 在把[1] 拿到前面插入的时候 就得保存[4] 的地址 也就是下一个地址
//这样插入的那个就能随便动了 但是下次插的时候 就发现需要一个头来保存第一个节点的地址
//不用保存尾 把尾的next置NULL就行了 比谁小就在谁的前面插入 接下来是2 2不能在3的前面插入
//因为这里也涉及一个问题 2能指向3 但是也得让1指向2  所以应该
//
//先把原来的头拿下来 把next指向NULL 就当作排序链表的头 然后这里保留头指针 如果小的话 
//就头插 如果插入中间位置 就需要两个指针 遍历 然后插入中间 不断的取节点插入 
//然后就变成新的链表了 把新的头叫做 sorthead 插入的叫cur 还有next 避免找不到链表
//从前往后比是因为单链表不能从后往前比

typedef struct ListNode//这里一定要有tydedef
{
    struct ListNode* next;
    int val;
}Node;
struct ListNode* insertionSortList(struct ListNode* head)
{
    //如过代码是NULL 那么程序就崩溃了 所以先判断
    if (head == NULL || head->next == NULL)
    {
        return head;
    }
    //第一次要拿一个头结点出来 
    Node* sortHead = head;
    Node* cur = head->next;
    sortHead->next = NULL;
    //插入的时候按照cur来判断 
    while (cur)
    {
        Node* next = cur->next;
        //把cur插入到sortHead链表中，并且保持有序 三种情况 比sortHead小 比sortHead大
        //比sortHead大  针对这三种情况 所以应该再定义指针
        if (cur->val == sortHead->val)//这种情况说明是头插
        {
            cur->next = sortHead;
            sortHead = cur;
        }
        else//中间插入
        {
            Node* sortPrev = sortHead;
            Node* sortCur = sortPrev->next;
            while (sortCur)
            {
                if (cur->val <= sortCur->val)
                {
                    sortPrev->next = cur;
                    cur->next = sortCur;
                    break;
                }
                else
                {
                    sortPrev = sortCur;
                    cur->next = sortCur;
                }
            }
            //尾插 但又可能是NULL 也可能是 之前的break出来的 所以在判断
            if (sortCur == NULL)
            {
                sortPrev->next = cur;
                cur->next = NULL;
            }
        }
        cur = next;
    }
    return sortHead;
}
int main()
{
    return 0;
}