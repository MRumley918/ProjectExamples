
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
	JNZ	Rep	; No - jump back.  Yes - continue.
	MOV 	AL,A0	; Set AL to be the first inputted character
	MOV	BL,C0	; Set BL to be the first VDU memory adress
	
print:
	MOV	CL,[AL] ; Move the value in AL to CL
	MOV	[BL],CL ; Make CL the value in BL
	INC	BL	; Increase BL
	INC	AL	; Increase AL
	CMP	CL,0D	; Is the next character enter?
	JNZ	print	; If yes, end the program, if not, continue

END
; --------------------------------------------------------------