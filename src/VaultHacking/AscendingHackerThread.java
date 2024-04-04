package VaultHacking;

public class AscendingHackerThread extends HackerThread {
    public AscendingHackerThread(Vault v) {
        super(v);
    }

    @Override
    public void run() {
        for ( int i =0 ; i <= Vault.MAX_PASSWORD ; i++){
            try {
                if(vault.isCorrectPassword(i)){
                    System.out.println(this.getName() + " guessed the password " + i + " correctly");
                    System.exit(0);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
