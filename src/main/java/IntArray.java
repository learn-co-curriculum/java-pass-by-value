import java.util.Arrays;

public class IntArray {

    public static void testArray(int[] nums) {
        nums[0] = 10;
        nums = new int[3];
        nums[0] = 20;
    }

    public static void main(String[] args) {
        int[] grades = {88,99,77,100};

        System.out.println("before testArray:" + Arrays.toString(grades) );

        testArray(grades);

        System.out.println("after testArray:" + Arrays.toString(grades) );
    }
}
