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



}
