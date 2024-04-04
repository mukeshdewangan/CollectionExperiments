package APIResponseFieldChange;

/** Levels of access for database objects */
public class JsonViews {
    /** For Jackson - by default, unlabelled fields are Public so don't need to actually use this */
    public static class Public {}

    /** Internal for Fivetran use, Backstage */
    public static class Internal extends Public {}
}