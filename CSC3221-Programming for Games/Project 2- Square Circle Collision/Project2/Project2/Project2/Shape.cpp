#include "Shape.h"



Shape::Shape(int x, int y) :Centre_x(x), Centre_y(y)
{

}
Shape::~Shape() {

}
// adds the movement distances to the shapes coordinates
void Shape::move(int x, int y) {
	Centre_x += x;
	Centre_y += y;
}
