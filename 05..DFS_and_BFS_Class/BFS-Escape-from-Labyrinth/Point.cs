using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Escape_from_Labyrinth
{
    public class Point
    {
        public int X { get; set; }

        public int Y { get; set; }

        public string Direction { get; set; }

        public Point PreviousPoint { get; set; }
    }
}
