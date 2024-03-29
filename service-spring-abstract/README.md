"abstract" because I am experimenting with abstracting functionality that I have used irl again and again.

A collection of best practices I want to remember and a playground to experiment.

## The App(s)

A good pinch of real world complexity is needed - a simple TODO app is not enough to document these
patterns.

And while we are at it, the code needs to be potentially Microservice ready - so a modular Monolith.
Just in case if the whole pirate world suddenly uses our app.
Obviously the app would be much simpler without that.

The tricky part of the example app is that pirates and crews might not be the same users,
so we need to hide most of the information from each other while still allowing interactions
(e.g. a crew assigns a treasure to itself but does not see the position).
Otherwise, you could just dump all the data into a huge collection and be done.
But that would be no real world scenario.

The first app variant is with Spring Boot because that's what I am currently working with a lot.

## Architecture

The architecture is inspired by the Ports & Adapters / Hexagonal pattern.
This answers 90% of questions of "where should I put this?" and "what are the basic classes needed
for this new feature?".
Additionally and maybe more important by default it enforces a heavily testable structure.

## API Contract

Every web app is a distributed system with min. two parts: server and client.
It's usually a good idea to introduce some kind of explicit contract.
This allows Contract-Driven Development, where BE and FE devs can first clarify the needed APIs and
models and then develop relatively independent.
It allows auto-generation of the fetch-code for the FE including TypeScript definitions,
and also on the BE the generation of Spring Controller-interfaces and DTOs with included basic type
validation.

Usually, we write the OpenAPI spec and then generate Spring and TypeScript code for that.
But currently, I am experimenting with generating the spec from the Spring code.

## Base Classes

Don't try this at home. They are derived from patterns I had to re-implement dozens of times over
the last year.
Maybe not the best approach (especially unsure about the normalisation problem), but lets us build
robust features quickly.
I chose a compositional approach instead of having a huge chunky class,
because there is always that one case where you need something a little different.
Additionally, by splitting up the base functionality into repo-layer, validation, error-handling and
normalisation parts,
these can be used independently as components which leads to lower coupling.

The base classes form a relatively thin layer over the Repository-pattern and encourage good
patterns with regard
to validation and error handling.
