namespace ShoppingCenter
{
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using Wintellect.PowerCollections;

    public class ShoppingCenterEngine
    {
        private const string PRODUCT_ADDED = "Product added";
        private const string NO_PRODUCTS_FOUND = "No products found";
        private const string X_PRODUCTS_DELETED = " products deleted";
        private const string INCORRECT_COMMAND = "Incorrect command";


        private Dictionary<string, OrderedBag<Product>> productsByName;

        private Dictionary<string, OrderedBag<Product>> productsByProducer;

        private Dictionary<string, Bag<Product>> productsByNameAndProducer;

        private OrderedDictionary<decimal, OrderedBag<Product>> productsByPrice;

        public ShoppingCenterEngine()
        {
            this.productsByName = new Dictionary<string, OrderedBag<Product>>();
            this.productsByProducer = new Dictionary<string, OrderedBag<Product>>();
            this.productsByNameAndProducer = new Dictionary<string, Bag<Product>>();
            this.productsByPrice = new OrderedDictionary<decimal, OrderedBag<Product>>();
        }

        public string ProcessCommand(string commandLine)
        {
            int spaceIndex = commandLine.IndexOf(' ');
            if (spaceIndex == -1)
            {
                return INCORRECT_COMMAND;
            }

            string command = commandLine.Substring(0, spaceIndex);
            string paramsStr = commandLine.Substring(spaceIndex + 1);
            string[] cmdParams = paramsStr.Split(';');

            switch (command)
            {
                case "AddProduct":
                    var price = decimal.Parse(cmdParams[1]);
                    return this.AddProduct(cmdParams[0], price, cmdParams[2]);
                case "FindProductsByName":
                    return this.FindProductsByName(cmdParams[0]);
                case "FindProductsByProducer":
                    return this.FindProductsByProducer(cmdParams[0]);
                case "FindProductsByPriceRange":
                    var from = decimal.Parse(cmdParams[0]);
                    var to = decimal.Parse(cmdParams[1]);
                    return this.FindProductsByPriceRange(from, to);
                case "DeleteProducts":
                    if (cmdParams.Count() == 1)
                    {
                        return this.DeleteProductsByProducer(cmdParams[0]);
                    }
                    return this.DeleteProductsByNameAndProducer(cmdParams[0], cmdParams[1]);
                default:
                    return INCORRECT_COMMAND;
            }

        }

        private string AddProduct(string name, decimal price, string producer)
        {
            var product = new Product(name, price, producer);

            this.productsByName.AppendValueToKey(name, product);
            this.productsByPrice.AppendValueToKey(price, product);
            this.productsByProducer.AppendValueToKey(producer, product);
            var nameProducerKey = this.CombineKey(name, producer);
            this.productsByNameAndProducer.AppendValueToKey(nameProducerKey, product);

            return PRODUCT_ADDED;
        }

        private string DeleteProductsByProducer(string producer)
        {
            var productsToDelete = this.productsByProducer.GetValuesForKey(producer);
            if (productsToDelete.Any())
            {
                var count = productsToDelete.Count();
                foreach (var product in productsToDelete)
                {
                    this.productsByName[product.Name].Remove(product);
                    this.productsByPrice[product.Price].Remove(product);
                    var nameAndProducerKey = this.CombineKey(product.Name, product.Producer);
                    this.productsByNameAndProducer[nameAndProducerKey].Remove(product);
                }

                this.productsByProducer.Remove(producer);
                return count + X_PRODUCTS_DELETED;
            }

            return NO_PRODUCTS_FOUND;
        }

        private string DeleteProductsByNameAndProducer(string name, string producer)
        {
            var nameAndProducerKey = this.CombineKey(name, producer);
            var productsToDelete = this.productsByNameAndProducer.GetValuesForKey(nameAndProducerKey);
            if (productsToDelete.Any())
            {
                var count = productsToDelete.Count();
                foreach (var product in productsToDelete)
                {
                    this.productsByName[product.Name].Remove(product);
                    this.productsByPrice[product.Price].Remove(product);
                    this.productsByProducer[producer].Remove(product);
                }

                this.productsByNameAndProducer.Remove(nameAndProducerKey);
                return count + X_PRODUCTS_DELETED;
            }

            return NO_PRODUCTS_FOUND;
        }

        private string FindProductsByName(string productName)
        {
            var productsFound = this.productsByName.GetValuesForKey(productName);
            if (productsFound.Any()) 
            {
                var formatedProducts = this.PrintProducts(productsFound);
                return formatedProducts;
            }

            return NO_PRODUCTS_FOUND;
        }

        private string FindProductsByProducer(string producer)
        {
            var productsFound = this.productsByProducer.GetValuesForKey(producer);
            if (productsFound.Any())
            {
                var formatedProducts = this.PrintProducts(productsFound);
                return formatedProducts;
            }

            return NO_PRODUCTS_FOUND;
        }

        private string FindProductsByPriceRange(decimal from, decimal to)
        {
            var productsFound = this.productsByPrice.Range(from, true, to, true).SelectMany(x => x.Value).ToList();
            if (productsFound.Any())
            {
                productsFound.Sort();
                var formatedProducts = this.PrintProducts(productsFound);
                return formatedProducts;
            }

            return NO_PRODUCTS_FOUND;
        }

        private string PrintProducts(IEnumerable<Product> productsList)
        {
            return string.Join(Environment.NewLine, productsList);
        }

        private string CombineKey(string name, string producer)
        {
            string key = name + ";" + producer;
            return key;
        }
    }
}