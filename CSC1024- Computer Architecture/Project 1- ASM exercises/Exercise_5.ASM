; ----- EXAMPLE 2 ------- HEXADECIMAL --------------------------

; ===== CONTROL THE TRAFFIC LIGHTS =============================

	CLO		; Close unwanted windows.
			; Turn off all the traffic lights.
	MOV AL,0	; Copy 00000000 into the AL register.
	OUT 01		; Send AL to Port One (The traffic lights).
Start:
			; Both lights are red
	MOV AL,90	; Copy 10010000 into the AL register.
	OUT 01		; Send AL to Port One (The traffic lights).
			; Light 1: Red Light 2: Red/Amber
	MOV AL,98	; Copy 10011000 into the AL register.
	OUT 01		; Send AL to Port One (The traffic lights).
			; Light 1: Red/Amber Light 2: Green
	MOV AL,C4	; Copy 11000100 into the AL register.
	OUT 01		; Send AL to Port One (The traffic lights).
			; Light 1: Green Light 2: Amber
	MOV AL,28	; Copy 10010000 into the AL register.
	OUT 01		; Send AL to Port One (The traffic lights).
			; Light 1: Amber Light 2: Red
	MOV AL,50	; Copy 01010000 into the AL register.
	OUT 01		; Send AL to Port One (The traffic lights).
	JMP Start	; Jump back to the start.
	END		; Program ends.
	
; ===== Program Ends ==========================================