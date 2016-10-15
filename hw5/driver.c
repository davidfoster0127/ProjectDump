//Assignment 5 David Foster
#include "animal.h"
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

void listAnimals(FILE *file);
void addAnimal(FILE* file);
void deleteAnimal(FILE* file);
void searchFor(FILE* file);
void writeToCSV(FILE* file);

void main() {
    char r = 'a';
    FILE *file = fopen("rescue.dat", "rb+");
    while (r != '0') {
        r = 'a';
        puts("\nEnter 1 to add a new animal");
        puts("Enter 2 to delete an animal");
        puts("Enter 3 to search for an existing animal");
        puts("Enter 4 to write information to a CSV file");
        puts("Enter 0 to end operations and close the file");
        scanf("%c", &r);    
        switch(r) {
                //my personal display method that lsits the open spots and 
                //the currently archived animals
            case 'l':
                listAnimals(file);
                break;
            case '1':
                addAnimal(file);
                break;
            case '2':
                deleteAnimal(file);
                break;
            case '3':
                searchFor(file);
                break;
            case '4':
                writeToCSV(file);
                break;
            case '0':
                fclose(file);
                exit(0);               
        }
    }
}
void listAnimals(FILE* file) {
    int i = 0;
    int unusedIDs = 0;
    short int ids[10];
    fseek(file, 0, SEEK_SET);
    Animal aptr = malloc(sizeofanimal());
    //read in the first short int
    fread(&unusedIDs, sizeof(short int), 1, file);
    printf("# of available ids: %d\n", unusedIDs);
    for(i = 0; i < 10; i++) {
        fread(&ids[i], sizeof(short int), 1, file);
    }
    for(i = 0; i < unusedIDs; i++) {
        printf("%d ", ids[i]);
    }
    fseek(file, sizeof(short int)*11, SEEK_SET);
    while (fread(aptr, sizeofanimal(), 1, file) != 0) {
        printf("\n%d %s", getid(aptr), getname(aptr));
    }
    printf("\n");
    while(getchar() != '\n');
    free(aptr);
}
void addAnimal(FILE* file) {
    int i = 0;
    int toadd = 0;
    short int temp;
    char c;
    int unusedIDs = 0;
    short int ids[10];
    char *buffer = malloc(sizeof(char) *255);
    Animal aptr = malloc(sizeofanimal());
    
    fseek(file, 0, SEEK_SET);
    //read in unsused ids and the array of positions
    fread(&unusedIDs, sizeof(short int), 1, file);
    for(i = 0; i < 10; i++) {
        fread(&ids[i], sizeof(short int), 1, file);
    } 
    
    //checks for holes in the file to fill  
    if (unusedIDs > 0) {
        //checks if the first entry in array is 0 (no spots to array but may be spots to fill)
        if(ids[0] == 0) {
            while (fread(aptr, sizeofanimal(), 1, file) != 0) {
                if (strcmp(getname(aptr), "unknown") == 0) {
                    toadd = getid(aptr);
                    break;
                }
            }
        //otherwise sets the new id to the first element in the array    
        } else {
            toadd = ids[0];
        }   
        //if no holes exist then it appends to the end of the file
    } else {
        //iterates to the end of the file and just adds one to the id of the last entry
        while (fread(aptr, sizeofanimal(), 1, file) != 0);
        toadd = getid(aptr)+1;
    }
    
    //set position in file to where animal will be added and collect the values one by one
    fseek(file, sizeof(short int)*11 + sizeofanimal()*(toadd-1), SEEK_SET);
    printf("\nNew animal id# %d", toadd);
    setid(aptr, toadd);
    printf("\nEnter animal name: ");
    scanf("%s", buffer);
    setname(aptr, buffer);
    strcpy(buffer, "");
    printf("Enter new animal species: ");
    while(getchar() != '\n');
    scanf("%s", buffer);
    setspecies(aptr, buffer);
    printf("Enter new animal size: ");
    while(getchar() != '\n');
    scanf("%c", &c);
    setsize(aptr, c);
    printf("Enter new animal age: ");
    while(getchar() != '\n');
    scanf("%hd", &temp);
    setage(aptr, temp);
    
    //write new animal to file
    fwrite(aptr, sizeofanimal(), 1, file);
    
    //go back to front of the file and update unused entries and table in the front
    fseek(file, 0, SEEK_SET);
    for (i = 0; i < unusedIDs; i++) {
        ids[i] = ids[i+1];
    }
    ids[9] = 0;
    if (unusedIDs > 0) {
        unusedIDs--;
    }
    fwrite(&unusedIDs, sizeof(short int), 1, file);
    fwrite(&ids, sizeof(short int), 10, file);
    while(getchar() != '\n');
    free(buffer);
    free(aptr);
}

