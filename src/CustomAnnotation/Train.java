package CustomAnnotation;

@MyAnnotation(value = 1, value2 = "chuggy_train")
public class Train {
    public String name;
    public Integer count;

    public Train(String name, Integer count){
        this.count = count;
        this.name = name;
    }
    public Train(){

    }
    @Override
    public String toString(){
        return "This train "+ name + " has "+ count + " bogies";
    }
}
