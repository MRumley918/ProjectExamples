#pragma once
#include "Shape.h"
#include <iostream>
using namespace std;
class Square : public Shape
{
public:
	Square();
	Square(int edge);
	Square(int x, int y);
	Square(int x, int y, int edge);
	~Square();
	//getters for top left and bottom right(setters are in shape class)
	int getEdge() { return edge; }
	int getTLX() { return getX(); }
	int getTLY() { return getY() + edge; }
	int getBRX() { return getX() + edge; }
	int getBRY() { return getY(); }
	void print();

private:
	int edge;
};

