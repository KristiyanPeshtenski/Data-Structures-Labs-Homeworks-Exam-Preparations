using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _05.LinkedStack
{
    class LinkedStackMain
    {
        static void Main(string[] args)
        {
            var stack = new LinkedStack<int>();

            stack.Push(10);
            stack.Push(15);
            stack.Push(20);
            stack.Push(25);

            var arr = stack.ToArray();
            foreach (var item in arr)
            {
                Console.WriteLine(item);
            }

        }
    }
}
