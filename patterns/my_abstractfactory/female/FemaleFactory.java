package by.it.mazniou.my_abstractfactory.female;


import by.it.mazniou.my_abstractfactory.AbstractFactory;
import by.it.mazniou.my_abstractfactory.Human;

public class FemaleFactory implements AbstractFactory {
    public Human getPerson(int age){
        if(age>0&&age<= KidGirl.MAX_AGE)return new KidGirl();
        if(age>KidGirl.MAX_AGE&&age<= TeenGirl.MAX_AGE)return new TeenGirl();
        if(age>TeenGirl.MAX_AGE)return new Woman();
        return null;
    }
}
