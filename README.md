# Transit Example

A project to test out transitive dependencies in ClojureDart. Specifically, I
have a ClojureDart library,
[`transit-cljd`](https://github.com/wevre/transit-cljd), which depends on a Dart
package, [`transit_dart`](https://github.com/wevre/transit-dart). The goal here
is to have all of these dependencies available by simply including transit-cljd
in `deps.edn`.

## Versions

Tag `baseline` is the first example, with all the dependencies handled manually.
It works in that I can send transit messages between front-end and back-end. But
I have installed all the dependencies myself, including a local (manual) copy of
the `wevre.transit-cljd` namespace under the `src` directory, and reference to
`transit_dart` in pubspec.yaml.

Next step is to remove the manual copy of `wevre.transit-cljd` under the `src`
directory and pick it up instead with a reference in `deps.edn`. This is the
version tagged `cljd-deps`. That works. Good.

Now we want to remove `transit_dart` from `pubspec.yaml`, with the expectation
that it will get picked up as a transitive dependency by the ClojureDart
compiler. Baptiste Dupuche has created a version of ClojureDart that is meant to
do this: include transitive dependencies for Dart packages that are found in
ClojureDart libraries. This version is tagged with `remove-transit_dart`. I
can't get this version to work, when attempting to compile namespace
`wevre.transit-cljd` (which comes from the `transit-cljd` library) there is an
error about not finding 'package:transit_dart/transit_dart.dart'.

I checked with Baptiste and I need to use the version he created of ClojureDart,
the version that pulls in dependencies, I need to use that version in _both_
this sample project, _and_ in the `transit-cljd` project. I don't think that
will be expected in the future, but for now, since this is all a work in
progress, it is needed. There is a new tag of this project, `same-hash`, where
everyone is now using the same version of ClojureDart.

## Usage

This project is a combination of a ClojureDart+Flutter frontend, and a Clojure
backend running a Jetty server to receive transit requests and serve back
transit responses. There is very little error checking, and by 'very little' I
mean 'none'. The front-end is a simple form with two fields. You type in two
numbers and hit the button. The values of the fields are sent as a map to the
server, which adds them together, and provides a response string of the
summation. I know, very sophisticated.

To get the backend server up and running, start a Clojure repl with the `clj`
alias, load/evaluate the `adapter` namespace, and execute the `go` function. You
can stop the server with the `halt!` function. I'm using VS Code and Calva, so I
simply jack-in and run things from the editor. But from the command-line, one
could do:

```
clj -M:clj
user=> (require '[adapter])
user=> (adapter/go)
```
