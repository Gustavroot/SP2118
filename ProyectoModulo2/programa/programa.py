#!/usr/bin/python

#Importaciones aqui
#import math
import modulopoisson
from visual import *


#Definiciones de funciones aqui
#Esta funcion dibuja un "poligono" triangular...
def dibujadoTriangulo(tripletePuntos,radioCilindros2):
    #bla=2
    segm=10
    for counter in range(0,segm):
        puntoUNO=tripletePuntos[1]+(tripletePuntos[0]-tripletePuntos[1])*(float(counter)/float(segm))
        puntoDOS=tripletePuntos[2]+(tripletePuntos[0]-tripletePuntos[2])*(float(counter)/float(segm))
        cylinder(pos=puntoDOS, axis=(puntoUNO-puntoDOS), radius=radioCilindros2)
        #cylinder(pos=tripletePuntos[2], axis=(tripletePuntos[1]-tripletePuntos[2]), radius=radioCilindros2)
    

#Esta funcion dibuja un segmento unidad del cuadriculado...
def dibujadoUnidadCuadriculado(cuadrupletePuntos,radioCilindros1):
   #bla=1
   dibujadoTriangulo([cuadrupletePuntos[0],cuadrupletePuntos[1],cuadrupletePuntos[2]],radioCilindros1)
   dibujadoTriangulo([cuadrupletePuntos[1],cuadrupletePuntos[2],cuadrupletePuntos[3]],radioCilindros1)

#Esta funcion dibuja una superficie, a partir de un conjunto de puntos...
def dibujadoSURFACE(arrayPuntos):
    #Se toma el valor maximo entre los dos pasos, y una quinta parte de ese sera el radio
    #de las esferas
    pasoX=(float(endpointB)-float(endpointA))/float(stepN)
    pasoY=(float(endpointD)-float(endpointC))/float(stepM)
    radioPuntosRESULT=max([pasoX,pasoY])/10
    
    #Se grafica la funcion obtenida a traves del metodo de difs finitas...
    counterTMP2=0
    #print len(arrayRESULT)
    listVertexs=[]
    #print len(arrayPuntos)
    for counterTMP in range(0,len(arrayPuntos)):
        #print pasoX
        #print counterTMP%stepN
        #print endpointA+(counterTMP-counterTMP2*(stepN+1))*pasoX
        if counterTMP%(stepN+1)==0 and counterTMP!=0:
            counterTMP2+=1
        sphereBuffer=sphere(pos=vector(endpointC+counterTMP2*pasoY,arrayPuntos[counterTMP],endpointA+(counterTMP-counterTMP2*(stepN+1))*pasoX), radius=radioPuntosRESULT, color=color.white)
        listVertexs.append(sphereBuffer)
        #print counterTMP2
    #Llamado a funcion dibujado unidad cuadricula...
    dibujadoUnidadCuadriculado([listVertexs[0].pos,listVertexs[1].pos,listVertexs[int(stepN+1)].pos,listVertexs[int(stepN+2)].pos],radioPuntosRESULT)


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
    endpointB=9.0
if not(endpointC.isdigit()):
    endpointC=0
if not(endpointD.isdigit()):
    endpointD=4.5
if not(stepM.isdigit()):
    stepM=15.0
if not(stepN.isdigit()):
    stepN=15.0
if not(tolerance.isdigit()):
    tolerance=0.1
if not(maxIterationsN.isdigit()):
    maxIterationsN=50.0

#Seria bueno agregar un verificado de la conmensurabilidad entre
#los limites superiores y la cantidad de divisiones de los ejes,
#pero ya hay un limite en N...

print endpointB

#Llamado al modulo de C aqui
print "\nEjecutando resolucion a traves de modulo de C...\n"
arrayRESULT=modulopoisson.solvepoisson([float(endpointA), float(endpointB), float(endpointC), float(endpointD), float(stepM), float(stepN), float(tolerance), float(maxIterationsN)])
#print "RESULTADO: "+str(arrayRESULT)
#print len(arrayRESULT)

#Se obtiene el valor maximo en el array de resultados, para setear el eje y
maxValue=max(arrayRESULT)
#print maxValue

#Codigo VPython aqui...
#------------------------------------------------------------------------------------------------
#Se configura la escena...
scene2 = display(title='Ecuacion de Poisson', center=(0.5,0.5,0.5))
scene2.forward=vector(-0.396691, -0.227749, -0.889251)

#Se configuran los ejes coordenados
ejeY=box(size=vector(maxValue/20,maxValue*8,maxValue/20),color=color.green)
ejeX=box(size=vector(endpointB*2.1,maxValue/20,maxValue/20),color=color.green)
ejeZ=box(size=vector(maxValue/20,maxValue/20,endpointD*2.1),color=color.green)

#sphere(pos=vector(-1,0,0), radius=radioPuntosRESULT, color=color.white)

#fourSidesFigure=Polygon([(0,0),(8,0.7),(4,0.7)])

#Se hace un llamado al dibujado de superficie...
dibujadoSURFACE(arrayRESULT)
