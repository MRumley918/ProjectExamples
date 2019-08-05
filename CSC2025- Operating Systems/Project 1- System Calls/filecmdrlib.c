/*
 * Replace the following string of 0s with your student number
 * 150251891
 */
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <pwd.h>
#include <dirent.h>
#include <time.h>
#include <string.h>
#include <stdint.h>
#include <stddef.h>
#include <errno.h>
// you will probably need other system library header files included here

#include "filecmdrlib.h"

#define MODE_S_LEN  11
#define TIME_S_LEN  17

/* 
 * is_user_exec helper function to test user execute permission for given 
 * file mode and owner uid and gid.
 * Uses getuid() to get the uid of the calling process and getgid() to get the 
 * gid of the calling process.
 * This function is not part of the filecmdrlib interface.
 */ 
bool is_user_exec(mode_t mode, uid_t ouid, gid_t ogid) {
    if (ouid == getuid())
        return mode & S_IXUSR;
    
    if (ogid == getgid())
        return mode & S_IXGRP;
        
    return mode & S_IXOTH;
}

/* 
 * The following functions are defined in filecmdrlib.h and need to be
 * modified to comply with their definitions and the coursework specification.
 */
 
int execfile(char *path) {
	
	char* args[3];
	char str[1024];
	// first argument must be the path to be executed
	args[0]=path;
	// last argument must be null terminator
	args[2]=NULL;
	printf("Enter any arguments for %s: ",path);
	scanf("%s",str);
	// first argument is user inputted string
	// if there is no argument CTRL+D has to be used- this should be bypassed  
	args[1]=str;
	// executes path with specified arguments
	execv(path,args);
	
    return 0;
}

int listdir(char *path) {
	// converts the path to a directory
	DIR *d= opendir(path);
	struct dirent *dir;
	// checks to see if the directory is valid
	if(d){
		// while loop checks directory has files
		 while ((dir = readdir(d)) != NULL){
			 // prints each file/directory in the directory
			 printfinf(dir->d_name);
		 }
		 // closes the directory to free memory
		 closedir(d);
	}
	else 
		// fails if unable to open directory
		return -1;
	
    return 0;
}

int listfile(char *path) {
	// opens the file path
	FILE* file = fopen(path, "r"); 
	// number of lines of the file read
    size_t nread;
	// reads 1024 characters at a time
	char buf[1024];
	// if file can be opened 
	if (file){
		// while the number of lines read is greater than 0
		while((nread = fread(buf, 1, sizeof buf, file)) > 0)
			// writes the characters to stdout
			fwrite(buf, 1, nread, stdout);
		// closes the file to free memory 
		fclose(file);
	}
	else 
		// fails if unable to open file
		return -1;

    
   printf("\n");

  
    return 0;
}

char *mode2str(mode_t mode, uid_t ouid, gid_t ogid) { 
	// if the mode is bigger or smaller than it can be then program fails and errno is set 
	if (mode<MODE_MIN||mode>MODE_MAX)
		errno=EDOM;
	// allocates memory 	
    char *mode_s = (char *) calloc(MODE_S_LEN, sizeof(char));
	//is it a regular file?
	if(S_ISREG(mode)){
		// uses the is_user_exec boolean to see if the file is an executable or not 
		if (is_user_exec(mode,ouid,ogid))
			mode_s[0]=*"e";
		else 
			mode_s[0]=*"f";
	}
	
	//is it a directory?
	else if(S_ISDIR(mode))
		mode_s[0]=*"d";
	//is it a symbolic link?
	else if(S_ISLNK(mode))
		mode_s[0]=*"l";
	//other
	else 
		mode_s[0]=*"o";
	// sets the other characters in the mode using if statements
	mode_s[1]=*(mode & S_IRUSR ? "r" : "-");
	mode_s[2]=*(mode & S_IWUSR ? "w" : "-");
	mode_s[3]=*(mode & S_IXUSR ? "x" : "-");
	mode_s[4]=*(mode & S_IRGRP ? "r" : "-");
	mode_s[5]=*(mode & S_IWGRP ? "w" : "-");
	mode_s[6]=*(mode & S_IXGRP ? "x" : "-");
	mode_s[7]=*(mode & S_IROTH ? "r" : "-");
	mode_s[8]=*(mode & S_IWOTH ? "w" : "-");
	mode_s[9]=*(mode & S_IXOTH ? "x" : "-");
	
	
	
    return mode_s;
}

int printfinf(char *path) {
    // creates filestat object
	struct stat fileStat;
	if(stat(path,&fileStat) < 0)    
        return -1;
	struct passwd *pwd;
	// gets the user id as a string from the int provided by st_uid
	pwd=getpwuid(fileStat.st_uid);
	// allocates memory for mode 
	char *pathmode= malloc(MODE_S_LEN);
	// performs mode2str on the paths mode 
	pathmode=mode2str(fileStat.st_mode,fileStat.st_uid,fileStat.st_gid);
	// prints the required information as a string 
    printf("%s %s %lld %s %s \n",pathmode,pwd->pw_name,fileStat.st_size,time2str(fileStat.st_mtime),path);
	// returns the correct FTYPE based on the first letter of the mode string
	if(pathmode[0]==*"e")
		return FTYPE_EXE;
	else if(pathmode[0]==*"f")
		return FTYPE_REG;
	else if(pathmode[0]==*"d")
		return FTYPE_DIR;
	else if(pathmode[0]==*"l")
		return FTYPE_LNK;
	else if(pathmode[0]==*"o")
		return FTYPE_OTH;
    else 
		return FTYPE_ERR;
}

char *time2str(time_t time) {
	// sets up a time structure with the time_t variable. passes through localtime to convert it to the local time 
	struct tm *info= localtime(&time);
	// the correct format of the time and date 
    static char *str_fmt = "%d/%m/%Y %H:%M";
	// allocates the required memory for a time string 
	char *time_s = malloc(TIME_S_LEN); 
	// formats the time into time_s 
	strftime(time_s,TIME_S_LEN,str_fmt,info);
    return time_s;
}

int useraction(int ftype, char *path) {
    static const char *action_prompt[] = {
        "Do you want to list the directory %s (y/n): ",
        "Do you want to execute %s (y/n): ",
        "Do you want to list the file %s (y/n): " 
    };
	
	// values of ftype correspond to the correct string in action_prompt 
    printf(action_prompt[ftype],path);
	// answer only needs a single character 
	char answer;
	scanf(" %c",&answer);
	// scanf needs to be cleared so if the user enters multiple chars it produces an error e.g if the user enters 'yellow' then it still counts as 'y'
	
	// checks to see if answer is Y or y 
	if(answer == 'y'|| answer=='Y'){
		// checks the ftype to determine which method is to be run 
		if(ftype==FTYPE_DIR)
			listdir(path);
		if(ftype==FTYPE_EXE)
			execfile(path);
		if(ftype==FTYPE_REG)
			listfile(path);
	}
    
    return 0;
}
