#include <mpi.h>
#include <stdio.h>

int main(int argc,char ** argv){
   int procs, id, largo, rc; 

   char host[MPI_MAX_PROCESSOR_NAME];

   rc = MPI_Init(&argc,&argv);
   if (rc != MPI_SUCCESS) {
     printf ("Error inicializando MPI. Finalizando...\n");
     MPI_Abort(MPI_COMM_WORLD, rc);
     }

   MPI_Comm_size(MPI_COMM_WORLD,&procs);
   MPI_Comm_rank(MPI_COMM_WORLD,&id);
   MPI_Get_processor_name(host, &largo);
   printf ("Procesos = %d, Mi id = %d, Corriendo en %s\n", procs, id, host);


   MPI_Finalize();
   return 0;
}
