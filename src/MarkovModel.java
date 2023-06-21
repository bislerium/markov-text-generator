import java.util.ArrayList;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel{

    private int numberOfChar;

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

    public String toString() {
        return  "MarkovModel of order " + numberOfChar;
    }
}






