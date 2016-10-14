//Assignment 3 Compressed Literature - David Foster & Zeeshan Karim
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CodingTree {
	
	//stores chars in file with frequency
	public final Map<Character, Integer> charMap = new HashMap<Character, Integer>();
	//stores all codes used for encoding chars
	public final Map<Character, String> codes = new HashMap<Character, String>(); 
	//stores all char nodes sorted by frequency
	public final ArrayList<Node> charList = new ArrayList<Node>();
	//array of 8 bit chunks that comprise the encoded message
	public byte[] bytes;
	//contains the root of the final huffman tree
	public Node root;
	//contains string representation of encoded message as bits
	public StringBuilder encoded;
	
	static class Node implements Comparable<Node> {
        char ch;
        int freq;
        Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
        
        Node(int freq, Node left, Node right) {
        	this.freq = freq;
        	this.left = left;
        	this.right = right;
        }
        
        private boolean isLeaf() {
            return (left == null) && (right == null);
        }

        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
        
        public String toString() {
			return "Node "+ch + "  Frequency:" + freq;					
        }
	}
	
	public CodingTree(String message){
		
		//count frequency of chars in whole string
		//make a node for each new char
		for (int i = 0; i <message.length()-1; i++) {
			if (charMap.containsKey(message.charAt(i))) {
				int old = charMap.get(message.charAt(i));
				old++;
				charMap.put(message.charAt(i), old);
			} else {
				charMap.put(message.charAt(i), 1);
				charList.add(new Node(message.charAt(i), 1, null, null));
			}
		}
		
		//assign correct frequency to each char within arraylist
		for (int i = 0; i<charList.size(); i++) {
			charList.get(i).freq = charMap.get(charList.get(i).ch);
		}
		
		Collections.sort(charList);		
		formTree(charList);
		formCodes(root, "");
		writeBits(message);
	}
	
	private void formTree(ArrayList<Node> list) {
		ArrayList<Node> temp = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			temp.add(list.get(i));
		}
		while (temp.size() > 1) {
			Collections.sort(temp);
			Node newTree = new Node(temp.get(0).freq+temp.get(1).freq,
					temp.get(0), temp.get(1));
			temp.remove(0);
			temp.remove(0);
			temp.add(0, newTree);
		}
		root = temp.get(0);	
	}

	private void formCodes(Node troot, String tcode) {
		if (!troot.isLeaf()) {
			formCodes(troot.left, tcode+'0');
			formCodes(troot.right, tcode+'1');			
		} else {
			codes.put(troot.ch, tcode);
		}
	}
	
	private void writeBits(String message) {
		encoded = new StringBuilder();
		for (int i = 0; i <message.length()-1; i++) {
			encoded.append(codes.get(message.charAt(i)));
		}

		bytes = new byte[encoded.length()/8];
		for (int i = 0; i < bytes.length;i++) {
			bytes[i] = (byte) Integer.parseUnsignedInt(encoded.substring(i*8, (i*8)+8), 2);
		}
		System.out.println("done");
		
		//may have to gather last few bits		
//		if (codeString.length() % 8 != 0) {
//			bytes[k] = Byte.parseByte(codeString.substring(i*8, (i*8)+ 
//					(codeString.length() % 8)));
//		}
	}
	
	public static void decode(String infile, String keyfile) throws IOException{
		
		Map<String, Character> dcodes = new HashMap<String, Character>(); 
		StringBuilder encoded = new StringBuilder();
		StringBuilder decodekeys = new StringBuilder();
		StringBuilder decoded = new StringBuilder();
		
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
		while(decodekeys.length() > 1){
			int endindex;
			endindex = decodekeys.indexOf(", ");
			dcodes.put(decodekeys.substring(2, endindex), decodekeys.charAt(0));
			decodekeys.delete(0, endindex + 2);
		}
		
		// Traverse encoded and decode with dcodes map, place result in decoded
		int beginindex = 0;
		int endindex = 0;
		do{
			endindex++;
			if(dcodes.containsKey(encoded.substring(beginindex, endindex))){
				decoded.append(dcodes.get(encoded.substring(beginindex, endindex)));
				//System.out.print(dcodes.get(encoded.substring(beginindex, endindex)));
				beginindex = endindex;
			}
		}while(endindex < encoded.length());
		
		// Print decoded out to file
		Files.write(Paths.get("decoded.txt"), decoded.toString().getBytes());
	}
}
