/*
 ============================================================================
 Name        : sortArray.c
 Author      : Julian Romero
 Version     :
 Copyright   : Your copyright notice
 Description : sorts an inputed array
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

void ascendingInsertionSort(int array[], int n)
{
  int i, target, j;

  for (i = 1; i < n; i++) {
    target = array[i];
    j = i - 1;

    while (j >= 0 && array[j] > target) {
      array[j + 1] = array[j];
      j = j - 1;
    }
    array[j + 1] = target;
  }
}

void descendingInsertionSort(int array[], int n)
{
  int i, target, j;

  for (i = 1; i < n; i++) {
    target = array[i];
    j = i - 1;

    while (j >= 0 && array[j] < target) {
      array[j + 1] = array[j];
      j = j - 1;
    }
    array[j + 1] = target;
  }
}
int main(int argc, char* argv[]){

  int n;

  FILE *fp = fopen(argv[1],"r");

  if(fp == NULL){
    return 0;
  }
  //reads first line of size of array
  fscanf(fp,"%d\n",&n);

  int array[n];

  int i;

  int countOdd = 0;

  int countEven = 0;

  for(i = 0;i < n; i++)
     {
       fscanf(fp, "%d\t", &array[i]);
       if(array[i] % 2 == 0){
         countEven++;
       }
       else{
         countOdd++;
       }
     }
   int a;
   int evenArray[countEven];
   int oddArray[countOdd];
   //input evens and odds in respective arrays
   int evenCount = 0;
   int oddCount = 0;
   for (a = 0; a < n; a++ ) {
     if(array[a] % 2 == 0){
       evenArray[evenCount] = array[a];
       evenCount++;
     }
     else{
       oddArray[oddCount] = array[a];
       oddCount++;
     }
   }
   int final[n];
   if(oddCount != 0 && evenCount ==0){
     descendingInsertionSort(oddArray,countOdd);
     int j;
     for ( j = 0; j < n; j++ ) {
       final[ j ] = oddArray[j];
     }

   }
   else if(oddCount == 0 && evenCount !=0){
     ascendingInsertionSort(evenArray,countEven);
     int j;

     for ( j = 0; j < n; j++ ) {

       final[ j ] = evenArray[j];
     }
   }

   else{

     ascendingInsertionSort(evenArray,countEven);

     descendingInsertionSort(oddArray,countOdd);

     int j;
     int z = 0;

     for ( j = 0; j < n; j++ ) {
       if(j < countEven){
         final[ j ] = evenArray[j];

       }
       else{
         final[ j] = oddArray[z];
         z++;
       }
     }
   }
   int l;

   for (l = 0; l < n; l++ ) {
     printf("%d\t", final[l] );
   }

   return final;
 }


