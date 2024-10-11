package com.keyin;

public class UndoRedoManager<T> {
    // Inner class to represent each state
    private class StateNode {
        private T data;
        private StateNode previous;
        private StateNode next;

        // Constructor to initialize a state
        private StateNode(T data) {
            this.data = data;
        }
    }

    private StateNode current;  // Keeps track of the current state

    // Method to add a new state
    public void addNewState(T newState) {
        StateNode newNode = new StateNode(newState);

        // Link the current node with the new node
        newNode.previous = current;
        if (current != null) {
            current.next = newNode;
        }

        // Move to the new state
        current = newNode;
    }

    // Method to undo to the previous state
    public T undo() {
        if (current == null) {
            System.out.println("Nothing to undo.");
            return null;
        }

        // Check if we can move to the previous state
        if (current.previous == null) {
            System.out.println("No more undo steps available.");
            return null;
        }

        current = current.previous;
        return current.data;
    }

    // Method to redo to the next state
    public T redo() {
        if (current == null || current.next == null) {
            System.out.println("Nothing to redo.");
            return null;
        }

        // Move to the next state
        current = current.next;
        return current.data;
    }

    // Testing the UndoRedoManager class
    public static void main(String[] args) {
        UndoRedoManager<String> manager = new UndoRedoManager<>();
        manager.addNewState("Task 1");
        manager.addNewState("Task 2");
        manager.addNewState("Task 3");

        System.out.println("Current: " + manager.current.data);  // Expect: Task 3
        System.out.println("Undo: " + manager.undo());  // Expect: Task 2
        System.out.println("Undo: " + manager.undo());  // Expect: Task 1
        System.out.println("Redo: " + manager.redo());  // Expect: Task 2
    }
}
