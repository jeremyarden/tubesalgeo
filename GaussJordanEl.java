public static void GaussJordanEl(double[][] M, double[] N)
	  {
	    int i, j, k, l, m, n, o, p, q, row, col,max;
	    double dvdr;
	    int CountZero;

	    //Pivot row
	    row = M.length;
	    col = M[0].length;

	    GaussianEl(M,N);
	    boolean stop = false;
	    
	    for (p = 0; p < col; p++)
	    {
	    	  
	      q = row - 1;
	      while ((M[q][p] != 1) && (q > 0))
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
	      
	      
	    
	      
	    }
	  }
