// Name - Shaik Kamal Mohammed Adil
// StudentID - 801151613

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Decoder {
	public static String output_filename;
	
		// Function to implement Decoding 
	public static void decodeString(String inputstring,int bit_size) throws IOException {
		double max_table_size=0;
		max_table_size=Math.pow(2, bit_size);
		int table_size=256;
		List<Integer> encodedstring=new ArrayList<Integer>();
		List<String> decodedstring=new ArrayList<String>();
		BufferedReader reader=null;
		InputStream stream=new FileInputStream(inputstring);
		reader=new BufferedReader(new InputStreamReader(stream,"UTF-16BE"));
		int value=0;
		// Loading the passed file content into string
		while((value=reader.read())!=-1) {
			encodedstring.add((int)value);
		}
		reader.close();
		// Initializing dictionary table for characters
		Map<Integer,String> table=new HashMap<Integer,String>();
		for(int i=0;i<=255;i++) {
			table.put(i,(char)i+"");
		}
		int code=encodedstring.get(0);
		String string=""+table.get(code);
		decodedstring.add(string);
		String newstring="";
		// Decoding the file content passed 
		for(int i=1;i<encodedstring.size();i++) {
			code=encodedstring.get(i);
			if(!table.containsKey(code)) {
				newstring=string+string.charAt(0);
			}
			else
				newstring=table.get(code);
			decodedstring.add(newstring);
			if(table.size()<max_table_size) {
				table.put(table_size++, string+newstring.charAt(0));
			}
			string=newstring;
			
			
		}
		// Passing the decoded string to a file	
		createOutputfile(decodedstring);
		
		
	}
	
	private static void createOutputfile(List<String> decodedstring) {
		
		BufferedWriter writer=null;
		
		try {
		// Creating a new file and writing the contents of decoded string to the file       
			FileOutputStream fileStream = new FileOutputStream(output_filename+"_decoded.txt");
			 writer = new BufferedWriter(new OutputStreamWriter(fileStream, "UTF-8"));
			 Iterator<String> Itr = decodedstring.iterator();
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
		String file_name=args[0];  
		output_filename = file_name;
		int bitlength=Integer.parseInt(args[1]);
		// trimming the extension of the file
		output_filename=output_filename.substring(0, output_filename.lastIndexOf("."));
		decodeString(file_name,bitlength);
	  } 
	}

