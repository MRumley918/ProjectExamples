#include "Vector3D.h"
#include "Bin.h"
#include <iostream>

using namespace std;

int main() {
	// Initialise 3 vectors
	Vector3D v1(2, 1, 3);
	Vector3D v2(3, 3, 3);
	Vector3D v3(1, 1, 1);
	//print the vectors
	cout << "V1 : "<< v1 << endl;
	cout << "V2 : "<< v2 << endl;
	cout << "V3 : " << v3 << endl;
	//Expected result (5,4,6)
	cout << "V1 + V2 = "<<v1+v2 << endl;
	//Expected result (-1,-2,0)
	cout << "V1 - V2 = " << v1 - v2 << endl;
	//Expected result (10,5,15)
	cout << "V1 * 5 = " << v1 * 5 << endl;
	//Expected result 18
	cout << "V1 * V2 (Scalar Product) " << v1*v2 << endl;
	//Expected result (6,-3,-3)
	cout << "V1 % V2 (Vector Product) " << v1%v2 << endl;
	//Expected result (1,1,1)
	cout << "V2 / 3 =" << v2 / 3 << endl;
	//Expected result 1.73205080757
	cout << "Magnitude of V3: " << v3.magnitude() << endl;
	//Expected result (0.57735,0.57735,0.57735)
	cout << "Unit vector of V3: " << v3.unitVector() << endl;
	//Expected result (-0.816497,0.408248,0.408248)
	cout << "Orthogonal unit vector of V2 and V1: " << v2.orthUnitVector(v1) << endl;
	v2 = v3;
	//Expect v2 to now be (1,1,1)
	cout << "New V2 value " << v2 << endl;
	//Expected value 0(false)
	cout << "Is v1 equal to v2? " << (v1 == v2) << endl;
	//Expected value 1(true)
	cout << "Is V1 equal to itself? " << (v1 == v1) << endl;
	//Expected value 1(true)
	cout << "Is V1 not equal to V2? " << (v1 != v2) << endl;
	//Expected value 0(false)
	cout << "Is V1 not equal to itself? " << (v1 != v1) << endl;
	// += assignment operator
	(v1 += v2);
	// V1 now equals (3,2,4)
	cout << "V1 now equals"<<v1 << endl;
	//-= assignment operator
	(v1 -= v2);
	//V1 now equals (2,1,3)
	cout << "V1 now equals" << v1 << endl;
	// Expected result 2
	cout << "V1 X value: "<<v1.getX() << endl;
	// Expected result 1
	cout << "V1 Y value: " << v1.getY() << endl;
	// Expected result 3
	cout << "V1 Z value: " << v1.getZ() << endl;
	//Set new xyz values for V1
	v1.setX(9);
	v1.setY(9);
	v1.setZ(9);
	//V1 should now equal (9,9,9)
	cout << "New V1 value: " << v1 << endl;

	// Initialise 2 Bins
	Bin testBin(5);
	Bin testBin2(3);
	//add values to these bins
	
	testBin.add(2, 1, 3);
	testBin.add(3, 3, 3);
	testBin.add(4, 4, 4);
	testBin.add(5, 5, 5);
	testBin.add(1, 1, 1);
	// 6th vector added to bin of size 5- allocates extra memory
	testBin.add(6, 6, 6);
	testBin2.add(4, 5, 6);
	testBin2.add(6, 7, 8);
	testBin2.add(8, 5, 3);
	cout << "Test Bin 1: ";
	testBin.print();
	cout << "Test Bin 2: ";
	testBin2.print();
	// remove 2nd value from bin
	testBin.remove(2);
	cout << "Test bin 1 with second value removed: ";
	testBin.print();
	// first test bin becomes equal to second bin
	testBin = testBin2;
	cout << "Test Bin 1 = Test Bin 2 ";
	testBin.print();
	
	
	
}