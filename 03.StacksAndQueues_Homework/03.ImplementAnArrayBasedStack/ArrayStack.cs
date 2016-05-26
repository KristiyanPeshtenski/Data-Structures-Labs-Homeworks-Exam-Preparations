namespace _03.ImplementAnArrayBasedStack
{
    using System;

    public class ArrayStack<T>
    {
        private const int DEFAULT_CAPACITY = 16;
        private T[] elements;

        public ArrayStack(int capacity = DEFAULT_CAPACITY)
        {
            this.elements = new T[capacity];
        }

        public int Count { get; private set; }

        public void Push(T element)
        {
            if (this.Count == this.elements.Length)
            {
                this.Grow();
            }

            this.elements[this.Count] = element;
            this.Count++;
        }

        public T Pop()
        {
            if (this.Count == 0)
            {
                throw new InvalidOperationException("stack is empty.");
            }

            var element = this.elements[this.Count - 1];
            this.elements[this.Count - 1] = default(T);
            this.Count--;
            return element;
        }
        
        
        public T[] ToArray()
        {
            var resultArr = new T[this.Count];
            int counter = 0;
            for (int i = this.Count - 1; i >= 0; i--)
            {
                resultArr[counter] = this.elements[i];
                counter++;
            }

            return resultArr;
        }

        private void Grow()
        {
            var growedArr = new T[2 * this.elements.Length];
            Array.Copy(this.elements, growedArr, this.elements.Length);
            this.elements = growedArr;
        }
    }
}
