CC = gcc
CFLAGS = -Wall -std=c99 -g

# Targets
TARGET = crack
UNIT_TEST = unitTest
OBJECTS = magic.o block.o md5.o password.o crack.o
UNIT_OBJECTS = magic.o block.o md5.o password.o unitTest.o

# Default target to build the crack program
all: $(TARGET)

# Rule to build the crack executable
$(TARGET): $(OBJECTS)
	$(CC) $(CFLAGS) -o $(TARGET) $(OBJECTS)

# Rule to build the unit test program
unitTest: $(UNIT_OBJECTS)
	$(CC) $(CFLAGS) -o $(UNIT_TEST) $(UNIT_OBJECTS)

# Compile magic.c to magic.o
magic.o: magic.c magic.h
	$(CC) $(CFLAGS) -c magic.c

# Compile block.c to block.o
block.o: block.c block.h magic.h
	$(CC) $(CFLAGS) -c block.c

# Compile md5.c to md5.o
md5.o: md5.c md5.h block.h magic.h
	$(CC) $(CFLAGS) -c md5.c

# Compile password.c to password.o
password.o: password.c password.h md5.h block.h magic.h
	$(CC) $(CFLAGS) -c password.c

# Compile crack.c to crack.o
crack.o: crack.c password.h md5.h block.h magic.h
	$(CC) $(CFLAGS) -c crack.c
	
# Clean up object files and the executables
clean:
	rm -f $(TARGET) $(UNIT_TEST) $(OBJECTS) $(UNIT_OBJECTS)

