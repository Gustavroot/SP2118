#!/bin/bash

#Specify ffmpeg PATH
ffmpegPATH=/home/gustavo/Downloads/src0/ffmpeg-0.11.1/ffmpeg

rm -R ../videos/transf/
mkdir ../videos/transf/

#echo "cosa"
stringList=`ls ../videos/`
arrayList=(${stringList//\ / })

#echo ${#arrayList[@]}

counter=0
for i in $(ls ../videos/)
do
    fileToProcess=${arrayList[$counter]}
    #echo $fileToProcess
    if [[ -d "../videos/"$fileToProcess ]]; then
        continue
        #echo "Procesando archivo "${arrayList[$counter]}"..."
    else
        echo ""
        echo "Procesando archivo "${arrayList[$counter]}"..."
        $ffmpegPATH -i "../videos/"$fileToProcess -s 640x480 -b:v 512k -vcodec mpeg1video -acodec copy "TRANSFORMED"$fileToProcess
    fi
    counter=`expr $counter + 1`
done

echo ""

#echo ${arrayList[0]}
