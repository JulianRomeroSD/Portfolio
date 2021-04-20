/*
 ============================================================================
 Name        : linkedList.c
 Author      : Julian Romero
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */
#include <stdio.h>
#include <stdlib.h>


struct Node{
  int data;
  struct Node *next;
};
void addToFront(struct Node** head, int data){
  struct Node* newNode = (struct Node*) malloc(sizeof(struct Node));
  newNode->data = data;
  newNode->next = *head;
  *head = newNode;
}
void insertAfter(struct Node* prev,int data){
  if(prev == NULL){
    return;
  }
  struct Node* newNode = (struct Node*) malloc(sizeof(struct Node));
  newNode->data = data;
  newNode->next = prev->next;
  prev->next = newNode;
}
struct Node * searchSpot(struct Node* head,int x){
  struct Node* ptr = head;
  struct Node* prev = NULL;
  while(ptr!=NULL){
    if(ptr->data  <= x){
      prev = ptr;
      ptr = ptr->next;
    }
    else{
      return prev;
    }
  }
  return prev;
}
void handleMemory(struct Node* head) {
  struct Node* Head = head;
  struct Node* temp = NULL;

  while (Head != NULL) {
    temp = Head;
    Head = Head->next;
    free(temp);
  }
}
void deleteNode(struct Node **head, int data)
{
  struct Node* ptr = *head,*prev;

  if (ptr!=NULL && ptr->data == data)
    {
      *head = ptr->next;
      free(ptr);
      return;
    }
  while (ptr != NULL && ptr->data != data)
    {
      prev = ptr;
      ptr = ptr->next;
    }
  if (ptr == NULL){
    return;
  }
  prev->next = ptr->next;
  free(ptr);
}
void removeDuplicates(struct Node* head)
{
  struct Node* ptr = head;
  struct Node* node;

  if (ptr == NULL)
    return;

  while (ptr->next != NULL)
    {
      if (ptr->data == ptr->next->data)
        {
          node = ptr->next->next;
          free(ptr->next);
          ptr->next = node;
        }
      else
        {
          ptr = ptr->next;
        }
    }
}
void print(struct Node* n)
{
  while (n != NULL) {
    printf("%d\t", n->data);
    n = n->next;
  }
}
void printCount(struct Node* n)
{
  int count = 0;
  while (n != NULL) {
    count++;
    n = n->next;
  }
  printf("%d\n", count);
}
int main(int argc, char* argv[]) {

  struct Node* head = NULL;
  FILE *fp = fopen(argv[1],"r");
  if(fp == NULL){
    printf("error");
    return 0;
  }
  char c;
  int d;
  //insert and delete first
  while(1){
    int n = fscanf(fp,"%c\t%d\n",&c,&d);
    if(n!=2){
      break;
    }
    //when list empty
    if(head==NULL){
      if(c == 'i'){
        addToFront(&head,d);
      }
      else{
        continue;
      }
    }
    //when not
    else if(head !=NULL){
      if(c == 'i'){
        struct Node* searchedNode  =searchSpot(head,d);
        if(searchedNode == NULL){
          addToFront(&head,d);
        }
        else{
          insertAfter(searchedNode,d);
        }
      }
      else{
        deleteNode(&head,d);

      }
    }
  }
  printCount(head);
  removeDuplicates(head);
  print(head);
  handleMemory(head);
  fclose(fp);
  return 0;
}
