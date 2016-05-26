using System;
using System.Collections.Generic;
using System.Linq;

public class GraphConnectedComponents
{
    public static void Main()
    {
        graph = ReadGraph();
        FindGraphConnectedNodes();

    }

    private static List<int>[] graph = new List<int>[]
    {
       new List<int>() { 3, 6 },
       new List<int>() { 3, 4, 5, 6 },
       new List<int>() { 8 },
       new List<int>() { 0, 1, 5 },
       new List<int>() { 1, 6 },
       new List<int>() { 1, 3 },
       new List<int>() { 0, 1, 4 },
       new List<int>() {  },
       new List<int>() { 2 }
    };

    private static bool[] visited;

    private static void DFS (int node)
    {
        
        if (!visited[node])
        {
            visited[node] = true;
            foreach (var child in graph[node])
            {
                DFS(child);
            }

            Console.Write(" " + node);
        }
    }

    private static void FindGraphConnectedNodes()
    {
        visited = new bool[graph.Length];
        for (int node = 0; node < graph.Length; node++)
        {
            if (!visited[node])
            {
                Console.Write("Connected component: ");
                DFS(node);
                Console.WriteLine();
            }
            
        }
    }

    private static List<int>[] ReadGraph()
    {
        var n = int.Parse(Console.ReadLine());
        var graph = new List<int>[n];

        for (int i = 0; i < n; i++)
        {
            graph[i] = Console.ReadLine()
                .Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries)
                .Select(int.Parse)
                .ToList();     
        }

        return graph;
    }
}
