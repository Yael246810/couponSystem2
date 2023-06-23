package couponSystemSpring.example.CouponSystem.utils;

public class TestUtils {
    private static int count = 1;

    public static void test(String title){
        System.out.println(String.format("Test %03d - %s\n", count++, title));

    }
}
