package VaultHacking;

public abstract class HackerThread extends Thread {
    protected Vault vault;
    public HackerThread(Vault v){
        vault = v;
        this.setName(this.getClass().getSimpleName());
        this.setPriority(Thread.MAX_PRIORITY);
    }

    @Override
    public void start(){
        System.out.println("Starting hacker thread " + this.getName());
        super.start();
    }

}
