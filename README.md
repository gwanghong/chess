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
[Chess Server Design (sequence-diagram)](https://sequencediagram.org/index.html?presentationMode=readOnly#initialData=IYYwLg9gTgBAwgGwJYFMB2YBQAHYUxIhK4YwDKKUAbpTngUSWDABLBoAmCtu+hx7ZhWqEUdPo0EwAIsDDAAgiBAoAzqswc5wAEbBVKGBx2ZM6MFACeq3ETQBzGAAYAdAE5M9qBACu2GADEaMBUljAASij2SKoWckgQaIEA7gAWSGBiiKikALQAfOSUNFAAXDAA2gAKAPJkACoAujAA9D4GUAA6aADeAETtlMEAtih9pX0wfQA0U7jqydAc45MzUyjDwEgIK1MAvpjCJTAFrOxclOX9g1AjYxNTs33zqotQyw9rfRtbO58HbE43FgpyOonKUCiMUyUAAFJForFKJEAI4+NRgACUh2KohOhVk8iUKnU5XsKDAAFUOrCbndsYTFMo1Kp8UYdOUyABRAAyXLg9RgdOAoxgADNvMMhR1MIziSyTqDcSpymgfAgEDiRCo2XLmaSYCBIXIUNTKLSOndZi83hxZj9tgztPL1GzjOUAJIAOW54UFwtG1v0ryW9s22xg3vqNWltDBOtOepJqnKRpQJoUPjAqQtQxFKCdRP1rNO7sjPq5ftjt3zs2AWdS9QgAGt0OXozB69nZc7i4rCvGUOUu42W+gtVQ8blToCLmUIlCkVBIqp1VhZ8D+0VqJcYNdLfnJuVVk8R03W2gj1N9hPKFvsuZygAmJxObr7vOjK8nqZnseXmBjxvdAOFMLxfH8AJoHYckYB5CBoiSAI0gyLJkHMNkjl3ao6iaVoDHUBI0HfAYDy-T4nhtJZdj6A4sJBQoN13D8a3In9nmDW0aIBc5NyVbUhxgBAEKQNBYXgxDUXRWJsUHXVe2TMkKTNOEAxQIMFiWQsmWTN0OXIXl+X9MjDAlCApRuGAAHUWErLlqzuGAAF4nIc-MYAAMhgKj3mc1yfNApMFWnAdlUEno1LosL5KLRTDWNTJM2zXNWILHtYuCwoy29X1jM-dTOwbc82yjGMR3SnTgv4ycVUK7NirQCcpxnXjdwk0SVzXTAmJBarmNI-Lv0eX8iv-IbaNvXrCgfDBn1fEi1PG09RovcaDhAsDvD8QIvBQNsJN8ZhkPSTJMBm5g+vnCppEM+ouWaFoCNUIjuhHaAkAAL3iRJygAHj-C98kmtkeuHBt3q+ggfpgf6VvQIGeswsLymE+xDvEhDDqkjFZOixMFJZcoOBQbhEobWEAfQbSXRLLL9Juvk7rq1JrNs8J7MppIXOZhqYCQMUef-GAuQADQ9BoyAqmmt0HcpV2LJqExaoE2sxrNOoQddWqm7cSjmt9eiincdfO-X33WzhNogwJIQ4ODoRgABxfNWWO1CzvQvJQuN8oKkdrk8Jaex81e8GoE+760D+zmgfokHtbB7MIcj6O4bQBHtaRgSUehZ3RlUcTc5d7GZMVwx8Yyg1yTAJKc056m+1LfSKEMfnBYvYWxYlqXG+q8EYHqcJKXsloYAAMQUHluTLmLKoNNvB+Htlq7ztRYQb3Sm85QyBRgYPRg9aRZhQzIVKtGAdAQUBmzP2s9-zL13LH8IagAWXv0Vuhsuy3NFbmT9NCZGAAAfYBv9DDc0vtfW+owe6bz7rVHo+81BGxquXZWc5yg8iLvnDWWsVY63olcPoyDVDjEqP0ZBh9yEAEYnwAGYAAsTwAEwPuH0L4UCQA3xMisL4yDH5sVoo0VBd4QrwE9mAM2vQSEu3IRUSh+ZqHlDoUwlhJ1AGDQmJwq+3C2F8KeAIw82i9giLMJbTwW1ILYB8FAbA3B4AJUMKvFIGiPY5Aut7PWlRagNAesg0OSdw6QyIqneq-58jdAAJA9CmEYoRMBREMTOAQq4sNwmA0MQ-YxHDEndUzggwSaYTSr1hHAJxq8S5YhnhXOeKY94UlrhTNOG9Mrsk5HtPmAtOad3FvUSWQVXTiNlgPIeI9x6T2nnJWpNNygLzGWyYpmRSnxIKmpVpQy6aegrFWVZsw1LthjMguBVUvH9yodIGpGDgTlHKemZZ+Y8H5IITLYozE4lKOkLQhhjC8lx3EabGAL4DaxNkQfL5Kifl5I2pY62ARLAk2EskGAAApCAoknbuQCFw5s7iMKXV9lUSkgcAloCiW9YJKcYYx3fHMK+CKoBwAgMJKAKwbIejui0AAQjyBQcAADS-DPnfKYX8t5yTQZ7nSaOTJdLgAMqZSytlLAOVcm5bygVQrwUisYTxF5hTygACt0VoFKWi0SlSUBohxjUgkBMq6NPJvXE5mz2kGT5LvA5z837M1Zj-Hp3NOYupLAa8BtqZD2vqSvfMsILkbNpm67kHrBQXOPhothswcVsPHi-d+yC-Xsw-igQ+fki2H2Da87Oe5kGoKnHayu9TjUWpjam8B1p6WUEVdAeNelyiUiqNIBQTN83dG5IKVhJkWhZqAa5A539C0XNLRcitwzkYwHlsmK5jEE6opNZa1cmtnlzkrd44F5tgYAskdInoFtQKwu2gELw8qJHplgMAbA9jCCR1ce7c6WdvHXVuvdVoxgL3btSS+vAVSj18TObVEA3A8CMj0AYde4bBn1OJqTTRcIe1bxkEBzsGpqzocjUTEmFIUClLw1sgjjMObEeOdM+tdTyPYaaTRt1DMA4MYQMzFdoaN0si3SkzBkHlxqC6ojVdPsgXzUNhe04gKz0KY2kAA)