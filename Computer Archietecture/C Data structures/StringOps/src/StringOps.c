/*
 ============================================================================
 Name        : StringOps.c
 Author      : Julian Romero
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */
#include <stdio.h>
#include <string.h>
int main(int argc, char **argv) {
  int i;
  int j;
  char final;
  for(i =1;i<argc;i++){
    for(j=0;j<strlen(argv[i]);j++){
      final = argv[i][j];
      if(final =='e'|| final == 'o'|| final =='a'|| final =='i'||final == 'u'||final=='A'||final == 'E'||final =='I'||final=='O'||final=='U'){
      printf("%c",final);
      }
  }
  }
}
