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
    arrayResults=(double *)malloc(totalDatos*sizeof(double));
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
    //3. el tercer paso es el llenado de la grilla con ceros, lo cual esta hecho
    //de manera automatica al solicitar memoria dinamicamente
    //4. Se crean mas constantes, llamadas lambda, muC, eleC
    double lambdaC=(h*h)/(k*k);
    double muC=2*(1+lambdaC);
    double eleC=1;
    //5. se lleva a cabo un while, que controla el resto del flujo del programa,
    //y se sale de este while en caso de superar el numero de iteraciones N... lo
    //que se hace en este while son iteraciones de Gauss-Seidell
    //Se declaran algunas variables antes del while, que se utilizaran ahi
    double z, norm;
    while(eleC<datosingresados[7]){
        z=(-(h*h)*FnDensidad(datosingresados[0]+1*h,datosingresados[2]+(datosingresados[4]-1)*k)+FnFronteraIzquierda(datosingresados[2]+(datosingresados[4]-1)*k)+lambdaC*FnFronteraSuperior(datosingresados[0]+1*h)+lambdaC*arrayResults[(int)(1+(datosingresados[4]-2)*datosingresados[5])]+arrayResults[(int)(2+(datosingresados[4]-1)*datosingresados[5])])/muC;
        norm=abs(z-arrayResults[(int)(1+(datosingresados[4]-1)*datosingresados[5])]);
        arrayResults[(int)(1+(datosingresados[4]-1)*datosingresados[5])]=z;
        for(i=2; i<(datosingresados[5])){
            
        }

        eleC++;
    }

    //-----------------------------------------------------------------



    // Retorna un objeto Python, a partir del array resultante...
    lengthArrayResults=(datosingresados[4]+1)*(datosingresados[5]+1);
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
    PyObject* lista2 = PyList_New(0);
    for(i=0; i<lengthArrayResults; i++){
        PyList_Append(lista2, Py_BuildValue("f", arrayResults[i]));
    }
    //return Py_BuildValue(strToPassPyObj, &datosingresados);
    //return Py_BuildValue("i", 56);
    return lista2;
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
