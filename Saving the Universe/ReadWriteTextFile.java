import java.io.*;
import java.util.*;

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
    //System.out.println("Original file contents : " + getContents(testFile));


	//breakCase(testFile);
	Case allcases = new Case(testFile);



    setContents(testFile2, allcases.getResult());
    //System.out.println("New file contents : " + getContents(testFile2));
  }
}


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
				int engineNum = Integer.valueOf(line).intValue();
				Vector engineV = new Vector();
				Vector wordV = new Vector();

				for (int j=0; j<engineNum; j++){
					line = input.readLine();
					engineV.addElement(line);
				}

				line = input.readLine();
				int wordNum = Integer.valueOf(line).intValue();

				for (int j=0; j<wordNum; j++){
					line = input.readLine();
					wordV.addElement(line);
				}

			  	oneCase(i,engineV,wordV);
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


  public void oneCase (int c,Vector engineV,Vector wordV){
	String re="";
	int switchNum=0;

	//System.out.println("c="+c+" engineV="+engineV+" wordV="+wordV);


	for(int i=0;i<wordV.size();i++){
		int maxJump=0;
		String currentEngine="";

		for (int j=0; j<engineV.size();j++){
			int jumpNum=findJumpNum(i,wordV,(String)(engineV.elementAt(j)));

			//System.out.println("- testing:"+engineV.elementAt(j)+" jumpNum="+jumpNum);

			if (jumpNum>maxJump){
				maxJump=jumpNum;
				currentEngine=(String)(engineV.elementAt(j));
			}
		}



		i+=maxJump-1;
		if (i<wordV.size() && i-maxJump+1!=0){
			switchNum++;


			//System.out.println("i="+i+" maxJump="+maxJump+" currentEngine="+currentEngine+" wordV="+wordV.elementAt(i)+" switchNum="+switchNum);
		}


	}


	re=switchNum+"";

	//System.out.println("re="+re);

	result+="Case #"+c+": "+re+System.getProperty("line.separator");
  }

	public int findJumpNum(int j,Vector v, String s){
		for (int i=j;i<v.size();i++){
			if (s.equals((String)(v.elementAt(i)))){
				return (i-j);
			}
		}
		return (v.size()-j);

	}



}
