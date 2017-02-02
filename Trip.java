

class Trip implements Comparable{

	Time d,a;
	String from;

	public Trip(Time dd, Time aa, String ff) {
		d=dd;
		a=aa;
		from=ff;
	}

	public int compareTo(Trip t){
		if (d.earlierThan(t.getTimeD()))
			return -1;
		if (d.equals(t.getTimeD()))
			return 0;
		return 1;
	}

	public Time getTimeD(){
		return d;
	}

	public Time getTimeA(){
		return a;
	}

	public String getFrom(){
		return from;
	}

	public String toString(){
		return d.toString()+" "+a.toString()+" from "+from;
	}
}