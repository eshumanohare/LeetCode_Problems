package thi;
import java.util.Scanner;
class Node 
{
	public int data;
	public Node link;
	Node()
	{
		data=0;
		link=null;
	}
	Node(int n)
	{
		data=n;
		link=null;
	}
	Node(int n, Node p) 
	{
			data = n;
			link = p;
	}
	
	public void setLink(Node a)
	{
		link=a;
	}
}



public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Node a=new Node(1);
		a.link=a;
		for(int i=0;i<n;i++)
		{
			int b = in.nextInt();
			if(b==1)
			{
				int c=in.nextInt();
				Node temp=new Node(c);
				temp.link=a.link;
				a.link=temp;
			}
			else
			if(b==2)
			{
				a=a.link;
			}
			else
			if(b==3)
			{
				System.out.println(a.data);
			}
	
		}
		
	}

}
