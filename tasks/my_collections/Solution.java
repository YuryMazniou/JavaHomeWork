package by.it.mazniou.my_collections;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("ddd");
        hashSet.add("rrrr");
        AmigoSet amigoSet = new AmigoSet(hashSet);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(amigoSet);
        objectOutputStream.close();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        AmigoSet amigoSet1 = (AmigoSet)  objectInputStream.readObject();
        System.out.println(amigoSet.equals(amigoSet1));
        System.out.println(amigoSet);
        System.out.println("________");
        System.out.println(amigoSet1);
        Set<Integer> set=new HashSet<>();
    }
}
