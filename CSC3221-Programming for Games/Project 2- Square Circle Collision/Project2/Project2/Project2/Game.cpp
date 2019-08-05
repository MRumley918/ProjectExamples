#include "Shape.h"
#include "Square.h"
#include "Circle.h"
#include <iostream>
#include <vector>
#include <cmath>
using namespace std;
//detects collision between 2 squares
bool CollisionDetect(Square* s1, Square* s2) {
	// checks the top left and bottom right coordinates of both squares against each other
	if ((s1->getTLX() <= s2->getBRX()) && (s1->getBRX() >= s2->getTLX()) && (s1->getTLY() >= s2->getBRY()) && (s1->getBRY() <= s2->getTLY())) {
		return true;
	}
	else {
		return false;
	}
}
// detects collision between a square and a circle
bool CollisionDetect(Square* s, Circle* c) {
	// half the edge of the square- to find the centre later in this method
	double sqEdge = (s->getEdge() / 2);
	// vertical and horizontal distance between the centre of the circle and centre of the square
	double distX = abs(c->getX() - (s->getX() - sqEdge));
	double distY = abs(c->getY() - (s->getY() - sqEdge));
	//if the distance is greater than half of the circle and half the rectangle, they are too far apart to collide
	if ((distX > (sqEdge + c->getRad())) || (distY > (sqEdge + c->getRad()))) {
		return false;
	}
	//if the distance is less than half the square then the two shapes are definitely colliding
	else if ((distX <= sqEdge) || (distY <= sqEdge)) {
		return true;
	}
	//else test for collision at the square's corner
	else {
		double dx = distX - sqEdge;
		double dy = distY - sqEdge;
		//use pythagoras formula to compare distance
		return(dx*dx + dy*dy <= c->getRad()*c->getRad());
	}
	return false;
}
//detect collision between 2 circles
bool CollisionDetect(Circle* c1, Circle* c2) {
	//combined radius
	int radSum = c1->getRad() + c2->getRad();
	//combined distance
	double distSum = sqrt(pow((c2->getX() - c1->getX()), 2) + pow((c2->getY() + c1->getY()), 2));
	// if the distance is less than the radius, the two circles are colliding
	if (distSum <= radSum) {
		return true;
	}
	else {
		return false;
	}
}
// general collision method for two shapes
bool Collision(Shape* Shape1, Shape* Shape2) {
	// intially casts the two shapes to square,circle
	Square* sq = dynamic_cast<Square*>(Shape1);
	Circle* cir = dynamic_cast<Circle*>(Shape2);
	// if they both return null pointers, the shapes are the wrong way around
	if (sq == nullptr && cir == nullptr) {
		// the shapes are cast correctly and then checked to see if they collide
		sq = dynamic_cast<Square*>(Shape2);
		cir = dynamic_cast<Circle*>(Shape1);
		return CollisionDetect(sq, cir);
	}
	// if the square is a null pointer, the shapes are both circles
	else if (sq == nullptr) {
		Circle* cir2 = dynamic_cast<Circle*>(Shape1);
		return CollisionDetect(cir, cir2);
	}
	// if the circle is a null pointer then the shapes are both square
	else if (cir == nullptr) {
		Square* sq2 = dynamic_cast<Square*>(Shape2);
		return CollisionDetect(sq, sq2);
	}
	// otherwise the initial casting is correct
	else {
		return CollisionDetect(sq, cir);
	}
}
//play the game
void play(int shapeNumber) {
	// decrements the shape number to avoid off by one error
	shapeNumber--;
	// initialises the vectors required
	vector<Shape*> shapes;
	vector<Shape*>::iterator it;
	vector<Shape*>::iterator it1;
	//initialises a few constants
	// max and min are the biggest and smallest sizes a shape can be, as well as the max and min distances the shape moves
	int min = 1;
	int max = 5;
	// gamesize*gamesize is the size of the grid the game is played in
	int gamesize = 20;
	// squares is the number of squares- this avoids division errors when generating shapes
	int squares = 0;
	// the number of rounds 
	int round = 1;
	// half of the shapes are squares
	for (int i = 0; i <= ((shapeNumber) / 2); i++) {
		//generates a new square at a random position in the game area with a random edge size
		Shape* s = new Square(rand() % (gamesize) , rand() % (gamesize), min + rand() % (max - min));
		//prints the details of the square
		s->print();
		//number of squares has increased by one
		squares++;
		//the square is added to the shapes vector
		shapes.push_back(s);
	}
	// the rest of the shapes are circles
	for (int i = 0; i <= ((shapeNumber)-squares); i++) {
		// a new circle is generated at a random position in the game space with a random radius
		Shape* c = new Circle(rand() % (gamesize), rand() % (gamesize), min + rand() % (max - min));
		//the circle is printed out
		c->print();
		//the circle is added to the vector
		shapes.push_back(c);
	}
	// the amount of shapes is printed
	cout << "Playing with " << shapes.size() << " shapes" << endl;
	// while there is at least 2 shapes still in play
	while (shapes.size()>1) {
		// the round number is displayed
		cout << "Round: " << round << endl;
		// the round is increased
		round++;
		//for each shape in the shapes vector
		for (it = shapes.begin(); it != shapes.end(); it++) {
			// moves the shape by a random distance
			(*it)->move(min + rand() % (max - min) , min + rand() % (max - min));
			// if the x or y coordinate is greater than the game space then its coordinate is reset to zero
			if ((*it)->getX()>=gamesize) {
				(*it)->setX(0);
			}
			if ((*it)->getY() >= gamesize) {
				(*it)->setY(0);	
			}
		}
		// iterates through all the shapes twice, the second iterator starts at position 1 
		for (it = shapes.begin(); it != shapes.end();) {
			for (it1 = it + 1; it1 != shapes.end();) {
				// if two shapes collide, print out the details of both shapes, then delete them both
				if (Collision((*it), (*it1))) {
					(*it)->print();
					cout << "has collided with" << endl;
					(*it1)->print();
					delete (*it);
					delete (*it1);
					(*it) = nullptr;
					(*it1) = nullptr;
					it1 = shapes.erase(it1);
					it = shapes.erase(it);
					break;
				}
				// otherwise keep iterating, checking each shape against each other
				else {
					
					it1++;
				}
			}
			//increase the first iterator, unless the end has been reached
			if (shapes.size() == 0) {
				break;
			}
			it++;	
		}
	}
	// if there is one shape left, the game has been won and the winning shape is printed
	if (shapes.size() == 1) {
		cout << endl;
		cout << endl;
		shapes[0]->print();
		cout << " is the winner! After "<<round<<" rounds"<< endl;
	}
	// if there are no shapes, the game has no winner
	if (shapes.size() == 0) {
		cout << endl;
		cout << endl;
		cout << "There was no winner... After " <<round<<" rounds"<< endl;
	}
}
int main() {
	// the main method plays the game with a user specifed amount of shapes
	play(17);
}