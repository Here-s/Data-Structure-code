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

//����2��ͷ�巨 �嵽һ���µ������� �ͱ�� 5->4->3->2->1->NULL�� ȡ������ͷ�� ������ֻ��һ��newhead 
//newhead�ǿյ� �����������ȡ���� Ȼ��ͷ���ȥ ȡһ��curָ��1 nextָ��2 ���Ծ���curָ��newhead 
//Ȼ��newhead����Ųһ�� Ų��1 next��ָ��cur  next=next->next ����ѭ���͹��� ������cur����µ�ͷ
//��curָ��NULL��ʱ��ͽ�����

typedef struct ListNode//����һ��Ҫ��tydedef
{
	struct ListNode* next;
}ListNode;

struct ListNode* reverseList(struct ListNode* head)
{
	//����˼·��ȡcurͷ�嵽��newheadΪͷ����������
	struct ListNode* newHead = NULL;
	struct ListNode* cur = head;
	struct ListNode* next = cur->next;
	while (cur != NULL)
	{
		//ͷ��
		cur->next = newHead;
		newHead = cur;//���ͷ��
		cur = next;
		next = next->next;
	}
	return newHead;//������������µ�ͷ
}
int main()
{

	return 0;
}