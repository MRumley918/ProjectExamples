; ----- EXAMPLE 4 ------- COUNTING AND JUMP --------------------------

	MOV	BL,1	; Initial value stored in BL

Rep:			; Jump back to this label
	MUL	BL,2	; Multiply BL by TWO
	JMP	Rep	; Jump back to Rep

	END		; Program Ends

; ===== Program Ends ===============================