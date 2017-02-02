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
				String st1=line;
				oneCase(i,st1);
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
	String prevSt="";
	for (int i=0;i<str.length();i++){
		String newSt=getString(str.charAt(i));
		if (prevSt.length()>0 && newSt.substring(0,1).equals(prevSt.substring(0,1))){
			re+=" "+newSt;
		} else {
			re+=newSt;
		}
		prevSt=newSt;

	}





//*** output
	System.out.println("re="+re);
//***
	result+="Case #"+c+": "+re+System.getProperty("line.separator");
  }


	public String getString(char ch){
		switch (ch){
			case 'a':	return "2";
			case 'b':	return "22";
			case 'c':	return "222";
			case 'd':	return "3";
			case 'e':	return "33";
			case 'f':	return "333";
			case 'g':	return "4";
			case 'h':	return "44";
			case 'i':	return "444";
			case 'j':	return "5";
			case 'k':	return "55";
			case 'l':	return "555";
			case 'm':	return "6";
			case 'n':	return "66";
			case 'o':	return "666";
			case 'p':	return "7";
			case 'q':	return "77";
			case 'r':	return "777";
			case 's':	return "7777";
			case 't':	return "8";
			case 'u':	return "88";
			case 'v':	return "888";
			case 'w':	return "9";
			case 'x':	return "99";
			case 'y':	return "999";
			case 'z':	return "9999";
			case ' ':	return "0";
			default:	return "1";
		}
	}













//** Library
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

//**


























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
    File testFile = new File("D:\\java\\readwritetext\\T9 Spelling\\source.txt");
    File testFile2 = new File("D:\\java\\readwritetext\\T9 Spelling\\result.txt");
	Case allcases = new Case(testFile);
    setContents(testFile2, allcases.getResult());
  }
}


//set path=C:\Program Files\Java\jdk1.6.0_21\bin;%PATH%
//http://code.google.com/codejam/contest/dashboard?c=351101#s=p2

















