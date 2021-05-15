#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>

//�Ƴ������е��ڸ���ֵval�����нڵ�  ����
//���룺1->2->6->3->4->5->6  val=6
//�����1->2->3->4->5

//��ΪҪɾ�����е�6 ������һ�����ҽӿ� �ҵ����е�6 Ȼ��ɾ�� ����Ч�ʵ� ��Ϊÿ�ζ�Ҫ��ͷ��
//Ҫ��O(N)��ʱ�临�Ӷ�������ɵĻ� ��Ҫֻдһ������ �ȸ�һ��λ��cur ��ͷ��ʼһ��һ��������
//�ҵ�6֮�� ɾ���� ��ΪҪ��ǰһ�� ǰһ�������е�ַ ͨ����ַ��ɾ�� ǰһ����prev 
//��prev��nextָ��cur��next �����Ͱ�6ɾ�� Ȼ����������������� ��curָ��ڶ���6��ʱ�� 
//prev��nextָ��NULL �����Ͱ����е�6��ɾ�� ��Ӧ�ÿ��ǵ�һ������val ����������Ļ� 
//�ոյĳ���ͻ���� ����д�ĳ������Ǳ�����ָ��Ŀ��������� ��Ϊ��ʱcurָ��6 ��prev����NULL
//�������������ͷɾ�� ��Ҫ��ָ��ָ����һ���ڵ� ���µ�ͷ 

typedef struct ListNode//����һ��Ҫ��tydedef
{
	struct ListNode* next;
	int val;
}Node;

struct ListNode* removeElements(struct LIstNode* head, int val)//�з���ֵ ˵���Ƿ����������ͷ
	//��Ϊ�����ͷ���ܻ�� ���Է����������ͷ
{
	Node* prev = NULL;
	Node* cur = head;
	while (cur)//�������뷢�� curΪNULL�ͽ�����
	{
		if (cur->val == val)//������val 
		{
			if (cur == head)//˵��prev����NULL ����Ҫɾ��ֵ����ͷ Ҳ�Ͳ���Ҫprev������
			{
				head = cur->next;//��ͷָ����һ���ڵ� 
				free(cur);
				cur = head;//��cur���·ŵ�ͷ���� ��Ϊ֮ǰ�ı�ɾ��
			}
			else
			{
				prev->next = cur->next;
				free(cur);//�����һ����6��ʱ�� ���ߵ����else�������� ����free֮�� cur����һ��Ұָ����
				cur = prev->next;//�����Ļ� cur�͵���NULL�� 
			}
		}
		else//���־���û������val ������������ 
		{
			prev = cur;//cur������֮ǰ �Ȱ�������prev 
			cur = cur->next;
		}
	}
	return head;
}
//���ˢ���Ҳ�������Ļ� ����vs������� ����Ҫд���� ����д ����Ҫ��ӡ �õ���һ��һ�����ͺ���
int main()
{
	//�����ĸ��ڵ������ �����ʹ�������һ������
	Node* n1 = (Node*)malloc(sizeof(Node));
	n1->val = 6;
	Node* n2 = (Node*)malloc(sizeof(Node));
	n2->val = 6;
	Node* n3 = (Node*)malloc(sizeof(Node));
	n3->val = 1;
	Node* n4 = (Node*)malloc(sizeof(Node));
	n4->val = 6;
	n1->next = n2;
	n2->next = n3;
	n3->next = n4;
	n4->next = NULL;
	Node* head = removeElements(n1, 6);//n1���������ͷ 6������Ҫɾ����ֵ Node* head�����շ���ֵ
	return 0;
}