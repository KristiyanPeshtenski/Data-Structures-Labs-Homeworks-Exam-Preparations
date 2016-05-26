namespace ShoppingCenter
{
    using System;

    public class Product : IComparable<Product>
    {
        public string Name { get; set; }

        public decimal Price { get; set; }

        public string Producer { get; set; }

        public Product(string name, decimal price, string producer)
        {
            this.Name = name;
            this.Price = price;
            this.Producer = producer;
        }

        public int CompareTo(Product other)
        {
            if (this.Name.Equals(other.Name))
            {
                if (this.Producer.Equals(other.Producer))
                {
                    return this.Price.CompareTo(other.Price);
                }

                return this.Producer.CompareTo(other.Producer);
            }

            return this.Name.CompareTo(other.Name);
        }

        public override string ToString()
        {
            return "{" +
                this.Name + ";" +
                this.Producer + 
                ";" +
                string.Format("{0:f2}", this.Price) +
                "}";
        }
    }
}
