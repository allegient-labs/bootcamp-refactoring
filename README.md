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
2. At the start of the `tick` method, add a `switch` statement that checks for `name` and calls a new method. For example:
    ```java
    switch (name) {
    case "Normal Item":
      call normalTick();
      return;
    }
  
    private void normalTick() {
    }
    ```
    This should break several tests.

3. Add code to the new `normalTick` method to make the tests pass.
4. Continue this process for the remaining names.
5. Once all the tests are passing with the new methods, you should be able to delete the incomprehensible `if` statement

Discussion points:
1. Should you simply implement a `conjuredTick()` method now? Will you write procedures, or trust objects? This code is not open/closed.
2. Your `normalTick` and `brieTick` methods probably look very similar. Should you try to make an abstraction?


## Step 2 - Trust Objects

How to we move towards objects in an incremental manner? This is different in dynamic languages versus static languages.  Sandi's talk shows how to do it with duck typing in Ruby, but that doesn't work in Java. So we're going to introduce an `Item` interface here. It will go away eventually, but we need it for now so we can incrementally refactor.

The idea is to create objects that encapsulate the code contained in the methods your wrote in step 1. For now there will be some overhead added to the solution to support incremental refactoring. Trust the process! 

1. Create an interface `Item` with four methods that match the public API of `GildedRose`
2. Create a `Normal` class that implements `Item`. Copy the code from the `normalTick` method in `GildedRose` to the `tick` method in `Normal`
3. Change the `initialize` method in `GildedRose` - if the name is "Normal Item", then creat en instance of the `Normal` class and store it. Then forward the parameters to the `initialize` method in the new class you've just created.
4. Change `getQuality` and `getDaysRemaining` in the `GildedRose` class to delegate to the item instance if there is one, else just return the values as usual.
5. Change the `tick` method in `GildedRose` to delegate to the item instance if there is one, else call the existing methods.
6. Repeat steps 2-5 for the remaining items
7. Now the `tick` method in `GildedRose` should simply forward to the item instance if there is one

Discussion Points:
1. The tests have continued to pass during all the intermediate steps. This is the reason for all the overhead we've introduced.
2. This may seem a bit crazy and you will be very tempted to fix all this duplicate stuff from the start. Finish the refactoring you are on before proceeding to the next one.

## Step 3 - Code Cleanup           
Now we've got a lot of unnecessary code. Let's clean it up.

1. You will now have an item in every case, so remove the `quality` and `daysRemaining` attributes from `GildedRose` and simply forward all public API methods to the item instance.
2. Move the `switch` statement out of the `initialize` method...make a new method `private Item forName(String name)` that will return the proper item instance for each name. Call this new method in the `initialize` method

Discussion Points:
1. The code in `GildedRose` should now be very clear and easy to understand. We can now see that `GildedRose` has become a middleman
2. `GildedRose` is also acting as an `Item` factory

## Step 4 - Make a Separate Factory
At this point the `GildedRose` class is doing two things:

1. It is an item factory
2. It is a middleman

This violates two OO principles...

1. It violates the "single responsibility" principle
2. Middleman is an evil code small

We'll fix the single responsibility violation first...

1. Change `GildedRose` so that the constructor takes an `Item` instance. You will no longer need the `name` attribute
2. Make a new class `GildedRoseFactory`
3. Move the `forName` method from `GildedRose` to `GildedRoseFactory`. Change its signature to `public static GildedRose forName(String name)`
4. Change `GildedRoseTest` to use the factory rather than instantiating a `GildedRose` directly

Break out the factory - leave the middle man (will also have to change the tests)

## Step 5 - Fix the Middle Man with Inheritance
`GildedRose` is now a pure middle man and the is a terrible code smell. But our client (the tests) are written to talk to the `GildedRose` API and we don't want to change that.

You have probably also noticed that there is a lot of duplicated code in the various item classes and the only difference is the `tick` method.

We can solve this problem with inheritance. Inheritance is not evil, but it is dangerous and often misused. We want shallow narrow hierarchies. Our current issue is a good use for inheritance.

1. Add `quality` and `daysRemaining` attributes to `GildedRose`. Make them `protected`. Change the `initialize`, `getQuality`, and `getDaysRemaining` methods to use these field directly.
2. Remove the `item` instance variable, and change the constructor so that it doesn't accept any parameters
3. The `tick` method can just be an empty method
4. Change the four item classes so that they extend `GildedRose` rather than implementing `Item`. Remove all the duplicated code - the `tick` method is all that remains
5. Delete the `Item` interface
6. Do you still need the `Sulfuras` class?
7. Fix the factory so that it creates proper instances in the `GildedRose` hierarchy  

Discussion Notes:
1. We now have a very clean solution where the single responsibility principle is followed properly and all the classes are small and relatively simple. It's tempting to stop here...
2. The factory is not really open/closed 

## Step 6 - Separate Configuration from Business Logic
The factory does two things - it figures out what thing to make, and then it makes it. We can separate these two things so that it will be easier to extend in the future. Here's how the factory can change:

```java
public class GildedRoseFactory {
    private static Map<String, Class<?>> specificClasses = new HashMap<>();
    
    static {
        specificClasses.put("Normal Item", Normal.class);
        specificClasses.put("Aged Brie", Brie.class);
        specificClasses.put("Backstage passes to a TAFKAL80ETC concert", BackstagePass.class);
    }
    
    public static GildedRose forName(String name) {
        Class<?> clazz = specificClasses.computeIfAbsent(name, s -> GildedRose.class);
        try {
            GildedRose gr = (GildedRose) clazz.getDeclaredConstructor().newInstance();
            return gr;
        } catch (Exception e) {
            // should never happen
            throw new RuntimeException(e);
        }
    }
}
```

Discussion Points:
1. We've now separated the "configuration" of the system from the implementation of the system. The `Map` holds the configuration and it is the only thing that needs to change if we want to add more item types - and of course we will need to implement the new item type.
2. This configuration could be externalized to a Database, or a YAML file, or many other types of external configuration


## Step 7 - Implement Conjured 

1. There are 6 tests in `GildedRoseTest` that have the `@Disabled` annotation. Rove the annotation from the tests.
2. Make a new `Conjured` class
3. Add the configuration of `Conjured` to the factory
4. Implement the `tick` method in `Conjured`

## Summary

- Prefer duplication over the wrong abstraction
- Reach for open/closed
- (Kent Beck) make the change easy (this might be hard), then make the easy change
- make small things! Objects with a single responsibility
- Embrace SOLID principles - they are the rules of OO design
- Intermediate refactorings often make code more complicated. Trust the process.
