#include <stdio.h>
#include "pm.h"             // for glo.h:  mp, call_nr, who_p etc.
#include "mproc.h"          // for proc table

// system call for library function printchildpids
int do_printchildpids() {
	// pid is the first int passed to the function by message 
	pid_t pid = m_in.m1_i1;
	// prints out the first line with the given pid
	printf("Searching for children of process: %d\n",pid);
	// if the pid is greater than 0
	if(pid){
		// iterates through the number of processes in the process table 
		for(int i=0; i<NR_PROCS;i++){
			// finds the pid of the parent process 
			pid_t ppid = mproc[mproc[i].mp_parent].mp_pid;
			// if the pids are equal and not zero
			if(pid==ppid&&mproc[i].mp_pid != 0 ){
				// the child pid is printed out 
				printf("%d\n",mproc[i].mp_pid);
			}
		}
		// the value of 0 is returned
		return 0;
	}
	// if the pid is less than 0
    return -1;
}

// system call for library function getnchildren
int do_getnchildren() {
	// the pid is the first int passed to the functon by the message 
    pid_t pid = m_in.m1_i1;
	// an int to keep a running total of the number of children 
	int nchildren = 0;
	if(pid){
		// iterates through the number of processes in the process table 
		for(int i=0; i<NR_PROCS;i++){
			// finds the pid of the parent process 
			pid_t ppid = mproc[mproc[i].mp_parent].mp_pid;
			// if the pids are equal and not zero
			if(pid==ppid&&mproc[i].mp_pid != 0 ){
				// the number of children is incremented
				nchildren++;
			}
		}
		
	}
	// the number of children is returned
	return nchildren;
	// if the pid is less than 1 then -1 is returned
	if(pid<1){
		return -1;
	}
    
}

// system call for library function getchildpids
int do_getchildpids() {
	// the pid is set to the first int in the message
	pid_t pid= m_in.m1_i1;
	// the int nchildren is set to the second int in the message
	int nchildren = m_in.m1_i2;
	// the address of the array is set to the first pointer in the message 
	pid_t *childpids= m_in.m1_p1;
	// a running total of the number of elements in the array
	int n=0;
	// an array of pids is generated of the size nchildren
	pid_t childpidscopy[nchildren];
	if(pid){
		// iterates through the processes in the process table
		for(int i=0; i<NR_PROCS;i++){
			pid_t ppid = mproc[mproc[i].mp_parent].mp_pid;
			// if the number of elements in the array is more than the size of the array then its copied over and the size of the array is returned
			if(n>nchildren){
				sys_vircopy(SELF,(long)childpidscopy,who_e,*childpids,sizeof(pid_t)*nchildren);
				return nchildren;
			}
			// if the process is a child then its pid is added to the array and the number is incremented
			if(pid==ppid){
				childpidscopy[n]= mproc[i].mp_pid;
				n++;
				
			}
			
		}
		// the array is copied to the library function and its size is returned
		sys_vircopy(SELF,(long)childpidscopy,who_e,*childpids,sizeof(pid_t)*nchildren);
		return n;
		
	}
	// if there are any errors then the return value is -1   
    return -1;
}
