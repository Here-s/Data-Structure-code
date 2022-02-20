import java.util.*;
public class MapAndSet {

    //二叉搜索树：根节点左边小于根节点，根节点右边大于根节点

    //前 k 个高频单词
    public static List<String> topKFrequent(String[] words, int k) {
        HashMap<String,Integer> map = new HashMap<>();
        //1、统计每个单词出现的次数 map
        for (String s:words) {
            if (map.get(s) == null) {
                map.put(s,1);
            } else {
                int val = map.get(s);
                map.put(s,val+1);
            }
        }
        //2、建立一个大小为 k 的小根堆
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue().compareTo(o2.getValue()) == 0) {
                    return o2.getKey().compareTo(o1.getKey());
                }
                return o1.getValue() - o2.getValue();
            }
        });
        //3、遍历 map
        for (Map.Entry<String,Integer> entry:map.entrySet()) {
            if (minHeap.size()<k) {
                minHeap.offer(entry);
            } else {
                Map.Entry<String,Integer> top = minHeap.peek();
                //判断频率是否相同
                if (top.getValue().compareTo(entry.getValue()) == 0) {
                    //比较单词大小，小的入堆
                    if (top.getKey().compareTo(entry.getKey()) > 0) {
                        //说明堆顶的大
                        minHeap.poll();
                        minHeap.offer(entry);
                    }
                } else {
                    if (top.getValue().compareTo(entry.getValue()) < 0) {
                        minHeap.poll();
                        minHeap.offer(entry);
                    }
                }
            }
        }
        System.out.println(minHeap);
        List<String> ret = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            Map.Entry<String,Integer> top = minHeap.poll();
            ret.add(top.getKey());
        }
        Collections.reverse(ret);
        return ret;
    }

    public static void main10(String[] args) {
        String[] worlds = {"hello","abcd","ok","hello","abcd","ok","abcd","love","me"};
        List<String> str = topKFrequent(worlds,2);
        System.out.println(str);
    }

    public static void func(String strExce, String strActual) {
        Set<Character> set = new HashSet<>();
        Set<Character> actual = new HashSet<>();
        //用 foreach 可读性会很高
        for (int i = 0; i < strActual.length(); i++) {
            actual.add(strActual.toUpperCase().charAt(i));
        }
        for (int i = 0; i < strExce.length(); i++) {
            if (!actual.contains(strExce.toUpperCase().charAt(i)) && !set.contains(strExce.toUpperCase().charAt(i))) {
                System.out.println(strExce.toUpperCase().charAt(i));
                set.add(strExce.toUpperCase().charAt(i));
            }
        }
    }
    public static void main9(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();
        func(s1, s2);
    }

    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < jewels.length(); i++) {
            set.add(jewels.charAt(i));
        }
        int count = 0;
        for (int i = 0; i < stones.length(); i++) {
            if (set.contains(stones.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    //复制带有随即指针的链表
//    public node copyRandomList(node head) {
//        Map<node, node> map = new HashMap<>();
//        node cur = head;
//        while (cur != null) {
//            map.put(cur, new node(cur.val));
//            cur = cur.next;
//        }
//        cur = head;
//        while (cur != null) {
//            map.get(cur).next = map.get(cur.next);
//            map.get(cur).random = map.get(cur.random);
//            cur = cur.next;
//        }
//        return map.get(head);
//    }

    public static int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x:nums) {
            if (set.contains(x)) {
                set.remove(x);
            } else {
                set.add(x);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return nums[i];
            }
        }
        return -1;
    }
    public static void main8(String[] args) {
        int[] arr = {1,2,3,2,1};
        int ret = singleNumber(arr);
        System.out.println(ret);
    }

    //3、从 10w 个数据中，找出第一个重复的数据：每次放之前都检查一下 set 当中是不是有了，如果有了就返回
    public static int func3(int[] array) {
        Set<Integer> set = new HashSet<>();
        for (int x:array) {
            if (set.contains(x)) {
                return x;
            }
            set.add(x);
        }
        return -1;//-1 表示不包含这个元素。
    }
    public static void main7(String[] args) {
        int[] array = new int[10000];
        Random random = new Random();
        for (int i = 0; i< array.length; i++) {
            array[i] = random.nextInt(1000);
        }
        int ret = func3(array);
        System.out.println(ret);
    }

    //2、将 10w 个数据去重
    public static Set<Integer> func2(int[] array) {
        Set<Integer> set = new HashSet<>();
        for (int x:array) {
            set.add(x);
        }
        return set;
    }
    public static void main6(String[] args) {
        int[] array = new int[10000];
        Random random = new Random();
        for (int i = 0; i< array.length; i++) {
            array[i] = random.nextInt(1000);
        }
        Set<Integer> set = func2(array);
        System.out.println(set);
    }

    //1、给定 10w 个数据，统计每个数据出现的次数
    //key 是关键字， value 是出现的次数
    public static Map<Integer,Integer> func1(int[] array) {
        Map<Integer,Integer> map = new HashMap<>();
        //判断 array 中的元素，是否在 map当中，不在就是 1 ，在就是在原来的基础上 + 1
        for (int i = 0; i < array.length; i++) {
            if (map.get(array[i]) == null) {
                map.put(array[i],1);
            } else {
                int val = map.get(array[i]);
                map.put(array[i],val+1);
            }
        }
        return map;
    }
    public static void main5(String[] args) {
        int[] array = new int[10000];
        Random random = new Random();
        for (int i = 0; i< array.length; i++) {
            array[i] = random.nextInt(1000);
        }
        Map<Integer,Integer> map = func1(array);
        System.out.println(map);
    }

    //带 Tree 的底层就是红黑树

    //Set 是一个集合，存入里面的数据会自动去重
    public static void main4(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(1);
        System.out.println(set);
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        //LinkedHashSet 是 HeshMap 和 LinkedList 的结合
    }

    //entryset：会把 key 和 value 放在一起
    //HashMap 的插入，删除，查找的时间复杂度都是O(1)
    //TreeMap 里面的数据是一定可以比较的，如果要对插入的数据排序的话，就要用 TreeMap
    //做题用 HashMap
    public static void main3(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("abc",3);
        map.put("word",2);
        map.put("hello",4);
        map.put(null,null);//HashMap 是可以放 null 的 TreeMap 就会报错
        System.out.println(map);
        //这个放在一起就是 Set 集合，每个内容就是 key 和 value 在一起
        Set<Map.Entry<String,Integer>> entrySet =  map.entrySet();
        //输出值
        for (Map.Entry<String, Integer> entry : entrySet) {
            System.out.println(entry.getKey()+"->"+entry.getValue());
        }
    }

    public static void main2(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("abc",3);
        map.put("word",2);
        map.put("hello",4);
        //put 存储元素的时候，key 如果相同，val 值就会被覆盖
        System.out.println(map);
        //获取到所有的不重复的集合，set 当中放的元素都是不重复的
        Set<String> set = map.keySet();
        System.out.println(set);
    }

    //HashMap 在存储元素的时候，不是按照存储顺序进行打印的，
    // 存储的时候是根据一个函数进行存储的，具体存储到哪里，由函数来确定。这个函数就是哈希函数
    public static void main1(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        //put 往里面放元素
        map.put("abc",3);
        map.put("word",2);
        map.put("hello",4);
        System.out.println(map);//输出是：{abc=3, hello=4, word=2} 存放和输出的顺序不一样
        int ret = map.get("abc");//通过 key 获取对应的 value 值
        //如果没有值的话，就是空指针异常
        System.out.println(ret);
        //没有这个值的话，就是m默认值 98
        System.out.println(map.getOrDefault("abc",98));
        Integer ret2 = map.remove("abc");
        System.out.println(ret2);
        System.out.println(map);
    }

    //map 和 set 是一种专门用来搜索和查询的容器或数据结构，效率很高
    //为了解决在 增删查改 情况下使用的数据结构
    //模型：一般把搜索的数据称为关键字(key)，和关键字对于的称为值（value)  称为 key-value 的键值对
    // 纯 key 模型：set
    // key-value 模型：map

    //高阶数据结构考：AVL树，红黑树，B 系列树，并查集，位图 布隆过滤器，图，LRUCache
}
