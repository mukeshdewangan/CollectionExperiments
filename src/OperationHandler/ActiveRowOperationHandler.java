package OperationHandler;


import java.util.Arrays;
import java.util.List;

public class ActiveRowOperationHandler extends OperationHandlers {
    @Override
    public void handle() {
        System.out.println("Active row event handler");

        String[] arr = {"a","b", "c","d","e", "f"};
        List<String> listArr = Arrays.asList(arr);

    }
}
