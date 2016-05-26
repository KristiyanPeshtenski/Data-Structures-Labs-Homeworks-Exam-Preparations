namespace _01.SumAndAverage
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    public class SumAndAverage
    {
        static void Main(string[] args)
        {
            var input = Console.ReadLine();
            var numbers = new List<int>();
            if (string.IsNullOrEmpty(input))
            {
                numbers.Add(0);
            }
            else
            {
                var splittedInput = input.Split(' ');
                numbers.AddRange(splittedInput.Select(n => int.Parse(n)));
            }

            var sum = numbers.Sum();
            var avg = numbers.Average();

            Console.WriteLine("Sum: " + sum);
            Console.WriteLine("Average: "  + avg);

        }
    }
}
