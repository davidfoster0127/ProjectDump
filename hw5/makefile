#makefile for rescuebin
rescuebin: driver.o animal.o 
	gcc -o rescuebin driver.o animal.o

driver.o: driver.c
	gcc -c driver.c
    
animal.o: animal.c animal.h
	gcc -c animal.c