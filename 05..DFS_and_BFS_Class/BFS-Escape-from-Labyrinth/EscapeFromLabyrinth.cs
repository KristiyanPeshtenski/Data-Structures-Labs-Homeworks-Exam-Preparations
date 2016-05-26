using Escape_from_Labyrinth;
using System;
using System.Collections.Generic;

public class EscapeFromLabyrinth
{
    public static void Main()
    {
        labyrint = ReadLabyrint();
        var startPoint = FindStartPossition();
        if (startPoint == null)
        {
            Console.WriteLine("No exit!");
            Environment.Exit(0);
        }

        var shortestExit = FindShortestPathToExit(startPoint);
        if (shortestExit == null)
        {
            Console.WriteLine("No exit!");
        }
        else if (shortestExit == "")
        {
            Console.WriteLine("Start is at the exit.");
        }
        else
        {
            Console.WriteLine("Shortest exit: " + shortestExit);
        }
    }

    static char[ , ] ReadLabyrint()
    {
        width = int.Parse(Console.ReadLine());
        height = int.Parse(Console.ReadLine());
        var labyrint = new char[height, width];

        for (int y = 0; y < height; y++)
        {
            var input = Console.ReadLine().ToCharArray();
            for (int x = 0; x < width; x++)
            {
                labyrint[y, x] = input[x];
            }
        }

        return labyrint;
    }

    private const char StartPossitionSymbol = 's';

    private static int width = 9;

    private static int height = 7;

    private static char[,] labyrint =
    {
        { '*', '*', '*', '*', '*', '*', '*', '*', '*' },
        { '*', '-', '-', '-', '-', '*', '*', '-', '-' },
        { '*', '*', '-', '*', '-', '-', '-', '-', '*' },
        { '*', '-', '-', '*', '-', '*', '-', '*', '*' },
        { '*', 's', '*', '-', '-', '*', '-', '*', '*' },
        { '*', '*', '-', '-', '-', '-', '-', '-', '*' },
        { '*', '*', '*', '*', '*', '*', '*', '-', '*' }
    };

    

    private static string FindShortestPathToExit(Point startPoint)
    {
        var queue = new Queue<Point>();

        queue.Enqueue(startPoint);
        while (queue.Count > 0)
        {
            var currentPoint = queue.Dequeue();
            if (IsExit(currentPoint))
            {
                var path = TraceBack(currentPoint);
                return path;
            }

            TryDirection(queue, currentPoint, "U", 0, -1);
            TryDirection(queue, currentPoint, "R", 1, 0);
            TryDirection(queue, currentPoint, "D", 0, 1);
            TryDirection(queue, currentPoint, "L", -1, 0);
        }

        return null;
    }

    private static string TraceBack(Point currentPoint)
    {
        var path = new List<string>();
        while (currentPoint.PreviousPoint != null)
        {
            path.Add(currentPoint.Direction);
            currentPoint = currentPoint.PreviousPoint;
        }

        path.Reverse();
        return string.Join("", path);
    }

    private static bool IsExit(Point currentCell)
    {
        var isExitX = currentCell.X == 0 || currentCell.X == width - 1;
        var isExitY = currentCell.Y == 0 || currentCell.Y == height - 1;

        return isExitX || isExitY;
    }

    private static Point FindStartPossition()
    {
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                if (labyrint[y, x] == StartPossitionSymbol)
                {
                    var startPoint = new Point()
                    {
                        Y = y,
                        X = x
                    };

                    return startPoint;
                }
            }
        }

        return null;
    }

    private static void TryDirection(Queue<Point> queue, Point currentPoint, string direction, int x, int y)
    {
        var newX = currentPoint.X + x;
        var newY = currentPoint.Y + y;

        if (
            newX >= 0 && newX < width &&
            newY >= 0 && newY < height &&
            labyrint[newY, newX] == '-')
        {
            var newPoint = new Point()
            {
                X = newX,
                Y = newY,
                Direction = direction,
                PreviousPoint = currentPoint
            };

            labyrint[newY, newX] = StartPossitionSymbol;
            queue.Enqueue(newPoint);
        }
    }
}
