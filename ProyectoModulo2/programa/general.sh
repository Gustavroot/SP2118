#!/bin/bash

echo "------------------------------------------------------------------------"
echo "Inicializaciones..."

python setup.py build
sudo python setup.py install

./programa.py

#echo "eso"
