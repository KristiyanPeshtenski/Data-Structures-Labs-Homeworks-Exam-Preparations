using System;

namespace AvlTreeLab
{
    public class Node<T> where T : IComparable<T>
    {

        private Node<T> leftChild;

        private Node<T> rightChild;

        public Node(T value)
        {
            this.Value = value;
        }

        public T Value { get; set; }

        public Node<T> Parent { get; set; }

        public Node<T> LeftChild
        {
            get { return this.leftChild; }
            set
            {
                if (value != null)
                {
                    value.Parent = this;
                }

                this.leftChild = value;
            }
        }

        public Node<T> RightChild
        {
            get { return this.rightChild; }
            set
            {
                if (value != null)
                {
                    value.Parent = this;
                }

                this.rightChild = value;
            }
        }

        public int BallanceFactor { get; set; }

        public bool IsLeftChild {
            get
            {
                return this.Value.CompareTo(Parent.Value) < 0;
            }
        }

        public bool IsRightChild
        {
            get
            {
                return this.Value.CompareTo(Parent.Value) > 0;
            }
        }

        public int ChildCount
        {
            get
            {
                int childCount = 0;
                if (this.LeftChild != null)
                {
                    childCount++;
                }
                if (this.RightChild != null)
                {
                    childCount++;
                }

                return childCount;
            }
        }

        public override string ToString()
        {
            return this.Value.ToString();
        }
    }
}

