//David Foster HW1
#include <stdio.h>
#include <stdbool.h>
#include <stdint.h>
#include <math.h>

#define BITS 8

int sumCalculation(unsigned int n);

int main(void) {
    int checkType, i = 0;
    char c,tempc = 0;
    unsigned int csint, tempcsint = 0;
    while (c != 'q') {
    printf("\nWhat type of display do you want?");
    printf("\nEnter 1 for character parity, 2 for integer checksum: ");
    scanf("%d", &checkType);
        if ( checkType == 1) {
            printf("You have chosen parity.");
            printf("\nEnter a character for parity calculation: ");
            scanf("%c", &c);
            //fixes some weird newline problems
            if (c == '\n') scanf("%c", &c);
            printf("\nCharacter: %c, bit representation: ", c);
            //protective copy... unnecessary??
            tempc = c;
            int counter = 0;
            //compares bits of mask with char and outputs findings. counts 1's
            int mask = 1 << 7;
            for (i = 1; i <= sizeof(char) * BITS; i++) {
            if (tempc & mask){
	            putchar('1'); counter++; }
            else putchar('0');
            tempc <<= 1;
            } 
            printf("\nNumber of ones: %d", counter); //3
            tempc = c;
            printf("\nEven 1 parity for the character is: "); 
            //if counter is even then it outputs a 0 in front and a 1 if odd 
            for (i = 1; i <= sizeof(char) * BITS; i++) {
            if (counter % 2 == 0 && i == 1) putchar('0');
            else if(counter % 2 == 1 && i == 1)	putchar('1');
            else if(tempc & mask){
            putchar('1'); }
            else putchar('0');
            tempc <<= 1;
            }
            
        } else {
            printf("You have chosen checksum.");
            printf("\nEnter an integer for checksum calculation: ");
            scanf("%d", &csint);
            printf("\nInteger: %d, bit representation: ", csint);
            tempcsint = csint;
            int mask1 = 1 << 31;
            //compares int to the mask and then shifts them over. outputs a space every 8 bits
            for (i = 1; i <= sizeof(int) * BITS; i++) {
				if (tempcsint & mask1) putchar('1');
				else putchar('0');
				tempcsint <<= 1;
				if (! (i % 8)) putchar(' ');
   			}
            tempcsint = csint;
            //calculates sum of 4 bytes in sumcalculation
            int result = sumCalculation(tempcsint);
            printf("\nSum of the number is: %d", result);
            //mod 255 for the checksum
            int checksum = result % 255;
            printf("\nChecksum of the number is: %d, Bit representation: ", checksum);
            //reads out checksum in 8 bits
            int mask2 = 1 << 7;
            for (i = 1; i <=sizeof(char)*BITS; i++) {
            if (checksum & mask2) putchar('1');
            else putchar('0');  
                checksum<<=1;
            }
        }
    printf("\nEnter r to repeat, q to quit: ");
    scanf("%c", &c);
    if (c == '\n') scanf("%c", &c);
    if (c == 'q') break;
    }	
}

int sumCalculation(unsigned int n) {		
    int i, result = 0;
    //calculate the sum by splitting ints into 8 bit chunks
    for (i = 0; i <sizeof(int); i++) {    
    result += (n>>(8*i))&255;
    } 
    return result;
}