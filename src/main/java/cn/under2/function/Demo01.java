package cn.under2.function;

import java.util.function.Function;

/**
 * Function 函数式接口 有一个输入参数，有一个输出
 * 只要是函数式接口 就可以用Lambda简化
 */
public class Demo01 {
    public static void main(String[] args) {
        //工具类 输出 输入的值
        Function function = new Function<String, String>() {
            @Override
            public String apply(String str) {
                return str;
            }
        };

        Function functionLambda1 = (str) -> {
            return str;
        };
        Function functionLambda2 = str -> {
            return str;
        };
        Function functionLambda3 = str -> str;

        System.out.println(function.apply("hello "));
        System.out.println(functionLambda3.apply("hello lambda3 ."));
    }
}
