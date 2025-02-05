#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// define type seat
typedef struct Seat {
    char row;
    int num;
    bool available;
    int priority;
    int class;
} seat;

// define type node for Queue
typedef struct Node {
    seat* data;
    struct Node *next;
    int priority;
} node;

// define type queue
typedef struct Queue {
    node* front;
    node* rear;
    int size;
}queue;

// populates plane as an array of seat types
void createPlane(seat plane[20][4]) {
    for (int i = 0; i < 20; i++) {
        for (int j = 0; j < 4; j++) {
            plane[i][j].row = i + 65;
            plane[i][j].num = j + 1;
            plane[i][j].available = true;
            plane[i][j].priority = 0;
            // declare class
            if (i < 5) {
                plane[i][j].class = 0;
            } else if (i < 10) {
                plane[i][j].class = 1;
            } else {
                plane[i][j].class = 2;
            }
        }
    }
}

// populates seats on plane with passengers at random
void populate(seat plane[20][4]) {
    srand(time(NULL));
    for (int i = 0; i < 63; i++) {
        int row = rand() % 20;
        int num = rand() % 4;
        plane[row][num].available = false;
    }
}

// prints remaining available seats to terminal
void displaySeats(seat plane[20][4]) {
    char arr[3][10] = {"First", "Business", "Economy"};
    printf("FIRST CLASS:\n");
    for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 4; j++) {
            if (plane[i][j].available) {
                printf("      Seat %c%d - %s class\n", plane[i][j].row, plane[i][j].num, arr[plane[i][j].class]);
            }
        }
    }
    printf("BUSINESS CLASS:\n");
    for (int i = 5; i < 10; i++) {
        for (int j = 0; j < 4; j++) {
            if (plane[i][j].available) {
                printf("      Seat %c%d - %s class\n", plane[i][j].row, plane[i][j].num, arr[plane[i][j].class]);
            }
        }
    }
    printf("ECONOMY CLASS:\n");
    for (int i = 10; i < 20; i++) {
        for (int j = 0; j < 4; j++) {
            if (plane[i][j].available) {
                printf("      Seat %c%d - %s class\n", plane[i][j].row, plane[i][j].num, arr[plane[i][j].class]);
            }
        }
    }
}

// create an empty queue
queue* newQueue() {
    queue* q = (queue*) malloc(sizeof(queue));
    q->front = NULL;
    q->rear = NULL;
    return q;
}

// creates new node
node* newNode(seat* seat) {
    node* temp = (node*) malloc(sizeof(node));
    temp->priority = 0;
    temp->data = seat;
    temp->next = NULL;
    return temp;
}

// checks if queue is empty
int empty(queue* q) {
    return (q->front == NULL);
}

// add a new node to queue
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

// enqueue a priority seat
void enqueuePriority(queue* q, seat* seat) {
    node* temp = newNode(seat);
    temp->priority = 1;
    if (empty(q)) {
        q->front = temp;
        q->rear = temp;
        return;
    }
    if (q->front->priority < temp->priority) {
        temp->next = q->front;
        q->front = temp;
    } else {
        node* current = q->front;
        while (current->next != NULL && current->next->priority >= temp->priority) {
            current = current->next;
        }
        temp->next = current->next;
        current->next = temp;
    }
}

// remove item from queue
seat* dequeue(queue* q) {
    if (empty(q)) {
        return NULL;
    }
    node* temp = q->front;
    seat* data = temp->data;
    q->front = q->front->next;
    if (q->front == NULL) {
        q->rear = NULL;
    }
    free(temp);
    return data;
}

// return data from front node
seat* peek(queue* q) {
    if (empty(q)) {
        return NULL;
    }
    return q->front->data;
}

// print queue
void print(queue* q) {
    if (empty(q)) {
        printf("Queue is empty\n");
        return;
    }
    char arr[3][10] = {"First", "Business", "Economy"};
    char p[2][20] = {"without Priority", "with Priority"};
    node* temp = q->front;
    while (temp != NULL) {
        printf("Seat %c%d - %s Class - %s\n", temp->data->row, temp->data->num, arr[temp->data->class], p[temp->data->priority]);
        temp = temp->next;
    }
}

// free allocated memory
void freeQueue(queue* q) {
    while (!empty(q)) {
        dequeue(q);
    }
}

// main function
int main(void) {
    // welcome and get flight
    printf("Welcome to the airport\n");
    int flight = 0;
    printf("Please select a flight to board:\n");
    printf("1 - Flight to New York\n");
    scanf("%d", &flight);
    printf("You have selected Flight to New York\n");
    printf("Here are the available seats:\n");

    seat plane[20][4];
    createPlane(plane);
    populate(plane);
    displaySeats(plane);

    char aisle;
    printf("Please pick an Aisle (A-T)\n");
    scanf(" %c", &aisle);
    aisle = toupper(aisle);
    int chair;
    printf("Please pick a seat (1-4)\n");
    scanf("%d", &chair);

    char priority;
    printf("Would you like to purchase priority boarding for $50? (Y/N)\n");
    scanf(" %c", &priority);
    if (priority == 'Y' || priority == 'y') {
        plane[aisle - 65][chair - 1].priority = 1;
    }

    char arr[3][10] = {"First", "Business", "Economy"};
    char p[2][20] = {"without Priority", "with Priority"};
    char confirm;
    printf("Confirm: Seat %c%d in %s Class to New York %s? (Y/N)\n", aisle, chair, arr[plane[aisle - 65][chair - 1].class], p[plane[aisle - 65][chair - 1].priority]);
    scanf(" %c", &confirm);

    plane[aisle - 65][chair - 1].available = false;

    queue* q = newQueue();
    for (int i = 0; i < 20; i++) {
        for (int j = 0; j < 4; j++) {
            if (!plane[i][j].available) {
                if (plane[i][j].priority == 1) {
                    enqueuePriority(q, &plane[i][j]);
                } else {
                    enqueue(q, &plane[i][j]);
                }
            }
        }
    }
    printf("Here is the boarding order for the flight:\n");
    print(q);
    freeQueue(q);

    return 0;
}


