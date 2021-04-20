/*
 ============================================================================
 Name        : AssemblyInterpreter.c
 Author      : Julian Romero
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//arithmetic
int add(int x, int y){
	return y = y + x;
}

int sub(int x, int y){
	return y = y - x;
}

int mul(int x, int y){
	return y = y * x;
}

int divide(int x, int y){
	return y = x / y;
}

//moving data
int mov(int x, int y){
	return y = x;
}
//jumps

int jmp(int L){
	return L;
}

int je(int L,int x, int y){
	if(x == y){
		return jmp(L);
	}
	return -1;
}

int jne(int L,int x, int y){
	if(x != y){
		return jmp(L);
	}
	return -1;
}

int jg(int L,int x, int y){
	if(x > y){
		return jmp(L);
	}
	return -1;
}

int jge(int L,int x, int y){
	if(x >= y){
		return jmp(L);
	}
	return -1;
}

int jl(int L,int x, int y){
	if(x < y){
		return jmp(L);
	}
	return -1;
}

int jle(int L, int x, int y){
	if(x <= y){
		return jmp(L);
	}
	return -1;
}

//Input and output
int read(int x, char* token){
	if(strcmp(token,"ax") == 0){
		scanf("%d", &x);
		return x;
	}
	else if(strcmp(token,"bx") == 0){
		scanf("%d", &x);
		return x;
	}
	else if(strcmp(token,"cx") == 0){
		scanf("%d", &x);
		return x;
	}
	else if(strcmp(token,"dx") == 0){
		scanf("%d", &x);
		return x;
	}
	return x;
}

void print(int x){
	printf("%d", x);
}



int main(int argc, char* argv[]){
	short ax;
	short bx;
	short cx;
	short dx;

	int row = 100;
	int columns = 10;
	char operation[row][columns];
	FILE *fp = fopen(argv[1],"r");

	int numberOfLines = 0;
	int i = 0;

	//populates matrix of assembly code
	while(fgets(operation[i],columns, fp)){
		operation[i][strcspn(operation[i], "\r\n")] = '\0';
		i++;
	}
	numberOfLines = i;


	//goes through array and tokenizes doing commands
	for (int i = 0; i < numberOfLines; i++){
		char temp[30];
		strcpy(temp, operation[i]);
		char * token = strtok_r(temp," ");
		while(token != NULL){
			  if(strcmp(token,"read") == 0){
				  token = strtok(NULL, " ");

				  if(strcmp(token,"ax") == 0){
					  ax = read(ax,token);
				  }
				  else if(strcmp(token,"bx") == 0){
					  bx = read(bx,token);
				  }
				  else if(strcmp(token,"cx") == 0){
					  cx = read(cx,token);
				  }
				  else if(strcmp(token,"dx") == 0){
					  dx = read(dx,token);
				  }
			  }
			  else if(strcmp(token,"print") == 0){
				  token = strtok(NULL, " ");

				 if(strcmp(token,"ax") == 0){
					 print(ax);
				 }
				 else if(strcmp(token,"bx") == 0){
					 print(bx);
				 }
				 else if(strcmp(token,"cx") == 0){
					 print(cx);
				 }
				 else if(strcmp(token,"dx") == 0){
					 print(dx);
				 }
				 else{
					 int x = atoi(token);
					 print(x);
				 }
			  }
			  else if(strcmp(token,"add") == 0){
				  token = strtok(NULL, " ");
				  int x;
				  if(strcmp(token,"ax") == 0){
					  x = ax;
				  }
				  else if(strcmp(token,"bx") == 0){
					  x = bx;
				  }
				  else if(strcmp(token,"cx") == 0){
					  x = cx;
				  }
				  else if(strcmp(token,"dx") == 0){
					  x = dx;
				  }
				  else{
				  x = atoi(token);
				  }

				  token = strtok(NULL, " ");
				  if(strcmp(token,"ax") == 0){
					  ax = add(x, ax);
				  }
				  else if(strcmp(token,"bx") == 0){
					  bx = add(x, bx);
				  }
				  else if(strcmp(token,"cx") == 0){
					  cx = add(x, cx);
				  }
				  else if(strcmp(token,"dx") == 0){
					  dx = add(x, dx);
				  }
			  }
			  else if(strcmp(token,"sub") == 0){
				  token = strtok(NULL, " ");
				  int x;
				  if(strcmp(token,"ax") == 0){
					  x = ax;
				  }
				  else if(strcmp(token,"bx") == 0){
					  x = bx;
				  }
				  else if(strcmp(token,"cx") == 0){
					  x = cx;
				  }
				  else if(strcmp(token,"dx") == 0){
					  x = dx;
				  }
				  else{
					  x = atoi(token);
				  }

				  token = strtok(NULL, " ");
				  if(strcmp(token,"ax") == 0){
					  ax = sub(x, ax);
				  }
				  else if(strcmp(token,"bx") == 0){
					  bx = sub(x, bx);
				  }
				  else if(strcmp(token,"cx") == 0){
					  cx = sub(x, cx);
				  }
				  else if(strcmp(token,"dx") == 0){
					  dx = sub(x, dx);
				  }
			  }
			  else if(strcmp(token,"mul") == 0){
				  token = strtok(NULL, " ");
				  int x;
				  if(strcmp(token,"ax") == 0){
					  x = ax;
				  }
				  else if(strcmp(token,"bx") == 0){
					  x = bx;
				  }
				  else if(strcmp(token,"cx") == 0){
					  x = cx;
				  }
				  else if(strcmp(token,"dx") == 0){
					  x = dx;
				  }
				  else{
					  x = atoi(token);
				  }

				  token = strtok(NULL, " ");
				  if(strcmp(token,"ax") == 0){
					  ax = mul(x, ax);
				  }
				  else if(strcmp(token,"bx") == 0){
					  bx = mul(x, bx);
				  }
				  else if(strcmp(token,"cx") == 0){
					  cx = mul(x, cx);
				  }
				  else if(strcmp(token,"dx") == 0){
					  dx = mul(x, dx);
				  }
			  }
			  else if(strcmp(token,"div") == 0){
				  token = strtok(NULL, " ");
				  int x;
				  if(strcmp(token,"ax") == 0){
					  x = ax;
				  }
				  else if(strcmp(token,"bx") == 0){
					  x = bx;
				  }
				  else if(strcmp(token,"cx") == 0){
					  x = cx;
				  }
				  else if(strcmp(token,"dx") == 0){
					  x = dx;
				  }
				  else{
					  x = atoi(token);
				  }

				  token = strtok(NULL, " ");
				  if(strcmp(token,"ax") == 0){
					  ax = divide(x, ax);
				  }
				  else if(strcmp(token,"bx") == 0){
					  bx = divide(x, bx);
				  }
				  else if(strcmp(token,"cx") == 0){
					  cx = divide(x, cx);
				  }
				  else if(strcmp(token,"dx") == 0){
					  dx = divide(x, dx);
				  }
			  }
			  else if(strcmp(token,"mov") == 0){
				  token = strtok(NULL, " ");
				  int x;
				  if(strcmp(token,"ax") == 0){
					  x = ax;
				  }
				  else if(strcmp(token,"bx") == 0){
					  x = bx;
				  }
				  else if(strcmp(token,"cx") == 0){
					  x = cx;
				  }
				  else if(strcmp(token,"dx") == 0){
					  x = dx;
				  }
				  else{
					  x = atoi(token);
				  }

				  token = strtok(NULL, " ");
				  if(strcmp(token,"ax") == 0){
					  ax = mov(x, ax);
				  }
				  else if(strcmp(token,"bx") == 0){
					  bx = mov(x, bx);
				  }
				  else if(strcmp(token,"cx") == 0){
					  cx = mov(x, cx);
				  }
				  else if(strcmp(token,"dx") == 0){
					  dx = mov(x, dx);
				  }
			  }
			  else if(strcmp(token,"jmp") == 0){
				  token = strtok(NULL, " ");
				  int L = atoi(token);
				  i = jmp(L) - 1;
			  }
			  else if(strcmp(token,"je") == 0){
				  token = strtok(NULL, " ");
				  int L = atoi(token);
				  token = strtok(NULL, " ");
				  int x;
				  if(strcmp(token,"ax") == 0){
				  	x = ax;
				  }
				  else if(strcmp(token,"bx") == 0){
				  	x = bx;
				  }
				  else if(strcmp(token,"cx") == 0){
				  	x = cx;
				  }
				  else if(strcmp(token,"dx") == 0){
					  x = dx;
				  }
				  else{
					  x = atoi(token);
				  }
				  token = strtok(NULL, " ");

				  int y;
				  if(strcmp(token,"ax") == 0){
				  	y = ax;
				  }
				  else if(strcmp(token,"bx") == 0){
				  	y = bx;
				  }
				  else if(strcmp(token,"cx") == 0){
				  	y = cx;
				  }
				  else if(strcmp(token,"dx") == 0){
				  	y = dx;
				  }
				  else{
					  y = atoi(token);
				  }

				  int index = je(L, x, y);
				  if(index == -1){
					  continue;
				  }
				  else{
					  i = index-1;
				  }
			 }
			  else if(strcmp(token,"jne") == 0){
				  token = strtok(NULL, " ");
				  int L = atoi(token);
				  token = strtok(NULL, " ");
				  int x;
				  if(strcmp(token,"ax") == 0){
				  	x = ax;
				  }
				  else if(strcmp(token,"bx") == 0){
				  	x = bx;
				  }
				  else if(strcmp(token,"cx") == 0){
				  	x = cx;
				  }
				  else if(strcmp(token,"dx") == 0){
				  	x = dx;
				  }
				  else{
				  	x = atoi(token);
				  }
				  token = strtok(NULL, " ");
				  int y;
				  if(strcmp(token,"ax") == 0){
				  	y = ax;
				  }
				  else if(strcmp(token,"bx") == 0){
				  	y = bx;
				  }
				  else if(strcmp(token,"cx") == 0){
				  	y = cx;
				  }
				  else if(strcmp(token,"dx") == 0){
				  	y = dx;
				  }
				  else{
				  	y = atoi(token);
				  }
				  int index = jne(L, x, y);
				  if(index == -1){
				  	continue;
				  }
				  else{
				  	i = index-1;
				  }
			  }
			  else if(strcmp(token,"jg") == 0){
				  token = strtok(NULL, " ");
				  int L = atoi(token);
				  token = strtok(NULL, " ");
				  int x;
				  if(strcmp(token,"ax") == 0){
					  x = ax;
				  }
				  else if(strcmp(token,"bx") == 0){
					  x = bx;
				  }
				  else if(strcmp(token,"cx") == 0){
					  x = cx;
				  }
				  else if(strcmp(token,"dx") == 0){
					  x = dx;
				  }
				  else{
					  x = atoi(token);
				  }
				  token = strtok(NULL, " ");
				  int y;
				  if(strcmp(token,"ax") == 0){
					  y = ax;
				  }
				  else if(strcmp(token,"bx") == 0){
					  y = bx;
				  }
				  else if(strcmp(token,"cx") == 0){
					  y = cx;
				  }
				  else if(strcmp(token,"dx") == 0){
					  y = dx;
				  }
				  else{
					  y = atoi(token);
				  }
				  int index = jg(L, x, y);
				  if(index == -1){
					  continue;
				  }
				  else{
					  i = index-1;
				  }
			  }
			  else if(strcmp(token,"jge") == 0){
				  token = strtok(NULL, " ");
				  int L = atoi(token);
				  token = strtok(NULL, " ");
				  int x;
				  if(strcmp(token,"ax") == 0){
					  x = ax;
				  }
				  else if(strcmp(token,"bx") == 0){
					  x = bx;
				  }
				  else if(strcmp(token,"cx") == 0){
					  x = cx;
				  }
				  else if(strcmp(token,"dx") == 0){
					  x = dx;
				  }
				  else{
					  x = atoi(token);
				  }
				  token = strtok(NULL, " ");
				  int y;
				  if(strcmp(token,"ax") == 0){
					  y = ax;
				  }
				  else if(strcmp(token,"bx") == 0){
					  y = bx;
				  }
				  else if(strcmp(token,"cx") == 0){
					  y = cx;
				  }
				  else if(strcmp(token,"dx") == 0){
					  y = dx;
				  }
				  else{
					  y = atoi(token);
				  }
				  int index = jge(L, x, y);
				  if(index == -1){
				  	continue;
				  }
				  else{
				  	i = index-1;
				  }
			  }
			  else if(strcmp(token,"jl") == 0){
				  token = strtok(NULL, " ");
				  int L = atoi(token);
				  token = strtok(NULL, " ");
				  int x;
				  if(strcmp(token,"ax") == 0){
				  	x = ax;
				  }
				  else if(strcmp(token,"bx") == 0){
				  	x = bx;
				  }
				  else if(strcmp(token,"cx") == 0){
				  	x = cx;
				  }
				  else if(strcmp(token,"dx") == 0){
				  	x = dx;
				  }
				  else{
				  	x = atoi(token);
				  }
				  token = strtok(NULL, " ");
				  int y;
				  if(strcmp(token,"ax") == 0){
				  	y = ax;
				  }
				  else if(strcmp(token,"bx") == 0){
				  	y = bx;
				  }
				  else if(strcmp(token,"cx") == 0){
				  	y = cx;
				  }
				  else if(strcmp(token,"dx") == 0){
				  	y = dx;
				  }
				  else{
				  	y = atoi(token);
				  }
				  int index = jl(L, x, y);
				  if(index == -1){
				  	continue;
				  }
				  else{
				  	i = index-1;
				  }
			  }
			  else if(strcmp(token,"jle") == 0){
				  token = strtok(NULL, " ");
				  int L = atoi(token);
				  token = strtok(NULL, " ");
				  int x;
				  if(strcmp(token,"ax") == 0){
				  	x = ax;
				  }
				  else if(strcmp(token,"bx") == 0){
				  	x = bx;
				  }
				  else if(strcmp(token,"cx") == 0){
				  	x = cx;
				  }
				  else if(strcmp(token,"dx") == 0){
				  	x = dx;
				  }
				  else{
				  	x = atoi(token);
				  }
				  token = strtok(NULL, " ");
				  int y;
				  if(strcmp(token,"ax") == 0){
				  	y = ax;
				  }
				  else if(strcmp(token,"bx") == 0){
				  	y = bx;
				  }
				  else if(strcmp(token,"cx") == 0){
				  	y = cx;
				  }
				  else if(strcmp(token,"dx") == 0){
					y = dx;
				  }
				  else{
				  	y = atoi(token);
				  }
				  int index = jle(L, x, y);
				  if(index == -1){
				  	continue;
				  }
				  else{
				  	i = index-1;
				  }
			  }
			  else{
			  	  continue;
			  }
		  }
	  }
}
