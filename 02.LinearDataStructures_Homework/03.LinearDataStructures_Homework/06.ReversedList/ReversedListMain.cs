namespace _06.ReversedList
{
    using System;

    class ReversedListMain
    {
        static void Main(string[] args)
        {
            var arr = new ReversedList<int>();
            arr.Add(1);
            arr.Add(2);
            arr.Add(3);
            arr.Add(4);
            arr.Add(3);
            arr.Add(3);
            arr.Add(3);
            arr.Add(3);
            arr.Add(3);

            arr.Remove(3);

            foreach (var item in arr)
            {
                Console.WriteLine(item);
            }
        }
    }
}
