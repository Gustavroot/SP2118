#!/bin/sh

if [ $# -eq 2 ]; then
a=$1
b=$2
else
read a;
read b;
fi

for i in `seq $a $b`; do
	echo "Calculando i = $i";
	expr $i \* $i >> a.dat
	echo "Fin."
done

echo "Fin del programa."
