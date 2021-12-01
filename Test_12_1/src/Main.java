import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    //给一个整数 输出其构成的杨辉三角
    //要求：用 List 返回值
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        ret.add(list1);

        for(int i = 1; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            List<Integer> preRow = ret.get(i-1);
            for(int j = 1; j < i; j++) {
                int num1 = preRow.get(j) + preRow.get(j-1);
                list.add(num1);
            }
            list.add(1);
            ret.add(list);
        }
        return ret;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<List<Integer>> list = generate(n);
        System.out.println(list);
    }
}
