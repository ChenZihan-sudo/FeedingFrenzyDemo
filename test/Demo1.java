package test;

public class Demo1 {

    public static void main(String[] args) {

        Test t1 = new Test();

        Test t2 = new Test();

        t1.p = "变量赋值了";
        t1.x =1;
        t1.say();
        t2.x =2;
        t1.say();
        t2.say();

    }

}

class Test{

    static String p;

    static int x;

    void say(){

        System.out.println("方法执行了");

        System.out.println(p);

        System.out.println(x);

    }

}