package by.it.mazniou.costom_tree;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;
    private ArrayList<Entry>list=new ArrayList<>();
    private int count;

    static class Entry<T> implements Serializable{
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren=true;
            this.availableToAddRightChildren=true;
        }
        public void checkChildren(){
            if(leftChild!=null)this.availableToAddLeftChildren=false;
            if(rightChild!=null)this.availableToAddRightChildren=false;
        }
        public boolean isAvailableToAddChildren(){
            return availableToAddRightChildren||availableToAddLeftChildren;
        }
    }
    public CustomTree() {
        this.root = new Entry<>("0");
    }
    public String getParent(String s){
        String name=null;
        list.clear();
        LinkedList<Entry> que = new LinkedList<>();
        que.add(root);
        while(que.peek() != null){
            if(que.getFirst().elementName.equals(s)){
                name=que.getFirst().parent.elementName;
                break;
            }
            else
            if(que.getFirst().leftChild!=null)que.add(que.getFirst().leftChild);
            if(que.getFirst().rightChild!=null)que.add(que.getFirst().rightChild);
            que.remove(que.getFirst());
        }
        return name;
    }
    @Override
    public int size() {
        int count=0;
        LinkedList<Entry> que = new LinkedList<>();
        que.add(root);
        while(que.peek() != null){
            if(que.getFirst().leftChild!=null)que.add(que.getFirst().leftChild);
            if(que.getFirst().rightChild!=null)que.add(que.getFirst().rightChild);
            que.remove();
            count++;
        }
        return count-1;
    }

    @Override
    public boolean add(String s) {
        list.clear();
        LinkedList<Entry> que = new LinkedList<>();
        que.add(root);
        while(que.peek() != null){
            if(que.getFirst().leftChild==null&&que.getFirst().rightChild==null){
                list.add(que.getFirst());
                que.remove();
            }
            else{
                if(que.getFirst().leftChild!=null&&que.getFirst().rightChild==null){
                    list.add(que.getFirst());
                    que.remove();
                }
                else{
                    if(que.getFirst().leftChild==null&&que.getFirst().rightChild!=null){
                        list.add(que.getFirst());
                        //que.add(que.getFirst().rightChild);
                        que.remove();
                    }
                    else{
                        if(que.getFirst().leftChild!=null&&que.getFirst().rightChild!=null){
                            que.add(que.getFirst().leftChild);
                            que.add(que.getFirst().rightChild);
                            que.remove();
                        }
                    }
                }
            }
        }
        Entry entry=new Entry(s);
        if(list.get(0).leftChild==null&&list.get(0).rightChild==null){
            entry.parent=list.get(0);
            list.get(0).leftChild=entry;
            list.get(0).availableToAddLeftChildren=false;
        }
        else{
            if(list.get(0).leftChild!=null&&list.get(0).rightChild==null){
                entry.parent=list.get(0);
                list.get(0).rightChild=entry;
                list.get(0).availableToAddRightChildren=false;
            }
            else{
                if(list.get(0).leftChild==null&&list.get(0).rightChild!=null){
                    entry.parent=list.get(0);
                    list.get(0).leftChild=entry;
                    list.get(0).availableToAddLeftChildren=false;
                }
            }
        }
        return true;
    }

    /*@Override
    public void add(int index, String element) {
        list.clear();
        LinkedList<Entry> que = new LinkedList<>();
        que.add(root);
        while(que.peek() != null){
            if(que.getFirst().leftChild==null&&que.getFirst().rightChild==null){
                list.add(que.getFirst());
                que.remove();
            }
            else{
                if(que.getFirst().leftChild!=null&&que.getFirst().rightChild==null){
                    list.add(que.getFirst());
                    que.remove();
                }
                else{
                    if(que.getFirst().leftChild==null&&que.getFirst().rightChild!=null){
                        list.add(que.getFirst());
                        //que.add(que.getFirst().rightChild);
                        que.remove();
                    }
                    else{
                        if(que.getFirst().leftChild!=null&&que.getFirst().rightChild!=null){
                            que.add(que.getFirst().leftChild);
                            que.add(que.getFirst().rightChild);
                            que.remove();
                        }
                    }
                }
            }
        }
        Entry entry=new Entry(element);
        if(list.get(0).leftChild==null&&list.get(0).rightChild==null){
            entry.parent=list.get(0);
            list.get(0).leftChild=entry;
            list.get(0).availableToAddLeftChildren=false;
        }
        else{
            if(list.get(0).leftChild!=null&&list.get(0).rightChild==null){
                entry.parent=list.get(0);
                list.get(0).rightChild=entry;
                list.get(0).availableToAddRightChildren=false;
            }
            else{
                if(list.get(0).leftChild==null&&list.get(0).rightChild!=null){
                    entry.parent=list.get(0);
                    list.get(0).leftChild=entry;
                    list.get(0).availableToAddLeftChildren=false;
                }
            }
        }
    }*/
    @Override
    public boolean remove(Object o) {
        try{
            String delete=(String) o;
            LinkedList<Entry> que = new LinkedList<>();
            que.add(root);
            while(que.peek() != null){
                if(que.getFirst().leftChild!=null&&que.getFirst().leftChild.elementName.equals(delete)){
                    que.getFirst().leftChild=null;
                    que.getFirst().availableToAddLeftChildren=true;
                    break;
                }
                if(que.getFirst().rightChild!=null&&que.getFirst().rightChild.elementName.equals(delete)){
                    que.getFirst().rightChild=null;
                    que.getFirst().availableToAddRightChildren=true;
                    break;
                }
                else{
                    if(que.getFirst().leftChild!=null)
                        que.add(que.getFirst().leftChild);
                    if(que.getFirst().rightChild!=null)
                        que.add(que.getFirst().rightChild);
                    que.remove();
                }
            }
        }
        catch(Exception e){
            throw new UnsupportedOperationException();
        }
        return true;
    }
    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }
    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }
    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }
}
