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
    File testFile = new File("C:\\java\\readwritetext\\lsource.txt");
    File testFile2 = new File("C:\\java\\readwritetext\\result.txt");
    //System.out.println("Original file contents : " + getContents(testFile));


	//breakCase(testFile);
	Case allcases = new Case(testFile);



    setContents(testFile2, allcases.getResult());
    //System.out.println("New file contents : " + getContents(testFile2));
  }
}


class Case{

	String num;
	String result="";

	public Case(File aFile) {

		try{
			StringBuilder contents = new StringBuilder();
		  	BufferedReader input =  new BufferedReader(new FileReader(aFile));
			String line = input.readLine();
			num=line;
			int i=1;
			while (( line = input.readLine()) != null){
			  oneCase(line,i);
			  i++;
			}
		}
		catch (IOException ex){
		  ex.printStackTrace();
		}
	}




  public void oneCase (String s,int c){
	String re="";
	StringTokenizer st = new StringTokenizer(s," ");

	String prevChar="";
	String dir="S";
	int x=0,y=0,minX=0,maxX=0,minY=0,maxY=0;

	String first=st.nextToken();
	String second=st.nextToken();

	if (true){

		Room currentRoom=null;
		Hashtable saveH = new Hashtable();
		for (int i=0; i<first.length(); i++){
			String eachChar=first.substring(i,i+1);
			if (eachChar.equals("W")){
				if (currentRoom!=null){
					currentRoom.setDirTo(dir);
					if (prevChar.equals("W"))
						currentRoom.setFalse(turnL(dir));
				}

				if (dir.equals("N")){
					y--;
				}
				else if (dir.equals("S")){
					y++;
				}
				else if (dir.equals("W")){
					x--;
				}
				else if (dir.equals("E")){
					x++;
				}

				maxX=Math.max(x,maxX);
				maxY=Math.max(y,maxY);
				minX=Math.min(x,minX);
				minY=Math.min(y,minY);

				Room tmpR=null;
				if (saveH.containsKey(x+","+y))
					tmpR=(Room)saveH.get(x+","+y);
				else
					tmpR= new Room(x,y);

				tmpR.setDirFrom(dir);

				if (i==0)
					tmpR.setSureTrue("N");

				currentRoom=tmpR;
				saveH.put(x+","+y,currentRoom);
			}
			else if (eachChar.equals("R")){
				currentRoom.setFalse(dir);
				currentRoom.setFalse(turnL(dir));
				dir=turnR(dir);
			}
			else if (eachChar.equals("L")){
				dir=turnL(dir);
			}
			prevChar=eachChar;

		}


		if (dir.equals("W"))
			minX++;
		if (dir.equals("E"))
			maxX--;
		if (dir.equals("S"))
			maxY--;



		currentRoom=null;
		dir=turnR(dir);
		dir=turnR(dir);

		for (int i=0; i<second.length(); i++){
			String eachChar=second.substring(i,i+1);
			if (eachChar.equals("W")){
				if (currentRoom!=null){
					currentRoom.setDirTo(dir);
					if (prevChar.equals("W"))
						currentRoom.setFalse(turnL(dir));
				}

				if (dir.equals("N")){
					y--;
				}
				else if (dir.equals("S")){
					y++;
				}
				else if (dir.equals("W")){
					x--;
				}
				else if (dir.equals("E")){
					x++;
				}

				maxX=Math.max(x,maxX);
				maxY=Math.max(y,maxY);
				minX=Math.min(x,minX);
				minY=Math.min(y,minY);

				Room tmpR=null;
				if (saveH.containsKey(x+","+y))
					tmpR=(Room)saveH.get(x+","+y);
				else
					tmpR= new Room(x,y);

				tmpR.setDirFrom(dir);

				if (i==0)
					tmpR.setSureTrue(turnR(turnR(dir)));


				currentRoom=tmpR;
				saveH.put(x+","+y,currentRoom);
			}
			else if (eachChar.equals("R")){
				currentRoom.setFalse(dir);
				currentRoom.setFalse(turnL(dir));
				dir=turnR(dir);
			}
			else if (eachChar.equals("L")){
				dir=turnL(dir);
			}

			prevChar=eachChar;


		}

		int col=Math.abs(minX-maxX)+1;
		int row=Math.abs(minY-maxY);


		//System.out.println("second col="+col+" row="+row+" minX="+minX+" maxX="+maxX+" minY="+minY+" maxY="+maxY);

		for(int i=1; i<=row; i++){
			String eachRow="";
			for (int j=0; j<col; j++){
				String ttt=(minX+j)+","+(minY+i);
				Room tmpRoom = (Room)(saveH.get(ttt));

				if (tmpRoom==null){
					eachRow+="-";
				}
				else {

					if (j==0){
						tmpRoom.setW(false);
					}
					if (j==col-1){
						tmpRoom.setE(false);
					}
					if (i==1){
						tmpRoom.setN(false);
					}
					if (i==row){
						tmpRoom.setS(false);
					}

					eachRow+=tmpRoom.toString();
				}
			}
			re+=eachRow+System.getProperty("line.separator");
		}

	}




	result+="Case #"+c+":"+System.getProperty("line.separator")+re;

  }

