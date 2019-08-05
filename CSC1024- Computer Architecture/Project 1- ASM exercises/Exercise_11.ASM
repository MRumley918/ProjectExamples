; ----- EXAMPLE 5 ------- CHARACTER INPUT OUTPUT --------------------------

; --------------------------------------------------------------
;  Input key presses from the keyboard until Enter is pressed.
; --------------------------------------------------------------
	CLO		; Close unwanted windows.
Rep:
	IN	00	; Wait for key press - Store it in AL.
	MOV 	[C0],AL	; Move the key into address [C0]
	CMP	AL,0D	; Was it the Enter key? (ASCII 0D)
	JNZ	Rep	; No - jump back.  Yes - end.

END
; --------------------------------------------------------------

