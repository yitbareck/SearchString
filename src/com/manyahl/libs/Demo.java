package com.manyahl.libs;

import java.math.BigInteger;

public class Demo {
    public static void main(String[] args) {
        try{
            System.out.println(StringSearch.findString(new BigInteger("32268744561300"), StringSearch.CharSet.LOWER_UPPER));
            System.out.println(StringSearch.findIndex("Ethiopia", StringSearch.CharSet.LOWER_UPPER));
        }catch(InvalidIndexException | StringAndCharsetMismatchException e){
            System.out.println(e.getMessage());
        }
    }
}
