public class Primitives {

    public static void testPrimitives(double a, boolean b, int c) {
        a = 0.45;
        b = true;
        c = 100;
    }

    public static void main(String[] args) {
        double humidity = 0.9;
        boolean isRaining = false;
        int temperature = 58;

        System.out.printf("before testPrimitives: %.2f %b %d %n", humidity, isRaining, temperature);

        testPrimitives(humidity, isRaining, temperature);

        System.out.printf("after testPrimitives: %.2f %b %d %n", humidity, isRaining, temperature);

    }
}
