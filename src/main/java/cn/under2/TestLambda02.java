package cn.under2;

public class TestLambda02 {


    public static void main(String[] args) {

        //1.局部内部类
        class Love implements Ilove{

            @Override
            public void love(int a) {
                System.out.println("i love ->" + a);
            }
        }

        Ilove ilove = new Love();
        ilove.love(5);

        //2.匿名内部类
        ilove = new Ilove() {
            @Override
            public void love(int a) {
                System.out.println("i love ->" + a);
            }
        };
        ilove.love(6);

        //3.lambda
        ilove = (int a)->{
            System.out.println("i love ->" + a);
        };
        ilove.love(7);

        //4.lambda
        ilove = (int a)->System.out.println("i love ->" + a);
        ilove.love(8);

        //5.lambda
        ilove = (a)->System.out.println("i love ->" + a);
        ilove.love(9);

        //6.lambda
        ilove = a->System.out.println("i love ->" + a);
        ilove.love(10);

        /**
         * 总结：
         *  1. 定义以恶搞函数式接口，并且只有一个方法
         *  2. 使用时定义一个接口然后直接下lambda表达式
         *  3.
         *     只有在方法体只有一行的时候才能省去花括号
         *     一个参数可以不带括号
         *     多个参数必须加括号，但是个省略参数类型
         */
    }
}
interface Ilove{
    void love(int a);
}
