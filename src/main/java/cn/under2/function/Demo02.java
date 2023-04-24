package cn.under2.function;


import java.util.function.Predicate;

/**
 * @FunctionalInterface public interface Predicate<T> {
 * <p>
 * 根据输入的参数 进行评估计算 如果符合条件 就返回true，否则 false
 * * Evaluates this predicate on the given argument.
 * *
 * * @param t the input argument
 * * @return {@code true} if the input argument matches the predicate,
 * * otherwise {@code false}
 * <p>
 * boolean test(T t);
 */
public class Demo02 {
    public static void main(String[] args) {
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String str) {
                //判断字符串是否为空
                return str.isEmpty();
            }
        };
        Predicate<String> predicate1 = (str) -> {
            return str.isEmpty();
        };
        Predicate<String> predicate2 = (str) -> str.isEmpty();
        System.out.println(predicate.test(""));
        System.out.println(predicate1.test("hello"));
        System.out.println(predicate2.test(""));
    }
}
