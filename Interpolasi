/*
 * 
 */
import java.util.Scanner;

public class Interpolasi  {
	private double pointInterp;
	private double solInterp;
	
	public Interpolasi()
	{
		pointInterp = 0;
		solInterp = 0;		//hasil dari interpolasi y(x) 
	}
	
	public void bacaPointInterp()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Masukkan point interpolasi: ");
		pointInterp = sc.nextDouble();
	}
	
	public void interpolate(double var[][], double sol[])
	{
		/*Kamus*/
		Solver s;
		/*Algoritma*/
		s = new Solver();
		s.gaussian(var, sol);
		s.backSub(var, sol);
		for(int i = 0;i<s.solution.length;i++)
		{
			solInterp+=s.solution[i]*pangkatN(pointInterp,i);
		}
	}
	
	public void printIntpRes(double var[][], double sol[])
	{
		/*Kamus*/
		Solver s;
		
		/*Algoritma*/
		s= new Solver();
		s.gaussian(var, sol);
		s.backSub(var, sol);
		for(int i = 0;i<s.solution.length;i++)
		{
			solInterp+=s.solution[i]*pangkatN(pointInterp,i);
		}
		System.out.print("Persamaan Polinom: ");
		for(int i = 0;i<s.solution.length;i++)
		{
			if(i == 0) System.out.printf("%.3f",s.solution[i]);
			else if(i == 1) System.out.printf("%.3fx", Math.abs(s.solution[i]));
			else
				System.out.printf("%.3fx^%d",Math.abs(s.solution[i]),i);
			if(i<s.solution.length-1)
			{
				System.out.print((s.solution[i+1]>=0)?" + ":" - ");
			}
		}
		System.out.println();
		System.out.printf("Hasil Interpolasi x = %.3f adalah %.3f", pointInterp, solInterp);
	}
	
	public double pangkatN(double x, int n)	//x bilangan yang ingin dipangkat, n pangkatnya
 	{
		double nilai;
		nilai = x;
		if(n == 0)
		{
			return 1.0;
		}
		else if(n == 1)
		{
			return x;
		} 
		else
		{
			for(int i = 1;i<n;i++)
			{
				nilai*=nilai;
			}
		}
		
		return nilai;
	}
	
}
