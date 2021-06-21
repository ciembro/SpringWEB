package com.crud.tasks.service;

import java.util.function.BinaryOperator;

public class ClassToDelete {

    public static Integer doSth(Integer x, Integer y, BinaryOperator<Integer> op){
        return op.apply(x,y);
    }

    public static void main(String[] args) {

        doSth(2, 4, new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer x, Integer y) {
                return x * y;
            }
        });

        doSth(2, 2, (x,y)->x*y );



    }
}
