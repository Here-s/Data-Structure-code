#define _CRT_SECURE_NO_WARNINGS 1
#include"4.30˳�����ɾ���3.h"





//void SeqListInit(SL s)//������ǳ�ʼ��
//{
//	s.size = 0;
//	s.a = NULL;
//	s.capacity = 0;// capacity�ǿռ�  �����ʾ��ʼ��Ϊ0
//}
void SeqListInit(SL* ps)//�����ǳ�ʼ���ĵڶ��ַ���
{
	ps->a = (SLDataType*)malloc(sizeof(SLDataType) * 4);//�����ǿ����ĸ��ռ�
	if (ps->a == NULL)
	{
		printf("�����ڴ�ʧ��\n");
		exit(-1);//����������
	}
	ps->size = 0;
	ps->capacity = 4;
}



//˳���β��
void SeqListPushBack(SL* ps, SLDataType x);//ps��ָ��
//˳���βɾ
void SeqListPopBack(SL* ps);
//˳���ͷ��
void SeqListPushFront(SL* ps, SLDataType x);
//˳���ͷɾ
void SeqListPopFront(SL* ps);


//����λ�õĲ���ɾ��
void SeqListInsert(struct SL* ps, int pos, SLDataType x);//int pos �ǲ������ݵ�λ��
void SeqListErase(struct SL* ps, int pos);//ɾ����ʱ�� ����λ�Ӿ�����
