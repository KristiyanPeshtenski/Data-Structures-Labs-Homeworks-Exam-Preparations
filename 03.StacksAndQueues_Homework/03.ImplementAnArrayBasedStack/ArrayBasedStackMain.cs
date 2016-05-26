using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _03.ImplementAnArrayBasedStack
{
    class ArrayBasedStackMain
    {
        static void Main(string[] args)
        {
            var arrStack = new ArrayStack<DateTime>();

            var arr = arrStack.ToArray();
            Console.WriteLine("cici");
        }
    }
}
