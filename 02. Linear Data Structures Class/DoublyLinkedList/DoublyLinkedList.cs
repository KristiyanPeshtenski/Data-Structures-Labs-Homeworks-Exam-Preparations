using System;
using System.Collections;
using System.Collections.Generic;

public class DoublyLinkedList<T> : IEnumerable<T>
{

    private class ListNode<T>
    {
        public T Value { get; private set; }

        public ListNode<T> Next { get; set; }

        public ListNode<T> Previous { get; set; }

        public ListNode(T value)
        {
            this.Value = value;
        }
    }

    private ListNode<T> head;

    private ListNode<T> tail;


    public int Count { get; private set; }

    public void AddFirst(T element)
    {
        if (this.Count == 0)
        {
            this.head = this.tail = new ListNode<T>(element);
        }
        else
        {
            var newNode = new ListNode<T>(element);
            newNode.Next = this.head;
            this.head.Previous = newNode;
            this.head = newNode;
        }

        this.Count++;
    }

    public void AddLast(T element)
    {
        if (this.Count == 0)
        {
            this.head = this.tail = new ListNode<T>(element);
        }
        else
        {
            var newNode = new ListNode<T>(element);
            newNode.Previous = this.tail;
            this.tail.Next = newNode;
            this.tail = newNode;
        }

        this.Count++;
    }

    public T RemoveFirst()
    {
        if (this.Count == 0)
        {
            throw new InvalidOperationException();
        }

        var firstElement = this.head.Value;

        if (this.Count == 1)
        {
            this.head = this.tail = null;
        }
        else
        {
            this.head = this.head.Next;
            this.head.Previous = null;
        }

        this.Count--;
        return firstElement;
    }

    public T RemoveLast()
    {
        if (this.Count == 0)
        {
            throw new InvalidOperationException("List is empty.");
        }

        var lastElement = this.tail.Value;

        if (this.Count == 1)
        {
            this.head = this.tail = null;
        }
        else
        {
            this.tail = this.tail.Previous;
            this.tail.Next = null;
        }

        this.Count--;
        return lastElement;
    }

    public void ForEach(Action<T> action)
    {
        var currentNode = this.head;

        while (currentNode != null)
        {
            action(currentNode.Value);
            currentNode = currentNode.Next;
        }
    }

    public IEnumerator<T> GetEnumerator()
    {
        var currentNode = this.head;
        while (currentNode != null)
        {
            yield return currentNode.Value;
            currentNode = currentNode.Next;
        }
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return this.GetEnumerator();
    }

    public T[] ToArray()
    {
        var array = new T[this.Count];
        if (this.Count == 0)
        {
            return array;
        }

        var currentNode = this.head;
        var arrayCounter = 0;
        while (currentNode != null)
        {
            array[arrayCounter] = currentNode.Value;
            currentNode = currentNode.Next;
            arrayCounter++;
        }

        return array;
    }
}


class Example
{
    static void Main()
    {
        var list = new DoublyLinkedList<int>();

        list.ForEach(Console.WriteLine);
        Console.WriteLine("--------------------");

        list.AddLast(5);
        list.AddFirst(3);
        list.AddFirst(2);
        list.AddLast(10);
        Console.WriteLine("Count = {0}", list.Count);

        list.ForEach(Console.WriteLine);
        Console.WriteLine("--------------------");

        list.RemoveFirst();
        list.RemoveLast();
        list.RemoveFirst();

        list.ForEach(Console.WriteLine);
        Console.WriteLine("--------------------");

        list.RemoveLast();

        list.ForEach(Console.WriteLine);
        Console.WriteLine("--------------------");
    }
}
