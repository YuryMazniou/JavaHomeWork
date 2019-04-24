package by.it.mazniou.my_abstractfactory;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;

public class FactoryProducer {
    public static enum  HumanFactoryType{
        MALE,
        FEMALE
    }
    public static AbstractFactory getFactory(HumanFactoryType e){
        if(e== HumanFactoryType.MALE)return new MaleFactory();
        if(e== HumanFactoryType.FEMALE)return new FemaleFactory();
        return null;
    }
}

