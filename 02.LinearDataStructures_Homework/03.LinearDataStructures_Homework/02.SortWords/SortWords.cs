namespace _02.SortWords
{
    using System;
    using System.Linq;

    class SortWords
    {
        static void Main(string[] args)
        {
            var input = Console.ReadLine();
            var words = input.Split(' ').ToList();
            words.Sort();
            Console.WriteLine(string.Join(" ", words));
        }
    }
}
