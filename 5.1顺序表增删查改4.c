//#define _CRT_SECURE_NO_WARNINGS 1
#include"5.1˳�����ɾ���4.h"


void SeqListInit(SL* ps)//�����ǳ�ʼ���ĵڶ��ַ���
{
	ps->a = (SLDataType* )malloc(sizeof(SLDataType) * 4);//�����ǿ����ĸ��ռ�
	if (ps->a == NULL)
	{
		printf("�����ڴ�ʧ��\n");
		exit(-1);//����������
	}
	ps->size = 0;
	ps->capacity = 4;
}

//���ݽӿ�
void SeqListCheckCapacity(SL* ps)
{
	//���ݴ���
	if (ps->size >= ps->capacity)
	{
		ps->capacity *= 2;//C�����е�����  ���ÿ����һ�� �ᵼ��Ƶ������ ����һ�������� ͨ�õķ�������2�� Խ����������Խ�� ���Ҫ��λ�����ݵĻ�
			//�´����ǰ�����ݵ�ĳ�� Ȼ��������  ���ǻ��˷�һ���Ŀռ� 
		ps->a = (SLDataType*)realloc(ps->a, sizeof(SLDataType) * ps->capacity);//realloc  ��ԭ���Ŀռ佻���� Ȼ�������ݵ���Ҫ�Ŀռ�
		if (ps->a == NULL)
		{
			printf("����ʧ��\n");
			exit(-1);
		}
	}

}


//˳���β��   
void SeqListPushBack(SL* ps, SLDataType x)//ps��ָ��
{
	assert(ps);
	//�����������Ҫ��һ���߼��� �ж� ���������Ҫ���� 
	ps->a[ps->size] = x;//��������˵  size�������һ�����ĺ����Ǹ������±�  ���ԾͰ�x�������һ�����ĺ���
	ps->size++;//����size��һ����  �ֱ��һ���±�
}

void SeqListPrint(SL* ps)
{
	assert(ps);
	for (int i = 0; i < ps->size; ++i)
	{
		printf("%d ", ps->a[i]);
	}
	printf("\n");
}



//˳���βɾ
void SeqListPopBack(SL* ps)
{
	assert(ps);
	//ps->a[ps->size - 1] = 0;//������ûʲô���� ��Ϊ������λ�þ���0�Ļ� Ҳ��Ӱ��
	ps->size--;
}

//˳���ͷ��  ��ʵ����Ų������ ��������������Ų �ǴӺ���ǰһ��һ��Ų �������һ�� ��ô����������Ųһ�� 
void SeqListPushFront(SL* ps, SLDataType x)
{
	assert(ps);
	//ֻҪ�ǲ��� ��Ӧ�ÿ��ǿռ�����
	//���԰�����ֱ����Ϊһ���ӿ� Ȼ��ȥ������
	void SeqListCheckCapacity(SL * ps);//���ھͲ���Խ�������
	int end = ps->size - 1;//��end��ɵ�һ��ʱ�Ͳ���Ų�� ��Ϊ��һ������0
	while (end>=0)
	{
		ps->a[end + 1] = ps->a[end];
		--end;
	}
	ps->a[0] = x;
	ps->size++;//++����Ϊ�ֶ���һ������
}
//˳���ͷɾ
void SeqListPopFront(SL* ps)
{
	assert(ps);
	//��ǰ����Ų���� Ȼ��size--
	int start = 0;
	while (start<ps->size-1)//start ��ǰ����Ų startҪŲ������ɾ��ǰ�ĵ����ڶ���λ�� ����ԭ����1234 Ų��3�Ǹ�λ��
	{
		ps->a[start] = ps->a[start + 1];
		++start;//�����͸�����
	}
	ps->size--;//����Ҫ�����һ����ֵΪ0 ��Ϊ��һԭ������0 ���˷�������
}


//����λ�õĲ���ɾ��
void SeqListInsert(struct SL* ps, int pos, SLDataType x);//int pos �ǲ������ݵ�λ��
void SeqListErase(struct SL* ps, int pos);//ɾ����ʱ�� ����λ�Ӿ�����
