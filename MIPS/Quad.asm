	.data
	a: .asciiz "a: "
	b: .asciiz "b: "
	c: .asciiz "c: "
	blank:	.asciiz "\n"
	.text
	.globl main
main:	#Printing out a, b and c ONE BY ONE for the user to input and moving the user input into registers.
        li $v0, 4
	la $a0, a
	syscall
	
	li $v0, 5
	syscall
	
	move $t0, $v0     #Moving the integer "a" to a register.
	
	li $v0, 4
	la $a0, b
	syscall
	
	li $v0, 5
	syscall
	
	move $t1, $v0        #Moving the integer "b" to a register.
	
	li $v0, 4
	la $a0, c
	syscall
	
	li $v0, 5
	syscall
	
	move $t2, $v0       #Moving the integer "c" to a register.
	
	addi $s0, $0, 0  #Initializing the counter to 0.
loop:   addi $s0, $s0, 1 #Incrementing the counter starting from 1.
	bgt $s0, $t2, exit #Exit if the counter exceeds the input "c".
	mult $s0, $s0    #Squaring the value of the counter
	mfhi $t3 #Placing the result in
	mflo $t4 #the product register
	sub $t5, $t4, $t0 #Subtracting x squared from the input "a".
	rem $t6, $t5, $t1 #The remainder of the divion between (x squared minus a) and b
	beq $t6, $0, results #If the remainder is zero, go to results.
	j loop

#Printing the valid integers. If nothing prints, then there is no solution.
results: li $v0, 1
	 move $a0, $s0
	 syscall
         li  $v0, 4		# Printing a blank line to separate the valid integers.
         la  $a0, blank
         syscall
	 j loop

exit:
	
