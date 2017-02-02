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
				dnum1 = Double.valueOf(line).doubleValue();

				line = input.readLine();
				num2 = Integer.valueOf(line).intValue();

				line = input.readLine();
				StringTokenizer st = new StringTokenizer(line," ");

				Vector v1=new Vector();
				for (int j=0; j<num2; j++){
					double dnum2=Double.valueOf(st.nextToken()).doubleValue();
					v1.addElement(dnum2);
				}

				oneCase(i,dnum1,v1);

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


  public void oneCase (int c,double credit,Vector vv){
	String re="";

//*** output
	System.out.println("-------------");
	System.out.println("case="+c+" credit="+credit+" vv="+vv);
//***


	for (int i=0;i<vv.size()-1;i++){
		for (int j=i+1;j<vv.size();j++){
			double tmpD1=((Double)vv.elementAt(i)).doubleValue();
			double tmpD2=((Double)vv.elementAt(j)).doubleValue();
			if((tmpD1+tmpD2)==credit){
				re=(i+1)+" "+(j+1);
				i=vv.size();
				j=vv.size();
			}
		}
	}



//** For any number of item
/*
	for (int i=0;i<vv.size();i++){
		double tmpD=((Double)vv.elementAt(i)).doubleValue();
		if (tmpD==credit){
			re=" "i+"";
		}
	}

	if (re.equals("")){
		re=getCase(0,credit,vv,"",1);
	}
*/
//**



//*** output
	System.out.println("re="+re);
//***
	result+="Case #"+c+": "+re+System.getProperty("line.separator");
  }


	public String getCase(double sum, double credit,Vector vv,String reSt,int pos){
		//System.out.println("getCase:sum="+sum+" credit="+credit+" vv="+vv+" reSt="+reSt+" pos="+pos);


		if (vv.size()==0){
			return "";
		}
		if (sum==credit){
			return reSt;
		}

		String re="";

		double tmpD=((Double)vv.elementAt(0)).doubleValue();
		if ((sum+tmpD)==credit){
			return reSt+" "+pos;
		} else if ((sum+tmpD)>credit){
			Vector vv2=(Vector)vv.clone();
			vv2.removeElementAt(0);
			String aa=getCase(sum,credit,vv2,reSt,pos+1);
			return aa;
		} else {
			Vector vv2=(Vector)vv.clone();
			for (int i=0;i<vv.size() && vv2.size()>1;i++){
				tmpD=((Double)vv2.elementAt(0)).doubleValue();

				//System.out.println("tmpD="+tmpD);

				vv2.removeElementAt(0);
				String aa=getCase(sum+tmpD,credit,vv2,reSt+" "+(pos+i),pos+1+i);
				if (!aa.equals("")){
					return aa;
				}
			}
		}
		return re;
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
    File testFile = new File("D:\\java\\readwritetext\\Store Credit\\source.txt");
    File testFile2 = new File("D:\\java\\readwritetext\\Store Credit\\result.txt");
	Case allcases = new Case(testFile);
    setContents(testFile2, allcases.getResult());
  }
}


//set path=C:\Program Files\Java\jdk1.6.0_21\bin;%PATH%
//http://code.google.com/codejam/contest/dashboard?c=351101#s=p0

















