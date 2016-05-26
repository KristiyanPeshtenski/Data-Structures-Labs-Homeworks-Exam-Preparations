namespace _06.LinkedStack.Tests
{
    using System;
    using Microsoft.VisualStudio.TestTools.UnitTesting;
    using _05.LinkedStack;
    using System.Collections.Generic;
    [TestClass]
    public class LinkedStackTests
    {
        [TestMethod]
        public void PushAndPopOneElement_ShouldReturnItCorrectly()
        {
            //Arrange
            var stack = new LinkedStack<int>();
            var expectedNumber = 5;

            //Act and Assert
            Assert.AreEqual(0, stack.Count);
            stack.Push(expectedNumber);
            Assert.AreEqual(1, stack.Count);
            var actualNumber = stack.Pop();
            Assert.AreEqual(expectedNumber, actualNumber);
            Assert.AreEqual(0, stack.Count);
        }

        [TestMethod]
        public void PushAndPop_1000_Elements_ShouldReturnThemCorrectly()
        {
            //Arrange
            var stack = new LinkedStack<string>();
            var excpectedStr = "test value";
            var elementsCount = 1000;

            //Act and Assert
            Assert.AreEqual(0, stack.Count);
            for (int i = 0; i < elementsCount; i++)
            {
                stack.Push(excpectedStr);
            }

            Assert.AreEqual(elementsCount, stack.Count);
            for (int i = 0; i < elementsCount; i++)
            {
                var poppedStr = stack.Pop();
                Assert.AreEqual(excpectedStr, poppedStr);
            }

            Assert.AreEqual(0, stack.Count);
        }

        [TestMethod]
        [ExpectedException(typeof(InvalidOperationException))]
        public void PopFromEmptyStack_ShouldThrow_InvalidOperationException()
        {
            //Arrange
            var stack = new LinkedStack<int>();
            Assert.AreEqual(0, stack.Count);

            //Act
            stack.Pop();
        }

        [TestMethod]
        public void ConvertStackWithSeveralElements_ToArray_ShouldReturnTheElementsInReverseOrder()
        {
            //Arrange
            var arrayToPush = new List<int>() { 7, -2, 5, 3 };
            var stack = new LinkedStack<int>();

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
            var stack = new LinkedStack<DateTime>();
            var expectedResult = new DateTime[0];

            //Act
            var arr = stack.ToArray();

            //Assert
            CollectionAssert.AreEqual(expectedResult, arr);
        }
    }
}
