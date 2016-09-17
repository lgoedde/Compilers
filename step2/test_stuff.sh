#! /bin/bash

FILES=testcases/input/*

for f in $FILES
do
	echo "Testing $f"
	 java -cp lib/antlr.jar:classes/ Micro $f
done