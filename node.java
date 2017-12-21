import java.sql.Timestamp;
import java.util.*;
import java.io.*;

public class node {
	
	public static String ip_addr="";
	public static int port=1234;
	private static ArrayList<Integer> neighbors = new ArrayList<Integer>();
	
	private static String filename = "C:\\Users\\Ruthvi Ketan Thakkar\\Desktop\\";
	
	static BufferedReader br = null;
	static FileReader fr=null;
	private static String fn;
	
	public static void main(String args[])
	{
		try
		{
			Scanner ip = new Scanner(System.in);
			System.out.print("File:");
			fn=ip.nextLine();
			System.out.print("My Port :");
			port=ip.nextInt();
		
			fr = new FileReader(filename+fn+".txt");
			br = new BufferedReader(fr);
			String currLine;
			int[] port_number = new int[3];
			
			while((currLine=br.readLine())!=null)
			{
				String[] ports = currLine.split(" ");
				
				int i=0;
				
				for(String s : ports)
				{
					port_number[i] = Integer.parseInt(s);
					//System.out.println(""+port_number[i]);
					i++;
				}
			}
			
			for(int i : port_number)
			{
				neighbors.add(i);
				//System.out.println(Arrays.deepToString(neighbors.toArray()));
			} 
			UdpServerThread rec = new UdpServerThread(port,port_number);
			rec.start();
				
			
			Timestamp time = new Timestamp(System.currentTimeMillis());
			String m ;
			m=ip.nextLine();
			m=m+time.toString();
			
			for(int  i : port_number)
			{
				UdpClientThread send = new UdpClientThread(m.getBytes(),ip_addr,i);
				send.start();
			} 
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
}
