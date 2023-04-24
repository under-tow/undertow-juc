package cn.under2.function;

import java.util.UUID;
import java.util.function.Supplier;

/**
 * @FunctionalInterface public interface Supplier<T> {
 * 没有参数 返回一个值
 * There is no requirement that a new or distinct result be returned
 * each time the supplier is invoked.
 * * Gets a result.
 * * @return a result
 * <p>
 * T get();
 * }
 */
public class Demo04 {
    public static void main(String[] args) {
        Supplier supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "UUID ==> " + UUID.randomUUID().toString();
            }
        };

        Supplier<String> supplier1 = () -> "UUID ==> " + UUID.randomUUID().toString();
        System.out.println(supplier.get());
        System.out.println(supplier1.get());
    }
}
