; ----- EXAMPLE 4 ------- COUNTING AND JUMP --------------------------
	
	MOV	AL,1	; Initial value stored in AL
	MOV	BL,0	; Initial value stored in BL
	MOV	[A0],AL ; Initial value in the memory location

Rep:			; Jump back to this label
	ADD	BL,AL	; Add AL to BL and store it in BL
	MOV	AL,[A0]	; Replace value in memory (the previous number in the sequence)
	MOV	[A0],BL	; Return the value to BL
	JMP	Rep	; Jump back to Rep

	END		; Program Ends

; ===== Program Ends ===============================