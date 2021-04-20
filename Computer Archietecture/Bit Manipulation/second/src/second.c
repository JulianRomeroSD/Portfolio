/*
 ============================================================================
 Name        : second.c
 Author      : Julian Romero
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
void printArray(int* mat, int row)
{
  for (int i = 0; i < row; i++){
    printf("%d\t", mat[i]);
  }
}

int decToBinarySize(unsigned short x){
  int* binary = malloc(16 * sizeof(int));
  int count = 0;
  while (x > 0){
    binary[count] = x % 2;
    x = x/2;
    count++;
  }

  free(binary);
  return count;
}

int* decToBinary(unsigned short x){
  int* binary = malloc(16 * sizeof(int));
  int length = 15;

  while (x > 0){
    binary[length] = x % 2;
    x = x/2;
    length--;
  }

  for (int i = length; i >= 0; i--){
    binary[i] = 0;
  }
  return binary;
}

int main(int argc, char** argv)
{

  int input;
  input = atoi(argv[1]);
  unsigned short x = (unsigned short)input;
  int count = 0;
  int numberOfPairs = 0;
  int* binary = decToBinary(x);
  int visited [16];

  //populate visited array
  for(int i = 0; i < 16; i++){
	  visited[i] = 0;
  }

  //get number of pairs
  for(int i = 0, j = 1; j < 16; i++,j++){
      if(binary[i] == 1 && binary[j] == 1 && visited[i] == 0) {
        numberOfPairs++;
        visited[j] = 1;
      }
   }

   //get count of 1's in binary
  for(int i = 0; i < 16; i++){
    if(binary[i] == 1){
      count++;
    }
  }

  free(binary);

  if(count % 2 == 0){
     printf("Even-Parity\t%d\n", numberOfPairs);
   }
   else{
     printf("Odd-Parity\t%d\n", numberOfPairs);
   }

  return 0;
}
