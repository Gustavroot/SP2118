#!/usr/bin/python

#Importaciones aqui
#import math
import modulopoisson

#Definiciones de funciones aqui


"""
#Definiciones de funciones aqui
def poissonFunction(x,y):
    #Se reciben los parametros y se procesan a traves de la funcion
    return -(math.cos(x+y))

def boundaryFunctionA():
    #Se reciben los parametros y se procesan a traves de la funcion

def boundaryFunctionB():
    #Se reciben los parametros y se procesan a traves de la funcion

def boundaryFunctionC():
    #Se reciben los parametros y se procesan a traves de la funcion

def boundaryFunctionD():
    #Se reciben los parametros y se procesan a traves de la funcion
"""





#Inicializacion de objetos aqui
#print "eso"
print "------------------------------------------------------------------------"
print "\nPrograma para la visualizacion de la solucion a la ecuacion de Poisson:"
print "(todas las distancias estan en cm)"
print "\n"
endpointA=raw_input('Ingrese limite de la izquierda (ej. 0): ')
endpointB=raw_input('Ingrese limite de la derecha (ej. 3.2): ')
endpointC=raw_input('Ingrese limite de arriba (ej. 0): ')
endpointD=raw_input('Ingrese limite de abajo: (ej. 1.6): ')
stepM=raw_input('Ingrese cantidad de divisiones para el eje y (m): ')
stepN=raw_input('Ingrese cantidad de divisiones para el eje x (n): ')
tolerance=raw_input('Ingrese la tolerancia (TOL): ')
maxIterationsN=raw_input('Ingrese el numero maximo de iteraciones (N): ')


#Llamado al modulo de C aqui
arrayRESULT=modulopoisson.solvepoisson([int(endpointA), int(endpointB), int(endpointC), int(endpointD), int(stepM), int(stepN), int(tolerance), int(maxIterationsN)])
print "\nEjecutando resolucion a traves de modulo de C...\n"
print arrayRESULT


#Codigo pesado aqui... VPython


