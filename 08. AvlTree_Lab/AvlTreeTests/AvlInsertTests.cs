namespace AvlTreeTests
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    using AvlTreeLab;
    using Microsoft.VisualStudio.TestTools.UnitTesting;

    [TestClass]
    public class AvlInsertTests
    {
        [TestMethod]
        public void AddSeveralElements_EmptyTree_ShouldIncreaseCount()
        {
            var nums = TestUtils.ToIntArray("1 2 3");

            var tree = new AvlTree<int>();
            foreach (int num in nums)
            {
                tree.Add(num);
            }

            Assert.AreEqual(nums.Length, tree.Count);
        }

        [TestMethod]
        public void Add_RepeatingElements_ShouldNotAddDuplicates()
        {
            var nums = TestUtils.ToIntArray("1 1 1");

            var tree = new AvlTree<int>();
            foreach (int num in nums)
            {
                tree.Add(num);
            }

            Assert.AreEqual(1, tree.Count);
        }

        [TestMethod]
        public void AddingMultipleItems_RandomOrder_ShouldForeachInOrder()
        {
            var nums = TestUtils.ToIntArray("1 5 3 20 6 13 40 70 100 200 -50");

            var tree = new AvlTree<int>();
            foreach (var num in nums)
            {
                tree.Add(num);
            }

            var sortedNumbers = nums.OrderBy(n => n).ToArray();
            var expectedSequence = new Queue<int>(sortedNumbers);

            tree.ForeachDfs((depth, num) =>
            {
                Assert.AreEqual(expectedSequence.Dequeue(), num);
            });
        }

        [TestMethod]
        public void Foreach_AddedManyRandomElements_ShouldReturnSortedAscending()
        {
            const int NumCount = 10000;
            var tree = new AvlTree<int>();
            var nums = new HashSet<int>();
            var random = new Random();
            for (int i = 0; i < NumCount; i++)
            {
                var num = random.Next(0, NumCount);
                nums.Add(num);
                tree.Add(num);
            }

            var sortedNumbers = nums.OrderBy(n => n).ToArray();
            var expectedSequence = new Queue<int>(sortedNumbers);

            tree.ForeachDfs((depth, num) =>
            {
                Assert.AreEqual(expectedSequence.Dequeue(), num);
            });
        }

        [TestMethod]
        public void AddingMultipleItems_InBalancedWay_ShouldForeachInOrder()
        {
            var numbers = TestUtils.ToIntArray("20 10 30 0 15 25 40");
            var tree = new AvlTree<int>();

            foreach (int number in numbers)
            {
                tree.Add(number);
            }

            var sortedNumbers = numbers.OrderBy(n => n).ToArray();
            var expectedSequence = new Queue<int>(sortedNumbers);

            tree.ForeachDfs((depth, num) =>
            {
                Assert.AreEqual(expectedSequence.Dequeue(), num);
            });
        }

        [TestMethod]
        public void Contains_AddedElement_ShouldReturnTrue()
        {
            var numbers = TestUtils.ToIntArray("-2 3 10 0 1 -16");
            var tree = new AvlTree<int>();
            foreach (int number in numbers)
            {
                tree.Add(number);
            }

            var contains3 = tree.Contains(3);
            Assert.IsTrue(contains3);
        }

        [TestMethod]
        public void Contains_NonAddedElement_ShouldReturnFalse()
        {
            var numbers = TestUtils.ToIntArray("1 7 3 -4 10 0");
            var tree = new AvlTree<int>();
            foreach (int number in numbers)
            {
                tree.Add(number);
            }

            var contains2 = tree.Contains(2);
            Assert.IsFalse(contains2);
        }
    }
}
