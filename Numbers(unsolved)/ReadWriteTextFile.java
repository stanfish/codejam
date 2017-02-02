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
				int nn = Integer.valueOf(line).intValue();

			  	oneCase(i,nn);
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



  public void oneCase (int c,int nn){
	String re="";
	//System.out.println("oneCase c="+c+" iV="+iV+" sV="+sV+" gas="+gas);
	double numnum=3+Math.sqrt(5);
	double storenum=numnum;
	for (int i=1;i<nn;i++){

		System.out.println("i="+i+" storenum="+storenum);

		//storenum=multi(storenum,numnum);
		storenum=storenum*numnum;

		System.out.println("storenum="+storenum);
	}

	System.out.println("storenum="+storenum+" storenum111="+formatSt(multi(storenum,numnum)));



	re=formatSt(storenum)+"";

	//System.out.println("re="+re);

	result+="Case #"+c+": "+re+System.getProperty("line.separator");
  }

 public double multi(double n1, double n2){
		double tmp=n1*n2;
		String tmpst=tmp+"";
		if (tmpst.indexOf(".")!=-1 && tmpst.indexOf(".")-3>0){
			tmpst=tmpst.substring(tmpst.indexOf(".")-3);
		}
		tmp=(Double.valueOf(tmpst)).doubleValue();
		return tmp;
 }

 public String formatSt(double nn){
	 String tmp = ((int)nn)+"";
	 int addd=3-tmp.length();
	 for (int i=0;i<addd;i++){
		 tmp="0"+tmp;
	 }
	 return tmp;
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
