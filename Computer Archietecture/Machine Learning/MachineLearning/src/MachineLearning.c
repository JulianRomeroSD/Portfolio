/*
 ============================================================================
 Name        : MachineLearning.c
 Author      : Julian Romero
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */
#include<stdio.h>
#include<stdlib.h>

// all methods declarations
void printMatrix(double** mat, int row, int col){
  for (int i = 0; i < row; i++){
    for(int j = 0; j < col; j++){
      printf("%lf\t", mat[i][j]);
    }
    printf("\n");
  }
}

double** multiplyMatrix(double **matA, double **matB, int r1, int c1, int r2, int c2)
{
  double** result=calloc(r1, sizeof(double*));
  for (int i = 0; i < r1; i++) {
    result[i] = calloc(c2, sizeof(double));
  }

  for (int i=0; i < r1; i++){
    for (int j = 0; j < c2; j++){
      double sum = 0;
      for(int k = 0;k < c1;k++){
        sum += (matA[i][k] * matB[k][j]);
      }
      result[i][j] = sum;
    }
  }
  return result;
}

double** transposeMatrix(double** mat, int row, int col)
{
  //allocate for transpose XT
  double** matTran= calloc(col, sizeof(double*));
  for (int i = 0; i < col; i++) {
    matTran[i] = calloc(row, sizeof(double));
  }

  for (int i=0; i < row; i++){
    for(int j = 0; j < col; j++){
      matTran[j][i] += mat[i][j];
    }
  }

  return matTran;
}


double** inverseMatrix(double **matA, int dimension)
{

  double** matI=calloc(dimension, sizeof(double*));
  for (int i = 0; i < dimension; i++) {
    matI[i] = calloc(dimension, sizeof(double));
  }


  //turn matI into identity
  for(int i = 0;i < dimension;i++){
    for (int j = 0; j < dimension; j++){
      if(i == j){
        matI[i][j] += 1;
      }
    }
  }


  double f;
  for (int p = 0; p < dimension; p++){
    f = matA[p][p];
    for(int j = 0; j < dimension ; j++){
      matA[p][j] = (matA[p][j] / f);
      matI[p][j] = (matI[p][j] / f);
    }
    for (int i = p + 1; i < dimension; i++){
      f = matA[i][p];
      for(int k = 0; k < dimension;k++){
        matA[i][k] = (matA[i][k] - (matA[p][k] * f));
        matI[i][k] = (matI[i][k] - (matI[p][k] * f));
      }
    }
  }
  for (int p = dimension -1; 0 <= p ; p--){
    for (int i = p -1; 0 <= i; i--){
      f = matA[i][p];
      for(int j = dimension -1; 0 <= j; j--){
        matA[i][j] = (matA[i][j] - (matA[p][j] * f));
        matI[i][j] = (matI[i][j] - (matI[p][j] * f));
      }
    }

  }
  return matI;
}
void handleMemory(double** matrix, int row){
  for (int i = 0; i < row; i++) {
    free(matrix[i]);
  }
  free(matrix);
}
//main method starts here
int main(int argc, char** argv){
  int K;
  int N;
  double A;
  double B;
  //training file
  FILE *fp = fopen(argv[1],"r");

  fscanf(fp,"%d\n",&K);
  fscanf(fp,"%d\n",&N);
  int columns = K + 1;

  //allocation for matrices original, X, and Y
  double **original = calloc(N, sizeof(double*));
  for (int i = 0; i < N; i++) {
    original[i] = calloc(columns, sizeof(double));
  }
		    double** X = calloc(N, sizeof(double*));
		    for (int i = 0; i < N; i++) {
		      X[i] = calloc(columns, sizeof(double));
		    }

		    double** Y = calloc(N, sizeof(double*));
		    for (int i = 0; i < N; i++) {
		      Y[i] = calloc(1, sizeof(double));
		    }


		    //original
		    for(int i = 0;i < N; i++){
		      for(int j = 0; j<columns;j++){
		        if(j == K){
		          fscanf(fp,"%lf\n",&A);
		          original[i][j] += A;
		        }
		        else{
		          fscanf(fp,"%lf%*c",&B);
		          original[i][j] += B;
		        }
		      }
		    }
		    //populate X
		    for (int i = 0; i < N; i++){
		      for (int j = 0; j < columns; j++){
		        if (j == 0){
		          X[i][j] += 1;
		        }
		        else{
		          X[i][j] += original[i][j-1];
		        }
		      }
		    }
				  //populate Y
				  for(int i = 0;i< N;i++){
				    Y[i][0] += original[i][K];
				  }
				  //original, X, and Y matrices

				  //transposeMatrix
				  double** XT = transposeMatrix(X, N, columns);

				  //multiply x and X^T
				  double** XTandX = multiplyMatrix(XT, X, columns, N, N, columns);

				  double** inversedXTandX = inverseMatrix(XTandX, columns);

				  //multiply inverse and XT
				  double** inversedandXT = multiplyMatrix(inversedXTandX, XT, columns, columns, columns, N);

				  //multiply inversedXTandX and Y
				  double** weights = multiplyMatrix(inversedandXT, Y, columns, N, N, 1);

				  fclose(fp);



					  //handling test input
					  FILE *fp1 = fopen(argv[2],"r");
					  int M;
					  double V;
					  double P;
					  fscanf(fp1,"%d\n",&M);
					  double** xFinal = calloc(M, sizeof(double*));
					  for (int i = 0; i < M; i++) {
					    xFinal[i] = calloc(columns, sizeof(double));
					  }

					  for(int i = 0;i < M; i++){
					    for(int j = 0; j < columns; j++){
					      if(j == 0){
					        xFinal[i][j] += 1;
					      }
					      else if(j == K){
					        fscanf(fp1,"%lf\n", &P);
					        xFinal[i][j] += P;
					      }
					      else{
					        fscanf(fp1,"%lf%*c",&V);
					        xFinal[i][j] += V;
					      }
					    }
					  }

					  double** value = multiplyMatrix(xFinal, weights, M ,columns, columns, 1);

					  for(int i = 0; i < M;i++){
					    printf("%0.0lf \n",value[i][0]);
					  }
					  fclose(fp1);

					  handleMemory(original,N);
					  handleMemory(X, N);
					  handleMemory(Y, N);
					  handleMemory(XT, columns);
					  handleMemory(XTandX, columns);
					  handleMemory(inversedXTandX, columns);
					  handleMemory(inversedandXT, columns);
					  handleMemory(weights, columns);
					  handleMemory(value, M);
					  handleMemory(xFinal, M);
					  return 0;
					}





