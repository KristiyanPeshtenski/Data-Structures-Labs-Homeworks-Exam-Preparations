using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _06.ReversedList
{
    public class ReversedList<T> : IEnumerable<T>
    {
        private const int DEFAULT_CAPACITY = 4;

        private T[] arr;

        public int Capacity { get; private set; }

        public int Count { get; private set; }

        public ReversedList(int capacity = DEFAULT_CAPACITY)
        {
            this.arr = new T[capacity];
            this.Capacity = capacity;
        }

        public void Add (T element)
        {
            if (this.Count == this.Capacity)
            {
                this.Grow();
            }

            this.arr[Count] = element;
            this.Count++;
        }

        private void Grow()
        {
            this.Capacity = this.Capacity * 2;
            T[] newArr = new T[this.Capacity];

            for (int i = 0; i < this.arr.Length; i++)
            {
                newArr[i] = this.arr[i];
            }

            this.arr = newArr;
        }

        public T this[int index]
        {
            get
            {
                if (index < 0 || index > this.Count - 1)
                {
                    throw new IndexOutOfRangeException();
                }

                int lastIndex = this.Count - 1;
                return this.arr[lastIndex - index];
            }
        }

        public void Remove(int index)
        {
            if (index < 0 || index > this.Count - 1)
            {
                throw new IndexOutOfRangeException();
            }

            var newArr = new T[this.Capacity];
            int removedIndex = this.Count - 1 - index;
            for (int i = 0; i < removedIndex; i++)
            {
                newArr[i] = this.arr[i];
            }
            for (int i = removedIndex; i < this.Count - 1; i++)
            {
                newArr[i] = this.arr[i + 1]; 
            }

            this.arr = newArr;
            this.Count--;
        }

        public IEnumerator<T> GetEnumerator()
        {
            int lastIndex = this.Count - 1;
            for (int i = lastIndex; i >= 0; i--)
            {
                yield return this.arr[i];
            }
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return this.GetEnumerator();
        }
    }
}
