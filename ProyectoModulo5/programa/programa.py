#!/usr/bin/python

import numpy as np
import cv2
import sys


drawing = False # true if mouse is pressed
mode = False # if True, draw rectangle. Press 'm' to toggle to curve
ix,iy = -1,-1


#img = cv2.imread('/home/gustavo/Desktop/Progra_Verano/GITrepo/ProyectoModulo5/testImgs/Blackeyegalaxy.jpg',0)
#img=None
#print "hola"
#img=np.zeros((512,512,3), np.uint8)

def funcionDetencionYReanudacion(counter, filename2, img2):
    global mode

    while(1):
        cv2.imshow(filename2, img2)
        kp=cv2.waitKey(200) & 0xFF
        if kp==ord('m'):
            mode = not mode
        elif kp==ord('q'):
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

def draw_lines(event, x, y, flags, param):
    global ix,iy,drawing,mode

    if event == cv2.EVENT_LBUTTONDOWN:
        drawing = True
        ix,iy = x,y

    elif event == cv2.EVENT_MOUSEMOVE:
        if drawing == True:
            if mode == True:
                cv2.rectangle(param,(ix,iy),(x,y),(0,255,0),-1)
            else:
                cv2.circle(param,(x,y),5,(0,0,255),-1)

    elif event == cv2.EVENT_LBUTTONUP:
        drawing = False
        if mode == True:
            cv2.rectangle(param,(ix,iy),(x,y),(0,255,0),-1)
        else:
            cv2.circle(param,(x,y),1,(0,0,255),-1)

    #if event == cv2.EVENT_LBUTTONDBLCLK:
    #    #print param
    #    cv2.circle(param,(x,y),100,(255,0,0),-1)

#A esta funcion se le pasara cada uno de los archivos de video, para ser procesado
def procesado(filename):
    print "Iniciando procesado del archivo %s..." %filename
    tmpVideo = cv2.VideoCapture(sys.argv[1]+filename)
    tmpRet, tmpFrame=tmpVideo.read()
    cap = cv2.VideoCapture(sys.argv[1]+filename)
    #print cap.get(3)
    #cap.set(CV_CAP_PROP_FRAME_WIDTH, 1280)
    #cap.set(CV_CAP_PROP_FRAME_HEIGHT, 1024)
    while True:
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
            break
    tmpVideo.release()
    cap.release()
    #Buscar una forma de liberar estos dos sin que genere error...!
    #cap.release()
    #cv2.destroyAllWindows()
    print "Procesamiento del archivo %s se ha completado." %filename


#Se hace un llamado a procesado, con nombres de archivos de video
#procesado("LEEDgraphite.wmv")


print "\nPROGRAMA PARA EL PROCESADO DE VIDEOS OBTENIDOS A PARTIR DE LEED\n"
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
