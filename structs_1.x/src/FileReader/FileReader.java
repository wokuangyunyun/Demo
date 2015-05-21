package FileReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReader {
	
	public static void main(String[] args) throws IOException {
		FileInputStream f = new FileInputStream("D:/Demo/structs_1.x/src/A.txt");
		BufferedReader dr = new BufferedReader(new InputStreamReader(f));
		BufferedWriter dw = new BufferedWriter(new FileWriter("D:/Demo/structs_1.x/src/B.txt"));
		String line = dr.readLine();
		while (line != null) {
			System.out.println(line);
			line = dr.readLine();
			Pattern p = Pattern.compile("([\u4e00-\u9fa5]+)");
			if(line != null&&!line.trim().contains("//")&&!line.trim().startsWith("<title>")&&!line.trim().startsWith("<!--")){
				Matcher m = p.matcher( line );      
				String mv = null;    
				while (m.find()) {    
				   mv = m.group(0);
				   dw.write(mv+" ");
			    }
				if(mv != null){
					dw.write("\n");
				}
			}
		    dw.flush();
	   }
	}
}
