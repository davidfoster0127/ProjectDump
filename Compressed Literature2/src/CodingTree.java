//Assignment 4 - Compressed Literature2    David Foster & Zeeshan Karim
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CodingTree {
	//default map size
	public static final int CAP = 32768;
	//map of words and separators to codes
	public MyHashTable<String, String> codes;
	public MyHashTable<String, Integer> freqMap;
	//list of entries for sorting(by frequency) and tree formation
	public final ArrayList<Entry> keyList = new ArrayList<Entry>();
	public byte[] bytes;
	public Entry root;
	String bits;
	
	public static final String nonSeparators = "0123456789" +
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
			"abcdefghijklmnopqrstuvwxyz" +
			"'-";

	CodingTree(String fulltext) {
		codes = new MyHashTable<>(CAP);
		freqMap = new MyHashTable<>(CAP);
		
		StringBuilder temp = new StringBuilder();
		String word;
		String separator;
		
		// Read in file and add contents to freqMap and keyList
		for(int i = 0; i < fulltext.length(); i++){
			if(nonSeparators.contains(fulltext.substring(i, i+1))){
				temp.append(fulltext.substring(i, i+1));
			}
			else {
				if(temp.length() != 0){
					word = temp.toString();
					temp.delete(0, temp.length());
					if(freqMap.containsKey(word)){
						freqMap.put(word, freqMap.get(word) + 1);
					}
					else{
						freqMap.put(word, 1);
						keyList.add(new Entry(word, ""));
					}
				}
				separator = fulltext.substring(i, i+1);
				if(freqMap.containsKey(separator)){
					freqMap.put(separator, freqMap.get(separator) + 1);
				}
				else{
					freqMap.put(separator, 1);
					keyList.add(new Entry(separator, ""));
				}
			}
		}

		//assign correct frequency to each word within keyList
		for (int i = 0; i<keyList.size()-1; i++) {
			keyList.get(i).setFreq(freqMap.get(keyList.get(i).getKey()));
		}
		formTree(keyList);
		formCodes(root, "");
		writeBits(fulltext);
	}

	public static String decode(String infile, String keyfile) throws IOException{
		Map<String, String> dcodes = new HashMap<String, String>(); 
		StringBuilder encoded = new StringBuilder();
		StringBuilder decodekeys = new StringBuilder();
		StringBuilder decoded = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		int beginindex = 0;
		int endindex = 0;
		// Read in Byte data from compressed file
		byte[] indata = Files.readAllBytes(Paths.get(infile));
		
		// Convert byte data to string
		for(int i = 0; i < indata.length; i++){
			if(indata[i] < 0){
				encoded.append(Integer.toBinaryString(indata[i]).substring(24));
			}
			else{
				// The following line was implemented using stackoverflow for reference
				// http://stackoverflow.com/a/12310078
				encoded.append(String.format("%8s", Integer.toBinaryString(indata[i] & 0xFF)).replace(' ', '0'));
			}
			//System.out.println(encoded);
		}
		
		// The following line was implemented using stackoverflow for reference
		// http://stackoverflow.com/a/3403112
		// read in codes file
		Scanner scanner = new Scanner(new File("codes.txt"));
		decodekeys.append(scanner.useDelimiter("\\Z").next());
		scanner.close();
		

		// Create dcodes map with char keys and binary string values
		beginindex = 0;
		endindex = 0;
		do{
			endindex = decodekeys.indexOf(", ", beginindex);
			temp.append(decodekeys.substring(beginindex, endindex));
			dcodes.put(temp.substring(temp.indexOf("=", 1) + 1), temp.substring(0, temp.indexOf("=", 1)));
			beginindex = endindex + 2;
			temp.delete(0, temp.length());
		}while(beginindex < decodekeys.length());
		
		
		// Traverse encoded and decode with dcodes map, place result in decoded
		beginindex = 0;
		endindex = 0;
		do{
			endindex++;
			if(dcodes.containsKey(encoded.substring(beginindex, endindex))){
				decoded.append(dcodes.get(encoded.substring(beginindex, endindex)));
				// decoded.append(" "); // THIS IS A BAD HOTFIX
				beginindex = endindex;
			}
		}while(endindex < encoded.length());
		//System.out.println(decoded.toString());
		return decoded.toString();	
	}
	
	
	
	
	private void formTree(ArrayList<Entry> list) {
		ArrayList<Entry> temp = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			temp.add(list.get(i));
		}
		while (temp.size() > 1) {
			Collections.sort(temp);
			Entry newTree = new Entry(null, null);
			newTree.setFreq(temp.get(0).getFreq() + temp.get(1).getFreq());
			newTree.left = temp.get(0);
			newTree.right = temp.get(1);
			temp.remove(0);
			temp.remove(0);
			temp.add(0, newTree);
		}
		root = temp.get(0);	
	}

	private void formCodes(Entry troot, String tcode) {
		if (!troot.isLeaf()) {
			formCodes(troot.left, tcode+'0');
			formCodes(troot.right, tcode+'1');			
		} else {
			codes.put(troot.getKey(), tcode);
		}
	}
	
	private void writeBits(String fulltext) {
		StringBuilder encoded = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		
		// Read in file and encode using codes map
		for(int i = 0; i < fulltext.length(); i++){
			if(nonSeparators.contains(fulltext.substring(i, i+1))){
				temp.append(fulltext.substring(i, i+1));
			}
			else {
				if(temp.length() != 0){
					//System.out.print(temp.toString());
					encoded.append(codes.get(temp.toString()));
					temp.delete(0, temp.length());
				}
				//System.out.print(fulltext.substring(i, i+1));
				encoded.append(codes.get(fulltext.substring(i, i+1)));
				}
			}
		
		bytes = new byte[encoded.length()/8];
		for (int i = 0; i < bytes.length;i++) {
			bytes[i] = (byte) Integer.parseUnsignedInt(encoded.substring(i*8, (i*8)+8), 2);
		}
		System.out.println("Done");

	}
}
