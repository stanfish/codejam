import java.io.*;
import java.util.*;

public class ReadWriteTextFile {

  static public String getContents(File aFile) {
    StringBuilder contents = new StringBuilder();

    try {
      BufferedReader input =  new BufferedReader(new FileReader(aFile));
      try {
        String line = null;
        while (( line = input.readLine()) != null){
          contents.append(line);
          contents.append(System.getProperty("line.separator"));
        }
      }
      finally {
        input.close();
      }
    }
    catch (IOException ex){
      ex.printStackTrace();
    }

    return contents.toString();
  }

  static public void setContents(File aFile, String aContents)
                                 throws FileNotFoundException, IOException {
    if (aFile == null) {
      throw new IllegalArgumentException("File should not be null.");
    }
    if (!aFile.exists()) {
      throw new FileNotFoundException ("File does not exist: " + aFile);
    }
    if (!aFile.isFile()) {
      throw new IllegalArgumentException("Should not be a directory: " + aFile);
    }
    if (!aFile.canWrite()) {
      throw new IllegalArgumentException("File cannot be written: " + aFile);
    }

    Writer output = new BufferedWriter(new FileWriter(aFile));
    try {
      output.write( aContents );
    }
    finally {
      output.close();
    }
  }

  public static void main (String... aArguments) throws IOException {
    File testFile = new File("C:\\java\\readwritetext\\source.txt");
    File testFile2 = new File("C:\\java\\readwritetext\\result.txt");
    //System.out.println("Original file contents : " + getContents(testFile));


	//breakCase(testFile);
	Case allcases = new Case(testFile);



    setContents(testFile2, allcases.getResult());
    //System.out.println("New file contents : " + getContents(testFile2));
  }
}


class Case{

	int num;
	String result="";

	public Case(File aFile) {

		try{
			StringBuilder contents = new StringBuilder();
		  	BufferedReader input =  new BufferedReader(new FileReader(aFile));
			String line = input.readLine();
			num = Integer.valueOf(line).intValue();


			int i=1;
			while (i<=num && ( line = input.readLine()) != null){
				int turnAround = Integer.valueOf(line).intValue();
				line = input.readLine();
				StringTokenizer st = new StringTokenizer(line," ");
				int NA = Integer.valueOf(st.nextToken()).intValue();
				int NB = Integer.valueOf(st.nextToken()).intValue();
				Vector VA = new Vector();
				Vector VB = new Vector();
				for (int j=0; j<NA; j++){
					line = input.readLine();
					VA.addElement(line);
				}
				for (int j=0; j<NB; j++){
					line = input.readLine();
					VB.addElement(line);
				}
			  	oneCase(i,VA,VB,NA,NB,turnAround);
			  	i++;
			}



		}
		catch (IOException ex){
		  ex.printStackTrace();
		}
	}

  public String getResult (){

	return result;

  }


  public void oneCase (int c,Vector VA,Vector VB,int NA,int NB,int turnAround){
	String re="";

	System.out.println("c="+c+" VA="+VA+" VB="+VB+" NA="+NA+" NB="+NB+" turnAround="+turnAround);
	Vector tripV = new Vector();

	for (int i=0; i<VA.size(); i++){
		String tmpS = (String)(VA.elementAt(i));
		StringTokenizer st = new StringTokenizer(tmpS," ");
		StringTokenizer stt = new StringTokenizer(st.nextToken(),":");
		Time timeD = new Time(Integer.valueOf(stt.nextToken()).intValue(),Integer.valueOf(stt.nextToken()).intValue());

		stt = new StringTokenizer(st.nextToken(),":");
		Time timeA = new Time(Integer.valueOf(stt.nextToken()).intValue(),Integer.valueOf(stt.nextToken()).intValue());
		timeA.addMin(turnAround);

		Trip t = new Trip(timeD,timeA,"A");
		tripV.addElement(t);
	}
	for (int i=0; i<VB.size(); i++){
		String tmpS = (String)(VB.elementAt(i));
		StringTokenizer st = new StringTokenizer(tmpS," ");
		StringTokenizer stt = new StringTokenizer(st.nextToken(),":");
		Time timeD = new Time(Integer.valueOf(stt.nextToken()).intValue(),Integer.valueOf(stt.nextToken()).intValue());

		stt = new StringTokenizer(st.nextToken(),":");
		Time timeA = new Time(Integer.valueOf(stt.nextToken()).intValue(),Integer.valueOf(stt.nextToken()).intValue());
		timeA.addMin(turnAround);

		Trip t = new Trip(timeD,timeA,"B");
		tripV.addElement(t);
	}

	sorting(tripV);

	int aa=0;
	int bb=0;
	Vector AV = new Vector();
	Vector BV = new Vector();
	for (int i=0; i<tripV.size();i++){
		Trip tmp = (Trip)(tripV.elementAt(i));
		if (tmp.getFrom().equals("A")){
			if (findEarlier(tmp.getTimeD(),AV)){}
			else
				aa++;

			BV.addElement(tmp.getTimeA());

		}
		else {
			if (findEarlier(tmp.getTimeD(),BV)){}
			else
				bb++;

			AV.addElement(tmp.getTimeA());
		}
		System.out.println("tmp="+tmp+" aa="+aa+" bb="+bb+" AV="+AV+" BV="+BV);
	}

	System.out.println("aa="+aa+" bb="+bb);

	re=aa+" "+bb;


	result+="Case #"+c+": "+re+System.getProperty("line.separator");
  }


  public boolean findEarlier(Time t, Vector v){
	  for (int i=0;i<v.size(); i++){
		  if (!t.earlierThan((Time)(v.elementAt(i)))){
			  v.removeElementAt(i);
			  return true;
		  }
	  }
		return false;

  }


  public void swap(int i,int j, Vector v){
	  Trip temp = (Trip)(v.elementAt(i));
	  v.setElementAt((Trip)(v.elementAt(j)),i);
	  v.setElementAt(temp,j);
  }

  public void sorting(Vector v){
	  int numSorted=0;
	  int index;
	  while(numSorted<v.size()){
		  Trip temp=(Trip)(v.elementAt(numSorted));
		  int i=numSorted;
		  for(i=numSorted;i>0;i--){
			  if (temp.earlierThan((Trip)(v.elementAt(i-1)))){
				  v.setElementAt(v.elementAt(i-1),i);
			  }
			  else {
				  break;
			  }

		  }
		  v.setElementAt(temp,i);
		  numSorted++;
	  }
  }

//Rounding a double value to how many decimal place
  public double rounding(double v,int d){
	  return (((long)(v*Math.pow(10,d)+0.5))/Math.pow(10,d));
  }


}



class Trip{

	Time d,a;
	String from;

	public Trip(Time dd, Time aa, String ff) {
		d=dd;
		a=aa;
		from=ff;
	}

	public boolean earlierThan(Trip t){
		if (d.earlierThan(t.getTimeD()))
			return true;
		return false;
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

class Time{

	int min,hr;


	public Time(int h, int m) {
		hr=h;
		min=m;
	}

	public void addMin(int m){
		min+=m;
		while (min>=60){
			min-=60;
			hr++;
		}
	}

	public String toString(){
		return hr+":"+min;
	}

	public int getMin(){return min;}
	public int getHr(){return hr;}

	public boolean earlierThan(Time t){
		if (hr<t.getHr())
			return true;
		else if (hr>t.getHr())
			return false;
		else {
			if (min<t.getMin())
				return true;
		}
		return false;
	}

	public boolean equals(Time t){
		if (hr==t.getHr() && min==t.getMin())
			return true;
		return false;
	}

}


