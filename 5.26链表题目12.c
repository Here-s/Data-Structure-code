#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>

//��������в�������
//���ǰ������Ȳ��� Ȼ�������� 
// [3]->[1]->[4]->[2] �������ĸ������� ���� �Ȱѵ�һ����������� �ѵڶ������ɲ������ 
//���ܴӺ���ǰ�� Ӧ�ô�ǰ����� ���谴���������� �������С �Ͳ��������ǰ�� �����ͷ���
//��Ҫͷ���� ����һ��Ҫ�ҵ�[4] �ڰ�[1] �õ�ǰ������ʱ�� �͵ñ���[4] �ĵ�ַ Ҳ������һ����ַ
//����������Ǹ�������㶯�� �����´β��ʱ�� �ͷ�����Ҫһ��ͷ�������һ���ڵ�ĵ�ַ
//���ñ���β ��β��next��NULL������ ��˭С����˭��ǰ����� ��������2 2������3��ǰ�����
//��Ϊ����Ҳ�漰һ������ 2��ָ��3 ����Ҳ����1ָ��2  ����Ӧ��
//
//�Ȱ�ԭ����ͷ������ ��nextָ��NULL �͵������������ͷ Ȼ�����ﱣ��ͷָ�� ���С�Ļ� 
//��ͷ�� ��������м�λ�� ����Ҫ����ָ�� ���� Ȼ������м� ���ϵ�ȡ�ڵ���� 
//Ȼ��ͱ���µ������� ���µ�ͷ���� sorthead ����Ľ�cur ����next �����Ҳ�������
//��ǰ���������Ϊ�������ܴӺ���ǰ��

typedef struct ListNode//����һ��Ҫ��tydedef
{
    struct ListNode* next;
    int val;
}Node;
struct ListNode* insertionSortList(struct ListNode* head)
{
    //���������NULL ��ô����ͱ����� �������ж�
    if (head == NULL || head->next == NULL)
    {
        return head;
    }
    //��һ��Ҫ��һ��ͷ������ 
    Node* sortHead = head;
    Node* cur = head->next;
    sortHead->next = NULL;
    //�����ʱ����cur���ж� 
    while (cur)
    {
        Node* next = cur->next;
        //��cur���뵽sortHead�����У����ұ������� ������� ��sortHeadС ��sortHead��
        //��sortHead��  ������������ ����Ӧ���ٶ���ָ��
        if (cur->val == sortHead->val)//�������˵����ͷ��
        {
            cur->next = sortHead;
            sortHead = cur;
        }
        else//�м����
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
            //β�� ���ֿ�����NULL Ҳ������ ֮ǰ��break������ �������ж�
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