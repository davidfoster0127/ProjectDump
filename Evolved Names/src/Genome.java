// Assignment #2, Evolved Names
// David Foster & Zeeshan Karim

public class Genome implements Comparable<Genome>{

	public static final String target = "CHRISTOPHER PAUL MARRIOTT";
	public static final char[] charSet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z', ' ', '-', '\''};
	private double muteRate;
	private StringBuilder name = new StringBuilder("A");
	
	//mutation rate between 0 and 1
	public Genome(double mutationRate) {
		muteRate = mutationRate;	
	}
	
	//copy constructor
	public Genome(Genome gene) {
		muteRate = gene.muteRate;
		name = new StringBuilder(gene.name.toString());
	}
	
	//chance to mutate one of 3 ways
	public void mutate() {
		int range = (int) (1/muteRate);
		int winner = Main.rng.nextInt(range);
		
		//mutation 1 add a char somewhere
		int mute1 = Main.rng.nextInt(range);
		if (mute1 == winner) {
			char newChar = charSet[Main.rng.nextInt(28)];
			int whereToAdd = Main.rng.nextInt(name.length()+1);
			if (whereToAdd == name.length()) name.append(newChar);
			else {
				String s1 = name.substring(whereToAdd);
				name.append(newChar);
				name.append(s1);
			}
			
		}
		
		//mutation 2 remove a char somewhere
		int mute2 = Main.rng.nextInt(range);
		if (mute2 == winner && name.length() > 2) {
			int whereToRemove = Main.rng.nextInt(name.length());
			name.deleteCharAt(whereToRemove);
		}
		
		//mutation 3 changing a char somewhere
		int mute3 = Main.rng.nextInt(range);
		if (mute3 == winner) {
			char newChar = charSet[Main.rng.nextInt(28)];
			int whereToChange = Main.rng.nextInt(name.length());
			name.deleteCharAt(whereToChange);
			name.insert(whereToChange, newChar);
		}

	}
	
	//update current genome by crossing it with other
	public void crossover(Genome other) {
		int i = 0;
		StringBuilder newName = new StringBuilder("");
		while (true) {
			if (Main.rng.nextBoolean()) {
				if (i < other.name.length()) {
					newName.append(other.name.charAt(i));
				}  else break;

			} else {//other parent chosen
				if (i < name.length()) {
					newName.append(name.charAt(i));
				} else break;
			}
			i++;			
		}
		name = newName;
		
	} 
	

	public Integer fitness() {
		
		int n = name.length();
		int m = target.length();
		
// ######################################################################################################
// 		The following code implements the Wagner Fischer Algorithm to determine Levenshtein edit distance
//		both this and the uncommented code below make use of the integers n and m, instantiated above		
//		
//		int D[][] = new int[n+1][m+1];
//		
//		for(int i = 0; i<m+1; i++){
//			D[0][i] = i;
//		}
//		for(int i = 0; i<n+1; i++){
//			D[i][0] = i;
//		}
//		
//		for(int i = 1; i<n+1; i++){
//			for(int j = 1; j<m+1; j++){
//				if(name.charAt(i-1) == target.charAt(j-1)){
//					D[i][j] = D[i-1][j-1];
//				}
//				else{
//					D[i][j] = Math.min(D[i-1][j] + 1, Math.min(D[i][j-1] + 1, D[i-1][j-1] + 1));
//				}
//			}
//		}
//		return (D[n][m] + (Math.abs(n-m)+1)/2);
// ######################################################################################################
		
		int max = Math.max(n,m);
		int min = Math.min(n,m);
		int f = Math.abs(m - n);
		for (int i = 0; i < max; i++) {
			if (i < min) {
				if (name.charAt(i) != target.charAt(i)) f++;
			} else f++;
		}
		return f;	
	}
	
	//display character string and fitness
	public String toString() {	
		return name.toString() + "  Fitness: " +fitness();
	}

	@Override
	public int compareTo(Genome other) {
	    if (fitness() > other.fitness()) return 1;
	    if (fitness() < other.fitness()) return -1;
		return 0;
	}
}
