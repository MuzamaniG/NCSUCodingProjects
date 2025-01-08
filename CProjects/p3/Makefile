CC = gcc
CFLAGS = -Wall -std=c99 -g

transit: transit.o map.o numbers.o
	$(CC) $(CFLAGS) -o transit transit.o map.o numbers.o

transit.o: transit.c map.h numbers.h
	$(CC) $(CFLAGS) -c transit.c

map.o: map.c map.h numbers.h
	$(CC) $(CFLAGS) -c map.c

numbers.o: numbers.c numbers.h
	$(CC) $(CFLAGS) -c numbers.c

clean:
	rm -f transit.o map.o numbers.o
	rm -f transit
	rm -f stdout.txt