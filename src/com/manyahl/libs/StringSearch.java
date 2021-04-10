package com.manyahl.libs;

import java.math.BigInteger;

public class StringSearch {

    /**
     * all possible character sets including lower case, uppercase, digits and special characters
     */
    private static String[] sets = { "abcdefghijklmnopqrstuvwxyz", "0123456789","!@#$%^&*" };

    /**
     * the character set chosen by the user
     */
    private static String chosenSet="";

    /**
     * possible character set combinations
     */
    public static enum CharSet{LOWER,UPPER,DIGIT,SPECIAL,LOWER_UPPER,LOWER_DIGIT,LOWER_SPECIAL,UPPER_DIGIT,
        UPPER_SPECIAL,DIGIT_SPECIAL,LOWER_UPPER_DIGIT,LOWER_UPPER_SPECIAL,LOWER_DIGIT_SPECIAL,
        UPPER_DIGIT_SPECIAL,LOWER_UPPER_DIGIT_SPECIAL};

    /**
     * @param set the character set combination
     */
    private static void setChoosenSet(CharSet set){
        switch(set){
            case LOWER:
                chosenSet =  sets[0];
                break;
            case UPPER:
                chosenSet =  sets[0].toUpperCase();
                break;
            case DIGIT:
                chosenSet =  sets[1];
                break;
            case SPECIAL:
                chosenSet =  sets[2];
                break;
            case LOWER_UPPER:
                chosenSet =  sets[0]+sets[0].toUpperCase();
                break;
            case LOWER_DIGIT:
                chosenSet =  sets[0]+sets[1];
                break;
            case LOWER_SPECIAL:
                chosenSet =  sets[0]+sets[2];
                break;
            case UPPER_DIGIT:
                chosenSet =  sets[0].toUpperCase()+sets[1];
                break;
            case UPPER_SPECIAL:
                chosenSet =  sets[0].toUpperCase()+sets[2];
                break;
            case DIGIT_SPECIAL:
                chosenSet =  sets[1]+sets[2];
                break;
            case LOWER_UPPER_DIGIT:
                chosenSet =  sets[0]+sets[0].toUpperCase()+sets[1];
                break;
            case LOWER_UPPER_SPECIAL:
                chosenSet =  sets[0]+sets[0].toUpperCase()+sets[2];
                break;
            case LOWER_DIGIT_SPECIAL:
                chosenSet =  sets[0]+sets[1]+sets[2];
                break;
            case UPPER_DIGIT_SPECIAL:
                chosenSet =  sets[0].toUpperCase()+sets[1]+sets[2];
                break;
            case LOWER_UPPER_DIGIT_SPECIAL:
                chosenSet =  sets[0]+sets[0].toUpperCase()+sets[1]+sets[2];
                break;
        }
    }

    /**
     * @param index the location or index of the string to search
     * @param set the character set combination to search the input string from
     * @return returns the string at the specified position or index
     */
    public static String findString(BigInteger index,CharSet set)throws InvalidIndexException{
        if(index.signum()==-1)
            throw new InvalidIndexException("Index can not be negative. (findString)");
        else {
            String str = "";
            setChoosenSet(set);
            int setLength = chosenSet.length();
            while (index.compareTo(BigInteger.ZERO) >= 0) {
                str = chosenSet.charAt(index.mod(BigInteger.valueOf(setLength)).intValue()) + str;
                index = index.divide(BigInteger.valueOf(setLength)).subtract(BigInteger.ONE);
            }
            return str;
        }
    }

    /**
     * @param str the input string
     * @param set the character set combination to search the input string from
     * @return the location or index of the input string in alphabetical order
     */
    public static BigInteger findIndex(String str,CharSet set)throws StringAndCharsetMismatchException{
        setChoosenSet(set);
        if(isStringValid(str)){
            int setLength = chosenSet.length(), strLength = str.length();
            BigInteger index = BigInteger.ZERO;
            BigInteger base=BigInteger.valueOf(setLength);
            for (int i = strLength - 1, j = 0; i >= 0; i--, j++)
                index = index.add(base.pow(i).multiply(BigInteger.valueOf(chosenSet.indexOf(str.charAt(j)))));
            index = index.add(countStringsFromLength1(strLength, base));
            return index;
        }else
            throw new StringAndCharsetMismatchException("Input string and character set do not match. (findIndex)");
    }

    /**
     * @param str the string to validate
     * @return a boolean value that determines if the string contains any character that doesn't appear in the chosen character set
     */
    private static boolean isStringValid(String str){
        for(char c:str.toCharArray()){
            if(chosenSet.indexOf(c)==-1)
                return false;
        }
        return true;
    }

    /**
     * @param endLength the length of s
     * @param base the number of characters in the chosen character set
     * @return the total number of strings between length 1 and endLength
     */
    private static BigInteger countStringsFromLength1(int endLength,BigInteger base){
        BigInteger count = BigInteger.ZERO;
        for (int i = 1; i < endLength; i++)
            count = count.add(base.pow(i));
        return count;
    }
}

class InvalidIndexException extends Exception{
    public InvalidIndexException(String message){
        super(message);
    }
}

class StringAndCharsetMismatchException extends Exception{
    public StringAndCharsetMismatchException(String message){
        super(message);
    }
}
