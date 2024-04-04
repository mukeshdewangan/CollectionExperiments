package OperationHandler;

import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    static List<OperationHandlers> emptyOperationHandlers(){
        return new ArrayList<>();
    }

    static List<OperationHandlers> fullOperationHandlers(){
        List<OperationHandlers> handlers = new ArrayList<>();
        handlers.add(new UpdateSchemaOperationHandler());
        handlers.add(new ActiveRowOperationHandler());
        return handlers;
    }


    public static void main(String[] args) {
        List<OperationHandlers> emptyHandlers = emptyOperationHandlers();
        emptyHandlers.forEach(handler -> handler.handle());

        System.out.println(" full handlers");

        List<OperationHandlers> handlers = fullOperationHandlers();
        handlers.forEach(handler -> handler.handle());
    }

}
