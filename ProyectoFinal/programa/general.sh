#!/bin/bash

transformedPath=../videos/transf/

rm -R RESULTS
mkdir RESULTS
./programa.py $transformedPath `ls $transformedPath`



#mv 2D_* ../RESULTS/
