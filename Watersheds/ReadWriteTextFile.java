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
			Vector v1=new Vector();
			while (i<=num1 && ( line = input.readLine()) != null){
				StringTokenizer st = new StringTokenizer(line," ");
				num2 = Integer.valueOf(st.nextToken()).intValue();
				num3 = Integer.valueOf(st.nextToken()).intValue();
				Vector vv2=new Vector();
				for (int j=0; j<num2; j++){
					line = input.readLine();
					StringTokenizer st2 = new StringTokenizer(line," ");
					Vector vv=new Vector();
					int k=0;
					while (st2.hasMoreTokens()){
						Cell tmpC = new Cell(Integer.valueOf(st2.nextToken()).intValue());
						if (k>0){
							tmpC.setW((Cell)(vv.elementAt(k-1)));
						}
						if (j>0){
							Vector tmpV=(Vector)(vv2.elementAt(j-1));
							tmpC.setN((Cell)(tmpV.elementAt(k)));
						}
						vv.addElement(tmpC);
						k++;
					}
					vv2.addElement(vv);
				}

				oneCase(i,vv2);

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


  public void oneCase (int c,Vector vv){
	String re="";
	String[] sinkCh = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","1"};
	int sinkNum=0;

	for (int i=0; i<vv.size(); i++){
		Vector tmpV=(Vector)(vv.elementAt(i));
		for (int j=0; j<tmpV.size(); j++){
			Cell tmpC=(Cell)(tmpV.elementAt(j));
			tmpC.setSink();
		}
	}

	for (int i=0; i<vv.size(); i++){
		Vector tmpV=(Vector)(vv.elementAt(i));
		for (int j=0; j<tmpV.size(); j++){
			Cell tmpC=(Cell)(tmpV.elementAt(j));
			String cc=sinkCh[sinkNum];
			String ccc=tmpC.getSink(cc);
			if (ccc.equals(cc)){
				sinkNum++;
			}
			re+=ccc;
			if (j<tmpV.size()-1){
				re+=" ";
			}
		}
		re+=System.getProperty("line.separator");
	}

	//System.out.println("c="+c+" vv="+vv);

	result+="Case #"+c+":"+System.getProperty("line.separator")+re;
  }


}

class Cell {
	int n;
	Cell east=null;
	Cell north=null;
	Cell west=null;
	Cell south=null;
	boolean sink=false;
	String sinkC="";


	public Cell(int num) {
		n=num;
	}
	public void setE(Cell e){
		east=e;
	}

	public void setN(Cell e){
		north=e;
		e.setS(this);
	}

	public void setS(Cell e){
		south=e;
	}

	public void setW(Cell e){
		west=e;
		e.setE(this);
	}

	public String toString(){
		//return n+(west!=null?" w="+west.getVal()+" ":" w=null ")+(north!=null?" n="+north.getVal()+" ":" n=null ")+(east!=null?" e="+east.getVal()+" ":" e=null ")+(south!=null?" s="+south.getVal()+" ":" s=null ")+" sink="+sink;
		return sinkC;
	}

	public int getVal(){
		return n;
	}

	public boolean isSink(){
		return sink;
	}

	public String getSinkC(){
		return sinkC;
	}

	public void setSinkC(String c){
		sinkC=c;
	}

	public void setSink(){
		sink=true;
		if (sink && east!=null && n>east.getVal()){
			sink=false;
		}

		if (sink && west!=null && n>west.getVal()){
			sink=false;
		}

		if (sink && north!=null && n>north.getVal()){
			sink=false;
		}

		if (sink && south!=null && n>south.getVal()){
			sink=false;
		}
	}

	public String getSink(String c){
		if (sink){
			if (sinkC.equals("")){
				sinkC=c;
			}
			return sinkC;
		}

		if (!sinkC.equals("")){
			return sinkC;
		}
		int smallestN=n;
		Cell smallestC=null;

		if (north!=null && north.getVal()<smallestN){
			smallestN=north.getVal();
			smallestC=north;
		}

		if (west!=null && west.getVal()<smallestN){
			smallestN=west.getVal();
			smallestC=west;
		}

		if (east!=null && east.getVal()<smallestN){
			smallestN=east.getVal();
			smallestC=east;
		}
		if (south!=null && south.getVal()<smallestN){
			smallestN=south.getVal();
			smallestC=south;
		}

		sinkC=smallestC.getSink(c);
		return sinkC;
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
    File testFile = new File("D:\\java\\readwritetext\\Watersheds\\source.txt");
    File testFile2 = new File("D:\\java\\readwritetext\\Watersheds\\result.txt");
	Case allcases = new Case(testFile);
    setContents(testFile2, allcases.getResult());
  }
}


//D:\java\readwritetext\Theme Park>set path=C:\Program Files\Java\jdk1.6.0_21\bin;%PATH%


















