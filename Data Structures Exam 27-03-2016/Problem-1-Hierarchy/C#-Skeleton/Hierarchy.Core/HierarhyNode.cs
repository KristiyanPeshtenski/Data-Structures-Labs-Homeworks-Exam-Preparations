namespace Hierarchy.Core
{
    using System.Collections.Generic;

    public class HierarchyNode<T>
    {
        public HierarchyNode(T value, HierarchyNode<T> parrent = null)
        {
            this.Value = value;
            this.Parent = parrent;
            this.Children = new Dictionary<T, HierarchyNode<T>>();
        }

        public T Value { get; private set; }

        public IDictionary<T, HierarchyNode<T>> Children { get; set; }

        public HierarchyNode<T> Parent { get; set; }

        public override bool Equals(object obj)
        {
            var other = (HierarchyNode<T>)obj;
            return this.Value.Equals(other.Value);
        }
    }
}
