using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _02.CalculateSequenceWithQueue
{
    class CalculateSequenceWithQueue
    {
        static void Main(string[] args)
        {
            var n = int.Parse(Console.ReadLine());
            var queue = new Queue<int>();
            var output = new List<int>();

            queue.Enqueue(n);

            while (output.Count() <= 50)
            {
                var currentNumber = queue.Dequeue();
                queue.Enqueue(currentNumber + 1);
                queue.Enqueue(2 * currentNumber + 1);
                queue.Enqueue(currentNumber + 2);

                output.Add(currentNumber);
            }

            Console.WriteLine(string.Join(", ", output));
        }
    }
}
