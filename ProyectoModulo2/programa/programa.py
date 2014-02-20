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
print "\nPrograma para la visualizacion de la solucion a la ecuacion de Poisson:\n"
endpointA=raw_input('Ingrese limite de la izquierda (a default=0): ')
endpointB=raw_input('Ingrese limite de la derecha (b default=9): ')
endpointC=raw_input('Ingrese limite de arriba (c default=0): ')
endpointD=raw_input('Ingrese limite de abajo: (d default=4.5): ')
stepM=raw_input('Ingrese cantidad de divisiones para el eje y (m default=15): ')
stepN=raw_input('Ingrese cantidad de divisiones para el eje x (n default=15): ')
tolerance=raw_input('Ingrese la tolerancia (tol default=0.1): ')
maxIterationsN=raw_input('Ingrese el numero maximo de iteraciones (N default=50): ')

#En caso de no ingresar objetos numericos, se hace un cambio del valor al default,
#esto para cada uno de los ingresados
if not(endpointA.isdigit()):
    endpointA=0
if not(endpointB.isdigit()):
    endpointB=9
if not(endpointC.isdigit()):
    endpointC=0
if not(endpointD.isdigit()):
    endpointD=4.5
if not(stepM.isdigit()):
    stepM=15
if not(stepN.isdigit()):
    stepN=15
if not(tolerance.isdigit()):
    tolerance=0.1
if not(maxIterationsN.isdigit()):
    maxIterationsN=50

#Seria bueno agregar un verificado de la conmensurabilidad entre
#los limites superiores y la cantidad de divisiones de los ejes,
#pero ya hay un limite en N...


#Llamado al modulo de C aqui
print "\nEjecutando resolucion a traves de modulo de C...\n"
arrayRESULT=modulopoisson.solvepoisson([float(endpointA), float(endpointB), float(endpointC), float(endpointD), float(stepM), float(stepN), float(tolerance), float(maxIterationsN)])
#print "RESULTADO: "+str(arrayRESULT)
#print len(arrayRESULT)


#Codigo pesado aqui... VPython


