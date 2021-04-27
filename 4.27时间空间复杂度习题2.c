#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>


//һ���������� nums �������������֮�⣬�������ֶ����������Σ���д�������ҳ�������ֻ����һ�ε�����
//Ҫ��ʱ�临�Ӷ���O(n)���ռ临�Ӷ���O(1)

//�Ȱ����������Ȼ���ʣ�����������Ի�ý��
//�������1�ε���Ϊx1��x2  ����֮�� �������εĶ����û��

int* singlenumber(int* nums, int numssize, int* returnsize)
{
	int ret = 0;//0���κ���������κ���
	for (int i = 0; i < numssize; ++i)
	{
		ret ^= nums[i];//ret��ֵ=x1^x2
		//��һ�����ǰ�x1��x2���뿪  ���Գ����ҹ���
	}//������ԭ������ͬ �������Ϊ1 ��ͬΪ0
	//����x2=5��x1=3  ��ô������ 0101   0011  ���������0110 ��6
	//��ʱ�����һ��λ �ҳ�xλΪ1  ret������Է���Ϊ1��λ ˵��x1��x2��
	//�ĵ�mλ��һ����һ��Ϊ1��һ��Ϊ0  
	// 
	//�����ʱ���ԭ��������x1��x2  ��mλΪһ��Ϊһ��  ��mλΪ0��Ϊһ��
	//x1��x2������һ�飬�������ɶԳ�����һ�� ֻ��x1��x2����һ��

	int m = 0;
	while (m < 32)
	{
		if (ret & (1 << m))//�ҳ�ret�ĵ�mλΪ1��λ
		{
			break;//��Ϊ�����(��0) ����break ��Ϊret��Ϊ0
		}
		else
		{
			++m;
		}
	}

	//����
	int x1 = 0, x2 = 0;
	for (int i = 0; i < numssize; ++i)
	{
		if (nums[i] & (1 << m))
		{
			x1 ^= nums[i];
		}
		else
		{
			x2 ^= nums[i];
		}
	}
	int* retarr = malloc(sizeof(int) * 2);
	retarr[0] = x1;
	retarr[1] = x2;
	*returnsize = 2;
	return retarr;
}
int main()
{
	return 0;
}