
; ----- EXAMPLE 5 ------- CHARACTER INPUT OUTPUT --------------------------

; --------------------------------------------------------------
;  Input key presses from the keyboard until Enter is pressed.
; --------------------------------------------------------------
	CLO		; Close unwanted windows.
	MOV 	BL,A0	; BL intitially points to the first memory address 
Rep:
	IN	00	; Wait for key press - Store it in AL.
	MOV 	[BL],AL	; Move the inputted key into the memory address
	INC	BL	; Move along to the next memory address
	CMP	AL,0D	; Was it the Enter key? (ASCII 0D)	
	JNZ	Rep	; No - jump back.  Yes - end.
	
print:
	
	MOV	AL,[A0]
	MOV	[C0],AL
	INC	AL
	JMP	print

END
; --------------------------------------------------------------