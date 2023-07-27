package org.example;
import java.lang.annotation.*;
import java.lang.reflect.*;
@Target(value= ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    int a();
    int b();
}

class MyClass {
    @MyAnnotation(a = 2, b = 5)
    public static void test(int a, int b) {
        System.out.println(a + " + " + b + " = " + (a + b));
    }
}

public class Main {
    public static void main(String[] args) {
        Class<?> cls = MyClass.class;
        Method[] methods = cls.getMethods();
        try {
            for (Method method : methods) {
                if (method.isAnnotationPresent(MyAnnotation.class)) {
                    method.invoke(null, method.getAnnotation(MyAnnotation.class).a(),
                            method.getAnnotation(MyAnnotation.class).b());
                }
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}