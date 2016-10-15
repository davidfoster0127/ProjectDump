//David Foster HW02
#include <stdio.h>
#include <string.h>

#define HEADER_SIZE 54

void main() {
    int width = 0;
    int height = 0;
    char filename[20];
    char header[HEADER_SIZE];
    
    printf("Enter the filename: ");
    scanf("%s", filename);
    strcat(filename, ".bmp");
    printf("Enter the height and width (in pixels): ");
    scanf("%d %d", &height, &width);  
    FILE* file = fopen(filename, "rb");
    FILE* out = fopen("copy1.bmp", "wb");  
    unsigned char pixels[height][width * 3];
    unsigned char pixelscopy[height][width * 3];
    fread(header, 1, HEADER_SIZE, file);
    fread(pixels, 1, height * width * 3, file);
    fclose(file);
    memcpy(pixelscopy, pixels, sizeof(pixels));
    
    double red = 0;
    double green = 0;
    double blue = 0;
    int row = 0;
    int column = 0;
    //sepia image filter... pixel arrangment is bgr not rgb
    for(row = 0; row < height; row++) {
        for(column = 0; column < width * 3; column+=3) {
            
            blue = pixels[row][column] * .131 + pixels[row][column + 1] * .534
                + pixels[row][column + 2] * .272;
            if(blue > 255) pixels[row][column] = 255;
            else pixels[row][column] = blue;
            
            green = pixels[row][column] * .168 + pixels[row][column + 1] * .686
                + pixels[row][column + 2] * .349;
            if(green > 255) pixels[row][column + 1] = 255;
            else pixels[row][column + 1] = green;
            
            red = pixels[row][column] * .189 + pixels[row][column + 1] * .769
                + pixels[row][column + 2] * .393;
            if(red > 255) pixels[row][column + 2] = 255;
            else pixels[row][column + 2] = red;
        }
    }
    fwrite(header, sizeof(char), HEADER_SIZE, out);
    fwrite(pixels, sizeof(char), height * width * 3, out);
    fclose(out);
    memcpy(pixels, pixelscopy, sizeof(pixelscopy));
           
    FILE* out2 = fopen("copy2.bmp", "wb");
    //blur image filter
    for(row = 1; row < height - 1; row++) {
        for(column = 3; column < (width * 3) - 3; column++) {//offset by the boundary
            //add the surrounding values
            int total = pixelscopy[row][column] + pixelscopy[row+1][column+3]
                + pixelscopy[row+1][column] + pixelscopy[row+1][column-3]
                + pixelscopy[row][column+3] + pixelscopy[row][column-3]
                + pixelscopy[row-1][column+3] + pixelscopy[row-1][column]
                + pixelscopy[row-1][column-3];
            pixels[row][column] = total/9; 
        } 
    }
    fwrite(header, sizeof(char), HEADER_SIZE, out2);
    fwrite(pixels, sizeof(char), height * width * 3, out2);
    fclose(out2);
    memcpy(pixels, pixelscopy, sizeof(pixelscopy));
           
    FILE* out3 = fopen("copy3.bmp", "wb");
    //horizontal mirror filter
    
    for(row = 0; row < height; row++) {
        for(column = 0; column < (width * 3/2); column+=3) {
            pixels[row][(width * 3) - column] = pixels[row][column];
            pixels[row][(width * 3) - column - 1] = pixels[row][column+2];
            pixels[row][(width * 3) - column - 2] = pixels[row][column+1];
        }
    }       
    fwrite(header, sizeof(char), HEADER_SIZE, out3);
    fwrite(pixels, sizeof(char), height * width * 3, out3);
    fclose(out3);
    memcpy(pixels, pixelscopy, sizeof(pixelscopy));
    
    FILE* out4 = fopen("copy4.bmp", "wb");
    //rotate 180 filter
    for(row = 0; row < height; row++) {
        for(column = 0; column < (width * 3/2); column+=3) {
            int tempB = pixels[row][column];
            int tempG = pixels[row][column + 1];
            int tempR = pixels[row][column + 2];
            pixels[row][column] = pixels[height - row - 1][(width * 3) - (column+3)];
            pixels[row][column + 1] = pixels[height - row - 1][(width * 3) - (column+2)];
            pixels[row][column + 2] = pixels[height - row - 1][(width * 3) - (column+1)];
            pixels[height - row - 1][(width * 3) - (column+3)] = tempB;
            pixels[height - row - 1][(width * 3) - (column+2)] = tempG;
            pixels[height - row - 1][(width * 3) - (column+1)] = tempR;   
        }  
    }
    fwrite(header, sizeof(char), HEADER_SIZE, out4);
    fwrite(pixels, sizeof(char), height * width * 3, out4);
    fclose(out4);
    printf("\nDone. Check the generated images.\n");       
}

