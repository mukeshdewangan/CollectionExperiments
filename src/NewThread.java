class NewThread extends Thread {
    @Override
    public void run(){
        System.out.println("This is NewThread Class object " + this.getName());
        for(int i =0 ; i< 190;i++){
            System.out.println("KILLER : " + i);
        }
    }
}
