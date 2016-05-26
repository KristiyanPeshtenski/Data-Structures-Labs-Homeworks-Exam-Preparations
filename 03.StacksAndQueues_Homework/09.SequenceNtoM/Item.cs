using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _09.SequenceNtoM
{
    public class Item<T>
    {
        public T Value { get; private set; }

        public Item<T>  PreviosNode { get; set; }

        public Item(T value, Item<T> prevItem = null)
        {
            this.Value = value;
            this.PreviosNode = prevItem;
        }
    }
}
