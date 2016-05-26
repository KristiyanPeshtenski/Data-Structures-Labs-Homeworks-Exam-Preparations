namespace _07.LinkedQueue
{
    using System;

    class LinkedQueueMain
    {
        static void Main(string[] args)
        {
            var queue = new LinkedQueue<int>();

            queue.Enqueue(5);


            var arr = queue.ToArray();
            foreach (var item in arr)
            {
                Console.WriteLine(item);
            }

        }
    }
}
