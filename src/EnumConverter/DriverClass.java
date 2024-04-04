package EnumConverter;

public class DriverClass {
    public static void main(String[] args) {
        HashCodeCaching hashCodeCaching = new HashCodeCaching("Mukesh");

        HashCodeCaching hashCodeCaching2 = new HashCodeCaching("Mukesh");
        System.out.println( hashCodeCaching.hashCode() + " " + hashCodeCaching2.hashCode());
        String str = new String("Many times");
        System.out.println(str.hashCode());

        HashCodeCachingNext next = new HashCodeCachingNext("Mukesh");
        System.out.println( next.hashCode() + " " + next.hashCode());

    }
}
