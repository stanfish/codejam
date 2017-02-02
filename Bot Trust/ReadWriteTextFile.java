import java.io.*;
import java.util.*;
import java.awt.Point;

class Case{
	int num1,num2,num3;
	double dnum1;
	String result="";
	public Case(File aFile) {

		try{
			StringBuilder contents = new StringBuilder();
		  	BufferedReader input =  new BufferedReader(new FileReader(aFile));
			String line = input.readLine();
			num1 = Integer.valueOf(line).intValue();
			int i=1;
			while (i<=num1 && ( line = input.readLine()) != null){
				String st=line;
				oneCase(i,st);

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


  public void oneCase (int c,String str){
	String re="";

//*** output
	System.out.println("-------------");
	System.out.println("case="+c+" str="+str);
//***

	Vector allStepsV=new Vector();
	Vector OStepsV=new Vector();
	Vector BStepsV=new Vector();

	StringTokenizer st = new StringTokenizer(str," ");
	int numStep=Double.valueOf(st.nextToken()).intValue();
	for (int i=0;i<numStep;i++){
		RobotStep tmpStep = new RobotStep(st.nextToken(),Double.valueOf(st.nextToken()).intValue());
		allStepsV.addElement(tmpStep);
		if (tmpStep.isO()){
			OStepsV.addElement(tmpStep);
		} else {
			BStepsV.addElement(tmpStep);
		}
	}

	int times=0;
	int OCurrent=1;
	int BCurrent=1;
	int Oi=0;
	int Bi=0;
	int Ai=0;
	while (Ai<allStepsV.size()){
		int ODiff=999;
		int BDiff=999;
		if (Oi<OStepsV.size()){
			ODiff=((RobotStep)(OStepsV.elementAt(Oi))).getNum()-OCurrent;
		}
		if (Bi<BStepsV.size()){
			BDiff=((RobotStep)(BStepsV.elementAt(Bi))).getNum()-BCurrent;
		}

		if (((RobotStep)(allStepsV.elementAt(Ai))).isO() && ODiff==0){
			Ai++;
			Oi++;
			if (BDiff!=0){
				BCurrent+=BDiff/Math.abs(BDiff);
			}

			//System.out.println((times+1)+" O:push button "+OCurrent+" B:move to "+BCurrent);

		} else if (!((RobotStep)(allStepsV.elementAt(Ai))).isO() && BDiff==0){
			Ai++;
			Bi++;
			if (ODiff!=0){
				OCurrent+=ODiff/Math.abs(ODiff);
			}

			//System.out.println((times+1)+" O:move to "+OCurrent+" B:push button "+BCurrent);

		} else {
			if (BDiff!=0){
				BCurrent+=BDiff/Math.abs(BDiff);
			}
			if (ODiff!=0){
				OCurrent+=ODiff/Math.abs(ODiff);
			}

			//System.out.println((times+1)+" O:move to "+OCurrent+" B:move to "+BCurrent);
		}
		times++;
	}

	re=times+"";

	//System.out.println("allStepsV="+allStepsV);



//*** output
	System.out.println("re="+re);
//***
	result+="Case #"+c+": "+re+System.getProperty("line.separator");
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


class RobotStep {

	String robotType="";
	int buttonNum=0;

	public RobotStep(String type, int butNum) {
		robotType=type;
		buttonNum=butNum;
	}

	public String toString(){
		return robotType+buttonNum;
	}

	public boolean isO(){
		return (robotType.equals("O"));
	}

	public int getNum(){
		return buttonNum;
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
    File testFile = new File("D:\\java\\readwritetext\\Bot Trust\\source.txt");
    File testFile2 = new File("D:\\java\\readwritetext\\Bot Trust\\result.txt");
	Case allcases = new Case(testFile);
    setContents(testFile2, allcases.getResult());
  }
}


//set path=C:\Program Files\Java\jdk1.6.0_21\bin;%PATH%

















