//Assignment 3 Compressed Literature - David Foster & Zeeshan Karim
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		
		StringBuilder myChar = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader("WarAndPeace.txt"));
		File original = new File("WarAndPeace.txt");
		long starttime = System.nanoTime();
		//initial file read
		while(br.ready()){
			String line = br.readLine();
			myChar.append(line+"\n");
		}
		
		CodingTree ct = new CodingTree(myChar.toString());

		File codes = new File("codes.txt");
		if (!codes.exists()) {
			codes.createNewFile();
		}
		FileWriter fw = new FileWriter(codes.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		//write code file
		for (int i = 0; i < ct.codes.size(); i++) {
			bw.write(ct.charList.get(i).ch);
			bw.write("=" + ct.codes.get(ct.charList.get(i).ch) + ", ");
		}
		//testCodingTree(myChar);
		
		File file = new File("encoded.txt");
		if(!file.exists()){
			file.createNewFile();
		}

		//write encoded message file
		FileOutputStream fos = new FileOutputStream(file.getAbsoluteFile());
		fos.write(ct.bytes);
		fos.close();
		br.close();
		bw.close();
		
		//print statistics
		long endtime = System.nanoTime();
		double runtime = (double)(endtime - starttime)/1000000000; // in milliseconds
		System.out.println("Runtime: "+runtime+" ms");
		System.out.println("File Compression: "+
		((float) file.length()/(float)original.length())*100+"%");
		
		CodingTree.decode("encoded.txt", "codes.txt");

	}
	
	public static void testCodingTree(StringBuilder message) {
		CodingTree ct = new CodingTree(message.toString());
//		for (int i = 0; i <ct.charList.size()-1; i++) {
//			System.out.println(ct.charList.get(i).toString());
//		}
		
		System.out.println(ct.root.toString());
		System.out.println(ct.root.freq + " root");
	}
}
