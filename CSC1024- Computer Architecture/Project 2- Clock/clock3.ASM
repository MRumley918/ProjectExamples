; ======================================================
; ===== Clock0.asm ===================================
; ===== Seven Segment Displays Port 02 =================

	
Start:
	STI

main:
	
	MOV	AL,FA
	CALL	60

	MOV	AL,A
	CALL	60

	MOV	AL,B6
	CALL	60

	MOV	AL,9E
	CALL	60

	MOV	AL,4E
	CALL	60
	
	MOV	AL,DC
	CALL	60

	MOV	AL,FC
	CALL	60

	MOV	AL,8A
	CALL	60

	MOV	AL,FE
	CALL	60

	MOV	AL,DE
	CALL	60
	
	
	

	ORG	60

	OUT	02
	
	MOV	AL,FB	
	OUT	02	

	MOV	AL,B	
	OUT	02	
	
	MOV	AL,B7	
	OUT	02	

	MOV	AL,9F	
	OUT	02	
	
	MOV	AL,4F
	OUT	02

	MOV	AL,DD
	OUT	02

	MOV	AL,FD
	OUT	02

	MOV	AL,8B
	OUT	02

	MOV	AL,FF
	OUT	02

	MOV	AL,DF
	OUT	02

	IRET
	
	
	
	END
; ======================================================
