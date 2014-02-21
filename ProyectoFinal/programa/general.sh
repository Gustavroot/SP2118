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
mv Datos_* "RESULTS_$now/Intensidades"

#Ahora se procede a generar las grafias con gnuplot...
stringList=`ls "RESULTS_$now/Intensidades"`
arrayList=(${stringList//\ / })
for i in $(ls "RESULTS_$now/Intensidades")
do
    fileToProcess=${arrayList[$counter]}
    #echo $fileToProcess
    gnuplot << EOF
    #Ahora se puede hacer gnuplot scripting aqui...
    set output  "PS_$fileToProcess.ps"
    set title "Intensidad vs Tiempo"
    set xlabel "Tiempo"
    set ylabel "Intensidad"
    plot "RESULTS_$now/Intensidades/$fileToProcess" using 1:2 with lines
    #Una vez generado el .ps...
    set term postscript
    set output "PS_$fileToProcess.ps"
    replot
    #set term x11
    #Se genera el png...
    set term png
    set output "Grafica_$fileToProcess.png"
    replot
    #set term x11
EOF
    counter=`expr $counter + 1`
done

rm PS_*
mv Grafica_* "RESULTS_$now/Intensidades"


read -p "¿Desea almacenar la informacion recien procesada? (s o n) " answr
if [ "$answr" == "s" ]
then
    currentDir=`pwd`
    cd /home/$userD/LEEDcicima/
    git pull origin master
    cp -R $currentDir"/RESULTS_$now" .
    git add -A
    git commit -m "Analisis computacional de LEED: "$now
    echo "Si se le pide un usuario y la correspondiente contraseña a continuacion, es la informacion de su usuario de Github:"
    git push origin master
fi


#mv 2D_* ../RESULTS/
