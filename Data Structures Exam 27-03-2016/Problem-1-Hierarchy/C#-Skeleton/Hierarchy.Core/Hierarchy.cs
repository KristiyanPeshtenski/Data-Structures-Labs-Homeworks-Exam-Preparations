namespace Hierarchy.Core
{
    using System;
    using System.Collections.Generic;
    using System.Collections;
    using System.Linq;
    public class Hierarchy<T> : IHierarchy<T>
    {
        private HierarchyNode<T> rootNode;
        private IDictionary<T, HierarchyNode<T>> elementsByValue;
        
        public Hierarchy(T root)
        {
            this.rootNode = new HierarchyNode<T>(root);
            this.elementsByValue = new Dictionary<T, HierarchyNode<T>>();
            this.elementsByValue.Add(root, this.rootNode);
        }

        public int Count
        {
            get
            {
                return this.elementsByValue.Count;
            }
        }

        public void Add(T element, T child)
        {
            if (!this.Contains(element))
            {
                throw new ArgumentException("Parent element don't exist " + element);
            }

            if (this.Contains(child))
            {
                throw new ArgumentException("Element already exists " + child);
            }

            var parent = this.elementsByValue[element];
            var newNode = new HierarchyNode<T>(child, parent);

            this.elementsByValue.Add(child, newNode);
            parent.Children.Add(child, newNode);
        }

        public void Remove(T element)
        {
            if (!this.Contains(element))
            {
                throw new ArgumentException("element don't exist " + element);
            }
            if (element.Equals(rootNode.Value))
            {
                throw new InvalidOperationException("Cannot remove root element " + element);
            }

            var elementToDelete = this.elementsByValue[element];
            var parent = elementToDelete.Parent;

            if (elementToDelete.Children.Any())
            {
                var unionedChildren = parent.Children.Union(elementToDelete.Children).ToDictionary(x => x.Key, x => x.Value);
                parent.Children = unionedChildren;
                foreach (var child in elementToDelete.Children.Values)
                {
                    child.Parent = parent;
                }
            }

            parent.Children.Remove(element);
            this.elementsByValue.Remove(element);
        }

        public IEnumerable<T> GetChildren(T item)
        {
            if (!this.Contains(item))
            {
                throw new ArgumentException("No such element " + item);
            }

            var parent = this.elementsByValue[item];
            return parent.Children.Keys;
        }

        public T GetParent(T item)
        {
            if (!this.Contains(item))
            {
                throw new ArgumentException("No such element " + item);
            }

            var element = this.elementsByValue[item];
            if (element.Parent == null)
            {
                return default(T);
            }

            return element.Parent.Value;
        }

        public bool Contains(T value)
        {
            return this.elementsByValue.ContainsKey(value);
        }

        public IEnumerable<T> GetCommonElements(Hierarchy<T> other)
        {
            return this.elementsByValue.Keys.Intersect(other.elementsByValue.Keys);
        } 

        public IEnumerator<T> GetEnumerator()
        {
            var queue = new Queue<HierarchyNode<T>>();
            queue.Enqueue(rootNode);

            while (queue.Count > 0)
            {
                var currentNode = queue.Dequeue();
                yield return currentNode.Value;

                foreach (var child in currentNode.Children.Values)
                {
                    queue.Enqueue(child);
                }
            }
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return this.GetEnumerator();
        }
    }
}