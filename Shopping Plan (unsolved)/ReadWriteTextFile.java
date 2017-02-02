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
				int itemN = Integer.valueOf(st.nextToken()).intValue();
				int storeN = Integer.valueOf(st.nextToken()).intValue();
				double gasPrice = Double.valueOf(st.nextToken()).doubleValue();
				Vector itemV = new Vector();
				Vector storeV = new Vector();

				line = input.readLine();
				StringTokenizer stt = new StringTokenizer(line," ");
				for (int j=0; j<itemN; j++){
					itemV.addElement(stt.nextToken());
				}
				for (int j=0; j<storeN; j++){
					line = input.readLine();
					storeV.addElement(line);
				}
			  	oneCase(i,itemV,storeV,gasPrice);
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


	double minPrice=999999999;

  public void oneCase (int c,Vector iV,Vector sV,double gas){
	String re="";
	System.out.println("oneCase c="+c+" iV="+iV+" sV="+sV+" gas="+gas);

	minPrice=999999999;
	Vector storeV = new Vector();
	Vector itemV = new Vector();
	for (int i=0; i<iV.size();i++){
		ItemType iTmp = new ItemType((String)(iV.elementAt(i)));
		itemV.addElement(iTmp);
	}
	for (int i=0; i<sV.size();i++){
		Store sTmp = new Store((String)(sV.elementAt(i)));
		storeV.addElement(sTmp);
	}




	buyItem((Vector)(itemV.clone()),storeV,gas,0.0,0.0,0.0);


	re=rounding(minPrice,7)+"";



	//System.out.println("re="+re);

	result+="Case #"+c+": "+re+System.getProperty("line.separator");
  }

  public String rounding(double v,int d){
	  String re = ""+(((long)(v*Math.pow(10,d)+0.5))/Math.pow(10,d));
	  int addZ=0;
	  if (re.indexOf(".")==-1)
	  	addZ=7;
	  else {
		addZ=d-re.length()+re.indexOf(".")+1;
	  }

	  if (addZ==7)
	  	re+=".";
	  for (int i=0;i<addZ;i++){
		  re+="0";
	  }

	  return re;

  }

	public Vector getStoresContain(Vector v, ItemType it){
		Vector sVTmp=(Vector)(v.clone());
		for (int i=0; i<v.size();i++){
			Store stmp=(Store)(v.elementAt(i));
			if (!stmp.hasItem(it.getName()))
				sVTmp.removeElement(stmp);
		}
		return sVTmp;
	}

	public double dist(double x1, double y1,double x2, double y2){
		return Math.sqrt((y1-y2)*(y1-y2)+(x1-x2)*(x1-x2));
	}

	public void buyItem(Vector iV, Vector sV, double g, double x, double y, double expense){

		//System.out.println("buyItem item="+iV+" expense="+expense+" x="+x+" y="+y);

		if (iV.size()==0){
			expense+=dist(x,y,0,0)*g;
			if (expense<minPrice)
				minPrice=expense;
			return;
		}
		if (expense>=minPrice)
			return;


		for (int i=0;i<iV.size();i++){
			ItemType it = (ItemType)(iV.elementAt(i));
			Vector storeV=getStoresContain(sV,it);
			for (int j=0;j<storeV.size();j++){
				Store st = (Store)(storeV.elementAt(j));
				double d = st.dist(x,y);
				double tmpExpense = expense;


				tmpExpense+=d*g+st.getPrice(it);

				//System.out.println("tmpExpense="+tmpExpense);

				Vector newIV = (Vector)(iV.clone());
				newIV.removeElementAt(i);

				if (it.isBad()){
					tmpExpense+=st.dist(0.0,0.0)*g;

					//System.out.println(" tmpExpense="+tmpExpense);

					buyItem(newIV,sV,g,0.0,0.0,tmpExpense);
				}
				else{
					buyItem(newIV,sV,g,st.getX(),st.getY(),tmpExpense);
				}
			}

		}




	}




}


class ItemType{
	String name="";
	boolean bad=false;

	public ItemType(String s){
		if(s.endsWith("!")){
			bad=true;
			name=s.substring(0,s.length()-1);
		}
		else
			name=s;
	}

	public String getName(){
		return name;
	}

	public boolean isBad(){
		return bad;
	}

	public String toString(){
		return name;
	}
}

class Item{
	String name="";
	double price=0.0;
	public Item(String s){
		StringTokenizer st = new StringTokenizer(s,":");
		name=st.nextToken();
		price=Double.valueOf(st.nextToken()).doubleValue();
	}

	public String getName(){
		return name;
	}

	public double getPrice(){
		return price;
	}

}

class Store{
	double x=0,y=0;
	Vector itemV=new Vector();

	public Store(String ff) {
		StringTokenizer st = new StringTokenizer(ff," ");
		x = Double.valueOf(st.nextToken()).doubleValue();
		y = Double.valueOf(st.nextToken()).doubleValue();
		while (st.hasMoreTokens()){
			itemV.addElement(new Item(st.nextToken()));
		}
	}

	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}

	public double dist(double x1, double y1){
		return Math.sqrt((y1-y)*(y1-y)+(x1-x)*(x1-x));
	}

	public double getPrice(ItemType it){
		for (int i=0;i<itemV.size();i++){
			Item itmp = (Item)(itemV.elementAt(i));
			if(it.getName().equals(itmp.getName())){
				return itmp.getPrice();
			}
		}
		return -1;
	}

	public boolean hasItem(String iname){
		for (int i=0;i<itemV.size();i++){
			Item itmp = (Item)(itemV.elementAt(i));
			if(iname.equals(itmp.getName())){
				return true;
			}
		}
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
