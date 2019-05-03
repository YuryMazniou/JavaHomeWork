package by.it.mazniou.agregator_links_jsoup;

import com.javarush.task.task28.task2810.model.Provider;

import java.util.Arrays;

public class Controller {
    private Provider[] providers;

    public Controller(Provider...providers){
        this.providers = providers;
        if(this.providers==null||this.providers.length==0)throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return "Controller{" +
                "providers=" + Arrays.toString(providers) +
                '}';
    }
}
