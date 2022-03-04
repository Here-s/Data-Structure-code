package reflectdemo1;

class Student{
    //私有属性name
    private String name = "Lockey";
    //公有属性age
    public int age = 18;
    //不带参数的构造方法
    public Student(){
        System.out.println("Student()");
    }
    private Student(String name,int age) {
        this.name = name;
        this.age = age;
        System.out.println("Student(String,name)");
    }
    private void eat(){
        System.out.println("i am eat");
    }
    public void sleep(){
        System.out.println("i am pig");
    }
    private void function(String str) {
        System.out.println(str);
    }
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class TestDemo {

    public static void main(String[] args) throws ClassNotFoundException {

        //Student student = new Student("123",10);//报错是因为，这个构造方法是 私有的

        //获取方法1
        Class<?> c1 = Class.forName("reflectdemo1.Student");
        //获取方法2
        Class<?> c2 = Student.class;
        //获取方法3
        Student student = new Student();
        Class<?> c3 = student.getClass();

        //都是 true 是因为 class 对象只有一个，不管使用哪种方法来获取 class 对象，此时对象只有一个
        System.out.println(c1 == c2);
        System.out.println(c1 == c3);
        System.out.println(c2 == c3);
    }
}
