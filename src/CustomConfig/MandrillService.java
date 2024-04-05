package CustomConfig;

import java.util.Optional;

public class MandrillService implements ServiceInterface {
    public Optional<CustomConfig> customConfig;
    public String name;
    public String schema;

    @Override
    public void setCustomConfiguration(CustomConfig config){
        this.customConfig = Optional.of(config);
    }

    @Override
    public Class<? extends CustomConfig> getCustomConfigClass(){
        return MandrillCustomConfig.class;
    }
}
