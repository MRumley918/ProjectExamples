Start:
	MOV	AL,2
	CALL	30	; A procedure to double AL
			; Result returned in AL.

ORG	30		; Code starts at address [30]

	ADD	AL,AL	; Do the addition.  Result goes into AL

	RET		; Return from the procedure
END