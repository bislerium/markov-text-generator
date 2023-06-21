
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;

    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> followList = new ArrayList<String>();
        int index = myText.indexOf(key);
        int keyLen = key.length();
        while (index != myText.length()-keyLen) {
            if (index == -1) {
                break;
            }
            String character = myText.substring(keyLen + index, index + keyLen + 1);
            followList.add(character);
            index = index+1;
            index = myText.indexOf(key,index);
        }
        return followList;
    }

}
