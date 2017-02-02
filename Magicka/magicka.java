/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jai
 */
public class magicka {
    public static String comblist[];
    public static String opplist[];
    public static String inpstr;
    public static char[] inp;
    public static ArrayList templist;
    public static char[] elelist;
public static void main(String args[]) throws FileNotFoundException, IOException
{
      FileReader fr=new FileReader("D:\\java\\readwritetext\\Magicka\\source.txt");
        BufferedReader brd=new BufferedReader(fr);
        int num=Integer.parseInt(brd.readLine());

        for(int i=0;i<num;i++)
        {
            elelist=new char[20];
            String temp=brd.readLine();
            String split[]=temp.split(" ");
            int combnum=Integer.parseInt(split[0]);
            comblist=new String[combnum];
            for(int j=0;j<combnum;j++)
            {
                comblist[j]=split[j+1];
                //System.out.println(comblist[j]);
            }
            int oppnum=Integer.parseInt(split[combnum+1]);
            opplist=new String[oppnum];
            for(int j=0;j<oppnum;j++)
            {
                opplist[j]=split[combnum+j+2];
                //System.out.println(opplist[j]);
            }
            int inplength=Integer.parseInt(split[combnum+oppnum+2]);
            //System.out.println(inplength);
            inpstr=split[combnum+oppnum+3];
            //System.out.println(inpstr);
            inp=inpstr.toCharArray();
            //int tempind=0;
            templist=new ArrayList();
            templist.add(inp[0]);
            //tempind=1;
            for(int j=1;j<inp.length;j++)
            {
                //templist[tempind]=inp[j];
                templist.add(inp[j]);
                if(templist.size() > 1)
                {
                    String front=templist.get(0).toString()+templist.get(1).toString();
                    String revfront=templist.get(1).toString()+templist.get(0).toString();
                    //System.out.println(front);

                    for(int k=0;k<combnum;k++)
                    {
                            char t1=front.charAt(0);
                            char t2=front.charAt(1);
if((comblist[k].contains(front) || comblist[k].contains(revfront)) && ((comblist[k].indexOf(templist.get(0).toString())==0 && comblist[k].indexOf(templist.get(1).toString())==1) || (comblist[k].indexOf(templist.get(0).toString())==1 && comblist[k].indexOf(templist.get(1).toString())==0)))
                        {
                            templist.set(0, comblist[k].charAt(2));
                            templist.remove(1);
                            //System.out.println(templist);
                        }
                    }
//                    }
//
                    if(templist.size()>=2)
                    {
                     String tail=templist.get(templist.size()-2).toString()+templist.get(templist.size()-1).toString();
                       String revtail=templist.get(templist.size()-1).toString()+templist.get(templist.size()-2).toString();
                    //System.out.println(front);
                    for(int k=0;k<combnum;k++)
                    {
                            char t1=tail.charAt(0);
                            char t2=tail.charAt(1);

                        if((comblist[k].contains(tail) || comblist[k].contains(revtail)) && ((comblist[k].indexOf(t1)==0 && comblist[k].indexOf(t2)==1) || (comblist[k].indexOf(t1)==1 && comblist[k].indexOf(t2)==0)||(comblist[k].contains(tail))))
                        {
                            templist.set(templist.size()-2, comblist[k].charAt(2));
                            templist.remove(templist.size()-1);

                        }
                    }

                     for(int k=0;k<oppnum;k++)
                     {
                         if(templist.contains(opplist[k].charAt(0 )) && templist.contains(opplist[k].charAt(1)))
                         {
                             templist.clear();
                         }
                     }

                }
                }

              //  tempind++;
            }
//            for(int j=0;j<templist.size();j++)
//            {
//                System.out.print(templist.get(j));
//            }
            System.out.println("Case #"+(i+1)+": "+templist);
          //  System.out.println();
            //System.out.println(inp[0]);
            //System.out.println(split[combnum+1]);
//            for(int j=0;j<split.length;j++)
//            {
//                System.out.print(split[j]+" ");
//            }
//            System.out.println();
        }





}
}
