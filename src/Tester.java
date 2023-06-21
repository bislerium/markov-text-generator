import edu.duke.FileResource;

import java.util.ArrayList;

public class Tester {
    public void testGetFollows() {
        MarkovOne mo = new MarkovOne();
        FileResource fr = new FileResource();
        String s = fr.asString().trim();
        mo.setTraining(s);
        ArrayList<String> ans = mo.getFollows("he");
        System.out.println(ans);
        System.out.println("The length is : " + ans.size());
    }
    public static void main(String[] args) {
        Tester t = new Tester();
        t.testGetFollows();
    }
}
