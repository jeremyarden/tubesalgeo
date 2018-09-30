
public class Solver 
{
	public double solution[];
	
	public void gaussian(double var [][], double sol []) 
	{
		boolean isZero;
		double temp, rowD, coef;		//rowD = pembagi untuk baris
		int rowN, colN;
		for(int k = 0;k<var.length;k++)
		{
			rowN = 0;colN = k;	/*rowN = untuk mencari baris yang tidak mengandung elmt 0
								jika semua baris dibawah diagonal 0 maka colN = mencari colN non zero*/
			/*Cek jika isinya 0 atau tidak*/
			if(var[k][k] == 0)
			{
				isZero = true;		
				while(isZero && rowN<var.length)
				{
					isZero = var[rowN][k] == 0;
					rowN++;
				}
				if(!isZero)	//Artinya perlu di switch isi baris k dengan rowN
				{
					for(int col = 0;col<var[rowN].length;col++)		//switch baris var
					{
						temp = var[k][col];
						var[k][col] = var[rowN][col];
						var[rowN][col] = temp;
					}
				
					temp = sol[k];				//switch "baris" sol
					sol[k] = sol[rowN];
					sol[rowN] = temp;
				} 
				else		//ketika semua isi baris dibawah diagonal 0
				{
					isZero = true;
					while(isZero && colN<var[k].length)
					{
						isZero = var[k][colN] == 0;
						colN++;
					}
					if(isZero)	//Maka baris dengan 0 semua ditaruh di paling bawah matriks
					{
						for(int col = 0;col<var[k].length;col++)		//switch baris var
						{
							temp = var[k][col];
							var[k][col] = var[var.length][col];
							var[var.length][col] = temp;
						}
					
						temp = sol[k];				//switch "baris" sol
						sol[k] = sol[var.length];
						sol[var.length] = temp;
					} else
					{
						colN = colN-1;	//Untuk mendapatkan posisi column yg pas
					}
				}
			}
			rowD = var[k][colN];			//Jika tidak terjadi apa" maka diagonal yg dipakai
			
			/*Pembagian baris dengan rowD untuk membuat leading coef 1*/
			for(int col = 0;col<var[k].length;col++)
			{
				var[k][col] = var[k][col]/rowD;
			}
			sol[k] = sol[k]/rowD;
			
			/*Membuat dibawah leading coef menjadi 0*/
			for(int row = k+1;row<var.length;row++)
			{
				coef = var[row][colN]/var[k][colN];		//Coefficient agar dibawah row 0
				for(int col = colN;col<var[row].length;col++)
				{
					var[row][col] -= coef*(var[k][col]);
				}
				sol[row]-=coef*(sol[k]);
			}
		}
	}
	
	public void backSub(double var[][], double sol[])
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
	
	public void printSol(double solution [])
	{
		System.out.println("Solusi dari SPL:");
		for(int i = 0;i<solution.length;i++)
		{
			System.out.printf("x%d = %.3f\n",i,solution[i]);
		}
	}
}
