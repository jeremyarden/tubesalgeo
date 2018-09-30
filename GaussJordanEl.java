import java.util.Scanner;

public class GaussJordanEl()
{
  public static void GaussJordanEl(double[][] M, double[] N)
  {
    int i, j, k, l, m, n, o, p, q, row, col;
    int max, dvdr;

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
      temp1 = M[k];
      M[k] = M[max];
      M[max] = temp1;

      double temp2;
      temp2 = N[k];
      N[k] = N[max];
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

    for (p = 0; p < col; p++)
    {
      q = row - 1;
      while ((M[q][p] != 1) && (q >= 0))
      {
        q--;
      }

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
}
