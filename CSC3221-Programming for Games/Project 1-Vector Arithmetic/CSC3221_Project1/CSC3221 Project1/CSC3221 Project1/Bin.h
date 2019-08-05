#pragma once
#include "Vector3D.h"
#include <iostream>
using namespace std;
class Bin
{
public:
	// constructor takes int size of bin
	Bin(int max);
	// copy constructor
	Bin(const Bin &b);
	// destructor
	~Bin();
	//assignment operator
	Bin& operator= (const Bin &b);

	// return xyz values at position 'pos' in the bin
	double getX(int pos);
	double getY(int pos);
	double getZ(int pos);

	// adds new Vector3D to end of bin
	void add(float x, float y, float z);
	// prints the bin
	void print();
	// removes the Vector3D at position 'pos' in bin and shifts other values up
	void remove(int pos);
	// exception enum
	enum exception{MEMFAIL};
	
	
private:
	// clears the bin of all values
	void clear();
	// sets the new bin size
	void setSize(int newSize);
	// bin of Vector3D objects
	Vector3D* bin;
	// number of objects in the bin
	int count;
	// size of memory allocated for bin
	int size;
};

