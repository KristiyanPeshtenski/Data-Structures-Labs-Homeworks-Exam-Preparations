namespace _05.RemoveOddOccurences
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    class RemoveOddOccurencesMain
    {
        static void Main(string[] args)
        {
            var input = Console.ReadLine().Split(' ');
            var numbers = ParseInput(input);
            var res = RemoveOddOccurences(numbers);

            Console.WriteLine(string.Join(" ", res));
        }

        private static List<int> ParseInput(string[] input)
        {
            var numbers = new List<int>();

            numbers.AddRange(input.Select(t => int.Parse(t)));
            return numbers;
        }

        private static List<int> RemoveOddOccurences(List<int> numbers)
        {
            var occurencies = numbers.GroupBy(x => x).ToDictionary(x => x.Key, x => x.Count());
            numbers.RemoveAll(x => occurencies[x]%2 != 0);
            return numbers;
        }

    }
}
