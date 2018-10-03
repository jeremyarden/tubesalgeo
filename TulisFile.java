import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class TulisFile {
	
	private static DecimalFormat df3 = new DecimalFormat("#.###");
	static final String file_loc = "/Users/abiyyuismunandar/Documents/Algeo/src/";
	
	public static void tulisSpl(double varOri[][],double solOri[], double var[][], double sol[],double solution[], String paraSolution[],String filename) throws IOException
	{
		File file = new File(file_loc+filename);
		file.createNewFile();
		
		FileWriter writer = new FileWriter(file);
		writer.write("Matriks Augmented Original: \n");
		for(int i = 0;i<varOri.length;i++)
		{
			for(int j = 0;j<varOri[i].length;j++)
			{
				writer.write(df3.format(varOri[i][j])+" ");
					
			}
			writer.write("| "+df3.format(solOri[i]));
			writer.write("\n");
		}
		
		writer.write("Matriks Augmented Setelah di Gauss/GaussJordan: \n");
		for(int i = 0;i<var.length;i++)
		{
			for(int j = 0;j<var[i].length;j++)
			{
				writer.write(df3.format(var[i][j])+" ");
					
			}
			writer.write("| "+df3.format(sol[i]));
			writer.write("\n");
		}
		
		writer.write("Solusi dari matriks: \n");
		if(paraSolution[0].equals("Tidak Ada Solusi"))
		{
			writer.write("Tidak Ada Solusi");
		} else if(paraSolution[0] != null)	//Banyak solusinya
		{
			for(int i = 0;i<paraSolution.length;i++)
			{
				writer.write("x"+i+1+" = " + paraSolution[i]);
			}
		}else {
			for(int i = 0;i<solution.length;i++)
			{
				writer.write("x"+i+1+" = "+df3.format(solution[i]));
			}
		}
		
		writer.flush();
		writer.close();
		
	}
	
	public static void tulisInterp(double var[][], double sol[], String filename) throws IOException
	{
		File file = new File(file_loc+filename);
		file.createNewFile();
		
		FileWriter writer = new FileWriter(file);
		
		Solver.GaussianEl(var, sol);
		Solver.backSub(var, sol);
		Interpolasi.solInterp = 0;
		for(int i = 0;i<Solver.solution.length;i++)
		{
			Interpolasi.solInterp+=Solver.solution[i]*Interpolasi.pangkatN(Interpolasi.pointInterp,i);
		}
		writer.write("Point yang dimasukkan:\n");
		int i = 0;
		while((i<Interpolasi.listX.length))
		{	
			writer.write("("+df3.format(Interpolasi.listX[i])+","+df3.format(Interpolasi.listY[i])+")\n");
			i++;
		}
		writer.write("Persamaan Polinom:\n");
		for( i = 0;i<Solver.solution.length;i++)
		{
			if(i == 0) writer.write(df3.format(Solver.solution[i]));
			else if(i == 1) writer.write(df3.format( Math.abs(Solver.solution[i]))+"x");
			else
				writer.write(df3.format(Math.abs(Solver.solution[i]))+"x^"+i);
			if(i<Solver.solution.length-1)
			{
				writer.write((Solver.solution[i+1]>=0)?" + ":" - ");
			}
		}
		writer.write("\n");
		writer.write("Hasil Interpolasi x = "+ df3.format(Interpolasi.pointInterp)+" adalah "+df3.format(Interpolasi.solInterp));
		
		writer.flush();
		writer.close();
	}
}
