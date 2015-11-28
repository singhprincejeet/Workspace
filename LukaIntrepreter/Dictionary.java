import java.util.NoSuchElementException;

// Author : Princejeet Singh Sandhu
public class Dictionary implements Map<String,Token> {

    private static Pair[] pair = new Pair[10];
    private int top=0;

    /**
     * returns the lefmost value associated with this specified key.
     *
     * @param key
     * @return result
     * @throws NullPointerException
     * @throws NoSuchElementException
     */
    public Token get(String key) throws NullPointerException, NoSuchElementException {
        if (key.equals(null)) {
            throw new NullPointerException();
        }
        else {
            int count =0;
            for (int i = top - 1; i > -1; i--) {
                if (key.equals(pair[i].getKey())) {
                    return pair[i].getValue();
                } else{
                    count++;
                }
            }
            if (count==top){
                throw new NoSuchElementException("key not found: " +key  );
            }
        }
        return null;
    }

    /**
     * returns true if an association exists for the specified key.
     *
     * @param key
     * @return result
     * @throws NullPointerException
     */
    public boolean contains(String key) throws NullPointerException {
        boolean result = false;
        if (key.equals(null)){
            throw new NullPointerException();
        }
        else {
            for (int i = top - 1; i > -1; i--) {
                if (key.equals(pair[i].getKey())) {
                    result = true;
                }
            }
            return result;
        }
    }

    /**
     *  creates a new association key-value.
     *
     * @param key
     * @param value
     * @throws NullPointerException
     */
    public void put(String key, Token value) throws NullPointerException {
        if (key.equals(null)){
            throw new NullPointerException();
        }
        else if (value.equals(null)){
            throw new NullPointerException();
        }
        else {
            if (top == pair.length) {
                Pair[] newPair = new Pair[pair.length + 5];
                for (int i = 0; i < pair.length; i++) {
                    newPair[i] = pair[i];
                }
                pair = newPair;
                put(key, value);
            } else {
                pair[top] = new Pair(key, value);
                top++;
            }
        }
    }

    /**
     * replaces the value of the leftmost occurrence of the association for the specified key.
     *
     * @param key
     * @param value
     * @throws NoSuchElementException
     * @throws NullPointerException
     */
    public void replace(String key, Token value) throws NoSuchElementException, NullPointerException {
        if (key.equals(null)){
            throw new NullPointerException();
        }
        else if (value.equals(null)){
            throw new NullPointerException();
        }
        else {
            int count =0;
            for (int i = top - 1; i > -1; i--) {
                if (key.equals(pair[i].getKey())) {
                    pair[i].setValue(value);
                    break;
                }
                else {
                    count++;
                }
            }
            if (count==top){
                throw new NoSuchElementException("key not found: " +key  );
            }
        }
    }

    public String toString() {

        StringBuffer result= new StringBuffer();
        result.append("{");
        for (int i =0; i<top; i++) {
            if(i!=0){
                result.append(",");
            }
            result.append("[key =");
            result.append(pair[i].getKey());
            result.append(",value=");
            result.append(pair[i].getValue()+"]");
        }
        result.append("}");
        return result.toString();
    }

    /**
     * removes the leftmost association for the specified key, and returns the value that was associated with the key.
     *
     * @param key
     * @return
     * @throws NoSuchElementException
     * @throws NullPointerException
     */
    public Token remove(String key) throws NoSuchElementException, NullPointerException {
        if(key.equals(null)){
            throw new NullPointerException();
        }
        else {
            int count=0;
            Pair temp;
            Token result = null;
            for (int i = top - 1; i > -1; i--) {
                if (key.equals(pair[i].getKey())) {
                    result = pair[i].getValue();
                    for (int j = i; j < top-1; j++) {
                        temp = pair[j];
                        pair[j] = pair[j + 1];
                        pair[j + 1] = temp;
                    }
                    break;
                }
                else{
                    count++;
                }
            }
            if (count==top){
                throw new NoSuchElementException("key not found: " +key );
            }
            pair[top - 1] = null;
            top--;
            return result;
        }
    }

    private static class Pair {
        private String key;
        private Token value;

        private Pair(String key, Token value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Token getValue() {
            return value;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public void setValue(Token value) {
            this.value = value;
        }
    }
}
