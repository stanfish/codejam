import java.io.*;
import java.util.*;
import java.awt.Point;

class Case{
	int num1;
	String result="";
	public Case(File aFile) {

		try{
			StringBuilder contents = new StringBuilder();
		  	BufferedReader input =  new BufferedReader(new FileReader(aFile));
			String line = input.readLine();

			num1 = Integer.valueOf(line).intValue();
			int i=1;
			while (i<=num1 && ( line = input.readLine()) != null){
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


  public void oneCase (int c,String str){
	long re=0;

	long[] numOrder = {1,0,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42};
	int num=0;

	Hashtable numbers = new Hashtable();

	for (int j=0; j<str.length(); j++){
		String ss=str.substring(j,j+1);
		if (numbers.get(ss)==null){
			numbers.put(ss,numOrder[num]);
			num++;
		}
	}

	if (num<2){
		num=2;
	}
	System.out.println("numbers="+numbers+" num="+num);
	long ii=0;
	for (int j=str.length()-1; j>=0; j--){
		String ss=str.substring(j,j+1);
		long iii=((Long)numbers.get(ss)).longValue();
		long tmp=iii*(long)Math.pow(num,ii);
		re+=tmp;
		ii++;

		//System.out.println("iii="+iii+" tmp="+tmp);
	}





	result+="Case #"+c+": "+re+System.getProperty("line.separator");
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
    File testFile = new File("C:\\java\\readwritetext\\All Your Base (unsolved)\\source.txt");
    File testFile2 = new File("C:\\java\\readwritetext\\All Your Base (unsolved)\\result.txt");
	Case allcases = new Case(testFile);
    setContents(testFile2, allcases.getResult());
  }
}
