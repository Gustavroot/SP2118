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

read -p "¿Desea almacenar la informacion recien procesada? (s o n)" answr
if [ "$answr" == "s" ]
then
    currentDir=`pwd`
    cd /home/$userD/LEEDcicima/
    git pull origin master
    cp $currentDir"/RESULTS_$now" -R .
    git add -A
    git commit -m "Analisis computacional de LEED: "$now
    echo "Si se le pide un usuario y la correspondiente contraseña a continuacion, es la informacion de su usuario de Github:"
    git push origin master
fi


#mv 2D_* ../RESULTS/
