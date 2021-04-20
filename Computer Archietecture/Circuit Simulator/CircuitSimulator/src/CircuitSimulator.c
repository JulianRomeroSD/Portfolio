/*
 ============================================================================
 Name        : CircuitSimulator.c
 Author      : Julian Romero
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include<math.h>
void handleMatrixMemory(int** matrix, int row){
  for (int i = 0; i < row; i++) {
    free(matrix[i]);
  }
  free(matrix);
}

void printMatrix(int** mat, int row, int col){
	  for (int i = 0; i < row; i++){
	    for(int j = 0; j < col; j++){
	    	if(j == col -1){
	    		printf("%d", mat[i][j]);
	    	}
	    	else{
	    		printf("%d ", mat[i][j]);
	    	}
	    }
	    printf("\n");
	  }
}

void MatrixCopy (int ** original, int ** copy, int row, int column) {
  for(int i = 0;i < row; i++){
	  for(int j = 0; j < column;j++){
		  copy[i][j] = original[i][j];
	  }
  }
}

int** NotMatrix(int **original,int **matA, int row, int column,int input, int out){
  int** result = calloc(row, sizeof(int*));
  for (int i = 0; i < row; i++) {
    result[i] = calloc(column, sizeof(int));
  }
  MatrixCopy(original, result, row, column);
  for(int i = 0; i < row;i++){
	  if(result[i][input] == 0){
		  result[i][column-out] = 1;
	  }
  }
  return result;
}

int** AndMatrix(int **original,int **matA, int **matB, int row, int column,int out){
  int** result=calloc(row, sizeof(int*));
  for (int i = 0; i < row; i++) {
    result[i] = calloc(column, sizeof(int));
  }
  MatrixCopy(original, result, row, column);

  for(int i = 0;i < row;i++){
	  result[i][column-out] = matA[i][column-out] * matB[i][column-out];
  }

  return result;
}

int** NandMatrix(int **original,int **matA, int **matB, int row, int column,int out){
  int** result=calloc(row, sizeof(int*));
  for (int i = 0; i < row; i++) {
    result[i] = calloc(column, sizeof(int));
  }
  MatrixCopy(original, result, row, column);

  for(int i = 0;i < row;i++){
	  result[i][column-out] = matA[i][column-out] * matB[i][column-out];
	  if(result[i][column-out] == 1){
		  result[i][column-out] = 0;
	  }
	  else{
		  result[i][column-out] = 1;
	  }
  }

  return result;
}

int** OrMatrix(int **original,int **matA, int **matB, int row, int column, int out){
  int** result=calloc(row, sizeof(int*));
  for (int i = 0; i < row; i++) {
    result[i] = calloc(column, sizeof(int));
  }
  MatrixCopy(original, result, row, column);

  for(int i = 0;i < row;i++){
	  if(matA[i][column-out] + matB[i][column-out] == 2){
		  result[i][column-out] = 1;
	  }
	  else{
		  result[i][column-out] = matA[i][column-out] + matB[i][column-out];
	  }
  }

  return result;
}

int** NorMatrix(int **original,int **matA, int **matB, int row, int column,int out){
  int** result=calloc(row, sizeof(int*));
  for (int i = 0; i < row; i++) {
    result[i] = calloc(column, sizeof(int));
  }
  MatrixCopy(original, result, row, column);
  for(int i = 0;i < row;i++){
	  if(matA[i][column-out] + matB[i][column-out] == 2){
		  result[i][column-out] = 0;
	  }
	  else{
		  result[i][column-out] = matA[i][column-out] + matB[i][column-out];

		  if(result[i][column-out] == 1){
		  		  result[i][column-out] = 0;
		  	  }
		  	  else{
		  		  result[i][column-out] = 1;
		  	  }
	  }
  }

  return result;
}

int** XorMatrix(int **original,int **matA, int **matB, int row, int column, int out){
  int** result=calloc(row, sizeof(int*));
  for (int i = 0; i < row; i++) {
    result[i] = calloc(column, sizeof(int));
  }
  MatrixCopy(original, result, row, column);
  for(int i = 0;i < row;i++){
	  if(matA[i][column-out] + matB[i][column-out] == 2){
		  result[i][column-out] = 0;
	  }
	  else{
		  result[i][column-out] = matA[i][column-out] + matB[i][column-out];
	  }
  }

  return result;
}

int** InputMatrix(int **original, int row, int column, int input, int out){
  int** result = calloc(row, sizeof(int*));
  for (int i = 0; i < row; i++) {
    result[i] = calloc(column, sizeof(int));
  }
  MatrixCopy(original, result, row, column);
  for(int i = 0; i < row;i++){
	  if(result[i][input] == 1){
		  result[i][column-out] = 1;
	  }
  }
  return result;
}

int main(int argc, char* argv[]){
  int numberOfInputs;
  int numberOfOutputs;

  FILE *fp = fopen(argv[1],"r");

  //checks first line for number of inputs and makes inputs array
  fscanf(fp,"%*s %d\n",&numberOfInputs);
  char inputs[numberOfInputs][30];

  //populates inputs with variables
  for(int i = 0;i < numberOfInputs;i++){
    fscanf(fp,"%s",inputs[i]);
  }



  //checks second line for number of outputs and makes outputs array
  fscanf(fp,"%*s %d\n",&numberOfOutputs);
  char outputs[numberOfOutputs][30];

  //populates outputs with variables
   for(int i = 0;i < numberOfOutputs;i++){
     fscanf(fp,"%s",outputs[i]);
   }

   //make matrix for input and output and populate with all zeros
   int columns = numberOfInputs + numberOfOutputs;
   int rows = pow(2, numberOfInputs);

   //makes zero matrix of generic truth table
   int** matrix = calloc(rows, sizeof(int*));
   for (int i = 0; i < rows; i++) {
	      matrix[i] = calloc(columns, sizeof(int));
   }

   int powerCount = numberOfInputs;

   //make generic truth table
   for(int j = 0; j < columns-numberOfOutputs; j++){
	   int power = pow(2,powerCount - 1);
	    int count = 0;
	    int secondCount = 0;

   	   for(int i = 0;i < rows; i++){
   			   if(count == power){
   				   matrix[i][j] = 1;
   				   secondCount++;
   				   if(secondCount == power){
   					count = 0;
   					secondCount = 0;
   				   }
   			   }
   			   else{
   				   matrix[i][j] = 0;
   				   count++;
   			   }
   		   }
   	   powerCount--;
   }



   char operations[100][100];
   int i = 0;
   int numberOfLines;
   int** result;
   int** specialResult;
   int** matrix1;
   int** matrix2;
   int **arrayOfMatrices[20];
   int inputCount;
   int outputCount = 0;
   int firstInputFlag = 0;
   int countForMatrices = 0;
   int countOfTemps = 0;
   char temps[30][30];
   int out = 1;
   int specialOut = numberOfOutputs;
   int outTempsCount = 0;
   int notMemoryFlag = 0;
//gets line by line and stores in array
   while(fgets(operations[i],100, fp)){
       operations[i][strcspn(operations[i], "\r\n")] = '\0';
       i++;
     }
   numberOfLines = i;

   //get all temp values and put in temp array if any
   //POPULATING TEMP ARRAY STARTS
   for (int i = 0; i < numberOfLines; i++){
	   char temp1[200];
	   strcpy(temp1, operations[i]);
	   char * token1 = strtok(temp1," ");
	   while(token1 != NULL){
		  if(strcmp(token1,"NOT") == 0){
		  token1 = strtok(NULL, " ");
		  token1 = strtok(NULL, " ");

		  for(int i = outTempsCount; i < numberOfOutputs ;i++){
			  if(strcmp(token1,outputs[i]) == 0){
				  outTempsCount++;
				  break;
			  }
			  else if(strcmp(token1,outputs[i]) != 0){
			  strcpy(temps[countOfTemps],token1);
			  countOfTemps++;
			  break;
		  }
	  }
	  token1 = strtok(NULL, " ");
		  }
		  else if(strcmp(token1,"AND") == 0){
		  token1 = strtok(NULL, " ");
		  token1 = strtok(NULL, " ");
		  token1 = strtok(NULL, " ");
		  for(int i = outTempsCount; i < numberOfOutputs ;i++){
			  if(strcmp(token1,outputs[i]) == 0){
				  outTempsCount++;
				  break;
			  }
			  else if(strcmp(token1,outputs[i]) != 0){
			  strcpy(temps[countOfTemps],token1);
			  countOfTemps++;
			  break;
		  }
	  }
	  token1 = strtok(NULL, " ");
		  }
		  else if(strcmp(token1,"OR") == 0){
			  token1 = strtok(NULL, " ");
			  token1 = strtok(NULL, " ");
			  token1 = strtok(NULL, " ");
			  for(int i = outTempsCount; i < numberOfOutputs ;i++){
				  if(strcmp(token1,outputs[i]) == 0){
					  outTempsCount++;
					  break;
				  }
				  else if(strcmp(token1,outputs[i]) != 0){
    			  strcpy(temps[countOfTemps],token1);
    			  countOfTemps++;
    			  break;
    		  }
    	  }
    	  token1 = strtok(NULL, " ");
          }
          else if(strcmp(token1,"NAND") == 0){
        	  token1 = strtok(NULL, " ");
        	  token1 = strtok(NULL, " ");
        	  token1 = strtok(NULL, " ");
		  for(int i = outTempsCount; i < numberOfOutputs ;i++){
			  if(strcmp(token1,outputs[i]) == 0){
				  outTempsCount++;
				  break;
			  }
			  else if(strcmp(token1,outputs[i]) != 0){
			  strcpy(temps[countOfTemps],token1);
			  countOfTemps++;
			  break;
		  }
	  }
	  token1 = strtok(NULL, " ");
          }
          else if(strcmp(token1,"NOR") == 0){
        	  token1 = strtok(NULL, " ");
        	  token1 = strtok(NULL, " ");
        	  token1 = strtok(NULL, " ");
		  for(int i = outTempsCount; i < numberOfOutputs ;i++){
			  if(strcmp(token1,outputs[i]) == 0){
				  outTempsCount++;
				  break;
			  }
			  else if(strcmp(token1,outputs[i]) != 0){
			  strcpy(temps[countOfTemps],token1);
			  countOfTemps++;
			  break;
		  }
	  }
	  token1 = strtok(NULL, " ");
          }

          else if(strcmp(token1,"XOR") == 0){
        	  token1 = strtok(NULL, " ");
        	  token1 = strtok(NULL, " ");
        	  token1 = strtok(NULL, " ");
		  for(int i = outTempsCount; i < numberOfOutputs ;i++){
			  if(strcmp(token1,outputs[i]) == 0){
				  outTempsCount++;
				  break;
			  }
			  else if(strcmp(token1,outputs[i]) != 0){
			  strcpy(temps[countOfTemps],token1);
			  countOfTemps++;
			  break;
		  }
	  }
	  token1 = strtok(NULL, " ");

          }
	   }
   	 }
   //TEMP ARRAY DONE
     //goes through array of operations line by line and tokenizes doing operations

     //START OF OPERATIONS
     for (int i = 0; i < numberOfLines; i++){
       char temp[200];
       strcpy(temp, operations[i]);
       char * token = strtok(temp," ");

     //token parsing and doing commands
         while(token != NULL){

        	 //START OF NOT
           if(strcmp(token,"NOT") == 0){
        	   notMemoryFlag = 1;
    	   token = strtok(NULL, " ");
	   if(numberOfOutputs > 1){
        	  out = specialOut;
            }
   //go through inputs and match input found
          for(inputCount = 0; inputCount < numberOfInputs; inputCount++){
            //Found first input and get index

            if(strcmp(token, inputs[inputCount]) == 0){
              matrix1 = InputMatrix(matrix, rows,columns,inputCount,out);
              token = strtok(NULL, " ");
              break;
            }
            }
	   	   //checks if temp inputs present
	   if(inputCount == numberOfInputs){
		  for(int temp = 0; temp < countOfTemps;temp++){
			  //found first input
			  if(strcmp(token, temps[temp]) == 0){
				   matrix1 = arrayOfMatrices[temp];
				   token = strtok(NULL, " ");
				   break;
			   }
		  }
	   }
	   //go through outputs and match output found
	   int i;
for(i = outputCount; i < numberOfOutputs;i++){
	if(strcmp(token, outputs[i]) == 0){

		   if(numberOfOutputs > 1){
			   if(i > 0){
				   specialResult = NotMatrix(result,matrix1, rows,columns,inputCount,specialOut);
				   handleMatrixMemory(result,rows);
				   specialOut--;
				   outputCount++;
				   if(outputCount == numberOfOutputs){
					   result = specialResult;
				   }
			   	   break;
			   }
			   else{
				   result = NotMatrix(matrix,matrix1, rows,columns,inputCount,specialOut);
				   token = strtok(NULL, " ");
				   specialOut--;
				   outputCount++;
				   break;
			   }
		   }
		   else{
			   result = NotMatrix(matrix,matrix1, rows,columns,inputCount, out);
		   	   token = strtok(NULL, " ");
		   	   break;
		   }
	   	}
	   }

	   //it is temp output so puts in array of temp matrices
	   if(i == numberOfOutputs){
		   arrayOfMatrices[countForMatrices] = NotMatrix(matrix,matrix1, rows,columns,inputCount, out);
		   token = strtok(NULL, " ");
		   countForMatrices++;
	   }


           }
           //START OF AND
           else if(strcmp(token,"AND") == 0){
        	   token = strtok(NULL, " ");
        	   if(numberOfOutputs > 1){
	            	  out = specialOut;
 	            }

        	   //go through inputs and match input found
        	          for(inputCount = 0; inputCount < numberOfInputs; inputCount++){
        	            //Found first input and get index
        	            if(strcmp(token, inputs[inputCount]) == 0 && firstInputFlag == 0){
        	              matrix1 = InputMatrix(matrix, rows,columns,inputCount,out);
        	              token = strtok(NULL, " ");
        	              firstInputFlag++;
        	            }
        	              //Found second input, and get index
        	              if(strcmp(token, inputs[inputCount]) == 0 && firstInputFlag == 1){
        	            	  matrix2 = InputMatrix(matrix, rows,columns,inputCount, out);
        	            	  token = strtok(NULL, " ");
        	            	  firstInputFlag = 0;
        	            	  break;
        	             }
        	            }
           	   	   //checks if temp inputs present
        		   if(inputCount == numberOfInputs){
        			  for(int temp = 0; temp < countOfTemps;temp++){
        				  //found first input
        				  if(strcmp(token, temps[temp]) == 0 && firstInputFlag == 0){
        					   matrix1 = arrayOfMatrices[temp];
        					   firstInputFlag++;
        					   token = strtok(NULL, " ");
        				   }
        				  //found second
        				  if(strcmp(token, temps[temp]) == 0 && firstInputFlag == 0){
        					   matrix2 = arrayOfMatrices[temp];
        					   firstInputFlag = 0;
        					   token = strtok(NULL, " ");
        					   break;
        				   }
        			  }
        		   }
        		   //go through outputs and match output found
        		   int i;
    		for(i = outputCount; i < numberOfOutputs;i++){
    			if(strcmp(token, outputs[i]) == 0){
    				   if(numberOfOutputs > 1){
    					   if(i > 0){
    						   specialResult = AndMatrix(result,matrix1, matrix2, rows,columns,specialOut);
    						   handleMatrixMemory(result,rows);
    						   specialOut--;
    						   outputCount++;
    						   if(outputCount == numberOfOutputs){
    							   result = specialResult;
    						   }

    					   	   break;
    					   }
    					   else{
    						   result = AndMatrix(matrix,matrix1, matrix2, rows,columns,specialOut);
    						   token = strtok(NULL, " ");
    						   outputCount++;
    						   specialOut--;
    						   break;
    					   }
    				   }
    				   else{
    					   result = AndMatrix(matrix,matrix1, matrix2, rows,columns,out);
    				   	   token = strtok(NULL, " ");
    				   	   break;
    				   }
    			   	}
    			   }

    			   //it is temp output so puts in array of temp matrices
    			   if(i == numberOfOutputs){
    				   arrayOfMatrices[countForMatrices] = AndMatrix(matrix,matrix1, matrix2, rows,columns,out);
    				   token = strtok(NULL, " ");
    				   countForMatrices++;
    			   }

           }

           //START OF OR
           else if(strcmp(token,"OR") == 0){
        	   token = strtok(NULL, " ");
        	   if(numberOfOutputs > 1){
	            	  out = specialOut;
    	            }
    	   //go through inputs and match input found
    	          for(inputCount = 0; inputCount < numberOfInputs; inputCount++){
    	            //Found first input and get index
    	            if(strcmp(token, inputs[inputCount]) == 0 && firstInputFlag == 0){
    	              matrix1 = InputMatrix(matrix, rows,columns,inputCount,out);
    	              token = strtok(NULL, " ");
    	              firstInputFlag++;
    	            }
    	              //Found second input, and get index
    	              if(strcmp(token, inputs[inputCount]) == 0 && firstInputFlag == 1){
    	            	  matrix2 = InputMatrix(matrix, rows,columns,inputCount,out);
    	            	  token = strtok(NULL, " ");
    	            	  firstInputFlag = 0;
    	            	  break;
    	             }
    	            }
       	   	   //checks if temp inputs present
    		   if(inputCount == numberOfInputs){
    			  for(int temp = 0; temp < countOfTemps;temp++){
    				  //found first input
    				  if(strcmp(token, temps[temp]) == 0 && firstInputFlag == 0){
    					   matrix1 = arrayOfMatrices[temp];
    					   firstInputFlag++;
    					   token = strtok(NULL, " ");
    				   }
    				  //found second
    				  if(strcmp(token, temps[temp]) == 0 && firstInputFlag == 1){
    					   matrix2 = arrayOfMatrices[temp];
    					   firstInputFlag = 0;
    					   token = strtok(NULL, " ");
    					   break;
    				   }
    			  }
    		   }
    		   //go through outputs and match output found
    		   int i;
		for(i = outputCount; i < numberOfOutputs;i++){
			if(strcmp(token, outputs[i]) == 0){
				   if(numberOfOutputs > 1){
					   if(i > 0){
						   specialResult = OrMatrix(result,matrix1, matrix2, rows,columns,specialOut);
						   handleMatrixMemory(result,rows);
						   specialOut--;
						   outputCount++;
						   if(outputCount == numberOfOutputs){
							   result = specialResult;
						   }
					   	   break;
					   }
					   else{
						   result = OrMatrix(matrix,matrix1, matrix2, rows,columns,specialOut);
						   token = strtok(NULL, " ");
						   specialOut--;
						   outputCount++;
						   break;
					   }
				   }
				   else{
					   result = OrMatrix(matrix,matrix1, matrix2, rows,columns,out);
				   	   token = strtok(NULL, " ");
				   	   break;
				   }
			   	}
			   }

			   //it is temp output so puts in array of temp matrices
			   if(i == numberOfOutputs){
				   arrayOfMatrices[countForMatrices] = OrMatrix(matrix,matrix1, matrix2, rows,columns,out);
				   token = strtok(NULL, " ");
				   countForMatrices++;
			   }
           }

           //START OF NAND
           else if(strcmp(token,"NAND") == 0){
    	   token = strtok(NULL, " ");
	   if(numberOfOutputs > 1){
        	  out = specialOut;
            }
   //go through inputs and match input found
          for(inputCount = 0; inputCount < numberOfInputs; inputCount++){
            //Found first input and get index

            if(strcmp(token, inputs[inputCount]) == 0 && firstInputFlag == 0){
              matrix1 = InputMatrix(matrix, rows,columns,inputCount,out);
              token = strtok(NULL, " ");
              firstInputFlag++;
            }
              //Found second input, and get index
              if(strcmp(token, inputs[inputCount]) == 0 && firstInputFlag == 1){
            	  matrix2 = InputMatrix(matrix, rows,columns,inputCount,out);
            	  token = strtok(NULL, " ");
            	  firstInputFlag = 0;
            	  break;
             }
            }
	   	   //checks if temp inputs present
	   if(inputCount == numberOfInputs){
		  for(int temp = 0; temp < countOfTemps;temp++){
			  //found first input
			  if(strcmp(token, temps[temp]) == 0 && firstInputFlag == 0){
				   matrix1 = arrayOfMatrices[temp];
				   firstInputFlag++;
				   token = strtok(NULL, " ");
			   }
			  //found second
			  if(strcmp(token, temps[temp]) == 0 && firstInputFlag == 1){
				   matrix2 = arrayOfMatrices[temp];
				   firstInputFlag = 0;
				   token = strtok(NULL, " ");
				   break;
			   }
		  }
	   }
	   //go through outputs and match output found
	   int i;
for(i = outputCount; i < numberOfOutputs;i++){
	if(strcmp(token, outputs[i]) == 0){
		   if(numberOfOutputs > 1){
			   if(i > 0){
				   specialResult = NandMatrix(result,matrix1, matrix2, rows,columns,specialOut);
				   handleMatrixMemory(result,rows);
				   specialOut--;
				   outputCount++;
				   if(outputCount == numberOfOutputs){
					   result = specialResult;
				   }
			   	   break;
			   }
			   else{
				   result = NandMatrix(matrix,matrix1, matrix2, rows,columns,specialOut);
				   token = strtok(NULL, " ");
				   specialOut--;
				   outputCount++;
				   break;
			   }
		   }
		   else{
			   result = NandMatrix(matrix,matrix1, matrix2, rows,columns,out);
		   	   token = strtok(NULL, " ");
		   	   break;
		   }
	   	}
	   }

	   //it is temp output so puts in array of temp matrices
	   if(i == numberOfOutputs){
		   arrayOfMatrices[countForMatrices] = NandMatrix(matrix,matrix1, matrix2, rows,columns,out);
		   token = strtok(NULL, " ");
		   countForMatrices++;
	   }

           }
           //START OF NOR

           else if(strcmp(token,"NOR") == 0){
    	   token = strtok(NULL, " ");
    	   if(numberOfOutputs > 1){
        	  out = specialOut;
            }
    	   //go through inputs and match input found
          for(inputCount = 0; inputCount < numberOfInputs; inputCount++){
            //Found first input and get index

            if(strcmp(token, inputs[inputCount]) == 0 && firstInputFlag == 0){
              matrix1 = InputMatrix(matrix, rows,columns,inputCount,out);
              token = strtok(NULL, " ");
              firstInputFlag++;
            }
              //Found second input, and get index
              if(strcmp(token, inputs[inputCount]) == 0 && firstInputFlag == 1){
            	  matrix2 = InputMatrix(matrix, rows,columns,inputCount,out);
            	  token = strtok(NULL, " ");
            	  firstInputFlag = 0;
            	  break;
             }
            }
	   	   //checks if temp inputs present
	   if(inputCount == numberOfInputs){
		  for(int temp = 0; temp < countOfTemps;temp++){
			  //found first input
			  if(strcmp(token, temps[temp]) == 0 && firstInputFlag == 0){
				   matrix1 = arrayOfMatrices[temp];
				   firstInputFlag++;
				   token = strtok(NULL, " ");
			   }
			  //found second
			  if(strcmp(token, temps[temp]) == 0 && firstInputFlag == 1){
				   matrix2 = arrayOfMatrices[temp];
				   firstInputFlag = 0;
				   token = strtok(NULL, " ");
				   break;
			   }
		  }
	   }
	   //go through outputs and match output found
	   int i;
for(i = outputCount; i < numberOfOutputs;i++){
	if(strcmp(token, outputs[i]) == 0){
		   if(numberOfOutputs > 1){
			   if(i > 0){
				   specialResult = NorMatrix(result,matrix1, matrix2, rows,columns,specialOut);
				   handleMatrixMemory(result,rows);
				   specialOut--;
				   outputCount++;
				   if(outputCount == numberOfOutputs){
					   result = specialResult;
				   }
			   	   break;
			   }
			   else{
				   result = NorMatrix(matrix,matrix1, matrix2, rows,columns,specialOut);
				   token = strtok(NULL, " ");
				   specialOut--;
				   outputCount++;
				   break;
			   }
		   }
		   else{
			   result = NorMatrix(matrix,matrix1, matrix2, rows,columns,out);
		   	   token = strtok(NULL, " ");
		   	   break;
		   }
	   	}
	   }

	   //it is temp output so puts in array of temp matrices
	   if(i == numberOfOutputs){
		   arrayOfMatrices[countForMatrices] = NorMatrix(matrix,matrix1, matrix2, rows,columns,out);
		   token = strtok(NULL, " ");
		   countForMatrices++;
	   }


           }
           //START OF XOR
           else if(strcmp(token,"XOR") == 0){
        	   token = strtok(NULL, " ");
    	   if(numberOfOutputs > 1){
            	  out = specialOut;
	            }
	   //go through inputs and match input found
	          for(inputCount = 0; inputCount < numberOfInputs; inputCount++){
	            //Found first input and get index

	            if(strcmp(token, inputs[inputCount]) == 0 && firstInputFlag == 0){
	              matrix1 = InputMatrix(matrix, rows,columns,inputCount,out);
	              token = strtok(NULL, " ");
	              firstInputFlag++;
	            }
	              //Found second input, and get index
	              if(strcmp(token, inputs[inputCount]) == 0 && firstInputFlag == 1){
	            	  matrix2 = InputMatrix(matrix, rows,columns,inputCount,out);
	            	  token = strtok(NULL, " ");
	            	  firstInputFlag = 0;
	            	  break;
	             }
	            }
   	   	   //checks if temp inputs present
		   if(inputCount == numberOfInputs){
			  for(int temp = 0; temp < countOfTemps;temp++){
				  //found first input
				  if(strcmp(token, temps[temp]) == 0 && firstInputFlag == 0){
					   matrix1 = arrayOfMatrices[temp];
					   firstInputFlag++;
					   token = strtok(NULL, " ");
				   }
				  //found second
				  if(strcmp(token, temps[temp]) == 0 && firstInputFlag == 1){
					   matrix2 = arrayOfMatrices[temp];
					   firstInputFlag = 0;
					   token = strtok(NULL, " ");
					   break;
				   }
			  }
		   }
		   //go through outputs and match output found
		   int i;
	for(i = outputCount; i < numberOfOutputs;i++){
		if(strcmp(token, outputs[i]) == 0){
			   if(numberOfOutputs > 1){
				   if(i > 0){
					   specialResult = XorMatrix(result,matrix1, matrix2, rows,columns,specialOut);
					   handleMatrixMemory(result,rows);
					   specialOut--;
					   outputCount++;
					   if(outputCount == numberOfOutputs){
						   result = specialResult;
					   }
				   	   break;
				   }
				   else{
					   result = XorMatrix(matrix,matrix1, matrix2, rows,columns,specialOut);
					   token = strtok(NULL, " ");
					   specialOut--;
					   outputCount++;
					   break;
				   }
			   }
			   else{
				   result = XorMatrix(matrix,matrix1, matrix2, rows,columns,out);
			   	   token = strtok(NULL, " ");
			   	   break;
			   }
		   	}
		   }

		   //it is temp output so puts in array of temp matrices
		   if(i == numberOfOutputs){
			   arrayOfMatrices[countForMatrices] = XorMatrix(matrix,matrix1, matrix2, rows,columns,out);
			   token = strtok(NULL, " ");
			   countForMatrices++;
		   }
           }
           else{
        	   break;
           }

           handleMatrixMemory(matrix1,rows);
           if(notMemoryFlag > 0){
        	   notMemoryFlag = 0;
           }
           else{
        	   handleMatrixMemory(matrix2,rows);
           }

     }
  }

    printMatrix(result,rows, columns);
    handleMatrixMemory(matrix,rows);
    handleMatrixMemory(result,rows);

}
