#include <Python.h>
//#include <string.h>

/*
 * Confg de mod en C para la resolucion de la ecuacion de Poisson
 * 
 */

// Función modulopoisson.solvepoisson()
static PyObject *modulopoisson_solvepoisson(PyObject *self, PyObject *args) {

    int i;
    PyObject *lista;
    if(!PyArg_ParseTuple(args, "O", &lista))
        return NULL;

    //Se crea un array, en el cual se guardaran los datos ingresados
    int datosingresados[8];

    //Se extraen esos datos ingresados
    for(i=0; i<PyList_Size(lista); i++){
        //printf("%d", (int)PyInt_AsLong(PyList_GetItem(lista,i)));
        datosingresados[i]=PyInt_AsLong(PyList_GetItem(lista,i));
        //printf("%d", datosingresados[i]);
    }


    //Procesamiento de los datos ingresados...
    //-----------------------------------------------------------------
    //Longitud del array de resultados...
    int lengthArrayResults;
    //code...
    //-----------------------------------------------------------------

    // Retorna un objeto Python, a partir del array resultante...
    lengthArrayResults=8;
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
    return Py_BuildValue(strToPassPyObj, &datosingresados);
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
