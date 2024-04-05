package CustomConfig;

public interface ServiceInterface {

    default void setCustomConfiguration(CustomConfig config){
        throw new UnsupportedOperationException("Custom configuration is not supported for this service");
    }

    default Class<? extends CustomConfig> getCustomConfigClass(){
        throw new UnsupportedOperationException("Custom configuration is not supported for this service");
    }

}
