#pragma once
#include <iostream>
using namespace std;
class Vector3D
{
public:
	// initial vector is (0,0,0)
	Vector3D(){
		x = 0;
		y = 0;
		z = 0;
	}
	//empty destructor (not needed)
	~Vector3D(void){}


	// sets values to user inputs
	Vector3D(const double x, const double y, const double z) {
		this->x = x;
		this->y = y;
		this->z = z;
	}
	// copy constructor
	Vector3D(const Vector3D &v);
	// getters and setters for xyz values
	double getX() const;
	double getY() const;
	double getZ() const;
	void setX(const double newX);
	void setY(const double newY);
	void setZ(const double newZ);
	// computes magnitude of vector
	double magnitude();
	// Vector product 
	Vector3D operator%(const Vector3D &a);
	// Scalar product
	double operator*(const Vector3D &a);
	// Arithmetic operators
	Vector3D operator+(const Vector3D &a);
	Vector3D operator-(const Vector3D &a);
	Vector3D operator*(const double a);
	Vector3D operator/(const double a);
	// returns unit vector pointing in same direction as vector
	Vector3D unitVector();
	// returns unit vector orthogonal to the two vectors 
	Vector3D orthUnitVector(const Vector3D &a);
	// Assignment operator
	Vector3D& operator=(const Vector3D &a);
	// Relational operators
	bool operator==(const Vector3D &a);
	bool operator!=(const Vector3D &a);
	// Update operators
	Vector3D& operator+=(const Vector3D &a);
	Vector3D& operator-=(const Vector3D &a);

	// overwritten << operator to easily output Vector3D object
	inline friend ostream& operator<<(ostream& o, const Vector3D& v) {
		o << "(" << v.x << "," << v.y << "," << v.z << ")" ;
		return o;
	}

	
private:
	// xyz values of vector
	double x;
	double y;
	double z;
};

