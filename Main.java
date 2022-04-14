import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Main {

    public static Queue<String> calls = new ConcurrentLinkedQueue<>();
    public static volatile boolean cycle = true;

    public static void main(String[] args) {

        Thread ATC = new Thread(new ATC(calls));
        ATC.start();

        Thread operator = new Thread(new Operator(calls, "Оператором "));
        operator.start();

        try {
            ATC.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Работа АТС завершена");
        try {
            operator.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Работа операторов завершена");
    }
}
