import java.util.ArrayList;
import java.util.Random;

public class MarkovModel {
    private int numberOfChar;
    private String myText;
    private Random myRandom;

    public MarkovModel(int num) {
        numberOfChar = num;
        myRandom = new Random();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public ArrayList<String> getFollows(String key) {
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

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - numberOfChar);
        String key = myText.substring(index, index + numberOfChar);
        sb.append(key);
        for(int k=0; k < numChars; k++){
            ArrayList<String> followList = getFollows(key);
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
}






