	.data

inputstring: 	.asciiz "I am a student in University."
outputstring:	.space 100
blank:	.asciiz "\n"

	.text
	.globl main

main:  la  $t0, inputstring		# $t0 is a pointer to the first character in the string. 
       
       li  $v0, 4		# Printing the original string
       la  $a0, inputstring
       syscall
       
       li  $v0, 4		# print a blank line
       la  $a0, blank
       syscall

loop:  lb $t1, 0($t0)    #We take the data from the element in the input string and place it in a new register.
       beq $t1, 0, exit      # We exit if there is nothing in the string.
       blt $t1, 'a', move_string  #If the ASCII value is less than the character 'a' we need to move along the string.
       bgt $t1, 'z', move_string  #If the ASCII value is greater than the character 'z' we need to move along the string.
       sub $t1, $t1, 32    #To get the ASCII value of the capital letter, we subtract 32.
       sb $t1, 0($t0)    #We store back the byte we loaded before.

move_string: #The following is to move along the input string.
       addi $t0, $t0, 1    
       j loop

exit: 
      li  $v0, 4		#Printing the capitalized string
      la  $a0, inputstring
      syscall

