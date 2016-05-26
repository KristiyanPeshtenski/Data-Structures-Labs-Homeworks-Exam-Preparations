using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AvlTreeLab
{
    class Program
    {
        static void Main(string[] args)
        {
            var avl = new AvlTree<int>();

            avl.Add(5);
            avl.Add(10);
            avl.Add(4);
            avl.Add(3);
            avl.Add(11);

            Console.WriteLine(avl.Contains(12));

        }
    }
}
