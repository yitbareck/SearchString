# StringSearch

StringSearch is a Java package for searching strings by their location in alphabetical order.

## Usage

```java
import com.manyahl.libs;
import java.math.BigInteger;

class Demo{
    public static void main(String[] srgs){
       try{
            System.out.println(StringSearch.findString(new BigInteger("73727"), StringSearch.CharSet.LOWER));//displays the 73727th string in alphabetical order (i.e. a, b, c, ..., z, aa, ab, ..., deap, deaq, dear)
            System.out.println(StringSearch.findIndex("dear", StringSearch.CharSet.LOWER));//displays the index or position of the string 'dear' in alphabetical order
        }catch(InvalidIndexException | StringAndCharsetMismatchException e){
            System.out.println(e.getMessage());
        }
    }
}
```

# Feedback

Feedbacks are welcome!
