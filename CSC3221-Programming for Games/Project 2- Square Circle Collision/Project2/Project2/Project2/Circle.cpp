#include "Circle.h"
using namespace std;

// a circle with no radius parameter will have a radius of 1 by default
Circle::Circle() :Shape(), rad(1)
{
}
Circle::Circle(int rad) : Shape(), rad(rad)
{
}
Circle::Circle(int x, int y) : Shape(x, y), rad(1)
{
}
Circle::Circle(int x, int y, int rad) : Shape(x, y), rad(rad)
{
}
Circle::~Circle()
{
}
//prints the circle
void Circle::print() {
	cout << "Circle at " << getX() << " , " << getY() << " radius " << getRad() << endl;

}