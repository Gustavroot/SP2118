#!/bin/bash

echo "------------------------------------------------------------------------"
echo "Inicializaciones..."

python setup.py build
sudo python setup.py install

./programa.py

gnuplot scriptGNUPLOT
gnuplot scriptGNUPLOTanalitico

#echo "eso"
