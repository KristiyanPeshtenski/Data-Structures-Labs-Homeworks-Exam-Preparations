using System;
using System.Collections.Generic;

public class Tree<T>
{
    public T Value { get; set; }

    public IList<Tree<T>> Childern { get; set; }

    public Tree(T value, params Tree<T>[] children)
    {
        this.Value = value;
        this.Childern = new List<Tree<T>>();

        foreach (var child in children)
        {
            this.Childern.Add(child);
        }
    }

    public void Print(int indent = 0)
    {
        Console.Write(new string(' ', 2 * indent));
        Console.WriteLine(this.Value);
        foreach (var child in this.Childern)
        {
            child.Print(indent + 1);
        }
    }

    public void Each(Action<T> action)
    {
        action(this.Value);
        foreach (var child in this.Childern)
        {
            child.Each(action);
        }

    }
}
