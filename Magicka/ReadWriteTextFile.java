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

	StringTokenizer st = new StringTokenizer(str," ");
	int numStep=Double.valueOf(st.nextToken()).intValue();
	Vector invokeV=new Vector();
	for (int i=0;i<numStep;i++){
		invokeV.addElement(new InvokeObj(st.nextToken()));
	}

	numStep=Double.valueOf(st.nextToken()).intValue();
	Vector opposeV=new Vector();
	for (int i=0;i<numStep;i++){
		opposeV.addElement(new OpposeObj(st.nextToken()));
	}

	numStep=Double.valueOf(st.nextToken()).intValue();
	String listStr = st.nextToken();
	for (int i=1;i<listStr.length();i++){
		String newSt=invokeE(invokeV,listStr.substring(i,i+1),listStr.substring(i-1,i));
		if (newSt.length()==1){
			String beforeSt="";
			if (i-2>=0){
				beforeSt=listStr.substring(0,i-1);
			}
			String afterSt="";
			if (i+1<listStr.length()){
				afterSt=listStr.substring(i+1);
			}
			listStr=beforeSt+newSt+afterSt;
			i=0;
		} else {
			for (int j=i;j>0;j--){
				if (canOppose(opposeV,listStr.substring(i,i+1),listStr.substring(j-1,j))){
					String beforeSt="";
					if (j-2>=0){
						beforeSt=listStr.substring(0,j-1);
					}
					String afterSt="";
					if (i+1<listStr.length()){
						afterSt=listStr.substring(i+1);
					}
					listStr=beforeSt+afterSt;
					i=0;
					j=0;
				}
			}
		}

	}

	re=toList(listStr);

	System.out.println("listStr="+listStr+" invokeV="+invokeV+" opposeV="+opposeV);

//*** output
	System.out.println("re="+re);
//***
	result+="Case #"+c+": "+re+System.getProperty("line.separator");
  }

	public String invokeE(Vector inV,String ch1,String ch2){
		for (int i=0;i<inV.size();i++){
			if (((InvokeObj)(inV.elementAt(i))).canInvoke(ch1,ch2)){
				return ((InvokeObj)(inV.elementAt(i))).toResult(ch1,ch2);
			}
		}
		return ch1+""+ch2;
	}

	public boolean canOppose(Vector inV,String ch1,String ch2){
		for (int i=0;i<inV.size();i++){
			if (((OpposeObj)(inV.elementAt(i))).canOppose(ch1,ch2)){
				return true;
			}
		}
		return false;
	}

	public String toList(String st){
		if (st.equals("")){
			return "[]";
		}
		String re="[";
		for (int i=0;i<st.length();i++){
			if (i>0){
				re+=", ";
			}
			re+=st.substring(i,i+1);
		}
		re+="]";
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





class InvokeObj {
	String char1="";
	String char2="";
	String charResult="";

	public InvokeObj(String str) {
		char1=str.substring(0,1);
		char2=str.substring(1,2);
		charResult=str.substring(2,3);
	}

	public String toString(){
		return char1+char2+charResult+"";
	}

	public boolean canInvoke(String st1, String st2){
		return ((st1.equals(char1) && st2.equals(char2)) || (st1.equals(char2) && st2.equals(char1)));
	}

	public String toResult(String st1, String st2){
		if (canInvoke(st1,st2)){
			return charResult;
		}
		return st1+st2+"";
	}
}

class OpposeObj {

	String char1="";
	String char2="";

	public OpposeObj(String str) {
		char1=str.substring(0,1);
		char2=str.substring(1,2);
	}

	public String toString(){
		return char1+char2+"";
	}

	public boolean canOppose(String st1, String st2){
		return ((st1.equals(char1) && st2.equals(char2)) || (st1.equals(char2) && st2.equals(char1)));
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
    File testFile = new File("D:\\java\\readwritetext\\Magicka\\source.txt");
    File testFile2 = new File("D:\\java\\readwritetext\\Magicka\\result.txt");
	Case allcases = new Case(testFile);
    setContents(testFile2, allcases.getResult());
  }
}


//set path=C:\Program Files\Java\jdk1.6.0_21\bin;%PATH%

















