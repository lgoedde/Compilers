LIB_ANTLR := lib/antlr.jar
ANTLR_SCRIPT := Micro.g4

all: group compiler fib

group:
	@echo "Lucas Goedde Michael Baio"
compiler:
	rm -rf build
	mkdir build
	java -cp $(LIB_ANTLR) org.antlr.v4.Tool -o build $(ANTLR_SCRIPT)
	rm -rf classes
	mkdir classes
	javac -cp $(LIB_ANTLR) -d classes src/*.java build/*.java

fib:
	java -cp classes/:lib/antlr.jar Micro fibonacci.micro > fibonacci.scanner
	cat fibonacci.scanner
clean:
	rm -rf classes build

.PHONY: all group compiler clean
