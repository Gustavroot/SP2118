#!/bin/bash

#Specify ffmpeg PATH
ffmpegPATH=/home/gustavo/Downloads/src0/ffmpeg-0.11.1/ffmpeg

directVideos=../videos/
transformedPath=../videos/transf/
rm -R $transformedPath
mkdir $transformedPath

#echo "cosa"
stringList=`ls $directVideos`
arrayList=(${stringList//\ / })

#echo ${#arrayList[@]}

counter=0
for i in $(ls $directVideos)
do
    fileToProcess=${arrayList[$counter]}
    #echo $fileToProcess
    if [[ -d $directVideos$fileToProcess ]]; then
        continue
        #echo "Procesando archivo "${arrayList[$counter]}"..."
    else
        echo ""
        echo "Procesando archivo "${arrayList[$counter]}"..."
        $ffmpegPATH -i $directVideos$fileToProcess -s 640x480 -b:v 512k -vcodec mpeg1video -acodec copy "TRANSFORMED"$fileToProcess
        mv ./TRANSFORMED$fileToProcess $transformedPath
        #transformedPath
    fi
    counter=`expr $counter + 1`
done

echo ""
echo "Transformaciones finalizadas..."

#echo ${arrayList[0]}
