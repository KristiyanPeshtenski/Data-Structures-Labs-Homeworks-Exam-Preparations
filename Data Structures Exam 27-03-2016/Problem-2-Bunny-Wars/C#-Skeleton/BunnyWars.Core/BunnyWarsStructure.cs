namespace BunnyWars.Core
{
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using Wintellect.PowerCollections;

    public class BunnyWarsStructure : IBunnyWarsStructure
    {
        private const int MAX_TEAMS_COUNT = 5;
        private const int DEFAULT_BUNNY_DAMAGE = 30;

        private static ReverseSuffixComparator bunnyComparator = new ReverseSuffixComparator();

        private Dictionary<string, Bunny> bunniesByName =
            new Dictionary<string, Bunny>();

        private OrderedSet<Bunny>[] bunniesByTeam =
            new OrderedSet<Bunny>[MAX_TEAMS_COUNT];

        private OrderedSet<int> sortedRooms =
            new OrderedSet<int>();

        private OrderedDictionary<int, LinkedList<Bunny>[]> bunniesByRoomAndTeam =
            new OrderedDictionary<int, LinkedList<Bunny>[]>();

        private OrderedSet<Bunny> bunniesBySuffix = new OrderedSet<Bunny>(bunnyComparator);

        public int BunnyCount
        {
            get
            {
                return this.bunniesByName.Count;
            }
        }

        public int RoomCount
        {
            get { return this.sortedRooms.Count; }
        }

        public void AddRoom(int roomId)
        {
            if (this.sortedRooms.Contains(roomId))
            {
                throw new ArgumentException("Room Already exists.");
            }

            this.sortedRooms.Add(roomId);
            this.bunniesByRoomAndTeam[roomId] = new LinkedList<Bunny>[MAX_TEAMS_COUNT];
        }

        public void AddBunny(string name, int team, int roomId)
        {
            if (this.bunniesByName.ContainsKey(name))
            {
                throw new ArgumentException("Bunny with that name already exists " + name);
            }

            if (!this.sortedRooms.Contains(roomId))
            {
                throw new ArgumentException("Room not exist " + roomId);
            }

            var newBunny = new Bunny(name, team, roomId);

            // Add to BunniesByName
            this.bunniesByName[name] = newBunny;
            this.bunniesBySuffix.Add(newBunny);

            // Add to Team  
            if (this.bunniesByTeam[team] == null)
            {
                this.bunniesByTeam[team] = new OrderedSet<Bunny>();
            }
            this.bunniesByTeam[team].Add(newBunny);

            // Add to RoomAndTeam
            if (this.bunniesByRoomAndTeam[roomId] == null)
            {
                this.bunniesByRoomAndTeam[roomId] = new LinkedList<Bunny>[MAX_TEAMS_COUNT];
            }
            if (this.bunniesByRoomAndTeam[roomId][team] == null)
            {
                this.bunniesByRoomAndTeam[roomId][team] = new LinkedList<Bunny>();
            }
            this.bunniesByRoomAndTeam[roomId][team].AddLast(newBunny);

        }

        public void Remove(int roomId)
        {
            if (!this.sortedRooms.Contains(roomId))
            {
                throw new ArgumentException("Room not exist " + roomId);
            }

            this.sortedRooms.Remove(roomId);

            foreach (var bunnies in this.bunniesByRoomAndTeam[roomId])
            {
                if (bunnies != null && bunnies.Any())
                {
                    var currentBunny = bunnies.First;
                    while (currentBunny != null)
                    {
                        this.bunniesByName.Remove(currentBunny.Value.Name);
                        this.bunniesByTeam[currentBunny.Value.Team].Remove(currentBunny.Value);
                        this.bunniesBySuffix.Remove(currentBunny.Value);

                        currentBunny = currentBunny.Next;
                    }
                }

            }

            this.bunniesByRoomAndTeam.Remove(roomId);
        }

        public void Next(string bunnyName)
        {
            if (!this.bunniesByName.ContainsKey(bunnyName))
            {
                throw new ArgumentException("bunny don't exists " + bunnyName);
            }

            var bunny = this.bunniesByName[bunnyName];
            var currentRoomIndex = this.sortedRooms.IndexOf(bunny.RoomId);
            int newRoomIndex;
            if (currentRoomIndex == this.sortedRooms.Count - 1)
            {
                newRoomIndex = 0;
            }
            else
            {
                newRoomIndex = currentRoomIndex + 1;
            }

            this.MoveBunny(bunny, newRoomIndex);
        }

        public void Previous(string bunnyName)
        {
            if (!this.bunniesByName.ContainsKey(bunnyName))
            {
                throw new ArgumentException("bunny don't exists " + bunnyName);
            }

            var bunny = this.bunniesByName[bunnyName];
            var currentRoomIndex = this.sortedRooms.IndexOf(bunny.RoomId);
            int newRoomIndex;
            if (currentRoomIndex == 0)
            {
                newRoomIndex = this.sortedRooms.Count - 1;
            }
            else
            {
                newRoomIndex = currentRoomIndex - 1;
            }

            this.MoveBunny(bunny, newRoomIndex);
        }

        private void MoveBunny(Bunny bunny, int newRoomIndex)
        {
            var newRoomId = this.sortedRooms[newRoomIndex];
            this.bunniesByRoomAndTeam[bunny.RoomId][bunny.Team].Remove(bunny);
            if (this.bunniesByRoomAndTeam[newRoomId] == null)
            {
                this.bunniesByRoomAndTeam[newRoomId] = new LinkedList<Bunny>[MAX_TEAMS_COUNT];
            }
            if (this.bunniesByRoomAndTeam[newRoomId][bunny.Team] == null)
            {
                this.bunniesByRoomAndTeam[newRoomId][bunny.Team] = new LinkedList<Bunny>();
            }
            this.bunniesByRoomAndTeam[newRoomId][bunny.Team].AddLast(bunny);

            bunny.RoomId = newRoomId;
        }

        public void Detonate(string bunnyName)
        {
            if (!this.bunniesByName.ContainsKey(bunnyName))
            {
                throw new ArgumentException("bunny don't exists " + bunnyName);
            }

            var bunny = this.bunniesByName[bunnyName];
            var roomId = bunny.RoomId;

            for (int i = 0; i < MAX_TEAMS_COUNT; i++)
            {
                if (i == bunny.Team ||
                    this.bunniesByRoomAndTeam[roomId][i] == null ||
                    !this.bunniesByRoomAndTeam[roomId][i].Any())
                {
                    continue;
                }
                else
                {
                    var currentTeam = this.bunniesByRoomAndTeam[roomId][i];
                    var currentBunnyNode = currentTeam.First;
                    while (currentBunnyNode != null)
                    {
                        var currentBunny = currentBunnyNode.Value;
                        currentBunny.Health -= DEFAULT_BUNNY_DAMAGE;
                        if (currentBunny.Health <= 0)
                        {
                            bunny.Score += 1;
                            currentBunnyNode = currentBunnyNode.Next;
                            this.KillBunny(currentBunny);
                        }
                        else
                        {
                            currentBunnyNode = currentBunnyNode.Next;
                        }
                    }
                }
            }
        }

        private void KillBunny(Bunny bunny)
        {
            this.bunniesByName.Remove(bunny.Name);
            this.bunniesByTeam[bunny.Team].Remove(bunny);
            this.bunniesByRoomAndTeam[bunny.RoomId][bunny.Team].Remove(bunny);
            this.bunniesBySuffix.Remove(bunny);
        }

        public IEnumerable<Bunny> ListBunniesByTeam(int team)
        {
            return this.bunniesByTeam[team];
        }

        public IEnumerable<Bunny> ListBunniesBySuffix(string suffix)
        {
            var min = new Bunny(suffix, 0, 0);
            var max = new Bunny(char.MaxValue + suffix, 0, 0);

            return this.bunniesBySuffix.Range(min, true, max, false);
        }

        
    }
}
