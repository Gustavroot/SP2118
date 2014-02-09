#!/usr/bin/python

import numpy as np
import cv2
import sys


drawing = False # true if mouse is pressed
#mode = False # if True, draw rectangle. Press 'm' to toggle to curve
ix,iy = -1,-1

#La siguiente lista contrendra un listado de listas,
#cada una de las listas internas es un conjunto de puntos,
#con cada uno de esos conjuntos de puntos se dibujaran poligonos
#en los frames posteriores al dibujado
#Esta primera lista es la mas general... contiene un listado con n
#elementos, 1 por cada video. Cada elemento es un listado de los lazos
#correspondientes al video correspondiente
#La lista listadoFilesLazos parece ser completamente innecesaria...
listadoFilesLazos=[]
generalList=[]
specificList=[]


#img = cv2.imread('/home/gustavo/Desktop/Progra_Verano/GITrepo/ProyectoModulo5/testImgs/Blackeyegalaxy.jpg',0)
#img=None
#print "hola"
#img=np.zeros((512,512,3), np.uint8)


#En caso de presionar la letra 'q', se activa esta funcion,
#la cual detiene el programa para poder dibujar
#Si se vuelve a presionar 'q', se reanuda
def funcionDetencionYReanudacion(counter, filename2, img2):
    #global mode
    while(1):
        cv2.imshow(filename2, img2)
        kp=cv2.waitKey(200) & 0xFF
        #if kp==ord('m'):
        #    mode = not mode
        if kp==ord('q'): #elif
            print "Reanudado..."
            break
    '''
    if cv2.waitKey(200) & 0xFF != ord('q'):
        cv2.imshow(filename2, img2)
        counter+=200
        funcionDetencionYReanudacion(counter, filename2, img2)
    else:
        print "Reanudado..."
    '''

#Esta funcion es la que se llama al dibujar figuras (poligonos)
def draw_lines(event, x, y, flags, param):
    global generalList, specificList
    global ix,iy,drawing#,mode
    #Click inicial

    if event == cv2.EVENT_LBUTTONDOWN:
        specificList=[]
        bufferArray=[x,y]
        specificList.append(bufferArray)
        drawing = True
        ix,iy = x,y
    #Arrastrado del mouse
    elif event == cv2.EVENT_MOUSEMOVE:
        if drawing == True:
            bufferArray=[x,y]
            specificList.append(bufferArray)
            cv2.circle(param,(x,y),5,(0,0,255),-1)
            #Intento inicial de dibujo de lineas... fallo
            #cv2.line(param,(ix,iy),(x,y),(255,0,0),5)
            #if mode == True:
            #    cv2.rectangle(param,(ix,iy),(x,y),(0,255,0),-1)
            #else:
            #    cv2.circle(param,(x,y),5,(0,0,255),-1)
    #Cuando se suelta el mouse, se detiene el dibujado, y se completa
    #asi una figura cerrada
    elif event == cv2.EVENT_LBUTTONUP:
        bufferArray=[x,y]
        specificList.append(bufferArray)
        drawing = False
        cv2.circle(param,(x,y),1,(0,0,255),-1)
        #if mode == True:
        #    cv2.rectangle(param,(ix,iy),(x,y),(0,255,0),-1)
        #else:
        #    cv2.circle(param,(x,y),1,(0,0,255),-1)
        generalList.append(specificList)
        #Dibujado de poligono:
        pts = np.array(specificList, np.int32)
        pts = pts.reshape((-1,1,2))
        cv2.polylines(param,[pts],True,(255,0,0))
        #print specificList
        #print generalList
        print "Cantidad de lazos dibujados hasta el momento: 1 global mas %d locales" %(len(generalList)-1)

    #Este if funcionada para el caso de que se de doble click
    #if event == cv2.EVENT_LBUTTONDBLCLK:
    #    #print param
    #    cv2.circle(param,(x,y),100,(255,0,0),-1)

#A esta funcion se le pasara cada uno de los archivos de video, para ser procesados
def procesado(filename):
    global listadoFilesLazos, generalList

    print "\nIniciando procesado del archivo %s..." %filename
    tmpVideo = cv2.VideoCapture(sys.argv[1]+filename)
    tmpRet, tmpFrame=tmpVideo.read()
    cap = cv2.VideoCapture(sys.argv[1]+filename)
    #print cap.get(3)
    #cap.set(CV_CAP_PROP_FRAME_WIDTH, 1280)
    #cap.set(CV_CAP_PROP_FRAME_HEIGHT, 1024)
    while True:
        estadoFinal="sin interrumpir"
        ret, frame = cap.read()
        #Setting width and height
        #... pending
        if type(frame)!=type(tmpFrame):
            break
        #print np.size(frame, 1)
        #print np.size(frame, 0)
        #img = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
        img = cv2.cvtColor(frame, 0)
        #print type(img)
        #print filename
        cv2.namedWindow(filename)
        cv2.setMouseCallback(filename,draw_lines, img)
        cv2.imshow(filename, img)

        #cv2.namedWindow(filename,img)
        #cv2.setMouseCallback(filename,draw_lines)
        keyFinger=cv2.waitKey(15) & 0xFF
        if keyFinger == ord('q'):
            #Cuando se presiona la tecla q, se detiene hasta que se vuelva a presionar dicha letra
            print "Detenido..."
            funcionDetencionYReanudacion(0, filename, img)
            #keyFinger = cv2.waitKey(0) & 0xFF
            #if keyFinger == ord('q'):
            #    continue
        elif keyFinger == ord('s'):
            estadoFinal="interrumpido"
            break
    tmpVideo.release()
    cap.release()
    #Buscar una forma de liberar estos dos sin que genere error...!
    #cap.release()
    #cv2.destroyAllWindows()
    listadoFilesLazos.append(generalList)
    generalList=[]
    print "Procesamiento del archivo %s se ha completado (%s)." % (unicode(filename), unicode(estadoFinal))


#Se hace un llamado a procesado, con nombres de archivos de video
#procesado("LEEDgraphite.wmv")


print "\nPROGRAMA PARA EL PROCESADO DE VIDEOS OBTENIDOS A PARTIR DE LEED"
print "(al dibujar las regiones de conteo, recuerde que la primera es la global, y las siguientes son las relacionadas con curvas de intensidad)\n"
counter=0
for elem in sys.argv:
    if counter>=2:
        #print sys.argv[counter]
        #print counter
        procesado(sys.argv[counter])
    counter+=1


#print sys.argv[1]
#print sys.argv[2]
#print len(sys.argv)


print "\nFin del programa"
