import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Twit {
    private int tweet_id;
    private String author_id;
    private String inbound;
    private Date created_at;
    private String text;
    private int response_tweet_id;
    private int in_response_to_tweet_id;

    public Twit(int tweet_id, String author_id, String inbound, Date created_at, String text, int response_tweet_id, int in_response_to_tweet_id) {
        this.tweet_id = tweet_id;
        this.author_id = author_id;
        this.inbound = inbound;
        this.created_at = created_at;
        this.text = text;
        this.response_tweet_id = response_tweet_id;
        this.in_response_to_tweet_id = in_response_to_tweet_id;
    }

    // Getters 
    public int tweet_id() {
        return tweet_id;
    }
    
    public String author_id() {
        return author_id;
    }

    public String inbound() {
        return inbound;
    }
    

    public Date created_at() {
        return created_at;
    }
   
    public String text() {
        return text;
    }
    
    public int response_tweet_id() {
        return response_tweet_id;
    }
    
    public int in_response_to_tweet_id() {
        return in_response_to_tweet_id;
    }
    

    // Method to parse date string to Date object
    public static Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
        Date date = formatter.parse(dateString);
        return date;
    }

    // Method to read Twits from a CSV file
    public static List<Twit> leerTwitsCsv(String filename) throws FileNotFoundException {
        List<Twit> twits = new ArrayList<Twit>();

        Scanner scanner = new Scanner(new File(filename));
        scanner.useDelimiter(",|\n");

        while (scanner.hasNext()) {
            try {
                int tweet_id = scanner.nextInt();
                String author_id = scanner.next();
                String inbound = scanner.next();
                Date created_at = parseDate(scanner.next());
                String text = scanner.next();
                int response_tweet_id = scanner.nextInt();
                int in_response_to_tweet_id = scanner.nextInt();

                Twit twit = new Twit(tweet_id, author_id, inbound, created_at, text, response_tweet_id, in_response_to_tweet_id);
                twits.add(twit);
            } catch (Exception e) {
                // Discard malformed lines
            }
        }
        scanner.close();

        return twits;
    }
}
