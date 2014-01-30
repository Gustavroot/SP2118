/*
  Deteccion de bordes mediante calculo de gradientes.
  Utiliza matrices linearizadas como arreglos.

  CNCA, 2014.
*/

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int * leerMatriz(char * archivo, int * dims);
void convertirAGris(int * M, float * G, int X, int Y);
void gradienteX(float * G, float * Mx, int X, int Y);
void gradienteY(float * G, float * My, int X, int Y);
void unificar(float * Mx, float * My, float * M2, int X, int Y);
void umbralizar(float * M2, int * R, int X, int Y);
void escribirMatriz(char * archivo, int * R, int X, int Y);

int main(char argc, char ** argv){

   int * M;
   int dims[3];
   int i,j,k;
   M = leerMatriz(argv[1], dims);

   int X = dims[0];
   int Y = dims[1];
   int Z = dims[2];

   printf("%s: %ix%ix%i\n",argv[1],X,Y,Z);

//Declarar matriz para transformar a escala de grises

// 1. Convertir a gris

// 2. Calcular gradiente X

// 3. Calcular gradiente Y

// 4. Unificar gradientes

// 5. Umbralizar

// 6. Escribir matriz umbralizada
   printf("Guardando matriz en \"%s\"\n",argv[2]);
   escribirMatriz(argv[2], R, X, Y);

   return 0;
}

/*
   Lee una matriz de nxmx3 de un archivo de texto.
   Retorna un puntero a la matriz y escribe las dimensiones en 'dims'
*/
int * leerMatriz(char * archivo, int * dims){
	FILE * fp;

   fp = fopen (archivo, "r"); 
	int X,Y,Z;

	char buffer[3] = {0};
	if(fscanf(fp, "%s", buffer) != EOF)
		X = atoi(buffer);
	if(fscanf(fp, "%s", buffer) != EOF)
		Y = atoi(buffer);
	if(fscanf(fp, "%s", buffer) != EOF)
		Z = atoi(buffer);

	int * M;
	M = (int *) malloc(sizeof(int)*X*Y*Z);

	dims[0] = X;
	dims[1] = Y;
	dims[2] = Z;

	unsigned int i= 0;
	while(i < X*Y*Z && (fscanf(fp, "%s", buffer) != EOF)){
		M[i++] = atoi(buffer);
	}

   fclose(fp);

	return M;
}

/*
   Convierte la imagen (matriz M) a escala de grises (matriz G)
con una suma ponderada de los pixeles en las capas.
*/
void convertirAGris(int * M, float * G, int X, int Y){

}

/*
   Calcula la matriz de gradientes Mx en X, a partir de G
*/
void gradienteX(float * G, float * Mx, int X, int Y){
}

/*
   Calcula la matriz de gradientes My en Y, a partir de G
*/
void gradienteY(float * G, float * My, int X, int Y){

}

/*
  Unificar los gradientes Mx y My en M2
*/
void unificar(float * Mx, float * My, float * M2, int X, int Y){

}

/*
   Convertir a blanco y negro segun un umbral que determine la
presencia de un borde.
   M2 es la matriz de entrada, R la matriz resultante.
*/
void umbralizar(float * M2, int * R, int X, int Y){


}

/*
   Guarda la matriz resultado, R, en un archivo de texto.
*/
void escribirMatriz(char * archivo, int * R, int X, int Y){
   FILE * fp;

   fp = fopen (archivo, "w"); 
   rewind(fp);

   fprintf(fp,"%i\n",X);
   fprintf(fp,"%i\n",Y);
   fprintf(fp,"%i\n",1);

   int i,j;

   for(i=0;i<X*Y;i++) fprintf(fp,"%i\n",R[i]);

   fclose(fp);
}
