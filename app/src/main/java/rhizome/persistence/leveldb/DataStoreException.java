package rhizome.persistence.leveldb;

public class DataStoreException extends RuntimeException {
    public DataStoreException(String message) {
        super(message);
    }

    public DataStoreException(String message, Throwable cause) {
        super(message, cause);
    }    
}
