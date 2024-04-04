package EnumConverter;

public enum CopyType {
    XMIN,
    IMPORT,
    WAL;

    public OperationType getOperationType() {
        switch (this){
            case IMPORT:
                return OperationType.IMPORT;
            case WAL:
                return OperationType.WAL;
            case XMIN:
                return OperationType.XMIN;
            default:
                throw new IllegalArgumentException("CopyType " + this.name() + " cannot be converted to any OperationType");
        }
    }
}
