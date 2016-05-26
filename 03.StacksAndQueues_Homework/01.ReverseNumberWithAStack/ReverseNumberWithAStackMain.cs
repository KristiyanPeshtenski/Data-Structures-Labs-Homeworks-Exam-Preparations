namespace _01.ReverseNumberWithAStack
{
    using System;
    using System.Collections.Generic;

    class ReverseNumberWithAStackMain
    {
        static void Main()
        {
            var input = Console.ReadLine();
            if (string.IsNullOrEmpty(input))
            {
                Console.WriteLine("(empty)");
            }
            else
            {
                var splitedInput = input.Split(' ');
                var stack = new Stack<int>();
                foreach (var num in splitedInput)
                {
                    stack.Push(int.Parse(num));
                }

                while (stack.Count > 0)
                {
                    Console.WriteLine(stack.Pop());
                }
            }

        }
    }
}
