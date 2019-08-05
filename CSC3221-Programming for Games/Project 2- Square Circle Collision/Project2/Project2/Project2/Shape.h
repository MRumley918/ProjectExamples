#pragma once
using namespace std;
#include <iostream>
class Shape
{
public:
	//a shape would have coordinates 0,0 if not specified
	Shape(int x = START_X, int y = START_Y);
	virtual ~Shape();
	// moves shape 
	void move(int x, int y);
	//getters and setters for x and y coordinates of a shape
	void setX(int i) { Centre_x = i; }
	int getX() { return Centre_x; }
	void setY(int i) { Centre_y = i; }
	int getY() { return Centre_y; }
	// virtual print method, should not be called directly from this class
	virtual void print() { cout << "shape" << endl; }

protected:
	int Centre_x;
	int Centre_y;

private:
	const static int START_X = 0;
	const static int START_Y = 0;

};

