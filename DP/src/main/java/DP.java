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
    // 转移方程：F(i,j): min(F(i+1, j),F(i+1,j+1)) + array[i][j]
    // 初始状态：F(row-1,j) = array[row-1][j]
    // 返回结果：F(0.0)

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


    //一个机器人再 m*n 大小的地图的左上角，机器人每次向下或向右移动，然后到达右下角，有多少种路径
    // 状态 F(i,j)：从(0,0)到达(i,j)的路径的个数
    // 状态转移方程 F(i,j): F(i,j-1) + F(i-1,j)
    // 初始状态：F(i,0) = F(0,j) = 1
    // 返回：F(m-1, n-1)
    public int uniquePaths (int m, int n) {
        int[][] pathNum = new int[m][n];
        // F(i,0) = F(0,j) = 1
        for (int i = 0; i < m; i++) {
            pathNum[i][0] = 1;
        }
        for (int j = 1; j < n; j++) {
            pathNum[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                pathNum[i][j] = pathNum[i][j-1] + pathNum[i-1][j];
            }
        }
        return pathNum[m-1][n-1];
    }

    //最小路径和，从左上角走到右下角，只能向左和向右走
    // 转移方程：F(i,j) = min(F(i,j-1),F(i-1,j)) + array[i][j]
    // 第一行：F(0,j) = F(0,j-1) + array[0][j]
    // 第一列：F(i,0) = F(j-1,0) + array[i][0]
    // 初始状态：F(0,0) = array[0][0]
    // 返回：F(row-1,col-1)
    public int minPathSum(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int sum = grid[0][0];
        //第一行
        for (int i = 1; i < grid[0].length; i++) {
            grid[0][i] = grid[0][i-1] + grid[0][i];
        }
        //第一列
        for (int i = 1; i < grid.length; i++) {
            grid[i][0] = grid[i-1][0] + grid[i][0];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[i].length; j++) {
                grid[i][j] = Math.min(grid[i][j-1], grid[i-1][j]) + grid[i][j];
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }


    //把 n 个物品和一个大小为 n 的背包，给定数组 A 表示每个物品的大小和数组 V 表示每个物品的价值
    // 问最多能装进的总价值是多大。
    // 状态：F(i,j) = 从前 i 个商品当中选择，包的大小为 j 时id最大价值
    // 状态转移方程：如果 A[i-1] <= j，  F(i,j) = max(F(i-1,j),F(i-1,j-A[i-1]) + V[i-1])
    //            如果：A[i-1] > j,   F(i,j) = F(i-1,j)
    // 初始状态：F(0,j) = 0      F(i,0) = 0
    public int backPackII(int m, int[] A, int[] V) {
        int n = A.length;
        int[] maxValue = new int[m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = m; j > 0; j--) {
                if (A[i - 1] <= j) {
                    maxValue[j] = Math.max(maxValue[j],maxValue[j-A[i-1]] + V[i-1]);
                }
            }
        }
        return maxValue[m];
    }


    //给出一个字符串，分割使得分割出的每个字串都是回文串，计算分割回文的最小切割数。
    // 字符串 “aab” 返回1，因为分割为 aa b
    // 状态：F(i) = s的前i个字符的最小分割次数
    // 状态转移方程：F(i) = j<i && [j+1,i] 是回文串，找出一个 min(F(j) + 1)
    //             F(i): [1,i]是回文串：0
    // 初始状态：F(i) = i - 1
    // 返回结果：F(s.size())  F(s.length())
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0]=0;
        for(int i=1;i<=n;i++){
            dp[i]=i;
            for(int j=0;j<i;j++){
                if(valid(s,j,i-1)){
                    dp[i]=Math.min(dp[i],dp[j]+1);
                }
            }
        }
        return dp[n]-1;
    }
    public boolean valid(String s,int l,int r){
        while(l<r){
            if(s.charAt(l)!=s.charAt(r)){
                return false;
            }
            l++;
            r--;
        }
        return true;
    }


    //给定两个单词 word1 和 word2 计算将 word1 转化为 word2 需要多少步操作
    // 可以在单词中插入一个字符，删掉一个字符，替换一个字符。求最小操作次数
    // 问题：word1 到 word2 编辑距离
    // 子问题：word1 的局部变成 word2 局部需要的编辑距离
    // 状态 F(i,j): word1 前 i 个字符到 word2 的前 j 个字符的编辑距离
    // 状态转移方程：F(i,j)
    public int minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int j = 1; j <= n2; j++) dp[0][j] = dp[0][j - 1] + 1;
        for (int i = 1; i <= n1; i++) dp[i][0] = dp[i - 1][0] + 1;

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
            }
        }
        return dp[n1][n2];
    }
}