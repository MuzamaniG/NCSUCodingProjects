CC = gcc
CFLAGS = -Wall -std=c99 -g
DEPS = group.h input.h

fundraiser: fundraiser.o group.o input.o
	$(CC) $(CFLAGS) -o fundraiser fundraiser.o group.o input.o

group.o: group.c group.h input.h
	$(CC) $(CFLAGS) -c group.c

fundraiser.o: fundraiser.c group.h input.h
	$(CC) $(CFLAGS) -c fundraiser.c

input.o: input.c input.h
	$(CC) $(CFLAGS) -c input.c
	
clean:
	rm -f fundraiser.o group.o input.o fundraiser
