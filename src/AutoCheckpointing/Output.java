package AutoCheckpointing;

public interface Output {
    void upsert(Object obj);
    void update(Object obj);
    void delete(Object obj);
}

