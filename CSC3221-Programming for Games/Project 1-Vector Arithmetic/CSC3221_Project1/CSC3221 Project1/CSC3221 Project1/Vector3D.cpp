#include "Vector3D.h"
#include <math.h>
//gets xyz values of vector
double Vector3D::getX()const {
	return x;
}
double Vector3D::getY()const {
	return y;
}
double Vector3D::getZ()const {
	return z;
}
// sets xyz values to new inputs
void Vector3D::setX(const double newX) {
	x = newX;
}
void Vector3D::setY(const double newY) {
	y = newY;
}
void Vector3D::setZ(const double newZ) {
	z = newZ;
}
// return magnitude of vector
double Vector3D::magnitude() {
	return sqrt((x*x) + (y*y) + (z*z));
}
// Vector Product
Vector3D Vector3D::operator%(const Vector3D &a) {
	return Vector3D((a.y*z) - (a.z*y), (a.z*x) - (a.x*z), (a.x*y) - (a.y*x));
}
// Unit Vector
Vector3D Vector3D::unitVector() {
	double mag = magnitude();
	return Vector3D((x / mag), (y / mag), (z / mag));

}
//Orthogonal unit vector
Vector3D Vector3D::orthUnitVector(const Vector3D &a) {
	Vector3D orthogonal = operator%(a);
	double modOrthogonal = orthogonal.magnitude();
	return Vector3D((orthogonal.x / modOrthogonal), (orthogonal.y / modOrthogonal), (orthogonal.z / modOrthogonal));
}
// arithmetic operators
Vector3D Vector3D::operator+(const Vector3D &a) {
	return Vector3D((x + a.x), (y + a.y), (z + a.z));
}

Vector3D Vector3D::operator-(const Vector3D &a) {
	return Vector3D((x - a.x), (y - a.y), (z - a.z));
}
Vector3D Vector3D::operator*(const double a) {
	return Vector3D((x*a), (y*a), (z*a));
}
double Vector3D::operator*(const Vector3D &a) {
	return (a.x*x) + (a.y*y) + (a.z*z);
}
Vector3D Vector3D::operator/(const double a) {
	return Vector3D((x / a), (y / a), (z / a));
}
//assignment operator
Vector3D& Vector3D::operator=(const Vector3D &a) {
	if (*this == a) {
		return *this;
	}
	x = a.x;
	y = a.y;
	z = a.z;
	return *this;

}
// copy constructor
Vector3D::Vector3D(const Vector3D &v) {
	x = v.x;
	y = v.y;
	z = v.z;
}
//relational operators
bool Vector3D::operator==(const Vector3D &a) {
	return (a.x == x&&a.y == y&&a.z == z) ? true : false;
}
bool Vector3D::operator!=(const Vector3D &a) {
	return(a.x == x&&a.y == y&&a.z == z) ? false : true;


}
// update operators
Vector3D& Vector3D::operator+=(const Vector3D &a) {
	return Vector3D(x += a.x,
		y += a.y,
		z += a.z);
}
Vector3D& Vector3D::operator-=(const Vector3D &a) {
	return Vector3D(x -= a.x,
		y -= a.y,
		z -= a.z);
}
