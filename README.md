# TreeMap — Practice

Two practice problems that extend the chapter's `TreeMap`: answer questions about the order of the keys (the nearest key below or above a given key, and every key in a range), and find a key by its position in that order. Each problem is a copy of `TreeMap` that implements a larger interface, and each has a JUnit suite.

## Prerequisites

- JDK 17+ (JUnit 6 requires it).
- The JUnit jar is already vendored in `lib/`; there is nothing to download.

## Repository layout

```plaintext
code/
  README.md
  .gitignore
  lib/
    junit-platform-console-standalone-6.1.0.jar
  src/
    main/
      practice/
        OrderedMap.java                 # Map plus floorKey, ceilingKey, keysBetween
        OrderedTreeMap.java             # TreeMap, extended to an OrderedMap
        RankedMap.java                  # Map plus select and rank
        RankedTreeMap.java              # TreeMap, extended to a RankedMap
      map/
        Map.java                        # the Map ADT contract, with the nested Map.Entry
      list/
        List.java                       # the List ADT
        ArrayList.java                  # an array-backed List
      stack/
        Stack.java                      # the Stack ADT, used by the iterator
        LinkedStack.java                # linked implementation of Stack
    test/
      map/
        MapTest.java                    # abstract: the Map contract suite
      practice/
        OrderedTreeMapTest.java         # the contract suite plus the ordered queries
        RankedTreeMapTest.java          # the contract suite plus select and rank
  scripts/
    test.sh                             # compile and run the JUnit tests
```

## How to compile and run

- `scripts/test.sh` — compiles everything and runs the full JUnit suite.
- `scripts/test.sh practice.OrderedTreeMapTest` — compiles everything and runs only that test class. Use this while you are working on one problem and the other is still empty.

There is no demo program for these problems; the tests are how you check your work.

## What's here

- `practice.OrderedMap<K, V>` — the Map contract plus `floorKey`, `ceilingKey`, and `keysBetween`.
- `practice.OrderedTreeMap<K, V>` — the chapter's `TreeMap` with the three ordered queries added. `floorKey` and `ceilingKey` each follow one path down the tree, and `keysBetween` skips every subtree that cannot hold a key in the range.
- `practice.RankedMap<K, V>` — the Map contract plus `select`, which returns the k-th smallest key, and `rank`, which counts the keys smaller than a given key.
- `practice.RankedTreeMap<K, V>` — the chapter's `TreeMap` with a cached subtree size in each node, kept current like the cached height. `select` and `rank` each follow one path down the tree, and `size()` reads the root's size.
- `map.Map`, `list.List`, `list.ArrayList`, `stack.Stack`, `stack.LinkedStack` — copies of earlier chapters' code, included so this code compiles and runs on its own.
- `map.MapTest` — the abstract Map contract suite from the map chapter. Both practice test classes run it against their own class.
- `practice.OrderedTreeMapTest`, `practice.RankedTreeMapTest` — the tests for the two problems.
