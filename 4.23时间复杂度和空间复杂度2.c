#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>


//����ײ�ݹ�factoria1��ʱ�临�Ӷ�   ʵ�ֵ��ǽײ� �ײ��ǵݹ�
//���Ӷ��� O(N)  �ݹ��㷨��μ��㣺�ݹ����*ÿ��ÿ�εݹ麯���Ĵ���
//ÿ�εݹ麯���Ĵ����ǣ�N�γ���ÿ����1 ���ǳ�N  ���Ծ���N^2

//������ int m=10;  while(n--)  ����n��δ֪����ִ����n�� ÿ�εݹ���������n�� ���Ծ���n��ƽ��
//��������ֵݹ��ѭ���Ļ�   ����O(N^2) 
long long factorial(size_t N)
{
	return N < 2 ? N : factoria1(N - 1) * N;
}


//����binarysearch��ʱ�临�Ӷ�(���ֲ��ң�һ��һ����ң���ȫ�����������)  O(log��2Ϊ�׵�n�Ķ���)
// �ʼ��1��Ȼ����ǳ�2��2��2��ÿ��һ�ξͳ�2
// ÿ�β��ң�����N/2/2/2/2/.../2=1   N=2^x  x�ǲ��ҵĴ���  ������ľ���x  x�ڷ����������log��2Ϊ�׵�n�Ķ���
//��õ������ O(1)

//���ֲ���Ҫ���������飬
//����� ��ǿ
int binarysearch(int* a, int n, int x)
{
	assert(a);
	int begin = 0;
	int end = n;
	while (begin < end)
	{
		int mid = begin + ((end - begin) >> 1);
		if (a[mid] < x)
		{
			begin = mid + 1;
		}
		else if (a[mid] > x)
		{
			end = mid;
		}
		else
			return mid;
	}
	return -1;
}