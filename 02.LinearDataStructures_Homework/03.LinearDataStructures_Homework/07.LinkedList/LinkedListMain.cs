namespace _07.LinkedList
{
    using System;

    class LinkedListMain
    {
        static void Main(string[] args)
        {
            var myList = new LinkedList<int>();

            myList.Add(3);
            myList.Add(2);
            myList.Add(3);
            myList.Add(4);
            myList.Add(5);
            myList.Add(6);

            Console.WriteLine(myList.LastIndexOf(3));
        }
    }
}
