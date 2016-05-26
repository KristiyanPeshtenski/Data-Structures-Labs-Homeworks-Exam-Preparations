using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

using _03.ImplementAnArrayBasedStack;
using System.Collections.Generic;

namespace _05.ArrayBassedStack.Tests
{
    [TestClass]
    public class ArrayBasedStackTests
    {
        [TestMethod]
        public void PushAndPopOneNumber_ShouldReturnItCorrectly()
        {
            //Arrange
            var numberToAdd = 5;
            var stack = new ArrayStack<int>();

            //Act and Assert
            Assert.AreEqual(0, stack.Count);
            stack.Push(numberToAdd);
            Assert.AreEqual(1, stack.Count);
            var resNumber = stack.Pop();
            Assert.AreEqual(0, stack.Count);
            Assert.AreEqual(numberToAdd, resNumber);
        }

        [TestMethod]
        public void PushAndPop_1000_elements_ShouldReturnThemCorrectly()
        {
            //Arrange
            var stack = new ArrayStack<string>();
            var stringToAdd = "some string";

            //Act and Assert
            Assert.AreEqual(0, stack.Count);
            for (int i = 1; i <= 1000; i++)
            {
                stack.Push(stringToAdd);
                Assert.AreEqual(i, stack.Count);
            }
            for (int i = 999; i >= 0; i--)
            {
                var poppedString = stack.Pop();
                Assert.AreEqual(stringToAdd, poppedString);
                Assert.AreEqual(i, stack.Count);
            }

        }

        [TestMethod]
        [ExpectedException(typeof(InvalidOperationException))]
        public void PopFromEmptyStack_ShouldThrow_InvalidOperationException()
        {
            var stack = new ArrayStack<int>();
            Assert.AreEqual(0, stack.Count, "stack is not empty.");

            stack.Pop();
        }

        [TestMethod]
        public void PushAndPopSeveralElements_WithInitialCapacity_1_ShouldReturnThem()
        {
            //Arrange
            var firstNumber = 5;
            var secondNumber = 10;
            var stack = new ArrayStack<int>(1);

            //Act And Assert

            //Push
            Assert.AreEqual(0, stack.Count);
            stack.Push(firstNumber);
            Assert.AreEqual(1, stack.Count);
            stack.Push(secondNumber);
            Assert.AreEqual(2, stack.Count);

            //Pop
            var resNumber = stack.Pop();
            Assert.AreEqual(1, stack.Count);
            Assert.AreEqual(secondNumber, resNumber);
            resNumber = stack.Pop();
            Assert.AreEqual(0, stack.Count);
            Assert.AreEqual(firstNumber, resNumber);
        }

        [TestMethod]
        public void ConvertStackWithSeveralElements_ToArray_ShouldReturnTheElementsInReverseOrder()
        {
            //Arrange
            var arrayToPush = new List<int>() { 7, -2, 5, 3 };
            var stack = new ArrayStack<int>();

            //Act
            foreach (var num in arrayToPush)
            {
                stack.Push(num);
            }

            arrayToPush.Reverse();
            var stackArr = stack.ToArray();

            //Assert
            for (int i = 0; i < arrayToPush.Count; i++)
            {
                Assert.AreEqual(arrayToPush[i], stackArr[i]);
            }
        }

        [TestMethod]
        public void ConvertEmptyStackToArray_ShouldReturnEmptyArray()
        {
            //Arrange
            var stack = new ArrayStack<DateTime>();
            var expectedResult = new DateTime[0];

            //Act
            var arr = stack.ToArray();

            //Assert
            CollectionAssert.AreEqual(expectedResult, arr);
        }
    }
}
