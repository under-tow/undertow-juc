package cn.under2.function;

import java.util.function.Consumer;

/**
 * 要学会看源码
 *
 * @FunctionalInterface public interface Consumer<T> {
 * <p>
 * // 对输入的参数进行操作，没有返回值
 * * Performs this operation on the given argument.
 * * @param t the input argument
 * <p>
 * void accept(T t);
 */
public class Demo03 {
    public static void main(String[] args) {
        Consumer consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer i) {
                System.out.println(i + 5);
            }
        };
        Consumer<Integer> consumer1 = i -> System.out.println(i + 5);
        consumer.accept(8);
        consumer1.accept(1);
    }
}
