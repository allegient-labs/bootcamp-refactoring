# DMI Developer Boot Camp: Guided Refactoring Exercise

This is the Gilded Rose Kata - https://iamnotmyself.com/2011/02/13/refactor-this-the-gilded-rose-kata/

All credit to Sandi Metz (https://www.sandimetz.com/) for the inspiration for this exercise. Her talk "All the Little Things" (https://www.youtube.com/watch?v=8bZh5LMaSmE&t=1746s) is one of our favorite conference talks. This exercise will be a dim reflection of the brilliance she displayed during that talk. We've tried to take the ideas and progression she discussed in that talk and make them applicable to Java. Some things are different because Java is statically typed and Ruby (the language she used) is not, but the ideas are the same.

This exercise is about incremental refactoring. And making small things. And dealing with large incomprehensible if statements.

## Step 0 - Start State

The class `gilded.rose.GildedRose` needs to be enhanced. There are several disabled tests in `gilded.rose.GildedRoseTest` that are failing.

Your mission is to make the tests pass.

The following is a suggested path for making this enhancement. Along the way we will learn something about incremental refactoring. Some of the steps below may seem unnecessary to you - you might be able to clearly see a solution from the start.

One key principle of this refactoring exercise is this: a refactoring step will break tests. You job is to get back to having the tests pass as soon as you can. This means that we will be making small incremental fixes, rather than trying to fix everything from the start.

## Step 1 - An Incomprehensible If Statement

The giant `if` statement is incomprehensible. Start by breaking this down into smaller methods. Here's a suggestion for how to do it:

1. Don't try to understand the logic in the statement - that will take days. You are covered by tests, so you can work on refactoring by simply making the tests pass.
2. At the start of the `tick` method, add another `if` statement that checks for `name` and calls a new method. For example:
    ```java
    if ("Normal Item".equals(name) {
      call normalTick();
      return;
    }
  
    private void normalTick() {
    }
    ```
    This should break several tests.

3. Add code to the new `normalTick` method to make the tests pass.
4. Continue this process for the remaining names (Change the `if` statement to a `switch` statement).
5. Once all the tests are passing with the new methods, you should be able to delete the incomprehensible `if` statement

Discussion points:
1. Should you simply implement a `conjuredTick()` method now? Will you write procedures, or trust objects?
2. Your `normalTick` and `brieTick` methods probably look very similar. Should you try to make an abstraction?


## Step 2 - Trust Objects

How to we move towards objects in an incremental manner? This is different in dynamic languages versus static languages.  Sandi's talk shows how to do it with duck typing in Ruby, but that doesn't work in Java. So we're going to introduce an `Item` interface here. It will go away eventually, but we need it for now so we can incrementally refactor.

## Step 3 - Get Rid of Code Duplication           
Now we've got a lot of duplicated and unnecessary code. Clean all that up. First move the individual method's code back into the switch statement. Then make a "forName" method that creates an instance. We now have a middle man and a factory.

## Step 4 - Make a Factory
Break out the factory - leave the middle man (will also have to change the tests)

## Step 5 - Inheritance
Inheritance is not evil, but it is dangerours and often misused. We want shallow narrow hierarchies.

Remove the Item interface, make GildedRose a base class, remove the duplication in the sub classes, remove Sulfuras class because there is a good default case.

## Step 6 - Separate Configuration from Business Logic

## Step 7 - Implement Conjured 

## Summary

- Prefer duplication over the wrong abstraction
- Reach for open/closed
- (Kent Beck) make the change easy (this might be hard), then make the easy change
- make small things! Objects with a single responsibility
- Embrace SOLID principles - they are the rules of OO design
- Intermediate refactorings often make code more complicated. Trust the process.
