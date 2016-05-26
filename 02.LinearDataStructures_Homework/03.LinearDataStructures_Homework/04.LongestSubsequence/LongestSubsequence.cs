namespace _04.LongestSubsequence
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    class LongestSubsequence
    {
        static void Main(string[] args)
        {
            var input = Console.ReadLine().Split(' ');

            var numbers = ParseInput(input);
            var subsequence = FindLongestSubsequence(numbers);

            Console.WriteLine(string.Join(" ", subsequence));

        }

        private static List<int> ParseInput(string[] input)
        {
            var numbers = new List<int>();

            numbers.AddRange(input.Select(t => int.Parse(t)));
            return numbers;
        }

        private static List<int> FindLongestSubsequence (List<int> numbers)
        {
            if (numbers.Count == 1)
            {
                return numbers;
            }

            int count = 1;
            int longest = 1;
            int number = numbers[0];
            for (int i = 0; i < numbers.Count - 1; i++)
            {
                if (numbers[i] == numbers[i + 1])
                {
                    count++;
                }
                else
                {
                    if (count > longest)
                    {
                        longest = count;
                        number = numbers[i];
                    }

                    count = 1;
                }
            }

            var subsequence = Enumerable.Repeat(number, longest).ToList();
            return subsequence;
        }
    }
}
