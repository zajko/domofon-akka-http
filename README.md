# domofon-akka-http

Sample project to bootstrap Domofon API Server implementation using akka-http.

To get help: [![Join the chat at https://gitter.im/blstream/domofon-tck](https://badges.gitter.im/blstream/domofon-tck.svg)](https://gitter.im/blstream/domofon-tck)

## Getting started:

First, install SBT if you don't have it installed: http://www.scala-sbt.org/release/docs/Setup.html You could also use builtin launcher in root directory of `domofon-akka-http` using `./sbt`

```
git clone https://github.com/blstream/domofon-akka-http.git domofon-akka-http
cd domofon-akka-http
sbt test
```

First run will take quite long time, do it as soon as possible.

After everything is downloaded and tested you will get lots of:

```
501 Not Implemented
```

That means, there is some work for you to do.

Start SBT interactive shell:

```
sbt
```

And inside run test on every file change:

```
> ~test
```

After that, SBT will try to compile you source code and run tests on every change. Now you need to implement `DomofonService` to behave like defined in Swagger: http://editor.swagger.io/#/?import=https://raw.githubusercontent.com/blstream/domofon-api/gh-pages/domofon.yaml


When you want to run your server, it is super ease:

```
> run
```

You can also run your server in the background, so you could continue using SBT:

```
> reStart
```

You could even make it restart on every change:
```
> ~reStart
```

Ask on [![Join the chat at https://gitter.im/blstream/domofon-tck](https://badges.gitter.im/blstream/domofon-tck.svg)](https://gitter.im/blstream/domofon-tck) if you need any help, and none of us is around.
