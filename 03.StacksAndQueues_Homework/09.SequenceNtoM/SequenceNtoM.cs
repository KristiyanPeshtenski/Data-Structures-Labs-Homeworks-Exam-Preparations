using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _09.SequenceNtoM
{
    class SequenceNtoM
    {

        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            int m = int.Parse(Console.ReadLine());

            if (m < n)
            {
                Console.WriteLine("No Solution");
                Environment.Exit(0);
            }

            var sequenceQueue = new Queue<Item<int>>();
            sequenceQueue.Enqueue(new Item<int>(n));

            while (sequenceQueue.Count > 0)
            {
                var currentItem = sequenceQueue.Dequeue();

                if (currentItem.Value < m)
                {
                    var numPlusOne = new Item<int>(currentItem.Value + 1, currentItem);
                    sequenceQueue.Enqueue(numPlusOne);
                    var numPlusTwo = new Item<int>(currentItem.Value + 2, currentItem);
                    sequenceQueue.Enqueue(numPlusTwo);
                    var twiseNum = new Item<int>(currentItem.Value * 2, currentItem);
                    sequenceQueue.Enqueue(twiseNum);
                }
                if (currentItem.Value == m)
                {
                    PrintSolution(currentItem);
                    break;
                }
            }
        }

        private static void PrintSolution(Item<int> item)
        {
            var result = new List<int>();
            while (item != null)
            {
                result.Add(item.Value);
                item = item.PreviosNode;
            }

            Console.WriteLine(string.Join(" -> ", result));
        }
    }
}
