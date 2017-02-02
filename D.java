import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import java.math.*;
import java.io.*;
import java.util.*;
@SuppressWarnings("unchecked")
public class D{
	public static void main(String[] args) throws Exception{
		long start = System.currentTimeMillis();
		int t = INT(in.readLine());
		int T = 0;
		while(t-->0){
			T++;
			out.printf("Case #%d: ",T);
			new D().doit();
		}
	//	System.out.println("Time taken: "+((System.currentTimeMillis()-start)/1000.0)+"s");

	}
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter out = new PrintWriter(System.out, true);
	static StringTokenizer st;

	static int INT(Object o ) throws Exception { return Integer.parseInt(o.toString()); }
	static double DOUBLE(Object o ) throws Exception { return Double.parseDouble(o.toString()); }
	static void ST() throws Exception{ st = new StringTokenizer(in.readLine());}


	void doit() throws Exception{
		ST();
		nitems = INT(st.nextToken());
		nstores = INT(st.nextToken())+1;
		gas = DOUBLE(st.nextToken());
		String[] its = new String[nitems];
		ST();
		perish = new boolean[nitems];
		for(int i = 0; i < nitems; i++){
			its[i] = st.nextToken();
			if(its[i].charAt(its[i].length()-1) == '!'){
				its[i] = its[i].substring(0, its[i].length()-1);
				perish[i] = true;
			}
		}
		xs = new int[nstores];
		ys = new int[nstores];
		prices = new int[nstores][nitems];
		for(int i = 0; i < nstores; i++) fill(prices[i], Integer.MAX_VALUE/2);
		for(int i = 0; i < nstores-1; i++){
			ST();
			xs[i] = INT(st.nextToken());
			ys[i] = INT(st.nextToken());
			while(st.hasMoreTokens()){
				String s = st.nextToken();
				int ind = s.indexOf(':');
				prices[i][find(its, s.substring(0, ind))] = INT(s.substring(ind+1));
			}
		}

		PriorityQueue<State> que = new PriorityQueue();
		que.add(new State(nstores-1, 0, 0, 0));
		boolean[][][] been = new boolean[nstores][1<<nitems][2];
		while(true){
			State s = que.poll();
			if(been[s.at][s.msk][s.peri]) continue;
			been[s.at][s.msk][s.peri] = true;
			if(s.at == nstores-1 && s.msk+1 == (1<<nitems)){
				out.println(String.format("%.7f",s.cost).replaceAll(",","."));
				return;
			}
			for(int i = 0; i < nstores-1 && s.peri == 0; i++){
				if(been[i][s.msk][s.peri]) continue;
				que.add(new State(i, s.msk, s.peri, s.cost+gas*dist(s.at,i)));
			}
			if(!been[nstores-1][s.msk][0]) que.add(new State(nstores-1, s.msk, 0, s.cost+gas*dist(s.at, nstores-1)));
			for(int i = 0; i < nitems; i++){
				if(perish[i]){
					if(!been[s.at][s.msk|(1<<i)][1]) que.add(new State(s.at, s.msk | (1<<i), 1,s.cost+prices[s.at][i]));
				}
				else if(!been[s.at][s.msk|(1<<i)][s.peri]) que.add(new State(s.at, s.msk|(1<<i), s.peri, s.cost+prices[s.at][i]));
			}
		}
	}
	double dist(int a, int b){
		return sqrt((xs[a]-xs[b])*(xs[a]-xs[b])+(ys[a]-ys[b])*(ys[a]-ys[b]));
	}
	int nitems;
	int nstores;
	boolean[] perish;
	int[][] prices;
	int[] xs, ys;
	double gas;
	int find(String[] ar, String s){
		for(int i = 0; i < ar.length; i++) if(ar[i].equals(s)) return i;
		return -1;
	}
	class State implements Comparable<State>{
		int at, msk, peri;
		double cost;
		State(int a, int m, int peri, double c){ at = a; msk = m; cost = c; this.peri = peri;}
		public int compareTo(State s){
			if(cost != s.cost) return cost < s.cost ? -1 : 1;
			return 0;
		}
	}



	boolean isSquare(long n){
		return Arrays.binarySearch(sqs, n) >= 0;
	}


