/*
 ============================================================================
 Name        : hashTable.c
 Author      : Julian Romero
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

struct hashTable{
  int key;
  int value;
  struct hash *next;
};
int search(int number,struct hashTable table){
	struct hashTable *list = table[hashCode(number)];
	while(list!=NULL){
		if(list->value==number){
			return 1;
		}
		list = list->next;
	}
	return 0;
}
void insert(int number, struct hashTable table,int *duplicate, int *collision){
	int hashIndex = hashCode(number);
	struct hashTable *list = table[hashIndex];
	if(list == NULL){
		list[hashIndex].value = number;
		return;
	}
	while(list != NULL){
		if(list->value == number){
			duplicate++;
			return;
		}
		list = list->next;
	}
	collision++;
	struct hashTable *hashTable = malloc(sizeof(struct hashTable));
	hashTable->key = hashIndex;
	hashTable->value = hashIndex;
	hashTable->next = hashIndex;
	list = hashTable;
	hashTable[hashIndex] = list;

}

int hashCode(int key){
 int capacity = 10000;
 int hashCode = key % capacity;
 if(hashCode<0){
	 return capacity+ hashCode;
 }
 return hashCode;
}

void handleMemory(struct hashTable table)
{
        struct Node* temp;
        struct Node* crawler;
        for(int n = 0; n <10000; n++)
        {
            if (table[n] != NULL)
            {
                crawler = table[n];
                while (crawler != NULL)
                {
                    temp = crawler->next;
                    free(crawler);
                    crawler = temp;

                }
                temp = crawler;
                free(temp);
            }
        }

}



int main(int argc, char* argv[]) {
	int capacity = 10000;
	struct hashTable *table[capacity];
	int duplicate = 0;
	int collision = 0;

  FILE *fp = fopen(argv[1],"r");
  if(fp == NULL){
    printf("error");
    return 0;
  }
  char c;
  int d;
  int searchCount = 0;
  //insert and delete first
  while(1){
    int n = fscanf(fp,"%c\t%d\n",&c,&d);
    if(n!=2){
      break;
    }
    //when list empty

      if(c == 'i'){
    	  insert(d,&duplicate,&collision);
      }
      else{
    	  if(search(d)==1){
        	searchCount++;
        }
    	  else{
    		  continue;
    	  }
      }

      }
  printf("%d\n",collision+duplicate);
  printf("%d",searchCount);
  handleMemory(table);
  fclose(fp);

}
