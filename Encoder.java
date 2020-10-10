// Name - Shaik Kamal Mohammed Adil
// StudentID - 801151613

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Encoder {
	public static String file_name;
	
	// Function to implement Encoding 
	public static void encodeString(String inputstring,int bit_size) {
		double max_table_size=0;
		max_table_size=Math.pow(2, bit_size);
		int table_size=256;
	// Initializing dictionary table for characters
		Map<String,Integer> table=new HashMap<String,Integer>();
		for(int i=0;i<256;i++) {
			table.put((char)i+"", i);
		}
		List<Integer> outputstring=new ArrayList<Integer>();
		String string="";
		String symbol="";
	// Storing file input data into a character string	
		char[] chararray=inputstring.toCharArray();
	// Computing and storing new entries in the dictionary 	
		for(int i=0;i<chararray.length;i++) {
			symbol=""+chararray[i];
			if(table.containsKey(string+""+symbol))
					string=string+symbol;
			else {
				outputstring.add(table.get(string));
				if(table.size()<max_table_size) {
					table.put(string+""+symbol, table_size++);
				string=symbol;
				}
			}
		}
		if(!string.equals(""))
			outputstring.add(table.get(string));
	// Passing the encoded string to a file	
		createOutputFile(outputstring);
	}
	
	private static void createOutputFile(List<Integer> outputstring) {
		
		BufferedWriter writer=null;
	
		try {
		// Creating a new file and writing the contents of encoded string to the file  
			FileOutputStream fileStream = new FileOutputStream(file_name+".lzw");
			 writer = new BufferedWriter(new OutputStreamWriter(fileStream, "UTF-16BE"));
			 Iterator<Integer> Itr = outputstring.iterator();
				while (Itr.hasNext()) {
					writer.write(Itr.next());
				}
		      writer.flush();
		      writer.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}

	public static void main(String[] args) throws IOException {
		file_name=args[0];
		int bitlength=Integer.parseInt(args[1]);
		// Storing the content of the file in the string
		String data="";
		data=new String(Files.readAllBytes(Paths.get(file_name)));
		// trimming the extension of the file
		file_name=file_name.substring(0, file_name.lastIndexOf("."));
		encodeString(data, bitlength);

	}
}
