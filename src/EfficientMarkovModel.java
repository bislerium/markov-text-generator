import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel{

    private int order;
    private HashMap<String, ArrayList<String>> charMap;

    public EfficientMarkovModel(int order) {
        charMap= new HashMap<String, ArrayList<String>>();
        this.order = order;
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
        buildMap();
    }

    protected ArrayList<String> getFollows(String key) {
        return charMap.get(key);
    }

    public void buildMap() {
        charMap.clear();
        ArrayList<String > listChar;
        String key = myText.substring(0,order);    String character ="";
        for (int i = 0; i <= myText.length() - order; i++) {
            if (i != myText.length() - order) {
                character = myText.substring(i + order, i + order + 1);
            }
            if (!charMap.containsKey(key)) {
                listChar = new ArrayList<String>();
                if (i != myText.length() - order) {
                    listChar.add(character);
                }
                charMap.put(key, listChar);
            } else {
                listChar = getFollows(key);
//                if (!listChar.contains(character)) {
                if (i != myText.length() - order) {
                    listChar.add(character);
                }
//                }
            }
            key = key.substring(1) + character;
        }
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - order);
        String key = myText.substring(index, index + order);
        sb.append(key);
        for(int k=0; k < numChars; k++){
            ArrayList<String> followList = charMap.get(key);
            if (followList.size() == 0) {
                break;
            }
            index = myRandom.nextInt(followList.size());
            String character = followList.get(index);
            sb.append(character);
            key = key.substring(1) + character;
        }
        return sb.toString();
    }

    public void printHashMapInfo() {
        int maxSize = 0;
        ArrayList<String> maxKey = new ArrayList<String>();
        for (String ar : charMap.keySet()) {
            int currSize = charMap.get(ar).size();
            if (currSize > maxSize) {
                maxSize = currSize;
            }
        }
        for (String ar : charMap.keySet()) {
            int currSize = charMap.get(ar).size();
            if (currSize == maxSize) {
                maxKey.add(ar);
            }
        }
        System.out.println(charMap);
        System.out.println("The number of keys in the hashmap is : " + charMap.size());
        System.out.println("The size of the largest value in the HashMap is : " + maxSize);
        System.out.println("The keys that have the maximum size value is : " + maxKey.toString());
    }

    public String toString() {
        return  "Efficient-MarkovModel of order " + order;
    }

    public static void main(String[] args) {
        EfficientMarkovModel emm = new EfficientMarkovModel(1);
        emm.setTraining("yes-this-is-a-thin-pretty-pink-thistle");
        System.out.println(emm.myText.length());
        emm.buildMap();
        emm.printHashMapInfo();
    }
}

