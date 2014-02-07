#!/usr/bin/python

import numpy as np
import cv2
import sys
#from matplotlib import pyplot as plt


try:
    videoFile = cv2.VideoCapture(sys.argv[1])
except:
    print "problem opening input stream"
    sys.exit(1)
if not videoFile.isOpened():
    print "capture stream not open"
    #sys.exit(1)

#print videoFile.read()





'''
cap = cv2.VideoCapture('LEEDgraphite.avi')

print cap

while(cap.isOpened()):
    print "naa"
    ret, frame = cap.read()

    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

    cv2.imshow('frame', gray)
    cv2.waitKey(0)
    if cv2.waitKey(100) & 0xFF == ord('q'):
        break

cap.release()
#cv2.destroyAllWindows()

print "Hola"
'''
