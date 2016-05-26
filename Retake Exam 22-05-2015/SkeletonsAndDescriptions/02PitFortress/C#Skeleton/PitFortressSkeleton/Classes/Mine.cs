namespace PitFortress.Classes
{
    using Interfaces;
    using System;

    public class Mine : IMine
    {
        private static int idCounter = 0;
        private int damage;
        private int xCoordinate;

        public Mine(int id, Player player, int xCoordinate, int delay, int damage)
        {
            this.Id = id;
            this.Player = player;
            this.XCoordinate = xCoordinate;
            this.Damage = damage;
            this.Delay = delay; 
        }

        public int Id { get; private set; }

        public int Delay { get; set; }

        public int Damage
        {
            get { return this.damage; }
            set
            {
                if (value < 0 || value > 100)
                {
                    throw new ArgumentException("Invalid damage value " + value);
                }

                this.damage = value;
            }
        }

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

        public Player Player { get; private set; }

        public int CompareTo(Mine other)
        {
            return this.Id.CompareTo(other.Id);
        }

        private int GenerateId()
        {
            idCounter++;
            return idCounter;
        }
    }
}
