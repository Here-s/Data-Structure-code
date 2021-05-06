#define _CRT_SECURE_NO_WARNINGS 1
#include<iostream>
using namespace std;
#define h 100 //通过define定义使得 h 最大为100 因为太大会导致栈溢出

void setjudge()
{
    int size = 4;
    //太大的时候 就会导致栈溢出
    cout << "请输入矩阵的长，不可以大于100" << endl;
    cin >> size;
    cout << "请输入矩阵坐标的值，只可输入1或者0" << endl;
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
            cout << "此矩阵具有自反性" << endl;
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
            cout << "此矩阵具有反自反性" << endl;
        }
    }
    count = 0;
    for (i = 1; i <= size; i++)//i是行 j是列
    {
        for (j = size; j >= 1; j--)
        {
            if (a[i][j] == a[j][i])
            {
                count++;
            }
            if (count == size * size)
            {
                cout << "此矩阵具有对称性" << endl;
                goto abc;
            }
        }
    }
abc:
    cout << endl;
    count = 0;
    for (i = 1; i <= size; i++)//i是行 j是列
    {       
        for (j = size ; j >= 1; j--)
        {
            if (a[i][j] != a[j][i])
            {
                count++;
            }
            if (a[i][i] == 1 && count>0)
            {
                cout << "既不是对称性，又不是反对称性" << endl;
                goto ddd;
            }
        }
        if (a[i][i] == 1 && count == 0)
        {
            cout << "既是对称性，又是反对称性" << endl;
        }
    }             
    if (count >0 )//size * (size - 1)
        {
            cout << "此矩阵具有反对称性" << endl;
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
                    cout << "此矩阵具有传递性" << endl;
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

