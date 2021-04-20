package search;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LSEdriver {

	static Scanner sc = new Scanner(System.in);
		
		static String getOption() 
		{
			System.out.print("getKeyWord(): ");
			String response = sc.next();
			return response;
		}
		
		public static void main(String[] args) throws IOException {
			// TODO Auto-generated method stub

			LittleSearchEngine lse = new LittleSearchEngine();
			
			try
			{
				lse.makeIndex("docs.txt", "noisewords.txt");
			} 
			catch (FileNotFoundException e)
			{
			}		
			
			
			for (String hi : lse.keywordsIndex.keySet()) {
			System.out.println(hi+" "+lse.keywordsIndex.get(hi));

			
		}
			System.out.println(lse.top5search("wild","flatbed"));

		}

	}
