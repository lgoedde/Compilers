#!/bin/bash
# Author: Brian Rieder
# Date:   17 September 2016

make
zero=0;
cases_passed=0
cases_total=0
for testfile in `ls testcases/input/ | sort -V` ; do
  testnum=${testfile%.micro}
  echo "Testing file: $testfile"
  output=$(java -cp classes/:lib/antlr.jar Micro testcases/input/$testfile)
  expected=$(cat testcases/output/$testnum.out)
  diffs=$(diff <(echo "$output") <(echo "$expected") | wc -l)
  if  [ $diffs = 0 ] ; then
      printf "\033[0;32mPASSED\n\033[0m"
      (( cases_passed += 1 ))
      (( cases_total += 1 ))
    else
      printf "\033[0;31mFAILED\n\033[0m"
      diff -y <(echo "$output") <(echo "$expected")
      (( cases_total += 1 ))
    fi
  echo "" # newline


    #output=$(echo $output | tr -d '\r')
    
done
echo "Results: $cases_passed / $cases_total"
