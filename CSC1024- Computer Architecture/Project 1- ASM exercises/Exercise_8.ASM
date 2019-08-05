; ----- EXAMPLE 4 ------- COUNTING AND JUMP --------------------------

	MOV	BL,40	; Initial value stored in BL

Rep:			; Jump back to this label
	ADD	BL,3	; Add 3 to BL
	JMP	Rep	; Jump back to Rep

	END		; Program Ends

; ===== Program Ends ===============================