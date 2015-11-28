import java.util.NoSuchElementException;
// Author : Princejeet Singh Sandhu
/**
 *  A map stores key-value associations. For this project, a map can contain duplicate keys, in which cases the methods get, put, replace, and remove refer to the leftmost (last) association for the given key. Map is a generic type having two type parameters, K and V, where K is the type of the keys, and V is the type of the values.
 */
public interface Map<K,V> {

    public V get(K key) throws NullPointerException, NoSuchElementException;
    public boolean contains (K key) throws NullPointerException;
    public void put(K key, V value) throws NullPointerException;
    public void replace(K key, V value) throws NoSuchElementException, NullPointerException;
    public V remove(K key) throws NoSuchElementException, NullPointerException;


}
