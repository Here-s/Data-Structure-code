#pragma once //ͷ�ļ�����ŵ������� ����鿴����Щ�ӿ�


//˳�����Ч�����������б�����������

//��̬˳�����ƣ��;�̬���࣬���Ǵ�С�ɱ䣩
typedef int sldatatype;

struct seqlist
{
	sldatatype* a;//��Ϊ�Ƕ�̬�� ���ԾͲ���Ҫ����10��  ���Ǹ������� �������������ָ��
	int size;//��Ч���ݵĸ���
	int capacity;//�����Ǳ�ʾ�����ռ��С   ��Ϊ�Ƕ�̬�� ���Լ�capacity ��������������˼  Ҳ���ǿռ���������Ļ� ������

}; seqlist

//������ɾ��Ľӿ� 
//˳����ʼ��
void SeqListInit(SeqList* psl, size_t capacity);
//˳�������
void SeqListDestory(SeqList* psl);
//˳����ӡ
void SeqListPrint(SeqList* psl);
//���ռ䣬������ˣ���������
void ChenkCapacity(SeqList* psl);
//˳���β��
void SeqListPushBack(SeqList* psl, SLDataType x);
//˳���βɾ
void SeqListPopBack(SeqList* psl);
//˳���ͷ��
void SeqListPushFront(SeqList* psl, SLDataType x);
//˳���ͷɾ
void SeqListPopFront(SeqList* psl);
//˳������
int SeqListFind(SeqList* psl, SLDataType x);
//˳�����posλ�ò���x
void SeqListInsert(SeqList* psl, size_t pos, SLDataType x);
//˳���ɾ��posλ�õ�ֵ
void SeqListErase(SeqList* psl, size_t pos);




//�����Ǿ�̬˳�����ƣ����ܰ�����ȡ�� ��̬���ǹ̶���С  �Ժ������Ƕ�̬���ݱ�
typedef int sldatatype;
#define N 10
struct list
{
	sldatatype a[N];//˳�����һ�����飬�������ﶨ��һ������
	int size;//�����Ǳ�ʾ��Ч����
};

//void listpushback(struct list* ps, int x);//��������  ��������д�кܴ�����⣬�������double����ô�͵ðѽṹ����
//�ѽṹ���е�int�ĳ�double  ��ô�����Ĳ���ɾ�����ø�  �͵���typedef����� ���Ծͱ�ɺ�������д��

void listpushback(struct list* ps, sldatatype x);//�Ժ���дdouble�Ļ� �Ͱ�typedef int sldatatype��int�ĳ�double������

//�����ѽṹ�嵱�е�����ĳ�20 �����ֶ��ø� ���Կ���ʹ��#define������ ���ǽ��ṹ�嵱�е������Сʹ��
//ʹ��#define����Ϊ10  ������ĵĻ� ֱ�Ӹ�#define�����N�Ĵ�С������

//PushBack��β������  PopBackҲ��һ��β��ɾ��  PushFront��ͷ�ϵĲ��� PopFront��ͷ����ɾ��  ʹ��insert tail Ҳ���� 

//��̬˳���ʹ��malloc��relloc���