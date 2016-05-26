namespace PitFortress
{
    using System;
    using System.Collections.Generic;
    using Classes;
    using Interfaces;
    using Wintellect.PowerCollections;
    using System.Linq;

    public class PitFortressCollection : IPitFortress
    {
        private const int MAX_BATTLEFIELD_CAPACITY = 1000001;

        private int minesIdCounter;
        private int minionIdCounter;

        private Dictionary<string, Player> playersByName;
        private OrderedSet<Minion> minions;
        private OrderedSet<Mine> mines;
        private LinkedList<Minion>[] battlefield;

        public PitFortressCollection()
        {
            this.playersByName = new Dictionary<string, Player>();
            this.mines = new OrderedSet<Mine>();
            this.minions = new OrderedSet<Minion>();
            this.battlefield = new LinkedList<Minion>[MAX_BATTLEFIELD_CAPACITY];

            this.minesIdCounter = 1;
            this.minionIdCounter = 1;
        }

        public int PlayersCount
        {
            get { return this.playersByName.Count; }
        }

        public int MinionsCount { get { return this.minions.Count; } }

        public int MinesCount { get { return this.mines.Count; } }

        public void AddPlayer(string name, int mineRadius)
        {
            if (this.playersByName.ContainsKey(name))
            {
                throw new ArgumentException("Player name already taken " + name);
            }

            var newPlayer = new Player(name, mineRadius);
            this.playersByName.Add(name, newPlayer);
        }

        public void AddMinion(int xCoordinate)
        {
            var newMinion = new Minion(this.minionIdCounter, xCoordinate);
            this.minionIdCounter++;

            this.minions.Add(newMinion);
            if (this.battlefield[xCoordinate] == null)
            {
                this.battlefield[xCoordinate] = new LinkedList<Minion>();
            }

            this.battlefield[xCoordinate].AddLast(newMinion);
        }

        public void SetMine(string playerName, int xCoordinate, int delay, int damage)
        {
            if (!this.playersByName.ContainsKey(playerName))
            {
                throw new ArgumentException("Player don't exist " + playerName);
            }
            this.ValidateDelayValue(delay);

            var player = this.playersByName[playerName];
            var newMine = new Mine(this.minesIdCounter, player, xCoordinate, delay, damage);
            this.minesIdCounter++;

            this.mines.Add(newMine);
        }

        public IEnumerable<Minion> ReportMinions()
        {
            return this.minions;
        }

        public IEnumerable<Player> Top3PlayersByScore()
        {
            if (this.playersByName.Count < 3)
            {
                throw new ArgumentException();
            }

            return this.playersByName.Values
                .OrderByDescending(x => x.Score)
                .ThenByDescending(x => x.Name)
                .Take(3);
        }

        public IEnumerable<Player> Min3PlayersByScore()
        {
            if (this.playersByName.Count < 3)
            {
                throw new ArgumentException();
            }
            return this.playersByName.Values
                .OrderBy(x => x.Score)
                .ThenBy(x => x.Name)
                .Take(3);
        }

        public IEnumerable<Mine> GetMines()
        {
            return this.mines.OrderBy(x => x.Delay).ThenBy(x => x.Id);
        }

        public void PlayTurn()
        {
            var detonatedMines = new List<Mine>();
            foreach (var mine in this.mines)
            {
                mine.Delay--;
                if (mine.Delay <= 0)
                {
                    detonatedMines.Add(mine);
                    this.Detonate(mine);
                }
            }

            foreach (var mine in detonatedMines)
            {
                this.mines.Remove(mine);
            }
        }

        private void Detonate(Mine mine)
        {
            var minePossition = mine.XCoordinate;
            var radius = mine.Player.Radius;

            var startRangePossition = minePossition - radius;
            if (startRangePossition < 0)
            {
                startRangePossition = 0;
            }
            var endRangePossition = minePossition + radius;
            if (endRangePossition > this.battlefield.Length - 1)
            {
                endRangePossition = this.battlefield.Length - 1;
            }

            for (int i = startRangePossition; i <= endRangePossition; i++)
            {
                if (this.battlefield[i] == null || !this.battlefield[i].Any())
                {
                    continue;
                }
                else
                {
                    var deadMinions = new List<Minion>();
                    foreach (var minion in this.battlefield[i])
                    {
                        minion.Health -= mine.Damage;
                        if (minion.Health <= 0)
                        {
                            mine.Player.Score++;
                            deadMinions.Add(minion);
                        }
                    }

                    foreach (var minion in deadMinions)
                    {
                        this.battlefield[i].Remove(minion);
                        this.minions.Remove(minion);
                    }
                }
            }
        }

        private void ValidateDelayValue(int delay)
        {
            if (delay <= 0 || delay > 10000)
            {
                throw new ArgumentException("Invalid delay value " + delay);
            }
        }

        private void ValidateCoordinate(int coordinate)
        {

        }
    }
}
