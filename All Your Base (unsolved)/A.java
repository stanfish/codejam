import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;


public class A {
	static Vector<String> digits;
	static Map<String,Integer> alphabet;
	static Map<Integer,Long> powers;
	
	public static int readInt(BufferedReader r) throws Exception {
		
		return Integer.parseInt(r.readLine());
	}
	
	public static void readDigits(BufferedReader r) throws Exception {
		
		String s = r.readLine();
		//System.out.print(s + " -> ");
		for (int i=0;i<s.length();i++) {
			digits.add(s.substring(i,i+1));
			alphabet.put(digits.elementAt(i),-1);
		}
	}
	public static long power(int a, int b) {
		if (powers.containsKey(b)) return powers.get(b);
		long res = 1;
		for(int i = 0; i < b; i++) {
		res *= a;
		}
		powers.put(b, res);
		return res;
		}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader r = new BufferedReader(new FileReader(args[0]));
		BufferedWriter w = new BufferedWriter(new FileWriter(args[1]));
		
		int T = readInt(r);
		
		String res;
		int base, i, j, k;
		long N;
		for (int caseN=1; caseN <= T; caseN++) {
			digits = new Vector<String>();
			alphabet = new HashMap<String,Integer>();
			powers = new HashMap<Integer,Long>();
			res = "";
			readDigits(r);
			base = alphabet.size();
			if (base==1) {
				base=2;
				alphabet.put("sym", -1);
			}
			
			alphabet.put(digits.elementAt(0), 1); // first digit=1
			// second (different) digit=0
			for (i=1;i<digits.size();i++) {
				if (alphabet.get(digits.elementAt(i))==-1) {
					alphabet.put(digits.elementAt(i), 0);
					break;
				}
			}
			// other digits
			j = 2;
			for (k=i;k<digits.size();k++) {
				if (alphabet.get(digits.elementAt(k))==-1) 
					alphabet.put(digits.elementAt(k), j++);
			}
			
			// calc N
			j = 0;
			N = 0;
			for (i=digits.size()-1;i>=0;i--) {
				N += alphabet.get(digits.elementAt(i)) * power(base, j++);
			}
			
			//System.out.print("Case #" + caseN + ": " + N + "\n");
			w.write("Case #" + caseN + ": " + N + "\n");
			//w.flush();
		}
		w.close();
	}

}
