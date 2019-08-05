#include "Bin.h"
#include "Vector3D.h"
#include <cstdlib>

using namespace std;

Bin::Bin(int max)
{	
	// initally there are 0 objects in the bin
	count = 0;
	// the size of the allocated memory is the number inputted
	size = max;
	// memory is allocated for bin
	bin = (Vector3D*)calloc(size , sizeof(Vector3D));
	if (bin == NULL)
		throw MEMFAIL;
	
	
}
Bin::Bin(const Bin &b) {
	// allocates memory for size of new bin
	bin = (Vector3D*)calloc(b.size , sizeof(Vector3D));
	if (bin == NULL)
		throw MEMFAIL;
	// copies new bin into current bin
	memcpy(bin, b.bin, b.size * sizeof(Vector3D));
	// sets count and size equal to the new bin's values
	count = b.count;
	size = b.size;
}
Bin& Bin::operator=(const Bin &b) {
	// if the two bins are the same return this one
	if (this == &b) {
		return *this;
	}
	// if the other bin is empty clear the bin
	if (b.count == 0)
		clear();
	// set size of bin
	setSize(b.count);
	// copy the bin to the new bin
	memcpy(bin, b.bin, b.count * sizeof(Vector3D));
	return *this;
}
void Bin::clear() {
	// sets the count back to zero
	count = 0;
	// reallocates memory
	bin = (Vector3D*)realloc(bin, sizeof(Vector3D)*size);
}
void Bin::setSize(int newSize) {
	//sets the new size
	count = newSize;
	// if the size is not zero
	if (count != 0) {
		size = count;
		//reallocates memory
		bin = (Vector3D*)realloc(bin, sizeof(Vector3D)*size);
		if (bin == NULL)
			throw MEMFAIL;
	}
	// if size is zero clear bin
	else
		clear();

}

Bin::~Bin()
{
	// destructor, frees memory and sets bin to NULL
	if (bin) {
		free(bin);
		bin = NULL;
	}
	
}
void Bin::add(float x, float y, float z) {
	// creates new Vector3D from xyz values inputted
	Vector3D entry = Vector3D(x, y, z);
	// increases count in bin
	count++;
	// if the number of objects is bigger than the size of memory allocated
	if (count > size) {
		// doubles size of bin's memory and reallocates the memory
		size *= 2;
		bin = (Vector3D*)realloc(bin, sizeof(Vector3D)*size);
		if (bin == NULL)
			throw MEMFAIL;
	}
	// the value at the next entry in the bin is the Vector3D
	bin[count - 1] = entry;
}
void Bin::remove(int pos) {
	// if there is only one value in the bin removing it will clear the bin
	if (count == 1)
		clear();
	else {
		// for all values after the given position, move the entries up by 1
		for (int i = pos - 1; i < count - 1; ++i) {
			bin[i] = bin[i + 1];
		}
		// the count of values decreases by 1
		count--;
	}
}
void Bin::print() {
	cout << "{";
	// iterates over the bin and prints each value out
	for (int i = 0; i < count; ++i) {
		cout << bin[i];
		// sepearates the Vectors with a comma
		if (i < count - 1) {
			cout << ",";
		}
	}
	cout << "}"<<endl;
}
// returns the xyz values of the vector in the bin at a given position
double Bin::getX(int pos) {
	return bin[pos-1].getX();
}
double Bin::getY(int pos) {
	return bin[pos - 1].getY();
}
double Bin::getZ(int pos) {
	return bin[pos - 1].getZ();
}