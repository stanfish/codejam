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
				int itemN = Integer.valueOf(line).intValue();
				Long[] nn1=new Long[itemN];
				Long[] nn2=new Long[itemN];

				line = input.readLine();
				StringTokenizer st = new StringTokenizer(line," ");
				int k=0;
				while (st.hasMoreTokens()){
					nn1[k] = Long.valueOf(st.nextToken());
					k++;
				}

				line = input.readLine();
				st = new StringTokenizer(line," ");
				k=0;
				while (st.hasMoreTokens()){
					nn2[k] = Long.valueOf(st.nextToken());
					k++;
				}

			  	oneCase(i,nn1,nn2,itemN);
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



  public void oneCase (int c,Long[] nn1,Long[] nn2,int iii){
	String re="";
	//System.out.println("oneCase c="+c+" iV="+iV+" sV="+sV+" gas="+gas);

	long minRe=0;
	Vector vv1=new Vector();
	Vector vv2=new Vector();

	for (int i=0;i<iii; i++){
		vv1.addElement(nn1[i]);
		vv2.addElement(nn2[i]);
	}

	sorting(vv1);
	sorting(vv2);

	//System.out.println("vv1="+vv1+" vv2="+vv2);
	for (int j=0;j<vv1.size(); j++){
			minRe+=	((Long)(vv1.elementAt(j))).longValue() * ((Long)(vv2.elementAt(vv2.size()-1-j))).longValue();

			//System.out.println("s="+((Long)(vv1.elementAt(j))).longValue()+" l="+((Long)(vv2.elementAt(vv2.size()-1-j))).longValue());

	}

	/*
	for (int i=0;i<iii; i++){
		long minVal=999999999;
		int jj=-1;
		int kk=-1;
		for (int j=0;j<vv1.size(); j++){
			for (int k=0;k<vv2.size(); k++){
				if (((Long)(vv1.elementAt(j))).longValue() * ((Long)(vv2.elementAt(k))).longValue() < minVal){

					if (minVal==0 && ((Long)(vv1.elementAt(j))).longValue() * ((Long)(vv2.elementAt(k))).longValue()==0){
						long numC1=0;
						long numC2=0;
						if (((Long)(vv1.elementAt(j))).longValue()==0)
							numC1=((Long)(vv2.elementAt(k))).longValue();
						else
							numC1=((Long)(vv1.elementAt(j))).longValue();

						if (((Long)(vv1.elementAt(jj))).longValue()==0)
							numC2=((Long)(vv2.elementAt(kk))).longValue();
						else
							numC2=((Long)(vv1.elementAt(jj))).longValue();

						if (numC1>numC2){
							minVal=((Long)(vv1.elementAt(j))).longValue() * ((Long)(vv2.elementAt(k))).longValue();
							jj=j;
							kk=k;
						}

					}
					else{
						minVal=((Long)(vv1.elementAt(j))).longValue() * ((Long)(vv2.elementAt(k))).longValue();
						jj=j;
						kk=k;
					}
				}
			}
		}
		minRe+=minVal;

		//System.out.println("minVal="+minVal+" jj="+jj+" kk="+kk+" v1="+vv1+" v2="+vv2);

		vv1.removeElementAt(jj);
		vv2.removeElementAt(kk);



	}
	*/


	//System.out.println("minRe="+minRe);

	re=minRe+"";

	//System.out.println("re="+re);

	result+="Case #"+c+": "+re+System.getProperty("line.separator");
  }

 public void sorting(Vector v){
	  int numSorted=0;
	  int index;
	  while(numSorted<v.size()){
		  Long temp=(Long)(v.elementAt(numSorted));
		  int i=numSorted;
		  for(i=numSorted;i>0;i--){
			  if (temp.compareTo((Long)(v.elementAt(i-1)))<0){
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
