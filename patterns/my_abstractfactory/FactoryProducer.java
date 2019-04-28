package by.it.mazniou.my_abstractfactory;


import by.it.mazniou.my_abstractfactory.female.FemaleFactory;
import by.it.mazniou.my_abstractfactory.male.MaleFactory;

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

