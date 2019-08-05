; ----- EXAMPLE 4 ------- COUNTING AND JUMP --------------------------

	MOV	BL,40	; Initial value stored in BL

Rep:			; Jump back to this label
	DEC	BL	; Decrease BL by one
	JMP	Rep	; Jump back to Rep

	END		; Program Ends

; ===== Program Ends ===============================