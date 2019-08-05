; ----- EXAMPLE 3 ------- USING ASCII CODES --------------------------

; ---------------------------------------------------------------
	CLO		; Close unwanted windows.
; ===== IMMEDIATE MOVES =====
	MOV	AL,48	; Copy 48 HEX into the AL register
	MOV	BL,45	; Copy 45 HEX into the BL register
	MOV	CL,4C	; Copy 4C HEX into the CL register
	MOV	DL,4F	; Copy 4F HEX into the DL register

; ===== DIRECT MOVES =====
	MOV	[C0],AL	; Copy value in AL to RAM location [C0]
	MOV	[C1],BL	; Copy value in BL to RAM location [C1]
	MOV	[C2],CL	; Copy value in CL to RAM location [C2]
	MOV	[C3],CL	; Copy value in CL to RAM location [C3]
	MOV	[C4],DL	; Copy value in DL to RAM location [C4]
	
	END
; ---------------------------------------------------------------

