# flight_booker
Flight booking program for CS 240 class
John Humphrey
w/ Adam Sabet and Jing Lin Lai

## Contents:

## Introduction:
This program simulates buying a ticket for a particular flight and subsequently creating a boarding queue with priority. Created for CS 240 class at Whatcom Community College. There are two versions - one written in Java and the other written in C.

## How To Run:
As stated earlier there are two versions of the game. One in Java and one in C. In order to run the Java program, you should be able to just compile and run straight from your IDE. 

In order to run the C program you will first need to compile the program. I used and recommend [GCC - GNU Compiler Collection](https://gcc.gnu.org/). Once you have this setup you may run the compilation:

```
gcc -o flight main.c
```

after that it is just a matter of running the program:

```
./flight
```

## About:
We had initially brainstormed some project ideas and thought that this might be an interesting way to work with lists and queues. We first made some individual sketches of what the program might look like and combined some ideas on the logic. We then proceeded in making a full program in Java. At this point we all branched out even further, each with some extra credit ideas of our own. I decided to re-write the program and priority queue algorithm in C.

It was interesting transferring some of these ideas over into C. A major difference being Java is Object Oriented while C is generally considered procedural. C also requires the use of pointers which made this particularly interesting. Note the difference in **enqueue** functions between Java and C:

```java
public void enqueue(T data) {
        node<T> newNode = new node<>(data);
        if (rear == null) {
            front = newNode;
            rear = newNode;
            size++;
            return;
        }
        rear.next = newNode;
        rear = newNode;
        size++;
```

```c
void enqueue(queue* q, seat* seat) {
    node* temp = newNode(seat);
    if (empty(q)) {
        q->front = temp;
        q->rear = temp;
    } else {
        q->rear->next = temp;
        q->rear = temp;
    }
}
```

## Thoughts and Future Improvements:
This program felt a little basic and incomplete. It definitely worked well as an exercise in exploring and creating my own linked list and queue algorithms and for further exploring programming in C. However, as a program itself it feels like it's missing some elements. The C version needs proper input validation. I would like to add the option to choose from multiple different flights. I would like when auto-populating, to also assign random passengers with priority status. It would also be interesting to create a boarding simulation that de-queues the queue. Between the two programs this is all I had time for, but I would like to come back and add in these changes.



