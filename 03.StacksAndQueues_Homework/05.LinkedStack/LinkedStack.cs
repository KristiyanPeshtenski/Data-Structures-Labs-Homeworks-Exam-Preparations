using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _05.LinkedStack
{
    public class LinkedStack<T>
    {
        private Node<T> firstNode;

        public int Count { get; private set; }

        public void Push(T element)
        {
            var newNode = new Node<T>(element, this.firstNode);
            this.firstNode = newNode;
            this.Count++;
        }

        public T Pop()
        {
            if (this.Count == 0)
            {
                throw new InvalidOperationException("Stack is empty");
            }

            var popedElement = this.firstNode.Value;
            this.firstNode = this.firstNode.NextNode;
            this.Count--;

            return popedElement;
        }

        public T[] ToArray()
        {
            var arr = new T[this.Count];
            var currentNode = firstNode;
            for (int i = 0; i < this.Count; i++)
            {
                arr[i] = currentNode.Value;
                currentNode = currentNode.NextNode;
            }

            return arr;
        }

        private class Node<T>
        {
            public T Value { get; private set; }

            public Node<T> NextNode { get; set; }

            public Node(T value, Node<T> nextNode = null)
            {
                this.Value = value;
                this.NextNode = nextNode;
            }
        }
    }
}
