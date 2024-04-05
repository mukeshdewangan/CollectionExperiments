package VaultHacking;

public class PoliceThread extends Thread {
    @Override
    public void run() {
        for(int i = 10 ; i > 0 ; i--){
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Remaining seconds " + i);
        }
        System.out.println("GameOver for you Hackers");
        System.exit(0);
    }
}
