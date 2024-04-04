package VaultHacking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HackingSetup {
    public static void StartHacking(){
        Random random = new Random();
        int password = random.nextInt(Vault.MAX_PASSWORD);
        System.out.println("Password for vault " + password);

        Vault vault = new Vault(password);

        List<Thread> threads = new ArrayList<Thread>();
        threads.add(new AscendingHackerThread(vault));
        threads.add(new DescendingHackerThread(vault));
        threads.add(new PoliceThread());

        for ( Thread thread: threads) {
            thread.start();
        }

    }
}
