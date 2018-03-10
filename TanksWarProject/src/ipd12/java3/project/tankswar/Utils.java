package ipd12.java3.project.tankswar;

public class Utils {

    public static void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            threadMessage("I wasn't done!");
        }
    }

    public static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s%n", threadName, message);
    }

}
