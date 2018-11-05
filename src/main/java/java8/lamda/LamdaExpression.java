package java8.lamda;

import java.util.function.*;

/**
 * Created by skumar6 on 11/11/17.
 */
public class LamdaExpression {

    public static void main(String [] args ) {
        FunctionalInterface f = new FunctionalInterface() {

            @Override
            public void show() {
                System.out.println("hello world");
            }
        };
        f.show();
        FunctionalInterface f1  = ()->System.out.println("Hello Lamda World!!");
        f1.show();
        BiConsumer<String, String> bi = (a, b) -> System.out.println(a+b);
        bi.accept("Sumit"," Kumar");
        BiConsumer<Integer, Integer> bi1 = (a, b) -> System.out.println(add(a,b));
        bi1.accept(5,10);
        BiPredicate<Boolean, Boolean> bp = (a, b) -> calculatePredicate(a,b);
        System.out.println(bp.test(false,false));
    }

    private static int add(int a, int b) {
        return (a+b)*10;
    }

    private static boolean calculatePredicate(Boolean a, Boolean b) {
         if(a || b) return true;
        else
            return false;
    }
}
