#include <Python.h>

//Funcion de resolucion de la ecuacion de Poisson
static PyObject *modulopoisson_solvepoisson(PyObject *self, PyObject *args) {
    //code...
    // Lectura de los argumentos
    //int * argumentos;
    //    //PyObject *argumentos;
    //    //if(!PyArg_ParseTuple(args, "O", &argumentos))
    //        //return NULL;
    //procesado de los datos...
    //printf("eso queeso....");
    /*
    int arrayArgumentos[8];
    int i;
    for(i = 0; i < PyList_Size(argumentos); i++) {
        arrayArgumentos[i]=PyInt_AsLong(PyList_GetItem(lista, i));
        //temp = PyList_GetItem(lista, i);
        //suma += PyInt_AsLong(temp);
    }
    */
    //Ahora viene la devolucion de datos:
    //estado=34
    return Py_BuildValue("i", 1);
}
    //Py_BuildValue("i", PyInt_AsLong(PyList_GetItem(argumentos, 3)));

//Tabla de funciones...
static PyMethodDef FuncionesModulopoisson[] = {
{"solvepoisson", modulopoisson_solvepoisson, METH_VARARGS, "Resuelve la ecuacion diferencial de Poisson"},
{NULL, NULL, 0, NULL}
};

//Inicializacion del modulo...
PyMODINIT_FUNC initmodulopoisson(void) {
    // Inicialización del módulo
    (void) Py_InitModule("solvepoisson", FuncionesModulopoisson);
    //Se le indica al usuario que se ha inicializado el mod...
    //printf("Módulo modulopoisson inicializado...\n");
}
