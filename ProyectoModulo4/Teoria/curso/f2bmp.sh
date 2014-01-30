#!/bin/sh

echo "Convirtiendo $1 a $2 ..."
octave -q --eval "f2bmp('$1','$2');"
