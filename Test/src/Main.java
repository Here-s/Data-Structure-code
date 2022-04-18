import java.util.*;

public class Main {

    public static void main(String[] args) {
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

    public int MoreThanHalfNum_Solution(int [] array) {
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

    /*public static void main29(String[] args) {
        //默认是一个小根堆
        //而且放的元素之间是必须能比较的  不能是 null
        //关于对象的比较：
        // 1、equals()：比较两个对象相不相同
        // 2、比较大小：Comparable compareTo
        PriorityQueue<Card> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Card(2, "♥"));//
        priorityQueue.offer(new Card(1, "♥"));
        System.out.println(priorityQueue);
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

    /*public ListNode detectCycle(ListNode head) {
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
    }*/

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
