package com.example.desafio02.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NumberGenerator {
    private int next;
    private final static NumberGenerator instancia = new NumberGenerator();

    public static NumberGenerator getInstance(){
        return instancia;
    }

    public int getNext(){
        return ++next;
    }
}
