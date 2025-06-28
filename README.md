# Transit Example

A project to test out sending and receiving transit encoded data between a
running app and a server. It also served as a test of ClojureDart's support for
transitive dependencies which, when I first started, was still a work in
progress, but now it is working well!

This project uses the ClojureDart library
[`transit-cljd`](https://github.com/wevre/transit-cljd), which depends on a Dart
package, [`transit_dart`](https://github.com/wevre/transit-dart). The goal here
is to have all of these dependencies available by simply including `transit-cljd`
in `deps.edn`.

## Usage

This project is a combination of a ClojureDart+Flutter frontend, and a Clojure
backend running a Jetty server to receive transit requests and serve
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
