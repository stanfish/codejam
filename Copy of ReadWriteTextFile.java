import java.io.*;
import java.util.*;
import java.awt.Point;

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

				line = input.readLine();

			  	oneCase(i,line);
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



  public void oneCase (int c,String s){
	String re="";
	System.out.println("oneCase c="+c+" s="+s);

	String ss="0";
	for (int i; i<10; i++){
		ss=addS(ss);
		System.out.println("ss="+ss);
	}
/*
	String ss="";
	for(int i=0;i<s.length()-1;i++){
		sss
		for(int j=0;j<3;j++){
			System.out.println(j);
		}
	}
*/

	//System.out.println("re="+re);

	result+="Case #"+c+": "+re+System.getProperty("line.separator");
  }


public String addS(String s){
	for (int i=s.length()-1;i>=0;i--){
		int ii=Integer.valueOf(s.subString(i,i+1)).intValue();

	}

}

  public boolean isUgly(String s){
	int ii=Integer.valueOf(s).intValue();
	if (ii%2==0 || ii%3==0 || ii%5==0 || ii%7==0 || ii==0)
		return true;
	return false;
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
    File testFile = new File("C:\\java\\readwritetext\\source.txt");
    File testFile2 = new File("C:\\java\\readwritetext\\result.txt");
	Case allcases = new Case(testFile);
    setContents(testFile2, allcases.getResult());
  }
}
