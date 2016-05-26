namespace PitFortress.Classes
{
    using PitFortress.Interfaces;
    using System;
    public class Minion : IMinion
    {
        private const int DEFAULT_STARTING_HEALTH = 100;

        private int xCoordinate;

        public Minion(int id, int xCoordinates)
        {
            this.Id = id;
            this.XCoordinate = xCoordinates;
            this.Health = DEFAULT_STARTING_HEALTH;
        }

        public int Id { get; private set; }

        public int XCoordinate
        {
            get { return this.xCoordinate; }
            set
            {
                if (value < 0 || value > 1000000)
                {
                    throw new ArgumentException("Invalid XCoorcinate " + value);
                }

                this.xCoordinate = value;
            }
        }

        public int Health { get; set; }

        public int CompareTo(Minion other)
        {
            if (this.xCoordinate == other.xCoordinate)
            {
                return this.Id.CompareTo(other.Id);
            }

            return this.xCoordinate.CompareTo(other.xCoordinate);
        }
    }
}
