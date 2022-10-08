package com.github.prbrios.mfeapi;

public class Utils {
    
    public static int gerarNumeroSessao() {
        return (int) Math.floor(Math.random() * (999999 - 900000 + 1) + 900000);
    }

}
