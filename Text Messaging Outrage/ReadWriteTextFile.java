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

				StringTokenizer st = new StringTokenizer(line," ");
				int i1 = Integer.valueOf(st.nextToken()).intValue();
				int i2 = Integer.valueOf(st.nextToken()).intValue();
				int i3 = Integer.valueOf(st.nextToken()).intValue();

				line = input.readLine();
				st = new StringTokenizer(line," ");

				Vector vv=new Vector();
				for (int j=0;j<i3;j++)
					vv.addElement(Integer.valueOf(st.nextToken()));

			  	oneCase(i,i1,i2,i3,vv);
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



  public void oneCase (int c, int P, int K, int L, Vector nn){
	String re="";
	sorting(nn);
	System.out.println("oneCase c="+c+" P="+P+" k="+K+" L="+L+" nn="+nn);
	long hitnum=0;
	int ii=1;
	int jj=0;
	while (jj<nn.size()){
		for (int j=1;j<=K;j++){
			if (jj<nn.size()){
				hitnum+=((Integer)(nn.elementAt(jj))).intValue()*ii;
			}
			jj++;
		}
		ii++;
	}

	re=hitnum+"";

	//System.out.println("re="+re);

	result+="Case #"+c+": "+re+System.getProperty("line.separator");
  }


 public void sorting(Vector v){
	  int numSorted=0;
	  int index;
	  while(numSorted<v.size()){
		  Integer temp=(Integer)(v.elementAt(numSorted));
		  int i=numSorted;
		  for(i=numSorted;i>0;i--){
			  if (temp.compareTo((Integer)(v.elementAt(i-1)))>0){
				  v.setElementAt(v.elementAt(i-1),i);
			  }
			  else {
				  break;
			  }

		  }
		  v.setElementAt(temp,i);
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
    File testFile = new File("C:\\java\\readwritetext\\source.txt");
    File testFile2 = new File("C:\\java\\readwritetext\\result.txt");
	Case allcases = new Case(testFile);
    setContents(testFile2, allcases.getResult());
  }
}
