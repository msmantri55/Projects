

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <assert.h>

int bmp_open( char* bmp_filename,        unsigned int *width, 
              unsigned int *height,      unsigned int *bits_per_pixel, 
              unsigned int *padding,     unsigned int *data_size, 
              unsigned int *data_offset, unsigned char** img_data ){


  FILE* bmp_file;
  char fheader [2];
    *width=0;
    *height=0;
    *bits_per_pixel=0;
    *padding=0;
    *data_size=0;
    *data_offset=0;
  bmp_file = fopen( bmp_filename, "r" );
  if( bmp_file == NULL ){
	  return -1;
  }
        fread(fheader, sizeof(char), 2, bmp_file);
	    fread(data_size, sizeof(unsigned int), 1, bmp_file);
		fread(data_offset, sizeof(unsigned int), 1, bmp_file);
		fread(data_offset, sizeof(unsigned int), 1, bmp_file);
        
		fread(width, sizeof(unsigned int), 1, bmp_file);
		fread(width, sizeof(unsigned int), 1, bmp_file);
		fread(height, sizeof(unsigned int), 1, bmp_file);
        fread(bits_per_pixel, sizeof(char), 2, bmp_file);
		fread(bits_per_pixel, sizeof(char), 2, bmp_file);
		rewind(bmp_file);
		
		
		if(((*bits_per_pixel)*(*width))%32 == 0) {
		 *padding = 0;
		 }
		 else if (((*bits_per_pixel)*(*width))%32 == 8) {
		 *padding = 3;
		 }
		 else if (((*bits_per_pixel)*(*width))%32 == 16) {
		 *padding = 2;
		 }
		 else if (((*bits_per_pixel)*(*width))%32 == 24) {
		 *padding = 1;
		 }
		
		*img_data = (unsigned char*)malloc(*data_size);
		fread(*img_data, 1, *data_size, bmp_file);
		
		

		
		 
         fclose(bmp_file);
  
  return 0;  
}


void bmp_close( unsigned char **img_data ){

  if( *img_data != NULL ){
    free( *img_data );
    *img_data = NULL;
  }
}

int bmp_mask( char* input_bmp_filename, char* output_bmp_filename, 
              unsigned int x_min, unsigned int y_min, unsigned int x_max, unsigned int y_max,
              unsigned char red, unsigned char green, unsigned char blue )
{
  unsigned int img_width;
  unsigned int img_height;
  unsigned int bits_per_pixel;
  unsigned int data_size;
  unsigned int padding;
  unsigned int data_offset;
  unsigned char* img_data    = NULL;
  
  int open_return_code = bmp_open( input_bmp_filename, &img_width, &img_height, &bits_per_pixel, &padding, &data_size, &data_offset, &img_data ); 
  
  if( open_return_code ){ printf( "bmp_open failed. Returning from bmp_mask without attempting changes.\n" ); return -1; }
 
  
    FILE* bmp_output;
	bmp_output = fopen( output_bmp_filename, "w");
	if( bmp_output == NULL ){
	  return -1;
  }
    unsigned char* picture_data = data_offset + img_data;
	if(x_max > img_width || y_max > img_height) {
	return -1;
	}
	
	int y_MAX, y_MIN;
	for(int i = x_min - 50; i < x_min + 50; i++) {
		y_MAX = sqrt(2500-pow(abs(i-x_min), 2)) + y_min;
		y_MIN = -sqrt(2500-pow(abs(i-x_min), 2)) + y_min;
	for (int j = y_MIN; j < y_MAX; j++) {
		// Calculates the size of the row in bytes. Padding along with width*3 because each pixel has 3 bytes/3 primary colors.
    picture_data[ i*(img_width*3+padding) + j*3 + 2 ] = red;
	picture_data[ i*(img_width*3+padding) + j*3 + 1 ] = green;
	picture_data[ i*(img_width*3+padding) + j*3 + 0 ] = blue;
	}
	}

    fwrite(img_data, 1, data_size, bmp_output);	
    fclose(bmp_output);
	
  
  bmp_close( &img_data );
  
  return 0;
}         

