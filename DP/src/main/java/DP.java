import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.Set;

public class DP {

    //DP的三个特点：
    // 1、把原问题分解为几个相似的子问题
    // 2、所有的子问题都只需要解决一次
    // 3、存储子问题的解

    //DP问题的四个考虑角度：
    // 1、状态定义，一定要形成递推关系
    // 2、状态间的转移方程定义
    // 3、状态的初始化
    // 4、返回结果


    //斐波那契数列，因为是前一项+后一项。所以只要返回前两项的和就好了
    // 状态转移方程：F(i) = F(i-1) + F(i-2)
    // 初始状态：F(0) = 0, F(1) = 1
    // 返回结果：F(n)
    public static int Fibonacci(int n) {
//        if (n == 0) {
//            return 0;
//        }
//        if (n == 1 || n == 2) {
//            return 1;
//        }
//        return Fibonacci(n - 1) + Fibonacci(n - 2);

        //通过循环来解决，时间复杂度和空间复杂度都低
        int f1 = 1;
        int f2 = 0;
        int fn = 0;
        for (int i = 2; i <= n; i++) {
            fn = f1 + f2;
            //更新中间状态
            f2 = f1;
            f1 = fn;
        }
        return fn;
    }


    //给定一个字符串s和一组单词dict，判断s是否可以分割成一个单词序列，
    // 使得单词序列中所有的单词都是dict中的单词。也就是分割的东西，都可以在单词当中找到

    //问题：字符串s是否可以被分割
    // 状态F(i)：字符串前i个字符是否可以被分割
    // 状态转移方程：F(i)：j <i && F(j) && [j + 1, i] 是否可以在词典当中找到
    // 初始状态：F(0): true   辅助状态
    // 返回结果：F(字符串长度): f(s.size()) f(s.length())

    public boolean wordBreak(String s, Set<String> dict) {
        boolean[] canBreak = new boolean[s.length() + 1];
        canBreak[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            // j < i && F(j) && [j + 1, i]
            for (int j = 0; j < i; j++) {
                if (canBreak[j] && dict.contains(s.substring(j, i))) {
                    //前 i 个字符能找到
                    canBreak[i] = true;
                    break;
                }
            }
        }
        return canBreak[s.length()];
    }


    //给出一个三角形，计算从三角形顶部到底部的最小路径和，每一步都可以移动到下面一行相邻的数字
    // 状态F(i)：从(0,0)到（i,j)的最小路径和
    // 转移方程：F(i,j): min(F(i-1, j-1),F(i-1,j)) + array[i][j]
    // 初始状态：F(0,0) = array[0][0]
    // 返回结果：min（F(row-1,j))

    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        int sum ;
        sum = getResult(triangle,0,0);
        return sum;
    }
    public int getResult(ArrayList<ArrayList<Integer>> triangle,int l,int k) {
        int sum = triangle.get(l).get(k);
        if(l < triangle.size()-1) {
            sum = sum + Math.min(getResult(triangle, l + 1, k), getResult(triangle, l + 1, k + 1));
        }
        return sum;
    }
}