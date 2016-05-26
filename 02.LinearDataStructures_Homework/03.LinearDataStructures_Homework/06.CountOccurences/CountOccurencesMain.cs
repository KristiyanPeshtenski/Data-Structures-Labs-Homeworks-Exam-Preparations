using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _06.CountOccurences
{
    class CountOccurencesMain
    {
        static void Main(string[] args)
        {
            var input = Console.ReadLine().Split(' ');
            int[] numbers = ParseInput(input).ToArray();
            var occurences = CountOccurences(numbers);

            foreach (var num in occurences.Keys)
            {
                Console.WriteLine(string.Format("{0} -> {1} times", num, occurences[num]));
            }
        }

        private static List<int> ParseInput(string[] input)
        {
            var numbers = new List<int>();

            numbers.AddRange(input.Select(t => int.Parse(t)));
            return numbers;
        }

        private static Dictionary<int, int> CountOccurences(int[] numbers)
        {
            var occurences = numbers.GroupBy(x => x)
                .ToDictionary(x => x.Key, x => x.Count())
                .OrderBy(x => x.Value).OrderBy(x => x.Key);

            return occurences.ToDictionary(x => x.Key, x => x.Value);
        }
    }
}
