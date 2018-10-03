import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class SPL {
	
	static final String file_loc = "/Users/abiyyuismunandar/Documents/Algeo/src/";
	public static double var [][];
	public static double sol [];
	//Asumsi matriks selalu dalam bentuk nxn
	
	/*Defaul constructor*/
	public SPL()
	{
		var = new double[0][0];
		sol = new double[0];
	}
	
	/*Copy constructor*/
	public SPL(double otherVar[][], double otherSol [])
	{
		var = new double[otherVar.length][otherVar[0].length];
		sol = new double[otherSol.length];
		
		for(int i = 0;i<var.length;i++)
		{
			for(int j = 0;j<var[i].length;j++)
			{
				var[i][j] = otherVar[i][j];
			}
			sol[i] = otherSol[i];
		}
	}
	
	public static void setUpSPL()
	{
		/*Kamus*/
		int n; 	//Jumlah variabel
		int p; //jumlah persamaan
		Scanner sc;
		double filledVar[][];
		double filledSol[];
		/*Algoritma*/
		sc = new Scanner(System.in);
		System.out.print("Masukkan banyak variabel: ");
		n = sc.nextInt();
		System.out.print("Masukkan banyak persamaan");
		p = sc.nextInt();
		filledVar = new double[p][n];
		filledSol = new double[p];
		System.out.println("Masukkan konstanta variabel: ");
		for(int i = 0;i<filledVar.length;i++)
		{
			for(int j = 0;j<filledVar[i].length;j++)
			{
				filledVar[i][j] = sc.nextDouble();
			}
		}
		System.out.println("Masukkan solusi dari persamaan: ");
		for(int i = 0;i<filledSol.length;i++)
		{
			filledSol[i] = sc.nextDouble();
		}
		
		var = filledVar;
		sol = filledSol;
	}
	
	public static void bacaInterpol()
	{
		/*Kamus*/
		Scanner sc;
		int n;
		double x,y;
		double intpVar[][];
		double intpSol[];
		Interpolasi intp = new Interpolasi();
		/*Algoritma*/
		sc = new Scanner(System.in);
		System.out.print("Masukkan banyak point: ");
		n = sc.nextInt();
		intpVar = new double[n][n];
		intpSol = new double[n];
		Interpolasi.setUplist(n);
		for(int k = 0;k<n;k++)		//k = row
		{
			System.out.print("Masukkan (x"+k+",y"+k+"):");
			x = sc.nextDouble(); y = sc.nextDouble();
			Interpolasi.isiPoint(x,y,k);
			for(int j = 0;j<n;j++)	//j = column
			{
				intpVar[k][j] = intp.pangkatN(x,j);
			}
			intpSol[k] = y;
		}
		var = intpVar;
		sol = intpSol;
	}
	
	public static void cetakAugment()
	{
		System.out.println("Matriks Augmented: ");
		for(int i = 0;i<var.length;i++)
		{
			for(int j = 0;j<var[i].length;j++)
			{
				System.out.printf("%.3f",var[i][j]);
				System.out.print(" ");	
			}
			System.out.printf("%.3f",sol[i]);
			System.out.println();
		}
	}
	
	@SuppressWarnings("resource")
	public static void bacaSplFile(String filename)
	{
		/*Kamus*/
		Scanner reader = null, countElmt = null, counterRow = null;
		int i;
		int countRow;			//menghitung panjang Row
		int countCol;			//Menghitung panjang column
		double filledVar[][];
		double filledSol[];
		/*Algoritma*/
		try {
			reader = new Scanner(new FileInputStream(file_loc+filename));
			countElmt = new Scanner(new FileInputStream(file_loc+filename));;
			counterRow = new Scanner(new FileInputStream(file_loc+filename));;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		i = 0;countRow = 0;countCol = 0;
		while(counterRow.hasNext())
		{
			counterRow.nextLine();
			countRow++;
		}
		while(countElmt.hasNext())
		{
			countElmt.nextLine();
			countCol++;
		}
		filledVar = new double[countRow][countCol/countRow - 1];
		filledSol = new double[countRow];
		for( i = 0;i<filledVar.length;i++)
		{
			for(int j = 0;j<filledVar[0].length;j++)
			{
				if(reader.hasNext())
				{
					filledVar[i][j] = reader.nextDouble();
					if(j == filledVar[i].length-1 && reader.hasNext())
					{
						filledSol[i] = reader.nextDouble();
					}
				}
			}
		}
		var = filledVar;
		sol = filledSol;
	}
	
	public static void bacaInterpolFile(String filename)
	{
		/*Kamus*/
		Scanner reader = null, counter = null;
		int n;		//jumlah point yang akan menjadi persamaan
		double x,y;
		double intpVar[][];
		double intpSol[];
		Interpolasi intp = new Interpolasi();
		/*Algoritma*/
		try {
			reader = new Scanner(new FileInputStream(file_loc+filename));
			counter = new Scanner(new FileInputStream(file_loc+filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		n = 0;
		while(counter.hasNext())
		{
			counter.nextLine();
			n++;
		}
		intpVar = new double[n][n];
		intpSol = new double[n];
		Interpolasi.setUplist(n);
		for(int k = 0;k<n;k++)		//k = row
		{
			x = reader.nextDouble(); y = reader.nextDouble();
			Interpolasi.isiPoint(x, k, k);
			for(int j = 0;j<n;j++)	//j = column
			{
				intpVar[k][j] = intp.pangkatN(x,j);
			}
			intpSol[k] = y;
		}
		var = intpVar;
		sol = intpSol;
	}
	
}
