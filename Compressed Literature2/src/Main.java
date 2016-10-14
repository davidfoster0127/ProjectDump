import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//Assignment 4 - Compressed Literature2    David Foster & Zeeshan Karim
public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder message = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader("WarAndPeace.txt"));
		File original = new File("WarAndPeace.txt");
		long starttime = System.nanoTime();
		//initial file read
		while(br.ready()){
			String line = br.readLine();
			message.append(line+"\n");
		}
		CodingTree ct = new CodingTree(message.toString());
		
		File codes = new File("codes.txt");
		if (!codes.exists()) {
			codes.createNewFile();
		}
		FileWriter fw = new FileWriter(codes.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		//write code file
		//for (int i = 0; i < ct.codes.entries; i++) {
		for (int i = 0; i < ct.codes.entries; i++) {
			bw.write(ct.keyList.get(i).getKey());
			bw.write("=" + ct.codes.get(ct.keyList.get(i).getKey()) + ", ");
			
		}
		
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
		double runtime = (double)(endtime - starttime)/1000000; // in milliseconds
		System.out.println("Runtime: "+runtime+" ms");
		System.out.println("File Compression: "+
		((float) file.length()/(float)original.length())*100+"%");
		
		Files.write(Paths.get("decoded.txt"), CodingTree.decode("encoded.txt", "codes.txt").getBytes());
	}
	
	public void testCodingTree() {
		
	}
	
	public void testHashTable() {
		
	}

}