	void printMat(double[][] mat){
		for(int i = 0; i < mat.length; i++){
			for(int j = 0; j < mat[i].length; j++){
				System.out.printf("%f ",mat[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	BigInteger getRoot(BigInteger n){
		BigInteger low = BigInteger.ZERO;
		BigInteger high = n;
		while(low.compareTo(high) < 0){
			BigInteger mid = low.add(high).divide(new BigInteger("2"));
			int cmp = mid.multiply(mid).compareTo(n);
			if(cmp == 0) return mid;
			if(cmp > 0) high = mid.subtract(BigInteger.ONE);
			else low = mid.add(BigInteger.ONE);
		}
		if(low.multiply(low).equals(n)) return low;
		return null;
	}


	boolean palin(String s){
		for(int i = 0; i < s.length()/2; i++) if(s.charAt(i) != s.charAt(s.length()-1-i)) return false;
		return true;
	}



	long gcd(long a, long b){
		return b == 0 ? abs(a) : gcd(b, a%b);
	}

	boolean isPrime(long n){
		if(n < primes.length) return primes[(int)n];
		/*for(int i = 0; ps[i]*ps[i] <= n; i++){
			if(n%ps[i] == 0) return false;
		}
		return true;*/
		BigInteger big = new BigInteger(n+"");
		return big.isProbablePrime(1);
	}

	boolean[] squares;
	long[] sqs;
	void squares(){
		squares = new boolean[1000000];
		sqs = new long[1000000];
		for(long i = 0; i < sqs.length; i++){
			sqs[(int)i] = i*i;
			if(i*i < squares.length) squares[(int)(i*i)] = true;
		}
	}

	boolean[] primes;
	long[] ps;
	void sieve(){
		primes = new boolean[10000001];
		fill(primes, true);
		primes[0] = false;
		primes[1] = false;
		for(int i = 2; i*i < primes.length; i++){
			if(!primes[i])continue;
			for(int j = i*i; j < primes.length; j+=i){
				primes[j] = false;
			}
		}
		int cnt = 0;
		for(int i = 0; i < primes.length; i++) if(primes[i]) cnt++;
		ps = new long[cnt];
		cnt = 0;
		for(int i = 0; i < primes.length; i++) if(primes[i]) ps[cnt++] = i;

	}


}

class Frac implements Comparable{
	BigInteger u, d;
	public Frac(String up, String down){
		u = new BigInteger(up);
		d = new BigInteger(down);
		reduce();
	}
	public Frac(long up, long down){
		u = new BigInteger(Long.toString(up));
		d = new BigInteger(Long.toString(down));
		reduce();
	}
	public Frac(BigInteger up, BigInteger down){
		u = up;
		d = down;
		reduce();
	}
	Frac add(Frac f){
		BigInteger gcd = d.gcd(f.d);
		BigInteger adjMe = f.d.divide(gcd);
		BigInteger adjIt = d.divide(gcd);
		BigInteger under = adjMe.multiply(d);
		return new Frac(adjMe.multiply(u).add(adjIt.multiply(f.u)), under);
	}
	Frac sub(Frac f){
		BigInteger gcd = d.gcd(f.d);
		BigInteger adjMe = f.d.divide(gcd);
		BigInteger adjIt = d.divide(gcd);
		BigInteger under = adjMe.multiply(d);
		return new Frac(adjMe.multiply(u).subtract(adjIt.multiply(f.u)), under);
	}
	Frac mul(Frac f){
		return new Frac(u.multiply(f.u), d.multiply(f.d));
	}
	Frac div(Frac f){
		return new Frac(u.multiply(f.d), d.multiply(f.u));
	}
	public int compareTo(Object o){
		Frac f = (Frac)o;
		return u.multiply(f.d).compareTo(f.u.multiply(d));
	}
	public String toString(){
		return u+"/"+d;
	}
	public Frac abs(){
		return new Frac(u.abs(),d.abs());
	}
	private void reduce(){
		BigInteger gcd = u.gcd(d);
		if(!gcd.equals(BigInteger.ONE)){
			u = u.divide(gcd);
			d = d.divide(gcd);
		}
	}
	public boolean equals(Object o){
		Frac f = (Frac)o;
		return f.u.equals(u) && f.d.equals(d);
	}

	public int hashCode(){
		return 17*u.hashCode()+d.hashCode();
	}
}
