import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.net.*;
import com.google.gson.*;

public class Solution {
    /*
     * Complete the function below.
     * https://en.wikipedia.org/w/api.php?action=parse&section=0&prop=text&format=json&page=[topic]
     */

    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }
    
    public static int countMatches(String text, String str)
    {
        if (isEmpty(text) || isEmpty(str)) {
            return 0;
        }
 
        Matcher matcher = Pattern.compile(str).matcher(text);
 
        int count = 0;
        while (matcher.find()) {
            count++;
        }
 
        return count;
    }

    static int getTopicCount(String topic) throws Exception {
        String sURL = "https://en.wikipedia.org/w/api.php?action=parse&section=0&prop=text&format=json&page=" + topic;

        URL url = new URL(sURL);
        URLConnection request = url.openConnection();
        request.connect();

        JsonParser jsonParser = new JsonParser();
        String text = jsonParser.parse(new InputStreamReader((InputStream) request.getContent()))
            .getAsJsonObject().get("parse")
            .getAsJsonObject().get("text")
            .getAsJsonObject().get("*").getAsString();

        return countMatches(text, topic);
    }

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        int res;
        String _topic;
        try {
            _topic = in.nextLine();
        } catch (Exception e) {
            _topic = null;
        }
        
        try {
            res = getTopicCount(_topic);
        } catch (Exception e) {
            res = 0;
        }
        bw.write(String.valueOf(res));
        bw.newLine();
        
        bw.close();
    }
}
