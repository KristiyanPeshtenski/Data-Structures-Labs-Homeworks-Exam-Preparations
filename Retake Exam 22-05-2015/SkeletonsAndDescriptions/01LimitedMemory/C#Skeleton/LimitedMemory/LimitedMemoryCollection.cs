using System.Collections.Generic;
using System.Collections;
using System.Linq;

namespace LimitedMemory
{
    public class LimitedMemoryCollection<K, V> : ILimitedMemoryCollection<K, V>
    {
        private Dictionary<K, LinkedListNode<Pair<K,V>>> entities;
        private LinkedList<Pair<K, V>> sortedEntities; 

        public LimitedMemoryCollection(int capacity)
        {
            this.Capacity = capacity;
            this.entities = new Dictionary<K, LinkedListNode<Pair<K, V>>>();
            this.sortedEntities = new LinkedList<Pair<K, V>>();
        } 

        public IEnumerator<Pair<K, V>> GetEnumerator()
        {
            var currentPair = this.sortedEntities.First;
            while (currentPair != null)
            {
                yield return currentPair.Value;
                currentPair = currentPair.Next;
            }
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return this.GetEnumerator();
        }

        public int Capacity { get; private set; }

        public int Count { get { return this.entities.Count; } }

        public void Set(K key, V value)
        {
            if (this.entities.ContainsKey(key))
            {
                var entity = this.entities[key];
                entity.Value.Value = value;
                this.RefreshPriority(entity);
            }
            else
            {
                var newPair = new Pair<K, V>(key, value);
                if (this.Count >= this.Capacity)
                {
                    var entityToRemove = this.sortedEntities.Last;
                    this.entities.Remove(entityToRemove.Value.Key);
                    this.sortedEntities.Remove(entityToRemove);
                }

                var node = this.sortedEntities.AddFirst(newPair);
                this.entities.Add(key, node);
            }
            
        }

        public V Get(K key)
        {
            if (!this.entities.ContainsKey(key))
            {
                throw new KeyNotFoundException("Key don't exist " + key);
            }

            var entity =  this.entities[key];
            this.RefreshPriority(entity);

            return entity.Value.Value;
        }

        private void RefreshPriority(LinkedListNode<Pair<K,V>> entity)
        {
            this.sortedEntities.Remove(entity);
            this.sortedEntities.AddFirst(entity);
        }
    }
}
