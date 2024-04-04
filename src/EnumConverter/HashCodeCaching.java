package EnumConverter;

public class HashCodeCaching {
        private final String name;
        private final int cachedHashCode;

        public HashCodeCaching(String name) {
            this.name = name;
            this.cachedHashCode = calcHashCode();
        }

        @Override
        public final int hashCode() {
            return cachedHashCode;
        }

        private int calcHashCode() {
            return name.hashCode();
        }

        @Override
        public boolean equals(Object o){
            return (this == o)
                    || ((o != null)
                    && (o.getClass() == getClass())
                    && name.equals(((HashCodeCaching)o).name));
        }
}

class HashCodeCachingNext extends HashCodeCaching{

    public HashCodeCachingNext(String name) {
        super(name);
    }
}
