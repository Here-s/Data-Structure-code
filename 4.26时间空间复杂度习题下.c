#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>


//��ʧ������  ����ʵ��  Ҫ��ʱ�临�Ӷ�O(n)���  ����Ļ� ����O(n^2)��

//���� [9,3,5,2,0,6,1,4,7]����nums   ȱ��8   numssize����9
int missingnumber(int* nums, int numssize)
{
	int x = 0;//�Ƚ�ȱ��ֵx��ֵΪ0
	for (size_t i = 0; i < numssize; ++i)
	{
		x^= nums[i];//����ֵ����x���һ��  ���൱��x����������ֵ������� Ψ��û�и�x���
		//������������һ������x���������һ�������  x����Щֵ[9,3,5,2,0,6,1,4,7]���
	}
	for (size_t i = 0; i <= numssize; ++i)//������x��0-9������ֵ�����һ��
	{
		x ^= i;//������<=numssize ��Ϊ�����ж��ٸ�Ԫ�� ���������ǰ����±������ ���Ծ���+1
		//+1���Ƕ���x ��Ϊ����ԭ����0-10 ��һ�� ����numssize��10 ԭ�������±���10��Ԫ��
		//������11��Ԫ�� ������ԭ������������ ���Ҷ���x ������������֮���ʣ��x
	}
	return x;
}
int main()
{
	int nums[] = { 9,3,5,2,0,6,1,4,7 };
	int numssize = sizeof(nums) / sizeof(nums[0]);
	int z= missingnumber(nums, numssize);
	printf("%d\n", z);
	return 0;
}