package enumdemo;

public class Singleton {
    //懒汉模式实现单例模式，因为有一个对象之后， uniqueInstance 就不为空了，所以就不能再产生对象了
    private volatile static Singleton uniqueInstance;
    private Singleton() {}
    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class){
                if(uniqueInstance == null){//进入区域后，再检查一次，如果仍是null,才创建实例
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
