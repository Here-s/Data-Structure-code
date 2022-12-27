import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Backtracking {

    //员工的重要性
    //给定一个保存员工信息的数据结构，它包含了员工 唯一的 id ，重要度 和 直系下属的 id 。
    //比如，员工 1 是员工 2 的领导，员工 2 是员工 3 的领导。他们相应的重要度
    // 为 15 , 10 , 5 。那么员工 1 的数据结构是 [1, 15, [2]] ，员工 2的 数据结构是 [2, 10, [3]]
    // 员工 3 的数据结构是 [3, 5, []] 。注意虽然员工 3 也是员工 1 的一个下属，
    // 但是由于 并不是直系 下属，因此没有体现在员工 1 的数据结构中。
    //现在输入一个公司的所有员工信息，以及单个员工 id ，返回这个员工和他所有下属的重要度之和。
    class Solution {
        public int dfs(Map<Integer, Employee> info, int id) {
            Employee e = info.get(id);
            int sum = e.importance;
            List<Integer> list = e.subordinates;
            for(int subId:list) {
                sum += dfs(info, subId);
            }
            return sum;
        }
        public int getImportance(List<Employee> employees, int id) {
            if(employees.isEmpty()) {
                return 0;
            }
            Map<Integer, Employee> map = new HashMap<>();
            for(Employee e : employees) {
                map.put(e.id, e);
            }
            return dfs(map, id);
        }
    }

    //图像渲染
    //有一幅以 m x n 的二维整数数组表示的图画 image ，其中 image[i][j] 表示该图画的像素值大小。
    //你也被给予三个整数 sr ,  sc 和 newColor 。你应该从像素 image[sr][sc] 开始对图像进行 上色填充 。
    //为了完成 上色工作 ，从初始像素开始，记录初始坐标的 上下左右四个方向上 像素值与初始坐标相同的相连像素点，
    // 接着再记录这四个方向上符合条件的像素点与他们对应 四个方向上 像素值与初始坐标相同的相连像素点，……，
    // 重复该过程。将所有有记录的像素点的颜色值改为 newColor 。最后返回 经过上色渲染后的图像 。
    class Solution {
        int[] kx = {-1, 1, 0, 0};
        int[] ky = {0, 0, -1, 1};
        public void dfs(int[][] image, int x, int y, int oldColor, int color) {
            if(image[x][y] == oldColor) {
                image[x][y] = color;
                for(int i = 0; i < 4; i++) {
                    int newx = x + kx[i];
                    int newy = y + ky[i];
                    if(newx >= 0 && newx < image.length && newy >= 0 && newy < image[0].length) {
                        dfs(image, newx, newy, oldColor, color);
                    }
                }
            }
        }

        public int[][] floodFill(int[][] image, int sr, int sc, int color) {
            int oldcolor = image[sr][sc];
            if(oldcolor != color) {
                dfs(image, sr, sc, oldcolor, color);
            }
            return image;
        }
    }

    //被围绕的区域
    //给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，
    // 并将这些区域里所有的 'O' 用 'X' 填充。
    class Solution {

        public void dfs(char[][] board, int x, int y) {
            if(x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'O') {
                return;
            }
            board[x][y] = 'k';
            dfs(board, x+1, y);
            dfs(board, x-1, y);
            dfs(board, x, y+1);
            dfs(board, x, y-1);
        }
        public void solve(char[][] board) {
            for(int i = 0; i < board.length; i++) {
                //左右两侧
                dfs(board, i, 0);
                dfs(board, i, board[0].length - 1);
            }
            for(int i = 0; i < board[0].length; i++) {
                //上下
                dfs(board, 0, i);
                dfs(board, board.length - 1, i);
            }
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[0].length; j++) {
                    if(board[i][j] == 'k') {
                        board[i][j] = 'O';
                    } else if(board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }

    //岛屿数量
    //给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
    //岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
    //此外，你可以假设该网格的四条边均被水包围。
    class Solution {
        public void dfs(char[][]grid, int i, int j) {
            if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
                return;
            }
            grid[i][j] = '0';
            dfs(grid, i + 1, j);
            dfs(grid, i - 1, j);
            dfs(grid, i, j + 1);
            dfs(grid, i, j - 1);
        }

        public int numIslands(char[][] grid) {
            int num = 0;
            for(int i = 0; i < grid.length; i++) {
                for(int j = 0; j < grid[0].length; j++) {
                    if(grid[i][j] == '1') {
                        num++;
                        dfs(grid, i, j);
                    }
                }
            }
            return num;
        }
    }

    //电话号码的组合
    //给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
    //答案可以按 任意顺序 返回。给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
    class Solution {
        public void dfs(List<String> list, Map<String, String> map, String[] strings, int len, int index, StringBuffer buffer) {
            if(index == len) {
                list.add(buffer.toString());
            } else {
                //得到字符串
                String s = map.get(strings[index]);
                for(int i = 0; i < s.length(); i++) {
                    buffer.append(s.charAt(i));
                    dfs(list, map, strings, len, index+1, buffer);
                    buffer.deleteCharAt(index);
                }
            }
        }
        public List<String> letterCombinations(String digits) {
            List<String> list = new ArrayList<>();
            if (digits.length() == 0) {
                return list;
            }
            Map<String, String> map = new HashMap<>();
            map.put("2", "abc");
            map.put("3", "def");
            map.put("4", "ghi");
            map.put("5", "jkl");
            map.put("6", "mno");
            map.put("7", "pqrs");
            map.put("8", "tuv");
            map.put("9", "wxyz");
            String[] strings = digits.split("");
            int len = digits.length();
            int index = 0;
            StringBuffer buffer = new StringBuffer();
            dfs(list, map, strings, len, index, buffer);
            return list;
        }
    }

    //N 叉树的层序遍历
    //给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
    //树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
    class Solution {
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> list = new ArrayList<List<Integer>>();
            if(root == null) {
                return list;
            }
            Queue<Node> queue = new ArrayDeque<>();
            queue.offer(root);
            while(!queue.isEmpty()) {
                int count = queue.size();
                List<Integer> level = new ArrayList<>();
                for(int i = 0; i < count; i++) {
                    Node cur = queue.poll();
                    level.add(cur.val);
                    for(Node k : cur.children) {
                        queue.offer(k);
                    }
                }
                list.add(level);
            }
            return list;
        }
    }

}
