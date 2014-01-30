/***************************************
PPP.c:
   Ejercicio de programación: Calcular el producto vector-Matriz en paralelo utilizando MPI.

   La dimension de los vectores debe ser un multiplo de la cantidad de procesos.  La matriz se representa como un arreglo lineal.

CNCA, 2014.

-------------
Actividades:
-------------

1. Agregar las líneas correspondientes en el 
método "main", siguiendo los comentarios con la
palabra HACER.

2. Completar el contenido de la función prodM.

3. Compilar y ejecutar.  Probar diferentes tamaños
de vectores.

   Para compilar utilice el comando:
   $mpicc.openmpi PVM.c -o [nombre binario]

   Para ejecutar utilice el comando
   $mpiexec.openmpi -n [np] ./[nombre binario]

donde 'np' es el número de procesadores, entre 1 y 4.
****************************************/

#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <mpi.h>

void inicializarV(int * v, long l);
void inicializarM(int * v, long n, long m);
void mostrarV(int * v, long l);
void mostrarM(int * M, long n, long m);
void prodM(int * v, int * M, long n, long m, int * y);
void despl(char * mensaje, int id);

int main(int argc, char ** argv){

	int procs, id;
	long N=10;
	long M=10;
	long N_local=10;
	int * v; //Puntero para el vector
	int * m; //Puntero para la matriz

//Inicializar ambiente MPI
	MPI_Init(&argc, &argv); //Iniciar MPI
	MPI_Comm_rank(MPI_COMM_WORLD, &id); //Determinar id de cada proceso
	MPI_Comm_size(MPI_COMM_WORLD, &procs); //Determinar cantidad de procesos

   	if (id == 0) {
		srand(time(NULL));
		if(argc > 1)
			N = atol(argv[1]);
		M = N;

		printf("%i procesos.\nUtilizando vector de dimension %i y matriz %ix%i\n", procs, N, N, M);
		double mem = (double) (((N+N*M)*sizeof(int)) + ((N/procs)*M+N)*sizeof(int)*procs + (N/procs * sizeof(int) * procs));
		mem = (double) mem / (1024*1024);
		printf("Utilizando aprox. %3.2f MB RAM.\n", mem);

//Reservar memoria para el vector y la matriz
		v = (int *) malloc(N * sizeof(int));//v_;
		m = (int *) malloc(N * M * sizeof(int));//m_;

		printf("Inicializando arreglo.\n");
		inicializarV(v, N);

		printf("Inicializando matriz.\n");
		inicializarM(m,N,M);

		printf("Preparacion finalizada.\n");		
	}

/* HACER: Difundir el valor de N y M*/
	if(id == 0) printf("Repartiendo N = %i, N_local = %i y M = %i.\n", N, N/procs, M);

	MPI_Barrier(MPI_COMM_WORLD);
	double t1 = MPI_Wtime();

//HACER: Broadcast de N y de M

//HACER: cada proceso calcula N_local

/* Hacer: Distribuir el vector y la matriz */

	//Los procesos mayores que 0 declaran arreglo v
	if(id > 0) v = (int *) malloc(N * sizeof(int));

//Por uniformidad de llamados MPI, hacer puntero 'a' que apunte  a 'v'
	int * a = v;

//Declarar y reservar espacio para submatriz B
//Hacer: calcular tamano de matriz B
	int tam_B = **;
	int * B = //malloc

	despl("Distribuyendo vector.", id);

	MPI_Barrier(MPI_COMM_WORLD);

//Hacer: Broadcast de vector 'a'


//Distribuir matriz m en submatriz local B
	despl("Distribuyendo Matriz", id);
	MPI_Barrier(MPI_COMM_WORLD);
//Hacer: Scatter de m por filas

/* Calcular producto vector submatriz */
	despl("Calcular producto en paralelo...", id);

//Hacer: Determinar longitud del arreglo 'y_local' para el resultado del producto parcial.
	int tam_yLocal = **;
	int * y_local = //malloc

//Hacer: calcular el producto vector*subMatriz y guardar en y_local
	despl("Producto finalizado.\n", id);

/* Construir el vector resultado, reutilizando el vector 'a' */
	int * y = a;

//Hacer: Construir 'y' (GATHER) a partir de los 'y_local', guardando todo en proceso 0

/* Mostrar el resultado y finalizar */
	MPI_Barrier(MPI_COMM_WORLD);
	double t2 = MPI_Wtime();

	if(id == 0){
		printf("El primer elemento de y es: %i",y[0]);
		printf("\nt = %3.4f\n", (t2-t1));
	}

	MPI_Finalize();
}

/*
   CUERPO DE LOS METODOS
*/

void despl(char * mensaje, int id){
	if(id == 0){
		printf(mensaje);
		printf("\n");
	}
}

void inicializarV(int * v, long l){
//Hacer: inicializar VECTOR
}

void inicializarM(int * M, long n, long m){
//Hacer: inicializar Matriz como arreglo lineal
}


void prodM(int * V, int * M, long n, long m, int * y){
//Hacer: calcular producto vector matriz, donde la matriz es un arreglo lineal.
//La matriz es nxm y el arreglo m.
//El resultado queda en el vector 'y'.
}
