package com.example.desafio02.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NumberGenerator {
    private int next;

    public NumberGenerator(int next) {
        this.next = next;
    }

    public int getNext(){
        return ++next;
    }
}
