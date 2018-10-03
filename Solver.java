import java.util.Random;
public class Solver 
{
	public static double solution[];
	public static String paraSolution[];
	private static String tipeSolusi;
	
	public static void solve(double var[][], double sol[],boolean GJ)
	/* Var dan Sol yang belum di Gauss atau Apapun
	 * boolean GJ menentukan memakai Gauss Jordan atau Gauss saja
	 */
	{	
		if(GJ)
		{
			GaussJordanEl(var,sol);
		} else
		{
			GaussianEl(var,sol);
		}
		tipeSolusi = checkLastRow(var,sol);
		if(tipeSolusi.equals("Solusi Banyak"))
		{
			GaussJordanEl(var,sol);						//Asumsi sudah dalam bentuk Gauss yang benar
			paraSolution = new String[var[0].length];	//Para solution sepanjang banyak variabel
			paraSolve(var,sol);
		} else if(tipeSolusi.equals("Tidak Ada Solusi"))
		{
			paraSolution = new String[1];
			paraSolution[0] = "Tidak Ada Solusi";
		} else	//Ada solusi bisa pake backSub
		{
			backSub(var,sol);
		}
	}
	public static String checkLastRow(double var[][], double sol[])
	{
		boolean allZero;
		int colLength, i;
		
		colLength = var[var.length-1].length-1;
		allZero = true;i = 0;
		while(i<colLength && allZero)
		{
			allZero = var[colLength][i] == 0;
			i++;
		}
		
		if(allZero && sol[sol.length-1] == 0)		//Solusi banyak
		{
			return "Solusi Banyak";
		}
		else //Tidak ada solusi
		{
			return "Tidak Ada Solusi";
		}
	}
	
	public static void backSub(double var[][], double sol[])
	{
		solution = new double[var.length];
		double sum;
		for(int i = var.length-1;i>=0;i--)
		{
			sum = 0.0;
			for(int j = i +1;j<var.length;j++)
			{
				sum += var[i][j] * solution[j];
			}
			solution[i] = (sol[i] - sum)/var[i][i];
		}
	}
	
	private static void paraSolve(double var[][], double sol[])
	{
		/*Kamus*/
		
		/*Algoritma*/	
		for(int i = var[0].length - 1;i>=0;i--)	//Mengisi variabel-variabel yang menjadi parametrik
		{
			if(isiColumn(var,i) > 1)
			{
				paraSolution[i] = randomString();
			}
		}
		for(int row = 0; row<var.length;row++)
		{
			int col = row;
			while(var[row][col] != 1 && col<var[row].length - 1 )		//Mencari kolom yang bukan 0
			{
				col++;
			}
			if(paraSolution[col] == null)		//Artinya variabel ini belum diisi
			{
				paraSolution[col] = ""+sol[row];		//Konstanta persamaan
				for(int j = col + 1;j<var[row].length;j++)
				{
					if(var[row][j] != 0)
					{
						paraSolution[col] += var[row][j] > 0 ? " - " : " + ";
						paraSolution[col] += var[row][j]+""+paraSolution[j];
					}
				}
			}
		}
		
	}
	
	private static String randomString()
	{	
		Random rd = new Random();
		int rnd = 97 + rd.nextInt(26);	//Generate ASCII from 97 - 122
		char rndChar = (char) rnd;
		
		return ""+rndChar;
		
		
	}
	
