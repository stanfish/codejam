import java.io.*;
import java.util.*;
import java.awt.Point;

class Case{
	long num1,R,K,N;
	String result="";
	public Case(File aFile) {

		try{
			StringBuilder contents = new StringBuilder();
		  	BufferedReader input =  new BufferedReader(new FileReader(aFile));
			String line = input.readLine();


			num1 = Integer.valueOf(line).intValue();


			int i=1;

			while (i<=num1 && ( line = input.readLine()) != null){

				Vector v1=new Vector();

				StringTokenizer st = new StringTokenizer(line," ");

				R=Long.valueOf(st.nextToken());
				K=Long.valueOf(st.nextToken());
				N=Long.valueOf(st.nextToken());


				String line2 = input.readLine();
				StringTokenizer st2 = new StringTokenizer(line2," ");
				long j=1;
				while (j<=N){
					v1.addElement(st2.nextToken());
					j++;
				}

				oneCase(i,R,K,N,v1);
			  	i++;


			}

			//System.out.println(result);

		}
		catch (IOException ex){
		  ex.printStackTrace();
		}
	}

  public String getResult (){

	return result;

  }


  public void oneCase (int c,long R,long K, long N,Vector vv){

	//System.out.println(c+" vv="+vv+" vv.size="+vv.size()+" R="+R+" K="+K);
	String re="";

	int currentPos=0;
	long total=0;
	long sumVV=0;
	for (int i=0; i<vv.size(); i++){
		sumVV+=(new Long(""+vv.elementAt(i))).longValue();
	}


	System.out.println("c="+c);
	System.out.println(K);
	System.out.println(sumVV);
	if (K>=sumVV){
		total=sumVV*R;
		System.out.println(c+" speed up");
	}
	else {
		for (long i=0; i<R; i++){

			long j=0;
			long currentTotal=0;
			boolean finish=false;
			while (!finish && currentTotal<K && j<vv.size()){
				long tryNum=(new Long(""+vv.elementAt(currentPos))).longValue() ;

				if (currentTotal+tryNum>K){
					finish=true;
				}
				else {
					currentTotal+=tryNum;
					if (currentPos==vv.size()-1){
						currentPos=0;
					}
					else {
						currentPos++;
					}
				}
				j++;
			}
			total+=currentTotal;
		}
	}

	result+="Case #"+c+": "+total+System.getProperty("line.separator");
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
    File testFile = new File("D:\\java\\readwritetext\\Theme Park\\source.txt");
    File testFile2 = new File("D:\\java\\readwritetext\\Theme Park\\result.txt");
	Case allcases = new Case(testFile);
    setContents(testFile2, allcases.getResult());
  }
}


//D:\java\readwritetext\Theme Park>set path=C:\Program Files\Java\jdk1.6.0_21\bin;%PATH%


















