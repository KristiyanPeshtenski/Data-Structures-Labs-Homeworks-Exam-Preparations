namespace ExamPreparationTelerik_OnlineMarket
{
    using System;

    public class Product : IComparable<Product>
    {
        private string name;
        private double price;
        private string type;

        public Product(string name, double price, string type)
        {
            this.Name = name;
            this.Price = price;
            this.Type = type;
        }

        public string Name
        {
            get { return this.name; }
            set
            {
                if (value.Length < 3 || value.Length > 20)
                {
                    throw new ArgumentException();
                }

                this.name = value;
            }
        }

        public string Type
        {
            get { return this.type; }
            set
            {
                if (value.Length < 3 || value.Length > 20)
                {
                    throw new ArgumentException();
                }

                this.type = value;
            }
        }

        public double Price
        {
            get { return this.price; }
            set
            {
                if (value < 0 || value > 5000)
                {
                    throw new ArgumentException();
                }

                this.price = value;
            }
        }

        public override bool Equals(object obj)
        {
            var other = (Product)obj;
            return this.Name.Equals(other.Name);
        }

        public int CompareTo(Product other)
        {
            if (this.price == other.price)
            {
                if (this.name == other.name)
                {
                    return this.type.CompareTo(other.type);
                }

                return this.name.CompareTo(other.name);
            }

            return this.price.CompareTo(other.price);
        }

        public override string ToString()
        {
            return string.Format("{0}({1})", this.name, this.price);
        }
    }
}
