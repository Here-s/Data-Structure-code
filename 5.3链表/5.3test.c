#define _CRT_SECURE_NO_WARNINGS 1
#include"5.3����.c"



void TestSeqList1()
{
	SeqList s;//�ṹ��ʹ�õĻ�  ��һ��Ӧ�ó�ʼ��  ����ʼ���Ļ�  ԭ�������ֵ
	SeqListInit(&s);//���������ֵ  
	//���洫���ǽṹ��ָ��  ���洫���ǽṹ��
	SeqListPushBack(&s, 1);//����ַ��ȥ����ֵ����ȥ
	SeqListPushBack(&s, 2);
	SeqListPushBack(&s, 3);
	SeqListPushBack(&s, 4);
	SeqListPushBack(&s, 5);
	SeqListPushBack(&s, 6);
	SeqListPushBack(&s, 7);
	SeqListPushBack(&s, 8);//����û����  ����ֻ�����ĸ��ռ�  Խ����һ��������(�����Ƽ� ����ȫ�����) ûԽ�粻һ��û������ ��ĳ�����ʱ��ͻᱨ

	SeqListPrint(&s);//��Ϊ�ǽṹ��  ���Ծ�������ָ��

	SeqListPopBack(&s);
	SeqListPopBack(&s);//����PopBack ����ִ������βɾ  ɾ�����������������
	SeqListPrint(&s);

	SeqListPushFront(&s, -1);
	SeqListPushFront(&s, -2);
	SeqListPushFront(&s, -3);//��Ȼ������û��  ���ǵ��Ե�ʱ�� �ᷢ�����ݺ�����Ŀռ��С������ �ռ������ �൱��Խ�����
	//ֻҪ�ǲ��� ��Ӧ�ÿ��ǿռ�����
	SeqListPrint(&s);

	SeqListPopFront(&s);
	SeqListPopFront(&s);//ɾ������ǰ�������
	SeqListPrint(&s);

	SeqListInit(&s);//���ٶ�̬���ٵĿռ� �ֲ���������Ҫ��

	int pos = SeqListFind(&s, 5);
	if (pos != -1)
	{
		SeqListErase(&s, pos);//�ҵ�5�� Ȼ����ɾ�� ͨ��λ��ɾ��
	}

}
int main()
{
	TestSeqList1();

	return 0;
}