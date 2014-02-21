#!/bin/bash

transformedPath=../videos/transf/
userD="gustavo"

#Fecha actual, con identificador unico...
now=$(date +"%m_%d_%Y")"_"$(date +%s)

rm -R RESULTS*
mkdir "RESULTS_$now"
mkdir "RESULTS_$now/TwoDim"
mkdir "RESULTS_$now/Intensidades"
./programa.py $transformedPath `ls $transformedPath`

mv 2D_* "RESULTS_$now/TwoDim"

currentDir=`pwd`
#cd /home/$userD/LEEDcicima/
#git pull origin master


#mv 2D_* ../RESULTS/
