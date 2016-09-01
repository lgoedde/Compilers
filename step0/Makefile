all: group compiler

group:
	echo "Lucas Goedde:lgoedde, Michael Baio:mbaio"

compiler: helloworld

helloworld: helloworld.c
	$(CC) -o $@ $<

clean:
	rm helloworld
