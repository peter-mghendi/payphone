import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.foreign.SegmentScope;

import static org.example.libsample.LibSample.*;

public class Main {
    public static void main(String[] args) {
        int a = 8;
        int b = 2;

        // Sum
        int sum = add(a, b);
        System.out.printf("The sum is %d.\n", sum);

        // Subtract
        int difference = subtract(a, b);
        System.out.printf("The difference is %d.\n", difference);

        // Multiply
        int product = multiply(a, b);
        System.out.printf("The product is %d.\n", product);
        
        // Greet
        // Passing strings require a little more legwork. 
        // We allocate the string to a memory segment off-heap and pass the pointer to that to the native code.  
        SegmentAllocator allocator = SegmentAllocator.nativeAllocator(SegmentScope.auto());
        MemorySegment generalKenobi = allocator.allocateUtf8String("General Kenobi");
        MemorySegment result = greet(generalKenobi);
        String greeted = result.getUtf8String(0);
        System.out.printf("%s.\n", greeted);

        // Greet with a mull pointer
        String nullGreeted = greet(MemorySegment.NULL).getUtf8String(0);
        System.out.printf("%s.\n", nullGreeted);
    }
}