# cljd_transit_example

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
version tagged `cljd-deps`.
