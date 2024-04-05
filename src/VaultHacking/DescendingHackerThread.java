package VaultHacking;

public class DescendingHackerThread extends HackerThread {
    public DescendingHackerThread(Vault v) {
        super(v);
    }

    @Override
    public void run() {
        for ( int i = Vault.MAX_PASSWORD; i >= 0 ; i--){
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
