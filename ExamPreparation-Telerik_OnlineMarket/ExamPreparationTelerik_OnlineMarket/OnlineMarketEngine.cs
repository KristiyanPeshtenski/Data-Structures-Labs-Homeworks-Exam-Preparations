namespace ExamPreparationTelerik_OnlineMarket
{
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using Wintellect.PowerCollections;

    public class OnlineMarketEngine
    {
        private const string ADDED_SUCCESSFULLY = "Ok: Product {0} added successfully";
        private const string DUPLICATE_PRODUCT = "Error: Product {0} already exists";
        private const string INVALID_COMMAND = "Invalid Command";
        private const string PRODUCT_TYPE_DONT_EXIST = "Error: Type {0} does not exists";
        private const string FILTER_PRODUCT = "Ok: {0}";

        private const int MAX_PRODUCTS_CONT = 10;


        private Dictionary<string, OrderedSet<Product>> productsByType;
        private OrderedDictionary<double, OrderedSet<Product>> productsByPrice;

        public OnlineMarketEngine()
        {
            this.productsByType = new Dictionary<string, OrderedSet<Product>>();
            this.productsByPrice = new OrderedDictionary<double, OrderedSet<Product>>();
        }

        public void Run()
        {
            while (true)
            {
                var input = Console.ReadLine().Split(' ');
                var command = input[0];
                var commandResult = string.Empty;
                switch (command)
                {
                    case "add":
                        var price = double.Parse(input[2]);
                        commandResult = this.AddProduct(input[1], price, input[3]);
                        break;
                    case "filter":
                        commandResult = this.HandleFilterCommand(input);
                        break;
                    case "end":
                        return;
                    default:
                        commandResult = INVALID_COMMAND;
                        break;
                }

                Console.WriteLine(commandResult);
            }
        }

        private string HandleFilterCommand(string[] commandParts)
        {
            var commandResult = string.Empty;

            switch (commandParts[2])
            {
                case "type":
                    commandResult = this.FilterProductsByType(commandParts[3]);
                    break;
                case "price":
                    if (commandParts.Length == 7)
                    {
                        var minPrice = double.Parse(commandParts[4]);
                        var maxPrice = double.Parse(commandParts[6]);
                        commandResult = this.FilterProductsByMinAndMaxPrice(minPrice, maxPrice);
                    }
                    else if (commandParts.Length == 5 && commandParts[3] == "from")
                    {
                        var minPrice = double.Parse(commandParts[4]);
                        commandResult = this.FilterProductsByMinPrice(minPrice);
                    }
                    else
                    {
                        var maxPrice = double.Parse(commandParts[4]);
                        commandResult = this.FilterProductsByMaxPrice(maxPrice);
                    }
                    break;
                default:
                    break;
            }

            return commandResult;

        }

        private string AddProduct(string name, double price, string type)
        {
            var product = new Product(name, price, type);
            if (this.productsByType.ContainsKey(type) && this.productsByType[type].Contains(product))
            {
                return string.Format(DUPLICATE_PRODUCT, product.Name);
            }

            // Add in products by type
            if (!this.productsByType.ContainsKey(type))
            {
                this.productsByType[type] = new OrderedSet<Product>();
            }
            this.productsByType[type].Add(product);

            // Add in products by price
            if (!this.productsByPrice.ContainsKey(price))
            {
                this.productsByPrice[price] = new OrderedSet<Product>();
            }
            this.productsByPrice[price].Add(product);

            return string.Format(ADDED_SUCCESSFULLY, product.Name);
        }

        private string FilterProductsByType(string type)
        {
            if (!productsByType.ContainsKey(type))
            {
                return string.Format(PRODUCT_TYPE_DONT_EXIST, type);
            }

            var matches = this.productsByType[type].Take(MAX_PRODUCTS_CONT);
            return this.PrintProducts(matches);
        }

        private string FilterProductsByMinAndMaxPrice(double minPrice, double maxPrice)
        {
            var matches = this.productsByPrice.Range(minPrice, true, maxPrice, true)
                .SelectMany(x => x.Value)
                .Take(MAX_PRODUCTS_CONT);

            if (!matches.Any())
            {
                return "Ok: ";
            }

            return this.PrintProducts(matches);
        }

        private string FilterProductsByMinPrice(double minPrice)
        {
            var matches = this.productsByPrice.RangeFrom(minPrice, true)
                .SelectMany(x => x.Value)
                .Take(MAX_PRODUCTS_CONT);

            if (!matches.Any())
            {
                return "Ok: ";
            }

            return this.PrintProducts(matches);
        }

        private string FilterProductsByMaxPrice(double maxPrice)
        {
            var matches = this.productsByPrice.RangeTo(maxPrice, true)
                .SelectMany(x => x.Value)
                .Take(MAX_PRODUCTS_CONT);

            if (!matches.Any())
            {
                return "Ok: ";
            }

            return this.PrintProducts(matches);
        }

        private string PrintProducts(IEnumerable<Product> products)
        {
            //var sortedProducts = products.OrderBy(x => x);
            var formatedResults = string.Join(", ", products);
            return string.Format(FILTER_PRODUCT, formatedResults);
        }
    }
}
