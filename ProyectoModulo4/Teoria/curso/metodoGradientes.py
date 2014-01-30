#!/usr/bin/python

import sys
import time
import os
import commands
import re
import numpy



def leerMatriz(archivo):
    lines = open(archivo, 'r').readlines()
    tamX=int(lines[0])
    tamY=int(lines[1])
    capas=int(lines[2])
    
    dataRGB = numpy.zeros((tamX,tamY,capas))
    
    l = 3
    
    for i in range(0, tamX):
        for j in range(0, tamY):
            for k in range(0, capas):
                dataRGB[i,j,k] = int(lines[l])
                
                l+=1

    return dataRGB
    
def convertirAGris(matriz):
    
    return dataGS
    
def gradienteX(matriz):
    
    return matrizX

def gradienteY(matriz):
  
    return matrizY
    

def unificar(matrizX, matrizY):
   
    
    return data

def umbralizar(matriz):
    
    return matriz

def escribirMatriz(archivo, matriz):
    s = matriz.shape
    salida = open(archivo,'w')
    
    salida.write(str(s[0])+"\n")
    salida.write(str(s[1])+"\n")
    salida.write("1\n")
    
    for i in range(0, s[0],1):
        for j in range(0, s[1],1):
            salida.write(str(int(matriz[i,j,0]))+"\n")
            
    salida.close()
    return


#funcion de entrada
if(len(sys.argv) == 3): #nombreDelEjecutable, archivoEntrada archivoSalida
    archivoFuente = sys.argv[1]
    dataRGB = leerMatriz(archivoFuente)
    dataGS = convertirAGris(dataRGB)
    matrizX = gradienteX(dataGS)
    matrizY = gradienteY(dataGS)
    data = unificar(matrizX, matrizY)
    data = umbralizar(data)
    escribirMatriz(sys.argv[2], data)
else:
    print("parametros incorrectos.\nuso: ./metodoGradientes archivoEntrada archivoSalida\n\n")
