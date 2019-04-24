package by.it.mazniou.my_abstractfactory.male;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

public class MaleFactory implements AbstractFactory {
    public Human getPerson(int age){
        if(age>0&&age<=KidBoy.MAX_AGE)return new KidBoy();
        if(age>KidBoy.MAX_AGE&&age<=TeenBoy.MAX_AGE)return new TeenBoy();
        if(age>TeenBoy.MAX_AGE)return new Man();
        return null;
    }
}
