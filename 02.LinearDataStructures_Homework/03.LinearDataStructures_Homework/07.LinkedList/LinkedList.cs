using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _07.LinkedList
{
    class LinkedList<T> : IEnumerable<T>
    {
        class ListNode
        {
            public ListNode(T value)
            {
                this.Value = value;
            }

            public T Value { get; private set; }

            public ListNode NextNode { get; set; }
        }

        private ListNode head;

        private ListNode tail;

        public int Count { get; private set; }

        public void Add (T element)
        {
            if (this.Count == 0)
            {
                this.head = this.tail = new ListNode(element);
            }

            var newNode = new ListNode(element);
            if (this.Count == 1)
            {
                this.tail = newNode;
                this.head.NextNode = this.tail;
            }

            this.tail.NextNode = newNode;
            this.tail = newNode;

            this.Count++;
        }

        public void Remove(int index)
        {
            if (index < 0 || index > this.Count - 1)
            {
                throw new IndexOutOfRangeException();
            }
            else if (this.Count == 1 && index == 0)
            {
                this.head = this.tail = null;
            }
            else if (index == 0)
            {
                this.head = this.head.NextNode;
            }
            else
            {
                var currentNode = this.head;
                var counter = 1;

                while (counter < index)
                {
                    currentNode = currentNode.NextNode;
                    counter++;
                }

                var removedNode = currentNode.NextNode;
                currentNode.NextNode = removedNode.NextNode;
            }

            this.Count--;
        }

        public int FirstIndexOf(T value)
        {
            var currentNode = this.head;
            var index = 0;
            while (currentNode != null)
            {
                if (currentNode.Value.Equals(value))
                {
                    return index;
                }

                currentNode = currentNode.NextNode;
                index++;
            }

            return -1;
        }

        public int LastIndexOf(T value)
        {
            var currentNode = this.head;
            int lastIndex = -1;
            int index = 0;
            while (currentNode != null)
            {
                if (currentNode.Value.Equals(value))
                {
                    lastIndex = index;
                }

                currentNode = currentNode.NextNode;
                index++;
            }

            return lastIndex;
        }

        public IEnumerator<T> GetEnumerator()
        {
            var currentNode = this.head;
            while (currentNode != null)
            {
                yield return currentNode.Value;
                currentNode = currentNode.NextNode;
            }
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return this.GetEnumerator();
        }
    }
}
