//Assignment 5 David Foster
#ifndef ANIMAL_H
#define ANIMAL_H

typedef struct animal *Animal;

short int getid(Animal aptr);
char* getname(Animal aptr);
char* getspecies(Animal aptr);
char getsize(Animal aptr);
short int getage(Animal aptr);

void setid(Animal aptr, short int id);
void setname(Animal aptr, char* name);
void setspecies(Animal aptr, char* species);
void setsize(Animal aptr, char size);
void setage(Animal aptr, short int age);

#endif