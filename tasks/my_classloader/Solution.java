package by.it.mazniou.my_classloader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Set set=new HashSet();
        AnimalLoader loader = new AnimalLoader(pathToAnimals, ClassLoader.getSystemClassLoader());
        File myFolder = new File(pathToAnimals);
        File[] files = myFolder.listFiles();
        List<String>list=new ArrayList<>();
        for (int i = 0; i <files.length; i++) {
            if(files[i].getName().contains(".class")){
                list.add(files[i].getName().replace(".class",""));
            }
        }
        System.out.println(list);
        for (int i = 0; i <list.size(); i++) {
            Class clazz = loader.loadClass(list.get(i));
            if(Animal.class.isAssignableFrom(clazz)){
                Constructor[] constructors = clazz.getConstructors();
                for (Constructor constructor : constructors) {
                    Class[] paramTypes = constructor.getParameterTypes();
                    if(paramTypes.length==0)set.add(clazz.newInstance());
                }
            }
        }
        return set;
    }
}
