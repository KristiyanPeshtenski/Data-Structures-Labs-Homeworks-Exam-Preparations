namespace AvlTreeLab
{
    using System;

    public class AvlTree<T> where T : IComparable<T>
    {
        private Node<T> root;

        public int Count { get; private set; }

        public void Add(T item)
        {
            var inserted = true;
            if (this.root == null)
            {
                this.root = new Node<T>(item);
            }
            else
            {
                inserted = this.InserInternal(root, item);
            }

            if (inserted)
            {
                this.Count++;
            }
        }

        private bool InserInternal(Node<T> node, T item)
        {
            var currentNode = node;
            var newNode = new Node<T>(item);
            var ShouldRetrace = false;

            while (true)
            {
                if (currentNode.Value.CompareTo(item) < 0)
                {
                    if (currentNode.RightChild == null)
                    {
                        currentNode.RightChild = newNode;
                        currentNode.BallanceFactor--;
                        
                        ShouldRetrace = currentNode.BallanceFactor != 0;
                        break;
                    }

                    currentNode = currentNode.RightChild;
                }
                else if (currentNode.Value.CompareTo(item) > 0)
                {
                    if (currentNode.LeftChild == null)
                    {
                        currentNode.LeftChild = newNode;
                        currentNode.BallanceFactor++;
                        ShouldRetrace = currentNode.BallanceFactor != 0;
                        break;
                    }

                    currentNode = currentNode.LeftChild;
                }
                // Item Already present 
                else
                {
                    return false;
                }
            }

            if (ShouldRetrace)
            {
                this.RetraceInsert(currentNode);
            }

            return true;
        }

        private void RetraceInsert(Node<T> node)
        {
            var parent = node.Parent;
            while (parent != null)
            {
                if (node.IsLeftChild)
                {
                    if (parent.BallanceFactor == 1)
                    {
                        parent.BallanceFactor++;
                        if (node.BallanceFactor == -1)
                        {
                            // Make the branch straight
                            this.RotateLeft(node);
                        }

                        this.RotateRight(parent);
                        break;
                    }

                    else if (parent.BallanceFactor == -1)
                    {
                        parent.BallanceFactor = 0;
                        break;
                    }
                    else
                    {
                        parent.BallanceFactor = 1;
                    }
                }
                // Node is Right Child 
                else
                {
                    if (parent.BallanceFactor == -1)
                    {
                        parent.BallanceFactor--;
                        if (node.BallanceFactor == 1)
                        {
                            this.RotateRight(node);
                        }

                        this.RotateLeft(parent);
                        break;
                    }
                    else if (parent.BallanceFactor == 1)
                    {
                        parent.BallanceFactor = 0;
                    }
                    else
                    {
                        parent.BallanceFactor = -1;
                    }
                }

                node = parent;
                parent = node.Parent;
            }
        }

        private void RotateRight(Node<T> node)
        {
            var parent = node.Parent;
            var child = node.LeftChild;
            if (parent != null)
            {
                if (node.IsLeftChild)
                {
                    parent.LeftChild = child;
                }
                else
                {
                    parent.RightChild = child;
                }
            }
            else
            {
                this.root = child;
                this.root.Parent = null;
            }

            node.LeftChild = child.RightChild;
            child.RightChild = node;

            node.BallanceFactor -= 1 + Math.Max(child.BallanceFactor, 0);
            child.BallanceFactor -= 1 - Math.Min(node.BallanceFactor, 0);
        }

        private void RotateLeft(Node<T> node)
        {
            var parent = node.Parent;
            var child = node.RightChild;
            if (parent != null)
            {
                if (node.IsLeftChild)
                {
                    parent.LeftChild = child;
                }
                else
                {
                    parent.RightChild = child;
                }
            }
            else
            {
                this.root = child;
                this.root.Parent = null;
            }

            node.RightChild = child.LeftChild;
            child.LeftChild = node;

            node.BallanceFactor += 1 - Math.Min(child.BallanceFactor, 0);
            child.BallanceFactor += 1 + Math.Max(node.BallanceFactor, 0);
        }

        public void ForeachDfs(Action<int, T> action)
        {
            if (this.Count == 0)
            {
                return;
            }

            this.InorderDfs(this.root, 1, action);
        }

        private void InorderDfs(Node<T> node, int depth, Action<int, T> action)
        {
            var currentNode = node;
            if (currentNode.LeftChild != null)
            {
                InorderDfs(currentNode.LeftChild, depth + 1, action);
            }

            action(depth, currentNode.Value);

            if (currentNode.RightChild != null)
            {
                InorderDfs(currentNode.RightChild, depth + 1, action);
            }
        }

        public bool Contains(T item)
        {
            var node = this.root;
            while (node != null)
            {
                if (node.Value.Equals(item))
                {
                    return true;
                }
                else if(node.Value.CompareTo(item) < 0)
                {
                    node = node.RightChild;
                }
                else
                {
                    node = node.LeftChild;
                }
            }

            return false;
        }
    }
}
