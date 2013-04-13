package main.java;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;


import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class recupTweet {

	public static void main(String[] args) throws IOException, InterruptedException {
		try {
            // gets Twitter instance with default credentials
            Twitter twitter = new TwitterFactory().getInstance();
            
            java.io.File csvfile = new java.io.File("results.csv");
            if (! csvfile.exists())
				csvfile.createNewFile();
            if (! csvfile.exists())
				csvfile.createNewFile();
            if (! csvfile.canWrite())
			{
				System.out.println("Le fichier results.csv ne peut être écrirt !");
				System.exit(1);
			}
            
            OutputStreamWriter w_csv = new OutputStreamWriter ( new FileOutputStream (csvfile) );
            
            try {
            	User Gallious = twitter.verifyCredentials();
	            List<Status> statuses = twitter.getUserTimeline();
	            //System.out.println("Showing @" + Gallious.getScreenName() + "'s home timeline.");
	            for (Status status : statuses) {
	            	w_csv.write(CVSconvert.print(status));
					w_csv.write("\n");
	                //System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
	            }
	            Thread.sleep(3000);    
	        }
            
            catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Erreur de chargement de le timeline: " + te.getMessage());
	        }
			
		w_csv.close();
		
		System.exit(0);
		}
		
		catch (IOException e){
			System.out.println("Erreur avec le fichier .csv");
		}
    }

}


