package FokatInitialization;

public class AnotherStaticClass {
    private static final BigDummyClass dummyClass = new BigDummyClass("B.Ed.", 32);

    public static String getEducation(){
        return dummyClass.getEducation();
    }

    public static int getAge(){
        return dummyClass.getAge();
    }
}
