#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>


//��תһ��������  ���磺
//���룺1->2->3->4->5->NULL  �����5->4->3->2->1->NULL
//���Ե�����ݹ�ط�ת����

//���õĽⷨ��ѭ�� ��Ϊ�Ƿ�ת ���Բ���Ҫ���������� 
//���Կ���3��ָ���淽��  ����һ��һ��ȡ����ͷ�� Ҳ����ʵ��
//�淽�������� ��1ǰ���һ��NULL Ȼ����1��ת���� 1ԭ��ָ��2 ��������ָ�����NULL�Դ�ѭ���ͺ���
//node1 ��ʾΪn1 node2��ʾΪn2  Ȼ����n2ָ��n1 ���Ǿ��Ҳ���n3�� ����˵�ٶ���һ��n3 n3��Ϊ�˱�����һ��
//����һ��֮��ͨ��ѭ�� �Ϳ���һֱ���� �Ӷ��õ���� ����Ҫ��1��nextָ��NULL ���Կ���һ������һ��NULL
//��һ����n1ָ��NULL Ȼ��һ��������� ��n2����n1  n3����n2  n3��next����n3 n3ΪNULL��ʱ��
//n2����ָ��n1 n2ΪNULL��ʱ��ͽ����� �������ͷ����n1 �����ͷ�ת��

typedef struct ListNode//����һ��Ҫ��tydedef
{
	struct ListNode* next;
}ListNode;

struct ListNode* reverseList(struct ListNode* head)
{
	if (head == NULL||head->next==NULL)
	{
		return head;
	}
	struct ListNode* n1 = NULL, * n2 = head, * n3 = head->next;
	while (n2)//��Ϊn2ΪNULL�ǽ������� ����������n2�ͺ���
	{
		n2->next = n1;//�����Ƿ�ת
		n1 = n2;
		n2 = n3;
		if(n3!=NULL)//��Ϊn3ΪNULL�Ͳ���Ҫ�����������ж�һ��
		n3 = n3->next;//��Ϊ����������ʱ�� n3��NULL ���� ���ж�һ�� ��ֹ�������
	}
	return n1;//ѭ������֮�� n1����ͷ
}

int main()
{
	return 0;
}