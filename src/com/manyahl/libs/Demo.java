package com.manyahl.libs;

import java.math.BigInteger;

public class Demo {
    public static void main(String[] args) {
        try{
            System.out.println(StringSearch.findString(new BigInteger("73727"), StringSearch.CharSet.LOWER));
            System.out.println(StringSearch.findIndex("dear", StringSearch.CharSet.LOWER));
        }catch(InvalidIndexException | StringAndCharsetMismatchException e){
            System.out.println(e.getMessage());
        }
    }
}
