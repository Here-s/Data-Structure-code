import java.util.*;

public class Main {

    //寻找第 k 大的元素
    public class Solution {
        public int findKth(int[] a, int n, int K) {
            if(a.length == 0) {
                return -1;
            }
            PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2-o1;
                }
            });

            for(int i = 0; i < a.length; i++) {
                queue.add(a[i]);
            }

            for(int i = 1; i <K ; i++) {
                queue.poll();
            }
            return queue.poll();
        }
    }

    //最小的 k 个数
    public class Solution {
        public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
            ArrayList<Integer> list = new ArrayList<>();
            if(input.length == 0) {
                return list;
            }
            Queue<Integer> queue = new PriorityQueue<>();
            for(int i = 0; i < input.length; i++) {
                queue.add(input[i]);
            }
            for(int i = 0; i < k; i++) {
                list.add(queue.poll());
            }
            return list;
        }
    }

    //滑动窗口最大值
    public class Solution {
        public ArrayList<Integer> maxInWindows(int [] num, int size) {
            Deque<Integer> queue = new ArrayDeque<>();
            ArrayList<Integer> list = new ArrayList<>();
            if(size == 0) {
                return list;
            }
            //放入的是下标，方便比较
            for (int i = 0; i < num.length; i++) {
                while(!queue.isEmpty() && num[queue.peekLast()] < num[i]) {
                    queue.pollLast();
                }
                queue.add(i);
                if(queue.peekFirst() == i - size) {
                    //窗口前面应该缩小了
                    queue.pollFirst();
                }
                //达到窗口大小
                if(i >= size - 1) {
                    list.add(num[queue.peekFirst()]);
                }
            }
            return list;
        }
    }

    //一个数组，里面都是正数，而且没有重复值，一个位置的值，代表一个面值的货币
    // 数组的值代表可以使用当前面值的货币，货币可以无限使用，要组出一个钱数，有多少种方法
    // dp 版本
    class Solution {
        public int way(int[] arr, int aim) {
            if (arr.length == 0) {
                return 0;
            }
            int n = arr.length;
            int[][] dp = new int[n + 1][aim + 1];
            dp[n][0] = 1;
            for (int index = n - 1; index >= 0; index--) {
                for (int col = 0; col <= aim; col++) {
                    dp[index][aim] = dp[index + 1][aim];
                    if (aim - arr[index] >= 0) {
                        dp[index][aim] += dp[index][aim - arr[index]];
                    }
                }
            }
            return dp[0][aim];
        }
    }

    //一个数组，里面都是正数，而且没有重复值，一个位置的值，代表一个面值的货币
    // 数组的值代表可以使用当前面值的货币，货币可以无限使用，要组出一个钱数，有多少种方法
    class Solution {
        //随便使用 index 位置的钱
        public int process(int[] arr, int index, int aim) {
            if (index == arr.length) {
                return aim == 0 ? 1 : 0;
            }
            int ways = 0;
            //arr[index] 加起来不能超过 aim
            for (int num = 0; num * arr[index] <= aim; num++) {
                ways += process(arr, index + 1, aim - arr[index] * num);
            }
            return ways;
        }
        public int way(int[] arr, int aim) {
            return process(arr, 0, aim);
        }
    }

    //给定一个 x 行，y 列的数组，一个人在 n，m 位置，然后给一个参数 k，
    // 这个人每次都可以 上下左右 四个方向走，等概率随机，求最后活下来的概率，只要越界，就会死掉
    class Solution {
        //n * m 的区域，从 row, col 出发，走 k 步之后，获得的生存次数
        public long process(int n, int m, int row, int col, int k) {
            if (row < 0 || row == n || col < 0 || col == m) {
                return 0;
            }
            //走完了，并且 row, col 没越界
            if (k == 0) {
                return 1;
            }
            //没走完，并且没越界
            long live = process(n, m, row - 1, col, k - 1);
            live += process(n, m, row + 1, col, k - 1);
            live += process(n, m, row, col - 1, k - 1);
            live += process(n, m, row, col + 1, k - 1);
            return  live;
        }
        public long gcd(long m, long n) {
            return n == 0? m: gcd(n, m % n);
        }
        public String live(int n, int m, int i, int j, int k) {
            long num = (long)Math.pow(4, k);
            long live = process(n, m, i, j, k);
            long gcd = gcd(num, live);
            return String.valueOf((live / gcd) + "/" + (num / gcd));
        }
    }

    //象棋的马在指定位置出发，然后跳到指定位置，必须跳 n 步，有多少种跳法
    // dp 版本
    class Solution {
        public int getValue(int[][][] dp, int row, int col, int step) {
            if(row < 0 || row > 8 || col < 0 || col > 9) {
                return 0;
            }
            return dp[row][col][step];
        }
        //x 有 9 个格子，y 有 10 个格子
        public int houseJump(int x, int y, int n) {
            if(x < 0 || x > 8 || y < 0 || y > 9) {
                return 0;
            }
            int[][][] dp = new int[9][10][n + 1];
            dp[0][0][0] = 1;
            for (int h = 1; h < n + 1; h++) {
                for (int r = 0; r < 9; r++) {
                    for (int c = 0; c < 10; c++) {
                        dp[r][c][h] = getValue(dp, x + 1, y + 2, n - 1);
                        dp[r][c][h] = getValue(dp, x + 1, y - 2, n - 1);
                        dp[r][c][h] = getValue(dp, x + 2, y + 1, n - 1);
                        dp[r][c][h] = getValue(dp, x + 2, y - 1, n - 1);
                        dp[r][c][h] = getValue(dp, x - 1, y + 2, n - 1);
                        dp[r][c][h] = getValue(dp, x - 1, y - 2, n - 1);
                        dp[r][c][h] = getValue(dp, x - 2, y - 1, n - 1);
                        dp[r][c][h] = getValue(dp, x - 2, y + 1, n - 1);
                    }
                }
            }
            return dp[x][y][n];
        }
    }

    //象棋的马在指定位置出发，然后跳到指定位置，必须跳 n 步，有多少种跳法
    class Solution {
        //潜台词：从（0，0）位置出发，跳到（x，y）位置，必须跳 n 步。所以从 x，y 位置跳回 0，0 也可以
        public int process(int x, int y, int n) {
            if(x < 0 || x > 8 || y < 0 || y > 9) {
                return 0;
            }
            if (n == 0) {
                if (x == 0 && y == 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
            return process(x + 1, y + 2, n - 1) +
                    process(x + 1, y - 2, n - 1) +
                    process(x + 2, y + 1, n - 1) +
                    process(x + 2, y - 1, n - 1) +
                    process(x - 1, y + 2, n - 1) +
                    process(x - 1, y - 2, n - 1) +
                    process(x - 2, y - 1, n - 1) +
                    process(x - 2, y + 1, n - 1);
        }
        //x 有 9 个格子，y 有 10 个格子
        public int houseJump(int x, int y, int n) {
            return process(x, y, n);
        }
    }

    //两人从数组里面左边或者右边拿数字，拿到最大的是赢家，求先手能获得的最优分数是多少
    /**
     * 动态规划版本
     */
    class Solution {
        public int win(int[] arr) {
            if(arr == null || arr.length == 0) {
                return -1;
            }
            int[][] f = new int[arr.length][arr.length];
            int[][] s = new int[arr.length][arr.length];
            for (int i = 0; i < arr.length; i++) {
                f[i][i] = arr[i];
            }
            int row = 0;
            int col = 1;
            //对角线开始填信息
            while (col < arr.length) {
                int i = row;
                int j = col;
                while (i < arr.length && j < arr.length) {
                    f[i][i] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
                    s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
                    i++;
                    j++;
                }
                col++;
            }
            return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
        }
    }

    //两人从数组里面左边或者右边拿数字，拿到最大的是赢家，求先手能获得的最优分数是多少
    class Solution {
        //这里是后手拿
        public int s(int[] arr, int i, int j) {
            if (i == j) {
                return 0;
            }
            //i - j 选最小，因为对手会把最小的留出来
            return  Math.min(func(arr, i + 1, j), func(arr, i, j - 1));
        }
        //这里是先手拿
        public int func(int[] arr, int i, int j) {
            if (i == j) {
                return arr[i];
            }
            //i - j 选最大
            return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));
        }
        public int win(int[] arr) {
            if(arr == null || arr.length == 0) {
                return -1;
            }
            return Math.max(func(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
        }
    }

    //按之字形打印二叉树
    public class Solution {
        public ArrayList Print(TreeNode pRoot) {
            Deque<TreeNode> deque = new LinkedList<>();
            ArrayList<ArrayList<Integer>> res = new ArrayList<>();
            if (pRoot != null) {
                deque.add(pRoot);
            }
            while (!deque.isEmpty()) {
                // 打印奇数层
                ArrayList<Integer> tmp = new ArrayList<>();
                for (int i = deque.size(); i > 0; i--) {
                    // 从左向右打印
                    TreeNode node = deque.removeFirst();
                    tmp.add(node.val);
                    // 先左后右加入下层节点
                    if (node.left != null) {
                        deque.addLast(node.left);
                    }
                    if (node.right != null) {
                        deque.addLast(node.right);
                    }
                }
                res.add(tmp);
                if (deque.isEmpty()) break; // 若为空则提前跳出
                // 打印偶数层
                tmp = new ArrayList<>();
                for (int i = deque.size(); i > 0; i--) {
                    // 从右向左打印
                    TreeNode node = deque.removeLast();
                    tmp.add(node.val);
                    // 先右后左加入下层节点
                    if (node.right != null) {
                        deque.addFirst(node.right);
                    }
                    if (node.left != null) {
                        deque.addFirst(node.left);
                    }
                }
                res.add(tmp);
            }
            return res;
        }
    }

    //数组中的逆序对
    public class Solution {
        int num = 0;
        public int InversePairs(int [] array) {
            int[] temp = new int[array.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
            sort(array, 0, array.length - 1, temp);
            return num;
        }
        private  void sort(int[] array, int left, int right, int []temp) {
            if (left < right) {
                int mid = (left + right) / 2;
                sort(array, left, mid, temp); //左边归并排序，使得左子序列有序
                sort(array, mid + 1, right,
                        temp); //右边归并排序，使得右子序列有序
                merge(array, left, mid, right, temp); //将两个有序子数组合并操作
            }
        }
        private  void merge(int[] array, int left, int mid, int right, int[] temp) {
            int i = left;//左序列指针
            int j = mid + 1; //右序列指针
            int t = 0;//临时数组指针
            while (i <= mid && j <= right) {
                if (array[i] <= array[j]) {
                    temp[t++] = array[i++];
                } else {
                    temp[t++] = array[j++];
                    num = (num + mid - i + 1) % 1000000007;
                }
            }

            while (i <= mid) { //将左边剩余元素填充进temp中
                temp[t++] = array[i++];
            }
            while (j <= right) { //将右序列剩余元素填充进temp中
                temp[t++] = array[j++];
            }
            t = 0;
            //将temp中的元素全部拷贝到原数组中
            while (left <= right) {
                array[left++] = temp[t++];
            }
        }
    }


    //二维数组的查找
    public class Solution {
        public boolean Find(int target, int [][] array) {
            int i = array.length - 1;
            int j = 0;
            while(i >= 0 && j < array[0].length) {
                if(array[i][j] > target) {
                    i--;
                } else if (array[i][j] < target) {
                    j++;
                } else {
                    //找到了
                    return true;
                }
            }
            return false;
        }
    }


    //最终版兑换零钱 dp
    class Solution {
        public int minCoins(int[] arr, int aim) {
            int[][] dp = new int[arr.length - 1][aim + 1];
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j <= aim; j++) {
                    dp[i][j] = -2;
                }
            }
            for (int i = 0; i <= arr.length; i++) {
                dp[i][0] = 0;
            }
            for (int j = 1; j <= aim; j++) {
                dp[arr.length][j] = 0;
            }
            for (int i = arr.length - 1; i >= 0; i--) {
                for (int j = 1; j <= aim; j++) {
                    //没凑够钱，有硬币
                    int p1 = dp[i + 1][j];
                    int p2 = -1;
                    if (aim - arr[i] >= 0) {
                        p2 = dp[i + 1][aim - arr[i]];
                    }

                    if (p1 == -1 && p2 == -1) {
                        dp[i][j] = -1;
                    } else {
                        if (p1 == -1) {
                            dp[i][j] = p2 + 1;
                        }
                        if (p2 == -1) {
                            dp[i][j] = p1;
                        }
                        dp[i][j] = Math.min(p1, p2 + 1);
                    }
                }
            }
            return dp[0][aim];
        }
    }

    //兑换零钱, dp 优化
    //给定数组arr，arr中所有的值都为正整数且不重复。每个值代表一种面值的货币，
    // 每种面值的货币可以使用任意张，再给定一个aim，代表要找的钱数，求组成aim的最少货币数。
    class Solution {
        public int func(int[] arr, int index, int aim, int[][] dp) {
            if (aim < 0) {
                return -1;
            }
            if (dp[index][aim] != -2) {
                return dp[index][aim];
            }

            if (aim == 0) {
                dp[index][aim] = 0;
            } else if (index == arr.length) {
                dp[index][aim] = -1;
            } else {
                int p1 = func(arr, index + 1, aim, dp);
                int p2 = func(arr, index + 1, aim - arr[index], dp) + 1;

                if (p1 == -1 && p2 == -1) {
                    dp[index][aim] = -1;
                } else {
                    if (p1 == -1) {
                        dp[index][aim] = p2 + 1;
                    }
                    if (p2 == -1) {
                        dp[index][aim] = p1;
                    }
                    dp[index][aim] = Math.min(p1, p2 + 1);
                }
            }
            return dp[index][aim];
        }
        public int minCoins(int[] arr, int aim) {
            int[][] dp = new int[arr.length - 1][aim + 1];
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j <= aim; j++) {
                    dp[i][j] = -2;
                }
            }
            return func(arr, 0, aim, dp);
        }
    }

    //兑换零钱
    //给定数组arr，arr中所有的值都为正整数且不重复。每个值代表一种面值的货币，
    // 每种面值的货币可以使用任意张，再给定一个aim，代表要找的钱数，求组成aim的最少货币数。
    class Solution {
        public int func(int[] arr, int index, int aim) {
            if (aim < 0) {
                return -1;
            }
            if (aim == 0) {
                return 0;
            }
            //没凑够钱，没硬币
            if (index == arr.length) {
                return -1;
            }
            //没凑够钱，有硬币
            int p1 = func(arr, index + 1, aim);
            int p2 = func(arr, index + 1, aim - arr[index]) + 1;
            if (p1 == -1 && p2 == -1) {
                return -1;
            } else {
                if (p1 == -1) {
                    return p2 + 1;
                }
                if (p2 == -1) {
                    return p1;
                }
                return Math.min(p1, p2 + 1);
            }
        }
        public int minCoins(int[] arr, int aim) {
            return func(arr, 0, aim);
        }
    }

    //机器人走路，优化之后（记忆化搜索，就是调用到了之前的位置，直接返回之前记录的值就好了）
    // 有两个可变参数是可以确定的，就是 k 和 s ，     k：还剩几步， s：当前位置
    class Solution {
        public int func(int n, int e, int k, int s, int[][] dp) {
            if (dp[k][s] != -1) {
                //之前算过这个逻辑，所以就不用再去算了，直接返回之前算好的值就可以了
                return dp[k][s];
            }
            //缓存没命中
            if (k == 0) {
                //步数走完了
                //记录缓存
                dp[k][s] = s == e ? 1 : 0;
                return dp[k][s];
            }

            //还有步数可以走
            if (s == 1) {
                //到最左边了，所以 s 是 2
                dp[k][s] = func(n, e, k - 1, 2, dp);
                return dp[k][s];
            } else if (s == n) {
                //到最右边了，所以 s 是 n - 1
                dp[k][s] = func(n, e, k - 1, n - 1, dp);
                return dp[k][s];
            } else {
                dp[k][s] = func(n, e, k - 1, s - 1, dp) + func(n, e, k - 1, s + 1, dp);
            }
            return dp[k][s];
        }
        public int walkWays(int n, int e, int s, int k) {
            //因为加了缓存，而且有两个参数，所以就要加一个二维表，第一个是 k ，所以 k + 1 就可以了
            // 第二个是当前位置，在 1 - n 的格子上面，所以是 n + 1
            int[][] dp = new int[k + 1][n + 1];
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[0].length; j++) {
                    dp[i][j] = -1;
                }
            }
            return func(n, e, k, s, dp);
        }
    }
    
    //机器人走路，从开始走到结束位置（一维，有 1-N 段路），走 n 步，有多少种走法
    // 可优化的点：如果之后又调用到了之前的位置，直接拿之前位置的记录值就可以了
    class Solution {
        public int func(int n, int e, int k, int s) {
            if (k == 0) {
                return s == e ? 1 : 0;
            }
            if (s == 1) {
                //到最左边了，所以 s 是 2
                return func(n, e, k - 1, 2);
            }
            if (s == n) {
                //到最右边了，所以 s 是 n - 1
                return func(n, e, k - 1, n - 1);
            }
            return func(n, e, k - 1, s - 1) + func(n, e, k - 1, s + 1);
        }
        public int walkWays(int n, int e, int s, int k) {
            return func(n, e, k, s);
        }
    }

    //接雨水
    class Solution {
        public int trap(int[] height) {
            int maxRain = 0;
            Deque<Integer> queue = new ArrayDeque<>();
            int n = height.length;
            for(int i = 0; i < n; i++) {
                while(!queue.isEmpty() && height[i] > height[queue.peek()]) {
                    //单调栈上面的元素比下面大了
                    int top = queue.poll();
                    if(queue.isEmpty()) {
                        break;
                    }
                    int left = queue.peek();
                    //水池的宽度
                    int width = i - left - 1;
                    int high = Math.min(height[left], height[i]) - height[top];
                    maxRain += width * high;
                }
                queue.push(i);
            }
            return maxRain;
        }
    }

    //滑动窗口的最大值
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || k < 1 || nums.length < k) {
                return null;
            }
            Deque<Integer> queue = new ArrayDeque<>();
            int[] arr = new int[nums.length - k + 1];
            int index = 0;
            for (int i = 0; i < nums.length; i++) {
                while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                    queue.pollLast();
                }
                //小的都清理掉了，放入大的
                queue.addLast(i);
                if (queue.peekFirst() == i - k) {
                    //窗口过期的部分
                    queue.pollFirst();
                }
                if (i >= k - 1) {
                    //达到窗口大小
                    arr[index++] = nums[queue.peekFirst()];
                }
            }
            return arr;
        }
    }

    //最长回文串
    class Solution {
        public int longestPalindrome(String s) {
            char[] ch = s.toCharArray();
            Map<Character, Integer> map = new HashMap<>();
            for(int i = 0; i < ch.length; i++) {
                if (map.containsKey(ch[i])) {
                    //存在 key，然后 +1
                    map.put(ch[i], map.get(ch[i]) + 1);
                } else {
                    //不存在，放到 map 当中
                    map.put(ch[i], 1);
                }
            }
            int max = 0;
            for (char c = 'A'; c <= 'z'; c++) {
                //字符是否在 map 当中
                if (map.containsKey(c)) {
                    if (map.get(c) % 2 == 0) {
                        //出现 2 次，直接加进去
                        max += map.get(c);
                    } else if (map.get(c) > 2) {
                        //大于 2 次，-1 就变成偶数次了
                        max += map.get(c) - 1;
                    }
                }
            }
            if (max < s.length()) {
                //有奇数的情况，奇数放到最中间，再 +1
                max += 1;
            }
            return max;
        }
    }

    //盛最多水的容器
    class Solution {
        public int maxArea(int[] height) {
            int left = 0;
            int right = height.length - 1;
            int max = 0;
            while(left < right) {
                if(height[left] < height[right]) {
                    max = Math.max(max, (right - left) * height[left]);
                    left++;
                } else {
                    max = Math.max(max, (right - left) * height[right]);
                    right--;
                }
            }
            return max;
        }
    }

    //将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序
    class Solution {
        public void moveZeroes(int[] nums) {
            int n = 0;
            //把遇到的非 0 都挪到前面
            for(int i = 0; i < nums.length; i++) {
                if(nums[i] != 0) {
                    nums[n] = nums[i];
                    n++;
                }
            }
            //把后面的都变成 0
            for(int i = n; i < nums.length; i++) {
                nums[i] = 0;
            }
        }
    }

    //一个矩阵中只有0和1两种值，每个位置都可以和自己的上、下、左、右四个位置相连，
    // 如果有一片1连在一起，这个部分叫做一个岛，求一个矩阵中有多少个岛?
    class Solution {
        public void getGrid(char[][] grid, int i, int j, int n, int m) {
            if(grid[i][j] != '1') {
                //说明不是岛屿，直接返回
                return;
            }
            //当前位置变为 2
            grid[i][j] = '2';
            if(i + 1 < n) {
                getGrid(grid, i + 1, j, n, m);
            }
            if(i - 1 >= 0) {
                getGrid(grid, i - 1, j, n, m);
            }
            if(j + 1 < m) {
                getGrid(grid, i, j + 1, n, m);
            }
            if(j - 1 >= 0) {
                getGrid(grid, i, j - 1, n, m);
            }
        }
        public int numIslands(char[][] grid) {
            int num = 0;
            int n = grid.length;
            int m = grid[0].length;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(grid[i][j] == '1') {
                        //是岛屿
                        num++;
                        getGrid(grid, i, j, n, m);
                    }
                }
            }
            return num;
        }
    }

    //规定1和A对应、2和B对应、3和C对应...
    //那么一个数字字符串比如"111"，就可以转化为"AAA”、“KA"和"AK"。
    // 给定一个只有数字字符组成的字符串str，返回有多少种转化结果。
    class Solution {
        public int process(char[] str, int i) {
            if(i == str.length) {
                return 1;
            }
            if(str[i] == '0') {
                return 0;
            }
            if(str[i] == '1') {
                //i 作为单独的部分，后续有多少解法
                int res = process(str, i + 1);
                if(i + 1 < str.length) {
                    //因为 i 是 1，所以还是字符下标，所以可以看看 i 和后面数字组成一队
                    // 也就是直接跳过了 i + 1 的字符，直接去 i + 2 的字符
                    res += process(str, i + 2);
                }
                return res;
            }
            if(str[i] == '2') {
                int res = process(str, i + 1);
                if(i + 1 < str.length && str[i + 1] >= '0' && str[i + 1] <= '6') {
                    res += process(str, i + 2);
                }
                return res;
            }
            //剩下的就是 3 ~ 9 的数字了，不能和后续数字合并了
            return process(str, i + 1);
        }
        public int numDecodings(String s) { //解码方法
            char[] ch = s.toCharArray();
            return process(ch, 0);
        }
    }

    //使用递归逆序栈
    public static int func(Stack<Integer> stack) {
        int num = stack.pop();
        if(stack.isEmpty()) {
            //栈为空，说明 num 已经是栈底的元素了
            return num;
        } else {
            int last = func(stack);
            stack.push(num);
            return last;
        }
    }
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        //拿到栈底的元素
        int num = func(stack);
        //继续去拿
        reverse(stack);
        //然后递归放到栈里面
        stack.push(num);
    }

    //打印一个字符串的全排列
    public class Solution {
        ArrayList<String> res = new ArrayList<>();
        char[] c;
        public ArrayList<String> Permutation(String str) {
            c = str.toCharArray();
            dfs(0);
            return res;
        }
        void dfs(int x) {
            if (x == c.length - 1) {
                //到了最后一个字母，添加排列进去
                res.add(String.valueOf(c));
                return;
            }
            HashSet<Character> set = new HashSet<>();
            for (int i = x; i < c.length; i++) {
                if (set.contains(c[i])) continue; //有重复，直接跳过
                set.add(c[i]);
                //交换剩下的其它字母的位置
                swap(i, x);
                //延展到 x + 1 字符的位置
                dfs(x + 1);
                //恢复交换之前的位置
                swap(i, x);
            }
        }
        void swap(int a, int b) {
            char tmp = c[a];
            c[a] = c[b];
            c[b] = tmp;
        }
    }

    //一个字符串的全部子序列
    class Solution {
        public int distinctSubseqII(String s) {
            int mod = (int)1e9 + 7;
            long result = 0L;

            // 记录26个字符每个字符的子序列总数
            long[] letter = new long[26];
            for (char sc : s.toCharArray()) {

                // 获得字符sc前一次统计的子序列数
                long pre = letter[sc - 'a'];

                // 计算当前字符sc的子序列数
                letter[sc - 'a'] = (result + 1) % mod;

                // 加mod的目的是为了防止结果溢出为负数
                result = (result + letter[sc - 'a'] - pre + mod) % mod;
            }
            return (int)result;
        }
    }

    //汉诺塔问题
    class Solution {
        // 将N个圆盘从A柱经由B柱移动到C柱
        public void func(int i, List<Integer> A, List<Integer> B, List<Integer> C) {
            if(i == 1) {
                //还剩一个圆盘，直接移动到 C 柱子
                C.add(0,A.remove(0));
                return;
            }
            //把 A 柱子最后一个盘子上面的所有盘子都移动到 B 柱子上面
            func(i - 1, A, C, B);
            C.add(0, A.remove(0));
            //把所有 B 柱子上面的盘子都移动到 C 上面
            func(i - 1, B, A, C);
        }
        public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
            int n = A.size();
            func(n, A, B, C);
        }
    }

    //数据流的中位数
    class MedianFinder {

        PriorityQueue<Integer> min ;
        PriorityQueue<Integer> max ;
        /** initialize your data structure here. */
        public MedianFinder() {
            min = new PriorityQueue<>();
            max = new PriorityQueue<>((a,b) -> {return  b - a ;});
        }

        public void addNum(int num) {
            max.add(num);
            min.add(max.remove());
            if (min.size() > max.size()) {
                max.add(min.remove());
            }
        }

        public double findMedian() {
            if (max.size() == min.size()) {
                return (max.peek() + min.peek()) / 2.0;
            } else {
                return max.peek();
            }
        }
    }

    //字母异位词分组
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            List<List<String>> list = new ArrayList<>();
            Map<String, List<String>> map = new HashMap<>();
            for(int i = 0; i < strs.length; i++) {
                char[] arr = strs[i].toCharArray();
                Arrays.sort(arr);
                String key = new String(arr);
                if(map.containsKey(key)) {
                    //有这一类的list
                    map.get(key).add(strs[i]);
                } else {
                    //还没有这一类的list
                    List<String> list1 = new ArrayList<>();
                    list1.add(strs[i]);
                    map.put(key, list1);
                    list.add(list1);
                }
            }
            return list;
        }
    }

    //最多可以参加的会议数目
    class Solution {
        public int maxEvents(int[][] events) {
            Set<Integer> set = new HashSet<>();
            Arrays.sort(events, (first, second) -> first[1]==second[1]?
                    first[0]-second[0]:first[1]-second[1]);

            for(int[] event: events) {
                for(int i = event[0]; i<=event[1]; i++)
                    if(set.add(i)) break;
            }
            return set.size();
        }
    }

    //二叉树的序列化与反序列化
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if(root == null) {
                return "#_";
            }
            String str = root.val + "_";
            str += serialize(root.left);
            str += serialize(root.right);
            return str;
        }

        // Decodes your encoded data to tree.
        public TreeNode getTree(Queue<String> queue) {
            String val = queue.poll();
            if(val.equals("#")) {
                return null;
            }
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = getTree(queue);
            node.right = getTree(queue);
            return node;
        }
        public TreeNode deserialize(String data) {
            String[] strings = data.split("_");
            Queue<String> queue = new ArrayDeque<>();
            for(int i = 0; i < strings.length; i++) {
                queue.offer(strings[i]);
            }
            return getTree(queue);
        }
    }

    //二叉树的公共祖先（优化版本）
    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if(root == null || root == p || root == q) {
                return root;
            }
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if(left == null) {
                return right;
            }
            if(right == null) {
                return left;
            }
            return root;
        }
    }

    //二叉树的最近公共祖先
    class Solution {
        public void process(TreeNode node, HashMap<TreeNode, TreeNode> map) {
            if(node == null) {
                return;
            }
            map.put(node.left, node);
            map.put(node.right, node);
            process(node.left, map);
            process(node.right, map);
        }
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if(root == null) {
                return root;
            }
            HashMap<TreeNode, TreeNode> map = new HashMap<>();
            //每个节点的父节点存在哈希表里面
            map.put(root, root);
            process(root, map);
            TreeNode node = p;
            Set<TreeNode> set1 = new HashSet<>();
            while(node != map.get(node)) {
                set1.add(node);
                node = map.get(node);
            }
            //set1已经放满了 p 的链路
            node = q;
            while(node != map.get(node)) {
                if(set1.contains(node)) {
                    break;
                }
                node = map.get(node);
            }
            return node;
        }
    }

    //是否为完全二叉树
    class Solution {
        public boolean isCompleteTree(TreeNode root) {
            if(root == null) {
                return false;
            }
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            boolean child = false;
            while(!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if(node.left == null && node.right != null) {
                    return false;
                }
                if(child && (node.left != null || node.right != null)) {
                    return false;
                }
                if(node.left == null || node.right == null) {
                    //接下来就都是孩子节点了
                    child = true;
                }
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }
            return true;
        }
    }

    //验证二叉搜索树
    class Solution {
        public void listTree(TreeNode root, List<Integer> list) {
            if(root == null) {
                return;
            }
            listTree(root.left, list);
            list.add(root.val);
            listTree(root.right, list);
        }
        public boolean isValidBST(TreeNode root) {
            if(root == null) {
                return false;
            }
            List<Integer> list = new ArrayList<>();
            listTree(root, list);
            for(int i = 1; i < list.size(); i++) {
                if(list.get(i) <= list.get(i - 1)) {
                    return false;
                }
            }
            return true;
        }
    }

    //中序遍历非递归
    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if(root == null) {
                return list;
            }
            Stack<TreeNode> stack = new Stack<>();
            while(!stack.isEmpty() || root != null) {
                if(root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    list.add(root.val);
                    root = root.right;
                }
            }
            return list;
        }
    }

    //后续遍历非递归
    class Solution {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if(root == null) {
                return list;
            }
            Stack<TreeNode> stack1 = new Stack<>();
            Stack<TreeNode> stack2 = new Stack<>();
            stack1.push(root);
            while(!stack1.isEmpty()) {
                TreeNode node = stack1.pop();
                stack2.push(node);
                if(node.left != null) {
                    stack1.push(node.left);
                }
                if(node.right != null) {
                    stack1.push(node.right);
                }
            }
            while(!stack2.isEmpty()) {
                list.add(stack2.pop().val);
            }
            return list;
        }
    }

    //先序遍历非递归
    class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if(root == null) {
                return list;
            }
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while(!stack.isEmpty()) {
                TreeNode node = stack.pop();
                list.add(node.val);
                if(node.right != null) {
                    stack.push(node.right);
                }
                if(node.left != null) {
                    stack.push(node.left);
                }
            }
            return list;
        }
    }

    //复制带随机指针的链表
    class Solution {
        public Node copyRandomList(Node head) {
            Map<Node, Node> map = new HashMap<>();
            Node node = head;
            while(node != null) {
                map.put(node, new Node(node.val));
                node = node.next;
            }
            Stack<Integer> stack = new Stack<>();

            node = head;
            while(node != null) {
                map.get(node).next = map.get(node.next);
                map.get(node).random = map.get(node.random);
                node = node.next;
            }
            return map.get(head);
        }
    }

    //链表是否是回文结构
    public class Solution {
        /**
         *
         * @param head ListNode类 the head
         * @return bool布尔型
         */
        public boolean isPail (ListNode head) {
            // write code here
            Stack<Integer> stack = new Stack<>();
            ListNode node = head;
            ListNode node2 = head;
            while(node2 != null && node2.next != null) {
                node2 = node2.next.next;
                node = node.next;
            }
            while(node != null) {
                stack.push(node.val);
                node = node.next;
            }
            node = head;
            while(!stack.isEmpty()) {
                if(node.val != stack.pop()) {
                    return false;
                }
                node = node.next;
            }
            return true;
        }
    }

    //归并排序
    public static void process(int[] arr, int l, int r) {
        if (l == r) {
            return ;
        }
        int mid = (l + r) / 2;
        process(arr, l, mid);
        process(arr, mid + 1, r);
        marge(arr, l, mid, r);
    }
    public static void marge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            if (arr[p1] >= arr[p2]) {
                help[i++] = arr[p2++];
            } else {
                help[i++] = arr[p1++];
            }
        }
        while (p1 <= m) {
            //后半部分空了
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
//        System.out.println(Arrays.toString(help));
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }

    //快排
    public static void quickSort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int i = left;
        int j = right;
        int part = arr[i];//基准值
        while (i < j) {
            //右边找到比 num 小的了
            while (arr[j] >= part && i < j) {
                j--;
            }
            if (i < j) {
                arr[i++] = arr[j];
            }
            //左边找到比 part 大的了
            while (arr[i] < part && i < j) {
                i++;
            }
            if (i < j) {
                arr[j--] = arr[i];
            }

        }
        arr[i] = part;
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }

    //递归求最大值
    public static int getMax(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        int mid = (l + r) / 2;
        int left = getMax(arr, l, mid);
        int right = getMax(arr, mid + 1, r);
        return Math.max(left, right);
    }

    //数组中只出现一次的两个数
    public class Solution {
        /**
         * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
         *
         *
         * @param array int整型一维数组
         * @return int整型一维数组
         */
        public int[] FindNumsAppearOnce (int[] array) {
            // write code here
            Set<Integer> set = new HashSet<>();
            int[] arr = new int[2];
            for(int i = 0; i < array.length; i++) {
                if(set.contains(array[i])) {
                    set.remove(array[i]);
                } else {
                    set.add(array[i]);
                }
            }
            int i = 0;
            for(int num : set) {
                arr[i++] = num;
            }
            return arr;
        }
    }

    //二叉树中和为某一值的路径
    public class Solution {
        /**
         *
         * @param root TreeNode类
         * @param sum int整型
         * @return bool布尔型
         */
        public boolean hasPathSum (TreeNode root, int sum) {
            // write code here
            if(root == null) {
                return false;
            }
            if(root.left == null && root.right == null && sum - root.val == 0) {
                return true;
            }
            return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
        }
    }

    //二叉树层序遍历
    public class Solution {
        /**
         *
         * @param root TreeNode类
         * @return int整型ArrayList<ArrayList<>>
         */

        public ArrayList<ArrayList<Integer>> levelOrder (TreeNode root) {
            // write code here

            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            if(root == null) {
                return list;
            }
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            while(!queue.isEmpty()) {

                ArrayList<Integer> list1 = new ArrayList<>();
                int n = queue.size();
                for(int i = 0; i < n; i++) {
                    TreeNode node = queue.poll();
                    list1.add(node.val);
                    if(node.left != null) {
                        queue.add(node.left);
                    }
                    if(node.right != null) {
                        queue.add(node.right);
                    }
                }

                list.add(list1);
            }
            return list;
        }
    }

    //二叉树中序遍历
    public class Solution {
        /**
         * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
         *
         *
         * @param root TreeNode类
         * @return int整型一维数组
         */
        public void order(List<Integer> list, TreeNode node) {
            if(node == null) {
                return;
            }
            order(list, node.left);
            list.add(node.val);
            order(list, node.right);
        }
        public int[] inorderTraversal (TreeNode root) {
            // write code here
            List<Integer> list = new ArrayList<>();
            order(list, root);
            int[] arr = new int[list.size()];
            for(int i = 0; i < arr.length; i++) {
                arr[i] = list.get(i);
            }
            return arr;
        }
    }

    //二叉搜素树的后序遍历序列
    class Solution52 {
        public boolean verifyPostorder(int[] postorder) {
            return recur(postorder, 0, postorder.length - 1);
        }
        boolean recur(int[] postorder, int i, int j) {
            if(i >= j) return true;
            int p = i;
            while(postorder[p] < postorder[j]) p++;
            int m = p;
            while(postorder[p] > postorder[j]) p++;
            return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
        }
    }


    //从上到下每一层从左到右打印在一行，打印二叉树
    class Solution51 {
        public List<List<Integer>> levelOrder(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();
            List<List<Integer>> res = new ArrayList<>();
            if(root != null) queue.add(root);
            while(!queue.isEmpty()) {
                List<Integer> tmp = new ArrayList<>();
                for(int i = queue.size(); i > 0; i--) {
                    TreeNode node = queue.poll();
                    tmp.add(node.val);
                    if(node.left != null) queue.add(node.left);
                    if(node.right != null) queue.add(node.right);
                }
                res.add(tmp);
            }
            return res;
        }
    }


    //从上到下打印二叉树
    class Solution50 {
        public int[] levelOrder(TreeNode root) {
            if(root == null) return new int[0];
            int[] res;
            List<Integer> list = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while(!queue.isEmpty()){
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
            }
            Integer[] arr = list.toArray(new Integer[list.size()]);
            res = new int[arr.length];
            for(int i = 0; i < arr.length; i ++)
                res[i] = arr[i];
            return res;
        }
    }


    //栈的压入，弹出序列
    class Solution49 {
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            Stack<Integer> stack = new Stack<>();
            int i = 0;
            for(int num = 0; num < pushed.length; num++) {
                stack.push(pushed[num]);
                while(!stack.isEmpty() && stack.peek() == popped[i]) {
                    stack.pop();
                    i++;
                }
            }
            return stack.isEmpty();
        }
    }


    //二叉树的最近公共祖先
    class Solution48 {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if(root == null || root == p || root == q) return root;
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if(left == null) return right;
            if(right == null) return left;
            return root;
        }
    }


    //二叉搜索树的最近公共祖先
    class Solution47 {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            while(root != null) {
                if(root.val < p.val && root.val < q.val)
                    root = root.right;
                else if(root.val > p.val && root.val > q.val)
                    root = root.left;
                else break;
            }
            return root;
        }
    }

    //不用加减乘除做加法
    class Solution46 {
        public int add(int a, int b) {
            while(b != 0) {
                int c = (a & b) << 1;
                a ^= b;
                b = c;
            }
            return a;
        }
    }

    //圆圈中最后剩下的数字
    class Solution45 {
        public int f(int n, int m) {
            if (n == 1) {
                return 0;
            }
            int x = f(n - 1, m);
            return (m + x) % n;
        }
        public int lastRemaining(int n, int m) {
            return f(n, m);
        }
    }


    //扑克牌中的顺子
    class Solution44 {
        public boolean isStraight(int[] nums) {
            Set<Integer> set = new HashSet<>();
            int max = 0;
            int min = 14;
            for(int num : nums) {
                if(num == 0) continue;
                max = Math.max(max, num);
                min = Math.min(min, num);
                if(set.contains(num)) {
                    return false;
                }
                set.add(num);
            }
            return max - min < 5;
        }
    }


    //左旋转字符串
    class Solution43 {
        public String reverseLeftWords(String s, int n) {
            return s.substring(n, s.length()) + s.substring(0, n);
        }
    }


    //翻转单词顺序
    class Solution42 {
        public String reverseWords(String s) {
            String[] strings = s.split(" ");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = strings.length - 1; i >= 0; i--) {
                if (strings[i].equals(" ")) {
                    continue;
                }
                stringBuilder.append(strings[i]).append(" ");
            }
            return stringBuilder.toString().trim();
        }
    }


    //和为 s 的两个连续正数序列
    class Solution41 {
        public int[][] findContinuousSequence(int target) {
            int i = 1;
            int j = 1;
            int sum = 0;
            List<int[]> list = new ArrayList<>();
            while (i <= target / 2) {
                if (sum < target) {
                    sum += j;
                    j++;
                } else if (sum > target) {
                    sum -= i;
                    i++;
                } else {
                    int[] arr = new int[j - i];
                    for (int k = i; k < j; k++) {
                        arr[k - i] = k;
                    }
                    list.add(arr);
                    sum -= i;
                    i++;
                }
            }
            return list.toArray(new int[list.size()][]);
        }
    }

    //和为 s 的两个数字
    class Solution40 {
        public int[] twoSum(int[] nums, int target) {
            int i = 0;
            int j = nums.length-1;
            while (i < j) {
                int s = nums[i] + nums[j];
                if (s < target) {
                    i++;
                } else if (s > target) {
                    j--;
                } else {
                    return new int[] {nums[i], nums[j]};
                }
            }
            return nums;
        }
    }

    //平衡二叉树
    class Solution39 {
        public int tree(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = tree(root.left);
            if (left == -1) {
                return -1;
            }
            int right = tree(root.right);
            if (right == -1) {
                return -1;
            }
            return Math.abs(left - right) < 2 ? Math.max(left,right) +1 : -1;
        }
        public boolean isBalanced(TreeNode root) {
            return tree(root) != -1;
        }
    }


    //二叉树的深度
    class Solution38 {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
        }
    }


    //二叉搜索树的第 k 大节点
    class Solution37 {
        int k;
        int num;
        public void mid(TreeNode root) {
            if (root == null) {
                return;
            }
            mid(root.right);
            if (k == 0) {
                return;
            }
            k--;
            if (k == 0) {
                num = root.val;
            }
            mid(root.left);
        }
        public int kthLargest(TreeNode root, int k) {
            this.k = k;
            mid(root);
            return num;
        }
    }


    //0 - n-1 中消失的数字
    class Solution36 {
        public int missingNumber(int[] nums) {
            int i = 0;
            int j = nums.length - 1;
            while(i <= j) {
                int m = (i + j) / 2;
                if(nums[m] == m) {
                    i = m + 1;
                } else {
                    j = m - 1;
                }
            }
            return i;
        }
    }


    //求排序数组当中一个数字出现的次数
    class Solution35 {
        public int helper(int[] nums, int tar) {
        int i = 0, j = nums.length - 1;
        while(i <= j) {
            int m = (i + j) / 2;
            if(nums[m] <= tar) {
                i = m + 1;
            } else {
                j = m - 1;
            }
        }
        return i;
        }
        public int search(int[] nums, int target) {
            return helper(nums, target) - helper(nums, target - 1);
        }
    }


    //两个链表的第一个公共节点
    class Solution34 {
        ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode a = headA;
            ListNode b = headB;
            while (headA != headB) {
                a = a != null ? a.next : headB;
                b = b != null ? b.next : headA;
            }
            return headA;
        }
    }


    //第一个只出现一次的字符
    class Solution33 {
        public char firstUniqChar(String s) {
            HashMap<Character, Boolean> map = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                if (!map.containsKey(s.charAt(i))) {
                    map.put(s.charAt(i), true);
                } else {
                    //已经存在
                    map.put(s.charAt(i), false);
                }
            }
            for (int i = 0; i < s.length(); i++) {
                if (map.get(s.charAt(i))) {
                    return s.charAt(i);
                }
            }
            return ' ';
        }
    }


    //连续子数组的最大和
    class Solution32 {
        public int maxSubArray(int[] nums) {
            int ret = nums[0];
            for(int i = 1; i < nums.length; i++) {
                nums[i] += Math.max(nums[i - 1], 0);
                ret = Math.max(ret, nums[i]);
            }
            return ret;
        }
    }


    //输出最小的 k 个数
    class Solution31 {
        public int[] getLeastNumbers(int[] arr, int k) {
            int[] ret = new int[k];
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            for (int i = 0; i < arr.length; i++) {
                queue.offer(arr[i]);
            }
            for (int i = 0; i < k; i++) {
                ret[i] = queue.poll();
            }
            return ret;
        }
    }


    //数组当中出现次数超过一半的数字
    class Solution30 {
        public int majorityElement(int[] nums) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (!map.containsKey(nums[i])) {
                    map.put(nums[i], 1);
                } else {
                    map.put(nums[i], map.get(nums[i]) + 1);
                    if (map.get(nums[i]) > nums.length / 2) {
                        return nums[i];
                    }
                }
            }
            return nums[0];
        }
    }


    //第二高的薪水
    //select max(salary) as secondHighestSalary from Employee
    // where salary < (select max(Salary) from Employee)


    //查找重复的电子邮箱
    //select email from person group by email having count(email) > 1;


    //超过经理收入的员工
    //select a.name as 'Employee' from employee as a,employee as b
    // where a.managerid = b.id and a.salary > b.salary;


    //组合两个表
    //select firstname,lastname,city,state from person left join
    // address on person.personid = address.personid


    //某网站包含两个表，Customers 表和 Orders 表。编写一个 SQL 查询，找出所有从不订购任何东西的客户。
    //select a.name as 'customers' from customers as a where a.id not in (select customerid from orders);

    //删除链表中的重复元素
    class Solution29 {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode cur = head;
            while(cur != null && cur.next != null) {
                if(cur.val == cur.next.val) {
                    cur.next = cur.next.next;
                } else {
                    cur = cur.next;
                }
            }
            return head;
        }
    }

    //加一
    class Solution28 {
        public int[] plusOne(int[] digits) {
            for (int i = digits.length - 1; i >= 0; i--) {
                digits[i]++;
                digits[i] = digits[i] % 10;
                if (digits[i] != 0) return digits;
            }
            digits = new int[digits.length + 1];
            digits[0] = 1;
            return digits;
        }
    }

    //最后一个单词的长度
    class Solution27 {
        public int lengthOfLastWord(String s) {
            String[] strings = s.split(" ");
            return strings[strings.length - 1].length();
        }
    }

    //最长公共前缀
    class Solution26 {
        public String longestCommonPrefix(String[] strs) {
            if(strs.length == 0)
                return "";
            String ans = strs[0];
            for(int i =1;i<strs.length;i++) {
                int j=0;
                for(;j<ans.length() && j < strs[i].length();j++) {
                    if(ans.charAt(j) != strs[i].charAt(j))
                        break;
                }
                ans = ans.substring(0, j);
                if(ans.equals(""))
                    return ans;
            }
            return ans;
        }
    }

    //对称二叉树
    class Solution25 {
        public boolean check(TreeNode leftTree, TreeNode rightTree) {
            if (leftTree == null && rightTree == null) {
                return true;
            }
            if (leftTree == null || rightTree == null) {
                return false;
            }
            return leftTree.val == rightTree.val && check(leftTree.left, rightTree.right) && check(leftTree.right, rightTree.left);
        }
        public boolean isSymmetric(TreeNode root) {
            return check(root, root);
        }
    }

    //二叉树的镜像
    class Solution24 {
        public TreeNode mirrorTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode tmp = root.left;
            root.left = mirrorTree(root.right);
            root.right = mirrorTree(tmp);
            return root;
        }
    }

    //两个栈实现队列
    class CQueue {
        Stack<Integer> stack1;
        Stack<Integer> stack2;
        public CQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }
        public void appendTail(int value) {
            stack1.push(value);
        }
        public int deleteHead() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    int key = stack1.pop();
                    stack2.push(key);
                }
            }
            if (stack2.isEmpty()) {
                return -1;
            }
            return stack2.pop();
        }
    }

    //重建二叉树，根据先序和后续
    class Solution23 {
        public int tmp = 0;
        public TreeNode build(int[] preorder, int start, int[] inorder, int end) {
            if(start > end) {
                return null;
            }
            TreeNode root = new TreeNode((char) preorder[tmp]);
            int rootIndex = findLocation(inorder, start, end, preorder[tmp]);
            if(rootIndex == -1) {
                return null;
            }
            tmp++;
            root.left = build(preorder, start, inorder, rootIndex - 1);
            root.right = build(preorder, rootIndex + 1, inorder, end);
            return root;
        }
        public int findLocation(int[] inorder, int start, int end, int key) {
            for(int i = start; i <= end; i++) {
                if(inorder[i] == key) {
                    return i;
                }
            }
            return -1;
        }
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if(preorder == null || inorder == null) {
                return null;
            }
            return build(preorder, 0, inorder, preorder.length - 1);
        }
    }

    //根据前序遍历贺中序遍历构造二叉树
    class Solution22 {
        public int preIndex = 0;
        public TreeNode createTreeByPandI(int[] preorder, int[] inorder, int inbegin, int inend) {
            if(inbegin > inend) {
                return null;
            }
            TreeNode root = new TreeNode((char)preorder[preIndex]);
            int rootIndex = findIndexOfI(inorder, inbegin, inend,preorder[preIndex]);
            if(rootIndex == -1) {
                return null;
            }
            preIndex++;
            root.left = createTreeByPandI(preorder, inorder, inbegin, rootIndex - 1);
            root.right = createTreeByPandI(preorder, inorder, rootIndex + 1, inend);
            return root;
        }
        private int findIndexOfI(int[] inorder, int inbegin, int inend, int key) {
            for(int i = inbegin; i <= inend; i++) {
                if(inorder[i] == key) {
                    return i;
                }
            }
            return -1;
        }
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if(preorder == null || inorder == null) {
                return null;
            }
            return createTreeByPandI(preorder, inorder, 0, inorder.length - 1);
        }
    }

    //二叉树的层序遍历
    class Solution17 {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> ret = new ArrayList<>();
            if (root == null) {
                return ret;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> list = new ArrayList<>();
                while (size != 0) {
                    TreeNode cur = queue.poll();
                    list.add((int) cur.val);
                    if (cur.left != null) {
                        queue.offer(cur.left);
                    }
                    if (cur.right != null) {
                        queue.offer(cur.right);
                    }
                    size--;
                }
                ret.add(list);
            }
            return ret;
        }
    }

    //相同的树
    class Solution16 {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if(p == null && q == null) {
                return true;
            } else if(p != null && q == null || q != null && p == null) {
                return false;
            } else if(p.val != q.val) {
                return false;
            } else {
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            }
        }
    }

    //年终奖
    public int getMost(int[][] board) {
        int row = board.length;
        int col = board[0].length;
        for(int i = 1; i < col; i++) {
            board[0][i] += board[0][i-1];
        }
        for(int i = 1; i < row; i++) {
            board[i][0] += board[i-1][0];
        }
        for(int i = 1; i < row; i++) {
            for(int j = 1; j < col; j++) {
                board[i][j] += Math.max(board[i-1][j], board[i][j-1]);
            }
        }
        return board[row-1][col-1];
    }

    //微信红包
    public static int getValue(int[] gifts, int n) {
        int num = 0;
        int count = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < gifts.length; i++) {
            if (map.get(gifts[i]) == null) {
                map.put(gifts[i], 1);
            } else {
                map.put(gifts[i], map.get(gifts[i]) + 1);
            }
            if (map.get(gifts[i]) > gifts.length / 2) {
                return gifts[i];
            }
        }
        return 0;
    }

    public static void main98(String[] args) {
        int[] arr = {1,2,3,2,2};
        int n = 5;
        System.out.println(getValue(arr, n));
    }

    //二叉树中序遍历
    class Solution14 {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<Integer>();
            inorder(root, list);
            return list;
        }
        public void inorder(TreeNode root, List<Integer> list) {
            if (root == null) {
                return;
            }
            inorder(root.left, list);
            inorder(root.right, list);
        }
    }

    //合并两个有序数组
    class Solution13 {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            for (int i = 0; i != n; ++i) {
                nums1[m + i] = nums2[i];
            }
            Arrays.sort(nums1);
        }
    }

    //搜索插入位置
    class Solution19 {
        public int searchInsert(int[] nums, int target) {
            int left = 0;
            int right = nums.length;
            while(left < right) {
                int mid = (left + right) / 2;
                if(nums[mid] == target) {
                } else if(nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return 0;
        }
    }

    //字符串反转
    public static void main97(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder(scanner.nextLine());
        System.out.println(stringBuilder.reverse());
    }

    //移除元素
    class Solution10 {
        public int removeElement(int[] nums, int val) {
            int left = 0;
            for (int right = 0; right < nums.length; right++) {
                if (nums[right] != val) {
                    nums[left] = nums[right];
                    left++;
                }
            }
            return left;
        }
    }

    //删除数组当中的重复项
    class Solution11 {
        public int removeDuplicates(int[] nums) {
            int p = 0;
            int q = 1;
            while(q < nums.length){
                if(nums[p] != nums[q]){
                    nums[p + 1] = nums[q];
                    p++;
                }
                q++;
            }
            return p + 1;
        }
    }

    //汽水瓶
    public static void main96(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            if (n == 0) {
                break;
            }
            int count = 0;
            while (n >= 3) {
                count += n / 3;
                int num = n % 3;
                n /= 3;
                n += num;
            }
            if (n == 2) {
                count++;
            }
            System.out.println(count);
        }
    }

    //最长回文子串
    class Solution12 {
        public int longCount(String s, int left, int right) {
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                --left;
                ++right;
            }
            return right - left - 1;
        }
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 1) {
                return "";
            }
            int start = 0, end = 0;
            for (int i = 0; i < s.length(); i++) {
                int len1 = longCount(s, i, i);
                int len2 = longCount(s, i, i + 1);
                int len = Math.max(len1, len2);
                if (len > end - start) {
                    start = i - (len - 1) / 2;
                    end = i + len / 2;
                }
            }
            return s.substring(start, end + 1);
        }
    }

    //统计每个月兔子的总数
    public static void main95(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        n--;
        int a = 0;
        int b = 1;
        int c = a + b;
        while (n != 0) {
            c = a + b;
            a = b;
            b = c;
            n--;
        }
        System.out.println(c);
    }

    //计算某字符出现的次数
    public static void main94(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] line = scanner.nextLine().split("");
        String s = scanner.next();
        int count = 0;
        if (s.charAt(0) >= 'A' && s.charAt(0) <= 'Z') {
            String s2 = s.toLowerCase();
            for (int i = 0; i < line.length; i++) {
                if (line[i].equals(s) || line[i].equals(s2)) {
                    count++;
                }
            }
        } else {
            String s2 = s.toUpperCase();
            for (int i = 0; i < line.length; i++) {
                if (line[i].equals(s) || line[i].equals(s2)) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    //杨辉三角变形
    public static void main93(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n <= 2) {
            System.out.println(-1);
        } else if (n % 2 == 1) {
            System.out.println(2);;
        } else if (n % 4 == 0) {
            System.out.println(3);
        } else {
            System.out.println(4);
        }
    }

    //扑克牌大小
    public static void main92(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] pai = scanner.nextLine().split("-");
        String[] pai1 = pai[0].split(" ");
        String[] pai2 = pai[1].split(" ");
        String s = "34567891jJQKA2";
        if (pai[0].equals("joker JOKER") || pai[1].equals("joker JOKER")) {
            System.out.println("joker JOKER");
        } else if (pai1.length == pai2.length) {
            if (s.indexOf(pai1[0].substring(0, 1)) > s.indexOf(pai2[0].substring(0, 1))) {
                System.out.println(pai[0]);
            } else {
                System.out.println(pai[1]);
            }
        } else if (pai1.length == 4) {
            System.out.println(pai[0]);
        } else if (pai2.length == 4) {
            System.out.println(pai[1]);
        } else {
            System.out.println("ERROR");
        }
    }

    //完全数计算
    public static void main91(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int count = 0;
        for (int i = 2; i <= n; i++) {
            int sum = 0;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    if (i / j == j) {
                        sum += j;
                    } else {
                        sum = sum + i / j + j;
                    }
                }
            }
            if (sum + 1 == i) {
                count++;
            }
        }
        System.out.println(count);
    }

    //手套牛客
    public class Gloves {
        public int findMinimum(int n, int[] left, int[] right) {
            int leftSum = 0;
            int rightSum = 0;
            int sum = 0;
            int leftMin = Integer.MAX_VALUE;
            int rightMin = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (left[i] * right[i] == 0) {
                    sum += left[i] + right[i];
                } else {
                    leftSum += left[i];
                    rightSum += right[i];
                    if (leftMin > left[i]) {
                        leftMin = left[i];
                    }
                    if (rightMin > right[i]) {
                        rightMin = right[i];
                    }
                }
            }
            return Math.min(leftSum - leftMin + 1,rightSum - rightMin + 1) + sum +1;
        }
    }

    //查找输入整数二进制中的 1 的个数
    public static void main90 (String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            int count = 0;
            while (n != 0) {
                if (n % 2 == 1) {
                    count++;
                }
                n /= 2;
            }
            System.out.println(count);
        }
    }

    //根据输入日期，计算当前是这一年第几天
    public static void main89(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        int month = scanner.nextInt();
        int day = scanner.nextInt();
        int[] days = {31,28,31,30,31,30,31,31,30,31,30,31};
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            days[1] = 29;
        }
        int sum = 0;
        for (int i = 0; i < month - 1; i++) {
            sum += days[i];
        }
        sum += day;
        System.out.println(sum);
    }

    //参数解析
    public static void main88(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int count = 1;
        int i = 0;
        while (i < str.length()) {
            if (str.charAt(i) == '"') {
                i++;
                while (i < str.length() && str.charAt(i) != '"') {
                    i++;
                }
            }
            if (str.charAt(i) == ' ') {
                count++;
            }
            i++;
        }
        System.out.println(count);
        i = 0;
        int flag = 1;
        while (i < str.length()) {
            //碰到第一个双引号
            if (str.charAt(i) == '"') {
                flag ^= 1;
            }
            //不是双引号和空格就输出
            if (str.charAt(i) != ' ' && str.charAt(i) != '"') {
                System.out.print(str.charAt(i));
            }
            //双引号里面的要输出
            if (str.charAt(i) == ' ' && flag == 0) {
                System.out.print(str.charAt(i));
            }
            //双引号以外的空格换行
            if (str.charAt(i) == ' ' && flag == 1) {
                System.out.println();
            }
            i++;
        }
    }

    //查找组成一个偶数最接近的两个素数
    public static boolean isPrime1(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static void main87(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = n / 2; i >= 0; i--) {
            if (isPrime1(i) && isPrime1(n - i)) {
                System.out.println(i);
                System.out.println(n - i);
                break;
            }
        }
    }

    //二进制插入：给定两个32位整数n和m，同时给定i和j，将m的二进制数位插入到n的二进制的第j到第i位,
    // 保证n的第j到第i位均为零，且m的二进制位数小于等于i-j+1，其中二进制的位数从0开始由低到高。
    public int binInsert(int n, int m, int j, int i) {
        return n | (m << j);
    }

    //调整数组顺序使奇数位于偶数前面
    class Solution7 {
        public int[] exchange(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            while(left < right) {
                while(left < right && nums[left] % 2 == 1) {
                    left++;
                }
                while(left < right && nums[right] % 2 == 0) {
                    right--;
                }
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
            }
            return nums;
        }
    }

    //在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，
    // 每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，
    // 输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
    class Solution2 {
        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            int i = matrix.length - 1, j = 0;
            while(i >= 0 && j < matrix[0].length)
            {
                if(matrix[i][j] > target) i--;
                else if(matrix[i][j] < target) j++;
                else return true;
            }
            return false;
        }
    }

    //反转链表，要求 空间复杂度：O（1）  时间复杂度：O（N）
    class Solution1 {
        public ListNode reverseList(ListNode head) {
            ListNode cur = head;
            ListNode prev = null;
            while (head != null) {
                head = head.next;
                cur.next = prev;
                prev = cur;
                cur = head;

            }
            return prev;
        }
    }

    public static void main86(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] s1 = scanner.nextLine().split("#");
        char[] ch = s1[0].toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] >= 'A' && ch[i] <= 'Z') {
                //大写
                String c = String.valueOf(ch[i]);
                stringBuilder.append(c.toLowerCase());
            } else if (ch[i] >= 'a' && ch[i] <= 'z') {
                //小写
                String a = String.valueOf(ch[i]);
                stringBuilder.append(a.toUpperCase());
            } else {
                stringBuilder.append(ch[i]);
            }
        }
        System.out.println(stringBuilder);
    }

    //把一个指数转化为二进制，求连续的 1 的个数
    public static void main85(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        StringBuilder stringBuilder = new StringBuilder();
        while (n != 0) {
            stringBuilder.append(n % 2);
            n /= 2;
        }
        int max = 0;
        String[] s = stringBuilder.toString().split("");
        int i = 0;
        while (i < s.length) {
            int count = 0;
            if (s[i].equals("1")) {
                count++;
                i++;
                while (i < s.length && s[i].equals("1")) {
                    count++;
                    i++;
                }
            }
            if (count >= max) {
                max = count;
            }
            i++;
        }
        System.out.println(max);
    }

    //求二叉树节点最近公共祖先
    public class LCA {
        public int getLCA(int a, int b) {
            while (a != b) {
                if (a > b) {
                    a /= 2;
                } else {
                    b /= 2;
                }
            }
            return a;
        }
    }

    //密码强度等级牛客
    public static void main84(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int sum = 0;
        //判断密码长度
        if (s.length() <= 4) {
            sum += 5;
        } else if (s.length() <= 7) {
            sum += 10;
        } else {
            sum += 25;
        }
        //判断字母
        int small = 0;
        int big = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                small++;
            } else if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                big++;
            }
        }
        if (small == 0 && big == 0) {
            sum += 0;
        } else if (small == 0 && big != 0) {
            sum += 10;
        } else if (small != 0 && big == 0) {
            sum += 10;
        } else {
            sum += 20;
        }
        //数字
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                num++;
            }
        }
        if (num == 0) {
            sum += 0;
        } else if (num == 1) {
            sum += 10;
        } else {
            sum += 20;
        }
        //符号
        String ch = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
        int countCh = 0;
        String[] s1 = s.split("");
        for (int i = 0; i < s.length(); i++) {
            if (ch.contains(s1[i])) {
                countCh++;
            }
        }
        if (countCh == 0) {
            sum += 0;
        } else if (countCh == 1) {
            sum += 10;
        } else {
            sum += 25;
        }

        if (small != 0 && big != 0 && num != 0 && countCh != 0) {
            sum += 5;
        } else if ((small != 0 || big != 0) && num != 0 && countCh != 0) {
            sum += 3;
        } else if ((small != 0 || big != 0) && num != 0) {
            sum += 2;
        }
        //输出
        if (sum >= 90) {
            System.out.println("VERY_SECURE");
        } else if (sum >= 80) {
            System.out.println("SECURE");
        } else if (sum >= 70) {
            System.out.println("VERY_STRONG");
        } else if (sum >= 60) {
            System.out.println("STRONG");
        } else if (sum >= 50) {
            System.out.println("AVERAGE");
        } else if (sum >= 25) {
            System.out.println("WEAK");
        } else {
            System.out.println("VERY_WEAK");
        }
    }

    //井字棋，判断 1 的玩家是否胜出
    public class Board {
        public boolean checkWon(int[][] board) {
            if (board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][0] == 1) {
                return true;
            } else if (board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][0] == 1) {
                return true;
            } else if (board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][0] == 1) {
                return true;
            } else if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] == 1) {
                return true;
            } else if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] == 1) {
                return true;
            }
            return false;
        }
    }

    //计算 n*m 的棋盘格子，n 横向，m 纵向。从左上角走到右下角共有多少种走法，
    // 只能往右和往下走。
    public static int count(int n, int m) {
        if (n == 1 || m == 1) {
            return m+n;
        }
        return count(n - 1, m) + count(n, m - 1);
    }
    public static void main83(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.println(count(n, m));
    }

    //给定两个整形，编写一个函数返回 A+B，不能使用 + 和其它算术运算符
    public class UnusualAdd {
        public int addAB(int A, int B) {
            int sum = 0;
            while (B != 0) {
                sum = A ^ B;
                B = (A & B) << 1;
                A = sum;
            }
            return A;
        }
    }

    //求两个整数的最小公倍数
    public static void main82(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = b;
        while (true) {
            if (c % b == 0 && c % a == 0) {
                System.out.println(c);
                break;
            }
            c++;
        }
    }

    //根据 字典序，和 字符串长度排序
    public static void main81(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String noon = scanner.nextLine();
        String[] strings = new String[n];
        int dire = 1;
        int len = 1;
        for (int i = 0; i < n; i++) {
            strings[i] = scanner.nextLine();
        }
        for (int i = 0; i < n - 1; i++) {
            if (strings[i].compareTo(strings[i + 1]) > 0) {
                dire = 0;
            }
            if (strings[i].length() > strings[i + 1].length()) {
                len = 0;
            }
        }
        if (dire == 0 && len == 0) {
            System.out.println("none");
        } else if (dire == 0) {
            System.out.println("lengths");
        } else if (len == 0) {
            System.out.println("lexicographically");
        } else if (len == 1 && dire == 1) {
            System.out.println("both");
        }
    }


    public static void main80(String[] args) {
        System.out.println(new B().getValue());
    }
    static class A{
        protected int value;
        public A(int v) {
            setValue(v);
        }
        public void setValue(int value) {
            this.value = value;
        }
        public int getValue() {
            try {
                value++;
                return value;
            } catch (Exception e) {
                System.out.println(e.toString());
            } finally {
                this.setValue(value);
                System.out.println(value);
            }
            return value;
        }
    }
    static class B extends A{
        public B() {
            super(5);
            setValue(getValue() - 3);
        }
        public void setValue(int value) {
            super.setValue(2 * value);
        }
    }


    //多线程按顺序输出 c b a
    public static void main79(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("a");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("b");
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("c");
            }
        });
        t3.start();
        t3.join();
        t2.start();
        t2.join();
        t1.start();
    }
    //判断是否是合法括号
    public class Parenthesis {
        public boolean chkParenthesis(String A, int n) {
            String[] arr = A.split("");
            String s = "{}()[]";
            Stack<String> stack1 = new Stack<>();
            Stack<String> stack2 = new Stack<>();
            for (int i = 0; i < n; i++) {
                if (!s.contains(arr[i])) {
                    return false;
                }
                stack1.push(arr[i]);
            }
            return true;
        }
    }

    //将一个数变成斐波那契数需要多少步
    public static void main78(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int count = 0;
        int a = 0;
        int b = 1;
        int c = a + b;
        while (c < num) {
            a = b;
            b = c;
            c = a + b;
        }
        int k = num - b;
        int j = c - num;
        System.out.println(Math.min(k, j));
    }
    public static void main77(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int table = scanner.nextInt();
        int custCount = scanner.nextInt();
        int[] tablePeople = new int[table];
        for (int i = 0; i < table; i++) {
            tablePeople[i] = scanner.nextInt();
        }
        Arrays.sort(tablePeople);
        //两个参数，客人和消费额
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        //先放钱再放人
        int[] moneys = new int[custCount];
        for (int i = 0; i < custCount; i++) {
            int number = scanner.nextInt();
            int money = scanner.nextInt();
            moneys[i] = money;
            hashMap.put(money, number);
        }
        Arrays.sort(moneys);
        long sum = 0;
        for (int i = 0; i < custCount; i++) {
            //取出最大的 钱 对应的人
            int needTable = hashMap.get(moneys[custCount - i - 1]);
            for (int j = 0; j < table ;j++) {
                if (tablePeople[j] >= needTable) {
                    //说明可以坐下吃饭
                    sum += moneys[custCount - i - 1];
                    tablePeople[j] = 0;
                    break;
                }
            }
        }
        System.out.println(sum);
    }
    public ArrayList<Integer> GetLeastNumbers_Solution (int[] input, int k) {
        Arrays.sort(input);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0;i < k; i++) {
            list.add(input[i]);
        }
        return list;
    }
    public static void main76(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] s = scanner.nextLine().split("");
        String number = "1234567890";
        HashMap<Integer, StringBuilder> hashMap = new HashMap<>();
        int i = 0;
        int max = 0;
        while (i < s.length) {
            int count = 0;
            StringBuilder stringBuilder = new StringBuilder();
            if (number.contains(s[i])) {
                count++;
                stringBuilder.append(s[i]);
                i++;
                while (i < s.length && number.contains(s[i])) {
                    stringBuilder.append(s[i]);
                    count++;
                    i++;
                }
                if (max < count) {
                    max = count;
                }
                hashMap.put(count, stringBuilder);
            }
            i++;
        }
        System.out.println(hashMap.get(max));
    }
    public static void main75(String[] args) {
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
        int h = in.nextInt();
        int count = 0;
        int[][] arr = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (arr[i][j] == 0) {
                    count++;
                    if(i + 2 < w) {
                        arr[i + 2][j] = 1;
                    }
                    if(j + 2 < h) {
                        arr[i][j + 2] = 1;
                    }
                }

            }
        }
        System.out.println(count);
    }
    public int StrToInt(String str) {
        if (str.equals("")) {
            return 0;
        }
        String s1 = "1234567890+-/";
        String[] s2 = str.split("");
        if (s2[0].equals("+")) {
            if (s2.length == 1) {
                return 0;
            }
            str = str.substring(1, str.length());
            return Integer.parseInt(str);
        }
        if (s2[0].equals("-")) {
            if (s2.length == 1) {
                return 0;
            }
            str = str.substring(1, str.length());
            return -Integer.parseInt(str);
        }
        for (int i = 0; i < s2.length; i++) {
            if (!s1.contains(s2[i])) {
                return 0;
            }
        }
        if (!str.contains("/")) {
            return Integer.parseInt(str);
        }
        String[] strings = str.split("/");
        return Integer.parseInt(strings[0]) / Integer.parseInt(strings[1]);
    }
    public static void main74(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int sum = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum = Math.max(sum + arr[i], arr[i]);
            if (sum > max) {
                max = sum;
            }
        }
        System.out.println(max);
    }
    public static void main73(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] s1 = scanner.nextLine().split("");
        String s2 = scanner.nextLine();
        int i = 0;
        int count = 0;
        while (i <= s1.length) {
            StringBuilder stringBuilder = new StringBuilder();
            int j = 0;
            for (; j < i; j++) {
                stringBuilder.append(s1[j]);
            }
            stringBuilder.append(s2);
            for (; j < s1.length; j++) {
                stringBuilder.append(s1[j]);
            }
            StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
            stringBuilder1 = stringBuilder1.reverse();
            if (stringBuilder1.toString().equals(stringBuilder.toString())) {
                count++;
            }
            i++;
        }
        System.out.println(count);
    }
    public static void main72(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int flag = 0;
        if (m < 0) {
            flag = 1;
            m = -m;
        }
        HashMap<Integer, Character> hashMap = new HashMap<>();
        hashMap.put(10, 'A');
        hashMap.put(11, 'B');
        hashMap.put(12, 'C');
        hashMap.put(13, 'D');
        hashMap.put(14, 'E');
        hashMap.put(15, 'F');
        StringBuilder stringBuilder = new StringBuilder();
        while (m != 0) {
            int k = m % n;
            if (k > 9) {
                stringBuilder.append(hashMap.get(k));
            } else {
                stringBuilder.append(k);
            }
            m /= n;
        }
        if (flag == 1) {
            stringBuilder.append("-");
        }
        stringBuilder.reverse();
        System.out.println(stringBuilder);
    }
    public static void main71(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n1 = scanner.nextInt();//a-b
        int n2 = scanner.nextInt();//b-c
        int n3 = scanner.nextInt();//a+b
        int n4 = scanner.nextInt();//b+c
        //n1+n3 = 2a   n2+n4 = 2b
        int a = (n1 + n3) / 2;
        int b = (n2 + n4) / 2;
        int c = b - n2;
        if ((n1 == a - b) && (n2 == b - c) && (n3 == a + b) && (n4 == b + c)) {
            System.out.println(a + " " + b + " " + c);
        } else {
            System.out.println("No");
        }
    }
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode newHead = head;
        Stack<ListNode> stack = new Stack();
        while (newHead != null) {
            stack.push(newHead);
            newHead = newHead.next;
        }
        while (!stack.isEmpty())
        for (int i = 0; i < k; i++) {
            head = stack.pop();
        }
        return head;
    }

    public static int[] exchange(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while(left < right) {
            while(left < right && nums[left] % 2 == 1) {
                left++;
            }
            while(left < right && nums[right] % 2 == 0) {
                right--;
            }
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
        }
        return nums;
    }

    public static void main70(String[] args) {
        int[] arr = {1,2,3,4};
        exchange(arr);
    }
    public static int MoreThanHalfNum_Solution(int [] array) {
        Arrays.sort(array);
        int i = 0;
        while (i < array.length - 1) {
            int count = 1;
            int num = 0;
            if (array[i] == array[i + 1]) {
                num = array[i];
                count++;
                i++;
                while(i < array.length - 1 && array[i] == array[i + 1]) {
                    count++;
                    i++;
                }
                if (count > array.length/2) {
                    return num;
                }
            }
            i++;
        }
        return array[0];
    }

    public static void main69(String[] args) {
        int[] arr = {1};
        int n = MoreThanHalfNum_Solution(arr);
        System.out.println(n);
    }
    public static void main68(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] s = in.nextLine().split("");
        String s1 = "0123456789";
        int i = 0;
        int num = 0;
        HashMap<Integer, StringBuilder> hashMap = new HashMap<>();
        while (i < s.length) {
            StringBuilder stringBuilder = new StringBuilder();
            int count = 0;
            if(s1.contains(s[i])) {
                stringBuilder.append(s[i]);
                count++;
                i++;
                while (i < s.length && s1.contains(s[i])) {
                    stringBuilder.append(s[i]);
                    count++;
                    i++;
                }
                if (count > num) {
                    num = count;
                    hashMap.put(num, stringBuilder);
                }
            }
            i++;
        }
        System.out.println(hashMap.get(num));
    }

    public static void main67(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            if (n == 0) {
                break;
            }
            int count = 0;
            while (n >= 3) {
                count += n / 3;
                int num = n % 3;
                n /= 3;
                n += num;
            }
            if (n == 2) {
                count++;
            }
            System.out.println(count);
        }
    }

    public static void main66(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int count = 0;
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j < i; j++) {
                if(i%j==0){
                    sum+=j;
                }
            }
            if (sum == i) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static void main65(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] str = scanner.nextLine().split(" ");
        int[] arr = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
        int sum = 0;
        if (Integer.parseInt(str[0]) % 400 == 0 ||
                (Integer.parseInt(str[0]) % 4 == 0 && Integer.parseInt(str[0]) % 100 != 0)) {
            //说明是闰年
            arr[1] += 1;
        }
        for (int i = 0; i < Integer.parseInt(str[1]) - 1; i++) {
            sum += arr[i];
        }
        sum += Integer.parseInt(str[2]);
        System.out.println(sum);
    }

    public static void main64(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int[][] arr = new int[N][M];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }
        System.out.println(13);
    }

    public static void main63(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //N 表示距离
        int N = scanner.nextInt();
        //M 红绿灯个数
        int M = scanner.nextInt();
        //K 通过 K 个红绿灯之后就可氮气瞬移
        int K = scanner.nextInt();
        //最高速度 1/V
        double V = scanner.nextDouble();
        V = 1.0/V;
        //数组三个元素表示：第 i 个红绿灯居家多远，绿灯时间，红灯时间
        int[][] arr = new int[M][3];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }
        double time = 0;
        boolean danqi = true;
        boolean tiaochu = false;
        while (N > 0) {
            for (int i = 0; i < M-1; i++) {

                if (danqi == true) {
                    N -= arr[i][0];
                    danqi = false;
                }
                if (N <= 0){
                    tiaochu = true;
                    break;
                }
                int len = arr[i+1][0] - arr[i][0];
                time += len/V;
                double a = time;
                int flag = 0;
                while (true) {
                    if (a < arr[i+1][1] || a / arr[i+1][1] == 0) {
                        flag = 1;
                        break;
                    }
                    a -= arr[i+1][1];
                    if (a < arr[i+1][2] || a / arr[i+1][2] == 0) {
                        flag = 2;
                        break;
                    }
                    a -= arr[i+1][2];
                }
                danqi = true;
                if (i % 2 == 1) {
                    N -= len;
                }
                if (flag == 2) {
                    time += arr[i+1][2] - a;
                    N -= len;
                }
            }
            if (tiaochu == true) {
                break;
            }
        }
        System.out.println(time);
    }


    public static int cut(int[] arr, int j) {
        int n = j;
        int k = 0;
        int sum = 0;
        while (n <= arr.length) {
            int[] arr1 = new int[j];
            int a = 0;
            for (int i = k; i < n; i++) {
                arr1[a] = arr[i];
                a++;
            }
            Arrays.sort(arr1);
            boolean flag = true;
            for (int i = 1; i < arr1.length; i++) {
                if (Math.abs(arr1[i] - arr1[i - 1]) > 1) {
                    flag = false;
                }
            }
            if (flag == true) {
                sum += 1;
            }
            k++;
            n++;
        }
        return sum;
    }
    public static void main62(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }
        int count = 1;
        for (int i = 2; i <= N; i++) {
            int num = cut(arr,i);
            count += num;
        }
        System.out.println(count);
    }

    public static void main61(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int[][] arr = new int[N][M];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }
        int limit = scanner.nextInt();

    }

    public static boolean judge(long num, int n) {
        while (n != 0) {
            if (num % 10 == 0) {
                n--;
            } else {
                return false;
            }
            num /= 10;
        }
        return true;
    }
    public static void main60(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int K = scanner.nextInt();
        long sum = 1;
        long count = 1;
        while (true) {
            count++;
            sum *= count;
            if (judge(sum,K)) {
                System.out.println(count);
                return;
            }
        }
    }

    public static int count(int[] arr,int n) {
        int zhong = arr.length/2;
        int num = arr[zhong] - n;
        if (n >= arr[zhong]) {
            return 0;
        }
        if (arr.length % 2 == 0) {
            return num;
        }
        return num + 1;
    }
    public static void main59(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] arr = new int[N];
        int[] arr2 = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
            arr2[i] = arr[i];
        }
        Arrays.sort(arr2);
        for (int i = 0; i < N; i++) {
            System.out.print(count(arr2, arr[i]));
            if (i < N-1) {
                System.out.print(" ");
            }
        }
    }

    public static void main58(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        Map<Character,Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i),map.get(s.charAt(i))+1);
            } else {
                map.put(s.charAt(i),1);
            }
            if (count < map.get(s.charAt(i))) {
                count = map.get(s.charAt(i));
            }
        }
        for (char a = 'A'; a <= 'Z'; a++) {
            if (map.containsKey(a) && map.get(a) == count) {
                System.out.println(a);
            }
        }
    }


    public static void main57(String[] args) {
        int count = 0;
        for (int i = 20; i <= 20222; i++) {
            int j = i;
            boolean flag = true;
            int k = 0;
            int m = 0;
            String s = String.valueOf(i);
            while (j != 0) {

                k = j % 10;
                j /= 10;
                m = j % 10;
                if (k < m) {
                    flag = false;
                }
            }
            if (flag == true && s.charAt(0) < s.charAt(1)) {
                count++;
                System.out.println(i);
            }

        }
        System.out.println("count=: "+count);
    }


    public static void main56(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int count = 0;
        List<String> list = new ArrayList<>();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '"') {
                i++;
                while (str.charAt(i) != '"') {
                    s.append(str.charAt(i));
                    i++;
                }
                list.add(s.toString());
                s = new StringBuilder("");
            }
            if (str.charAt(i) != ' ' && str.charAt(i) != '"') {
                s.append(str.charAt(i));
            }
            if (str.charAt(i) == ' '&& !s.toString().equals("")) {
                list.add(s.toString());
                s = new StringBuilder("");
            }
        }
        if (!s.toString().equals("")) {
            list.add(s.toString());
            s = new StringBuilder("");
        }
        System.out.println(list.size());
        for (String s1:list) {
            System.out.println(s1);
        }
    }

    public static boolean isPrime(int m) {
        for (int i = 2; i <= Math.sqrt(m); i++) {
            if (m % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static void main55(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int half = n / 2;
        for (int i = half; i > 0; i--) {
            if (isPrime(i) && isPrime(n - i)) {
                System.out.println(i);
                System.out.println(n - i);
                return;
            }
        }
    }

    public static void main54(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        StringBuilder s = new StringBuilder();
        while (n != 0) {
            s.append(n % 2);
            n /= 2;
        }
        int count = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                count++;
            } else {
                if (count > max) {
                    max = count;
                    count = 0;
                } else {
                    count = 0;
                }
            }
        }
        if (count > max) {
            max = count;
            count = 0;
        }
        System.out.println(max);
    }

    public static boolean checkWon(int[][] board) {
        for (int i = 0;  i < board.length; i++) {
                if (board[i][0] == board[i][1] && board[i][1] == board[i][2]
                        && board[i][0] != 0 && board[i][0] != -1) {
                    return true;
                }
                if (board[0][i] == board[1][i] && board[1][i] == board[2][i]
                        && board[i][0] != 0 && board[i][0] != -1) {
                    return true;
                }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]
                && board[0][0] != 0 && board[0][0] != -1) {
            return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]
                && board[2][0] != 0 && board[2][0] != -1) {
            return true;
        }
        return false;
    }


    public static int med(int n, int m) {
        if ((m == 1 && n >= 1) || (n == 1 && m >= 1)) {
            return m + n;
        }
        return med(n - 1, m) + med(n, m - 1);
    }
    public static void main53(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            System.out.println(med(n, m));
        }
    }

    public static boolean SortDictionary(String[] s) {
        for (int i = 0; i < s.length - 1; i++) {
            if (s[i].compareTo(s[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }
    public static boolean SortLength(String[] s) {
        for (int i = 0; i < s.length - 1; i++) {
            if (s[i].length() > s[i + 1].length()) {
                return false;
            }
        }
        return true;
    }
    public static void main52(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = scanner.next();
        }
        if (SortDictionary(strings) && SortLength(strings)) {
            System.out.println("both");
        } else if (SortDictionary(strings)) {
            System.out.println("lexicographically");
        } else if (SortLength(strings)) {
            System.out.println("lengths");
        } else {
            System.out.println("none");
        }
    }

    public static void main51(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        for (int i = Math.max(a, b); i <= a * b; i++) {
            if (i % a == 0 && i % b == 0) {
                System.out.println(i);
                break;
            }
        }
    }

    public static boolean chkParenthesis(String A, int n) {
        if (n % 2 == 1 || A == null) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == '(') {
                stack.push(A.charAt(i));
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.peek() == '(' && A.charAt(i) == ')') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    public static void main50(String[] args) {
        String s = "()))))()(d";
        int n = 10;
        System.out.println(chkParenthesis(s, n));
    }

    public static void main49(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int a = 0;
        int b = 1;
        int c = a + b;
        int count = 0;
        while (true) {
            c = a + b;
            if (c == n) {
                System.out.println(count);
                return;
            }
            if (b < n && n < c) {
                count = Math.min((n - b),(c - n));
                break;
            }
            a = b;
            b = c;
        }
        System.out.println(count);
    }

    public static void main48(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String a = scanner.nextLine();
        String b = scanner.nextLine();
        int count = 0;
        for (int i = 0; i < a.length() + 1; i++) {
            StringBuilder s = new StringBuilder(a.substring(0,i));
            s.append(b);
            s.append(a.substring(i));
            String s1 = s.toString();
            String s2 = s.reverse().toString();
            if (s1.equals(s2)) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static void main47(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        int N = scanner.nextInt();
        StringBuilder s = new StringBuilder();
        if (M == 0) {
            s.append(0);
        }
        int tmp = M;
        if (M < 0) {
            M = -M;
        }
        Map<Integer,Character> map = new HashMap<>();
        map.put(10,'A');
        map.put(11,'B');
        map.put(12,'C');
        map.put(13,'D');
        map.put(14,'E');
        map.put(15,'F');
        while (M != 0) {
            if (M % N > 10) {
                s.append(map.get(M % N));
            } else {
                s.append(M % N);
            }
            M /= N;
        }
        if (tmp < 0) {
            s.append("-");
        }
        s.reverse();
        System.out.println(s);
    }

    public static void main46(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] str = scanner.nextLine().split("");
        String str2 = new String("1234567890");
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            if (str2.contains(str[i])) {
                s1.append(str[i]);
            } else {
                if (s1.length() > s2.length()) {
                    s2 = s1;
                    s1 = new StringBuilder("");
                } else {
                    s1 = new StringBuilder("");
                }
            }
        }
        if (s1.length() > s2.length()) {
            s2 = s1;
        }
        System.out.println(s2);
    }

    public int MoreThanHalfNum_Solution1(int [] array) {
        Arrays.sort(array);
        int count = 1;
        int num = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] == array[i - 1]) {
                count++;
            } else {
                count = 1;
            }
            if (count > array.length / 2) {
                num = array[i];
                break;
            }
        }
        return num;
    }

    public static void main45(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int count = 1;
        for (int i = 1; i < n - 1; i++) {
            if ((arr[i] > arr[i-1] && arr[i] > arr[i+1]) ||
                    (arr[i] < arr[i-1] && arr[i] < arr[i+1])) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static void main44(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] str = scanner.nextLine().split(" ");
        for (int i = str.length - 1; i >= 0; i--) {
            System.out.print(str[i] + " ");
        }
    }

    public static void main43(String[] args) {
        int one = 0;
        int i = 1;
        while (true) {
            int tmp = i;
            while(tmp != 0) {
                if(tmp % 10 == 1) {
                    one++;
                }
                tmp /= 10;
            }
            if (one > 2021) {
                break;
            }
            i++;
        }
        System.out.println(i-1);
    }

    public static void main42(String[]   args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        String str = String.valueOf(arr[0]);
        str += " ";
        for (int i = 1; i < arr.length; i++) {
            while (str.contains(String.valueOf(arr[i]))) {
                arr[i] += 1;
            }
            str += arr[i];
            str += " ";
        }
        String[] s = str.split("");
        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i]);
            if (i == s.length - 2){
                break;
            }
        }
    }

    public static void main41(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer,Integer> map = new HashMap<>();
        int num1 = 0;
        int num2 = 0;
        int sum = 0;
        int max = -1;
        for (int i = 0; i < 7; i++) {
            sum = 0;
            num1 = scanner.nextInt();
            num2 = scanner.nextInt();
            sum = num1 + num2;
            if (max < sum) {
                max = sum;
            }
            if (!map.containsKey(sum)) {
                map.put(sum,i+1);
            }
        }
        System.out.println(map.get(max));
    }

    public static void main40(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        if (num == 1) {
            System.out.println(1);
            return;
        }
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        ret.add(list1);
        int flag = 1;
        int i = 1;
        while (true) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            List<Integer> preRow = ret.get(i-1);
            for(int j = 1; j < i; j++) {
                int num1 = preRow.get(j) + preRow.get(j-1);
                list.add(num1);
                if (num1 == num){
                    ret.add(list);
                    flag = 0;
                    break;
                }
            }
            if (flag == 0) {
                break;
            }
            list.add(1);
            ret.add(list);
            i++;
        }
        int count = 1;
        for (i = 0; i <= ret.size(); i++) {
            List<Integer> k = ret.get(i);
            for (int j = 0; j <= i; j++) {
                int key = k.get(j);
                if (key == num){
                    System.out.println(count);
                    return;
                }
                count++;
            }
        }
    }

    public static void main39(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] road = new int[scanner.nextInt() + 1];
        int count = scanner.nextInt();
        for (int i = 0; i < road.length; i++) {
            road[i] = 1;
        }
        int num1 = 0;
        int num2 = 0;
        for (int i = 0; i < count; i++) {
            num1 = scanner.nextInt();
            num2 = scanner.nextInt();
            for (int j = num1; j <= num2; j++) {
                road[j] = 0;
            }
        }
        int tree = 0;
        for (int i = 0; i < road.length; i++) {
            if (road[i] == 1) {
                tree++;
            }
        }
        System.out.println(tree);
    }

    public static int count1 = 0;
    public static void frog (int num) {
        if (num < 0) {
            return;
        }
        if (num == 0) {
            count1++;
        } else {
            frog(num - 1);
            frog(num - 2);
            frog(num - 3);
        }
        return;
    }
    public static void main38(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        frog(num);
        System.out.println(count1);
    }

    public static void main37(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int nums = num;
        if (num == 0 || num == 1) {
            System.out.println(num);
            return;
        }
        List<Integer> list = new ArrayList<>();
        int i = 0;
        for (i = 2; i < nums; i++) {
            if (num % i == 0) {
                list.add(i);
                num /= i;
                i = 1;
            }
            if (num == 1) {
                break;
            }
        }
        if (i == nums) {
            list.add(i);
        }
        System.out.print(nums+"=");
        for (i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i != list.size() - 1) {
                System.out.print("*");
            }
        }
    }

    public static void main36(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int apple = scanner.nextInt();
        int height = scanner.nextInt();
        height += 30;
        int[] arr = new int[apple];
        for (int i = 0; i < apple; i++) {
            arr[i] = scanner.nextInt();
        }
        Arrays.sort(arr);
        int count = 0;
        for (int i = 0; i <= apple; i++) {
            if (arr[i] < height) {
                count++;
            } else {
                break;
            }
        }
        System.out.println(count);
    }

    public static void main35(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] str = scanner.nextLine().split(" ");
        int[] month = {31,28,31,30,31,30,31,31,30,31,30,31};
        if (Integer.parseInt(str[0]) % 400 == 0 ||
                (Integer.parseInt(str[0]) % 4 == 0 && Integer.parseInt(str[0]) % 100 != 0)) {
            month[1] = 29;
        }
        int sum = 0;
        for (int i = 0; i < Integer.parseInt(str[1]) - 1; i++) {
            sum += month[i];
        }
        sum += Integer.parseInt(str[2]);
        System.out.println(sum);
    }

    public static void main34(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int up = 0;
        int low = 0;
        int dig = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                dig++;
            }
            if (Character.isUpperCase(s.charAt(i))) {
                up++;
            }
            if (Character.isLowerCase(s.charAt(i))) {
                low++;
            }
        }
        System.out.println(up);
        System.out.println(low);
        System.out.println(dig);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] arr = Arrays.copyOf(nums1,nums1.length + nums2.length);
        int j = 0;
        for (int i = nums1.length; i < arr.length; i++) {
            arr[i] = nums2[j];
            j++;
        }
        Arrays.sort(arr);
        if (arr.length % 2 == 0) {
            int k = arr.length / 2;
            return (arr[k] + arr[k - 1])/2.0;
        }
        return arr[arr.length / 2];
    }

    public static int lengthOfLongestSubstring(String s) {
        char[] ch = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int max = 0;
        for (int end = 0; end < ch.length; end++) {
            if (map.containsKey(ch[end])) {
                start = Math.max(map.get(ch[end]),start);
            }
            max = Math.max(end - start + 1,max);
            map.put(ch[end], end + 1);
        }
        return max;
    }

    public static void main33(String[] args) {
        String s = "abba";
        lengthOfLongestSubstring(s);
    }

    public double average(int[] salary) {
        Arrays.sort(salary);
        int sum = 0;
        for (int i = 1; i < salary.length - 1; i++) {
            sum += salary[i];
        }
        return 1.0 * sum / (salary.length - 2);
    }

    public boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        StringBuilder s = new StringBuilder(str);
        s.reverse();
        return str.equals(s.toString());
    }

    public static String largestOddNumber(String num) {
        int n = num.length();
        for(int i = n-1; i >= 0; i--) {
            if (Integer.parseInt(String.valueOf(num.charAt(i))) % 2 != 0) {
                return num;
            }
            num = num.substring(0,i);
        }
        return num;
    }

    public static void main32(String[] args) {
        String str = "4206";
        largestOddNumber(str);
    }

    public static void main31(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<String> set = new HashSet<>();
        while (scanner.hasNext()) {
            set.add(scanner.next());
        }
        System.out.println(set.size());
    }

    public static void main30(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int k = 0;
        int m = 1;
        int sum = 0;
        while (sum < N) {
            sum = k + m;
            k = m;
            m = sum;
        }
        System.out.println(Math.min((N - k),(sum - N)));
    }

    public static void main28(String[] args) {
        int a = 10;
        int b = 20;
        System.out.println(a > b);
        System.out.println(a < b);
        System.out.println(a == b);
        System.out.println();
        char c1 = 'A';
        char c2 = 'B';
        System.out.println(c1 > c2);
        System.out.println(c1 < c2);
        System.out.println(c1 == c2);
        System.out.println();
        boolean b1 = true;
        boolean b2 = false;
        System.out.println(b1 == b2);
        System.out.println(b1 != b2);
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && stack.peek() < nums2[i]) {
                map.put(stack.pop(),nums2[i]);
            }
            stack.add(nums2[i]);
        }
        int[] arr = new int[nums1.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = map.getOrDefault(nums1[i],-1);
        }
        return arr;
    }

    public static List<String> buildArray(int[] target, int n) {
        List<String> list = new LinkedList<>();
        int j = 0;
        for (int i = 1; i <= n; i++) {
            if (target[target.length - 1] < i) {
                break;
            }
            if (target[j] == i) {
                list.add("Push");
                j++;
            } else {
                list.add("Push");
                list.add("Pop");
            }

        }
        return list;
    }

    public static void main27(String[] args) {
        int[] arr = {1,2};
        int n = 4;
        buildArray(arr, n);
    }

    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = new ListNode(-1);
        ListNode newHead = cur;
        Stack<ListNode> stack = new Stack<>();
        while (head != null){
            stack.add(head);
            head = head.next;
        }
        while (!stack.isEmpty()) {
            cur.next = stack.pop();
            cur = cur.next;
        }
        cur.next = null;
        return newHead.next;
    }
    public static void main26(String[] args) {
        ListNode cur = new ListNode(1);
        ListNode head = cur;
        cur.next = new ListNode(2);
        cur = cur.next;
        cur.next = new ListNode(3);
        cur = cur.next;
        cur.next = new ListNode(4);
        cur = cur.next;
        cur.next = new ListNode(5);
        cur = cur.next;
        reverseList(head);
    }

    public static ListNode deleteDuplication(ListNode pHead) {
        if(pHead == null){
            return  null;
        }
        // 先找出相同结点，存入 set
        HashSet<Integer> set = new HashSet<>();
        ListNode pre = pHead;
        ListNode cur = pHead.next;
        while(cur != null){
            if(cur.val == pre.val){
                set.add(cur.val);
            }
            pre = cur;
            cur = cur.next;
        }
        // 再根据相同节点删除
        // 先删头部
        while(pHead != null && set.contains(pHead.val)){
            pHead = pHead.next;
        }
        if(pHead == null){
            return null;
        }
        // 再删中间结点
        pre = pHead;
        cur = pHead.next;
        while(cur != null){
            if(set.contains(cur.val)){
                pre.next = cur.next;
                cur = cur.next;
            }else{
                pre = cur;
                cur = cur.next;
            }
        }
        return pHead;
    }
    public static void main25(String[] args) {
        ListNode cur = new ListNode(1);
        ListNode head = cur;
        cur.next = new ListNode(2);
        cur = cur.next;
        cur.next = new ListNode(3);
        cur = cur.next;
        cur.next = new ListNode(3);
        cur = cur.next;
        cur.next = new ListNode(4);
        cur = cur.next;
        cur.next = new ListNode(4);
        cur = cur.next;
        cur.next = new ListNode(5);
        cur = cur.next;
        deleteDuplication(head);
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode cur = new ListNode(-1);
        ListNode newHead = cur;
        while (list1 != null && list2!= null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                cur = cur.next;
                list1 = list1.next;
            } else {
                cur.next = list2;
                cur = cur.next;
                list2 = list2.next;
            }
        }
        if (list1 == null) {
            cur.next = list2;
        }
        if (list2 == null) {
            cur.next = list1;
        }
        return newHead.next;
    }

    public static ListNode oddEvenList(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode oddNumber = head;//单数
        ListNode numbered = head.next;//偶数
        ListNode cur = new ListNode(-1);
        ListNode cur2 = new ListNode(-1);
        ListNode beredHead = numbered;
        while (numbered != null && numbered.next != null) {
            oddNumber.next = numbered.next;
            oddNumber = oddNumber.next;
            numbered.next = oddNumber.next;
            numbered = numbered.next;
        }
        oddNumber.next = beredHead;
        return head;
    }

    public static void main24(String[] args) {
        ListNode cur = new ListNode(1);
        ListNode head = cur;
        cur.next = new ListNode(2);
        cur = cur.next;
        cur.next = new ListNode(3);
        cur = cur.next;
        cur.next = new ListNode(4);
        cur = cur.next;
        cur.next = new ListNode(5);
        cur = cur.next;
        oddEvenList(head);
    }

    public ListNode sortList(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        Collections.sort(list);
        ListNode cur = new ListNode(-1);
        ListNode newHead = cur;
        for (int i = 0; i < list.size(); i++) {
            newHead.next = new ListNode(list.get(i));
            newHead = newHead.next;
        }
        return cur.next;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode cur = new ListNode(0);
        ListNode head = cur;
        if (l1.val == 0 && l2.val == 0 && l1.next == null && l2.next == null) {
            return cur;
        }
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        while (!stack1.isEmpty()) {
            s1.append(stack1.pop());
        }
        while (!stack2.isEmpty()) {
            s2.append(stack2.pop());
        }
        long sum = Long.parseLong(s1.toString()) + Long.parseLong(s2.toString());
        while (sum != 0) {
            cur.next = new ListNode((int) (sum % 10));
            cur = cur.next;
            sum /= 10;
        }
        return head.next;
    }
    public static void main23(String[] args) {
        ListNode cur1 = new ListNode(2);
        ListNode head1 = cur1;
        cur1.next = new ListNode(4);
        cur1 = cur1.next;
        cur1.next = new ListNode(9);
        ListNode cur2 = new ListNode(5);
        ListNode head2 = cur2;
        cur2.next = new ListNode(6);
        cur2 = cur2.next;
        cur2.next = new ListNode(4);
        cur2 = cur2.next;
        cur2.next = new ListNode(9);
        addTwoNumbers(head1,head2);
    }

    public static ListNode removeZeroSumSublists(ListNode head) {
        ListNode cur = new ListNode(0);
        cur.next = head;
        Map<Integer, ListNode> map = new HashMap<>();
        int sum = 0;
        for (ListNode d = cur; d != null; d = d.next) {
            sum += d.val;
            map.put(sum, d);
        }
        sum = 0;
        for (ListNode d = cur; d != null; d = d.next) {
            sum += d.val;
            d.next = map.get(sum).next;
        }
        return cur.next;
    }
    public static void main22(String[] args) {
        ListNode cur = new ListNode(1);
        ListNode head = cur;
        cur.next = new ListNode(2);
        cur = cur.next;
        cur.next = new ListNode(-3);
        cur = cur.next;
        cur.next = new ListNode(3);
        cur = cur.next;
        cur.next = new ListNode(1);
        cur = cur.next;
        removeZeroSumSublists(head);
    }

    public static ListNode removeDuplicateNodes(ListNode head) {
        Set<Integer> set = new HashSet<>();
        ListNode cur = new ListNode(-1);
        ListNode newHead = cur;
        while (head != null) {
            if (!set.contains(head.val)) {
                cur.next = new ListNode(head.val);
                cur = cur.next;
                set.add(head.val);
            }
            head = head.next;
        }
        cur.next = null;
        return newHead.next;
    }
    public static void main21(String[] args) {
        ListNode cur = new ListNode(1);
        ListNode head = cur;
        cur.next = new ListNode(2);
        cur = cur.next;
        cur.next = new ListNode(3);
        cur = cur.next;
        cur.next = new ListNode(3);
        cur = cur.next;
        cur.next = new ListNode(2);
        cur = cur.next;
        cur.next = new ListNode(1);
        cur = cur.next;
        cur.next = null;
        removeDuplicateNodes(head);
    }


    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int[] arr = new int[stack.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = stack.pop();
        }
        return arr;
    }

    //一年中的第几天
    public static int dayOfYear(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));
        int[] num = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            num[1] = 29;
        }
        int sumDay = 0;
        for (int i = 0; i < month - 1; i++) {
            sumDay += num[i];
        }
        sumDay += day;
        return sumDay;

        //也可以用 API 来做
        //return LocalDate.parse(date).getDayOfYear();
    }

    public static void main20(String[] args) {
        String s = "2019-01-09";
        int num = dayOfYear(s);
        System.out.println(num);
    }

    public static int countCharacters(String[] words, String chars) {
        //把单母的词个数放进去
        Map<Character,Integer> map = new HashMap<>();
        for (char ch:chars.toCharArray()) {
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        int count = 0;
        for (String str:words) {
            Map<Character,Integer> mapWords = new HashMap<>();
            for (char word:str.toCharArray()) {
                mapWords.put(word,mapWords.getOrDefault(word,0)+1);
            }
            boolean flg = true;
            for (char wordCount:str.toCharArray()) {
                if (map.getOrDefault(wordCount,0) < mapWords.getOrDefault(wordCount,0)) {
                    flg = false;
                    break;
                }
            }
            if (flg == true) {
                count += str.length();
            }
        }
        return count;
    }

    public static void main19(String[] args) {
        String[] str = {"cat","bt","hat","tree"};
        String str1 = "atach";
        int tmp = countCharacters(str, str1);
        System.out.println(tmp);
    }

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int x:stones) {
            maxHeap.offer(x);
        }
        while (maxHeap.size() > 1) {
            int a = maxHeap.poll();
            int b = maxHeap.poll();
            if (a > b) {
                maxHeap.offer(a-b);
            }
        }
        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }

    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < s1.length(); i++){
            map1.put(s1.charAt(i), map1.getOrDefault(s1.charAt(i), 0) + 1);
        }
        for (int i = 0; i < s2.length(); i++){
            map2.put(s2.charAt(i), map2.getOrDefault(s2.charAt(i), 0) + 1);
        }
        for (char key : map1.keySet()){
            if (map1.get(key) != map2.get(key))
                return false;
        }
        return true;
    }

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int index = nums.length;
        while (left <= right) {
            int mid = left + (right - left)/2;
            if (target <= nums[mid]) {
                index = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return index;
    }

    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }

    public boolean isUgly(int n) {
        if(n < 1) {
            return false;
        }
        while (n % 2 == 0 || n % 3 == 0 || n % 5 == 0) {
            if (n % 2 == 0) {
                n /= 2;
            } else if (n % 3 == 0) {
                n /= 3;
            } else if (n % 5 == 0) {
                n /= 5;
            }
        }
        return n == 1;
    }

    static boolean out(char c){
        System.out.print(c);
        return true;
    }
    public static void main18(String[] argv){
        int i = 0;
        for(out('A');out('B') && (i<2);out('C')){
            i++;
            out('D');
        }
    }

    private int nextInt(int n) {
        int sum = 0;
        while (n > 0) {
            sum += (n%10)*(n%10);
            n /= 10;
        }
        return sum;
    }
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = nextInt(n);
        }
        return n == 1;
    }

    public static int romanToInt(String s) {
        Map<Character,Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        char[] ch = s.toCharArray();
        int sum = 0;
        for (int i = 0; i< ch.length; i++) {
            if (i < ch.length - 1 && map.get(ch[i]) >= map.get(ch[i+1])) {
                sum += map.get(ch[i]);
            } else if (i < ch.length - 1 && map.get(ch[i]) < map.get(ch[i+1])){
                sum -= map.get(ch[i]);
            } else {
                sum += map.get(ch[i]);
            }
        }
        return sum;
    }
    public static void main16(String[] args) {
        String s = "III";
        int tmp = romanToInt(s);
        System.out.println(tmp);
    }

    public int removeElement(int[] nums, int val) {
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[left] = nums[i];
                left++;
            }
        }
        return left;
    }

    public static int removeDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        Object[] arr = set.toArray();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            nums[i] = (int)arr[i];
        }

        return set.size();
    }
    public static void main15(String[] args) {
        int[] arr = {1,1,2};
        int tmp = removeDuplicates(arr);
        System.out.println(tmp);
    }

    public String truncateSentence(String s, int k) {
        StringBuilder str = new StringBuilder();
        String[] strings = s.split(" ");
        for (int i = 0; i < k; i++) {
            str.append(strings[i]);
            if (i != k - 1){
                str.append(" ");
            }
        }
        return str.toString();
    }

    public boolean isCompleteTree(TreeNode root) {
        if(root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur != null) {
                queue.offer(cur.left);
                queue.offer(cur.right);
            } else {
                break;
            }
        }
        while (!queue.isEmpty()) {
            TreeNode cur = queue.peek();
            if (cur == null) {
                return false;
            }
            queue.poll();
        }
        return true;
    }

    public static void main14(String[] args) {
        //用哈希表做
        Scanner scanner = new Scanner(System.in);
        int goods = scanner.nextInt();
        int weight = scanner.nextInt();
        int[][] goodsInfo = new int[goods][2];
        double[][] valueRatio = new double[goods][2];
        for (int i = 0; i < goods; i++) {
            //放重量
            goodsInfo[i][0] = scanner.nextInt();
            //放价值
            goodsInfo[i][1] = scanner.nextInt();
            //放比值对应的位置
            valueRatio[i][0] = i;
            //放比值
            valueRatio[i][1] = (double) goodsInfo[i][1] / goodsInfo[i][0];
        }
        //得到最大回报率
        double sum = 0;
        Arrays.sort(valueRatio, (o1, o2) -> {
            if(o1[1] - o2[1] >= 0){
                return -1;
            }
            return 1;
        });
        for (int i = 0; i < goods; i++) {
            int k = (int) valueRatio[i][0];
            if (goodsInfo[k][0] > weight) {
                sum += valueRatio[i][1] * weight;
                break;
            }
            sum += goodsInfo[k][1];
            weight -= goodsInfo[k][0];
        }
        System.out.println(String.format("%.1f" , sum));
    }

    public static void main13(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i < n; i++) {
            Arrays.sort(arr);
            arr[i] += arr[i - 1];
            stack.push(arr[i]);
        }
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        System.out.println(sum);
    }

    public static void main12(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                for (int m = i; m <= n; m++) {
                    if (i + j + m == n) {
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }

    public static void main11(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dan = 0;
        int wei = 0;
        while (scanner.hasNext()) {
            int k = scanner.nextInt();
            if (k == 0) {
                dan++;
            } else {
                wei++;
            }
            if (wei >= 21 || dan >= 21) {
                if (wei - dan > 1) {
                    System.out.println(1);
                    break;
                } else if (dan - wei > 1){
                    System.out.println(0);
                    break;
                }
            }
        }
    }

    public static void main10(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.next();
        String str2 = scanner.next();
        StringBuilder s1 = new StringBuilder(str1);
        StringBuilder s2 = new StringBuilder(str2);
        s1.reverse();
        s2.reverse();
        int sum = Integer.parseInt(String.valueOf(s1)) + Integer.parseInt(String.valueOf(s2));
        StringBuilder s3 = new StringBuilder(String.valueOf(sum));
        s3.reverse();
        int num = Integer.parseInt(s3.toString());
        System.out.println(num);
    }

    public static void main9(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        TreeNode root = binaryTree.creatTree();
        binaryTree.preOrder(root);
        System.out.println();
        binaryTree.inOrder(root);
        System.out.println();
        binaryTree.postOrder(root);
        System.out.println();
        System.out.println(binaryTree.size(root));
        System.out.println(binaryTree.levelSize(root));
        System.out.println(binaryTree.getKLevelNodeCount(root,3));
        System.out.println(binaryTree.find(root, 'E').val);
    }



    public static void main8(String[] args) {
        RecentCounter recentCounter = new RecentCounter();
        System.out.println(recentCounter.ping(1));
        System.out.println(recentCounter.ping(100));
        System.out.println(recentCounter.ping(3001));
        System.out.println(recentCounter.ping(3002));
    }

    public static void main7(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int num = 2;
        StringBuilder stringBuilder = new StringBuilder();
        while (num <= n) {
            if (n % num == 0) {
                stringBuilder.append(num+" ");
                n /= num;
            } else {
                num++;
            }
        }
        System.out.println(stringBuilder);
    }

    public static void main6(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n <= 0) {
            return;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 0; i < n; i++) {
            stack.push(scanner.nextInt());
        }
        int count = 1;
        int num = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int val = stack.pop();
            if (val == stack.peek()) {
                count++;
            } else {
                if (num <= count) {
                    num = count;
                    max = val;
                    count = 1;
                }
            }
        }
        System.out.println(max);
    }

    public static void main5(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<List<Integer>> list = new ArrayList<>();
        //第一行：
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list.add(list1);
        for (int i = 1; i < n; i++) {
            List<Integer> list2 = new ArrayList<>();

            list2.add(1);//每一行的开始都是 1

            List<Integer> preRow = list.get(i - 1);//拿到上一行
            for (int j = 1; j < i; j++) {
                int num1 = preRow.get(j) + preRow.get(j - 1);
                list2.add(num1);
            }

            list2.add(1);//每一行的结尾都是 1
            list.add(list2);
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                System.out.print(list.get(i).get(j)+" ");
            }
            System.out.println();
        }
    }

    public static void main4(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }
        Arrays.sort(arr);
        for (int a:arr) {
            System.out.print(a);
        }
    }

    public static boolean isValid(String s) {
        Stack<String> stack = new Stack<>();
        Stack<String> stack1 = new Stack<>();
        String[] strings = s.split("");
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals("(") || strings[i].equals("[") || strings[i].equals("{")) {
                stack.push(strings[i]);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.peek().equals("(") && strings[i].equals(")") ||
                        stack.peek().equals("[") && strings[i].equals("]") ||
                        stack.peek().equals("{") && strings[i].equals("}")) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }

    public static void main3(String[] args) {
        String s = "]";
        System.out.println(isValid(s));
    }


    public static int calPoints(String[] ops) {
        if (ops == null) {
            return 0;
        }
        Stack<String> stack = new Stack<>();
        int num2 = 0;
        int num1 = 0;
        for (int i = 0; i < ops.length; i++) {
            if (ops[i].equals("+")) {
                num2 = Integer.parseInt(stack.pop());
                num1 = Integer.parseInt(stack.peek());
                stack.push(String.valueOf(num2));
                stack.push(String.valueOf(num2 + num1));
            } else if (ops[i].equals("D")) {
                num2 = Integer.parseInt(stack.peek());
                stack.push(String.valueOf(num2 * 2));
            } else if (ops[i].equals("C")) {
                stack.pop();
            } else {
                stack.push(ops[i]);
            }
        }
        int sum = 0;
        int k = stack.size();
        for (int i = 0; i < k; i++) {
            sum += Integer.parseInt(stack.pop());
        }
        return sum;
    }

    public static void main2(String[] args) {
        String[] strings = "52CD+".split("");
        int sum = calPoints(strings);
        System.out.println(sum);
    }

    class Solution3 {
        public int calPoints(String[] ops) {
            if (ops == null) {
                return 0;
            }
            Stack<String> stack = new Stack<>();
            int num2 = 0;
            int num1 = 0;
            for (int i = 0; i < ops.length; i++) {
                if (ops[i].equals("+")) {
                    num2 = Integer.parseInt(stack.pop());
                    num1 = Integer.parseInt(stack.pop());
                    stack.push(String.valueOf(num2 + num1));
                } else if (ops[i].equals("D")) {
                    num2 = Integer.parseInt(stack.pop());
                    num1 = Integer.parseInt(stack.pop());
                    stack.push(String.valueOf(num2 * num1));
                } else if (ops[i].equals("C")) {
                    stack.pop();
                } else {
                    stack.push(ops[i]);
                }
            }
            int sum = 0;
            for (int i = 0; i < stack.size(); i++) {
                sum += Integer.parseInt(stack.pop());
            }
            return sum;
        }
    }

    public class Solution4 {
        public boolean IsPopOrder(int [] pushA,int [] popA) {
            Stack<Integer> stack = new Stack<>();
            int i = 0;
            int j = 0;
            while (i < pushA.length && j < popA.length) {
                stack.push(pushA[i]);
                i++;
                if (stack.peek() == popA[j]) {
                    stack.pop();
                    j++;
                }
            }
            if (stack != null) {
                return false;
            }
            return true;
        }
    }

    class Solution5 {
        public boolean backspaceCompare(String s, String t) {
            if (s == null || t == null) {
                return false;
            }
            Stack<String> stack = new Stack<>();
            String[] strings = s.split("");
            Stack<String> stack1 = new Stack<>();
            String[] strings1 = t.split("");
            for (int i = 0; i < s.length(); i++) {
                if (strings[i].equals("#")) {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                } else {
                    stack.push(strings[i]);
                }
            }
            for (int i = 0; i < t.length(); i++) {
                if (strings1[i].equals("#")) {
                    if (!stack1.isEmpty()) {
                        stack1.pop();
                    }
                } else {
                    stack1.push(strings1[i]);
                }
            }
            return stack.equals(stack1);

//            int flag = 0;
//            while (stack.isEmpty() && stack1.isEmpty()) {
//                String s1 = stack.peek();
//                String s2 = stack1.peek();
//                if (!s1.equals(s2)) {
//                    flag = 1;//说明为假
//                }
//                stack.pop();
//                stack1.pop();
//            }
//            if (flag == 1){
//                return false;
//            }
//            return true;
        }
    }

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<List<Integer>> list1 = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        k %= grid.length*grid[0].length;
        int[] arr = new int[grid.length*grid[0].length];
        int[] arr1 = new int[grid.length*grid[0].length];
        int len = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                arr[len] = grid[i][j];
                len++;
            }
        }
        int x = 0;
        for (int i = arr.length - k; i < arr.length; i++) {
            arr1[x] = arr[i];
            x++;
        }
        for (int i = 0; i < arr.length - k; i++) {
            arr1[x] = arr[i];
            x++;
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            List<Integer> list2 = new ArrayList<>();
            for (int j = 0; j < grid[0].length; j++) {
                list2.add(arr1[count]);
                count++;
            }
            list1.add(list2);
        }
        return list1;
    }
    
    //十六进制转换为十进制
    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        long a = Long.parseLong(str,16);
        System.out.println(a);
    }

    //找出数组中重复的数字。

    /*在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
    数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
    请找出数组中任意一个重复的数字。*/

    class Solution6 {
        public int findRepeatNumber(int[] nums) {
            Arrays.sort(nums);
            for(int i = 1; i < nums.length; i++){
                if(nums[i] == nums[i - 1]){
                    return nums[i];
                }
            }
            return -1;
        }
    }

    //两数之和

    /*给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出
    和为目标值 target  的那 两个 整数，并返回它们的数组下标。
    你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
    你可以按任意顺序返回答案。*/

    //这里通过暴力遍历的方法去求解
    class Solution9 {
        public int[] twoSum(int[] nums, int target) {
            for(int i = 0;i< nums.length; i++){
                for(int j = 0;j<nums.length; j++){
                    if(nums[i] + nums[j] == target && i != j){
                        return new int[]{i,j};
                    }
                }
            }
            return new int[0];
        }
    }



    //找到小镇的法官

    /*在一个小镇里，按从 1 到 n 为 n 个人进行编号。传言称，这些人中有一个是小镇上的秘密法官。
    如果小镇的法官真的存在，那么：
    小镇的法官不相信任何人。
    每个人（除了小镇法官外）都信任小镇的法官。
    只有一个人同时满足条件 1 和条件 2 。
    给定数组 trust，该数组由信任对 trust[i] = [a, b] 组成，表示编号为 a 的人信任编号为 b 的人。
    如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的编号。否则，返回 -1。*/

    class Solution8 {
        public int findJudge(int n, int[][] trust) {
            if (n == 1) return 1;
            int[] arr = new int[n + 1];
            for (int i = 0; i < trust.length; i++) {
                arr[trust[i][1]]++;
                arr[trust[i][0]]--;
            }
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == n - 1) {
                    return i;
                }
            }
            return -1;
        }
    }
}
