//David Foster HW3
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// number of words in a typical Unix dictionary file
#define DICT_WORDS 99171
// number of replacement suggestions
#define SUGGESTIONS 10

void findSuggestions(char *dict[], char *suggest[], char word[]);


int main(int argc, char *argv[] ) {
 
    FILE *fptr = fopen((char*)argv[1], "r");
    // set up a string that will be used to read one word at a time from the dictionary file  
    char word[60];
    // set up a string array for the dictionary, where each row/word will be allocated dynamically based on the actual word length
    char *dict[DICT_WORDS];
    // set up a string array for the top 10 suggestions, where each row/word will be allocated dynamically based on the actual word length
    char *suggest[SUGGESTIONS];
    int index = 0;
    int found = 0;
    
    while (fscanf(fptr, "%s", word) > 0) {
        dict[index] = (char*) malloc(sizeof(word));
        strcpy(dict[index], word);
        index++;
    }
    
    //check for correct argument #
    if(argc == 3) {
        strcpy(word, argv[2]);
        //iterate through and look for exact match
        for (index = 0; index <DICT_WORDS; index++) {
            if (strcmp(word, dict[index]) == 0) {
                printf("%s found\n", word);
                found = 1;
                break;
            } 
        }
        if (found == 0) {
            findSuggestions(dict, suggest, word);   
        }
        
    } else {
        printf("Wrong number of arguments\n");
    }
    
   fclose(fptr);
   return 0;
    
}
//Looks for top ten suggestions for a mispelled word
//Pre: dictionary char* array, suggestion char* array, char array word to be found
//Post: fills suggest[] with 10 suggestions found by searching for like words via 6 criterion
void findSuggestions(/*in*/ char *dict[], /*in/out*/ char *suggest[], /*in*/ char word[]) {
    int suggestions = 0;
    int n = 0;
    int count = 0;
    int index = 0;
    int found = 0;
    
    //search criterion 1
    for (index = 0; index<DICT_WORDS; index++) {
        char dword[60];
        strcpy(dword, dict[index]);
        // find all words of the same length 
        if (strlen(dict[index]) == strlen(word)){
            //compare each char in order and mark any differences
            for(n = 0; n < strlen(word); n++) {
                if (word[n] != dword[n]) {
                    count++;
                }
            }
            //check for identical words already suggested
            if (suggestions > 0) {
                    for (n = 0; n < suggestions; n++) {
                        if (strcmp(dict[index], suggest[n]) == 0) {
                            found = 1;
                        }
                    }
                }
            //if only one difference is present and there are less than 10 suggestions and the word is not already in the list
            if (count == 1 && suggestions < 10 && found == 0) {
                suggest[suggestions] = (char*) malloc(sizeof(dict[index]));
                strcpy(suggest[suggestions], dict[index]);
                count = 0;
                suggestions++;
            }
            found = 0;
            count = 0;
        }
    }
    
    //search criterion 2
    for (index = 0; index < DICT_WORDS; index++) {
        char dword[60];
        strcpy(dword, dict[index]);
        //find all words of the same length+1
        if (strlen(dict[index]) == (strlen(word) +1)) {
            //check for duplicates
            if (suggestions > 0) {
                    for (n = 0; n < suggestions; n++) {
                        if (strcmp(dict[index], suggest[n]) == 0) {
                            found = 1;
                        }
                    }
                }
            //compare the two strings only for the length of the users word
            if (strncmp(dict[index], word, strlen(word)) == 0 && suggestions < 10 && found == 0) {
                suggest[suggestions] = (char*) malloc(sizeof(dict[index]));
                strcpy(suggest[suggestions], dict[index]);
                suggestions++;
            }
            found = 0;
        }   
    }
    
    //search criterion 3
    for (index = 0; index < DICT_WORDS; index++) {
        char dword[60];
        strcpy(dword, dict[index]);
        //find all words of the same length-1
        if (strlen(dict[index]) == (strlen(word) -1)) {
            //check for duplicates
            if (suggestions > 0) {
                    for (n = 0; n < suggestions; n++) {
                        if (strcmp(dict[index], suggest[n]) == 0) {
                            found = 1;
                        }
                    }
                }
            //compare the two strings for the length of the users word minus the last char
            //really should only produce one suggestion if any exists
            if (strncmp(dict[index], word, (strlen(word)-1)) == 0 && suggestions < 10 && found == 0) {
                suggest[suggestions] = (char*) malloc(sizeof(dict[index]));
                strcpy(suggest[suggestions], dict[index]);
                suggestions++;
            }
            found = 0;
        }
    
    }
    
    //search criterion 4
    for (index = 0; index<DICT_WORDS; index++) {
        char dword[60];
        strcpy(dword, dict[index]);
        //find all word of the same length if the length of the word is longer than 5
        if (strlen(dict[index]) == strlen(word) && strlen(word) > 5){
            //check for differences between each char
            for(n = 0; n < strlen(word); n++) {
                if (word[n] != dword[n]) {
                    count++;
                }
            }
            //check for duplicates
            if (suggestions > 0) {
                    for (n = 0; n < suggestions; n++) {
                        if (strcmp(dict[index], suggest[n]) == 0) {
                            found = 1;
                        }
                    }
                }
            //if 2 differences then add it to the list
            if (count == 2 && suggestions < 10 && found == 0) {
                suggest[suggestions] = (char*) malloc(sizeof(dict[index]));
                strcpy(suggest[suggestions], dict[index]);
                count = 0;
                suggestions++;
            }
            found = 0;
            count = 0;
        }
    }
    
    //search criterion 5
    for (index = 0; index < DICT_WORDS; index++) {
        char dword[60];
        strcpy(dword, dict[index]);
        //find words shorter than the (word -1)*2 and at least as long as the word +1
        if (strlen(dict[index]) <= (strlen(word)-1)*2 && strlen(dict[index]) >= strlen(word) +1) {
            //check for duplicates
            if (suggestions > 0) {
                    for (n = 0; n < suggestions; n++) {
                        if (strcmp(dict[index], suggest[n]) == 0) {
                            found = 1;
                        }
                    }
                }
            //checks to see if the first word length -1 chars match
            if (strncmp(dict[index], word, (strlen(word)-1)) == 0 && suggestions < 10 && found == 0) {
                suggest[suggestions] = (char*) malloc(sizeof(dict[index]));
                strcpy(suggest[suggestions], dict[index]);
                suggestions++;
            }
            found = 0;
        }
    
    }
    
    //search criterion 6
    for (index = 0; index < DICT_WORDS; index++) {
        char dword[60];
        char fword[60];
        strcpy(dword, dict[index]);
        //find words shorter than the (word -1)*2 and at least as long as the word +1
        if (strlen(dict[index]) <= (strlen(word)-1)*2 && strlen(dict[index]) >= strlen(word)+1) {
            strncpy(fword, word, (strlen(word)-1));
            //check for duplicates
            if (suggestions > 0) {
                    for (n = 0; n < suggestions; n++) {
                        if (strcmp(dict[index], suggest[n]) == 0) {
                            found = 1;
                        }
                    }
                }
            //look for substring (user word minus last char) anywhere in the dict word
            if (strstr(dict[index], fword) != NULL && suggestions < 10 && found == 0) {
                suggest[suggestions] = (char*) malloc(sizeof(dict[index]));
                strcpy(suggest[suggestions], dict[index]);
                suggestions++;
            }
            found = 0;
        }
    }
    
    printf("Suggestions: \n");
    for (index = 0; index < suggestions; index++) {
        printf("%s\n", suggest[index]);
    }
    

}