namespace BunnyWars.Core
{
    using System;

    public class Bunny : IComparable<Bunny>
    {
        private const int DEFAULT_STARTING_HEALTH = 100;

        public Bunny(string name, int team, int roomId)
        {
            this.Name = name;
            this.Team = team;
            this.RoomId = roomId;
            this.Health = DEFAULT_STARTING_HEALTH;
        }

        public int RoomId { get; set; }

        public string Name { get; private set; }

        public int Health { get; set; }

        public int Score { get; set; }

        public int Team { get; private set; }

        public int CompareTo(Bunny other)
        {
            return other.Name.CompareTo(this.Name);
        }
    }
}
