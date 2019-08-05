#pragma once
#include "Shape.h"
#include <iostream>
using namespace std;
class Circle : public Shape
{
public:
	Circle();
	Circle(int rad);
	Circle(int x, int y);
	Circle(int x, int y, int rad);
	~Circle();
	int getRad() { return rad; }
	void print();
private:
	int rad;
};

