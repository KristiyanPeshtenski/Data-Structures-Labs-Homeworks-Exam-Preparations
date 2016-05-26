using System;

public class CircularQueue<T>
{
    private const int DEFAULT_CAPACITY = 16;

    private T[] elements;

    private int startIndex = 0;

    private int endIndex = 0;

    public int Count { get; private set; }

    public CircularQueue()
    {
        this.elements = new T[DEFAULT_CAPACITY];
    }

    public CircularQueue(int capacity)
    {
        this.elements = new T[capacity];
    }

    public void Enqueue(T element)
    {
        if (this.elements.Length == this.Count)
        {
            this.Grow();
        }

        this.elements[this.endIndex] = element;
        this.endIndex = (this.endIndex + 1) % this.elements.Length;
        this.Count++;

    }

    public T Dequeue()
    {
        if (this.Count == 0)
        {
            throw new InvalidOperationException("Queue is empty.");
        }

        var result =  this.elements[this.startIndex];
        this.elements[startIndex] = default(T);
        this.startIndex = (this.startIndex + 1) % this.elements.Length;
        this.Count--;
        return result;
    }

    public T[] ToArray()
    {
        var newArray = new T[this.Count];
        this.CopyAllElements(newArray);
        return newArray;
    }

    private void Grow()
    {
        var newElements = new T[2 * this.elements.Length];
        this.CopyAllElements(newElements);
        this.elements = newElements;
        this.startIndex = 0;
        this.endIndex = this.Count;
    }

    private void CopyAllElements(T[] resultArr)
    {
        var sourceIndex = this.startIndex;
        var destinationIndex = 0;
        for (int i = 0; i < this.Count; i++)
        {
            resultArr[destinationIndex] = this.elements[sourceIndex];
            sourceIndex = (sourceIndex + 1) % this.elements.Length;
            destinationIndex++;
        }
    }
}


class Example
{
    static void Main()
    {
        var queue = new CircularQueue<int>();

        queue.Enqueue(1);
        queue.Enqueue(2);
        queue.Enqueue(3);
        queue.Enqueue(4);
        queue.Enqueue(5);
        queue.Enqueue(6);

        Console.WriteLine("Count = {0}", queue.Count);
        Console.WriteLine(string.Join(", ", queue.ToArray()));
        Console.WriteLine("---------------------------");

        var first = queue.Dequeue();
        Console.WriteLine("First = {0}", first);
        Console.WriteLine("Count = {0}", queue.Count);
        Console.WriteLine(string.Join(", ", queue.ToArray()));
        Console.WriteLine("---------------------------");

        queue.Enqueue(-7);
        queue.Enqueue(-8);
        queue.Enqueue(-9);
        Console.WriteLine("Count = {0}", queue.Count);
        Console.WriteLine(string.Join(", ", queue.ToArray()));
        Console.WriteLine("---------------------------");

        first = queue.Dequeue();
        Console.WriteLine("First = {0}", first);
        Console.WriteLine("Count = {0}", queue.Count);
        Console.WriteLine(string.Join(", ", queue.ToArray()));
        Console.WriteLine("---------------------------");

        queue.Enqueue(-10);
        Console.WriteLine("Count = {0}", queue.Count);
        Console.WriteLine(string.Join(", ", queue.ToArray()));
        Console.WriteLine("---------------------------");

        first = queue.Dequeue();
        Console.WriteLine("First = {0}", first);
        Console.WriteLine("Count = {0}", queue.Count);
        Console.WriteLine(string.Join(", ", queue.ToArray()));
        Console.WriteLine("---------------------------");
    }
}
