package cn.under2;

/**
 * 推到Lambda表达式
 */
public class TestLambda {

    //3.静态内部类
    static class Like2 implements ILike{

        @Override
        public void lambda() {
            System.out.println("i like lambda2");
        }
    }


    public static void main(String[] args) {
        ILike like = new Like();
        like.lambda();

        Like2 like2 = new Like2();
        like2.lambda();
        //4.局部内部类
        class Like3 implements ILike{

            @Override
            public void lambda() {
                System.out.println("i like lambda3");
            }
        }
        Like3 like3 = new Like3();
        like3.lambda();

        //5.匿名内部类
        like = new ILike() {
            @Override
            public void lambda() {
                System.out.println("i like lambda4");
            }
        };
        like.lambda();
        //6.lambda简化
        like = ()-> System.out.println("i like lambda5");
        like.lambda();
    }

}

//1.定义以恶搞函数式接口
interface ILike{
    void lambda();
}
//2.实现类
class Like implements ILike{

    @Override
    public void lambda() {
        System.out.println("i like lambda");
    }
}
