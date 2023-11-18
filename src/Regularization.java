import java.util.ArrayList;

public class Regularization {
    //вычисление коэффициентов по формуле w = (XT*X + lambda*I)^-1 * XT * Y
    public double[] L2Regularization(ArrayList<Point> points,int M,double L){
        double[] x=new double[points.size()];
        double[] y= new double[points.size()];
        for(int i =0;i<points.size();i++){
            x[i]=points.get(i).x;
            y[i]=points.get(i).y;
        }
        double[][] X= new double[x.length][M];
        for(int i=0;i<x.length;i++)
        {
            for (int j=0;j<M;j++){
                X[i][j]=Math.pow(x[i],j);
            }
        }
        double[][] I=new double[M][M];
        for(int i=0;i<M;i++){
            for (int j =0;j<M;j++){
                if(i==j){
                    I[i][j]=L;
                }
                else{
                    I[i][j]=0;
                }
            }
        }
        I[0][0]=0;
        double[][] XT=new double[M][x.length];
        for (int i = 0; i < X.length; i++) {
            for (int j = 0; j < X[i].length; j++) {
                XT[j][i] = X[i][j];
            }
        }
        double [][] matrixM = new double[XT.length][X[0].length];
        for (int i = 0; i < matrixM.length; i++) {
            for (int j = 0; j < matrixM[0].length; j++) {
                matrixM[i][j] = 0;
                for (var k = 0; k < XT[0].length; k++) {
                    matrixM[i][j] += XT[i][k] * X[k][j];
                }
            }
        }
        double [][] A=new double[matrixM.length][matrixM[0].length];
        for(int i =0;i<A.length;i++){
            for (int j = 0;j<A[i].length;j++){
                A[i][j]=matrixM[i][j]+I[i][j];
            }
        }
        double[][] inversion=inversion(A,A.length);
        double [] temp = new double[X[0].length];
        for (int j = 0; j < X[0].length; j++) {
            for (int i = 0; i < y.length; i++) {
                temp[j] += X[i][j] * y[i];
            }
        }
        double [] result = new double[A[0].length];
        for (int j = 0; j < A[0].length; j++) {
            for (int i = 0; i < temp.length; i++) {
                result[j] += inversion[i][j] * temp[i];
            }
        }
        return result;
    }
    public double[][] inversion(double [][]A, int N)
    {
        double temp;

        double [][] E = new double[N][N];


        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
            {
                E[i][j] = 0f;

                if (i == j)
                    E[i][j] = 1f;
            }

        for (int k = 0; k < N; k++)
        {
            temp = A[k][k];

            for (int j = 0; j < N; j++)
            {
                A[k][j] /= temp;
                E[k][j] /= temp;
            }

            for (int i = k + 1; i < N; i++)
            {
                temp = A[i][k];

                for (int j = 0; j < N; j++)
                {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }

        for (int k = N - 1; k > 0; k--)
        {
            for (int i = k - 1; i >= 0; i--)
            {
                temp = A[i][k];

                for (int j = 0; j < N; j++)
                {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                A[i][j] = E[i][j];
        return A;
    }
}
