import java.io.*;
import java.util.*;
import java.awt.Point;

class Case{
	int num1,num2,num3;
	String result="";
	public Case(File aFile) {

		try{
			StringBuilder contents = new StringBuilder();
		  	BufferedReader input =  new BufferedReader(new FileReader(aFile));
			String line = input.readLine();
			num1 = Integer.valueOf(line).intValue();
			int i=1;
			while (i<=num1 && ( line = input.readLine()) != null){
				num2 = Integer.valueOf(line).intValue();
				Vector v1=new Vector();
				for (int j=0; j<num2; j++){
					line = input.readLine();
					double d1=Double.valueOf(line).doubleValue();
					v1.addElement(d1);
				}

				oneCase(i,v1);

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


  public void oneCase (int c,Vector vv){
	String re="";

//*** output
	System.out.println(" ");
	System.out.println("case="+c);
	for (int i=0; i<vv.size(); i++){
		System.out.println(i+": "+vv.elementAt(i));
	}
//***

	sortingDouble(vv);
	System.out.println(vv);

	Vector resultV = new Vector();
	Vector inV = new Vector();
	Interval in = new Interval(0,1);
	inV.addElement(in);

	while(vv.size()>0){
		inV=processStep(inV,vv,resultV);
	}


	System.out.println("result="+resultV);

	for (int i=0; i<resultV.size(); i++){
		re+=resultV.elementAt(i)+System.getProperty("line.separator");
	}


	result+="Case #"+c+":"+System.getProperty("line.separator")+re;
  }


	public Vector processStep(Vector inV, Vector vv, Vector resultV){
		Vector newInV=new Vector();

		//System.out.println("before steps inV="+inV+" newInV="+newInV+" vv="+vv+" resultV="+resultV);


		for (int i=0; i<inV.size(); i++){	//each interval
			Vector breakDownInV = ((Interval)inV.elementAt(i)).processMe(vv,resultV);
			newInV=addingVector(newInV,breakDownInV);
		}

		//System.out.println("after steps inV="+inV+" newInV="+newInV+" vv="+vv+" resultV="+resultV);

		return newInV;
	}


//Adding elements in Vector b to the end of Vector a
	public Vector addingVector(Vector a, Vector b){
		for (int i=0; i<b.size(); i++){
			a.addElement(b.elementAt(i));
		}
		return a;
	}

	public void sortingDouble(Vector v){
	  int numSorted=0;
	  int index;
	  while(numSorted<v.size()){
		  double temp1=((Double)v.elementAt(numSorted)).doubleValue();
		  int i=numSorted;
		  for(i=numSorted;i>0;i--){
			  double temp2=((Double)v.elementAt(i-1)).doubleValue();
			  if (temp1<temp2){
				  v.setElementAt(v.elementAt(i-1),i);
			  }
			  else {
				  break;
			  }
		  }
		  v.setElementAt(temp1,i);
		  numSorted++;
	  }
	}







}






class Interval{
	public double startNum,endNum;
	public Interval(double s,double e){
		startNum=s;
		endNum=e;
	}
	public Interval getMiddleInterval(){
		double tmp=(endNum-startNum)/3;
		return new Interval(startNum+tmp,startNum+tmp*2);
	}
	public Vector getBreakIntervals(Vector inV){
		double tmp=(endNum-startNum)/3;
		Vector v=new Vector();
		Interval in1=new Interval(startNum,startNum+tmp);
		for (int i=0;i<inV.size();i++){
			double tmpNum=((Double)inV.elementAt(i)).doubleValue();
			if (in1.isIn(tmpNum)){
				v.addElement(in1);
				i=inV.size();
			}
		}

		Interval in2=new Interval(startNum+2*tmp,endNum);
		for (int i=0;i<inV.size();i++){
			double tmpNum=((Double)inV.elementAt(i)).doubleValue();
			if (in2.isIn(tmpNum)){
				v.addElement(in2);
				i=inV.size();
			}
		}

		return v;
	}
	public Vector processMe(Vector inV, Vector reV){
		Interval middleIn = getMiddleInterval();
		middleIn.eliminatNum(inV,reV);
		return getBreakIntervals(inV);
	}
	public boolean isIn(double num){
		return (num>=startNum && num<=endNum);
	}
	public String toString(){
		return "["+startNum+","+endNum+"]";
	}
	public void eliminatNum(Vector inV,Vector reV){

		//System.out.println("eliminatNum "+toString()+" inV="+inV+" reV="+reV);

		for (int i=0; i<inV.size(); i++){
			double tmpNum=((Double)inV.elementAt(i)).doubleValue();
			if (isIn(tmpNum)){

				//System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhh");

				reV.addElement(inV.elementAt(i));
				inV.removeElementAt(i);
				i--;
			}
		}
	}
}















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
    File testFile = new File("D:\\java\\readwritetext\\Vanishing Numbers (unsolved)\\source.txt");
    File testFile2 = new File("D:\\java\\readwritetext\\Vanishing Numbers (unsolved)\\result.txt");
	Case allcases = new Case(testFile);
    setContents(testFile2, allcases.getResult());
  }
}


//set path=C:\Program Files\Java\jdk1.6.0_21\bin;%PATH%
//http://code.google.com/codejam/contest/dashboard?c=842485#

















