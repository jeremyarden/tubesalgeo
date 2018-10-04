import java.util.Scanner;

public static void GaussianEl(double[][] M, double[] N)
	  {
	    int i, j, k, l, m, n, o, p, capeksayakak, capek, row, col;
	    double dvdr;
	    int max;
	    boolean AllZero;
	  
	    //Pivot row
	    row = M.length;
	    col = M[0].length;
	  
	    if (row <= col)
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
	      k = j+1;
	      while(k<row)
	      {
		    	  if (Math.abs(M[k][j]) > Math.abs(M[max][j]))
		        {
		    		  max = k;
		        }
		    	  k++;
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
	      
	      //Biar bawahny jadi 0
	      n = j + 1;
	      while (n < row)
	      {
	        dvdr = M[n][l] / M[j][l];
	        for (o = 0; o < col; o++)
	        {
	          M[n][o] = M[n][o] - (dvdr * M[j][o]);
	        }
	        N[n] = N[n] - (dvdr * N[j]);
	        n++;
	      }
	    }
	    }
