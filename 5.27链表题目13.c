#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>

//ɾ���������ظ��Ľ��  ����ɾ�� ͷβҲҪ����
//��һ������������� �����ظ��Ľ�� ��ɾ�����������ظ��Ľ�� �ظ��Ľ�㲻����
//��������ͷָ�� ���磺1->2->3->3->4->4->5 �����Ϊ 1->2->5 
//
//˼·���϶�����һǰһ������ָ�� �������ָ��һ���Ļ� ��ɾ�� ����ҲҪ���ж��ǲ���ֻ��һ�����
//һǰ(cur)һ��(next)����ָ�� ����ָ����ȵ�ʱ�� next������ ֱ��cur��next����ͬ
//��ʱ�Ͱ���ͬ��free������ ����Ҫ��freeǰ����Ǹ��ڵ�ָ��next����ڵ� ��������Ҫһ��ָ��
//��Ҫһ��ָ��ǰ���ָ��(prev) Ȼ���ٰ�next����cur next���������� ������ ���һ�� next������
//ֱ��������һ�� Ȼ�����free��cur next����prev ���������� ֱ��nextΪNULL ������ 
//��������������ͷ��β�м�����ͬ�� ���� 1 1 1 3 4 4 prev����ǰ�� curָ���һ��1 prevָ��ڶ���1
//һ���� cur��prev ��ͬ ��cur��next����ͬ��ʱ�� ��prev��nextָ��next ��������� ��ΪprevҪ�ƶ�
//��Ϊcur��next һ���ƶ���ʱ�� prevҲҪ�ƶ� ��cur��nextû��ͬʱ�ƶ���ʱ�� prev�Ͳ��ö�
//��������ͷ��ȫ��ͬ�� ��������һ�֣�1 2 3 3 4 5 5 5 �տ�ʼ cur��next����ͬ ����������
//������ָ��һ���ƶ� ���ɾ������ͬ�� prev���� ���ǵ�cur��next �ߵ���ͬ�ĵ�һ���͵ڶ���5��ʱ��
//next�������� ��next�ߵ�NULL��ʱ�� ����ͣ���� ���ǰ�������5Ҳ��ɾ�� Ȼ���prevָ����next(NULL)
//Ȼ��cur���ƶ� Ҳ��NULL �����Ϳ��ܳ����� ΪNULL 
//

typedef struct ListNode//����һ��Ҫ��tydedef
{
	struct ListNode* next;
	int val;
}ListNode;
struct ListNode* deleteDuplication(ListNode* pHead)
{
	if (pHead == NULL || pHead->next == NULL)
	{
		return pHead;
	}
	ListNode* prev = NULL;
	ListNode* cur = pHead;
	ListNode* next = cur->next;
	//���������ǱȵĹ��� Ҫ����next��ΪNULL ���������ͨ���
	while (next)
	{
		if (cur->val != next->val)//ͬʱ������ 
		{
			prev = cur;
			cur = next;
			next = next->next;
		}
		else//��ʾ������ȵ������ 
		{
			while (next && cur->val == next->val)//�����ǰ�cur��next �ĵ�ָ����ȵĵط� ����next��ΪNULL
				//������ж�β���������
			{
				next = next->next;
			}
			//�ߵ����� ��˵��cur��next �������
			if (prev)
			{
				prev->next = next;//������Ǹպð�β��ͬ����� ��β�ó�NULL��
			}
			else//������ 1 1 1 3 4 ���������ʱ�� �ͷ��������� ��Ϊ�����cur��next��ͬ 
				//��next��cur����ȵĻ� �Ͱ�next����prev->next ���Ǵ�ʱ��prev��NULL ���Գ���ͱ�����
				//����������ж� �������һ����������Ҫɾ����� ǰ��ͷ��� ��ô�ͻ�����β��ȵ�����
			{
				pHead = next;
			}
			//Ȼ���ͷ���ͬ�Ľڵ�
			while (cur != next)//��Ϊ�ͷ�֮��cur����ǰ�� ������������ʹ��cur��next�����
			{
				ListNode* del = cur;
				cur = cur->next;//�����ͷŵ�cur֮�� �Ҳ�����һ���ĵ�ַ
				free(del);//
			}
			if (next)
			{
				next = next->next;//�ͷ�֮�� next��������Ų ���nextΪNULL�Ļ� ����ѭ�� Ȼ����ֳ�������
			}
		}
	}
	return pHead;
}
int main()
{
	return 0;
}