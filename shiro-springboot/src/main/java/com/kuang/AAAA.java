package com.kuang;

public class AAAA {


    private AAAA(){
    }
    private static AAAA Instance = new AAAA();

    public static AAAA getInstance(){
        return Instance;
    }
}
