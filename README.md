# ♕ BYU CS 240 Chess

This project demonstrates mastery of proper software design, client/server architecture, networking using HTTP and WebSocket, database persistence, unit testing, serialization, and security.

## 10k Architecture Overview

The application implements a multiplayer chess server and a command line chess client.

[![Sequence Diagram](10k-architecture.png)](https://sequencediagram.org/index.html#initialData=C4S2BsFMAIGEAtIGckCh0AcCGAnUBjEbAO2DnBElIEZVs8RCSzYKrgAmO3AorU6AGVIOAG4jUAEyzAsAIyxIYAERnzFkdKgrFIuaKlaUa0ALQA+ISPE4AXNABWAexDFoAcywBbTcLEizS1VZBSVbbVc9HGgnADNYiN19QzZSDkCrfztHFzdPH1Q-Gwzg9TDEqJj4iuSjdmoMopF7LywAaxgvJ3FC6wCLaFLQyHCdSriEseSm6NMBurT7AFcMaWAYOSdcSRTjTka+7NaO6C6emZK1YdHI-Qma6N6ss3nU4Gpl1ZkNrZwdhfeByy9hwyBA7mIT2KAyGGhuSWi9wuc0sAI49nyMG6ElQQA)

## Modules

The application has three modules.

- **Client**: The command line program used to play a game of chess over the network.
- **Server**: The command line program that listens for network requests from the client and manages users and games.
- **Shared**: Code that is used by both the client and the server. This includes the rules of chess and tracking the state of a game.

## Starter Code

As you create your chess application you will move through specific phases of development. This starts with implementing the moves of chess and finishes with sending game moves over the network between your client and server. You will start each phase by copying course provided [starter-code](starter-code/) for that phase into the source code of the project. Do not copy a phases' starter code before you are ready to begin work on that phase.

## IntelliJ Support

Open the project directory in IntelliJ in order to develop, run, and debug your code using an IDE.

## Maven Support

You can use the following commands to build, test, package, and run your code.

| Command                    | Description                                     |
| -------------------------- | ----------------------------------------------- |
| `mvn compile`              | Builds the code                                 |
| `mvn package`              | Run the tests and build an Uber jar file        |
| `mvn package -DskipTests`  | Build an Uber jar file                          |
| `mvn install`              | Installs the packages into the local repository |
| `mvn test`                 | Run all the tests                               |
| `mvn -pl shared test`      | Run all the shared tests                        |
| `mvn -pl client exec:java` | Build and run the client `Main`                 |
| `mvn -pl server exec:java` | Build and run the server `Main`                 |

These commands are configured by the `pom.xml` (Project Object Model) files. There is a POM file in the root of the project, and one in each of the modules. The root POM defines any global dependencies and references the module POM files.

## Running the program using Java

Once you have compiled your project into an uber jar, you can execute it with the following command.

```sh
java -jar client/target/client-jar-with-dependencies.jar

♕ 240 Chess Client: chess.ChessPiece@7852e922
```
[Chess Server Design (sequence-diagram)](https://sequencediagram.org/index.html?presentationMode=readOnly#initialData=IYYwLg9gTgBAwgGwJYFMB2YBQAHYUxIhK4YwDKKUAbpTngUSWDABLBoAmCtu+hx7ZhWqEUdPo0EwAIsDDAAgiBAoAzqswc5wAEbBVKGBx2ZM6MFACeq3ETQBzGAAYAdAE5M9qBACu2GADEaMBUljAASij2SKoWckgQaIEA7gAWSGBiiKikALQAfOSUNFAAXDAA2gAKAPJkACoAujAA9D4GUAA6aADeAETtlMEAtih9pX0wfQA0U7jqydAc45MzUyjDwEgIK1MAvpjCJTAFrOxclOX9g1AjYxNTs33zqotQyw9rfRtbO58HbE43FgpyOonKUCiMUyUAAFJForFKJEAI4+NRgACUh2KohOhVk8iUKnU5XsKDAAFUOrCbndsYTFMo1Kp8UYdOUyABRAAyXLg9RgdOAoxgADNvMMhR1MIziSyTqDcSpymgfAgEDiRCo2XLmaSYCBIXIUNTKLSOndZi83hxZj9tgztPL1GzjOUAJIAOW54UFwtG1v0ryW9s22xg3vqNWltDBOtOepJqnKRpQJoUPjAqQtQxFKCdRP1rNO7sjPq5ftjt3zs2AWdS9QgAGt0OXozB69nZc7i4rCvGUOUu42W+gtVQ8blToCLmUIlCkVBIqp1VhZ8D+0VqJcYNdLfnJuVVk8R03W2gj1N9hPKFvsuZygAmJxObr7vOjK8nqZnseXmBjxvdAOFMLxfH8AJoHYckYB5CBoiSAI0gyLJkHMNkjl3ao6iaVoDHUBI0HfAYDy-T4nhtJZdj6A4sJBQoN13D8a3In9nmDW0aIBc5NyVbUhxgBAEKQNBYXgxDUXRWJsUHXVe2TMkKTNOEAxQIMFiWQsmWTN0OXIXl+X9MjDAlCApRuGAAHUWErLlqzuGAAF4nIc-MYAAMhgKj3mc1yfNApMFWnAdlUEno1LosL5KLRTDWNTJM2zXNWILHtYuCwoy29X1jM-dTOwbc82yjGMR3SnTgv4ycVUK7NirQCcpxnXjdwk0SVzXTAmJBarmNI-Lv0eX8iv-IbaNvXrCgfDBn1fEi1PG09RovcaDhAsDvD8QIvBQNsJN8ZhkPSTJMBm5g+vnCppEM+ouWaFoCNUIjuhHaAkAAL3iRJygAHj-C98kmtkeuHBt3q+ggfpgf6VvQIGeswsLymE+xDvEhDDqkjFZOixMFJZcoOBQbhEobWEAfQbSXRLLL9Juvk7rq1JrNs8J7MppIXOZhqKpprdB3KVdiyahMWqBNrMazTqEHXVqpu3Eo5rfXoop3BXzuV991s4TaIMCSEODg6EYAAcXzVljtQs70LyUL1fKCpTa5PCWnsfNXvBqBPu+tA-s5oH6JB+WwezCHff9uG0AR+WkYElHoXN0ZVHExOLexmTRcMfGMoNckwCSnNOepvtS307k+QFNzRQAMXCGoAFlmdZuyef-Py24vPnS+q8Fq7EOSc8qvOKSTtQUvpbvdLLzlDKr93Rg9aRZhQzIVKtGAdAQUBm3X2sYAXlAvXcuvG4P9zuhs1u1I71fTRMmAAB9H-7jut53vfRinqr7Zq8LD9UGrP+W5QbG1iGPVQMs5YSwVvRK4fQAHjEqP0Q+S8kEAEYnwAGYAAsTw76f3uH0L478QC7xMisL4h9j5sVoo0IBd4QrwFtmALWvQEEWyQRUFB+Y0HlEwbg-BJ176DQmCQ7eZDCGUKeNQw8Yi9j0LMLrTwW1ILYB8FAbA3B4AJUMGPFIwibY5Aur-bCtQGgPUPp7MO3tIZEUjvVf8+RugAEgehTFkbQmADCGJnBgVcWGjjAYyPzDQohkweIwLjn-VMuix6wjgHE-MGcsRZxisPFMB8KSFwplHEu086az0rnlVKMBT5NxHC3dmnc2zc05t-V0TDBb9zSUPGmsT0yZHiZ4gqal8mZXZJ6CsVYemzBvqVc+X8gqNN7rVVB0hWniznOURJnSUBjygd1WOTTijMQ8bw6QGDsE4O8cDJhmsYAvhVu4jhi9Dn8OOacjaKj9YBEsCTYSyQYAACkICiTNu5AIpDmxGIwpdR2VRKSuysWgFxb1bERxhgHd8cxt7vKgHACAwkoArBsh6O6LQABCPIFBwAANJUIOUc3Bpyg5LOBAEgOlE0WUExdi3FLB8VciJSS8llK7nUpwZEuc0S+4ACs-loHib80SGyUBohxq0gkBMR4F3JsXBptNBkGWKa-cpzcr7VM5h3ep0ySyzMEmpJVMgVWZPzt0g5-TGmFJ1UZSZKAl4r2EYQ2YwLCFlPrk3Q+VT7LzI7vMzVAtkZ7kPkAqcyrc6ZIlbK-MsJ5ljJMtaFlGKsXQCdVqsslIqjSAUEzYN3RuSCgISZFofqH6uRvoa0NBzw0HMjTs+OMBhbJkWYxEOPzJVytXLLLZUSO1K0ufNVWZzTgXKudrJRoEXnbQCF4YAYREDplgMAbAWjCC+wMdbc60TsIMxdvdVoxgzl9v8cwrdKTR0iotambgeBGR6AMLCXGAl0ntKMCTCkIi4T5r0uUM9TNgAamrNas1RMANdNTSBmeMhbocyg4fGDtq4OkxQDkpDLrwNoYQMzdtz6u0+BFoPelu5N14E2Yjcdu553TrpbOlhbCeg61AkAA)