//Assignment 5 David Foster
#include "animal.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#pragma pack(1)
struct animal {
short int id;
char name[20];
char species[35];
char size;
short int age;
};

int sizeofanimal() {
    return sizeof(struct animal);
}
short int getid(Animal aptr) {
    return aptr->id;
}
char* getname(Animal aptr) {
    return aptr->name;
}
char* getspecies(Animal aptr) {
    return aptr->species;
}
char getsize(Animal aptr) {
    return aptr->size;
}
short int getage(Animal aptr) {
    return aptr->age;
}
void setid(Animal aptr, short int id) {
    aptr->id = id;
}
void setname(Animal aptr, char* name) {
    strcpy(aptr->name, name);
}
void setspecies(Animal aptr, char* species) {
    strcpy(aptr->species, species);
}
void setsize(Animal aptr, char size) {
    aptr->size = size;
}
void setage(Animal aptr, short int age) {
    aptr->age = age;
}