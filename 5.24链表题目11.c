#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>

//�������� ����ͨ���������������һ��ָ�� ���һ��ָ��null ��ʵҲ�Ǹ������� 

//���ƴ����ָ�������  �󳧻ῼ������
//����һ������ ÿ���ڵ����һ���������ӵ��漴ָ�� ��ָ�����ָ�������е��κνڵ��սڵ�
//�������ָ��randomҲ����ָ�����Լ� ��Ŀ�Ǹ���������� random����ָ��ǰ�� ���� �Լ� null
//�����޷�Ԥ������ʲô���ӵ�
// 
//Ҫ�󷵻������������
// 
//������һ����n���ڵ���ɵ���������ʾ����/����е����� ÿ���ڵ���һ��[val,random_index]
//   val:һ����ʾNOde.val������  random_index:�漴ָ��ָ��Ľڵ�����(��Χ��0��n-1)���
//   ��ָ���κνڵ㣬��Ϊnull
// 
//����Ҫ��������һ��������� ��ʵ������ ��һ��ʲô�ڵ� ����ʲô�ڵ� ������ܺø���
//�����漴ָ�벻�ø��� �ɵ�����Ľڵ������е�ַָ������һ���ڵ� ���Ը��Ƶ�����ҲӦ��
//Ӧ��ָ���Ƶ�����������Ǹ���ַ ���Ƶ���������ĵ�ַ ��֪��ָ��ǰ�滹�Ǻ��� 
//��֪�����Ƶ��������� Ӧ��ָ��Ľڵ������� ���Ա���һ�� �ҵ��;�����ֵһ���ĵ�ַ
//Ȼ��ѵ�ַ����random ���������Ļ� ʱ�临�Ӷ���O(n^2) ��Ϊÿ��random������һ��
//����������random ��ָ��ͬһ���ڵ� ��ô��ֵ�ҵ�ʱ�� ��֪���ҵ�����ԭ����ֵ���ǿ�����ֵ
//���Բ���������
// 
//�����ֻ������������Ϊ����  ��һ�� ���ƿ����ڵ� ��Ҫ��������������ȥ Ӧ������ԭ����
//ԭ�ڵ�ĺ��� ���������Ľڵ� ����ԭ�ڵ�ĺ��� �ڶ��� ����һ�¿����ڵ��random �����ڵ��
//��random ÿ�������ڵ��random����ԭ�ڵ�ĺ��� ���Կ����ڵ��randomҲ��ԭ�ڵ��random����
//���Կ�����random����ԭrandom�ĺ��� ��ԭrandom��next ���˿�����random �������ξͺ���
//ֱ�Ӹ��Ƶ�random �������ֵȥ�ҵĻ� ����ͬ��ֵ �޷�ȷ�����ĸ� ���Բ���������xsxx��
//�����Ͱ�ԭ�����ƻ���  ���Բ���������������� ����ԭ���� ������ָ�� cur(ԭ�ڵ�) curcopy(�����Ľڵ�)
//next(ԭ�����ʾ��һ��) Ҫ�������Ļ� ��cur��nextָ��next �����Ͱѿ�������Ͽ���
//curcopy��nextָ��next��next(��Ϊ�����ǻ����next������ԭ����͸��������) Ȼ������ԭ����ָ��������ڵ�
//��������ĵڶ����ڵ�Ҳָ��������ڵ� Ȼ��������������
//
typedef struct ListNode//����һ��Ҫ��tydedef
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
    //�����ڵ����ӵ�ԭ����ĺ���
    while (cur)//ͨ��cur���ж϶�������
    {
        Node* copy = (Node*)malloc(sizeof(Node));
        copy->next = NULL;//�����κ�һ���ڵ� ��Ӧ����NULL
        copy->random = NULL;
        copy->val = cur->val;
        copy->next = cur->next;
        cur->next = copy;//����һ��˳��һ��������
    }
    //�������ڵ��random ָ��ԭ�ڵ��random�ĺ�һ��
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
    //���¿�������
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
int main()
{
    return 0;
}