	private static int isiColumn(double arr[][], int col) //isi artinya >0
	{
		int count;
		
		count = 0;
		for(int i = 0;i<arr.length;i++)
		{
			if(arr[i][col] > 0)
			{
				count++;
			}
		}
		return count;
	}
	public static void GaussianEl(double[][] M, double[] N)
	  {
	    int i, j, k, l, m, n, o, p, row, col;
	    double dvdr;
	    int max;
	  
	    //Pivot row
	    row = M.length;
	    col = M[0].length;
	  
	    if (row > col)
	    {
	      i = row;
	    }
	    else
	    {
	      i = col;
	    }
	    //Pivot row
	    for (j = 0; j < i; j++)
	    {
	      max = j;
	      for (k = j + 1; k < i; k++)
	      {
	        if (Math.abs(M[k][j]) > Math.abs(M[max][j]))
	        {
	          max = k;
	        }
	      }
	      double[] temp1;
	      temp1 = M[j];
	      M[j] = M[max];
	      M[max] = temp1;
	  
	      double temp2;
	      temp2 = N[j];
	      N[j] = N[max];
	      N[max] = temp2;
	      //Biar elemen pivotny jadi 1
	      l = j;
	      while ((l < col) && (M[j][l] == 0))
	      {
	        l++;
	      }
	      if (l < col)
	      {
	        dvdr = M[j][l];
	        for (m = j; m < col; m++)
	        {
	          M[j][m] = M[j][m] / dvdr;
	        }
	        N[j] = N[j] / dvdr;
	      }
	      //Biar bawahny jadi 0
	      for (n = j + 1; n < row; n++)
	      {
	        dvdr = M[n][l] / M[j][l];
	        for (o = 0; o < col; o++)
	        {
	          M[n][o] = M[n][o] - (dvdr * M[j][o]);
	        }
	        N[n] = N[n] - (dvdr * N[j]);
	      }
	    }
	  }
	
	public static void GaussJordanEl(double[][] M, double[] N)
	  {
	    int i, j, k, l, m, n, o, p, q, row, col,max;
	    double dvdr;

	    //Pivot row
	    row = M.length;
	    col = M[0].length;

	    if (row > col)
	    {
	      i = row;
	    }
	    else
	    {
	      i = col;
	    }
	    //Pivot row
	    for (j = 0; j < i; j++)
	    {
	      max = j;
	      for (k = j + 1; k < i; k++)
	      {
	        if (Math.abs(M[k][j]) > Math.abs(M[max][j]))
	        {
	          max = k;
	        }
	      }

	      double[] temp1;
	      temp1 = M[j];
	      M[j] = M[max];
	      M[max] = temp1;
	  
	      double temp2;
	      temp2 = N[j];
	      N[j] = N[max];
	      N[max] = temp2;
	      //Biar elemen pivotny jadi 1
	      l = j;
	      while ((l < col) && (M[j][l] == 0))
	      {
	        l++;
	      }
	      if (l < col)
	      {
	        dvdr = M[j][l];
	        for (m = j; m < col; m++)
	        {
	          M[j][m] = M[j][m] / dvdr;
	        }
	        N[j] = N[j] / dvdr;
	      }
	      //Biar bawahny jadi 0
	      for (n = j + 1; n < row; n++)
	      {
	        dvdr = M[n][l] / M[j][l];
	        for (o = 0; o < col; o++)
	        {
	          M[n][o] = M[n][o] - (dvdr * M[j][o]);
	        }
	        N[n] = N[n] - (dvdr * N[j]);
	      }
	    }
	    boolean stop = false;
	    
	    for (p = 0; p < col; p++)
	    {
	    	  
	      q = row - 1;
	      while ((M[q][p] != 1) && (q >= 0))
	      {
	        if ((p == col - 1) && (M[q][p] == 0))
	        {
	        		stop = true;
	        		break;
	        }
	    	  	q--;
	      }
	      if (stop) break;
	      double temp3;
	     
	      if (q != 0)
	      {
	        for (i = 0; i < q; i++)
	        {
	        	  temp3 = M[i][p] / M[q][p];
	          for (j = 0; j < col; j++)
	          {
	        	  
	        	  	M[i][j] = M[i][j] - (temp3 * M[q][j]);
	          }
	          
	          N[i] = N[i] - (temp3 * N[q]);
	        }
	      }
	     // SPL s = new SPL(M,N);
	     // s.cetakAugment();
	      
	    }
	  }
	
	public static void printSolution()
	{
		System.out.println("Solusi dari persamaan adalah:");
		if(paraSolution[0].equals("Tidak Ada Solusi"))
		{
			System.out.println("Tidak Ada Solusi");
		} else if(paraSolution[0] != null)	//Banyak solusinya
		{
			for(int i = 0;i<paraSolution.length;i++)
			{
				System.out.println("x"+(i+1)+" = " + paraSolution[i]);
			}
		}else {
			for(int i = 0;i<solution.length;i++)
			{	
				System.out.printf("x%d = %0.3f",i + 1,solution[i]);
			}
		}
	}
}
