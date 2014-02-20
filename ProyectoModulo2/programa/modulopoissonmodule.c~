#include <Python.h>
//#include <string.h>
#include <math.h>

/*
 * Confg de mod en C para la resolucion de la ecuacion de Poisson
 * 
 */


//Funciones matematicas...
//Funcion densidad, ya fue probada y funciona, recibe valores en radianes
double FnDensidad(double x, double y){
    return -(cos(x+y)+cos(x-y));
}

//Condiciones de frontera
//Ya fueron probadas y funcionan, reciben valores en radianes
double FnFronteraIzquierda(double y){
    return cos(y);
}
double FnFronteraDerecha(double y){
    return -cos(y);
}

double FnFronteraInferior(double x){
    return cos(x);
}
double FnFronteraSuperior(double x){
    return 0;
}



// Función modulopoisson.solvepoisson()
static PyObject *modulopoisson_solvepoisson(PyObject *self, PyObject *args) {

    //Contadores...
    int i,j;
    PyObject *lista;
    if(!PyArg_ParseTuple(args, "O", &lista))
        return NULL;

    //Se crea un array, en el cual se guardaran los datos ingresados
    float datosingresados[8];

    //Se extraen esos datos ingresados
    for(i=0; i<PyList_Size(lista); i++){
        //printf("%d", (int)PyInt_AsLong(PyList_GetItem(lista,i)));
        datosingresados[i]=PyFloat_AsDouble(PyList_GetItem(lista,i));
        //printf("%d", datosingresados[i]);
    }



    //Procesamiento de los datos ingresados...
    //-----------------------------------------------------------------
    //Longitud del array de resultados...
    int lengthArrayResults;
    //code...
    //double fnBLA=FnDensidad(4,5);
    //printf("Densidad: %f", fnBLA);

    //Primero, se crea el arreglo de resultados
    double *arrayResults;
    //Se define variable con total de datos en la grilla
    double totalDatos=(datosingresados[4]+1)*(datosingresados[5]+1);
    //Se define variable con pasos... h es para el eje x, k es para el y
    double h=(datosingresados[1]-datosingresados[0])/datosingresados[5];
    double k=(datosingresados[3]-datosingresados[2])/datosingresados[4];
    printf("Pasos: %f %f \n", h, k);
    arrayResults=(double *)malloc(totalDatos*sizeof(double));

    //SE LLENA DE CEROS EL ARRAY
    for(i=0; i<totalDatos; i++){
        arrayResults[i]=0;
    }

    //Se ingresan los valores de frontera izquierda
    int counterToFill=0;
    for(i=0; i<(int)totalDatos; i+=(int)(datosingresados[5]+1)){
        arrayResults[i]=FnFronteraIzquierda(datosingresados[2]+counterToFill*k);
        counterToFill++;
    }
    //Se ingresan los valores de frontera derecha
    counterToFill=0;
    for(i=datosingresados[5]; i<(int)totalDatos; i+=(int)(datosingresados[5]+1)){
        arrayResults[i]=FnFronteraDerecha(datosingresados[2]+counterToFill*k);
        counterToFill++;
    }
    //Se ingresan los valores de frontera inferior
    counterToFill=0;
    for(i=1; i<(int)datosingresados[5]; i++){
        arrayResults[i]=FnFronteraInferior(datosingresados[0]+counterToFill*h);
        counterToFill++;
    }
    //Se ingresan los valores de frontera superior
    counterToFill=0;
    for(i=(int)(totalDatos-1-datosingresados[5]+1); i<(int)(totalDatos-1); i++){
        arrayResults[i]=FnFronteraSuperior(datosingresados[0]+counterToFill*h);
        counterToFill++;
    }
    //Ahora se comienza el algoritmo...!
    //1. el primer paso es la creacion de los pasos, lo cual ya esta hecho
    //2. el segundo paso es la creacion de los valores espaciales de los puntos
    //de grilla internos, lo cual ya esta hecho tambien.. hay que tener claro
    //que las iteraciones se dan con i desde 1 hasta n-1, y con j desde 1 hasta
    //m-1
    //3. el tercer paso es el llenado de la grilla con ceros, lo cual ya se hizo
    //4. Se crean mas constantes, llamadas lambda, muC, eleC
    double lambdaC=(h*h)/(k*k);
    double muC=2*(1+lambdaC);
    double eleC=1;
    //printf("%f", muC);
    //5. se lleva a cabo un while, que controla el resto del flujo del programa,
    //y se sale de este while en caso de superar el numero de iteraciones N... lo
    //que se hace en este while son iteraciones de Gauss-Seidell
    //Se declaran algunas variables antes del while, que se utilizaran ahi
    double z;
    double norm;
    while(eleC<(datosingresados[7]+1)){
        //Paso 7 del libro...
        z=(-(h*h)*FnDensidad(datosingresados[0]+1.0*h,datosingresados[2]+(datosingresados[4]-1.0)*k)+FnFronteraIzquierda(datosingresados[2]+(datosingresados[4]-1.0)*k)+lambdaC*FnFronteraSuperior(datosingresados[0]+1.0*h)+lambdaC*arrayResults[(int)(1.0+(datosingresados[4]-2.0)*(datosingresados[5]+1.0))]+arrayResults[(int)(2.0+(datosingresados[4]-1.0)*(datosingresados[5]+1.0))])/muC;
        //printf("Z: %f \n", z);
        norm=fabs(z-arrayResults[(int)(1+(datosingresados[4]-1)*(datosingresados[5]+1))]);
        //printf("BICHO: %f \n", arrayResults[(int)(1+(datosingresados[4]-1)*(datosingresados[5]+1))]);
        //printf("%f \n", fabs(-1.0));
        arrayResults[(int)(1+(datosingresados[4]-1)*(datosingresados[5]+1))]=z;
        //printf("%f \n", arrayResults[(int)(1+(datosingresados[4]-1)*(datosingresados[5]+1))]);
        //Paso 8 del libro...
        for(i=2; i<(datosingresados[5]-1); i++){
            z=(-(h*h)*FnDensidad(datosingresados[0]+((float)(i)*h),datosingresados[2]+(datosingresados[4]-1.0)*k)+lambdaC*FnFronteraSuperior(datosingresados[0]+(float)(i)*h)+arrayResults[(int)((float)(i-1)+(datosingresados[4]-1.0)*(datosingresados[5]+1.0))]+arrayResults[(int)((float)(i+1)+(datosingresados[4]-1.0)*(datosingresados[5]+1.0))]+lambdaC*arrayResults[(int)((float)(i)+(datosingresados[4]-2.0)*(datosingresados[5]+1.0))])/muC;
            if(fabs(arrayResults[(int)(i+(datosingresados[4]-1)*(datosingresados[5]+1))]-z)>norm){
                norm=fabs(arrayResults[(int)(i+(datosingresados[4]-1)*(datosingresados[5]+1))]-z);
            }
            arrayResults[(int)(i+(datosingresados[4]-1)*(datosingresados[5]+1))]=z;
        }
        //printf("Z: %f \n", z);
        //printf("NORM: %f \n", norm);
        //Paso 9 del libro...
        z=(-(h*h)*FnDensidad(datosingresados[0]+(datosingresados[5]-1)*h,datosingresados[2]+(datosingresados[4]-1)*k)+FnFronteraDerecha(datosingresados[2]+(datosingresados[4]-1)*k)+lambdaC*FnFronteraSuperior(datosingresados[0]+(datosingresados[5]-1)*h)+arrayResults[(int)((datosingresados[5]-2)+(datosingresados[4]-1)*(datosingresados[5]+1))]+lambdaC*arrayResults[(int)((datosingresados[5]-1)+(datosingresados[4]-2)*(datosingresados[5]+1))])/muC;
        if(fabs(arrayResults[(int)((datosingresados[5]-1)+(datosingresados[4]-1)*(datosingresados[5]+1))]-z)>norm){
            norm=fabs(arrayResults[(int)((datosingresados[5]-1)+(datosingresados[4]-1)*(datosingresados[5]+1))]-z);
        }
        arrayResults[(int)((datosingresados[5]-1)+(datosingresados[4]-1)*(datosingresados[5]+1))]=z;
        //Paso 10 del libro...
        for(j=(datosingresados[4]-2); j>1; j--){
            //Paso 11 del libro...
            z=(-(h*h)*FnDensidad(datosingresados[0]+1*h,datosingresados[2]+j*k)+FnFronteraIzquierda(datosingresados[2]+j*k)+lambdaC*arrayResults[(int)(1+(j+1)*(datosingresados[5]+1))]+lambdaC*arrayResults[(int)(1+(j-1)*(datosingresados[5]+1))]+arrayResults[(int)(2+j*(datosingresados[5]+1))])/muC;
            if(fabs(arrayResults[(int)(1+j*(datosingresados[5]+1))]-z)>norm){
                norm=fabs(arrayResults[(int)(1+j*(datosingresados[5]+1))]-z);
            }
            arrayResults[(int)(1+j*(datosingresados[5]+1))]=z;
            //Paso 12 del libro...
            for(i=2; i<(datosingresados[5]-1); i++){
                z=(-(h*h)*FnDensidad(datosingresados[0]+i*h,datosingresados[2]+j*k)+arrayResults[(int)((i-1)+(j)*(datosingresados[5]))]+lambdaC*arrayResults[(int)(i+(j+1)*(datosingresados[5]))]+arrayResults[(int)((i+1)+j*(datosingresados[5]))]+lambdaC*arrayResults[(int)(i+(j-1)*(datosingresados[5]))])/muC;
                if(fabs(arrayResults[(int)(i+j*(datosingresados[5]+1))]-z)>norm){
                    norm=fabs(arrayResults[(int)(i+j*(datosingresados[5]+1))]-z);
                }
                arrayResults[(int)(i+j*(datosingresados[5]+1))]=z;
            }
            //Paso 13 del libro...
            z=(-(h*h)*FnDensidad(datosingresados[0]+(datosingresados[5]-1)*h,datosingresados[2]+j*k)+FnFronteraDerecha(datosingresados[2]+j*k)+arrayResults[(int)((datosingresados[5]-2)+j*(datosingresados[5]+1))]+lambdaC*arrayResults[(int)((datosingresados[5]-1)+(j+1)*(datosingresados[5]+1))]+lambdaC*arrayResults[(int)((datosingresados[5]-1)+(j-1)*(datosingresados[5]+1))])/muC;
            if(fabs(arrayResults[(int)((datosingresados[5]-1)+j*(datosingresados[5]+1))]-z)>norm){
                norm=fabs(arrayResults[(int)((datosingresados[5]-1)+j*(datosingresados[5]+1))]-z);
            }
            arrayResults[(int)((datosingresados[5]-1)+j*(datosingresados[5]+1))]=z;
        }
        //Paso 14 del libro...
        z=(-(h*h)*FnDensidad(datosingresados[0]+1*h,datosingresados[2]+1*k)+FnFronteraIzquierda(datosingresados[2]+1*k)+lambdaC*FnFronteraInferior(datosingresados[0]+1*h)+lambdaC*arrayResults[(int)(1+2*(datosingresados[5]+1))]+arrayResults[(int)(2+1*(datosingresados[5]+1))])/muC;
        if(fabs(arrayResults[(int)(1+1*(datosingresados[5]+1))]-z)>norm){
            norm=fabs(arrayResults[(int)(1+1*(datosingresados[5]+1))]-z);
        }
        arrayResults[(int)(1+1*(datosingresados[5]+1))]=z;
        //Paso 15 del libro...
        for(i=2; i<(datosingresados[4]-1); i++){
            z=(-(h*h)*FnDensidad(datosingresados[0]+i*h,datosingresados[2]+1*k)+lambdaC*FnFronteraInferior(datosingresados[0]+i*h)+arrayResults[(int)((i-1)+1*(datosingresados[5]+1))]+lambdaC*arrayResults[(int)(i+2*(datosingresados[5]+1))]+arrayResults[(int)((i+1)+1*(datosingresados[5]+1))])/muC;
            if(fabs(arrayResults[(int)(i+1*(datosingresados[5]+1))]-z)>norm){
                norm=fabs(arrayResults[(int)(i+1*(datosingresados[5]+1))]-z);
            }
            arrayResults[(int)(i+1*(datosingresados[5]+1))]=z;
        }
        //Paso 16 del libro...
        z=(-(h*h)*FnDensidad(datosingresados[0]+(datosingresados[5]-1)*h,datosingresados[2]+1*k)+FnFronteraDerecha(datosingresados[2]+1*k)+lambdaC*FnFronteraInferior(datosingresados[0]+(datosingresados[5]-1)*h)+arrayResults[(int)((datosingresados[5]-2)+1*(datosingresados[5]+1))]+lambdaC*arrayResults[(int)((datosingresados[5]-1)+2*(datosingresados[5]+1))])/muC;
        if(fabs(arrayResults[(int)((datosingresados[5]-1)+1*(datosingresados[5]+1))]-z)>norm){
            norm=fabs(arrayResults[(int)((datosingresados[5]-1)+1*(datosingresados[5]+1))]-z);
        }
        arrayResults[(int)((datosingresados[5]-1)+1*(datosingresados[5]+1))]=z;
        //Paso 17 del libro...
        //printf("\n Numero de iteracion: %f. Norm: %f \n", eleC, norm);
        //printf("NORM: %f \n", norm);
        if(norm<datosingresados[6]){
            lengthArrayResults=(datosingresados[4]+1)*(datosingresados[5]+1);
            PyObject* lista2 = PyList_New(0);
            for(i=0; i<lengthArrayResults; i++){
                PyList_Append(lista2, Py_BuildValue("f", arrayResults[i]));
            }
            //Se imprime cuantas iteraciones se llevaron a cabo...
            printf("Proceso finalizado. Num. iteraciones: %d \n\n", (int)eleC);
            return lista2;
        }
        eleC++;
    }
    printf("\nNo se alcanzo la tolerancia deseada con el numero maximo de interaciones ingresadas...");
    return Py_BuildValue("i",0);

    //-----------------------------------------------------------------



    // Retorna un objeto Python, a partir del array resultante...
/*
    int lengthStrToPassPyObj=lengthArrayResults+2+(lengthArrayResults-1)+1; //ese 1 extra es para el caracter de escape \n
    char *strToPassPyObj;
    //Se hace un pedido de memoria, de manera dinamica...
    strToPassPyObj=(char *)malloc(lengthStrToPassPyObj*sizeof(char));
    strToPassPyObj[0]='[';
    for(i=1; i<lengthStrToPassPyObj-1; i+=2){
        strToPassPyObj[i]='i';
        strToPassPyObj[i+1]=',';
    }
    strToPassPyObj[lengthStrToPassPyObj-2]=']';
    strToPassPyObj[lengthStrToPassPyObj-1]='\0';
    //Se imprime el array, para probar...
    printf("%s", strToPassPyObj);
    //return Py_BuildValue("i", estado);
*/
/*
    lengthArrayResults=(datosingresados[4]+1)*(datosingresados[5]+1);
    PyObject* lista2 = PyList_New(0);
    for(i=0; i<lengthArrayResults; i++){
        PyList_Append(lista2, Py_BuildValue("f", arrayResults[i]));
    }
    //return Py_BuildValue(strToPassPyObj, &datosingresados);
    //return Py_BuildValue("i", 56);
    return lista2;
*/

    //return listaReturn;
}

/*
// Función sistop.getuid()
static PyObject *sistop_getuid(PyObject *self, PyObject *args) {

  // Declaración de variables
  int uid;

  // Lectura de los argumentos
  if(!PyArg_ParseTuple(args, ""))
    return NULL;

  // Llama a la función getuid()
  uid = getuid();

  // Retorna un objeto Python
  return Py_BuildValue("i", uid);
}
*/

// Tabla de funciones/métodos
static PyMethodDef FuncionesModulopoisson[] = {
  {"solvepoisson", modulopoisson_solvepoisson, METH_VARARGS, "Resuelve la ecuacion de Poisson"},
  //{"getuid", sistop_getuid, METH_VARARGS, "Obtiene el UID del usuario actual"},
  {NULL, NULL, 0, NULL}
};

// Inicialización del módulo
PyMODINIT_FUNC initmodulopoisson(void) {

  // Inicialización del módulo
  Py_InitModule("modulopoisson", FuncionesModulopoisson);

  // Opcionalmente pueden ejecutarse otros enunciados
  //printf("Creando grilla y resolviendo puntos...\n");

}
