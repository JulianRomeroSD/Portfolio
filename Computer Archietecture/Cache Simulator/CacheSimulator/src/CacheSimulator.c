#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include<math.h>

char* hexToBinary(char* hex){
  char* binary = malloc(50*sizeof(char));
  for(int i=2; hex[i]!='\0'; i++){
    switch(hex[i]){
    case '0':
      strcat(binary, "0000");
      break;
    case '1':
      strcat(binary, "0001");
      break;
    case '2':
      strcat(binary, "0010");
      break;
    case '3':
      strcat(binary, "0011");
      break;
    case '4':
      strcat(binary, "0100");
      break;
    case '5':
      strcat(binary, "0101");
      break;
    case '6':
      strcat(binary, "0110");
      break;
    case '7':
      strcat(binary, "0111");
      break;
    case '8':
      strcat(binary, "1000");
      break;
    case '9':
      strcat(binary, "1001");
      break;
    case 'a':
    case 'A':
      strcat(binary, "1010");
      break;
    case 'b':
    case 'B':
      strcat(binary, "1011");
      break;
    case 'c':
    case 'C':
      strcat(binary, "1100");
      break;
    case 'd':
    case 'D':
      strcat(binary, "1101");
      break;
    case 'e':
    case 'E':
      strcat(binary, "1110");
      break;
    case 'f':
    case 'F':
      strcat(binary, "1111");
      break;
    default:
      printf("Invalid");
    }
  }
  if(strlen(binary) == 48){
    return binary;
  }
  for(int i = strlen(binary);i < 48 ;i++){
    strcat(binary,"0");
  }
  return binary;
}

int isPowerOfTwo(int n){
  if (n == 0){
    return 0;
  }
  while (n != 1){
    if (n % 2 != 0){
      return 0;
    }
    n = n / 2;
  }
  return 1;
}

int PowerOfTwo(int n){
  int power = 1;
  while (n != 2){
    n = n / 2;
    power++;
  }
  return power;
}


int binaryToDec(char *binary, int size){
  int decimal = 0;
  int x = size -1;


  for(int i = 0; i < size; i++){
    int num = (int) binary[i];

    decimal += num * pow(2, x);
    x--;
  }
  return decimal;
}

