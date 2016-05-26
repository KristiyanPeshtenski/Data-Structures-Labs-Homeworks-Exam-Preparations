namespace _07.LinkedQueue
{
    using System;

    public class LinkedQueue<T>
    {
        class QueueNode<T>
        {
            public T Value { get; private set; }

            public QueueNode<T> NextNode { get; set; }

            public QueueNode<T> PreviousNode { get; set; }

            public QueueNode(T value)
            {
                this.Value = value;
            }

        }

        private QueueNode<T> head;

        private QueueNode<T> tail;

        public int Count { get; private set; }

        public void Enqueue (T value)
        {
            var newNode = new QueueNode<T>(value); 
            if (this.Count == 0)
            {
                this.head = newNode;
            }
            else if (this.Count == 1)
            {
                this.tail = newNode;
                this.tail.PreviousNode = this.head;
                this.head.NextNode = this.tail;
            }
            else
            {
                this.tail.NextNode = newNode;
                newNode.PreviousNode = this.tail;
                this.tail = newNode;
            }

            this.Count++;
        }

        public T Dequeue()
        {
            if (this.Count == 0)
            {
                throw new InvalidOperationException("Queue is empty.");
            }

            var headValue = this.head.Value;
            this.head = this.head.NextNode;
            this.Count--;

            return headValue;
        }

        public T[] ToArray()
        {
            var resultArr = new T[this.Count];
            var currentNode = this.head;

            for (int i = 0; i < this.Count; i++)
            {
                resultArr[i] = currentNode.Value;
                currentNode = currentNode.NextNode;
            }

            return resultArr;
        }

    }

}
