namespace PitFortress.Classes
{
    using Interfaces;
    using System;

    public class Player : IPlayer
    {
        private int radius;

        public Player(string name, int radius)
        {
            this.Name = name;
            this.Radius = radius;
            this.Score = 0;
        }

        public string Name { get; private set; }

        public int Radius
        {
            get { return this.radius; }
            set
            {
                if (value < 0)
                {
                    throw new ArgumentException("radius cannot be negative number");
                }

                this.radius = value;
            }
        }

        public int Score { get; set; }

        public int CompareTo(Player other)
        {
            if (this.Score == other.Score)
            {
                return -this.Name.CompareTo(other.Name);
            }

            return -this.Score.CompareTo(other.Score);
        }
    }
}
