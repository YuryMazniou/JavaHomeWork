package by.it.mazniou.my_abstractfactory.female;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

public class FemaleFactory implements AbstractFactory {
    public Human getPerson(int age){
        if(age>0&&age<= KidGirl.MAX_AGE)return new KidGirl();
        if(age>KidGirl.MAX_AGE&&age<= TeenGirl.MAX_AGE)return new TeenGirl();
        if(age>TeenGirl.MAX_AGE)return new Woman();
        return null;
    }
}
