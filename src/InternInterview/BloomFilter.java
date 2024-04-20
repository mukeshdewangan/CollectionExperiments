package InternInterview;

public class BloomFilter<T> {

    public static void main(String[] args) {
        BloomFilter<Integer> integerBloomFilter = new BloomFilter<>();
        integerBloomFilter.addKey(10);
        integerBloomFilter.addKey(13);
        //integerBloomFilter.addKey(42);
        integerBloomFilter.addKey(98);
        //integerBloomFilter.addKey(67);

        System.out.println(integerBloomFilter.containsKey(10));
        System.out.println(integerBloomFilter.containsKey(11));
        System.out.println(integerBloomFilter.containsKey(42));
        System.out.println(integerBloomFilter.containsKey(98));
    }

    private final char[] bitMap = new char[64];

    public BloomFilter(){
        initialize();
    }

    public void addKey(T key){
        int first = hashFunction1(key);
        int second = hashFunction2(key);
        addToBitMap(first);
        addToBitMap(second);
    }

    public boolean containsKey(T key){
        int hash1 = hashFunction1(key);
        int hash2 = hashFunction2(key);
        return mayContainInBitMap(hash1) && mayContainInBitMap(hash2);
    }

    private void initialize(){
        for (int i = 0; i < 64; i++) {
            bitMap[i] = '0';
        }
    }

    private int hashFunction1(T key){
        return (key.hashCode()*100003) ;
    }

    private int hashFunction2(T key){
        return (key.hashCode()*100103);
    }

    private void addToBitMap(int num){
        String binaryString = Integer.toBinaryString(num);
        char[] binary = binaryString.toCharArray();
        int lastIndex =  binary.length-1;
        int index = 0;
        while(index <= lastIndex){
            if(binary[lastIndex-index] == '1' ){
                bitMap[index] = binary[lastIndex-index];
            }
            index++;
        }
//        for (int i=0; i < binary.length;i++) {
//            if(binary[i] == '1' ) bitMap[i] = binary[i];
//        }
    }

    private boolean mayContainInBitMap(int num){
        String binaryString = Integer.toBinaryString(num);

        char[] binary = binaryString.toCharArray();

        int lastIndex =  binary.length-1;
        int index = 0;
        while(index <= lastIndex){
            if(binary[lastIndex-index] == '1' && bitMap[index] == '0')
                return false;
            index++;
        }
//        for (int i = 0; i < binary.length; i++) {
//            if(binary[i] == '1' && bitMap[i] == '0') return false;
//        }
        return true;
    }
}
