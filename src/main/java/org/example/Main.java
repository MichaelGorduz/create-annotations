package org.example;

// Создание аннотации с параметрами

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Создание аннотации с параметрами
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String value();
    int count();
}

// Класс, содержащий метод, помеченный аннотацией
class MyClass {
    @MyAnnotation(value = "Hello World!", count = 5)
    public void myMethod() {
        System.out.println("myMethod() invoked");
    }
}

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        ; // Получение объекта класса MyClass
        MyClass obj = new MyClass();

        // Получение ссылки на метод с помощью рефлексии
        Method method = MyClass.class.getMethod("myMethod");

        // Проверка наличия аннотации
        if (method.isAnnotationPresent(MyAnnotation.class)) {
            // Получение объекта аннотации
            MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);

            // Получение параметров аннотации и вызов метода
            String value = annotation.value();
            int count = annotation.count();

            System.out.println("Значение параметра value: " + value);
            System.out.println("Значение параметра count: " + count);

            method.invoke(obj); // Вызов метода с помощью рефлексии
        }
    }
}