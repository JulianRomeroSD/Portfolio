/*
 ============================================================================
 Name        : third.c
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

  int* binary = decToBinary(x);

  for(int i = 0, j = 15; i < 16; i++, j--){
    if(i == j - 1){
      printf("Is-Palindrome");
      break;
    }
    else{

      if(binary[i] == binary[j]){
        continue;
      }
      else{
        printf("Not-Palindrome");
        break;
      }
    }
  }
  free(binary);
  return 0;
}
