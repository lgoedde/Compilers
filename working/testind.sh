#!/bin/bash
# Author: Mike Baio
# Date:   8 November 2016

testPath=$1
fileName=$2

inputFile=$testPath
inputFile+="input/"
inputFile+=$fileName
inputFile+=".input"

microFile=$testPath
microFile+="input/"
microFile+=$fileName
microFile+=".micro"

outFile=$fileName
outFile+=".actual"

givenOut=$fileName
givenOut+="output/"
givenOut+=$fileName
givenOut+=".out"

tinyFile=$testPath
tinyfile+="output/"
tinyFile+=$fileName
tinyFile+=".tinyout"

$(java -cp classes/:lib/antlr.jar Micro $microFile > $inputFile < $outFile)

$(tiny $outFile < $inputFile > actual.out)

