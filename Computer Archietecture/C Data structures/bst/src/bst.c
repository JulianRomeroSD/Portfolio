/*
 ============================================================================
 Name        : bst.c
 Author      : Julian Romero
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
struct Node{
  int key;
  struct Node *left, *right;
};
void inorderTraversal(struct Node *root){
  if(root != NULL){
    inorderTraversal(root->left);
    printf("%d\t",root->key);
    inorderTraversal(root->right);
  }
}
struct Node *newNode(int key)
{
  struct Node *newNode =  (struct Node *)malloc(sizeof(struct Node));
  newNode->key = key;
  newNode->left = newNode->right = NULL;
  return newNode;
}

struct Node* insert(struct Node* Node, int key)
{
  if (Node == NULL){
    return newNode(key);
  }

  if (key < Node->key){
    Node->left  = insert(Node->left, key);
  }
  else if (key > Node->key){
    Node->right = insert(Node->right, key);
  }
  return Node;
}


void handleMemory(struct Node* root) {

  if (root == NULL) {
    return;
  }
  handleMemory(root->left);
  handleMemory(root->right);
  free(root);
}
int main(int argc, char* argv[]) {
  FILE *fp = fopen(argv[1],"r");
  struct Node *root = NULL;
  if(fp == NULL){
    printf("error");
    return 0;
  }
  char c;
  int d;
  while(1){
    int n = fscanf(fp,"%c\t%d\n",&c,&d);
    if(n!=2){
      break;
    }
    if(root == NULL){
     root = insert(root,d);
    }
    else{
      insert(root,d);
    }
  }
  inorderTraversal(root);
  handleMemory(root);
  fclose(fp);
  return 0;
}
