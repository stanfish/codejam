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

			StringTokenizer st = new StringTokenizer(line," ");

			num1 = Integer.valueOf(st.nextToken()).intValue();
			num2 = Integer.valueOf(st.nextToken()).intValue();
			num3 = Integer.valueOf(st.nextToken()).intValue();


			int i=1;
			Vector v1=new Vector();
			while (i<=num2 && ( line = input.readLine()) != null){
				v1.addElement(line);
			  	i++;
			}

			i=1;
			while (i<=num3 && ( line = input.readLine()) != null){
				oneCase(i,line,v1,num1);
				//System.out.println("i="+i+" line="+line+" v1="+v1);
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


  public void oneCase (int c,String str,Vector vv,int num){
	int re=0;
	int j=0;
	Vector cVV=new Vector();
	for (int i=0;i<num;i++){
		String[] tmpSt=getLetter(str);
		str=tmpSt[1];
		String lett=tmpSt[0];
		cVV.addElement(lett);
	}

	//System.out.println("cVV="+cVV+" cVV.size="+cVV.size()+" vv="+vv);

	re=countMatch(vv,cVV);

	result+="Case #"+c+": "+re+System.getProperty("line.separator");
  }

	public int countMatch(Vector vv, Vector cvv){
		int match=0;
		for (int i=0;i<vv.size(); i++){
			String tmpS=(String)(vv.elementAt(i));
			if (isMatch(tmpS,cvv)){
				match++;
			}
		}
		return match;
	}

	public boolean isMatch(String ss, Vector vv){
		boolean stillMatch=true;
		for (int i=0;i<vv.size() && stillMatch;i++){
			String s1=(String)(vv.elementAt(i));
			if (s1.indexOf(ss.substring(i,i+1))==-1){
				stillMatch=false;
			}
		}
		return stillMatch;
	}

	public String[] getLetter(String st){
		String[] reSt = {"hello","world"};
		if (!st.substring(0,1).equals("(")){
			reSt[0]=st.substring(0,1);
			reSt[1]=st.substring(1);
		}
		else {
			reSt[0]=st.substring(1,st.indexOf(")"));
			reSt[1]=st.substring(st.indexOf(")")+1);
		}
		return reSt;
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
    File testFile = new File("C:\\java\\readwritetext\\Alien Language\\source.txt");
    File testFile2 = new File("C:\\java\\readwritetext\\Alien Language\\result.txt");
	Case allcases = new Case(testFile);
    setContents(testFile2, allcases.getResult());
  }
}
