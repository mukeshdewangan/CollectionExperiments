package FokatInitialization;

public class StaticClass {
    private static final DummyClass dummyClass = new DummyClass("sunny", "money");

    public static String getFirstName(){
        return dummyClass.getFirstName();
    }

    public static String getLastName(){
        return dummyClass.getLastName();
    }
}
