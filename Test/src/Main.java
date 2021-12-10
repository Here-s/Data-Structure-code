import java.util.Scanner;
import java.util.*;

public class Main {

    //找出数组中重复的数字。

    /*在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
    数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
    请找出数组中任意一个重复的数字。*/

    class Solution {
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
    class Solution2 {
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

    class Solution1 {
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
