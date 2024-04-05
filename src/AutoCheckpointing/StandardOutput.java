package AutoCheckpointing;

public class StandardOutput implements Output {
    @Override
    public void upsert(Object obj) {
        System.out.println("Upsert ");
    }

    @Override
    public void update(Object obj) {
        System.out.println("Update ");
    }

    @Override
    public void delete(Object obj) {
        System.out.println("Delete ");
    }
}
