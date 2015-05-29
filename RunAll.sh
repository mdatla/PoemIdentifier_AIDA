    #!/bin/bash

cd src/
echo "Hello"
javac execute/PrepareProgram.java
java execute/PrepareProgram
javac execute/RunPageSegmentaion.java
java execute/RunPageSegmentaion

NOME=$1
c=0
a=1
+++
if grep  "$NOME" data/Image_Lists/ImageList.txt ; then
        echo "CREATING ARRAY"
        while read line
        do
                myArray[$c]=$line # store line
                c=$(expr $c + 1) # increase counter by 1
        done < data/Image_Lists/ImageList.txt

else
        echo "Name not found"
fi

cd src/ 
javac execute/RunProgram.java


for i in ${myArray[@]} ;
        do
		echo "Processing Image $a"
		java execute/RunProgram 5 C $i
		a=$(expr $a + 1)
	done
