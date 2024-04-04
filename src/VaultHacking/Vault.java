package VaultHacking;

public class Vault {
    static final int MAX_PASSWORD = 9999;
    int password;
    public Vault(int pass){
        password = pass;
    }
    public boolean isCorrectPassword(int guess) throws InterruptedException {
        try{
            Thread.sleep(2);
        }
        catch (InterruptedException e){
        }
        return password == guess;
    }
}
