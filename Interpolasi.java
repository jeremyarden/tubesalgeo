import java.util.Scanner;

public class Interpolasi  {
	public static double pointInterp;
	public static double solInterp;
	public static double listX[];
	public static double listY[];
	
	public Interpolasi()
	{
		pointInterp = 0;
		solInterp = 0;		//hasil dari interpolasi y(x) 
	}
	public static void setUplist(int n)
	{
		listX = new double[n];
		listY = new double[n];
	}
	public static void bacaPointInterp()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Masukkan point interpolasi: ");
		pointInterp = sc.nextDouble();
	}
	
	public static void interpolate(double var[][], double sol[], boolean GJ)
	{
		/*Kamus*/

		/*Algoritma*/
		if(GJ)
		{
			Solver.GaussJordanEl(var, sol);
		}
		else 
		{
			Solver.GaussianEl(var, sol);
		}
		Solver.backSub(var, sol);
		for(int i = 0;i<Solver.solution.length;i++)
		{
			solInterp+=Solver.solution[i]*pangkatN(pointInterp,i);
		}
	}
	
	public static void printIntpRes(double var[][], double sol[],boolean GJ)
	{
		/*Kamus*/
		
		/*Algoritma*/
		interpolate(var,sol,GJ);
		System.out.println("Point yang dimasukkan:");
		int i = 0;
		while((i<listX.length))
		{
			System.out.printf("(%.3f,%.3f)\n", listX[i],listY[i]);
			i++;
		}
		System.out.print("Persamaan Polinom: ");
		for( i = 0;i<Solver.solution.length;i++)
		{
			if(i == 0) System.out.printf("%.3f",Solver.solution[i]);
			else if(i == 1) System.out.printf("%.3fx", Math.abs(Solver.solution[i]));
			else
				System.out.printf("%.3fx^%d",Math.abs(Solver.solution[i]),i);
			if(i<Solver.solution.length-1)
			{
				System.out.print((Solver.solution[i+1]>=0)?" + ":" - ");
			}
		}
		System.out.println();
		System.out.printf("Hasil Interpolasi x = %.3f adalah %.3f", pointInterp, solInterp);
	}
	
	public static double pangkatN(double x, int n)	//x bilangan yang ingin dipangkat, n pangkatnya
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
	public static void isiPoint(double X, double Y,int i)
	{
		listX[i] = X;
		listY[i] = Y;
	}
	
}
