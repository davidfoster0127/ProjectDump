

//Assignment 4 - Compressed Literature2    David Foster & Zeeshan Karim
public class Entry implements Comparable<Entry>{
	private String key;
	private String code;
	private int freq;
	public Entry left, right;
	
	Entry(String key, String code) {
		this.key = key;
		this.code = code;
		freq = 1;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String newValue) {
		code = newValue;
	}
	
	public int getFreq() {
		return freq;
	}
	
	public void setFreq(int newfreq) {
		freq = newfreq;
	}
	
	public boolean isLeaf() {
        return (left == null) && (right == null);
    }

	@Override
	public int compareTo(Entry that) {
		return this.freq - that.freq;
	}
	
	public boolean equalsEnt(String otherkey) {
		return this.key.equals(otherkey);	
	}
}
