#include "Square.h"


// a square with no edge parameter has an edge of 1 by default
Square::Square() :Shape(), edge(1)
{
}
Square::Square(int edge) : Shape(), edge(edge)
{
}
Square::Square(int x, int y) : Shape(x, y), edge(1)
{
}
Square::Square(int x, int y, int edge) : Shape(x, y), edge(edge)
{
}
Square::~Square()
{
}
// prints out the square
void Square::print() {
	cout << "Square at " << getX() << " , " << getY() << " edge " << getEdge() << endl;

}