int bmp_collage( char* bmp_input1, char* bmp_input2, char* bmp_result, int x_offset, int y_offset ){

  unsigned int img_width1;
  unsigned int img_height1;
  unsigned int bits_per_pixel1;
  unsigned int data_size1;
  unsigned int padding1;
  unsigned int data_offset1;
  unsigned char* img_data1    = NULL;
  
  int open_return_code = bmp_open( bmp_input1, &img_width1, &img_height1, &bits_per_pixel1, &padding1, &data_size1, &data_offset1, &img_data1 ); 
  
  if( open_return_code ){ printf( "bmp_open failed for %s. Returning from bmp_collage without attempting changes.\n", bmp_input1 ); return -1; }
  
   
  unsigned int img_width2;
  unsigned int img_height2;
  unsigned int bits_per_pixel2;
  unsigned int data_size2;
  unsigned int padding2;
  unsigned int data_offset2;
  unsigned char* img_data2    = NULL;
  
  open_return_code = bmp_open( bmp_input2, &img_width2, &img_height2, &bits_per_pixel2, &padding2, &data_size2, &data_offset2, &img_data2 ); 
  
  if( open_return_code ){ printf( "bmp_open failed for %s. Returning from bmp_collage without attempting changes.\n", bmp_input2 ); return -1; }
  
   

  unsigned int canvas_size;
  unsigned int canvas_width;
  unsigned int canvas_height;
  unsigned int canvas_padding;
  unsigned int img_datasize;
  unsigned int header;
 
  
  if(x_offset > img_width1 || y_offset > img_height1) {
  return -1;
  }
  
  if(x_offset < 0 || y_offset < 0) {
  return -1;
  }
  
  if(x_offset + img_width2 > img_width1) {
  canvas_width = x_offset + img_width2;
  }
  if(x_offset + img_width2 <= img_width1) {
  canvas_width = img_width1;
  }
  if(y_offset + img_height2 > img_height1) {
  canvas_height = y_offset + img_height2;
  }
  if(y_offset + img_height2 <= img_height1) {
  canvas_height = img_height1;
  }
  

  
  if(((bits_per_pixel1)*(canvas_width))%32 == 0) {
		 canvas_padding = 0;
		 }
		 else if (((bits_per_pixel1)*(canvas_width))%32 == 8) {
		 canvas_padding = 3;
		 }
		 else if (((bits_per_pixel1)*(canvas_width))%32 == 16) {
		 canvas_padding = 2;
		 }
		 else if (((bits_per_pixel1)*(canvas_width))%32 == 24) {
		 canvas_padding = 1;
		 }
		 
    img_datasize = ((3*canvas_width)+canvas_padding)*canvas_height;
	
	// data offset is equivalent to the size of the header.
	canvas_size = img_datasize + data_offset1;	 
	
	unsigned char* combinedimage = NULL;
	
	combinedimage = (unsigned char*)malloc(canvas_size);
    memcpy(combinedimage, img_data1, data_offset1);	
	// Skipping bm to get to canvas size, then referring to bmp open and skipping everything to width and then height.
    *(combinedimage + 2) = canvas_size;
    *(combinedimage + 18) = canvas_width;
	*(combinedimage + 22) = canvas_height;
    
	FILE* collage_file;
	collage_file = fopen(bmp_result, "w");
	
	if(collage_file == NULL) {
	return -1;
	}
    
	unsigned char* picture_data = data_offset1 + combinedimage;
	
	// Making the canvas white. 0xff is 255 in hexadecimal.
	for(int i = 0; i < canvas_height-1; i++) {
	for (int j = 0; j < canvas_width-1; j++) {
    picture_data[ i*(canvas_width*3+canvas_padding) + j*3 + 2 ] = 0xff;
	picture_data[ i*(canvas_width*3+canvas_padding) + j*3 + 1 ] = 0xff;
	picture_data[ i*(canvas_width*3+canvas_padding) + j*3 + 0 ] = 0xff;
	}
	}
	
	// Copying img1 onto canvas.
	char* img1_memory = NULL;
	img1_memory = data_offset1 + img_data1;
	for(int i = 0; i < img_height1-1; i++) {
	for (int j = 0; j < img_width1-1; j++) {
		// Each pixel has 3 colours so we are copying the values of each colour from img1 into the canvas.
    picture_data[ i*(canvas_width*3+canvas_padding) + j*3 + 2 ] = img1_memory[ i*(img_width1*3+padding1) + j*3 + 2 ];
	picture_data[ i*(canvas_width*3+canvas_padding) + j*3 + 1 ] = img1_memory[ i*(img_width1*3+padding1) + j*3 + 1 ];
	picture_data[ i*(canvas_width*3+canvas_padding) + j*3 + 0 ] = img1_memory[ i*(img_width1*3+padding1) + j*3 + 0 ];
	}
	}
	
	// Copying img2 onto canvas.
	char* img2_memory = NULL;
	img2_memory = data_offset2 + img_data2;
	int i, j, k, l;
	for(i = y_offset, k = 0; k < img_height2-1; i++, k++) {
	for (j = x_offset, l = 0; l < img_width2-1; j++, l++) {
		// Each pixel has 3 colours so we are copying the values of each colour from img2 into the canvas.
		// Treating char* pic data as an array which is possible.
    picture_data[ i*(canvas_width*3+canvas_padding) + j*3 + 2 ] = img2_memory[ k*(img_width2*3+padding2) + l*3 + 2 ];
	picture_data[ i*(canvas_width*3+canvas_padding) + j*3 + 1 ] = img2_memory[ k*(img_width2*3+padding2) + l*3 + 1 ];
	picture_data[ i*(canvas_width*3+canvas_padding) + j*3 + 0 ] = img2_memory[ k*(img_width2*3+padding2) + l*3 + 0 ];
	}
	}
	// line above can also be written as: *(picture_data + i*(canvas_width*3+canvas_padding) + j*3 + 2);
	
	fwrite(combinedimage, 1, canvas_size, collage_file);

  

      
  bmp_close( &img_data1 );
  bmp_close( &img_data2 );
  
  return 0;
}                  
