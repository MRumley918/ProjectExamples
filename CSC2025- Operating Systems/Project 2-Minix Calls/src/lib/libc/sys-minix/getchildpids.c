#include <lib.h>
#include <unistd.h>
#include <errno.h>

int printchildpids(pid_t pid) {

    message m;      // Minix uses message to pass parameters to a system call
    
    m.m1_i1 = pid;    // set first integer of message to pid
	// if the pid is less than 1 then -1 is returned and the errno is set
    if(pid<1){
		return -1;
		errno = EINVAL;
	}
	// the syscall is made to PRINTCHILDPIDS using the message
    _syscall(PM_PROC_NR, PRINTCHILDPIDS, &m);
    
}

int getnchildren(pid_t pid) {
    message m;      // Minix uses message to pass parameters to a system call
    
    m.m1_i1 = pid;    // set first integer of message to i
	// if the pid is less than 1 then -1 is returned and the errno is set
    if(pid<1){
		return -1;
		errno = EINVAL;
	}
	// the syscall is made to GETNCHILDREN using the message
   return _syscall(PM_PROC_NR, GETNCHILDREN, &m);
  
}
        
int getchildpids(pid_t pid, int nchildren, pid_t *childpids) {
    message m;	// Minix uses message to pass parameters to a system call
	m.m1_i1 = pid;  // set first integer of message to pid
	m.m1_i2 = nchildren;	 // set second integer of message to nchildren
	m.m1_p1 = childpids;	// set first pointer of message to childpids
	// if the pid is less than 1 or the array is too small
	if(pid<1||nchildren<0){
		return -1;
		errno = EINVAL;
	}
	// the syscall is made to GETCHILDPIDS using the message
	return  _syscall(PM_PROC_NR, GETCHILDPIDS, &m);
    
}
