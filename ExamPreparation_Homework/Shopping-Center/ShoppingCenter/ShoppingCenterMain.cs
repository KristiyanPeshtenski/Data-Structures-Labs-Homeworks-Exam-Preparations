namespace ShoppingCenter
{
    using System;
    using System.Globalization;
    using System.Threading;

    class ShoppingCenterMain
    {
        static void Main()
        {
            Thread.CurrentThread.CurrentCulture = CultureInfo.InvariantCulture;

            var center = new ShoppingCenterEngine();
            var linesCount = int.Parse(Console.ReadLine());

            for (int i = 0; i < linesCount; i++)
            {
                var command = Console.ReadLine();
                string commandResult = center.ProcessCommand(command);
                Console.WriteLine(commandResult);
            }
        }
    }
}
