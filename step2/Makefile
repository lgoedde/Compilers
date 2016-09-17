LIB_ANTLR := lib/antlr.jar
ANTLR_SCRIPT := Micro.g4

all: group compiler 

group:
	@echo "Lucas Goedde Michael Baio"
compiler:
	rm -rf build
	mkdir build
	java -cp $(LIB_ANTLR) org.antlr.v4.Tool -o build $(ANTLR_SCRIPT)
	rm -rf classes
	mkdir classes
	javac -cp $(LIB_ANTLR) -d classes src/*.java build/*.java

#fib:
#	java -cp classes/:lib/antlr.jar Micro testcases/fibonacci.micro > testcases/fibonacci.scanner
#	diff -b -B testcases/fibonacci.scanner testcases/fibonacci.out

#loop:
#	java -cp classes/:lib/antlr.jar Micro testcases/loop.micro > testcases/loop.scanner
#	diff -b -B testcases/loop.scanner testcases/loop.out

#nested:
#	java -cp classes/:lib/antlr.jar Micro testcases/nested.micro > testcases/nested.scanner
#	diff -b -B testcases/nested.scanner testcases/nested.out
	
#sqrt:
#	java -cp classes/:lib/antlr.jar Micro testcases/sqrt.micro > testcases/sqrt.scanner
#	diff -b -B testcases/sqrt.scanner testcases/sqrt.out



clean:
	rm -rf classes build

.PHONY: all group compiler clean
