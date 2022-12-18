import java.util.Arrays;
import java.util.Comparator;

public class Cupidity {

    //分割平衡字符串
    //平衡字符串中，'L' 和 'R' 字符的数量是相同的。
    //给你一个平衡字符串 s，请你将它分割成尽可能多的子字符串，并满足：
    //每个子字符串都是平衡字符串。
    //返回可以通过分割得到的平衡字符串的 最大数量 。
    class Solution {
        public int balancedStringSplit(String s) {
            int count = 0;
            int balance = 0;
            for(int i = 0; i < s.length(); i++) {
                char a = s.charAt(i);
                if (a == 'L') {
                    balance++;
                } else {
                    balance--;
                }
                if (balance == 0) {
                    count++;
                }
            }
            return count;
        }
    }

    //买卖股票的最佳时机2
    //给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
    //在每一天，你可以决定是否购买和/或出售股票。你在任何时候
    //最多只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
    //返回 你能获得的 最大利润
    class Solution {
        public int maxProfit(int[] prices) {
            int count = 0;
            for(int i = 1; i < prices.length; i++) {
                if(prices[i] > prices[i-1]) {
                    count += prices[i] - prices[i-1];
                }
            }
            return count;
        }
    }

    //跳跃游戏
    //给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
    //数组中的每个元素代表你在该位置可以跳跃的最大长度。
    //判断你是否能够到达最后一个下标。
    class Solution {
        public boolean canJump(int[] nums) {
            int max = 0;
            for(int i = 0; i < nums.length; i++) {
                //看看是否能到达当前位置
                if(max >= i) {
                    max = Math.max(max,i + nums[i]);
                    if(max >= nums.length-1) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    //无重叠区间
    //给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi]
    // 返回 需要移除区间的最小数量，使剩余区间互不重叠 。
    class Solution {

        public int eraseOverlapIntervals(int[][] intervals) {
            Arrays.sort(intervals, new Comparator<int[]>() {
                public int compare(int[] a1, int[] a2) {
                    return a1[1] - a2[1];
                }
            });
            int right = intervals[0][1];
            int count = 0;
            for(int i = 1; i < intervals.length; i++) {
                if(intervals[i][0] < right) {
                    count++;
                } else {
                    right = intervals[i][1];
                }
            }
            return count;
        }
    }
}
