#define _CRT_SECURE_NO_WARNINGS 1
#include<iostream>
using namespace std;
#define h 100 //ͨ��define����ʹ�� h ���Ϊ100 ��Ϊ̫��ᵼ��ջ���

void setjudge()
{
    int size = 4;
    //̫���ʱ�� �ͻᵼ��ջ���
    cout << "���������ĳ��������Դ���100" << endl;
    cin >> size;
    cout << "��������������ֵ��ֻ������1����0" << endl;
    int a[h][h];
    for (int i = 1; i <= size; i++)
    {
        for (int j = 1; j <= size; j++)
        {
            cin >> a[i][j];
        }
    }
    int i = 1;
    int j = 1;
    int count = 0;
    for (i = 1; i <= size; i++)
    {
        if (a[i][i] == 1)
        {
            count++;
        }
        if (count == size)
        {
            cout << "�˾�������Է���" << endl;
        }
    }
    count = 0;
    for (i = 1; i <= size; i++)
    {
        if (a[i][i] == 0)
        {
            count++;
        }
        if (count == size)
        {
            cout << "�˾�����з��Է���" << endl;
        }
    }
    count = 0;
    for (i = 1; i <= size; i++)//i���� j����
    {
        for (j = size; j >= 1; j--)
        {
            if (a[i][j] == a[j][i])
            {
                count++;
            }
            if (count == size * size)
            {
                cout << "�˾�����жԳ���" << endl;
                goto abc;
            }
        }
    }
abc:
    cout << endl;
    count = 0;
    for (i = 1; i <= size; i++)//i���� j����
    {       
        for (j = size ; j >= 1; j--)
        {
            if (a[i][j] != a[j][i])
            {
                count++;
            }
            if (a[i][i] == 1 && count>0)
            {
                cout << "�Ȳ��ǶԳ��ԣ��ֲ��Ƿ��Գ���" << endl;
                goto ddd;
            }
        }
        if (a[i][i] == 1 && count == 0)
        {
            cout << "���ǶԳ��ԣ����Ƿ��Գ���" << endl;
        }
    }             
    if (count >0 )//size * (size - 1)
        {
            cout << "�˾�����з��Գ���" << endl;
            goto def;
        }
def:
    cout << endl;
ddd:
    cout << endl;
    count = 0;
    for (int i = 1; i <= size; i++)
    {
        for (int j = 1; j <= size; j++)
        {
            for (int k = 1; k <= size; k++)
            {
                if (a[i][j] == 1 && a[j][k] == 1 && a[i][k] ==1)
                {
                    cout << "�˾�����д�����" << endl;
                }
            }
        }
    }
    for (int i = 1; i <= size; i++)
    {
        for (int j = 1; j <= size; j++)
        {
            cout << a[i][j] << " ";
        }
        cout << endl;
    }
}
int main()
{
    setjudge();
    return 0;
}

