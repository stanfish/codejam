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
	String re="";

	StringTokenizer st = new StringTokenizer(str," ");
	Point p1 = new Point(Integer.valueOf(st.nextToken()).intValue(),Integer.valueOf(st.nextToken()).intValue());
	Point p2 = new Point(Integer.valueOf(st.nextToken()).intValue(),Integer.valueOf(st.nextToken()).intValue());
	Point p3 = new Point(Integer.valueOf(st.nextToken()).intValue(),Integer.valueOf(st.nextToken()).intValue());

	//System.out.println("1="+p1+" 2="+p2+" 3="+p3);

	if (p1.equals(p2) || p3.equals(p2) || p3.equals(p1))
		re="not a triangle";
	else {
		if (isOnALine(p1,p2,p3))
			re="not a triangle";
		else{
			String triType=getTriType(p1,p2,p3);
			String triAngle=getTriAngle(p1,p2,p3);


			re=triType+" "+triAngle+" triangle";
		}
	}

	//System.out.println("re="+re);

	result+="Case #"+c+": "+re+System.getProperty("line.separator");
  }

	public String getTriAngle(Point p1,Point p2, Point p3){

		System.out.println("1="+angle(p1,p2,p3)+" 2="+angle(p3,p1,p2)+" 3="+angle(p1,p3,p2));

		if (angle(p1,p2,p3)==90 || angle(p1,p3,p2)==90 || angle(p3,p1,p2)==90)
			return "right";
		if (angle(p1,p2,p3)>90 || angle(p1,p3,p2)>90 || angle(p3,p1,p2)>90)
			return "obtuse";
		return "acute";
	}

    public double angle(Point p1,Point p2, Point p3) {
		int a_x=(int)p1.getX();
		int a_y=(int)p1.getY();
		int b_x=(int)p2.getX();
		int b_y=(int)p2.getY();
		int c_x=(int)p3.getX();
		int c_y=(int)p3.getY();
        int ba_x = a_x - b_x;
        int ba_y = a_y - b_y;
        int bc_x = c_x - b_x;
        int bc_y = c_y - b_y;

        double radians = Math.atan2(ba_x * bc_y - bc_x * ba_y, ba_x * bc_x
                + ba_y * bc_y)
                % (2 * Math.PI);

        double degrees = radians * 180 / Math.PI;

        if (degrees < 0)
            degrees += 360;

		if (degrees>=180)
			degrees = 360-degrees;

        return degrees;
    }

	public String getTriType(Point p1,Point p2, Point p3){
		if (dist(p1,p2)==dist(p1,p3) && dist(p1,p2)==dist(p2,p3))
			return "equilateral";
		else if (dist(p1,p2)==dist(p1,p3) || dist(p1,p2)==dist(p2,p3) || dist(p2,p3)==dist(p1,p3))
			return "isosceles";
		return "scalene";
	}

	public double dist(Point p1,Point p2){
		return ((p1.getY()-p2.getY())*(p1.getY()-p2.getY())+(p1.getX()-p2.getX())*(p1.getX()-p2.getX()));
	}

	public boolean isOnALine(Point p1,Point p2,Point p3){
		double m1=(p1.getY()-p2.getY())/(p1.getX()-p2.getX());
		double m2=(p3.getY()-p2.getY())/(p3.getX()-p2.getX());
		double m3=(p1.getY()-p3.getY())/(p1.getX()-p3.getX());
		return(m1==m2 || m2==m3 || m1==m3);
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
