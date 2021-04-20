/*
 ============================================================================
 Name        : first.c
 Author      : Julian Romero
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

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

unsigned short binaryToDec(int*binary, int size){
	unsigned short decimal = 0;
	int x = size -1;

	for(int i = 0; i < size; i++){
		decimal += binary[i] * pow(2, x);
		x--;
	}
	return decimal;
}

unsigned short set(unsigned short x, int n, int v){
	int* binary = decToBinary(x);
	int size = 16;

	int length = size -1;
	n = length - n;

	binary[n] = v;
	int newDecimal = binaryToDec(binary, size);
	free(binary);
	return newDecimal;
}

unsigned short comp(unsigned short x, int n){
	int* binary = decToBinary(x);
	int size = 16;

	int length = size -1;
	n = length - n;
	if (binary[n] == 0){
		binary[n] = 1;
	}
	else{
		binary[n] = 0;
	}

	int newDecimal = binaryToDec(binary, size);
	free(binary);

	return newDecimal;
}

int get(unsigned short x, int n){
	int *binary = decToBinary(x);
	int size = 16;

	int length = size -1;
	n = length - n;
	int value = binary[n];
	free(binary);
	return value;
}

int main(int argc, char* argv[])
{
	unsigned short x;
	char operation[5];
	int arg1;
	int arg2;
	FILE *fp = fopen(argv[1],"r");

	  //reads first line
	  fscanf(fp,"%hu\n",&x);

	  while(1){
	    int n = fscanf(fp,"%s\t%d\t\%d\n",operation, &arg1, &arg2);
	    if(n != 3){
	      break;
	    }
	    if(operation[0] == 'g'){
	    	int number = get(x, arg1);
	    	printf("%d\n", number);
	    }
	    else if(operation[0] == 'c'){
	    	x = comp(x, arg1);
	    	printf("%u\n", x);
	    }
	    else{
	    	x = set(x, arg1, arg2);
	    	printf("%u\n", x);
	    }
	  }
}
