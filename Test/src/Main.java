import java.time.LocalDate;
import java.util.*;
class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
}

public class Main {

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
    public static void main(String[] args) {
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

    class Solution {
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

    public class Solution1 {
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

    class Solution2 {
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

    class Solution3 {
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
    class Solution4 {
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

    class Solution5 {
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
