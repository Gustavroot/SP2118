#!/usr/bin/python

import numpy as np
import cv2
import sys

#A esta funcion se le pasara cada uno de los archivos de video, para ser procesado
def procesado(filename):
    print "Iniciando procesado del archivo %s..." %filename
    tmpVideo = cv2.VideoCapture('../videos/'+filename)
    tmpRet, tmpFrame=tmpVideo.read()
    cap = cv2.VideoCapture('../videos/'+filename)
    #print cap.get(3)
    #cap.set(CV_CAP_PROP_FRAME_WIDTH, 1280)
    #cap.set(CV_CAP_PROP_FRAME_HEIGHT, 1024)
    while(cap.isOpened()):
        ret, frame = cap.read()
        #Setting width and height
        #... pending
        if type(frame)!=type(tmpFrame):
            break
        #print np.size(frame, 1)
        #print np.size(frame, 0)
        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
        cv2.imshow('frame',gray)
        if cv2.waitKey(15) & 0xFF == ord('q'):
            break
    tmpVideo.release()
    cap.release()
    #cv2.destroyAllWindows()
    print "Procesamiento del archivo %s se ha llevado a cabo por completo." %filename

#Se hace un llamado a procesado, con nombres de archivos de video
#procesado("LEEDgraphite.wmv")

counter=0
for elem in sys.argv:
    if counter>=1:
        #print sys.argv[counter]
        procesado(sys.argv[counter])
    counter+=1



#print sys.argv[1]
#print sys.argv[2]
#print len(sys.argv)

print "\nFin del programa"
