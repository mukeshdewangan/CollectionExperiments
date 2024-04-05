public class Main {
    enum Color{
        BLACK("#000000"),
        RED("#FF0000"),
        GREEN("#008000"),
        YELLOW("#FFFF00");

        String colorCode;
        Color(String cCode){
            this.colorCode = cCode;
        }

        @Override
        public String toString(){
            if(this == BLACK) return this.name().toLowerCase();
            return colorCode;
        }
    }
    public static void main(String[] args) {
        for (Color c : Color.values() ) {
            System.out.println(c);
        }
    }
}