void deleteAnimal(FILE* file) {
    int i = 0;
    int todelete = 0;
    int unusedIDs = 0;
    int fileentries = 0;
    short int ids[10];
    fseek(file, 0, SEEK_SET);
    Animal aptr = malloc(sizeofanimal());
    //read in unused entries and table of holes
    fread(&unusedIDs, sizeof(short int), 1, file);
    for(i = 0; i < 10; i++) {
        fread(&ids[i], sizeof(short int), 1, file);
    } 
    printf("\nEnter id to delete: ");
    scanf("%d", &todelete);
    //check number of entries
    while (fread(aptr, sizeofanimal(), 1, file) != 0) {
        fileentries++;
    }
    fseek(file, sizeof(short int)*11, SEEK_SET);
    
    //check for validity of deletion request in between 0 and the number of entries
    if(0 < i < fileentries) {
        fseek(file, sizeofanimal()*(todelete-1), SEEK_CUR);
        fread(aptr, sizeofanimal(), 1, file);
        //get the animal at that position and check if name is unknown indicating empty point
        if(strcmp(getname(aptr), "unknown") == 0) {
            printf("Empty record\n");
            while(getchar() != '\n');
            free(aptr);
            return;
        } else {
            //otherwise set the name to unknown
            setname(aptr, "unknown");
            fseek(file, sizeof(short int)*11 + sizeofanimal()*(todelete-1), SEEK_SET);
            fwrite(aptr, sizeofanimal(), 1, file);
            fseek(file, 0, SEEK_SET);
            //if more than 10 are deleted, it doesnt append empty spot to the array
            if (unusedIDs < 10) {
                ids[unusedIDs] = todelete;         
            }
            unusedIDs++;
            //write new unused entries and table to beginning of file
            fwrite(&unusedIDs, sizeof(short int), 1, file);
            fwrite(&ids, sizeof(short int), 10, file);
        }
    } else {
        printf("Invalid id#\n");
        while(getchar() != '\n');
        free(aptr);
        return;
    }     
    while(getchar() != '\n');
    free(aptr);
}

void searchFor(FILE* file) {
    int i = 0;
    int search = 0;
    int unusedIDs = 0;
    int fileentries = 0;
    short int ids[10];
    fseek(file, 0, SEEK_SET);
    Animal aptr = malloc(sizeofanimal());
    fread(&unusedIDs, sizeof(short int), 1, file);
    for(i = 0; i < 10; i++) {
        fread(&ids[i], sizeof(short int), 1, file);
    }
    //check number of entries
    while (fread(aptr, sizeofanimal(), 1, file) != 0) {
        fileentries++;
    }
    printf("\nEnter an ID to search for: ");
    scanf("%d", &search);
    //check if search index is within bounds of our file
    if(0 < search < fileentries) {
        fseek(file, sizeof(short int)*11 + sizeofanimal()*(search-1), SEEK_SET);
        fread(aptr, sizeofanimal(), 1, file);
        //check for an empty position
        if (strcmp(getname(aptr), "unknown") == 0) {
            printf("No entry with that ID.");
            while(getchar() != '\n');
            free(aptr);
            return;
            //otherwise print out the animals info
        } else {
            printf("\nAnimal #%hd, %s, %s, %c, %hd", getid(aptr), getname(aptr),
                   getspecies(aptr), getsize(aptr), getage(aptr));
        }
    } else {
        printf("\nInvalid search index.");
        while(getchar() != '\n');
        free(aptr);
        return;
    }
    while(getchar() != '\n');
    free(aptr);
}

void writeToCSV(FILE* file) {
    fseek(file, sizeof(short int)*11, SEEK_SET);
    Animal aptr = malloc(sizeofanimal());
    FILE *csv = fopen("rescue.csv", "w");    
    //for every animal in the file create and animal and then if its name is unknown
    //dont print it, otherise format it to csv file
    while (fread(aptr, sizeofanimal(), 1, file) != 0) {
        if (strcmp(getname(aptr), "unknown")!= 0) {          
        fprintf(csv, "%hd,%s,%s,%c,%hd\n", getid(aptr), getname(aptr),
                getspecies(aptr), getsize(aptr), getage(aptr));
        }    
    }
    free(aptr);
    while(getchar() != '\n');
}