  public String turnL (String s){
	  if (s.equals("S"))
	  	return "E";
	  if (s.equals("E"))
	  	return "N";
	  if (s.equals("N"))
	  	return "W";

	  	return "S";
  }

  public String turnR (String s){
	  if (s.equals("S"))
	  	return "W";
	  if (s.equals("E"))
	  	return "S";
	  if (s.equals("N"))
	  	return "E";

	  	return "N";
  }


  public String getResult (){

	return result;

  }


}


class Room{

	boolean e=false,w=false,s=false,n=false;
	int x,y;
	String sureDir="";

	public Room(int xx, int yy) {
		x=xx;
		y=yy;
	}

	public void setE(boolean b){
		e=b;
	}

	public void setW(boolean b){
		w=b;
	}

	public void setS(boolean b){
		s=b;
	}

	public void setN(boolean b){
		n=b;
	}

	public boolean getE(){
		return e;
	}

	public boolean getW(){
		return w;
	}

	public boolean getS(){
		return s;
	}

	public boolean getN(){
		return n;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public void setDirFrom(String dir){
		if (dir.equals("S"))
			n=true;
		else if (dir.equals("N"))
			s=true;
		else if (dir.equals("E"))
			w=true;
		else if (dir.equals("W"))
			e=true;
	}

	public void setDirTo(String dir){
		if (dir.equals("S"))
			s=true;
		else if (dir.equals("N"))
			n=true;
		else if (dir.equals("E"))
			e=true;
		else if (dir.equals("W"))
			w=true;
	}

	public void setFalse(String dir){
		if (dir.equals("S"))
			s=false;
		else if (dir.equals("N"))
			n=false;
		else if (dir.equals("E"))
			e=false;
		else if (dir.equals("W"))
			w=false;
	}

	public void setTrue(String dir){
		if (dir.equals("S"))
			s=true;
		else if (dir.equals("N"))
			n=true;
		else if (dir.equals("E"))
			e=true;
		else if (dir.equals("W"))
			w=true;
	}

	public void setSureTrue(String d){
		sureDir+=d;
	}

	public String toString(){
		if (sureDir.indexOf("E")!=-1)
			e=true;
		if (sureDir.indexOf("W")!=-1)
			w=true;
		if (sureDir.indexOf("S")!=-1)
			s=true;
		if (sureDir.indexOf("N")!=-1)
			n=true;


		if (n && s && w && e)
			return "f";
		if (!n && s && w && e)
			return "e";
		if (n && !s && w && e)
			return "d";
		if (!n && !s && w && e)
			return "c";
		if (n && s && !w && e)
			return "b";
		if (!n && s && !w && e)
			return "a";
		if (n && !s && !w && e)
			return "9";
		if (!n && !s && !w && e)
			return "8";
		if (n && s && w && !e)
			return "7";
		if (!n && s && w && !e)
			return "6";
		if (n && !s && w && !e)
			return "5";
		if (!n && !s && w && !e)
			return "4";
		if (n && s && !w && !e)
			return "3";
		if (!n && s && !w && !e)
			return "2";
		if (n && !s && !w && !e)
			return "1";

		return "u";
	}



}