int main(int argc, char* argv[]){
  FILE *fp = fopen(argv[5],"r");
  int read = 0;
  int write = 0;
  int hit = 0;
  int miss = 0;


  //determining bits and sets
  int tag;
  int set;
  int block;
  int numberOfSets;
  int blocksPerSet;
  int archietecture = 48;

  //inputs
  int cacheSize = atoi(argv[1]);
  char *associativity =  argv[2];
  char *replacePolicy = argv[3];
  int blockSize = atoi(argv[4]);




  if(isPowerOfTwo(cacheSize) == 0){
    printf("error\n");
    return 0;
  }

  if(isPowerOfTwo(blockSize) == 0){
    printf("error\n");
    return 0;
  }

  //gets bits of block index and sets up to get set bits
  block = PowerOfTwo(blockSize);
  int cachePerLine = (PowerOfTwo(cacheSize) - block);



  //check associativity input and get set bits and tag bits
  if(strcmp(associativity,"direct") == 0){
    blocksPerSet = 1;
    set = pow(2,cachePerLine)/blocksPerSet;
    set = PowerOfTwo(set);
    numberOfSets = pow(2, set);
    tag = archietecture - set - block;
  }

  else if(strcmp(associativity,"assoc") == 0){
    blocksPerSet = cacheSize/blockSize;
    set = pow(2,cachePerLine)/blocksPerSet;
    set = PowerOfTwo(set);
    numberOfSets = pow(2, set);
    tag = archietecture - set - block;
  }

  else if (associativity[5] == ':'){
    char c = associativity[6];
    int num = c - '0';
    if(isPowerOfTwo(num) == 0){
      printf("error\n");
      return 0;
    }

    blocksPerSet = num;
    set = pow(2,cachePerLine)/blocksPerSet;
    set = PowerOfTwo(set);
    numberOfSets = pow(2, set);
    tag = archietecture - set - block;

  }

  else{
    printf("error\n");
    return 0;
  }

  //check replace policy
  if(strcmp(replacePolicy,"lru") == 0){

  }
  else if (strcmp(replacePolicy,"fifo") == 0){

  }

  else{
    printf("error\n");
    return 0;

  }

  char comp[20];
  char memory[20];
  char operation;

  char cache[numberOfSets][blocksPerSet][50];
  for(int i = 0; i < numberOfSets;i++){
    for(int j = 0; j < blocksPerSet;j++){
      for(int k = 0; k < 50;k++){
	cache[i][j][k] = '\0';
      }
    }
  }

  char* tagArray = malloc(tag*sizeof(char));
  char* setArray = malloc(set*sizeof(char));
  char* binary = hexToBinary(memory);

  while(1){

    int n = fscanf(fp,"%s %c %s\n",comp, &operation, memory);

    if(n != 3){
      break;
    }




    //make tag and set array
    for(int i = 0; i < strlen(binary); i++){
      if(i < tag){
	tagArray[i] = binary[i];
      }
      else if(i < tag + set){
	setArray[i] = binary[i];
      }
      else{
	break;
      }
    }
    int number = binaryToDec(setArray,set);

    if(operation == 'R'){
      int i;
      for(i = 0; i < blocksPerSet;i++){
	if(cache[number][i][0] == '\0'){
	  strcpy(cache[number][i],tagArray);
	  miss++;
	  read++;
	  break;
	}

	if(strcmp(cache[number][i], tagArray) == 0){
	  hit++;
	  break;
	}
	else{
	  continue;

	}
      }
      if(i == blocksPerSet){
	if(strcmp(replacePolicy,"lru") == 0){
	  int num;
	  int i;
	  for(i = 0; i < blocksPerSet;i++){
	    if(strcmp(cache[number][i],cache[number][blocksPerSet-1]) == 1){
	      num = i;
	      break;
	    }
	    else{
	      continue;
	    }
	  }
	  if(i == blocksPerSet){
	    num = blocksPerSet-1;
	  }

	  strcpy(cache[number][num],tagArray);
	  miss++;
	}
	else if (strcmp(replacePolicy,"fifo") == 0){
	  strcpy(cache[number][0],tagArray);
	  read++;
	  miss++;
	}
      }
    }
    else{
      int i;
      write++;
      for(i = 0; i < blocksPerSet;i++){
	if(cache[number][i][0] == '\0'){
	  strcpy(cache[number][i],tagArray);
	  miss++;
	  read++;
	  break;
	}

	if(strcmp(cache[number][i], tagArray) == 0){
	  hit++;
	  break;
	}
	else{
	  continue;

	}
      }
      if(i == blocksPerSet){

	if(strcmp(replacePolicy,"lru") == 0){
	  int num;
	  int i;
	  for(i = 0; i < blocksPerSet;i++){
	    if(strcmp(cache[number][i],cache[number][blocksPerSet-1]) == 1){
	      num = i;
	      break;
	    }
	    else{
	      continue;
	    }
	  }
	  if(i == blocksPerSet){
	    num = blocksPerSet-1;
	  }

	  strcpy(cache[number][num],tagArray);
	  read++;
	  miss++;
	}
	else if (strcmp(replacePolicy,"fifo") == 0){
	  strcpy(cache[number][0],tagArray);
	  read++;
	  miss++;

	}
      }

    }
  }
  free(tagArray);
  free(setArray);
  free(binary);

  printf("Memory reads: %d\n", read);
  printf("Memory writes: %d\n",write);
  printf("Cache hits: %d\n", hit);
  printf("Cache misses: %d\n", miss);

}
