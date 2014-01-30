#!/bin/sh

echo "Convirtiendo $1 a $2 ..."
octave -q --eval "bmp2f('$1','$2');"
