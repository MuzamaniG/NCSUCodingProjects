# Compiler and flags
CFLAGS += -Wall -std=c99 -g
LDLIBS +=

# Default target
driver: driver.o input.o map.o value.o
	gcc -o driver driver.o input.o map.o value.o $(LDLIBS)

# Clean rule
clean:
	rm -f driver *.o *.gcda *.gcno *.gcov

.PHONY: clean
